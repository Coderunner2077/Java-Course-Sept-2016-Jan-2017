/*
 * 												L'HERITAGE
 * Intro:
 * Imaginons que dans le prog r�alis� pr�c�demment, on voulait cr�er un autre type d'objet : des objets Capitale.
 * Ceux-ci ne seront rien d'autre ques des objets Ville avec un param�tre en plus... disons un monument.
 * On ne va tout de m�me pas recoder tout le contenu de la classe Ville dans la nouvelle classe ! 
 * Heureusement, l'h�ritage permet � des objets de fonctionner de la m�me fa�on que d'autres.
 * 
 * I./ Le principe de l'h�ritage
 * 1.) La notion
 * La notion de l'h�ritage est l'un des fondements de la programmation orient�e objet. Gr�ce � elle, on peut cr�er
 * des classes h�rit�es (aussi appel�es "classes d�riv�es") de leurs classes m�res (aussi appel�es "classes de 
 * base"). On peut cr�er autant de classes d�riv�es, par rapport � la classe de base, qu'on le souhaite. De plus,
 * on peut se servir d'une classe d�riv�e comme d'une classe de base pour �laborer encore une classe d�riv�e.
 * En reprenant l'exemple pr�c�dent, on va cr�er une nouvelle classe, nomm�e Capitale, h�rit�e de Ville. Eh bien, 
 * les objets Capitale auront tous les attributs et toutes les m�thodes associ�s aux objets Ville.
 * 
 * 2.) La cr�ation de la classe h�rit�e
 * Voici la notation pour cr�er la classe Capitale:
 * class Capitale extends Ville {
 * }
 * C'est le mot cl� extends qui informe Java que la classe Capitale est h�rit�e de Ville.
 * D�sormais, la classe Capitale poss�de les propri�t�s de la classe Ville. Les objets h�rit�es peuvent acc�der �
 * toutes les m�thodes public (ce n'est pas tout � fait vrai...) de leur classe m�re.
 * En fait, lorsqu'on d�clare une classe, si on ne sp�cifie pas de constructeur, le compilateur cr�era, au moment
 * de l'interpr�tation, le constructeur par d�faut. En revenache, lorsque l'on cr�e un constructeur, n'importe
 * lequel, la JVM ne cr�e plus le constructeur par d�faut.
 * La classe Capitale h�rite de la classe Ville, par cons�quent, le constructeur de l'objet Capitale appelle,
 * de fa�on tacite, le constructeur de la classe m�re. C'est pour cela que les variables d'instance ont pu �tre 
 * initialis�es.
 * 
 * 3.) La port�e protected
 * Les attributs de la classe m�re d�clar�s private ne peuvent pas �tre directement utilis�es par la classe d�riv�e.
 * C'est ici que le nouveau mot cl� protected fait son entr�e. En fait, seules les m�thodes et variables d�clar�es 
 * public ou protected peuvent �tre utilis�e dans une classe h�rit�e; le compilateur rejette en bloc toute
 * demande lorsque l'on tente d'acc�der � des ressources priv�es d'une classe m�re.
 * Remplacer private par protected dans la d�claration de variables ou de m�thodes de la clase Ville aura pour 
 * effet de les prot�ger de l'utilisteur de la classe tout en les rendant accessibles aux objets enfants.
 * Note : En Java, contrairement au C++, un objet ne peut h�riter que d'une seule classe m�re
 * 
 * Remarque : apparemment, en Java, l'utilisateur peut acc�der et m�me modifier les attributs protected
 * 
 *  4.) Le mot cl� super
 *  
 *  Ce qui diff�renciera Capitale de Ville est la pr�sence d'un nouveau champ : le nom d'un monument. Cela implique
 *  que l'on doit cr�er un constructeur par d�faut et un constructeur d'initialisation dans l'objet Capitale.
 *  Heureusement, on peut faire appel aux variables de ma classe m�re dans nos constructeurs gr�ce au mot cl� 
 *  super. Cela aura pour effet de r�cup�rer les �l�ments de l'objet de base, et de les envoyer � notre objet 
 *  h�rit�. Il suffira d'�crire "super();" dans le constructeur, suivi des nouveaux attributs compl�mentaires.
 *  La m�thode decrisToi() ne prend pas en compte le nom d'un monument. Eh bien le mot cl� super() fonctionne
 *  aussi bien pour les m�thodes d'instance (ce qui nous donne une m�thode decrisToi() un peu diff�rente, avec
 *  l'ajout du nouveau champ monument.
 *  
 *  Note : les commentaires bizarres sont des commentaires JavaDoc qui permettent de cr�er une documentation pour
 *  mon code.
 *  
 *  Ainsi, j'ai fait de la m�thode decrisToi() une m�thode polymorphe.
 *  
 *  II./ LE POLYMORPHISME
 *  
 *  1.) Le concept
 *  Le polymorphisme est encore un des concepts fondamentaux de la programmation oriet�e objet. Il compl�te 
 *  parfaitement le concept de l'h�ritage. Pour faire court, on peut le d�finir en disant qu'il permet de 
 *  manipuler des objets sans vraiment conna�tre leur type.
 *  On peut (et c'est ce qu'on va faire) construire un tableau d'objet et appeler decrisToi() sans se soucier de 
 *  son contenu : villes, capitales, ou les deux. R�f main
 *  D�finition: Une m�thode polymorphe a un squelette identique � la m�thode de base, mais traite les choses 
 *  diff�remment. Cette m�thode se trouve dans une autre classe et donc, par extension, dans une autre instance 
 *  de cette autre classe.
 *  Note : attention NE PAS CONFONDRE avec la surcharge de m�thode (qui diff�re de la m�thode originale par le
 *  nombre et/ou le type des param�tres qu'elle prend en entr�e).
 *  
 *  2.) La covariance des variables et superclasse
 *  Dans ma boucle o� j'affiche successivement les descriptions des villes et capitales du tableau, je n'utilise 
 *  que des objets Ville, on appelle ceci, la covariance des variables.
 *  Cela signifie qu'une variable objet peut contenir un objet qui h�rite du type de cette variable. Dans mon cas,
 *  un objet de type Ville peut contenir un objet de type Capitale. Dans ce cas, on dit que Ville est la 
 *  superclasse de Capitale. La covariance est efficace dans le cas o� la classe h�ritant red�finit certaines
 *  m�thodes de sa superclasse.
 *  Il y a autre chose � savoir sur l'h�ritage. Lorsque je cr�e une classe (Ville par exemple), celle-ci h�rite, 
 *  de fa�on tacite, de la classe Object pr�sente dans Java.
 *  Toutes les classes h�ritent donc des m�thodes de la classe Object, comme equals() qui prend un objet en
 *  param�tre et permet de tester l'�galit� des objets.
 *  
 *  3.) Polymorpher toString()
 *  Donc, en red�finissant une m�thode de la classe Object dans la classe Ville, on peut utiliser la covariance.
 *  La m�thode de la classe Object la plus souvent red�finie est toString() : elle retourne un String d�crivant
 *  l'objet en question (comme ma m�thode decrisToi()). Je vais donc copier la proc�dure decrisToi() dans une
 *  nouvelle m�thode de la classe Ville : toString(). R�f Ville 127; j'en fais de m�me dans Capitale.
 *  Ensuite, je teste l'affichage de mon tableau de villes et capitales en rempla�ant Ville par Object. 
 *  R�f 
 *  Et on constate que le r�sultat est exactement le m�me que pour le code avec decrisToi(). Donc, on n'a pas � 
 *  se soucier du type d'objet pour afficher sa desciption.
 *  Attention : si je ne r�d�finie pas ou ne "polymorphe" pas la m�thode d'une classe m�re dans une classe fille,
 *  � l'appel de celle-ci avec un objet fille, c'est la m�thode de la classe m�re qui sera invoqu�e.
 *  Une pr�cision s'impose : si j'ai un objet v de type Ville par exemple, que je n'ai pas red�fini la m�thode
 *  toString(), et que je teste ce code :
 *  System.out.println(v);
 *  ==>j'appelerai automatiquement la m�thode toString() de la classe Object. 
 *  Mais ici, comme j'ai red�fini la m�thode toString() dans ma classe Ville, ces deux instructions sont 
 *  �quivalentes :
 *  System.out.println(v.toString());
 *  System.out.println(v);
 *  
 *  4.) Transtyper une r�f�rence
 *  Attention : il faut savoir qu'une m�thode n'est invocable par un objet que si celui-ci d�finit ladite 
 *  m�thode.
 *  Ainsi, ce code ne fonctionnera pas :
 *  for(Object v : tableau){
      System.out.println(v.decrisToi()+"\n");
    }
    Afin qu'il fonctionne, je dois dire � la JVM que la r�f�rence de type Object est en fait une r�f�rence de 
    type Ville, comme ceci :
    ((Ville)v).decrisToi();
    Je transtype la r�f�rence v en Ville par cette syntaxe. Ici, l'ordre des op�rations s'effectue comme ceci:
     -	je transtype la r�f�rence v en Ville
     -	j'applique la m�thode decrisToi() � la r�f�rence appelante, i.e., ici, une r�f�rence Object chang�e en 
     	Ville.
     	
     L�, l'int�r�t des m�thodes polymorphes est totalement �vident : gr�ce � elles, on n'a plus � se soucier 
     du type de la variable appelante.
    5.)hashCode() et equals()
 	Il y a deux autres m�thodes qui sont souvnet red�finies :
 	-	public boolean equals(Object o) : qui permet de v�rifier si un objet est �gal � un autre
 	-	public int hashCode() : qui attribue un code de hashage � un objet.
 	La bonnet nouvelle, c'est que Eclipse permet de g�n�rer automatiquement ces deux m�thodes, via le menu :
	Source/Generate hashcode and equals.
	
	PETIR BEMOL:
	Il existe encore un type de m�thode : le type final. Un m�thode signal�e final est fig�e, on ne pourra
	jamais la red�finir (la m�thode getClass() de la classe Object en est un exemple).
	public final int maMethode(){
	//M�thode ne pouvant pas �tre surcharg�e
	}
	Et il existe aussi des classes d�clar�es final. Il va de soi que ces classes sont immuables. On ne peut pas
	faire h�riter un objet d'une classe d�clar�e final. Il en va de m�me pour les variables d�clar�es de la sorte.(?!)
	
	Remarque : en ce qui concerne les attributs d�clar�e en tant que final, il est impossible de r�assigner leur 
	valeur, tout comme pour les constantes. Mais les classes filles h�ritent bien de ces attributs.
	
	III./ DEPUIS JAVA 7 : LA CLASSE Objects
	La red�finition (si faite manuellement) des m�thodes comme equals() ou hashcode() n'est pas des plus simples.
	Avec Java 7, il existe une classe qui permet de mieux g�rer la red�finition de ces m�thodes :
	java.util.Objects. Attention, il ne s'agit pas de la classe java.lang.Object dont tous les objets h�ritent.
	Ici, il s'agit d'Objects avec un "s" � la fin. Ce nouvel objet ajoute deux fonctionnalit�s qui permettent 
	de simplifier la red�finition des m�thodes vues pr�c�demment.
	La classe Objects propose une m�thode hash(Objects...values). Cette m�thode s'occupe de faire tout le
	n�cessaire au calcul d'un code de hashage en v�rifiant si les attributs sont nulls ou non et tutti quanti.
	Voici � quoi ressemblerait mon code hashCode() avec cette nouveaut� :
	
	public int hashCode(){
	return Objects.hash(categorie, nbHabitants, nomPays, nomVille);
	}
	
	Il faudra bien s�r, penser � importer la classe pour pouvoir l'utiliser.
	Ce nouvel objet int�gre aussi une m�thode equals() qui se charge de v�rifier si les valeurs pass�es en
	param�tre sont null ou non. Du coup, on aura un code bcp plus clair et lisible. Voici � quoi ressemblerait
	equals() :
	public bolean equals(Object obj){
		//On v�rifie si les r�f�rences d'objet sont identiques
		 if(this == obj)
		 	return true;
		 //On s'assure que les objets sont de m�me type, ici de type Ville
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
		//D�finition d'un tableau de villes null
		Ville[] tableau = new Ville[6];
		//D�finition d'un tableau de noms de villes et un autre de nombre d'habitants
		String[] tab = {"Marseille", "Lille", "Orl�an", "Lyon", "Parij", "Nice"};
		int[] tab2 = {123456, 78456, 65487, 758325, 1594000, 213000};
		//les 3 premiers �l�ments du tableau seront des villes, les autres des capitales
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
		//Il ne reste plus qu'� les d�crire
		for(Ville V : tableau)
			System.out.println(V.decrisToi()+"\n");
		
		System.out.println("Je teste l'affichage de mon tableau de villes et capitales en rempla�ant Ville par"
				+ " Object\n");
		for(Object obj : tableau)
			System.out.println(obj.toString()+"\n");
		
		//testons la covariance des variables :
		Ville vil = new Ville();
		Capitale capi = new Capitale("Capi x", 777, "Pays x", "Monument x");
		vil = capi; // la covariance marche !
		//capi = vil; marche pas dans ce sens-l�
		System.out.println(vil.decrisToi()); // m�me le monument s'affiche
		MaClasse clas = new MaClasse();
		clas.monString = "En Java, on peut donc acc�der aux attributs protected et m�me les modifier !!";
		System.out.println(clas.monString);
	}

}
