import java.util.Scanner;

public class menu{

	public static void main(String[]args){

        Scanner clavier = new Scanner (System.in);
        int sousmenu = 0;
        boolean arret = false;
		System.out.println("--------------------------------------------------------");
		System.out.println("-----------------ANALYSEUR DE FICHIER-------------------");
		System.out.println("--------------------------------------------------------");

		System.out.println("--Merci de saisir le nom du fichier avec l'extension--");
		String fichier = clavier.nextLine();

		String [] tab_fichier = new String[1];
		
		tab_fichier[0] = fichier;

        while (!arret){
		System.out.println("--Type de Fichier a analyser--");
		System.out.println(" odt  (Fichier TEXTE) : tapez 1");
		System.out.println(" ods  (Fichier TABLEAU) : tapez 2");
		System.out.println(" odp  (Fichier DIAPORAMA) : tapez 3");
        System.out.println("quitter : tapez 9");
		sousmenu = clavier.nextInt();
		switch(sousmenu)
		{
			case 1 :{
				int choix = 0;
				System.out.println("---Quel Fichier voulez-vous analysez? ---");
				System.out.println("Meta : Une liste de champs prédéfinis fait partie du standard : application, titre, description, sujet, mots-clés, auteur initial, auteur, imprimé par, date de création, date de dernière modification, date de dernière impression, durée d'édition, modèle utilisé, rechargement automatique, langue, nombre d'éditions, durée totale d'édition, statistiques sur le document. ");
				System.out.println("Styles : Les styles s'appliquent sur toutes sortes d'objets : paragraphes, cadres, pages, caractères, numérotations, etc");
				System.out.println("Content : il contient le contenu réel du document ");
				System.out.println("--Merci de choisir--");
				System.out.println("META : tapez 1");
				System.out.println("STYLES : tapez 2");
				System.out.println("Content : tapez 2");
				System.out.println("quitter : tapez 9");
				choix = clavier.nextInt();
					switch(choix){
						case 1 : {
							TestZipStream.main(tab_fichier);
							System.out.println("Demarrage de l'analyse du META"); 
							break;
						}	
						case 2 : {
							System.out.println("Demarrage de l'analyse du STYLES"); 
							break;
						}
						case 3 : {
							System.out.println("Demarrage de l'analyse du Content"); 
							break;
						}
						case 9 : {
							arret = true; 
							break;
						}
						default : {
							System.out.println("entrez un choix entre 1 et 3"); 
							break;
						}
					}
				} break;


			case 2 : System.out.println("Analyse Tableau en cours de création"); break;
			case 3 : System.out.println("Analyse Diaporama en couurs de création"); break;
			case 9 : arret = true; break;
			default : System.out.println("entrez un choix entre 1 et 3"); break;
			}
		}
	}
}
