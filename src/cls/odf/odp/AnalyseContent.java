package src.cls.odf.odp;

import cls.ext.OdfFiles;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Analyse le fichier content.xml d'un fichier odp.
 */

public class AnalyseContent {
    Document content;

    public AnalyseContent(Document content){
        this.content = content;
    }

    /**
     * Regarde si au moins une balise avec ce nom existe.
     */
    public boolean CheckExistence (String balise){
        boolean retour = false;
        NodeList balises = content.getElementsByTagName(balise);
        if (balises.getLength()>0){
            retour = true;
        }
        return retour;
    }

    /**
     * Détermine le nombre de slides dans la présentation
     * @return Le nombre de slides
     */

    public int NombreSlides (){
        NodeList liste = content.getElementsByTagName("draw:page");
        int retour = liste.getLength();
        return retour;
    }

    /**
     * Retourne les résultats de l'analyse
     */
    public String RetourFinal() {
        boolean NumeroPages = CheckExistence("text:page-number"); //Est-ce qu'il y a les numéros de pages affichés ?
        boolean Tableau = CheckExistence("table:table"); //Est-ce qu'il y a au moins un tableau ?
        boolean Animations = CheckExistence("anim:par"); //Est-ce qu'il y a au moins une animation ?
        //int nbSlides = NombreSlides(); Réalisé de manière plus fiable par Victor avec Styles.xml
        String retour =  "Il y a un tableau : " + Tableau + " Les numéros de slides sont affichés " + NumeroPages + " Il y a une animation : " + Animations;
        return retour;
    }
}


