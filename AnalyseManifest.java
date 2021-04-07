import org.w3c.dom.*;

/**
 * Analyse le fichier Manifest.xml en partant de la base de FichierBase.
 */

public class AnalyseManifest extends FichierBase {
    Document Manifest;

    public AnalyseManifest(String nomFichier){
        super(nomFichier);
        Manifest = super.parse(ListeXML.MANIFEST);
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
