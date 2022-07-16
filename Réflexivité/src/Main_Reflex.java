import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * 												Java et la réflexivité
 * La réflexivité, aussi appelée introspection, consiste à découvirir de façon dynamique des informations relatives
 * à une classe ou à un objet. C'est notamment utilisé au niveau de la machine virtuelle Java lors de l'exécution 
 * du programme. En gros, la machine virtuelle stocke les informations relatives à une classe dans un objet.
 * La réflexivité n'est que le moyen de connaître toutes les informations concernant une classe donnée. On peut
 * même créer des instances de classes de façon dynamique grâce à cette notion.
 * 
 * I./ L'objet Class
 * 
 * Je n'examinerai ici que l'essentiel des fonctionnalités de l'objet Class .
 * 
 * 1.) Récupérer un objet Class
 * Au chargement d'une classe Java, la JVM crée automatiquement un objet. Celui-ci récupère toutes les caractèristiques
 * de la classe chargée. Il s'agit d'un objet Class.
 * Exemple : je crée trois nouvelles classes Java. A l'exécution de mon programme, la JVM va créer une classe pour
 * chacune d'elles. Et cet objet possède une multitude de méthodes permettant d'obtenir tous les renseignements
 * possibles et imaginables sur une classe.
 * Visitons sans plus tarder la classe String. 
   Voici les deux façons (équivalentes) de récupérer un objet Class :
   Class c = String.class;
   Class c2 = new String().getClass();
 * 
 * 2.) Connaître la superclasse d'une classe
 * La méthode getSuperclass() renvoie la superclasse d'une classe.
 * Exemple :
 * System.out.println("La superclasse de "+String.class.getName()+" est "+String.class.getSuperclass());
 * Note: la classe Object n'a pas de superclasse, car elle se trouve au sommet de la hiérarchie. Donc si je remplace
 * String de l'exemple précédent par Object, la méthode getSuperclass() renverra null.
 * 
 * En plus de ça, l'objet Class permet de connaître la façon dont mon objet est constitué : interfaces, classe mère,
 * variables...
 * 
 * 3.) Connaître la liste des interfaces d'une classe
 * 
 * La méthode getInterfaces() retourne un tableau de Class.
 * Réf main
 * 
 * 4.) Connaître la liste des méthodes d'une classe
 * 
 * La méthode getMethods() de l'objet Class retourne un tableau d'objets Method présent dans le package 
 * java.lang.reflect.Method (à importer)
 * L'objet Method regorge lui aussi des méthodes intéressantes. Par exemple, getParameterTypes() retourne un
 * tableau de Class comportant les arguments pour une méthode donnée.
 * Réf main
 * 
 * 5.) Connaître la liste des champs (variable de classe ou d'instance)
 * 
 * Ici, on procédera de la même façon qu'avec les listes des méthodes, sauf que cette fois, la méthode invoquée, 
 * getDeclaredFields(), retournera un tableau d'objets Field
 * Réf main
 * 
 * 6.) Connaître la liste des constructeurs de la classe
 * 
 * La méthode getConstructors() de l'objet Class renvoie un tableau d'objets Constructor. Ce dernier possède lui
 * aussi (de même que Method) la méthode getParameterTypes().
 * Réf main
 * 
 * II./ L'instanciation dynamique
 * 
 * Dans ce qui suit se réflète une partie seulement de la puissance de l'objet Class.
 * Je me crée une classe Paire.
 * Le but est de créer un objet Paire sans utiliser l'opérateur new.
 * Pour instancier un objet Paire, commençons par récupérer ses constructeurs. Ensuite, je prépare un tableau 
 * contenant les données à insérer, puis j'invoque la méthode toString().
 * Réf main.
 * Voici la procédure :
 * i.) je stocke d'abord le nom de la classe en question en recourant à la méthode getName() de l'objet Class, comme
 * ceci : String nom = Paire.class.getName();
 * ce qui suit se met dans le bloc try{...}
 * ii.) je crée un objet Class, comme ceci:
 * Class cl = Class.forName(nom);
 * iii.) je peux déjà instancier l'objet Paire, mais sans paramètres, comme ceci :
 * Object o = cl.newInstance();
 * iv.) je crée les paramètres du constructeur :
 * Class[] types = new Class[]{String.class, String.class};
 * v.) je récupère le constructeur avec les deux paramètres :
 * Constructor ct = cl.getConstructor(types);
 * Attention, il ne s'agit pas ici de getConstructors() vue précédemment
 * vi.) je peux instancier en soumettant des paramètres, comme ceci :
 * Object o2 = ct.newInstance(new String[]{"valeur 1", "valeur deux"});
 * 
 * Voici comment appeler une méthode de mes instances :
 * Je stocke d'abord grâce à la méthode getMethod() (de l'objet Class cl) dans l'objet Method la méthode que je souhaite
 * appeler. La méthode getMethod() comporte les paramètres suivants :
 * 		-	le nom de la méthode entre guillemets en premier paramètre
 * 		-	viennent ensuite les paramètres de la méthode que je souhaite appeler
 * Ensuite, on recourt à la méthode invoke(Object obj, Object...args) de l'objet Method. Voici ses paramètres :
 * 		-	la variable Object que j'ai instancié
 * 		-	les paramètres de la méthode invoquée
 * 
 * Attention, il y a beaucoup d'exceptions. Les voici : 
 * SecurityException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessExcep -
 * tion, NoSuchMethodException, InvocationTargetException (celui-ci à importer).
 * 
 * Voilà, je viens de créer deux instances d'une classe sans passer par l'opérateur new. Mieux encore, j'ai pu 
 * appeler une méthode de mes instances. Cette façon de faire, quoique très lourde, pourrait m'être utile.
 * ==> Certains frameworks (ensemble d'objets offrant des fonctionnalités pour développer) utilisent la réflexivité
 * afin d'instancier leurs objets (notamment les frameworks basés sur des fichiers de configuration XML tels que
 * Hibernate, Struts, Spring...).
 * Je n'utiliserai pas ce genre de technique tous les jours. Cependant, il est possible que j'aie besoin, pour une 
 * raison quelconque, de stocker le nom d'une classe Java dans une base de données afin, justement, de pouvoir
 * l'utiliser plus tard. Dans ce cas, avec le nom de la classe en question, je pourrai la manipuler 
 * dynamiquement.
 * 
 * 
 */
