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
import java.io.IOException;

/**
 * Format de donn&eacute;es sp&eacute;cifique, convertissant les informations du fichier odf en un objet java facilement utilisable
 * @author Corentin Machet
 * @version 1.0
 */
public class OdfFiles
{
    /**
     * Map associant les objets Document, issus de la conversion des fichiers xml, &agrave; leur nom de fichier
     */
    private HashMap<String, Document> map;
    /**
     * Nom de l'archive odf d'origine qui contenait les Document
     */
    private String zipName;
    /**
     * Archive d'origine
     */
    private ZipFile zip;

    /**
     * Constructeur 
     * @param zip archive d'origine
     * @param path chemin vers l'archive (pour r&eacute;cup&eacute;rer son nom)
     * @throws OdfException
     */
    public OdfFiles(ZipFile zip, String path) throws OdfException
    {
        this.zip = zip;
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

    /**
     * @param key nom du fichier xml que l'on souhaite r&eacute;cup&eacute;rer
     * @return le Document issu du fichier xml dont le nom est pass&eacute; en param&egrave;tre
     */
    public Document get(String key)
    {
        return map.get(key);
    }

    /**
     * @return le nom de l'archive odf d'origine
     */
    public String name()
    {
        return zipName;
    }

    /**
     * R&eacute;alise l'extraction compl&egrave;te de l'archive odf
     * @param destination le chemin vers la destination du r&eacute;sultat de l'extraction
     * @throws IOException
     */
    public void extract(String destination) throws IOException
    {
        File dir;
        if(destination == null)
            destination = ".";
        try
        {
            dir = new File(destination);
            if(dir.exists() && dir.isDirectory())
            {
                dir = new File(destination + File.separator + name() + ".extraction");
                if(!dir.mkdir())
                {
                    if(dir.exists() && dir.isDirectory())
                    {
                        System.err.println("\n\tLe fichier " + name() + " a deja ete extrait car " + dir.getPath() + " existe dans la destination.");
                        return;
                    }
                    throw new Exception("Le repertoire de destination n'a pas pu etre cree correctement.");
                }
            }
            else
            {
                throw new Exception("Le chemin d'acces au repertoire de destination est invalide.");
            }
        }
        catch(Exception e)
        {
            throw new IOException(e.getMessage());
        }
        zip.extractAll(dir.getPath());
    }

    public ZipFile getZip()
    {
        return zip;
    }
}
