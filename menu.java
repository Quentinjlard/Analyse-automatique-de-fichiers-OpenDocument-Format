import java.util.Scanner;

public class menu{

	public static void main(String[]args){

        Scanner clavier = new Scanner (System.in);
        boolean arret = false;
		System.out.println("--------------------------------------------------------");
		System.out.println("-----------------ANALYSEUR DE FICHIER-------------------");
		System.out.println("--------------------------------------------------------");

        while (!arret){
			
			System.out.println("-----------------------------------------");
			System.out.println("---Quel Fichier voulez-vous analysez? ---");
			System.out.println("------------Merci de choisir-------------");
			System.out.println("-----------------------------------------");
			System.out.println("META : tapez 1");
			System.out.println("STYLES : tapez 2");
			System.out.println("Content : tapez 2");
			System.out.println("Quitter : tapez 9");

			switch(clavier.nextInt()){
				case 1 : {
					System.out.println("Demarrage de l'analyse du META"); 
					metaodt.main(fichier());
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
		}
	}

	private static String[] fichier(){

		Scanner clavier = new Scanner (System.in);
		System.out.println("--------------------------------------------------------");
		System.out.println("---Merci de saisir le nom du fichier avec l'extension---");
		System.out.println("--------------------------------------------------------");
		String fichier = clavier.nextLine();
		String [] tab_fichier = new String[1];

		tab_fichier[0] = fichier;

		return tab_fichier;
	}
}
