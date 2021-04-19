package cls.odf.odt;

import cls.ext.OdfFiles ;
import  java.util.Vector ;
import java.util.Scanner;

public class MenuODT
{	
	public MenuODT(Vector<OdfFiles> vector)
	{
		int sousmenu = 0;
		boolean arret = false;
        
		while (!arret){
			System.out.println("--------------------------------------------------------");
			System.out.println("-------------------ANALYSEUR DE ODT---------------------");
			System.out.println("--------------------------------------------------------");
			System.out.println("---------------------Type d'analyse---------------------");
			System.out.println("--------------------------------------------------------");
			System.out.println(" Tapez 1 : Analyse complete");
			System.out.println(" Tapez 2 : Analyse simple");
			System.out.println(" Tapez 9 : Quitter");
			sousmenu = new Scanner(System.in).nextInt();
			switch(sousmenu)
			{
				case 1 :
				{
					int choix = 0;
					System.out.println("--------------------------------------------------------");
					System.out.println("-----------Quel Fichier voulez-vous analysez?-----------");
					System.out.println("--------------------Merci de choisir--------------------");
					System.out.println("--------------------------------------------------------");
					System.out.println(" Tapez 1 : META");
					//System.out.println(" STYLES 	: 	tapez 2");
					System.out.println(" Tapez 2 : Content");
					System.out.println(" Tapez 9 : Quitter");
					choix = new Scanner(System.in).nextInt();
					switch(choix)
					{
						case 1 :
						{
							for(OdfFiles odf : vector)
							{
								System.out.println("Demarrage de l'analyse du META");
								String nom = odf.name();
								String nomsans = nom.substring(0,nom.length() - 4); 
								new metaodt(odf.get("meta.xml"), nomsans);
								System.out.println();
							}
							break;
						}	
						/*case 2 :
						{
							System.out.println("Demarrage de l'analyse du STYLES");
							for(OdfFiles odf : vector)
							{
								System.out.println("Demarrage de l'analyse du META");
								String nom = odf.name(); 
								new styleG(odf.get("styles.xml"), nom);
								System.out.println();
							}
							break; 
						}*/
						case 2 :
						{
							System.out.println("Demarrage de l'analyse du Content");
							for(OdfFiles odf : vector)
							{
								System.out.println("Demarrage de l'analyse du META");
								String nom = odf.name(); 
								String nomsans = nom.substring(0,nom.length() - 4); 
								new contentodt(odf.get("content.xml"), nomsans);
								System.out.println();
							}
							break;
						}
						case 9 :
						{
							arret = true; 
							break;
						}
						default :
						{
							System.out.println("entrez un choix entre 1 et 3"); 
							break;
						}
					}
					break;
				}

				case 2 :
				{
					int choix = 0;
					System.out.println("--------------------------------------------------------");
					System.out.println("----------Quel Fichier voulez-vous analysez?------------");
					System.out.println("------------------ Merci de choisir --------------------");
					System.out.println("--------------------------------------------------------");
					System.out.println(" Tapez 1 : Auteur");
					System.out.println(" Tapez 2 : Date de creation");
					System.out.println(" Tapez 3 : Date de derniere modification");
					System.out.println(" Tapez 4 : Des attirbut (page,etc ...)");
					System.out.println(" Tapez 5 : Style personalise");
					System.out.println(" Tapez 9 : Quitter");
					choix = new Scanner(System.in).nextInt();
					switch(choix)
					{
						case 1 :
						{
							for(OdfFiles odf : vector)
							{
								System.out.println("Recherche de l'auteur");
								String nom = odf.name();
								String nomsans = nom.substring(0,nom.length() - 4); 
								new auteur(odf.get("meta.xml"), nomsans);
								System.out.println();
							}
							break;
						}	
						case 2 :
						{
							for(OdfFiles odf : vector)
							{
								System.out.println("Recherche de la date de création"); 
								String nom = odf.name();
								String nomsans = nom.substring(0,nom.length() - 4); 
								new dateCreation(odf.get("meta.xml"), nomsans);
								System.out.println();
							}
							break;
						}
						case 3 :
						{
							for(OdfFiles odf : vector)
							{
								System.out.println("Recherche de la date de derniere modification création"); 
								String nom = odf.name();
								String nomsans = nom.substring(0,nom.length() - 4); 
								new derniereModification(odf.get("meta.xml"), nomsans);
								System.out.println();
							}
							break;
						}
						case 4 :
						{
							for(OdfFiles odf : vector)
							{
								System.out.println("Recherche des attirbut (page,etc ...)"); 
								String nom = odf.name();
								String nomsans = nom.substring(0,nom.length() - 4); 
								new attributes(odf.get("meta.xml"), nomsans);
								System.out.println();
							}
							break;
						}
						case 5 :
						{
							for(OdfFiles odf : vector)
							{
								System.out.println("Demarrage du style personalise"); 
								String nom = odf.name();
								String nomsans = nom.substring(0,nom.length() - 4); 
								new stylepersonnaliser(odf.get("styles.xml"), nomsans);
								System.out.println();
							}
							break;
						}
						case 9 :
						{
							arret = true;  
							break;
						}
						default :
						{
							System.out.println("entrez un choix entre 1 et 4"); 
							break;
						}
					}
					break;
				}

				case 9 :
				{
					arret = true;  
					break;
				}
					
				default :
				{
					System.out.println("entrez un choix entre 1 et 2"); 
					break;
				} 
			}
		}
	}
}