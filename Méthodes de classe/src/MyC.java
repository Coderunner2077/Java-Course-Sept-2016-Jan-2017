
public class MyC {

	public static void main(String[] args) {
		// QUELQUES METHODES UTILES
		// Méthodes sur les chaînes de caractères
		
		//Méthode toLowerCase(void) permet de transformer tout caractère alphabétique en son équivalent minuscule. N'a
		//aucun effet sur les chiffres
		String chaine = new String("HELLO THE WORLD"), chaine2 = new String(), chaine3 = new String();
		chaine2 = chaine.toLowerCase();
		
		//Méthode toUpperCase(void) fait l'inverse
		chaine3 = chaine2.toUpperCase();
		System.out.println(chaine3);
		
		//length(void) renvoie la longueur d'une chaine de caractères en comptant les espaces
		int nbCarac = 0;
		nbCarac = chaine.length();
		System.out.println(nbCarac);
		
		//equals() permet de vérifier si deux chaines sont identiques
		String str1 = new String("Finito"), str2 = new String("Pinito");
		if(str1.equals(str2)) // j'aurais pu vérifier la négation, ainsi : if(!str1.equals(str2))
			System.out.println("Les deux chaines sont identiques");
		else
			System.out.println("Les deux chaines sont différentes");
		
		// charAt() extrait un caractère d'une chaîne de caractères. Pour l'index, le 1er caractère est le n°0
		char lettre = chaine2.charAt(3);
		
		//substring()
		/*la méthode substring() extrait une partie d'une chaîne de caractères. Elle prend deux entiers en argument.
		 * Le 1er définit le 1er caractère (inclus) de la sous-chaîne à extraire, le 2e correspond au dernier caractère
		 * (exclu) à extraire.
		 */
		String sousChaine = new String();
		sousChaine = chaine.substring(6, 15);
		System.out.println(sousChaine);
		
		//indexOf() et lastIndexOf()
		/*indexOf() explore une chaine de caractères à la recherche d'une suite donnée de caractères, et renvoie la 
		 position (un int) de la première occurence de la sous-chaîne passée en argument.
		lastIndexOf() fait la même chose, sauf qu'elle renvoie la dernière occurence et non la première
		*/
		
		String mot = new String("anticonstitutionnellement");
		int n = 0;
		n = mot.indexOf('t'); 		// n vaut 2
		n = mot.lastIndexOf('t'); 	// n vaut 24
		n = mot.indexOf("ti"); 		// n vaut 2
		n = mot.lastIndexOf("ti");	// n vaut 12
		n = mot.indexOf('z');		// n vaut -1   --> !!!!!!!!
		//NOTE : je mets des apostrophes si je ne recherche qu'un caractère, et des guillemets si je recherche une chaine
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Les méthodes suivantes nécessitent la classe Math, présente dans java.lang. Elle fait donc partie des
		// fondements de Java. Par conséquent, aucun import nécessaire pour utiliser la classe Math
		// POUR INFO : TOUTES CES METHODES RETOURNENT UN NOMBRE DE TYPE double :
		double X = 0.0;
		X = Math.random();
		// retourne un nombre aléatoire compris entre 0 et 1
		System.out.println("X ="+X);
		double sinus = Math.sin(120);
		double cosinus = Math.cos(120);
		double tangente = Math.tan(120);
		double absolu = Math.abs(-120.43);
		double d = 2;
		double exp = Math.pow(d, 3); // la fonction exposant
		//ici, on initialise la variable exp avec la valeur de d élevé au cube.
		//la méthode pow() prend donc une valeur en premier parametre, et un exposant en second
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// CREER SA PROPRE METHODE
		/*
		 * Les différences par rapport au le langage C :
		 * 	-	la fonction est à placer juste après la méthode main (à l'extérieur de celle-ci) et à l'intérieur de la
		 * 		classe. Sinon, le prog ne compilera pas
		 * 	-	deux mots sont éventuellement placés devant la fonction lors de sa déclaration : "public" (qui
		 * 		 détermine la portée de la méthode) et  "static" 
		 * 
		 * VOIR FCTS EN BAS
		 */
		String[] tableau = {"toto", "titi", "tata", "tete"};
		parcourirTableau(tableau);
		System.out.println(toString(tableau));
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//LA SURCHAGE DE METHODE
		/*
		 * La surcharge de méthode consiste à garder le nom d'une méthode (donc un type de traitement à faire:
		 * dans l'example précèdent --> liste un tableau) et à changer la liste ou le type de ses paramètres, voire
		 * même ajouter d'autres paramètres
		 */
		int[] tabNb = {1, 2, 3, 4, 5};
		String[][] tab = {{"toto", "titi", "tata", "tete"}, {"coco", "cici", "caca", "cece"}};
		parcourirTableau(tabNb);
		System.out.println("");
		parcourirTableau(tab);
	
		
	}
	
	static void parcourirTableau(String[] tab)
	{
		for(String str : tab)
			System.out.println(str);
	}
	
	static String toString(String[] tab)
	{
		System.out.println("Méthode toString()!\n------------ ");
		String retour = "";
		
		for(String str : tab)
			retour += str + "\n";
		
		return retour;
	}
	
	static void parcourirTableau(int[] tab)
	{
		for(int nb : tab)
			System.out.println(nb);
	}
	
	static void parcourirTableau(String[][] tab)
	{
		for(String sousTab[] : tab)
		{
			for(String str : sousTab)
			{
				System.out.print(str + "\t");
			}
			System.out.println("");
			
		}
	}
	
}
