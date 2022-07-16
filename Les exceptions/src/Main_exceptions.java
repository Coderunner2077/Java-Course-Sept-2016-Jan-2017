/*
 *                                                 LES EXCEPTIONS
 *
 *Une exception est une erreur se produisant dans un prog qui conduit le plus souvent � l'arr�t de celui-ci. Le 
 *gros message affich� en rouge que l'on rencontre lors d'un tel arr�t est g�n�r� par une exception... qui n'a
 *pas �t� captur�e.
 *Le fait de g�rer les exceptions s'appelle aussi "capturer une exception". Le principe consiste � rep�rer un
 *morceau de code (par ex, une division par 0) qui pourrait g�n�rer une exception, de capturer l'exception
 *correspondante et enfin, de la traiter, i.e. d'afficher un message personnalis� et continuer l'ex�cution.
 *
 *I./ Le bloc try{...} catch{...}
 *
 *Java contient une classe nomm�e Exception dans laquelle sont repertori�s diff�rents cas d'erreur. La division par
 *0 en fait partie.
 *Ref main une belle erreur : 99
 *Une division par 0 est une ArithmeticException.
 *Je vais maintenant afficher un message personnalis� lors d'une division par 0.
 *Avec try{...}, j'isole le code susceptible de lever une exception, et dans le cas d'une exception lev�e, 
 *le bloc catch{...} permet d'ex�cuter un code qui se substituera au code du bloc try (qui lui, sera interrompu).
 *Le param�tre de la clause catch permet de connaitre l'exception qui sera captur�e.
 *Et l'objet - ici e - peut servir � pr�ciser la nature de l'exception gr�ce � l'appel de la fonction getMessage()
 *Le mot cl� finally:
 *Je peux constater que, m�me si je tente d'intercepter une ArithmeticException (celle-ci se d�clenche lors
 *d'un probl�me de cast), gr�ce � la clause finally, un morceau de code est ex�cut� quoiqu'il arrive. Cela
 *est surtout utilis� lorsque je dois m'assurer d'avoir ferm� un fichier, clos ma connexion � une base de donn�es
 *ou un stocket (une connexion r�seau)
 *
 *II./ Les exceptions personnalis�es
 *1.) Cr�ation d'une exception personnalis�e
 *Attention : Je dois faire h�riter toutes mes classes d'exception personnalis�e de la classe Exception.
 *Je vais perfectionner un peu la gestion de mes objets Ville et Capitale : en mettant en oeuvre une exception
 *personnalis�e afin d'interdire l'instanciation d'un objet Ville ou Capitale pr�sentant un nombre n�gatif d'habitants.
 *Voici les �tapes d'une telle proc�dure :
 *		-	cr�er une classe h�ritant de la classe Exception : NombreHabitantsException (par convention, les 
 *		exceptions ont un nom se terminant par Exception)
 *		-	renvoyer l'exception lev�e � ma classe NombreHabitantsException
 *		-	ensuite, g�rer celle-ci dans ma classe NombreHabitantsException
 *
 *Pour faire cela, je dois apprendre deux mots cl�s :
 *		-	throws : ce mot cl� permet de signaler � la JVM qu'un morceau de code, une m�thode, une classe... est
 *		potentiellement dangereux et qu'il faut utiliser un bloc try{...}catch{...}. Il est suivi du nom de la
 *		classe qui va g�rer l'exception
 *		-	throw : celui-ci permet tout simplement de lever une exception manuellement en instanciant un
 *		objet de type Exception (ou un objet h�rit�). Dans l'exemple de l'ArithmeticException, il y a quelque part
 *		dans les m�andres de Java un throw new ArithmeticException
 *
 *Une fois ma classe NombreHabitantsException cr��e, je dois aller dans le constructeur de ma Ville afin de faire
 *en sorte de cr�er un objet de type NombreHabitantsException dans le cas o� l'on cr�e une ville avec un nb n�gatif
 * d'habitants.
 * Une fois les 3 �tapes termin�es, je ne peux pas instancier une Ville ou Capitale (avec param�tres) 
 * A MOINS QUE je ne mette l'instanciation en question dans un bloc try{}catch{}. 
 * Mais attention, si je d�clare mon objet Ville dans le bloc  try{}catch{}, il n'existera pas en dehors de ce 
 * dernier. Je dois donc d�clarer mon objet Ville avant ce bloc et l'instancier � l'int�rieur.
 * Mais attention, nouveau probl�me : que se passera-t-il si je d�clare une Ville avec un nombre n�gatif 
 * d'habitants? ==> en plus d'une exception lev�e pour le nombre d'habitants n�gatif, j'obtiendrai aussi une 
 * NullPointerException.
 * Pour r�soudre ce probl�me, on peut utiliser une simple clause finally avec, � l'int�rieur, l'instanciation d'un
 * objet Ville par d�faut si celui-ci est null
 * 
 * 2.) Gestion de l'exception
 * 
 * Il serait de bon ton de pouvoir r�colter plus de renseignements concernant l'exception. Par exemple, il serait
 * int�ressant de r�afficher le nombre d'habitants que l'objet a re�u.
 * Pour ce faire, je n'ai qu'� cr�er un deuxi�me constructeur dans ma classe NombreHabitantsException qui prend
 * un nombre d'habitants en param�tre.
 * Et je n'ai pas � r��crire ce param�tre dans le bloc catch()
 * Autre chose : la m�thode printStackTrace() (de l'objet Exception) renvoie le nom de l'exception lev�e, ainsi
 * que les num�ros des lignes se rapportant � l'exception lev�e
 * 
 * III./ La gestion de plusieurs exceptions
 * 
 * Effectivement, je peux capturer plusieurs exceptions � la fois.
 * Bien entendu, ceci est valable pour toute sorte d'exception, qu'elle soient personnalis�es ou inh�rentes � 
 * JAVA ! Par exemple ==> exception pour un nom de ville de moins de 3 lettres.
 * Note: dans le constructeur de la classe personnalis�e NomVilleException, j'ai utilis� super ==> pour afficher
 * le message d'erreur en utilisant la m�thode getMessage().
 * Autre chose importante : dans l'instruction throws, je mets toutes les exceptions correspondantes s�par�es par 
 * une virgule.
 * Je glisse un deuxi�me bloc catch pour traiter la deuxi�me exception. JE PEUX EN AJOUTER AUTANT QUE JE VEUX !
 * Si je mets un nombre d'habitants n�gatif et un nom avec moins de 3 lettres, c'est l'exception du nb d'habitants
 * qui sera lev� et pour cause : il s'agit de la 1re condition dans mon constructeur. Lorsque plusieurs exceptions,
 * sont g�r�es par une portion de code, il faut penser � mettre les bloc catch dans un ordre pertinent.
 * 
 * IV./ Depuis Java 7 : le multi-catch
 * Depuis Java 7, il est possible de catcher plusieurs exceptions dans l'instruction catch. Ceci se fait gr�ce �
 * l'op�rateur " | " qui permet d'informer la JVM que le bloc de code est susceptible d'engendrer plusieurs types
 * d'exception. Cela permet d'avoir un code plus compact. R�f main 152
 * Remarque : la d�claration de nom de l'exception ==> un seul nom pour deux exceptions ?? c'est assez d�licat...
 * Remarque : Une instanciation lanc�e par le biais de l'instruction throw doit �tre d�clar�e avec throws au 
 * pr�alable !
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
			System.out.println("action faite syst�matiquement");
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
		//Gestion de plusieurs exceptions diff�rentes
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
