package cls.odf.odp;
import cls.ext.OdfFiles;
import org.w3c.dom.*;
import java.util.Scanner;

/**
 * Analyse le fichier Meta.xml
 */

public class AnalyseMeta {
    Document Meta;

    public AnalyseMeta(Document Meta){
            this.Meta = Meta;
    }

        public String Metagenerator (){
            NodeList liste = Meta.getElementsByTagName("meta:generator");
            int taille = liste.getLength();
            String generator = null;

            for (int i = 0; i<taille; i++) {
                Node current = liste.item(i);
                generator = current.getTextContent();}

            return generator;
        }
        public String Titre () {
            NodeList liste = Meta.getElementsByTagName("dc:title");
            int taille = liste.getLength();

            String titre = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                titre = current.getTextContent();
            }

            return titre;
        }

        public String Auteurinitial() {
            NodeList liste = Meta.getElementsByTagName("meta:initial-creator");
            int taille = liste.getLength();

            String auteurini = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                auteurini = current.getTextContent();
            }

            return auteurini;
        }
        public String Auteur () {
            NodeList liste = Meta.getElementsByTagName("dc:creator");
            int taille = liste.getLength();

            String auteur = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                auteur = current.getTextContent();
            }

            return auteur;
        }
        public String Date () {
            NodeList liste = Meta.getElementsByTagName("meta:creation-date");
            int taille = liste.getLength();

            String date = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                date = current.getTextContent();
            }

            return date;
        }
        public String Metaedit () {
            NodeList liste = Meta.getElementsByTagName("meta:editing-duration");
            int taille = liste.getLength();

            String edit = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                edit = current.getTextContent();
            }

            return edit;
        }
        public String Nbdeparagraphes () {
            NodeList liste = Meta.getElementsByTagName("meta:document-statistic");
            int taille = liste.getLength();

            String para = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                Element e = (Element) current;
                para = e.getAttribute("meta:paragraph-count");
            }

            return para;
        }
        public String Nbdemots () {
            NodeList liste = Meta.getElementsByTagName("meta:document-statistic");
            int taille = liste.getLength();

            String mots = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                Element e = (Element) current;
                mots = e.getAttribute("meta:word-count");
            }

            return mots;
        }
        public void Meta (){
            boolean arret = false;
            int a;
            Scanner clavier = new Scanner(System.in);
            while(!arret){
                System.out.print("tape 1 pour titre ");
                System.out.print("tape 2 pour auteur ");
                System.out.print(" tape 3 pour auteurinitiale");
                System.out.print(" tape 4 pour metagenerator");
                System.out.print(" tape 5 pour date");
                System.out.print(" tape 6 pour metaedition");
                System.out.print(" tape 7 pour nombres de paragraphes");
                System.out.print(" tape 8 pour nombres de mots");
                System.out.print(" tape 9 pour sortir");
                a = clavier.nextInt();
                if(a==1) {
                    System.out.println("le titre est "+Titre ());
                }
                if(a==2) {
                    System.out.println("l'auteur est "+Auteur ());
                }
                if(a==3) {
                    System.out.println("l'auteur initial est "+Auteurinitial ());
                }
                if(a==4) {
                    System.out.println(Metagenerator ());
                }
                if(a==5) {
                    System.out.println("la date est " + Date ());
                }
                if(a==6) {
                    System.out.println(Metaedit ());
                }
                if(a==7) {
                    System.out.println("il y a "+ Nbdeparagraphes() +" paragraphes");
                }
                if(a==8) {
                    System.out.println("il y a "+ Nbdemots() +" mots");
                }
                if(a==9) {
                    arret = true;
                }
            }
        }

    }
