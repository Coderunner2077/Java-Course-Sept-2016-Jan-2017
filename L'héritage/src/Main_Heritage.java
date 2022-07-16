/*
 * 												L'HERITAGE
 * Intro:
 * Imaginons que dans le prog réalisé précédemment, on voulait créer un autre type d'objet : des objets Capitale.
 * Ceux-ci ne seront rien d'autre ques des objets Ville avec un paramètre en plus... disons un monument.
 * On ne va tout de même pas recoder tout le contenu de la classe Ville dans la nouvelle classe ! 
 * Heureusement, l'héritage permet à des objets de fonctionner de la même façon que d'autres.
 * 
 * I./ Le principe de l'héritage
 * 1.) La notion
 * La notion de l'héritage est l'un des fondements de la programmation orientée objet. Grâce à elle, on peut créer
 * des classes héritées (aussi appelées "classes dérivées") de leurs classes mères (aussi appelées "classes de 
 * base"). On peut créer autant de classes dérivées, par rapport à la classe de base, qu'on le souhaite. De plus,
 * on peut se servir d'une classe dérivée comme d'une classe de base pour élaborer encore une classe dérivée.
 * En reprenant l'exemple précédent, on va créer une nouvelle classe, nommée Capitale, héritée de Ville. Eh bien, 
 * les objets Capitale auront tous les attributs et toutes les méthodes associés aux objets Ville.
 * 
 * 2.) La création de la classe héritée
 * Voici la notation pour créer la classe Capitale:
 * class Capitale extends Ville {
 * }
 * C'est le mot clé extends qui informe Java que la classe Capitale est héritée de Ville.
 * Désormais, la classe Capitale possède les propriétés de la classe Ville. Les objets héritées peuvent accéder à
 * toutes les méthodes public (ce n'est pas tout à fait vrai...) de leur classe mère.
 * En fait, lorsqu'on déclare une classe, si on ne spécifie pas de constructeur, le compilateur créera, au moment
 * de l'interprétation, le constructeur par défaut. En revenache, lorsque l'on crée un constructeur, n'importe
 * lequel, la JVM ne crée plus le constructeur par défaut.
 * La classe Capitale hérite de la classe Ville, par conséquent, le constructeur de l'objet Capitale appelle,
 * de façon tacite, le constructeur de la classe mère. C'est pour cela que les variables d'instance ont pu être 
 * initialisées.
 * 
 * 3.) La portée protected
 * Les attributs de la classe mère déclarés private ne peuvent pas être directement utilisées par la classe dérivée.
 * C'est ici que le nouveau mot clé protected fait son entrée. En fait, seules les méthodes et variables déclarées 
 * public ou protected peuvent être utilisée dans une classe héritée; le compilateur rejette en bloc toute
 * demande lorsque l'on tente d'accéder à des ressources privées d'une classe mère.
 * Remplacer private par protected dans la déclaration de variables ou de méthodes de la clase Ville aura pour 
 * effet de les protéger de l'utilisteur de la classe tout en les rendant accessibles aux objets enfants.
 * Note : En Java, contrairement au C++, un objet ne peut hériter que d'une seule classe mère
 * 
 * Remarque : apparemment, en Java, l'utilisateur peut accéder et même modifier les attributs protected
 * 
 *  4.) Le mot clé super
 *  
 *  Ce qui différenciera Capitale de Ville est la présence d'un nouveau champ : le nom d'un monument. Cela implique
 *  que l'on doit créer un constructeur par défaut et un constructeur d'initialisation dans l'objet Capitale.
 *  Heureusement, on peut faire appel aux variables de ma classe mère dans nos constructeurs grâce au mot clé 
 *  super. Cela aura pour effet de récupérer les éléments de l'objet de base, et de les envoyer à notre objet 
 *  hérité. Il suffira d'écrire "super();" dans le constructeur, suivi des nouveaux attributs complémentaires.
 *  La méthode decrisToi() ne prend pas en compte le nom d'un monument. Eh bien le mot clé super() fonctionne
 *  aussi bien pour les méthodes d'instance (ce qui nous donne une méthode decrisToi() un peu différente, avec
 *  l'ajout du nouveau champ monument.
 *  
 *  Note : les commentaires bizarres sont des commentaires JavaDoc qui permettent de créer une documentation pour
 *  mon code.
 *  
 *  Ainsi, j'ai fait de la méthode decrisToi() une méthode polymorphe.
 *  
 *  II./ LE POLYMORPHISME
 *  
 *  1.) Le concept
 *  Le polymorphisme est encore un des concepts fondamentaux de la programmation orietée objet. Il complète 
 *  parfaitement le concept de l'héritage. Pour faire court, on peut le définir en disant qu'il permet de 
 *  manipuler des objets sans vraiment connaître leur type.
 *  On peut (et c'est ce qu'on va faire) construire un tableau d'objet et appeler decrisToi() sans se soucier de 
 *  son contenu : villes, capitales, ou les deux. Réf main
 *  Définition: Une méthode polymorphe a un squelette identique à la méthode de base, mais traite les choses 
 *  différemment. Cette méthode se trouve dans une autre classe et donc, par extension, dans une autre instance 
 *  de cette autre classe.
 *  Note : attention NE PAS CONFONDRE avec la surcharge de méthode (qui diffère de la méthode originale par le
 *  nombre et/ou le type des paramètres qu'elle prend en entrée).
 *  
 *  2.) La covariance des variables et superclasse
 *  Dans ma boucle où j'affiche successivement les descriptions des villes et capitales du tableau, je n'utilise 
 *  que des objets Ville, on appelle ceci, la covariance des variables.
 *  Cela signifie qu'une variable objet peut contenir un objet qui hérite du type de cette variable. Dans mon cas,
 *  un objet de type Ville peut contenir un objet de type Capitale. Dans ce cas, on dit que Ville est la 
 *  superclasse de Capitale. La covariance est efficace dans le cas où la classe héritant redéfinit certaines
 *  méthodes de sa superclasse.
 *  Il y a autre chose à savoir sur l'héritage. Lorsque je crée une classe (Ville par exemple), celle-ci hérite, 
 *  de façon tacite, de la classe Object présente dans Java.
 *  Toutes les classes héritent donc des méthodes de la classe Object, comme equals() qui prend un objet en
 *  paramètre et permet de tester l'égalité des objets.
 *  
 *  3.) Polymorpher toString()
 *  Donc, en redéfinissant une méthode de la classe Object dans la classe Ville, on peut utiliser la covariance.
 *  La méthode de la classe Object la plus souvent redéfinie est toString() : elle retourne un String décrivant
 *  l'objet en question (comme ma méthode decrisToi()). Je vais donc copier la procédure decrisToi() dans une
 *  nouvelle méthode de la classe Ville : toString(). Réf Ville 127; j'en fais de même dans Capitale.
 *  Ensuite, je teste l'affichage de mon tableau de villes et capitales en remplaçant Ville par Object. 
 *  Réf 
 *  Et on constate que le résultat est exactement le même que pour le code avec decrisToi(). Donc, on n'a pas à 
 *  se soucier du type d'objet pour afficher sa desciption.
 *  Attention : si je ne rédéfinie pas ou ne "polymorphe" pas la méthode d'une classe mère dans une classe fille,
 *  à l'appel de celle-ci avec un objet fille, c'est la méthode de la classe mère qui sera invoquée.
 *  Une précision s'impose : si j'ai un objet v de type Ville par exemple, que je n'ai pas redéfini la méthode
 *  toString(), et que je teste ce code :
 *  System.out.println(v);
 *  ==>j'appelerai automatiquement la méthode toString() de la classe Object. 
 *  Mais ici, comme j'ai redéfini la méthode toString() dans ma classe Ville, ces deux instructions sont 
 *  équivalentes :
 *  System.out.println(v.toString());
 *  System.out.println(v);
 *  
 *  4.) Transtyper une référence
 *  Attention : il faut savoir qu'une méthode n'est invocable par un objet que si celui-ci définit ladite 
 *  méthode.
 *  Ainsi, ce code ne fonctionnera pas :
 *  for(Object v : tableau){
      System.out.println(v.decrisToi()+"\n");
    }
    Afin qu'il fonctionne, je dois dire à la JVM que la référence de type Object est en fait une référence de 
    type Ville, comme ceci :
    ((Ville)v).decrisToi();
    Je transtype la référence v en Ville par cette syntaxe. Ici, l'ordre des opérations s'effectue comme ceci:
     -	je transtype la référence v en Ville
     -	j'applique la méthode decrisToi() à la référence appelante, i.e., ici, une référence Object changée en 
     	Ville.
     	
     Là, l'intérêt des méthodes polymorphes est totalement évident : grâce à elles, on n'a plus à se soucier 
     du type de la variable appelante.
    5.)hashCode() et equals()
 	Il y a deux autres méthodes qui sont souvnet redéfinies :
 	-	public boolean equals(Object o) : qui permet de vérifier si un objet est égal à un autre
 	-	public int hashCode() : qui attribue un code de hashage à un objet.
 	La bonnet nouvelle, c'est que Eclipse permet de générer automatiquement ces deux méthodes, via le menu :
	Source/Generate hashcode and equals.
	
	PETIR BEMOL:
	Il existe encore un type de méthode : le type final. Un méthode signalée final est figée, on ne pourra
	jamais la redéfinir (la méthode getClass() de la classe Object en est un exemple).
	public final int maMethode(){
	//Méthode ne pouvant pas être surchargée
	}
	Et il existe aussi des classes déclarées final. Il va de soi que ces classes sont immuables. On ne peut pas
	faire hériter un objet d'une classe déclarée final. Il en va de même pour les variables déclarées de la sorte.(?!)
	
	Remarque : en ce qui concerne les attributs déclarée en tant que final, il est impossible de réassigner leur 
	valeur, tout comme pour les constantes. Mais les classes filles héritent bien de ces attributs.
	
	III./ DEPUIS JAVA 7 : LA CLASSE Objects
	La redéfinition (si faite manuellement) des méthodes comme equals() ou hashcode() n'est pas des plus simples.
	Avec Java 7, il existe une classe qui permet de mieux gérer la redéfinition de ces méthodes :
	java.util.Objects. Attention, il ne s'agit pas de la classe java.lang.Object dont tous les objets héritent.
	Ici, il s'agit d'Objects avec un "s" à la fin. Ce nouvel objet ajoute deux fonctionnalités qui permettent 
	de simplifier la redéfinition des méthodes vues précédemment.
	La classe Objects propose une méthode hash(Objects...values). Cette méthode s'occupe de faire tout le
	nécessaire au calcul d'un code de hashage en vérifiant si les attributs sont nulls ou non et tutti quanti.
	Voici à quoi ressemblerait mon code hashCode() avec cette nouveauté :
	
	public int hashCode(){
	return Objects.hash(categorie, nbHabitants, nomPays, nomVille);
	}
	
	Il faudra bien sûr, penser à importer la classe pour pouvoir l'utiliser.
	Ce nouvel objet intègre aussi une méthode equals() qui se charge de vérifier si les valeurs passées en
	paramètre sont null ou non. Du coup, on aura un code bcp plus clair et lisible. Voici à quoi ressemblerait
	equals() :
	public bolean equals(Object obj){
		//On vérifie si les références d'objet sont identiques
		 if(this == obj)
		 	return true;
		 //On s'assure que les objets sont de même type, ici de type Ville
		 if(getClass() != obj.getClass())
		 	return false;
		 //Maintenant, on compare les attributs des deux objets
		 Ville other = (Ville)obj;
		 return Objects.equals(other.getCategorie(), this.getCategorie()) &&
	 Objects.equals(other.getNom(), this.getNom()) &&
	 Objects.equals(other.getNombreHabitants(), this.getNombreHabitants()) &&
	 Objects.equals(other.getNomPays(), this.getNomPays())
	}
	
	
 */

