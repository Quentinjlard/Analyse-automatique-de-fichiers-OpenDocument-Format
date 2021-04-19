package cls.net.lingala.zip4j.tasks;

import cls.net.lingala.zip4j.exception.ZipException;
import cls.net.lingala.zip4j.headers.HeaderUtil;
import cls.net.lingala.zip4j.headers.HeaderWriter;
import cls.net.lingala.zip4j.io.outputstream.SplitOutputStream;
import cls.net.lingala.zip4j.io.outputstream.ZipOutputStream;
import cls.net.lingala.zip4j.model.FileHeader;
import cls.net.lingala.zip4j.model.Zip4jConfig;
import cls.net.lingala.zip4j.model.ZipModel;
import cls.net.lingala.zip4j.model.ZipParameters;
import cls.net.lingala.zip4j.model.enums.CompressionMethod;
import cls.net.lingala.zip4j.model.enums.EncryptionMethod;
import cls.net.lingala.zip4j.progress.ProgressMonitor;
import cls.net.lingala.zip4j.tasks.RemoveFilesFromZipTask.RemoveFilesFromZipTaskParameters;
import cls.net.lingala.zip4j.util.BitUtils;
import cls.net.lingala.zip4j.util.FileUtils;
import cls.net.lingala.zip4j.util.InternalZipConstants;
import cls.net.lingala.zip4j.util.Zip4jUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cls.net.lingala.zip4j.headers.HeaderUtil.getFileHeader;
import static cls.net.lingala.zip4j.model.ZipParameters.SymbolicLinkAction.INCLUDE_LINK_AND_LINKED_FILE;
import static cls.net.lingala.zip4j.model.ZipParameters.SymbolicLinkAction.INCLUDE_LINK_ONLY;
import static cls.net.lingala.zip4j.model.enums.CompressionMethod.DEFLATE;
import static cls.net.lingala.zip4j.model.enums.CompressionMethod.STORE;
import static cls.net.lingala.zip4j.model.enums.EncryptionMethod.NONE;
import static cls.net.lingala.zip4j.model.enums.EncryptionMethod.ZIP_STANDARD;
import static cls.net.lingala.zip4j.progress.ProgressMonitor.Task.ADD_ENTRY;
import static cls.net.lingala.zip4j.progress.ProgressMonitor.Task.CALCULATE_CRC;
import static cls.net.lingala.zip4j.progress.ProgressMonitor.Task.REMOVE_ENTRY;
import static cls.net.lingala.zip4j.util.CrcUtil.computeFileCrc;
import static cls.net.lingala.zip4j.util.FileUtils.assertFilesExist;
import static cls.net.lingala.zip4j.util.FileUtils.getRelativeFileName;
import static cls.net.lingala.zip4j.util.Zip4jUtil.epochToExtendedDosTime;

public abstract class AbstractAddFileToZipTask<T> extends AsyncZipTask<T> {

  private ZipModel zipModel;
  private char[] password;
  private HeaderWriter headerWriter;

  AbstractAddFileToZipTask(ZipModel zipModel, char[] password, HeaderWriter headerWriter,
                           AsyncTaskParameters asyncTaskParameters) {
    super(asyncTaskParameters);
    this.zipModel = zipModel;
    this.password = password;
    this.headerWriter = headerWriter;

  }

  void addFilesToZip(List<File> filesToAdd, ProgressMonitor progressMonitor, ZipParameters zipParameters,
                     Zip4jConfig zip4jConfig) throws IOException {

    assertFilesExist(filesToAdd, zipParameters.getSymbolicLinkAction());

    byte[] readBuff = new byte[zip4jConfig.getBufferSize()];
    List<File> updatedFilesToAdd = removeFilesIfExists(filesToAdd, zipParameters, progressMonitor, zip4jConfig);

    try (SplitOutputStream splitOutputStream = new SplitOutputStream(zipModel.getZipFile(), zipModel.getSplitLength());
         ZipOutputStream zipOutputStream = initializeOutputStream(splitOutputStream, zip4jConfig)) {

      for (File fileToAdd : updatedFilesToAdd) {
        verifyIfTaskIsCancelled();
        ZipParameters clonedZipParameters = cloneAndAdjustZipParameters(zipParameters, fileToAdd, progressMonitor);
        progressMonitor.setFileName(fileToAdd.getAbsolutePath());

        if (FileUtils.isSymbolicLink(fileToAdd)) {
          if (addSymlink(clonedZipParameters)) {
            addSymlinkToZip(fileToAdd, zipOutputStream, clonedZipParameters, splitOutputStream);

            if (INCLUDE_LINK_ONLY.equals(clonedZipParameters.getSymbolicLinkAction())) {
              continue;
            }
          }
        }

        addFileToZip(fileToAdd, zipOutputStream, clonedZipParameters, splitOutputStream, progressMonitor, readBuff);
      }
    }
  }

