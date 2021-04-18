import org.w3c.dom.*;

/**
 * Analyse le fichier Meta.xml en partant de la base de FichierBase.
 */

public class AnalyseMeta extends FichierBase {
    Document Meta;

    public AnalyseMeta(String nomFichier){
        super(nomFichier);
        Meta= super.parse(ListeXML.META);
    }

   
    public String Metagenerator (){
        String generator = Meta.getElementsByTagName("meta:generator");
        return generator;
            }
    public String Titre (){
        String titre = Meta.getElementsByTagName("dc:title");
        return titre;
            }
    public String Auteurinitial(){
        String auteurini = Meta.getElementsByTagName("meta:initial-creator");
        return auteurini;
            }
    public String Auteur (){
        String auteur = Meta.getElementsByTagName("dc:creator");
        return auteur;
            }
    public String Date (){
        String date = Meta.getElementsByTagName("meta:creation-date");
        return date;
            }
    public String Metaedit (){
        String edit = Meta.getElementsByTagName("meta:editing-duration");
        return edit;
            }
    public String Nbdeparagraphesetnbdemots (){
        String mots = Meta.getElementsByTagName("meta:document-statistic");
        return mots;
            }
    public void Meta (){
        System.out.println(Metagenerator ());
        System.out.println("le titre est "+Titre ());
        System.out.println("l'auteur initial est "+Auteurinitial ());
        System.out.println("l'auteur est"+Auteur ());
        System.out.println("la date est"Date ());
        System.out.println(Metaedit ());
        System.out.println(Nbdeparagraphesetnbdemots ());
            }
        
    }