import java.util.Objects;

public class Main_Heritage {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Ville v1 = new Ville("Lyon", 1000000, "France");
		System.out.println(v1.decrisToi());
		Ville v2 = new Ville();
		v2.nbInstances = 3;
		System.out.println(Ville.nbInstances);
		System.out.println(Ville.getNbreInstancesBis());
		Capitale cap = new Capitale();
		Capitale cap1 = new Capitale("Paris", 1500000, "France", "la tour Eiffel");
		System.out.println(cap1.getMonument());
		System.out.println(cap1.decrisToi());
		//Définition d'un tableau de villes null
		Ville[] tableau = new Ville[6];
		//Définition d'un tableau de noms de villes et un autre de nombre d'habitants
		String[] tab = {"Marseille", "Lille", "Orléan", "Lyon", "Parij", "Nice"};
		int[] tab2 = {123456, 78456, 65487, 758325, 1594000, 213000};
		//les 3 premiers éléments du tableau seront des villes, les autres des capitales
		for(int i = 0; i < tab.length; i++)
		{
			if(i<3)
			{
				Ville V = new Ville(tab[i], tab2[i], "France");
				tableau[i] = V;
			}
			else
			{
				Capitale C = new Capitale(tab[i], tab2[i], "France", "la tour Eiffel");
				tableau[i] = C;
			}		
		}
		//Il ne reste plus qu'à les décrire
		for(Ville V : tableau)
			System.out.println(V.decrisToi()+"\n");
		
		System.out.println("Je teste l'affichage de mon tableau de villes et capitales en remplaçant Ville par"
				+ " Object\n");
		for(Object obj : tableau)
			System.out.println(obj.toString()+"\n");
		
		//testons la covariance des variables :
		Ville vil = new Ville();
		Capitale capi = new Capitale("Capi x", 777, "Pays x", "Monument x");
		vil = capi; // la covariance marche !
		//capi = vil; marche pas dans ce sens-là
		System.out.println(vil.decrisToi()); // même le monument s'affiche
		MaClasse clas = new MaClasse();
		clas.monString = "En Java, on peut donc accéder aux attributs protected et même les modifier !!";
		System.out.println(clas.monString);
	}

}