  private void addSymlinkToZip(File fileToAdd, ZipOutputStream zipOutputStream, ZipParameters zipParameters,
                               SplitOutputStream splitOutputStream) throws IOException {

    ZipParameters clonedZipParameters = new ZipParameters(zipParameters);
    clonedZipParameters.setFileNameInZip(replaceFileNameInZip(zipParameters.getFileNameInZip(), fileToAdd.getName()));
    clonedZipParameters.setEncryptFiles(false);
    clonedZipParameters.setCompressionMethod(CompressionMethod.STORE);

    zipOutputStream.putNextEntry(clonedZipParameters);

    String symLinkTarget = FileUtils.readSymbolicLink(fileToAdd);
    zipOutputStream.write(symLinkTarget.getBytes());

    closeEntry(zipOutputStream, splitOutputStream, fileToAdd, true);
  }

  private void addFileToZip(File fileToAdd, ZipOutputStream zipOutputStream, ZipParameters zipParameters,
                            SplitOutputStream splitOutputStream, ProgressMonitor progressMonitor,
                            byte[] readBuff) throws IOException {

    zipOutputStream.putNextEntry(zipParameters);
    int readLen;
    if (fileToAdd.exists() && !fileToAdd.isDirectory()) {
      try (InputStream inputStream = new FileInputStream(fileToAdd)) {
        while ((readLen = inputStream.read(readBuff)) != -1) {
          zipOutputStream.write(readBuff, 0, readLen);
          progressMonitor.updateWorkCompleted(readLen);
          verifyIfTaskIsCancelled();
        }
      }
    }

    closeEntry(zipOutputStream, splitOutputStream, fileToAdd, false);
  }

  private void closeEntry(ZipOutputStream zipOutputStream, SplitOutputStream splitOutputStream, File fileToAdd,
                          boolean isSymlink) throws IOException {
    FileHeader fileHeader = zipOutputStream.closeEntry();
    byte[] fileAttributes = FileUtils.getFileAttributes(fileToAdd);

    if (!isSymlink) {
      // Unset the symlink byte if the entry being added is a symlink, but the original file is being added
      fileAttributes[3] = BitUtils.unsetBit(fileAttributes[3], 5);
    }

    fileHeader.setExternalFileAttributes(fileAttributes);

    updateLocalFileHeader(fileHeader, splitOutputStream);
  }

  long calculateWorkForFiles(List<File> filesToAdd, ZipParameters zipParameters) throws ZipException {
    long totalWork = 0;

    for (File fileToAdd : filesToAdd) {
      if (!fileToAdd.exists()) {
        continue;
      }

      if (zipParameters.isEncryptFiles() && zipParameters.getEncryptionMethod() == EncryptionMethod.ZIP_STANDARD) {
        totalWork += (fileToAdd.length() * 2); // for CRC calculation
      } else {
        totalWork += fileToAdd.length();
      }

      //If an entry already exists, we have to remove that entry first and then add content again.
      //In this case, add corresponding work
      String relativeFileName = getRelativeFileName(fileToAdd, zipParameters);
      FileHeader fileHeader = getFileHeader(getZipModel(), relativeFileName);
      if (fileHeader != null) {
        totalWork += (getZipModel().getZipFile().length() - fileHeader.getCompressedSize());
      }
    }

    return totalWork;
  }

  ZipOutputStream initializeOutputStream(SplitOutputStream splitOutputStream, Zip4jConfig zip4jConfig) throws IOException {
    if (zipModel.getZipFile().exists()) {
      splitOutputStream.seek(HeaderUtil.getOffsetStartOfCentralDirectory(zipModel));
    }

    return new ZipOutputStream(splitOutputStream, password, zip4jConfig, zipModel);
  }

  void verifyZipParameters(ZipParameters parameters) throws ZipException {
    if (parameters == null) {
      throw new ZipException("cannot validate zip parameters");
    }

    if (parameters.getCompressionMethod() != STORE && parameters.getCompressionMethod() != DEFLATE) {
      throw new ZipException("unsupported compression type");
    }

    if (parameters.isEncryptFiles()) {
      if (parameters.getEncryptionMethod() == NONE) {
        throw new ZipException("Encryption method has to be set, when encrypt files flag is set");
      }

      if (password == null || password.length <= 0) {
        throw new ZipException("input password is empty or null");
      }
    } else {
      parameters.setEncryptionMethod(NONE);
    }
  }

