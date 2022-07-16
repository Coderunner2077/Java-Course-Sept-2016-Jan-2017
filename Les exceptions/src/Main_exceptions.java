/*
 *                                                 LES EXCEPTIONS
 *
 *Une exception est une erreur se produisant dans un prog qui conduit le plus souvent à l'arrêt de celui-ci. Le 
 *gros message affiché en rouge que l'on rencontre lors d'un tel arrêt est généré par une exception... qui n'a
 *pas été capturée.
 *Le fait de gérer les exceptions s'appelle aussi "capturer une exception". Le principe consiste à repérer un
 *morceau de code (par ex, une division par 0) qui pourrait générer une exception, de capturer l'exception
 *correspondante et enfin, de la traiter, i.e. d'afficher un message personnalisé et continuer l'exécution.
 *
 *I./ Le bloc try{...} catch{...}
 *
 *Java contient une classe nommée Exception dans laquelle sont repertoriés différents cas d'erreur. La division par
 *0 en fait partie.
 *Ref main une belle erreur : 99
 *Une division par 0 est une ArithmeticException.
 *Je vais maintenant afficher un message personnalisé lors d'une division par 0.
 *Avec try{...}, j'isole le code susceptible de lever une exception, et dans le cas d'une exception levée, 
 *le bloc catch{...} permet d'exécuter un code qui se substituera au code du bloc try (qui lui, sera interrompu).
 *Le paramètre de la clause catch permet de connaitre l'exception qui sera capturée.
 *Et l'objet - ici e - peut servir à préciser la nature de l'exception grâce à l'appel de la fonction getMessage()
 *Le mot clé finally:
 *Je peux constater que, même si je tente d'intercepter une ArithmeticException (celle-ci se déclenche lors
 *d'un problème de cast), grâce à la clause finally, un morceau de code est exécuté quoiqu'il arrive. Cela
 *est surtout utilisé lorsque je dois m'assurer d'avoir fermé un fichier, clos ma connexion à une base de données
 *ou un stocket (une connexion réseau)
 *
 *II./ Les exceptions personnalisées
 *1.) Création d'une exception personnalisée
 *Attention : Je dois faire hériter toutes mes classes d'exception personnalisée de la classe Exception.
 *Je vais perfectionner un peu la gestion de mes objets Ville et Capitale : en mettant en oeuvre une exception
 *personnalisée afin d'interdire l'instanciation d'un objet Ville ou Capitale présentant un nombre négatif d'habitants.
 *Voici les étapes d'une telle procédure :
 *		-	créer une classe héritant de la classe Exception : NombreHabitantsException (par convention, les 
 *		exceptions ont un nom se terminant par Exception)
 *		-	renvoyer l'exception levée à ma classe NombreHabitantsException
 *		-	ensuite, gérer celle-ci dans ma classe NombreHabitantsException
 *
 *Pour faire cela, je dois apprendre deux mots clés :
 *		-	throws : ce mot clé permet de signaler à la JVM qu'un morceau de code, une méthode, une classe... est
 *		potentiellement dangereux et qu'il faut utiliser un bloc try{...}catch{...}. Il est suivi du nom de la
 *		classe qui va gérer l'exception
 *		-	throw : celui-ci permet tout simplement de lever une exception manuellement en instanciant un
 *		objet de type Exception (ou un objet hérité). Dans l'exemple de l'ArithmeticException, il y a quelque part
 *		dans les méandres de Java un throw new ArithmeticException
 *
 *Une fois ma classe NombreHabitantsException créée, je dois aller dans le constructeur de ma Ville afin de faire
 *en sorte de créer un objet de type NombreHabitantsException dans le cas où l'on crée une ville avec un nb négatif
 * d'habitants.
 * Une fois les 3 étapes terminées, je ne peux pas instancier une Ville ou Capitale (avec paramètres) 
 * A MOINS QUE je ne mette l'instanciation en question dans un bloc try{}catch{}. 
 * Mais attention, si je déclare mon objet Ville dans le bloc  try{}catch{}, il n'existera pas en dehors de ce 
 * dernier. Je dois donc déclarer mon objet Ville avant ce bloc et l'instancier à l'intérieur.
 * Mais attention, nouveau problème : que se passera-t-il si je déclare une Ville avec un nombre négatif 
 * d'habitants? ==> en plus d'une exception levée pour le nombre d'habitants négatif, j'obtiendrai aussi une 
 * NullPointerException.
 * Pour résoudre ce problème, on peut utiliser une simple clause finally avec, à l'intérieur, l'instanciation d'un
 * objet Ville par défaut si celui-ci est null
 * 
 * 2.) Gestion de l'exception
 * 
 * Il serait de bon ton de pouvoir récolter plus de renseignements concernant l'exception. Par exemple, il serait
 * intéressant de réafficher le nombre d'habitants que l'objet a reçu.
 * Pour ce faire, je n'ai qu'à créer un deuxième constructeur dans ma classe NombreHabitantsException qui prend
 * un nombre d'habitants en paramètre.
 * Et je n'ai pas à réécrire ce paramètre dans le bloc catch()
 * Autre chose : la méthode printStackTrace() (de l'objet Exception) renvoie le nom de l'exception levée, ainsi
 * que les numéros des lignes se rapportant à l'exception levée
 * 
 * III./ La gestion de plusieurs exceptions
 * 
 * Effectivement, je peux capturer plusieurs exceptions à la fois.
 * Bien entendu, ceci est valable pour toute sorte d'exception, qu'elle soient personnalisées ou inhérentes à 
 * JAVA ! Par exemple ==> exception pour un nom de ville de moins de 3 lettres.
 * Note: dans le constructeur de la classe personnalisée NomVilleException, j'ai utilisé super ==> pour afficher
 * le message d'erreur en utilisant la méthode getMessage().
 * Autre chose importante : dans l'instruction throws, je mets toutes les exceptions correspondantes séparées par 
 * une virgule.
 * Je glisse un deuxième bloc catch pour traiter la deuxième exception. JE PEUX EN AJOUTER AUTANT QUE JE VEUX !
 * Si je mets un nombre d'habitants négatif et un nom avec moins de 3 lettres, c'est l'exception du nb d'habitants
 * qui sera levé et pour cause : il s'agit de la 1re condition dans mon constructeur. Lorsque plusieurs exceptions,
 * sont gérées par une portion de code, il faut penser à mettre les bloc catch dans un ordre pertinent.
 * 
 * IV./ Depuis Java 7 : le multi-catch
 * Depuis Java 7, il est possible de catcher plusieurs exceptions dans l'instruction catch. Ceci se fait grâce à
 * l'opérateur " | " qui permet d'informer la JVM que le bloc de code est susceptible d'engendrer plusieurs types
 * d'exception. Cela permet d'avoir un code plus compact. Réf main 152
 * Remarque : la déclaration de nom de l'exception ==> un seul nom pour deux exceptions ?? c'est assez délicat...
 * Remarque : Une instanciation lancée par le biais de l'instruction throw doit être déclarée avec throws au 
 * préalable !
 *
 *
 *
 */

