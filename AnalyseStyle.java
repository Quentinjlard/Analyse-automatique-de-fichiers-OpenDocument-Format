import org.w3c.dom.*;

/**
 * Analyse le fichier Meta.xml en partant de la base de FichierBase.
 */

public class AnalyseStyle extends FichierBase {
    Document Style;

    public AnalyseStyle(String nomFichier){
        super(nomFichier);
        Style= super.parse(ListeXML.META);
    }

   public int NombrePages (){
        NodeList liste = Style.getElementsByTagName("style:presentation-page-layout");
        int taille = liste.getLength();
        return taille;}
   public void TypeDiapositive (){
       NodeList liste = Style.getElementsByTagName("style:presentation-page-layout");
       int taille = liste.getLength();
       for (int i = 1; i<taille+1; i++) {
           Node current = liste.item(i);
           Element e = (Element)current;
           String styles = e.getAttribute("style:display-name");
           System.out.println("la page"+i+"a pour style de diapo"+styles);}}
        
    public void Style (){
        System.out.println("le nombre de page est de "+NombrePages ());
        TypeDiapositive ();
        
            }
        
    }
