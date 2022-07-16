import java.util.Scanner;//importe la classe Scanner du package java.util
import java.util.*;// importe toutes les classes du package java.util

public class MaClasse {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in); // je crée une nouvelle classe Scanner... mais il fo préalablement l'importer 
		//  J'ai initialisé l'objet Scanner avec l'entrée standard
		System.out.println("Tapez du texte : ");
		String str = sc.nextLine(); 
		System.out.println("Vous avez tape : " + str);
		System.out.print("Voila");
		System.out.println(",saisissez un nombre : ");
		int nb = sc.nextInt();
		System.out.println("Votre nombre : " + nb);
		// de façon générale, pour récupérer un type de variable, il suffit d'appeler next<Type de variable> 
		//commençant par une majuscule
		System.out.println("Tapez un double AVEC UNE VIRGULE (AU LIEU DU POINT...)");
		System.out.println("Ensuite, tapez un short, un long et enfin un byte");
		double deci = sc.nextDouble();
		short s = sc.nextShort();
		long l = sc.nextLong();
		byte b = sc.nextByte();
		System.out.println("Double : " + deci + ", short : " + s + ", long : " + l + ", byte : " + b);
		
		// ATTENTION, char n'est pas pris en compte pas Scanner. Voici comment il fo procéder:
		
		System.out.println("Saisissez une lettre (ou un mot, mais seulement le premier caractere sera pris en"
				+ " compte\n");
		sc.nextLine(); // ON VIDE LA LIGNE AVANT D'EN LIRE UNE AUTRE
		String chaine = sc.nextLine();
		char carac = chaine.charAt(0);
		System.out.println("Voici la lettre : " + carac);
		// REMARQUE : Je ne peux pas, après avoir invoqué nextInt, nextDouble, etc., directement récupérer une 
		// chaine de caracteres, car la méthode nextLine() videra la ligne commencée par les autres instructions
		// VOICI UNE CONTRACTION DE CE PROCEDE, où je ne vais pas avoir besoin d'une classe String :
		System.out.println("Tapez une autre lettre : ");
		char lettre = sc.nextLine().charAt(0);
		System.out.println("La voici : " + lettre);
		
	} 

}
