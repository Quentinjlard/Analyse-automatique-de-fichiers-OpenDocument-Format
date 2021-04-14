package cls.ext;

import java.util.HashMap;
import java.util.List;
import cls.net.lingala.zip4j.model.FileHeader;
import cls.exception.*;
import cls.net.lingala.zip4j.ZipFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

import java.io.File;

public class OdfFiles
{
    private HashMap<String, Document> map;
    private String zipName;

    public OdfFiles(ZipFile zip, String path) throws OdfException
    {
        map = new HashMap<String, Document>();
        List<FileHeader> headers;
        try
        {
            headers = zip.getFileHeaders();
            for(FileHeader header : headers)
            {
                try
                {
                    String name = header.getFileName();
                    if(name.endsWith(".xml") && !name.contains("/"))
                    {
                        var is = zip.getInputStream(zip.getFileHeader(name));
                        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                        Document doc = builder.parse(is);
                        is.close();
                        map.put(name, doc);
                        zipName = (new File(path)).getName();
                    }
                }
                catch(Exception ex)
                {
                    ex.printStackTrace();
                    System.exit(0);
                }
            }
        }
        catch(Exception e)
        {
            throw new OdfException(ExceptionTypes.ODFFILE_FAILED);
        }
    }

    public Document get(String key)
    {
        return map.get(key);
    }

    public String name()
    {
        return zipName;
    }
}
