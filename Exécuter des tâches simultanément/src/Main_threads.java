import java.util.concurrent.ForkJoinPool;

import fr.runnable.CompteEnBanque;
import fr.runnable.RunImpl;

/*
 * 								Exécuter ds tâches simultanément
 * Les threads sont des fils d'exécution d'un programme. Lorsqu'on en crée plusieurs, on
 * peut exécuter des tâches simultanément.
 * Dans ce chapitre, je verrai comment créer une (ou plusieurs) nouvelle(s) pile(s) de
 * fonctions grâce à ces fameux threads. Il existe une classe Thread dans Java permettant
 * leur gestion. On verra qu'il existe 2 façons de créer un nouveau thread.
 * 
 * I./ Une classe héritée de Thread
 * 
 * Lorsque j'exécute un programme, un thread est lancé ! Le thread correspond à la pile et
 * chaque nouveau thread créé génère une pile d'exécution. 
 * Réf main : Thread.currentThread().getName() renvoie le nom du thread principal de mon
 * application (ie main ici).
 * On peut voir un thread comme une machine bien huilée capable d'effectuer les tâches que
 * l'on lui spécifie. Une fois instancié, un thread attend son lancement. Dès que c'est 
 * fait, il invoque sa méthode run() qui va lui permettre de connaître les tâches qu'il
 * a à effectuer.
 * Pour créer un nouveau thread, il existe deux manières de faire :
 * 		-	créer une classe héritant de Thread
 * 		-	créer une implémentation de la classe Runnable et instancier un objet Thread
 * 			avec l'implémentation de cette interface
 * Voyons d'abord la 1re solution. Tout ce qu'il y a à faire, c'est redéfinir la méthode
 * run() de mon objet afin qu'il sache ce qu'il doit faire. Et aussi, leur donner des noms
 * pour pouvoir les différencier.
 * Je crée donc une classe gérant tout cela qui contient un constructeur comprenant
 * un String en paramère pour spécifier le nom du thread. Cette classe doit également
 * comprendre une méthode getName() afin de retourner ce nom. Aucun import nécessaire.
 * Réf main => je peux voir que l'ordre d'exécution de deux différents threads est souvent
 * aléatoire, car Java utilise un ordonnanceur. En effet, si j'utilise
 * plusieurs threads dans une même application, ceux-ci ne s'exécutent pas toujours
 * en même temps. En fait, l'ordonnanceur gère les threads de façon aléatoire : il va en
 * faire tourner un pendant un certain temps, puis un autre, puis revenir au premier, etc.,
 * jusqu'à ce qu'ils soient terminés. Lorsque l'ordonnanceur passe d'un thread à un autre,
 * le thread interrompu est mis en sommeil tandis que l'autre est en éveil.
 * Note : avec les processeurs multi-coeurs, il est possible d'exécuter plusieurs tâches
 * exactement en même temps.
 * 
 * Un thread peut présenter plusieurs états:
 * 		-	NEW : lors de sa création
 * 		-	RUNNABLE : lorsqu'on invoque la méthode start(), le thread est prêt à travailler
 * 		-	TERMINATED : lorsque le thread a effectué toutes ses tâches; on dit aussi qu'il
 * 			est "mort". On ne peut alors plus le relancer par la méthode start()
 * 		-	TIMED_WAITING : lorsque le thread est en pause (quand j'utilise sleep() par ex)
 * 		-	WAITING : lorsque le thread est en attente indéfinie
 * 		-	BLOCKED : lorsque l'ordonnanceur place un thread en sommeil pour en utiliser
 * 			un autre, il lui impose cet état.
 * 
 * Un thread est considéré comme terminé lorsque la méthode run() est ôtée de sa pile
 * d'exécution. En effet, une nouvelle pile d'exécution contient à sa base la méthode run()
 * de mon thread. Une fois celle-ci dépilée, ma nouvelle pile est détruite.
 * En fait, le thread principal crée un second thread qui se lance et construit une pile
 * dont la base est la méthode run() de ce dernier; et cette pile appelle une méthode, 
 * l'empile, effectue toutes les opérations demandées, et une fois qu'elle a terminé, elle 
 * dépile la méthode run() invoquée. La méthode run() prend fin, la pile est alors 
 * détruite. 
 * Je vais modifier ma classe TestThread afin d'afficher les états de mes threads que je
 * peux récupérer grâce à la méthode getState().
 * Dans ma classe TestThread, j'ai ajouté qq instructions d'affichage afin de visualiser
 * l'état courant de mes objets. Mais j'ai aussi ajouté un constructeur supplémentaire
 * prenant un Thread en paramètre afin d'obtenir l'état de mon 1er thread lors de
 * l'exécution du second.
 * Si j'avais un processus à un seul coeur, le 1er aurait été dans l'état BLOCKED lorsque
 * le 2nd est en cours de traitement : les threads ne s'exécutant pas en même temps si
 * le processeur n'est doté que d'un seul coeur.
 * Je peux aussi voir que les opérations effectuées par mes threads sont en fait codées
 * dans la méthode run().
 * Faire hériter un objet de Thread permet de créer un nouveau thread très facilement. Je
 * peux cependant procéder différemment : redéfinir uniquemenet ce que doit effectuer le
 * nouveau thread grâce à l'interface Runnable. Dans ce cas, je redéfinis uniquement ce
 * que la machine doit faire, et non la machine tout entière !
 * 
 * II./ Utiliser l'interface Runnable
 * 
 * Ne redéfinir que les tâches que le nouveau thread doit effectuer comprend un autre
 * avantage : la classe dont je dispose n'hérite d'aucune autre ! En effet, dans mon
 * test précédent, la classe TestThread ne pourra pas hériter d'une autre classe, 
 * puisqu'elle hérite déjà de Thread, tandis qu'avec une implémentation de Runnable, rien
 * n'empêche ma classe d'hériter de JFrame, par exemple...
 * Lors de l'implémentation de l'interface Runnable, il n'y a que la méthode run() à 
 * redéfinir.
 * Illustration :
 * Réf diagramme_runnable.png => j'ai créé un objet CompteEnBanque contenant une somme
 * d'argent par défaut (disons 100), une méthode pour retirer de l'argent(retraitArgent())
 * et une méthode retournant le solde (getSolde()). Cependant, avant de retirer de 
 * l'argent, je vérifierai que je ne suis pas à découvert. Mon thread va effectuer 
 * autant d'opérations que je le souhaite.
 * Je résume :
 * 		-	mon application peut contenir un ou plusieurs objets Thread
 * 		-	ceux-ci ne peuvent être constitués que d'un objet de type Runnable
 * 		-	dans mon cas, les objets Thread contiendront une implémentation de 
 * 			Runnable : RunImpl
 * 		-	cette implémentation possède un objet CompteEnBanque
 * 
 * Réf package fr.runnable.
 * Rien d'extraordinaire jusqu'ici. J'ajoute maintenant un nom à mon implémentation et 
 * je crée un deuxième thread utilisant un 2e compte. Il fo penser à modifier l'implémen -
 * tation afin que je puisse connaître le thread qui travaille.
 * Lorsque j'utilise deux instances distinctes de RunImpl utilisant elles-mêmes deux 
 * instances distinctes de CompteEnBanque ==> il n'y a rien de perturbant, tout va
 * comme sur des roulettes.
 * Mais que se passerait-il si j'utilisais la même instance de CompteEnBanque pour deux
 * threads différents ? ==> rien de perturbant puisque j'ai un processeur double-coeur 
 * wesch.
 * Sinon, mais que ce se passerait-il si j'utilisais la même instance de CompteEnBanque
 * pour plus de deux threads différents ? ==> on peut voir des incohérences monumentales !
 * On pourrait penser que le compte aurait été débité par pas de deux jusqu'à la fin sans
 * obtenir d'aberrations de ce genre (réf main), puisque l'on utilise le même objet. Eh
 * bien, non ! Pourquoi ? Tout simplement parce que l'ordonnanceur de Java place les 
 * threads en sommeil quand il le désire, et lorsque le thread qui était en sommeil se
 * réveille, il reprend sont travail là où il l'avait laissé !
 * 
 * Voyons maintenant comment résoudre ce problème.
 * 
 *  III./ Synchroniser ses threads
 *  
 *  Tout est dans le titre. En fait, ce qu'il faut faire, c'est indiquer à la JVM  qu'un
 *  thread est en train d'utiliser des données qu'un autre thread est susceptible 
 *  d'altérer.
 *  Ainsi, lorsque l'ordonnanceur met en sommeil un thread qui traitait des données
 *  utilisables par un autre thread, ce premier thread garde la priorité sur les données
 *  et tant qu'il n'a pas terminé son travail, les autres threads n'ont pas la 
 *  possibilité d'y toucher (même pas vrai !).
 *  Cela s'appelle synchroniser les threads. Cette opération est très délicate et demande
 *  bcp de compétences en programmation... Voici à quoi ressemble ma méthode 
 *  retraitArgent() synchronisée (dans la classe CompteEnBanque) :
 *  public synchronized void retraitArgent(int retrait){
 *  	solde = solde - retrait;
 *  	System.out.println("Le nouveau solde est : " + solde);
 *  }
 *  En effet, il suffit d'ajouter dans la déclaration de la méthode le mot clé 
 *  synchronized, grâce auquel la méthode est inaccessible à un thread si elle est déjà
 *  utilisée par un autre thread. Ainsi, les threads cherchant à utiliser des méthodes
 *  déjà prises en charge par un autre thread sont placés dans une "liste d'attente".
 *  
 *  Dans un contexte informatique, il peut être pratique et sécurisé d'utiliser des
 *  threads et des méthodes synchronisées lors d'accès à des services distants tels qu'un
 *  serveur d'applications ou un SGBD (Système de Gestion de Base de Données).
 *  
 *  ATTENTION ! CECI N'EST PAS CONFORME A MON OBSERVATION !!
 *  EN EFFET, LORSQUE, DISONS 3 THREADS, UTILISENT LA MÊME METHODE SYNCHRONISEE D'UNE MÊME
 *  INSTANCE DE CompteEnBanque, CETTE METHODE EST ACCESSIBLE A TOUS LES TROIS, MAIS LOIN
 *  D'AVOIR DES ABERRATIONS QUELCONQUES, LES 3 THREADS UTILISENT LES DONNEES DE LA
 *  MÊME INSTANCE DE FACON SYNCHRONISEE. C'EST COMME SI CHAQUE THREAD SAVAIT EN QUEL
 *  ETAT LE THREAD PRECEDENT A LAISSE LES DONNEES DE L'INSTANCE DU CompteEnBanque.
 *  
 *  SpinOff mon animation => je retourne à mon animation qui n'attend qu'un
 *  petit thread pour fonctionner correctement.
 *  
 *  IV./ Contrôler son animation
 *  
 *  Il me suffit de créer un nouveau thread lorsqu'on clique sur le bouton Go en lui
 *  passant une implémentation de Runnable en paramètre qui, elle, va appeler la méthode
 *  go() (sans oublier de remettre le booléen de contrôle à true). 
 *  Réf Animation => Fenetre
 *  
 *  V./ Depuis Java 7, le pattern Fork/Join
 *  
 *  La version 7 met à disposition des développeurs plusieurs classes permettant de mettre
 *  en application ce qu'on appelle "le pattern Fork/Join". Ce dernier n'est rien de plus
 *  que la mise en application d'un viel adage : "diviser pour mieux régner". Dans certains
 *  cas, il serait bon de pouvoir découper une tâche en plusieurs sous-tâches, faire en
 *  sorte que ces sous-tâches s'exécutent en parallèle et pouvoir récupérer le résultat
 *  de tout ceci une fois que tout est terminé. C'est exactement ce qu'il est possible
 *  de faire avec ces nouvelles classes.
 *  Avant de commencer, précisons qu'il y a un certain nombre de préréquis à cela :
 *  	-	la machine qui exécutera la tâche devra posséder un processeur à plusieurs
 *  		coeurs (2, 4 ou plus)
 *  	-	la tâche doit pouvoir être découpée en plusieurs sous-tâches
 *  	-	s'assurer qu'il y a un réel gain de performance ! (Dans certains cas, découper
 *  		une tâche rend le traitement plus long)
 *  
 *  En guise d'exemple, je vais faire une recherche de fichiers (simplifiée au maximum
 *  pour ne pas surcharger le code). Réf package fr.folder.scanner pour voir les classes
 *  utilisées, pour le moment sans la gestion Fork/Join.
 *  Réf Main_Folder => résultat du scan = 106 secondes
 *  
 *  Il est possible de découper le scan de chaque dossier dans une sous-tâche, et c'est
 *  exactement ce que je vais faire. Pour ce faire, je dois faire hériter ma classe
 *  FolderScanner d'une des classes permettant ce découpage. La plateforme Java 7 me met
 *  à disposition deux classes qui héritent de la classe abstraite ForkJoinTask<V> :
 *  	-	RecursiveAction : classe permettant de découper une tâche ne renvoyant aucune
 *  		valeur particulière. Elle hérite de ForkJoinTask<Void>
 *  	-	RecursiveTask<V> : similaire à la classe précédente mais retournant une valeur,
 *  		de type <V>, en fin de traitement.
 *  
 * C'est cette dernière que je vais utiliser pour pouvoir retourner le nombre de fichiers
 * trouvés.
 * Je vais devoir utiliser, en plus du découpage, un objet dont le rôle sera de superviser
 * l'exécution des tâches et des sous-tâches afin de pouvoir fusionner les threads en 
 * fin de traitement : ForkJoinPool.
 * 
 * Voici comment ça fonctionne :
 * Les objets qui permettent le découpage en sous-tâches fournissent trois méthodes
 * qui permettent cette gestion :
 * 	-	compute() : méthode abstraite à redéfinir dans l'objet héritant afin de
 * 			définir le traitement à effectuer
 * 	-	fork() : méthode qui crée un nouveau thread dans le pool de thread (ForkJoinPool)
 * 	-	join() : méthode qui permet de récupérer le résultat de la méthode compute()
 * 
 * Ces classes nécessitent que je redéfinisse la méthode compute() afin de définir ce 
 * qu'il y a à faire. 
 * Réf schema_forkjointask.png
 * Concrètement, avec mon exemple, voici ce qu'il va se passer :
 * 	-	je vais lancer le scan de mon dossier
 * 	-	mon objet qui sert à scanner le contenu va vérifier le contenu pour voir s'il n'y
 * 			a pas de sous-dossiers
 * 	-	pour chaque sous-dossier, je vais créer une nouvelle tâche et la lancer
 * 	-	je vais compter le nombre de fichiers qui correspond à mes critères dans le dossier
 * 			en cours de scan
 * 	-	je vais récupérer le nombre de fichiers trouvés par les exécutions en tâche de fond
 * 	-	je vais retourner le résultat final
 * 
 * Réf package fr.fork.join
 * ==> le scan en mode mono thread du dossier "C:\\Users\\Harry" a pris 106 secondes, alors
 * qu'en mode Fork/Join, ça n'a pris que 7 secondes
 * 
 * Cependant, je constate que l'utilisation de ce mode est très gourmand en ressources
 * processeurs. Il est donc à utiliser avec parcimonie.
 * Dans cet exemple, j'ai créé dynamiquement autant de threads que nécessaires pour
 * traiter mes tâches. Je n'aurai peut-être pas besoin de faire ceci pour des problèmes
 * où seulement 2 ou 3 sous-tâches suffisent, surtout si je le sais d'avance. L'idée
 * maîtresse revient à définir un seuil à partir duquel le traitement se fera en mode
 * Fork/Join, sinon, il se fera dans un seul thread (Attention, il se peut que le mode
 * Fortk/Join soit plus lent et consommateur qu'en mode normal). Voici comment procéder
 * dans ce genre de cas : Réf CalculSuite 
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
		
		System.out.println("statut du thread " + t.getName() + " après sleep(1000) = "
					+ t.getState());
		System.out.println("statut du thread " + t2.getName() + " après sleep(1000) = "
				+ t2.getState());
		System.out.println("statut du thread " + t3.getName() + " après sleep(1000) = "
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
		Thread th2 = new Thread(new RunImpl(cb, "Zéro"));
		Thread th3 = new Thread(new RunImpl(cb, "Mathéo"));
		th.start();
		th2.start();
		th3.start();
		
		System.out.println("============================================");
		ForkJoinPool pool = new ForkJoinPool();
		CalculSuite calcul = new CalculSuite(0, 1002);
		pool.invoke(calcul);
		System.out.println("Le résultat final est : " + calcul.getResultat());
	}
}
