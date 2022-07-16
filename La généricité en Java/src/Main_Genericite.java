/*
 * 												La généricité en Java
 * 
 * Le principe de la généricité, concept ajouté au JDK depuis la version 1.5, est de faire des classes qui 
 * n'acceptent qu'un certain type d'objets ou de données de façon dynamique.
 * 
 * I./ Principe de base
 * 1.) Principe de base
 * Comment puis-je faire en sorte que ma classe, disons Solo, puisse travailler avec n'importe quel type de données?
 * ==> changer le type des variables d'instances pour des Object.
 * Maintenant, quand j'essaie de faire:
 * Solo val = new Solo(12);
 * int nbre = val.getValeur();
 * ==> une erreur s'affiche car je n'ai pas casté mon objet en Integer, comme ceci:
 * int nbre = (Integer)val.getValeur();
 * 
 * Pour l'instant, on peut dire que ma classe peut travailler avec tous les types de données, mais les choses se 
 * corsent un peu à l'utilsation.Je serai surêment tenté de créer des classes par types de données, i.e. SoloInt, 
 * SoloString, etc. Et c'est là que la généricité s'avère utile, car avec cette dernière, je pourrai savoir ce que
 * contient mon objet Solo, et n'aurai qu'une seule classe à développer.
 * Dans la classe Mono, T (le type de la variable d'instance) n'est pas encore défini, je m'en occuperai à 
 * l'instanciation de la classe. Par contre, une fois instancié avec un type, l'objet ne pourra travailler
 * qu'avec le type de données que je lui ai spécifié. Réf main 156
 * Attention, il y aura conflit lorsqu'on essaye d'utiliser un autre type de données que celui que l'on a passé
 * à l'instance lors de sa création. Réf main 158.
 * Mais cette classe ne fonctionne pas qu'avec des Integer. Je peux lui assigner tous les types que je souhaite.
 * Réf main 159-163
 * Note : j'ai utilisé ici les classes des types primitifs (i.e. Integer au lieu de int).
 * En effet, lorsque je déclare une variable de type primitif, je peux utiliser aussi ses classes enveloppes (on
 * parle aussi de classe wrapper); elles ajoutent des méthodes de la classe Object à mes types primitifs ainsi
 * que des méthodes permettant de caster leurs valeurs, etc. Autre chose : depuis Java 5, est géré ce qu'on appelle
 * l'autoboxing, une fonctionnalité du langage permettant de transformer automatiquement un type primitif en 
 * classe wrapper (on appelle ça boxing) et inversement, i.e. une classe wrapper en type primitif (cela s'appelle
 * l'unboxing). Ces deux fonctionnalités forment l'autoboxing. Réf main 165-169
 * 
 * 2.) Plus loin dans la généricité
 * 
 * Il faut savoir que la généricité peut être multiple. J'ai créé une classe Mono, mais rien ne m'empêche de créer
 * une classe Duo, qui elle, prend deux paramètres génériques. Réf Duo
 * Le principe fonctionne de la même manière lorsqu'il y a deux paramètres génériques au lieu d'un (réf main 176)
 * Attention, en aucun cas je ne peux modifier la déclaration générique d'un objet une fois créé, car sinon je 
 * violerai la contrainte émse lors de la déclaration du type de référence (i.e. <Qqch>)
 * 
 * II./ Généricité et collections
 * 
 * 1.) Listes et généricité
 * 
 * On peut aussi utiliser la généricité sur les objets servant à gérer les collections. C'est même l'un des
 * points les plus utiles de la généricité.
 * En effet, lorsqu'on liste le contenu d'un ArrayList par exemple, on n'est jamais sûr à 100 % du type de référence
 * sur lequel on va tomber (normal, puisqu'un ArrayList accepte tous les types d'objets)... Eh bien, ce calvaire
 * est terminé et le polymorphisme va pouvoir réapparaître, plus puissant que jamais !
 * Réf main 184 voir comment utiliser la généricité avec les collections
 * Attention, la généricité sur les listes est régie par les lois vues précédemment : pas de type float (par ex) 
 * dans un ArrayList<String>.
 * 
 * 2.) Héritage et généricité
 * 
 * Là où les choses sont pernicieuses, c'est quand on emploie des classes usant de la généricité avec des objets
 * comprenant la notion d'héritage. L'héritage dans la généricité est l'un des concepts les plus complexes en Java.
 * Pourquoi ? Tout simplement parce qu'il va à l'encontre de ce que j'ai appris jusqu'à présent.
 * Prenons un exemple : j'ai une classe Voiture dont hérite une classe VoitureSansPermis
 * Là où ça se complique :
 * 		--> dans main, je fais :
 * 		List<Voiture> listeVoiture = new ArrayList<Voiture>();
 * 		List<VoitureSansPermis> listeVoitureSP = new ArrayList<VoitureSansPermis>();
 * 		listeVoiture = listeVoitureSP; //INTERDIT !!
 * 
 * Si j'ai l'habitude de la covariance des variables, je risque d'être déçu car cela n'existe pas avec la 
 * généricité. En tout cas, pas sous la même forme.
 * Imaginons deux secondes que l'instruction interdite soit permise : dans listeVoiture, j'ai le contenu de la 
 * liste des voitures sans permis, et rien ne m'empêche d'y ajouter une voiture. Là où le problème prend
 * toute sont envergure, c'est lorsque je voudrais sortir toutes les voitures sans permis de ma variable 
 * listeVoiture. Eh oui ! J'y ai ajouté une voiture ! Lors du balayage de la liste, j'aurai, à un moment,
 * une référence de type VoitureSansPermis à laquelle je tente d'affecter une référence de type Voiture. Voilà
 * pourquoi c'est interdit.
 * 3.) Elargir le contenu des collections (pour la lecture) avec le wildcard
 * Une des solutions consiste à utiliser le wildcard : "?". Le fait de déclarer une collection avec le wildcard,
 * comme ceci :
 * ArrayList<?> list;
 * ...revient à indiquer que notre collection accepte n'importe quel type d'objet. Cependant, y aura une 
 * restriction (voir plus loin).
 * Avec la généricité, on peut aller encore plus loin, au lieu de restreindre le contenu de ses listes, on peut
 * aussi l'élargir ! Si je veux par exemple qu'une ArrayList puisse avoir toutes les instances de Voiture et de 
 * ses filles... comment faire?
 * Note : ce qui suit s'applique aussi aux interfaces susceptibles d'être implémentées.
 * Liste n'acceptant que des instances de Voiture ou de ses sous-classes :
 * List<? extends Voiture> listeVoitureSP = new ArrayList<VoitureSansPermis>();
 * Une application de ceci consiste à écrire des méthodes génériques, par exemple une méthode qui permet
 * de lister toutes les valeurs de l'ArrayList, cité précédemment. Réf main 230
 * Mais, y a un gros MAIS : dès que l'on utilise le wildcard, les listes sont vérouillées en insertion ==> elles
 * se transforment en collection en lecture seule..
 * 
 * En fait, il faut savoir que c'est à la compilation du programme que Java ne me laisse pas faire : le wildcard
 * signifie "tout objet", et dès l'utilisation de celui-ci, la JVM verrouillera la compilation du programme afin de 
 * prévenir les risques d'erreur. Dans mon exemple, il est combiné avec extends, mais cela n'a pas d'incidence
 * directe : c'est le wildcard la cause du verrou (un objet générique comme tout objet Mono déclaré Mono<?> mono;
 * sera également bloqué en écriture.
 * Avant que je ne pose la question que je n'allais pas poser, non, déclarer la méthode 
 * afficher(List<Voiture> list(){...} ne me permet pas de parcourir des listes de voitures sans permis, même si
 * celles-ci héritent de la classe Voiture.
 * Les méthodes déclarées avec un type générique sont verrouillées afin de n'être utilisées qu'avec ce type bien 
 * précis, toujours pour les mêmes raisons !
 * 
 * 4.) Restreindre les collections acceptées par les méthodes
 * 
 * La méthode :
 * static void afficher(List<? extends Voiture> list){
 * 		for(Voiture v : list)
 * 			System.out.println(v.toString());
 * ...autorise n'importe quel objet de type List dont Voiture est la superclasse (y compris Voiture elle-même).
 * La signication de l'instruction suivante est donc que la méthode autorise un objet de type List de n'importe
 * quelle superclasse de la classe Voiture (y compris Voiture elle-même) :
 * static void afficher(List<? super Voiture> list){
 * 		for(Object obj : list)
 * 			System.out.println(obj.toString());
 * 
 * L'utilité du wildcard est surtout de permettre de retrouver le polymorphisme avec les collections. Afin de
 * mieux cerner tout cela, voici un petit exemple de code : Réf Garage. Réf main 224 ==> montre que le polymorphisme
 * est possible avec les collections.
 * 
 * 
 * 
 * 
 * CONCLUSION : La généricité est un concept très utile pour développer des objets travaillant avec plusieurs
 * types de données. Je passerai donc moins de temps à développer des classes traitant de façon identique
 * des données différentes. La généricité permet de réutiliser sans risque le polymorphisme avec les 
 * collections. Cela confère plus de robustesse à mon code. On peut coupler les collections avec la généricité !
 * Le wildcard (?) permet d'indiquer que n'importe quel type peut être traîté et donc accepté. Dès que le
 * wildcard(?) est utilisé, cela revient à rendre ladite collection en lecture seule. On peut élargir le champ
 * d'acceptation d'une collection générique grâce au mot clé extends. L'instruction <? extends MaClasse> autorise
 * toutes les collections de classes ayant pour supertype MaClasse. L'instruction <? super MaClasse> autorise
 * toutes les collections de classes ayant pour type MaClasse et tous ses supertypes. Pour ce genre de cas,
 * les méthodes génériques sont particulièrement adaptées et permettent d'utiliser le polymorphisme dans toute
 * sa splendeur
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */
import java.util.List;
import java.util.ArrayList;
import java.util.ListIterator;
public class Main_Genericite {