public class Main_Reflex {

	public static void main(String[] args) {
		String nom2 = Paire.class.getName();
		try {
			Class clas = Class.forName(nom2);
			Object obj = clas.newInstance();
			Class[] lesTypes = new Class[]{String.class, String.class};
			Constructor construct = clas.getConstructor(lesTypes);
			Object myObj = construct.newInstance(new String[]{"UnDeuxTrois", "QuatreCinqSix"});
			Method myMeth = clas.getMethod("setValeurs", new Class[]{String.class, String.class});
			Method getVal1 = clas.getMethod("getValeur1");
			myMeth.invoke(obj, "Et un", "Et deux !!");
			System.out.println(getVal1.invoke(obj));
			
		} catch(ClassNotFoundException | SecurityException | IllegalArgumentException 
				| InstantiationException | NoSuchMethodException | IllegalAccessException 
				| InvocationTargetException e) {
			e.printStackTrace();
		}
		// Yeah man !		
		
		
		Class c = String.class;
		Class c2 = new String().getClass();
		System.out.println("La superclasse de "+c.getName()+ " est : "+ c.getSuperclass());
		System.out.println("La superclasse de "+c2.getName()+ " est : "+ c2.getSuperclass());
		System.out.println("La superclasse de "+Object.class.getName()+" est : "+ Object.class.getSuperclass());
		
		System.out.println("===========================================");
		//La méthode getInterfaces() retourne un tableau de Class
		Class[] faces = c.getInterfaces();
		//Pour voir le nombre d'interfaces :
		System.out.println("La classe "+c.getName()+" compte "+faces.length+" interfaces implémentées :");
		//on parcourt le tableau d'interfaces :
		for(int i = 0; i<faces.length; i++)
			System.out.println(faces[i]);
		
		System.out.println("===========================================");
		Method[] m = c.getMethods();
		System.out.println("Il y a "+m.length+ " méthodes dans cette classe :");
		//on parcourt le tableau de méthodes
		for(int i = 0; i<m.length; i++)
		{
			System.out.println(m[i]);
			
			//afficher les arguments respectifs pour chaque méthode :
			Class[] p = m[i].getParameterTypes();
			for(int j = 0; j<p.length; j++)
				System.out.println(p[j].getName());
			System.out.println("-------------------------------------");
		}
		System.out.println("===========================================");
		//afficher la liste des champs :
		Field[] f = c.getDeclaredFields();
		System.out.println("Affichage de la liste des champs de la classe String");
		for(int i = 0; i<f.length; i++) 
			System.out.println(f[i].getName() + " ==> type : " + f[i].getType() + " ==> accessible ? " 
					+f[i].isAccessible());
		
		System.out.println("===========================================");
		//afficher la liste des constructeurs
		Constructor[] construc = c.getConstructors();
		System.out.println("Affichage de la liste des constructeurs de la classe String");
		for(int i = 0; i < construc.length; i++){
			System.out.println(construc[i]);
			
			Class[] argCon = construc[i].getParameterTypes();
			for(int j=0; j<argCon.length; j++)
				System.out.println(argCon[j]);
			System.out.println("---------------------------------------");
		}
		
		System.out.println("==============================================");
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Partie 2 : L'instanciation dynamique
		String nom = Paire.class.getName();
		try{
			// On crée un objet Class
			Class cl = Class.forName(nom);
			//Nouvelle instance de la classe Paire
			Object o = cl.newInstance();
			
			//On crée les paramètres du constructeur
			Class[] types = new Class[]{String.class, String.class};
			//On récupère le constructeur avec les deux paramètres
			Constructor ct = cl.getConstructor(types);
			
			//On instancie l'objet avec le constructeur surchargé !
			Object o2 = ct.newInstance(new String[]{"valeur 1", "valeur 2"});
			
			//On va chercher la méthode toString (le deuxième argument est null, car cette méthode n'a aucun param)
			Method met = cl.getMethod("toString", null);
			//La méthode invoke exécute la méthode sur l'objet passé en paramètre
			//Pas de paramètre, donc le deuxième argument de invoke est null
			System.out.println("===================================================");
			System.out.println("Méthode "+ met.getName()+" sur o2 : \n"+met.invoke(o2, null));
			System.out.println("Méthode " + met.getName()+ " sur o : \n"+met.invoke(o, null));
			Method m2 = cl.getMethod("getValeur1", null);
			System.out.println("Méthode " + m2.getName()+ " sur o2 : \n"+m2.invoke(o2, null));
			Method setValeurs = cl.getMethod("setValeurs", types);
			//AUTRE NOTATION :
			//Method setValeurs = cl.getMethod("setValeurs", new Class[]{String.class, String.class});
			System.out.println("Méthode "+ setValeurs.getName()+ " sur o2 : \n"); 
			setValeurs.invoke(o2, new String[]{"Gauche", "Droite"});
			//autre notation :setValeurs.invoke(o2, "Gauche", "Droite");
			System.out.println("Méthode "+ met.getName()+" sur o2 : \n"+met.invoke(o2, null));
		}catch (SecurityException e){
			e.printStackTrace();
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(InstantiationException e){
			e.printStackTrace();
		}catch(IllegalAccessException e){
			e.printStackTrace();
		}catch(NoSuchMethodException e){
			e.printStackTrace();
		}catch(InvocationTargetException e){
			e.printStackTrace();
		}
		
	}

}
