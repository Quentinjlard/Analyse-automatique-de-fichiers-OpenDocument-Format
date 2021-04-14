package cls.odf.odt;

import cls.ext.OdfFiles ;
import  java.util.Vector ;

import java.util.Scanner;

public class MenuODT
{	
	private boolean arret = false;

	public MenuODT(Vector<OdfFiles> vector)
	{

		Scanner clavier = new Scanner(System.in);
        int sousmenu = 0;
        
		System.out.println("--------------------------------------------------------");
		System.out.println("-------------------ANALYSEUR DE ODT---------------------");
		System.out.println("--------------------------------------------------------");

		System.out.println("--Type d'analyse --");
		System.out.println(" Analyse poussé : tapez 1");
		System.out.println(" Analyse simple : tapez 2");
		System.out.println(" Quitter : tapez 9");
		sousmenu = clavier.nextInt();

		switch(sousmenu)
		{
			case 1 :
			{
				int choix = 0;
				System.out.println("---Quel Fichier voulez-vous analysez? ---");
				System.out.println("--Merci de choisir--");
				System.out.println(" META 		: 	tapez 1");
				System.out.println(" STYLES 	: 	tapez 2");
				System.out.println(" Content	: 	tapez 2");
				System.out.println(" Quitter 	: 	tapez 9");
				choix = clavier.nextInt();
				switch(choix)
				{
					case 1 :
					{
						for(OdfFiles odf : vector)
						{
							System.out.println("Demarrage de l'analyse du META");
							String nom = odf.name();
							new metaodt(odf.get("meta.xml"), nom);
							System.out.println();
						}
						break;
					}	
					case 2 :
					{
						System.out.println("Demarrage de l'analyse du STYLES"); 
						break;
					}
					case 3 :
					{
						System.out.println("Demarrage de l'analyse du Content");
						for(OdfFiles odf : vector)
						{
							System.out.println("Demarrage de l'analyse du META");
							String nom = odf.name(); 
							new contentodt(odf.get("content.xml"), nom);
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
				System.out.println("---Quel Fichier voulez-vous analysez? ---");
				System.out.println("--Merci de choisir--");
				System.out.println(" Auteur : tapez 1");
				System.out.println(" Date de création : tapez 2");
				System.out.println(" Date de derniére modification : tapez 3");
				System.out.println(" Nombre de page : tapez 4");
				System.out.println(" Quitter : tapez 9");
				choix = clavier.nextInt();
				switch(choix)
				{
					case 1 :
					{
						for(OdfFiles odf : vector)
						{
							System.out.println("Demarrage de l'analyse du META");
							String nom = odf.name(); 
							new auteur(odf.get("meta.xml"), nom);
							System.out.println();
						}
						break;
					}	
					case 2 :
					{
						for(OdfFiles odf : vector)
						{
							System.out.println("Demarrage de l'analyse du META"); 
							String nom = odf.name();
							new dateCreation(odf.get("meta.xml"), nom);
							System.out.println();
						}
						break;
					}
					case 3 :
					{
						for(OdfFiles odf : vector)
						{
							System.out.println("Demarrage de l'analyse du META"); 
							String nom = odf.name();
							new derniereModification(odf.get("meta.xml"), nom);
							System.out.println();
						}
						break;
					}
					case 4 :
					{
						for(OdfFiles odf : vector)
						{
							System.out.println("Demarrage de l'analyse du META"); 
							String nom = odf.name();
							new styleG(odf.get("style.xml"), nom);
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