	public static void main(String[] args) {
		Solo val = new Solo(12);
		System.out.println(val.getValeur());
		int nbre = (Integer)val.getValeur();
		System.out.println(nbre);
		Mono<Integer> v = new Mono<Integer>(12);
		int nb = v.getValeur();
		Mono<Integer> v2 = new Mono<Integer>(51);
		//Mono<Integer> conflit = new Mono<Integer>(51.04f);// CONFLIT
		Mono<String> str = new Mono<String>("toto");
		Mono<Float> fl = new Mono<Float>(51.04f);
		Mono<Double> sh = new Mono<Double>(432.43233434d);
		Mono<Long> lg = new Mono<Long>(43434545345L);
		
		//boxing
		int nb2 = new Integer(54);
		double doub = new Double(4543.3434343434d);
		//unboxing (??)
		Double d = 45.434d;
		Character c = 'C';
		
		ArrayList al = new ArrayList();
		// Avant Java 5, il fallait faire al.add(new Integer(12))
		// Depuis Java 5, il suffit de faire :
		al.add(12);
		
		Duo<String, Boolean> dual = new Duo<String, Boolean>("toto", true);
		System.out.println("Valeurs de l'objet dual : valeur1 = "+dual.getValeur1()+", valeur2 = "
				+dual.getValeur2());
		Duo<Double, Character> dual2 = new Duo<Double, Character>(12.039d, 'Z');
		System.out.println("Valeurs de l'objet dual2 : valeur1 = "+ dual2.getValeur1() +", valeur2 = "
				+dual2.getValeur2());
		System.out.println("-------------------------------------------------------------");
		System.out.println("Liste de String");
		List<String> listeString = new ArrayList<String>();
		listeString.add("Premier mot");
		listeString.add("Second mot");
		listeString.add("Troisieme mot");
		listeString.add("Quatrième mot");
		// listeString.add(434); ==> erreur
		for(String stri : listeString)
			System.out.println(stri);
		
		System.out.println("--------------------------------------------------------------");
		System.out.println("Liste de float");
		List<Float> listeFloat = new ArrayList<Float>();
		listeFloat.add(14.34f);
		listeFloat.add(22.32f);
		listeFloat.add(33.43f);
		listeFloat.add(44.3423f);
		for(float f : listeFloat)
			System.out.println(f);
		
		List<? extends Voiture> listeVoitureSansP = new ArrayList<VoitureSansPermis>();
		//listeVoitureSansP.add(new VoitureSansPermis()); // liste verrouillée en insertion
		
		//Par contre, ce type d'utilisation fonctionne à merveille :
		//Liste de voiture
		List<Voiture> listeVoiture = new ArrayList<Voiture>();
		listeVoiture.add(new Voiture());
		listeVoiture.add(new Voiture());
		VoitureSansPermis vsp = new VoitureSansPermis();
		listeVoiture.add(vsp);
		//Liste de voiture sans permis
		List<VoitureSansPermis> listeVoitureSP = new ArrayList<VoitureSansPermis>();
		listeVoitureSP.add(new VoitureSansPermis());
		listeVoitureSP.add(new VoitureSansPermis());
		//listeVoitureSP.add(new Voiture()); ==> ERREUR !
		afficher(listeVoiture);
		System.out.println("Ok");
		afficher(listeVoitureSP);
		//Liste d'Object
		List<Object> listeObjet = new ArrayList<Object>();
		listeObjet.add(new Object());
		listeObjet.add(new Object());
		afficherSuper(listeObjet);
		
		//un petit test rapide
		Garage garage = new Garage();
		garage.add(listeVoiture);
		System.out.println("---------------------------------------");
		garage.add(listeVoitureSP);

		List<? super VoitureSansPermis> listeBis = new ArrayList<Voiture>(); // Ok
		List<? extends Voiture> listeBis2 = new ArrayList<VoitureSansPermis>(); // Ok
		// listeBis2.add(new Voiture()); // Erreur : ? ==>  lecture seule
	}
	//Avec cette méthode, on accepte aussi bien les collections de Voiture que les collections de VoitureSansPermis
	static void afficher(List<? extends Voiture> list){
		for(Voiture v : list)
			System.out.println(v.toString());
	}
	//Avec cette méthode, on accepte aussi bien les collections de Voiture que les les collections d'Object : la 
	//superclasse de toutes les classes
	static void afficherSuper(List<? super Voiture> list){
		for(Object obj : list)
			System.out.println(obj.toString());
	}
	
}
