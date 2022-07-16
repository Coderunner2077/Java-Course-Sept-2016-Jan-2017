import java.util.concurrent.ForkJoinPool;

import fr.runnable.CompteEnBanque;
import fr.runnable.RunImpl;

/*
 * 								Ex�cuter ds t�ches simultan�ment
 * Les threads sont des fils d'ex�cution d'un programme. Lorsqu'on en cr�e plusieurs, on
 * peut ex�cuter des t�ches simultan�ment.
 * Dans ce chapitre, je verrai comment cr�er une (ou plusieurs) nouvelle(s) pile(s) de
 * fonctions gr�ce � ces fameux threads. Il existe une classe Thread dans Java permettant
 * leur gestion. On verra qu'il existe 2 fa�ons de cr�er un nouveau thread.
 * 
 * I./ Une classe h�rit�e de Thread
 * 
 * Lorsque j'ex�cute un programme, un thread est lanc� ! Le thread correspond � la pile et
 * chaque nouveau thread cr�� g�n�re une pile d'ex�cution. 
 * R�f main : Thread.currentThread().getName() renvoie le nom du thread principal de mon
 * application (ie main ici).
 * On peut voir un thread comme une machine bien huil�e capable d'effectuer les t�ches que
 * l'on lui sp�cifie. Une fois instanci�, un thread attend son lancement. D�s que c'est 
 * fait, il invoque sa m�thode run() qui va lui permettre de conna�tre les t�ches qu'il
 * a � effectuer.
 * Pour cr�er un nouveau thread, il existe deux mani�res de faire :
 * 		-	cr�er une classe h�ritant de Thread
 * 		-	cr�er une impl�mentation de la classe Runnable et instancier un objet Thread
 * 			avec l'impl�mentation de cette interface
 * Voyons d'abord la 1re solution. Tout ce qu'il y a � faire, c'est red�finir la m�thode
 * run() de mon objet afin qu'il sache ce qu'il doit faire. Et aussi, leur donner des noms
 * pour pouvoir les diff�rencier.
 * Je cr�e donc une classe g�rant tout cela qui contient un constructeur comprenant
 * un String en param�re pour sp�cifier le nom du thread. Cette classe doit �galement
 * comprendre une m�thode getName() afin de retourner ce nom. Aucun import n�cessaire.
 * R�f main => je peux voir que l'ordre d'ex�cution de deux diff�rents threads est souvent
 * al�atoire, car Java utilise un ordonnanceur. En effet, si j'utilise
 * plusieurs threads dans une m�me application, ceux-ci ne s'ex�cutent pas toujours
 * en m�me temps. En fait, l'ordonnanceur g�re les threads de fa�on al�atoire : il va en
 * faire tourner un pendant un certain temps, puis un autre, puis revenir au premier, etc.,
 * jusqu'� ce qu'ils soient termin�s. Lorsque l'ordonnanceur passe d'un thread � un autre,
 * le thread interrompu est mis en sommeil tandis que l'autre est en �veil.
 * Note : avec les processeurs multi-coeurs, il est possible d'ex�cuter plusieurs t�ches
 * exactement en m�me temps.
 * 
 * Un thread peut pr�senter plusieurs �tats:
 * 		-	NEW : lors de sa cr�ation
 * 		-	RUNNABLE : lorsqu'on invoque la m�thode start(), le thread est pr�t � travailler
 * 		-	TERMINATED : lorsque le thread a effectu� toutes ses t�ches; on dit aussi qu'il
 * 			est "mort". On ne peut alors plus le relancer par la m�thode start()
 * 		-	TIMED_WAITING : lorsque le thread est en pause (quand j'utilise sleep() par ex)
 * 		-	WAITING : lorsque le thread est en attente ind�finie
 * 		-	BLOCKED : lorsque l'ordonnanceur place un thread en sommeil pour en utiliser
 * 			un autre, il lui impose cet �tat.
 * 
 * Un thread est consid�r� comme termin� lorsque la m�thode run() est �t�e de sa pile
 * d'ex�cution. En effet, une nouvelle pile d'ex�cution contient � sa base la m�thode run()
 * de mon thread. Une fois celle-ci d�pil�e, ma nouvelle pile est d�truite.
 * En fait, le thread principal cr�e un second thread qui se lance et construit une pile
 * dont la base est la m�thode run() de ce dernier; et cette pile appelle une m�thode, 
 * l'empile, effectue toutes les op�rations demand�es, et une fois qu'elle a termin�, elle 
 * d�pile la m�thode run() invoqu�e. La m�thode run() prend fin, la pile est alors 
 * d�truite. 
 * Je vais modifier ma classe TestThread afin d'afficher les �tats de mes threads que je
 * peux r�cup�rer gr�ce � la m�thode getState().
 * Dans ma classe TestThread, j'ai ajout� qq instructions d'affichage afin de visualiser
 * l'�tat courant de mes objets. Mais j'ai aussi ajout� un constructeur suppl�mentaire
 * prenant un Thread en param�tre afin d'obtenir l'�tat de mon 1er thread lors de
 * l'ex�cution du second.
 * Si j'avais un processus � un seul coeur, le 1er aurait �t� dans l'�tat BLOCKED lorsque
 * le 2nd est en cours de traitement : les threads ne s'ex�cutant pas en m�me temps si
 * le processeur n'est dot� que d'un seul coeur.
 * Je peux aussi voir que les op�rations effectu�es par mes threads sont en fait cod�es
 * dans la m�thode run().
 * Faire h�riter un objet de Thread permet de cr�er un nouveau thread tr�s facilement. Je
 * peux cependant proc�der diff�remment : red�finir uniquemenet ce que doit effectuer le
 * nouveau thread gr�ce � l'interface Runnable. Dans ce cas, je red�finis uniquement ce
 * que la machine doit faire, et non la machine tout enti�re !
 * 
 * II./ Utiliser l'interface Runnable
 * 
 * Ne red�finir que les t�ches que le nouveau thread doit effectuer comprend un autre
 * avantage : la classe dont je dispose n'h�rite d'aucune autre ! En effet, dans mon
 * test pr�c�dent, la classe TestThread ne pourra pas h�riter d'une autre classe, 
 * puisqu'elle h�rite d�j� de Thread, tandis qu'avec une impl�mentation de Runnable, rien
 * n'emp�che ma classe d'h�riter de JFrame, par exemple...
 * Lors de l'impl�mentation de l'interface Runnable, il n'y a que la m�thode run() � 
 * red�finir.
 * Illustration :
 * R�f diagramme_runnable.png => j'ai cr�� un objet CompteEnBanque contenant une somme
 * d'argent par d�faut (disons 100), une m�thode pour retirer de l'argent(retraitArgent())
 * et une m�thode retournant le solde (getSolde()). Cependant, avant de retirer de 
 * l'argent, je v�rifierai que je ne suis pas � d�couvert. Mon thread va effectuer 
 * autant d'op�rations que je le souhaite.
 * Je r�sume :
 * 		-	mon application peut contenir un ou plusieurs objets Thread
 * 		-	ceux-ci ne peuvent �tre constitu�s que d'un objet de type Runnable
 * 		-	dans mon cas, les objets Thread contiendront une impl�mentation de 
 * 			Runnable : RunImpl
 * 		-	cette impl�mentation poss�de un objet CompteEnBanque
 * 
 * R�f package fr.runnable.
 * Rien d'extraordinaire jusqu'ici. J'ajoute maintenant un nom � mon impl�mentation et 
 * je cr�e un deuxi�me thread utilisant un 2e compte. Il fo penser � modifier l'impl�men -
 * tation afin que je puisse conna�tre le thread qui travaille.
 * Lorsque j'utilise deux instances distinctes de RunImpl utilisant elles-m�mes deux 
 * instances distinctes de CompteEnBanque ==> il n'y a rien de perturbant, tout va
 * comme sur des roulettes.
 * Mais que se passerait-il si j'utilisais la m�me instance de CompteEnBanque pour deux
 * threads diff�rents ? ==> rien de perturbant puisque j'ai un processeur double-coeur 
 * wesch.
 * Sinon, mais que ce se passerait-il si j'utilisais la m�me instance de CompteEnBanque
 * pour plus de deux threads diff�rents ? ==> on peut voir des incoh�rences monumentales !
 * On pourrait penser que le compte aurait �t� d�bit� par pas de deux jusqu'� la fin sans
 * obtenir d'aberrations de ce genre (r�f main), puisque l'on utilise le m�me objet. Eh
 * bien, non ! Pourquoi ? Tout simplement parce que l'ordonnanceur de Java place les 
 * threads en sommeil quand il le d�sire, et lorsque le thread qui �tait en sommeil se
 * r�veille, il reprend sont travail l� o� il l'avait laiss� !
 * 
 * Voyons maintenant comment r�soudre ce probl�me.
 * 
 *  III./ Synchroniser ses threads
 *  
 *  Tout est dans le titre. En fait, ce qu'il faut faire, c'est indiquer � la JVM  qu'un
 *  thread est en train d'utiliser des donn�es qu'un autre thread est susceptible 
 *  d'alt�rer.
 *  Ainsi, lorsque l'ordonnanceur met en sommeil un thread qui traitait des donn�es
 *  utilisables par un autre thread, ce premier thread garde la priorit� sur les donn�es
 *  et tant qu'il n'a pas termin� son travail, les autres threads n'ont pas la 
 *  possibilit� d'y toucher (m�me pas vrai !).
 *  Cela s'appelle synchroniser les threads. Cette op�ration est tr�s d�licate et demande
 *  bcp de comp�tences en programmation... Voici � quoi ressemble ma m�thode 
 *  retraitArgent() synchronis�e (dans la classe CompteEnBanque) :
 *  public synchronized void retraitArgent(int retrait){
 *  	solde = solde - retrait;
 *  	System.out.println("Le nouveau solde est : " + solde);
 *  }
 *  En effet, il suffit d'ajouter dans la d�claration de la m�thode le mot cl� 
 *  synchronized, gr�ce auquel la m�thode est inaccessible � un thread si elle est d�j�
 *  utilis�e par un autre thread. Ainsi, les threads cherchant � utiliser des m�thodes
 *  d�j� prises en charge par un autre thread sont plac�s dans une "liste d'attente".
 *  
 *  Dans un contexte informatique, il peut �tre pratique et s�curis� d'utiliser des
 *  threads et des m�thodes synchronis�es lors d'acc�s � des services distants tels qu'un
 *  serveur d'applications ou un SGBD (Syst�me de Gestion de Base de Donn�es).
 *  
 *  ATTENTION ! CECI N'EST PAS CONFORME A MON OBSERVATION !!
 *  EN EFFET, LORSQUE, DISONS 3 THREADS, UTILISENT LA M�ME METHODE SYNCHRONISEE D'UNE M�ME
 *  INSTANCE DE CompteEnBanque, CETTE METHODE EST ACCESSIBLE A TOUS LES TROIS, MAIS LOIN
 *  D'AVOIR DES ABERRATIONS QUELCONQUES, LES 3 THREADS UTILISENT LES DONNEES DE LA
 *  M�ME INSTANCE DE FACON SYNCHRONISEE. C'EST COMME SI CHAQUE THREAD SAVAIT EN QUEL
 *  ETAT LE THREAD PRECEDENT A LAISSE LES DONNEES DE L'INSTANCE DU CompteEnBanque.
 *  
 *  SpinOff mon animation => je retourne � mon animation qui n'attend qu'un
 *  petit thread pour fonctionner correctement.
 *  
 *  IV./ Contr�ler son animation
 *  
 *  Il me suffit de cr�er un nouveau thread lorsqu'on clique sur le bouton Go en lui
 *  passant une impl�mentation de Runnable en param�tre qui, elle, va appeler la m�thode
 *  go() (sans oublier de remettre le bool�en de contr�le � true). 
 *  R�f Animation => Fenetre
 *  
 *  V./ Depuis Java 7, le pattern Fork/Join
 *  
 *  La version 7 met � disposition des d�veloppeurs plusieurs classes permettant de mettre
 *  en application ce qu'on appelle "le pattern Fork/Join". Ce dernier n'est rien de plus
 *  que la mise en application d'un viel adage : "diviser pour mieux r�gner". Dans certains
 *  cas, il serait bon de pouvoir d�couper une t�che en plusieurs sous-t�ches, faire en
 *  sorte que ces sous-t�ches s'ex�cutent en parall�le et pouvoir r�cup�rer le r�sultat
 *  de tout ceci une fois que tout est termin�. C'est exactement ce qu'il est possible
 *  de faire avec ces nouvelles classes.
 *  Avant de commencer, pr�cisons qu'il y a un certain nombre de pr�r�quis � cela :
 *  	-	la machine qui ex�cutera la t�che devra poss�der un processeur � plusieurs
 *  		coeurs (2, 4 ou plus)
 *  	-	la t�che doit pouvoir �tre d�coup�e en plusieurs sous-t�ches
 *  	-	s'assurer qu'il y a un r�el gain de performance ! (Dans certains cas, d�couper
 *  		une t�che rend le traitement plus long)
 *  
 *  En guise d'exemple, je vais faire une recherche de fichiers (simplifi�e au maximum
 *  pour ne pas surcharger le code). R�f package fr.folder.scanner pour voir les classes
 *  utilis�es, pour le moment sans la gestion Fork/Join.
 *  R�f Main_Folder => r�sultat du scan = 106 secondes
 *  
 *  Il est possible de d�couper le scan de chaque dossier dans une sous-t�che, et c'est
 *  exactement ce que je vais faire. Pour ce faire, je dois faire h�riter ma classe
 *  FolderScanner d'une des classes permettant ce d�coupage. La plateforme Java 7 me met
 *  � disposition deux classes qui h�ritent de la classe abstraite ForkJoinTask<V> :
 *  	-	RecursiveAction : classe permettant de d�couper une t�che ne renvoyant aucune
 *  		valeur particuli�re. Elle h�rite de ForkJoinTask<Void>
 *  	-	RecursiveTask<V> : similaire � la classe pr�c�dente mais retournant une valeur,
 *  		de type <V>, en fin de traitement.
 *  
 * C'est cette derni�re que je vais utiliser pour pouvoir retourner le nombre de fichiers
 * trouv�s.
 * Je vais devoir utiliser, en plus du d�coupage, un objet dont le r�le sera de superviser
 * l'ex�cution des t�ches et des sous-t�ches afin de pouvoir fusionner les threads en 
 * fin de traitement : ForkJoinPool.
 * 
 * Voici comment �a fonctionne :
 * Les objets qui permettent le d�coupage en sous-t�ches fournissent trois m�thodes
 * qui permettent cette gestion :
 * 	-	compute() : m�thode abstraite � red�finir dans l'objet h�ritant afin de
 * 			d�finir le traitement � effectuer
 * 	-	fork() : m�thode qui cr�e un nouveau thread dans le pool de thread (ForkJoinPool)
 * 	-	join() : m�thode qui permet de r�cup�rer le r�sultat de la m�thode compute()
 * 
 * Ces classes n�cessitent que je red�finisse la m�thode compute() afin de d�finir ce 
 * qu'il y a � faire. 
 * R�f schema_forkjointask.png
 * Concr�tement, avec mon exemple, voici ce qu'il va se passer :
 * 	-	je vais lancer le scan de mon dossier
 * 	-	mon objet qui sert � scanner le contenu va v�rifier le contenu pour voir s'il n'y
 * 			a pas de sous-dossiers
 * 	-	pour chaque sous-dossier, je vais cr�er une nouvelle t�che et la lancer
 * 	-	je vais compter le nombre de fichiers qui correspond � mes crit�res dans le dossier
 * 			en cours de scan
 * 	-	je vais r�cup�rer le nombre de fichiers trouv�s par les ex�cutions en t�che de fond
 * 	-	je vais retourner le r�sultat final
 * 
 * R�f package fr.fork.join
 * ==> le scan en mode mono thread du dossier "C:\\Users\\Harry" a pris 106 secondes, alors
 * qu'en mode Fork/Join, �a n'a pris que 7 secondes
 * 
 * Cependant, je constate que l'utilisation de ce mode est tr�s gourmand en ressources
 * processeurs. Il est donc � utiliser avec parcimonie.
 * Dans cet exemple, j'ai cr�� dynamiquement autant de threads que n�cessaires pour
 * traiter mes t�ches. Je n'aurai peut-�tre pas besoin de faire ceci pour des probl�mes
 * o� seulement 2 ou 3 sous-t�ches suffisent, surtout si je le sais d'avance. L'id�e
 * ma�tresse revient � d�finir un seuil � partir duquel le traitement se fera en mode
 * Fork/Join, sinon, il se fera dans un seul thread (Attention, il se peut que le mode
 * Fortk/Join soit plus lent et consommateur qu'en mode normal). Voici comment proc�der
 * dans ce genre de cas : R�f CalculSuite 
 * 
 * 
 *  
 *  
 *  
 *  
 * 
 */
