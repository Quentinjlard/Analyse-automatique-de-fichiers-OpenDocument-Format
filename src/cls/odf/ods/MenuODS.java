package cls.odf.ods;

import cls.ext.OdfFiles;
import java.util.Vector;

import java.util.Scanner;

public class MenuODS{

    public MenuODS(Vector<OdfFiles> vector){

      String odfFilePath;

  		Scanner clavier = new Scanner(System.in);
  		int i = 0;
          int sousmenu = 0;
          boolean arret = false;
  		System.out.println("----Début analyse ODS----");
      System.out.println("Infos generales : tapez 1");
      System.out.println("Contenu : tapez 2");
      System.out.println("Quittez : tapez 9");
      int choix = clavier.nextInt();

      switch(choix){
        case 1:
        {
          for(OdfFiles odf : vector){
                String nom = odf.name();
								/*En commentaire car sur libreoffice je sais pas comment on ajoute un auteur
                new auteur(odf.get("meta.xml"), nom);
								System.out.println();
                */
                new dateCreation(odf.get("meta.xml"), nom);
                System.out.println();
                /**new lastModif(odf.get("meta.xml"), nom);
                System.out.println();
                */
          }
          /*affichera l'auteur, la date de création (dans le meta.xml), la dernière modification, nombre de page(style.xml)*/
          i++;
          break;
        }

        case 2:
        {
          for(OdfFiles odf : vector){
            String nom = odf.name();
            new content.ods(odf.get("content.xml"));
            System.out.println();
          }
          /*affichera le contenu général du tableur Soit en passannt par le content.xml / Soit en affichant le png du thumbtails*/
          i++;
          break;
        }

        case 9:
        {
          arret = true;
          break;
        }
        default :
        {
          System.out.println("Choississez entre 1, 2 et 9");
          break;
        }
      }




        for (OdfFiles odf : vector){
            System.out.println(odf.get("content.xml").getNodeName());
        } 
    }
}
