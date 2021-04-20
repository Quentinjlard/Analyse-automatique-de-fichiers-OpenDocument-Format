package cls.odf.odp;

import cls.ext.OdfFiles;
import cls.net.lingala.zip4j.ZipFile;
import cls.net.lingala.zip4j.exception.ZipException;
import cls.net.lingala.zip4j.model.FileHeader;
import org.w3c.dom.*;
import cls.net.lingala.zip4j.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

/**
 * Analyse le fichier Manifest.xml.
 */

public class AnalyseManifest {
    Document Manifest;

    /**
     * Parce que manifest.xml est dans un dossier dans le zip, on ne peut pas le récupérer aussi simplement que les autres (malheureusement)
     * @param zip le ZipFile de l'odf.
     */
    public AnalyseManifest(ZipFile zip){
        this.Manifest = Manifest;
        FileHeader toParse = null;
        DocumentBuilder documentBuilder = null;
        try {
             documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Erreur interne");
            e.printStackTrace();
            System.out.println(e);
        }
        try {
            toParse = zip.getFileHeader("META-INF"+ File.separator+"manifest.xml");
        } catch (ZipException e) {
            e.printStackTrace();
        }
        try {
            Manifest = documentBuilder.parse(zip.getInputStream(toParse));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compte le nombre d'images ajoutées.
     * @return Le nombre d'images dans la présentation sous forme d'entier
     */
    public int NombreImages (){
        NodeList liste = Manifest.getElementsByTagName("manifest:file-entry");
        int taille = liste.getLength();
        int retour = 0;
        System.out.println(taille);
        for (int i = 0; i<taille; i++) {
            Node current = liste.item(i);
            Element e = (Element)current;
            String attribut = e.getAttribute("manifest:media-type");
            if (attribut.equals("image/png") || attribut.equals("image/jpg")){
                retour++;
            }
        }
        return retour;
    }


}