public class Main_exceptions {
	public static void main(String[] args){
		int j = 20, i = 0;
		
		try{
			System.out.println(j/i);
		} catch (ArithmeticException e){
			System.out.println("L'erreur : " + e.getMessage());
		
		}
		System.out.println("Show must go on");
		System.out.println("-------------------------------");
		/*
		try{
			System.out.println("==> "+1/0);
		} catch (ClassCastException e2){
			e2.printStackTrace();
		}
		finally {
			System.out.println("action faite systématiquement");
		}
		*/
		/*
		Ville v1 = null;
		try{
			v1 = new Ville("Nice", -10, "France");
			}catch (NombreHabitantsException v){}
		finally{
			if(v1 == null)
				v1 = new Ville();
		}
		System.out.println(v1.toString());
		*/
		// GESTION DE PLUSIEURS EXCEPTIONS
		Ville v2 = null;
		try{
			v2 = new Ville("Ri", 1200, "Brasil");
		}
		// Gestion de l'exception sur le nombre d'habitants
		catch(NombreHabitantsException e){
			e.printStackTrace();
		}
		
		// Gestion de l'exception sur le nom de ville
		catch(NomVilleException e2){
			System.out.println(e2.getMessage());
		}
		finally{
			if(v2 == null)
				v2 = new Ville();
		}
		
		// LE MULTI-CATCH
		Capitale v3 = null;
		try{
			v3 = new Capitale("Po", -12000, "France", "Tour");
		}
		//Gestion de plusieurs exceptions différentes
		catch(NombreHabitantsException | NomVilleException e3){
			System.out.println(e3.getMessage());
		}
		finally{
			if(v3 == null)
				v3 = new Capitale();
		}
		
		Capitale v4 = null;
		try {
			v4 = new Capitale("Xi", -10, "Xiao", "Xiine");
		} catch (NomVilleException | NombreHabitantsException e4) {
			e4.printStackTrace();
		} finally {
			if(v4 == null)
				v4 = new Capitale();
		}
		
		try {
			v4.setNomVille("Zo");
		} catch(NomVilleException e5) {
			e5.printStackTrace();
		}
		
		try {
			v4.setNbreHabitants(-1000000);
		} catch (NombreHabitantsException e6) {
			System.out.println(e6.getMessage() + "\n\tNombre d'habitants de cette ville :"
		+ v4.getNbreHabitants());
		}
		
		
	}

}
