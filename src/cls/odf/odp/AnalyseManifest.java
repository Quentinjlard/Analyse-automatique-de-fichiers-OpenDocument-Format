package src.cls.odf.odp;

import cls.ext.OdfFiles;
import org.w3c.dom.*;

import java.util.Vector;

/**
 * Analyse le fichier Manifest.xml en partant de la base de FichierBase.
 */

public class AnalyseManifest {
    Document Manifest;

    public AnalyseManifest(Document Manifest){
        this.Manifest = Manifest;
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
