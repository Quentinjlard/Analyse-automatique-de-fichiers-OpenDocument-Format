package cls.odf.odp;

import cls.ext.OdfFiles;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class MenuODP {
    public MenuODP(Vector<OdfFiles> vector) {
        boolean arret = false;
        int choix;
        while (!arret) {

            System.out.println("ANALYSE ODF, EDITION ODP");

            System.out.println("Que voulez-vous savoir ?");
            System.out.println("Information de base (meta) : tapez 1");
            System.out.println("Analyse totale : tapez 2");
            System.out.println("Sortie csv : tapez 3");
            System.out.println(" Quitter : tapez 9");
            choix = new Scanner(System.in).nextInt();
            switch (choix) {
                case 1: {
                    for (OdfFiles odf : vector) {
                        AnalyseMeta metaAnalyser = new AnalyseMeta(odf.get("meta.xml"));

                        metaAnalyser.Meta();
                    }
                    break;
                }

                case 2: {
                    for (OdfFiles odf : vector) {

                        AnalyseMeta metaAnalyser = new AnalyseMeta(odf.get("meta.xml"));
                        System.out.println("Informations de base : ");
                        metaAnalyser.Meta();
                        AnalyseManifest manifestAnalyser = new AnalyseManifest(odf.getZip());
                        System.out.println("Nombre d'images : " + manifestAnalyser.NombreImages());
                        AnalyseStyle styleAnalyser = new AnalyseStyle(odf.get("styles.xml"));
                        styleAnalyser.Style();
                        AnalyseContent contentAnalyser = new AnalyseContent(odf.get("content.xml"));
                        System.out.println("Analyse du contenu : " + contentAnalyser.RetourFinal());
                    }
                    break;
                }
                case 3: {
                    for (OdfFiles odf : vector) {
                        AnalyseMeta metaAnalyser = new AnalyseMeta(odf.get("meta.xml"));
                        AnalyseManifest manifestAnalyser = new AnalyseManifest(odf.getZip());
                        AnalyseContent contentAnalyser = new AnalyseContent(odf.get("content.xml"));
                        AnalyseStyle styleAnalyser = new AnalyseStyle(odf.get("styles.xml"));
                        FileWriter writer = null;
                        try {
                            writer = new FileWriter(odf.name() + ".csv");
                            PrintWriter print = new PrintWriter(writer);
                            print.println("Titre," + metaAnalyser.Titre());
                            print.println("Date creation," + metaAnalyser.Date());
                            print.println("NbSlides," + contentAnalyser.NombreSlides());
                            print.println("NbImages," + manifestAnalyser.NombreImages());
                            print.println("TableauExiste,"+contentAnalyser.CheckExistence("table:table"));
                            print.println("NbPagesExiste,"+contentAnalyser.CheckExistence("text:page-number"));
                            print.println("AnimExiste,"+contentAnalyser.CheckExistence("anim:par"));
                            writer.close();
                            System.out.println("Analyse réalisée : consultez " + odf.name());
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.out.println("erreur lors de la création du fichier csv");
                        }
                    }
                    break;

                }
                case 9: {
                    arret = true;
                    break;
                }
                default : {
                        System.out.println("entrez un choix : 1,2 ou 9");
                        break;
                    }
                }
            }


        }
    }