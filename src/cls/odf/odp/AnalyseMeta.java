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
                Element e = (Element)current;
                generator = e.getAttribute("meta:generator");}

            return generator;
        }
        public String Titre () {
            NodeList liste = Meta.getElementsByTagName("dc:title");
            int taille = liste.getLength();

            String titre = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                Element e = (Element) current;
                titre = e.getAttribute("dc:title");
            }

            return titre;
        }

        public String Auteurinitial() {
            NodeList liste = Meta.getElementsByTagName("meta:initial-creator");
            int taille = liste.getLength();

            String auteurini = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                Element e = (Element) current;
                auteurini = e.getAttribute("meta:initial-creator");
            }

            return auteurini;
        }
        public String Auteur () {
            NodeList liste = Meta.getElementsByTagName("dc:creator");
            int taille = liste.getLength();

            String auteur = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                Element e = (Element) current;
                auteur = e.getAttribute("dc:creator");
            }

            return auteur;
        }
        public String Date () {
            NodeList liste = Meta.getElementsByTagName("meta:creation-date");
            int taille = liste.getLength();

            String date = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                Element e = (Element) current;
                date = e.getAttribute("meta:creation-date");
            }

            return date;
        }
        public String Metaedit () {
            NodeList liste = Meta.getElementsByTagName("meta:editing-duration");
            int taille = liste.getLength();

            String edit = null;
            for (int i = 0; i < taille; i++) {
                Node current = liste.item(i);
                Element e = (Element) current;
                edit = e.getAttribute("meta:editing-duration");
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
            int compteur =0;
            int a;
            Scanner clavier = new Scanner(System.in);
            while(compteur!=8){
                System.out.print("tape 1 pour titre ");
                System.out.print("tape 2 pour auteur ");
                System.out.print(" tape 3 pour auteurinitiale");
                System.out.print(" tape 4 pour metagenerator");
                System.out.print(" tape 5 pour date");
                System.out.print(" tape 6 pour metaedition");
                System.out.print(" tape 7 pour nombres de paragraphes");
                System.out.print(" tape 8 pour nombres de mots");
                a = clavier.nextInt();
                if(a==1) {
                    System.out.println("le titre est "+Titre ());
                    compteur++;
                }
                if(a==2) {
                    System.out.println("l'auteur est"+Auteur ());
                    compteur++;
                }
                if(a==3) {
                    System.out.println("l'auteur initial est "+Auteurinitial ());
                    compteur++;
                }
                if(a==4) {
                    System.out.println(Metagenerator ());
                    compteur++;
                }
                if(a==5) {
                    System.out.println("la date est" + Date ());
                    compteur++;
                }
                if(a==6) {
                    System.out.println(Metaedit ());
                    compteur++;
                }
                if(a==7) {
                    System.out.println("il y a"+ Nbdeparagraphes() +"paragraphes");
                    compteur++;
                }
                if(a==8) {
                    System.out.println("il y a"+ Nbdemots() +"mots");
                    compteur++;
                }}
        }

    }
