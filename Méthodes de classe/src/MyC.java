
public class MyC {

	public static void main(String[] args) {
		// QUELQUES METHODES UTILES
		// M�thodes sur les cha�nes de caract�res
		
		//M�thode toLowerCase(void) permet de transformer tout caract�re alphab�tique en son �quivalent minuscule. N'a
		//aucun effet sur les chiffres
		String chaine = new String("HELLO THE WORLD"), chaine2 = new String(), chaine3 = new String();
		chaine2 = chaine.toLowerCase();
		
		//M�thode toUpperCase(void) fait l'inverse
		chaine3 = chaine2.toUpperCase();
		System.out.println(chaine3);
		
		//length(void) renvoie la longueur d'une chaine de caract�res en comptant les espaces
		int nbCarac = 0;
		nbCarac = chaine.length();
		System.out.println(nbCarac);
		
		//equals() permet de v�rifier si deux chaines sont identiques
		String str1 = new String("Finito"), str2 = new String("Pinito");
		if(str1.equals(str2)) // j'aurais pu v�rifier la n�gation, ainsi : if(!str1.equals(str2))
			System.out.println("Les deux chaines sont identiques");
		else
			System.out.println("Les deux chaines sont diff�rentes");
		
		// charAt() extrait un caract�re d'une cha�ne de caract�res. Pour l'index, le 1er caract�re est le n�0
		char lettre = chaine2.charAt(3);
		
		//substring()
		/*la m�thode substring() extrait une partie d'une cha�ne de caract�res. Elle prend deux entiers en argument.
		 * Le 1er d�finit le 1er caract�re (inclus) de la sous-cha�ne � extraire, le 2e correspond au dernier caract�re
		 * (exclu) � extraire.
		 */
		String sousChaine = new String();
		sousChaine = chaine.substring(6, 15);
		System.out.println(sousChaine);
		
		//indexOf() et lastIndexOf()
		/*indexOf() explore une chaine de caract�res � la recherche d'une suite donn�e de caract�res, et renvoie la 
		 position (un int) de la premi�re occurence de la sous-cha�ne pass�e en argument.
		lastIndexOf() fait la m�me chose, sauf qu'elle renvoie la derni�re occurence et non la premi�re
		*/
		
		String mot = new String("anticonstitutionnellement");
		int n = 0;
		n = mot.indexOf('t'); 		// n vaut 2
		n = mot.lastIndexOf('t'); 	// n vaut 24
		n = mot.indexOf("ti"); 		// n vaut 2
		n = mot.lastIndexOf("ti");	// n vaut 12
		n = mot.indexOf('z');		// n vaut -1   --> !!!!!!!!
		//NOTE : je mets des apostrophes si je ne recherche qu'un caract�re, et des guillemets si je recherche une chaine
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Les m�thodes suivantes n�cessitent la classe Math, pr�sente dans java.lang. Elle fait donc partie des
		// fondements de Java. Par cons�quent, aucun import n�cessaire pour utiliser la classe Math
		// POUR INFO : TOUTES CES METHODES RETOURNENT UN NOMBRE DE TYPE double :
		double X = 0.0;
		X = Math.random();
		// retourne un nombre al�atoire compris entre 0 et 1
		System.out.println("X ="+X);
		double sinus = Math.sin(120);
		double cosinus = Math.cos(120);
		double tangente = Math.tan(120);
		double absolu = Math.abs(-120.43);
		double d = 2;
		double exp = Math.pow(d, 3); // la fonction exposant
		//ici, on initialise la variable exp avec la valeur de d �lev� au cube.
		//la m�thode pow() prend donc une valeur en premier parametre, et un exposant en second
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// CREER SA PROPRE METHODE
		/*
		 * Les diff�rences par rapport au le langage C :
		 * 	-	la fonction est � placer juste apr�s la m�thode main (� l'ext�rieur de celle-ci) et � l'int�rieur de la
		 * 		classe. Sinon, le prog ne compilera pas
		 * 	-	deux mots sont �ventuellement plac�s devant la fonction lors de sa d�claration : "public" (qui
		 * 		 d�termine la port�e de la m�thode) et  "static" 
		 * 
		 * VOIR FCTS EN BAS
		 */
		String[] tableau = {"toto", "titi", "tata", "tete"};
		parcourirTableau(tableau);
		System.out.println(toString(tableau));
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//LA SURCHAGE DE METHODE
		/*
		 * La surcharge de m�thode consiste � garder le nom d'une m�thode (donc un type de traitement � faire:
		 * dans l'example pr�c�dent --> liste un tableau) et � changer la liste ou le type de ses param�tres, voire
		 * m�me ajouter d'autres param�tres
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
		System.out.println("M�thode toString()!\n------------ ");
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
