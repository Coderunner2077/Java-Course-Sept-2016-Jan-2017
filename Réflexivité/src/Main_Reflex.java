import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * 												Java et la r�flexivit�
 * La r�flexivit�, aussi appel�e introspection, consiste � d�couvirir de fa�on dynamique des informations relatives
 * � une classe ou � un objet. C'est notamment utilis� au niveau de la machine virtuelle Java lors de l'ex�cution 
 * du programme. En gros, la machine virtuelle stocke les informations relatives � une classe dans un objet.
 * La r�flexivit� n'est que le moyen de conna�tre toutes les informations concernant une classe donn�e. On peut
 * m�me cr�er des instances de classes de fa�on dynamique gr�ce � cette notion.
 * 
 * I./ L'objet Class
 * 
 * Je n'examinerai ici que l'essentiel des fonctionnalit�s de l'objet Class .
 * 
 * 1.) R�cup�rer un objet Class
 * Au chargement d'une classe Java, la JVM cr�e automatiquement un objet. Celui-ci r�cup�re toutes les caract�ristiques
 * de la classe charg�e. Il s'agit d'un objet Class.
 * Exemple : je cr�e trois nouvelles classes Java. A l'ex�cution de mon programme, la JVM va cr�er une classe pour
 * chacune d'elles. Et cet objet poss�de une multitude de m�thodes permettant d'obtenir tous les renseignements
 * possibles et imaginables sur une classe.
 * Visitons sans plus tarder la classe String. 
   Voici les deux fa�ons (�quivalentes) de r�cup�rer un objet Class :
   Class c = String.class;
   Class c2 = new String().getClass();
 * 
 * 2.) Conna�tre la superclasse d'une classe
 * La m�thode getSuperclass() renvoie la superclasse d'une classe.
 * Exemple :
 * System.out.println("La superclasse de "+String.class.getName()+" est "+String.class.getSuperclass());
 * Note: la classe Object n'a pas de superclasse, car elle se trouve au sommet de la hi�rarchie. Donc si je remplace
 * String de l'exemple pr�c�dent par Object, la m�thode getSuperclass() renverra null.
 * 
 * En plus de �a, l'objet Class permet de conna�tre la fa�on dont mon objet est constitu� : interfaces, classe m�re,
 * variables...
 * 
 * 3.) Conna�tre la liste des interfaces d'une classe
 * 
 * La m�thode getInterfaces() retourne un tableau de Class.
 * R�f main
 * 
 * 4.) Conna�tre la liste des m�thodes d'une classe
 * 
 * La m�thode getMethods() de l'objet Class retourne un tableau d'objets Method pr�sent dans le package 
 * java.lang.reflect.Method (� importer)
 * L'objet Method regorge lui aussi des m�thodes int�ressantes. Par exemple, getParameterTypes() retourne un
 * tableau de Class comportant les arguments pour une m�thode donn�e.
 * R�f main
 * 
 * 5.) Conna�tre la liste des champs (variable de classe ou d'instance)
 * 
 * Ici, on proc�dera de la m�me fa�on qu'avec les listes des m�thodes, sauf que cette fois, la m�thode invoqu�e, 
 * getDeclaredFields(), retournera un tableau d'objets Field
 * R�f main
 * 
 * 6.) Conna�tre la liste des constructeurs de la classe
 * 
 * La m�thode getConstructors() de l'objet Class renvoie un tableau d'objets Constructor. Ce dernier poss�de lui
 * aussi (de m�me que Method) la m�thode getParameterTypes().
 * R�f main
 * 
 * II./ L'instanciation dynamique
 * 
 * Dans ce qui suit se r�fl�te une partie seulement de la puissance de l'objet Class.
 * Je me cr�e une classe Paire.
 * Le but est de cr�er un objet Paire sans utiliser l'op�rateur new.
 * Pour instancier un objet Paire, commen�ons par r�cup�rer ses constructeurs. Ensuite, je pr�pare un tableau 
 * contenant les donn�es � ins�rer, puis j'invoque la m�thode toString().
 * R�f main.
 * Voici la proc�dure :
 * i.) je stocke d'abord le nom de la classe en question en recourant � la m�thode getName() de l'objet Class, comme
 * ceci : String nom = Paire.class.getName();
 * ce qui suit se met dans le bloc try{...}
 * ii.) je cr�e un objet Class, comme ceci:
 * Class cl = Class.forName(nom);
 * iii.) je peux d�j� instancier l'objet Paire, mais sans param�tres, comme ceci :
 * Object o = cl.newInstance();
 * iv.) je cr�e les param�tres du constructeur :
 * Class[] types = new Class[]{String.class, String.class};
 * v.) je r�cup�re le constructeur avec les deux param�tres :
 * Constructor ct = cl.getConstructor(types);
 * Attention, il ne s'agit pas ici de getConstructors() vue pr�c�demment
 * vi.) je peux instancier en soumettant des param�tres, comme ceci :
 * Object o2 = ct.newInstance(new String[]{"valeur 1", "valeur deux"});
 * 
 * Voici comment appeler une m�thode de mes instances :
 * Je stocke d'abord gr�ce � la m�thode getMethod() (de l'objet Class cl) dans l'objet Method la m�thode que je souhaite
 * appeler. La m�thode getMethod() comporte les param�tres suivants :
 * 		-	le nom de la m�thode entre guillemets en premier param�tre
 * 		-	viennent ensuite les param�tres de la m�thode que je souhaite appeler
 * Ensuite, on recourt � la m�thode invoke(Object obj, Object...args) de l'objet Method. Voici ses param�tres :
 * 		-	la variable Object que j'ai instanci�
 * 		-	les param�tres de la m�thode invoqu�e
 * 
 * Attention, il y a beaucoup d'exceptions. Les voici : 
 * SecurityException, IllegalArgumentException, ClassNotFoundException, InstantiationException, IllegalAccessExcep -
 * tion, NoSuchMethodException, InvocationTargetException (celui-ci � importer).
 * 
 * Voil�, je viens de cr�er deux instances d'une classe sans passer par l'op�rateur new. Mieux encore, j'ai pu 
 * appeler une m�thode de mes instances. Cette fa�on de faire, quoique tr�s lourde, pourrait m'�tre utile.
 * ==> Certains frameworks (ensemble d'objets offrant des fonctionnalit�s pour d�velopper) utilisent la r�flexivit�
 * afin d'instancier leurs objets (notamment les frameworks bas�s sur des fichiers de configuration XML tels que
 * Hibernate, Struts, Spring...).
 * Je n'utiliserai pas ce genre de technique tous les jours. Cependant, il est possible que j'aie besoin, pour une 
 * raison quelconque, de stocker le nom d'une classe Java dans une base de donn�es afin, justement, de pouvoir
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
		//La m�thode getInterfaces() retourne un tableau de Class
		Class[] faces = c.getInterfaces();
		//Pour voir le nombre d'interfaces :
		System.out.println("La classe "+c.getName()+" compte "+faces.length+" interfaces impl�ment�es :");
		//on parcourt le tableau d'interfaces :
		for(int i = 0; i<faces.length; i++)
			System.out.println(faces[i]);
		
		System.out.println("===========================================");
		Method[] m = c.getMethods();
		System.out.println("Il y a "+m.length+ " m�thodes dans cette classe :");
		//on parcourt le tableau de m�thodes
		for(int i = 0; i<m.length; i++)
		{
			System.out.println(m[i]);
			
			//afficher les arguments respectifs pour chaque m�thode :
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
			// On cr�e un objet Class
			Class cl = Class.forName(nom);
			//Nouvelle instance de la classe Paire
			Object o = cl.newInstance();
			
			//On cr�e les param�tres du constructeur
			Class[] types = new Class[]{String.class, String.class};
			//On r�cup�re le constructeur avec les deux param�tres
			Constructor ct = cl.getConstructor(types);
			
			//On instancie l'objet avec le constructeur surcharg� !
			Object o2 = ct.newInstance(new String[]{"valeur 1", "valeur 2"});
			
			//On va chercher la m�thode toString (le deuxi�me argument est null, car cette m�thode n'a aucun param)
			Method met = cl.getMethod("toString", null);
			//La m�thode invoke ex�cute la m�thode sur l'objet pass� en param�tre
			//Pas de param�tre, donc le deuxi�me argument de invoke est null
			System.out.println("===================================================");
			System.out.println("M�thode "+ met.getName()+" sur o2 : \n"+met.invoke(o2, null));
			System.out.println("M�thode " + met.getName()+ " sur o : \n"+met.invoke(o, null));
			Method m2 = cl.getMethod("getValeur1", null);
			System.out.println("M�thode " + m2.getName()+ " sur o2 : \n"+m2.invoke(o2, null));
			Method setValeurs = cl.getMethod("setValeurs", types);
			//AUTRE NOTATION :
			//Method setValeurs = cl.getMethod("setValeurs", new Class[]{String.class, String.class});
			System.out.println("M�thode "+ setValeurs.getName()+ " sur o2 : \n"); 
			setValeurs.invoke(o2, new String[]{"Gauche", "Droite"});
			//autre notation :setValeurs.invoke(o2, "Gauche", "Droite");
			System.out.println("M�thode "+ met.getName()+" sur o2 : \n"+met.invoke(o2, null));
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
