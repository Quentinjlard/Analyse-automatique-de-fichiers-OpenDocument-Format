import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.lingala.zip4j.ZipFile;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;

public class FichierBase {
    String nomFichier;
    String Dossier;
    DocumentBuilder documentBuilder;
    ZipFile fichier;

    public String getNomFichier() {
        return this.nomFichier;
    }

    public FichierBase(String nomFichier){
        this.nomFichier = nomFichier;
        Dossier = System.getProperty("user.dir");
        try {
            fichier = new ZipFile(Dossier+File.separator+nomFichier);
        } catch (IOException e) {
            System.out.println("Fichier non trouvé");
        }
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Erreur interne");
        }


    }

    public Document parse(ListeXML Type){
        var toParse = fichier.getFileHeaders(Type+".xml");
        return documentBuilder.parse(fichier.getInputStream(toParse));
    }
}