  void updateLocalFileHeader(FileHeader fileHeader, SplitOutputStream splitOutputStream) throws IOException {
    headerWriter.updateLocalFileHeader(fileHeader, getZipModel(), splitOutputStream);
  }

  private ZipParameters cloneAndAdjustZipParameters(ZipParameters zipParameters, File fileToAdd,
                                                    ProgressMonitor progressMonitor) throws IOException {
    ZipParameters clonedZipParameters = new ZipParameters(zipParameters);
    clonedZipParameters.setLastModifiedFileTime(epochToExtendedDosTime((fileToAdd.lastModified())));

    if (fileToAdd.isDirectory()) {
      clonedZipParameters.setEntrySize(0);
    } else {
      clonedZipParameters.setEntrySize(fileToAdd.length());
    }

    clonedZipParameters.setWriteExtendedLocalFileHeader(false);
    clonedZipParameters.setLastModifiedFileTime(fileToAdd.lastModified());

    if (!Zip4jUtil.isStringNotNullAndNotEmpty(zipParameters.getFileNameInZip())) {
      String relativeFileName = getRelativeFileName(fileToAdd, zipParameters);
      clonedZipParameters.setFileNameInZip(relativeFileName);
    }

    if (fileToAdd.isDirectory()) {
      clonedZipParameters.setCompressionMethod(CompressionMethod.STORE);
      clonedZipParameters.setEncryptionMethod(EncryptionMethod.NONE);
      clonedZipParameters.setEncryptFiles(false);
    } else {
      if (clonedZipParameters.isEncryptFiles() && clonedZipParameters.getEncryptionMethod() == ZIP_STANDARD) {
        progressMonitor.setCurrentTask(CALCULATE_CRC);
        clonedZipParameters.setEntryCRC(computeFileCrc(fileToAdd, progressMonitor));
        progressMonitor.setCurrentTask(ADD_ENTRY);
      }

      if (fileToAdd.length() == 0) {
        clonedZipParameters.setCompressionMethod(CompressionMethod.STORE);
      }
    }

    return clonedZipParameters;
  }

  private List<File> removeFilesIfExists(List<File> files, ZipParameters zipParameters, ProgressMonitor progressMonitor,
                                         Zip4jConfig zip4jConfig)
      throws ZipException {

    List<File> filesToAdd = new ArrayList<>(files);
    if (!zipModel.getZipFile().exists()) {
      return filesToAdd;
    }

    for (File file : files) {
      String fileName = getRelativeFileName(file, zipParameters);

      FileHeader fileHeader = getFileHeader(zipModel, fileName);
      if (fileHeader != null) {
        if (zipParameters.isOverrideExistingFilesInZip()) {
          progressMonitor.setCurrentTask(REMOVE_ENTRY);
          removeFile(fileHeader, progressMonitor, zip4jConfig);
          verifyIfTaskIsCancelled();
          progressMonitor.setCurrentTask(ADD_ENTRY);
        } else {
          filesToAdd.remove(file);
        }
      }
    }

    return filesToAdd;
  }

  void removeFile(FileHeader fileHeader, ProgressMonitor progressMonitor, Zip4jConfig zip4jConfig) throws ZipException {
    AsyncTaskParameters asyncTaskParameters = new AsyncTaskParameters(null, false, progressMonitor);
    RemoveFilesFromZipTask removeFilesFromZipTask = new RemoveFilesFromZipTask(zipModel, headerWriter, asyncTaskParameters);
    RemoveFilesFromZipTaskParameters parameters = new RemoveFilesFromZipTaskParameters(
        Collections.singletonList(fileHeader.getFileName()), zip4jConfig);
    removeFilesFromZipTask.execute(parameters);
  }

  private String replaceFileNameInZip(String fileInZipWithPath, String newFileName) {
    if (fileInZipWithPath.contains(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
      return fileInZipWithPath.substring(0, fileInZipWithPath.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR) + 1) + newFileName;
    }

    return newFileName;
  }


  private boolean addSymlink(ZipParameters zipParameters) {
    return INCLUDE_LINK_ONLY.equals(zipParameters.getSymbolicLinkAction()) ||
        INCLUDE_LINK_AND_LINKED_FILE.equals(zipParameters.getSymbolicLinkAction());
  }

  @Override
  protected ProgressMonitor.Task getTask() {
    return ProgressMonitor.Task.ADD_ENTRY;
  }

  protected ZipModel getZipModel() {
    return zipModel;
  }
}
