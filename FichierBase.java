import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Classe de base créée pour qu'on puisse hériter de celle-ci
 * Le but est de pouvoir créer un fichier d'analyse par type de fichier xml sans avoir à réimplémenter les méthodes de base.
 * Prend en argument le nom de fichier, AVEC extension (ex : "test.odp")
 */

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
        //fichier = new ZipFile(Dossier+File.separator+nomFichier);
        fichier = new ZipFile(nomFichier);
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Erreur interne");
            e.printStackTrace();
            System.out.println(e);
        }


    }

    /**
     * Crée un document en extrayant le fichier XML spécifié
     * @param Type : le fichier XML que vous souhaitez extraire, parmi l'énumération fournie (syntaxe : ListeXML.STYLES pour le fichier styles par ex)
     * @return le fichier xml parsé en type Document, pour voir ça :
     * @see org.w3c.dom.Document
     */

    public Document parse(ListeXML Type){
        FileHeader toParse = null;
        try {
            toParse = fichier.getFileHeader(Type+".xml");
        } catch (ZipException e) {
            System.out.println("Erreur dans la décompression du fichier");
            e.printStackTrace();
            System.out.println(e);
        }
        Document retour = null;
        try {
            retour = documentBuilder.parse(fichier.getInputStream(toParse));
        } catch (IOException | SAXException e) {
            e.printStackTrace();
            System.out.println(e);
            System.out.println("erreur lors du parse");
        }
        return retour;
    }
}