public class Main_threads {

	public static void main(String[] args) {
	
		System.out.println("Le nom du thread principal est : " 
	+ Thread.currentThread().getName());
		/*
		TestThread t = new TestThread("A");
		TestThread t2 = new TestThread(" B");
		//TestThread t3 = new TestThread("  C");
		t.start();
		t2.start();
		//t3.start();
		*/
		TestThread t = new TestThread("A");
		TestThread t2 = new TestThread(" B", t);
		TestThread t3 = new TestThread("  C", t2);
		
		try{
			Thread.sleep(1000);
		} catch(InterruptedException e){
			e.printStackTrace();
		}
		
		System.out.println("statut du thread " + t.getName() + " apr�s sleep(1000) = "
					+ t.getState());
		System.out.println("statut du thread " + t2.getName() + " apr�s sleep(1000) = "
				+ t2.getState());
		System.out.println("statut du thread " + t3.getName() + " apr�s sleep(1000) = "
				+ t3.getState());
		System.out.println("============================================");
		/*
		CompteEnBanque cb = new CompteEnBanque();
		Thread th = new Thread(new RunImpl(cb));
		th.start();
		System.out.println("============================================");
		*/
		CompteEnBanque cb = new CompteEnBanque();
		CompteEnBanque cb2 = new CompteEnBanque();
		Thread th = new Thread(new RunImpl(cb, "Cycboy"));
		Thread th2 = new Thread(new RunImpl(cb, "Z�ro"));
		Thread th3 = new Thread(new RunImpl(cb, "Math�o"));
		th.start();
		th2.start();
		th3.start();
		
		System.out.println("============================================");
		ForkJoinPool pool = new ForkJoinPool();
		CalculSuite calcul = new CalculSuite(0, 1002);
		pool.invoke(calcul);
		System.out.println("Le r�sultat final est : " + calcul.getResultat());
	}
}
