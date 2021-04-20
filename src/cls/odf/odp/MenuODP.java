package cls.odf.odp;

import cls.ext.OdfFiles;


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