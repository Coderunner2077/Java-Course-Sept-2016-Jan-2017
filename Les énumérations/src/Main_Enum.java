/*
 * 												LES ENUMERATIONS
 * Les énumérations constituent une notion nouvelle depuis Java 5. Ce sont des structures qui définissent une liste
 * de valeurs possibles. Cela permet de créer des types de données personnalisées. Je vais par exemple construire le 
 * type Langage qui ne peut prendre qu'un certain nombre de valeurs : JAVA, PHP, C, etc.
 * 
 * I./ Avant énumérations
 * J'aurai sans doute besoin, un jour ou l'autre, de données permettant de savoir ce que je dois faire. Bcp de
 * variables statiques dans Java servent à cela.
 * Comme je vois dans l'illustration (Ref main 49 ==> Avant_Enumeration), rien ne m'empêche de passer un paramètre 
 * innattendu à une méthode : c'est ce qui s'est passé à la dernière ligne de mon test (ae.fait(4)). C'est la
 * principale faiblesse d'une telle méthode (dont le comportement peut être faussé donc).
 * 
 * II./ Une solution : les enum
 * 
 * Une énumération est une classe contenant une liste de sous-objets.
 * Une énumeration se déclare comme une classe, mais en remplaçant le mot clé class par enum. 
 * Autre différence, les enumérations héritent de la classe java.lang.Enum
 * Ref Langage, rien de bien difficile ==> j'obtiens une structure qui encapsule quatre "objets". En fait, c'est
 * comme si j'avais un objet JAVA, un objet C, un objet CPlus et un objet PHP partageant tous les mêmes méthodes
 * issues de la classe java.lang.Object comme n'importe quel autre objet : equals(), toString(), etc.
 * Je constate aussi qu'il n'y a pas de déclaration de portée, ni de type : les énumérations s'utilisent comme
 * des variables statiques déclarées public : on écrira par exemple Langage.JAVA
 * De plus, je peux recourir à la méthode values() retournant la liste des déclarations de l'énumération dont voici 
 * l'exemple :
 * for(Langage.lang : Langage.values()){
 * 		if(Langage.JAVA.equals(lang))
 * 			System.out.println("J'aime le " + lang);
 * 		else
 * 			System.out.println(lang);
 * }
 * 
 * A présent, je vais ajouter un paramètre dans mon énumération, un constructeur et pour finir je vais redéfinir
 * la méthode toString().
 * Remarque : pas de déclaration de portée pour le constructeur, pour une raison simple ==> il est toujours 
 * considéré comme private afin de préserver les valeurs définies dans l'enum.
 * Autre note : les données formant mon énumération sont directement construites dans la classe.
 * Autre inconvénient de la méthode d'Avant_Enumeraton ==> je ne peux passer qu'un certain type d'argument en
 * paramètre. Réf main 57-58.
 * A présent ==> mécanisme protégé et code plus complet.
 * Ainsi, je peux compléter les comportements des objets d'une énumération en ajoutant des méthodes
 */
public class Main_Enum {

	public static void main(String[] args) {
		Avant_Enumeration ae = new Avant_Enumeration();
		ae.fait(Avant_Enumeration.PARAM1);
		ae.fait(Avant_Enumeration.PARAM2);
		ae.fait(4);
		
		for(Langage lang : Langage.values()){
			if(Langage.JAVA.equals(lang))
				System.out.println("J'aime le "+lang);
			else
				System.out.println(lang); // Variante : System.out.println(lang.toString());
		}
		//ae.fait(Langage.JAVA);// une exception
		//ae.fait(Langage.C);// une exception
		ae.fait(4);
		Langage l1 = Langage.JAVA;
		Langage l2 = Langage.C;
		l1.getEditor();
		l2.getEditor();
		
		Langage l3 = Langage.auTre;
		System.out.println(l3 + ", éditeur : ");
		l3.getEditor();
		System.out.println("Et pour finir : " + Langage.biduleConstruitDirectementALinterieurDeLénumération);
	}

	

}
