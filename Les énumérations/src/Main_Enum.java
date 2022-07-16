/*
 * 												LES ENUMERATIONS
 * Les �num�rations constituent une notion nouvelle depuis Java 5. Ce sont des structures qui d�finissent une liste
 * de valeurs possibles. Cela permet de cr�er des types de donn�es personnalis�es. Je vais par exemple construire le 
 * type Langage qui ne peut prendre qu'un certain nombre de valeurs : JAVA, PHP, C, etc.
 * 
 * I./ Avant �num�rations
 * J'aurai sans doute besoin, un jour ou l'autre, de donn�es permettant de savoir ce que je dois faire. Bcp de
 * variables statiques dans Java servent � cela.
 * Comme je vois dans l'illustration (Ref main 49 ==> Avant_Enumeration), rien ne m'emp�che de passer un param�tre 
 * innattendu � une m�thode : c'est ce qui s'est pass� � la derni�re ligne de mon test (ae.fait(4)). C'est la
 * principale faiblesse d'une telle m�thode (dont le comportement peut �tre fauss� donc).
 * 
 * II./ Une solution : les enum
 * 
 * Une �num�ration est une classe contenant une liste de sous-objets.
 * Une �numeration se d�clare comme une classe, mais en rempla�ant le mot cl� class par enum. 
 * Autre diff�rence, les enum�rations h�ritent de la classe java.lang.Enum
 * Ref Langage, rien de bien difficile ==> j'obtiens une structure qui encapsule quatre "objets". En fait, c'est
 * comme si j'avais un objet JAVA, un objet C, un objet CPlus et un objet PHP partageant tous les m�mes m�thodes
 * issues de la classe java.lang.Object comme n'importe quel autre objet : equals(), toString(), etc.
 * Je constate aussi qu'il n'y a pas de d�claration de port�e, ni de type : les �num�rations s'utilisent comme
 * des variables statiques d�clar�es public : on �crira par exemple Langage.JAVA
 * De plus, je peux recourir � la m�thode values() retournant la liste des d�clarations de l'�num�ration dont voici 
 * l'exemple :
 * for(Langage.lang : Langage.values()){
 * 		if(Langage.JAVA.equals(lang))
 * 			System.out.println("J'aime le " + lang);
 * 		else
 * 			System.out.println(lang);
 * }
 * 
 * A pr�sent, je vais ajouter un param�tre dans mon �num�ration, un constructeur et pour finir je vais red�finir
 * la m�thode toString().
 * Remarque : pas de d�claration de port�e pour le constructeur, pour une raison simple ==> il est toujours 
 * consid�r� comme private afin de pr�server les valeurs d�finies dans l'enum.
 * Autre note : les donn�es formant mon �num�ration sont directement construites dans la classe.
 * Autre inconv�nient de la m�thode d'Avant_Enumeraton ==> je ne peux passer qu'un certain type d'argument en
 * param�tre. R�f main 57-58.
 * A pr�sent ==> m�canisme prot�g� et code plus complet.
 * Ainsi, je peux compl�ter les comportements des objets d'une �num�ration en ajoutant des m�thodes
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
		System.out.println(l3 + ", �diteur : ");
		l3.getEditor();
		System.out.println("Et pour finir : " + Langage.biduleConstruitDirectementALinterieurDeL�num�ration);
	}

	

}
