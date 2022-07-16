
public class variables_operateurs {

	public static void main(String[] args) {
	/*
	 Le langage binaire est une suite de 0 et de 1, ce qu'on appelle bit.
	 Un bit est la plus petite valeur informatique : soit 0, soit 1.
	 Un octet est un regroupement de 8 bits.
	 Ko = 1024 octets; Mo = 1024 Ko; Go = 1024 Mo; To (Tera octet) = 1024 Go.
	 --> 1024 et pas 1000, parce que 1024 est une puissance de 2 (2^10).
	 
	 En Java, il existe deux types de variables :
	 	-	des variables de type simple ou "primitifs"
	 	-	des variables de type complexe ou des "objets"
	 Les variables de type simple, ce sont des nombres entiers, des nombres réels, des boléens ou encore des 
	 caractères. Et il y a plusieurs façons de déclarer certains de ces types.
	  1./ LES VARIABLES DE TYPE NUMERIQUE
	  Le type byte (1 octet) contient les entiers entre -128 et 127.
	 */
		byte temperature;
		temperature = 64;
	//short (2 octets) contient les entiers entre -32768 et 32767
		short vitesseMax;
		vitesseMax = 32000;
	// int (4 octets) : de -2*109 à 2*109 (9 zéros dérierre 2)
		int temperatureSoleil;
		temperatureSoleil = 15600000;
	// le type long (8 octets) : de -9*10^18 à 9*10^18
		long anneeLumiere;
		anneeLumiere = 9460700000000000L; // ATTENTION, UN "L" A LA FIN
	// le type float (4 octets) est utilisé pour les nombres avec une virgule flottante
		float pi;
		pi = 3.141592653f; // un "f" à la fin donc
		float nombre;
		nombre = 2.0f; // même si le nombre est rond, on met un point et 0 derrière.
	// le type double (8 octets) identique à float, si ce n'est qu'il contient plus de chiffres après la virgule
		double division;
		division = 0.333333333333333333333333333333333333333333334d; // ATTENTION AU "d" OBLIGATOIRE A LA FIN
		
	// 2./ DES VARIABLES STOCKANT UN CARACTERE
	// le type char contient UN caractère stocké entre apostrophes, comme ceci:
		char caractere;
		caractere = 'A';
		
	/* 3./ DES VARIABLES DE TYPE BOLEEN
	 * Le type boléen ne peut contenir que deux valeurs : true ou false, sans guillemets (ces valeurs sont natives
	 * dans le langage, il les comprend directement et sait les interpréter 
	 * */
		boolean question;
		question = true;
		
	/* 4./ LE TYPE String
	 * Il s'agit d'une variable de type complexe que l'on appelle objet. Il existe quatre méthodes de déclaration:
	 */
		String phrase;
		phrase = "Premiere methode";
		
		String deuxieme = new String();
		deuxieme = "Deuxieme methode de declaration";
		
		String chaine = "declaration directe";
		
		String derniere = new String("declaration plus ou moins directe");
		
	/*ATTENTION, String commence par une majuscule.
	 * String n'est pas un type de variable, mais un objet. Notre variable est un objet, on parle aussi d'une 
	 * instance : ici, une instance de la classe String.
	 * Pourquoi String commence par une majuscule? Il s'agit d'une convention de nommage : 
	 * 	-	tous mes noms de classe doivent commencer par une majuscule
	 *  -	tous mes noms de variables doivent commencer par une minuscule
	 *  -	si le nom d'une variable est composé de plusieurs mots, le premier commence par une minuscule, le ou 
	 *  les autres par une majuscule, et ce, sans séparation ;
	 *  -	tout ceci sans accentuation.
	 *  
	 *  Note : les objets sont définis par une ossature (un squelette) qui est en fait une classe. Ici, j'utilise
	 *  un objet String défini par une classe qui s'appelle "String". C'est pourquoi String a une majuscule, et pas
	 *  int, float, etc., qui eux ne sont pas définis par une classe.
	 *  
	 *  Autre chose : il est possible de compacter la phase de déclaration et d'initialisation pour les 
	 *  variables, tout comme en langage C.
	 */
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/* En ce qui concerne les opérateurs mathématiques, voici ce qui diffère du langage C :
	 */
		int nombre1 = 2, nmbre2 = 4;
		nombre1++;
		++nombre1;// fait la même chose que l'opérateur précèdent, mais il y a une subtilité à découvrir plus tard
		nmbre2--;
		--nmbre2;
		
		// SINON, TOUTES LES OPERATIONS BASIQUES AINSI QUE LES RACCOURCIS SE FONT EXACTEMENT COMME EN C
		//BIEN SUR, ON NE PEUT FAIRE DE TRAITEMENT ARITHMETIQUE QUE SUR DES VARIABLES DE MEME TYPE, SOUS PEINE DE 
		//PERDRE DE LA PRECISION LORS DU CALCUL.
		
	//AFFICHER LE CONTENU D'UNE VARIABLE DANS LA CONSOLE:
		System.out.println(nombre1);
		System.out.print(nmbre2);
		double nb1 = 10, nb2 = 3; // ICI PAS DE "d" A LA FIN??
		int resultat = (int)(nb1/nb2); 
		System.out.println("\nLe resultat de l'operation : " + resultat);
		//Ici, l'opérateur "+" sert d'opérateur de concaténation. I.e. il permet de mélanger du texte brut et des variables.
		System.out.println("Resultat = " + nb1/nb2); // SANS CREER DE VARIABLE RESULTAT
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*CONVERSIONS, ou "cast"
	 * Précédemment, j'avais fait une conversion lors du calcul (int)(nb1/nb2). J'ai "casté" le résultat d'une opération mathématique. 
	 * Et je serai souvent amené à le faire. C'est
	 * très simple.
	 */
		//D'un type int en type float
		int i = 123;
		float j = (float)i;
		//De int en double;
		double k = (double)i;
		// Et inversement:
		double m = 1.23;
		double n = 2.9999999;		
		int entier = (int)m;// entier vaut 1
		entier = (int)n; // entier vaut 2;
		// Ce type de conversion s'appelle conversion d'ajustement, ou cast de variable.
		//ATTENTION A CE QUI SUIT:
		int number1 = 3, number2 = 2;
		double result = (double)(number1/number2);
		System.out.println(result); // IL AFFICHE 1.0 ET PAS 1.5
		//LA REPONSE SE TROUVE DANS LES REGLES DE PRIORITE DE TRAITEMENT DES OPERATIONS. EN EFFET, Java fait d'abord le calcul, ensuite
		//le cast du résultat en double, puis il l'affecte dans ma variable result
		//Voici comment il fo faire pour avoir un résultat correct:
		result = (double)(number1)/(double)(number2);
		System.out.println(result);
		
		// CONVERTIR UN NOMBRE EN TEXTE ET INVERSEMENT
		int aze = 12;
		String nombreTexte = new String();
		//nombreTexte = nombreTexte.valueOf(aze); 
		double doubl = 433434.4545d;
		nombreTexte = nombreTexte.valueOf(doubl);
		System.out.println("Ceci est un texte : " + nombreTexte);
		//Voici comment faire marche arrière
		double numbere = Double.valueOf(nombreTexte).doubleValue();// il y a aussi floatValue(), doubleValue(),...
		System.out.println("Ceci est un nombre de type double: " + numbere);
		float fif = Float.valueOf(nombreTexte).floatValue();
		System.out.println("Ceci est un nombre de type float : " + fif);
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/*DEPUIS JAVA 7: LE FORMATAGE DES NOMBRES
	 * Pour faciliter la lecture d'un nombre, je peux formater mes variables de type numériques avec un séparateur, l'underscore("_").
	 * Attention, les underscore doivent être placés entre deux caractères numériques. Ils ne peuvent donc pas être placés au 
	 * début ou à la fin d'un nombre, ni avant ou après un séparateur de décimal.
	 */
		double nblong = 1_000_000_000_000_000d;
		long longnombre = 2_43_44_43_45_43_04_31L;
	// Pour déclarer des expressions numériques en hexadécimal, je dois utiliser le préfixe "0x" :
		int integere = 0xFF; // vaut 255 en base 10
		int autreInt = 0x14; // vaut 20 en base 10
		int encoreInt = 0x13_F8; // vaut 5112
	//DEPUIS Java 7, j'ai aussi la possibilité d'utiliser la notation binaire, en utilisant le préfixe "0b" :
		int binaire = 0b1111_1111; // vaut 255
		int binaireAutre = 0b1000_0000_0000; // vaut 2048
		int biEncore = 0b100000000000; // vaut 2048
	// Cette notation peut s'avérer utile pour certains programme Java qui travaillent directement sur les bits.
	
		
		
	}
}
