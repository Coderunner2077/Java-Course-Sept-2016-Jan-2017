import java.util.Scanner;
public class MyC {

	public static void main(String[] args) {
		// TABLEAUX
		int entiers[] = {0, 1, 2, 3, 4}; // je n'indique pas la taile du tableau
		double decimaux[] = {0.0, 0.1, 0.2, 0.3, 0.4};
		char tableauCaracteres[] = {'a', 'b', 'c', 'd', 'e'}; // CE N'EST PAS UNE CHAINE DE CARACTERES, MAIS BEL ET BIEN UN TABLEAU DE char
		// NOTE : PAS DE CARACTERES DE FIN DE CHAINES DANS LE TABLEAU DE char ?!
		
		String tableauChaines[] ={"chaine1", "chaine2", "chaine3", "chaine4"};//peut s'écrire:String[] tableauChaines = ...
		//Voici comment créer des tableaux vides:
		int integers[] = new int[5]; // ON DOIT INDIQUER LE NOMBRE DE CASES CONTENUES DANS LE TABLEAU
		//Ou encore :
		int[] integer = new int[5];
		
		//TABLEAUX MULTIDIMENSIONNELS
		int premierNombres[][] = {{0, 2, 4, 6, 8}, {1, 3, 5, 7, 9}};
		
		//PETIT BEMOL	
		String[] str = new String[10];
		//L'instruction de la ligne 26 va déclencher une exception
		//Car j'essaye d'écrire à la case 11 de mon tableau 
		//Alors que celui-ci n'en contient que 10 
		// D'où le message suivant : java.lang.ArrayIndexOutOfBoundsException
		//str[10] = "Un exception";
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// UTILISER ET RECHERCHER DANS UN TABLEAU
		int i = 0;
		char tabCaracteres[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
		char continuer = 'O', carac = ' ';
		Scanner sc = new Scanner(System.in);
		do// boucle principale
		{
			do
			{// On répète la boucle tant que l'utilisateur n'a pas rentré une lettre du tableau
				i = 0;
				System.out.println("Rentrez une lettre en minuscule SVP");
				carac = sc.nextLine().charAt(0);
				// Boucle de recherche dans le tableau
				while(i < tabCaracteres.length && carac != tabCaracteres[i])// !!!!!!!!
					i++;
					
				if(i < tabCaracteres.length)// !!!!!!!
					System.out.println("La lettre \"" + carac + "\" se trouve bien dans le tableau");
				else
					System.out.println("La lettre \"" + carac + "\" ne se trouve pas dans le tableau");
			}while(i>=tabCaracteres.length);
			do{
				System.out.println("Voulez-vous réessayer? (O/N)");
				continuer = sc.nextLine().charAt(0);
			}while(continuer != 'O' && continuer != 'N');	
			
		}while(continuer == 'O');
		
		System.out.println("Finito");	
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// AFFICHAGE D'UN TABLEAU BIDIMENSIONNEL
		int tabBiDim[][] = {{0, 2, 4, 6, 8}, {1, 3, 5, 7, 9}};
		i = 0;
		int j = 0;
		while(i < 2)
		{
			j = 0;
			while(j < 5)
			{
				System.out.print(tabBiDim[i][j]);
				j++;
			}
			System.out.println(""); // UN SAUT DE LIGNE
			i++;
		}
		System.out.println("");
		for(i = 0; i < 2; i++)
		{
			for(j = 0; j < 5; j++)
				System.out.print(tabBiDim[i][j]);
			System.out.println("");
		}
		
		// AFFICHAGE D'UN TABLEAU AVEC LA NOUVELLE SYNTAXE
		String tab[] = {"toto", "titi", "tutu", "tete", "tata"};
		for(String chaine : tab)
			System.out.println(chaine);
		//EXPLICATION: à chaque tour de boucle, la valeur courant du tableau est mise dans la variable "chaine"
		// on peut le faire avec d'autres types. FAUTDRA VEILLER A CE QUE LES TYPES CORRESPONDENT
		
		// AFFICHAGE D'UN TABLEAU BIDIMENSIONNEL AVEC LA NOUVELLE SYNTAXE
		// Cette syntaxe ne fonctionnera pas sur les versions antérieures à JDK 1.5
		String tableau[][]={{"toto", "titi", "tutu", "tete", "tata"}, {"1", "2", "3", "4"}};
		i = 0;
		j = 0;
		for(String sousTab[] : tableau) // 1.) je récupère le tableau
		{
			j = 0;
			for(String chaine : sousTab) // 2.) je récupère les valeurs du tableau
			{
				System.out.println("La valeur de la case correspondante est " + chaine);
				System.out.println("tableau["+ i +"]["+ j +"] = " + tableau[i][j]);
				j++;
			}
			i++;
		}
	}

}
