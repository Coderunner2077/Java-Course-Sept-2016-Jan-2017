/*
 * 												La g�n�ricit� en Java
 * 
 * Le principe de la g�n�ricit�, concept ajout� au JDK depuis la version 1.5, est de faire des classes qui 
 * n'acceptent qu'un certain type d'objets ou de donn�es de fa�on dynamique.
 * 
 * I./ Principe de base
 * 1.) Principe de base
 * Comment puis-je faire en sorte que ma classe, disons Solo, puisse travailler avec n'importe quel type de donn�es?
 * ==> changer le type des variables d'instances pour des Object.
 * Maintenant, quand j'essaie de faire:
 * Solo val = new Solo(12);
 * int nbre = val.getValeur();
 * ==> une erreur s'affiche car je n'ai pas cast� mon objet en Integer, comme ceci:
 * int nbre = (Integer)val.getValeur();
 * 
 * Pour l'instant, on peut dire que ma classe peut travailler avec tous les types de donn�es, mais les choses se 
 * corsent un peu � l'utilsation.Je serai sur�ment tent� de cr�er des classes par types de donn�es, i.e. SoloInt, 
 * SoloString, etc. Et c'est l� que la g�n�ricit� s'av�re utile, car avec cette derni�re, je pourrai savoir ce que
 * contient mon objet Solo, et n'aurai qu'une seule classe � d�velopper.
 * Dans la classe Mono, T (le type de la variable d'instance) n'est pas encore d�fini, je m'en occuperai � 
 * l'instanciation de la classe. Par contre, une fois instanci� avec un type, l'objet ne pourra travailler
 * qu'avec le type de donn�es que je lui ai sp�cifi�. R�f main 156
 * Attention, il y aura conflit lorsqu'on essaye d'utiliser un autre type de donn�es que celui que l'on a pass�
 * � l'instance lors de sa cr�ation. R�f main 158.
 * Mais cette classe ne fonctionne pas qu'avec des Integer. Je peux lui assigner tous les types que je souhaite.
 * R�f main 159-163
 * Note : j'ai utilis� ici les classes des types primitifs (i.e. Integer au lieu de int).
 * En effet, lorsque je d�clare une variable de type primitif, je peux utiliser aussi ses classes enveloppes (on
 * parle aussi de classe wrapper); elles ajoutent des m�thodes de la classe Object � mes types primitifs ainsi
 * que des m�thodes permettant de caster leurs valeurs, etc. Autre chose : depuis Java 5, est g�r� ce qu'on appelle
 * l'autoboxing, une fonctionnalit� du langage permettant de transformer automatiquement un type primitif en 
 * classe wrapper (on appelle �a boxing) et inversement, i.e. une classe wrapper en type primitif (cela s'appelle
 * l'unboxing). Ces deux fonctionnalit�s forment l'autoboxing. R�f main 165-169
 * 
 * 2.) Plus loin dans la g�n�ricit�
 * 
 * Il faut savoir que la g�n�ricit� peut �tre multiple. J'ai cr�� une classe Mono, mais rien ne m'emp�che de cr�er
 * une classe Duo, qui elle, prend deux param�tres g�n�riques. R�f Duo
 * Le principe fonctionne de la m�me mani�re lorsqu'il y a deux param�tres g�n�riques au lieu d'un (r�f main 176)
 * Attention, en aucun cas je ne peux modifier la d�claration g�n�rique d'un objet une fois cr��, car sinon je 
 * violerai la contrainte �mse lors de la d�claration du type de r�f�rence (i.e. <Qqch>)
 * 
 * II./ G�n�ricit� et collections
 * 
 * 1.) Listes et g�n�ricit�
 * 
 * On peut aussi utiliser la g�n�ricit� sur les objets servant � g�rer les collections. C'est m�me l'un des
 * points les plus utiles de la g�n�ricit�.
 * En effet, lorsqu'on liste le contenu d'un ArrayList par exemple, on n'est jamais s�r � 100 % du type de r�f�rence
 * sur lequel on va tomber (normal, puisqu'un ArrayList accepte tous les types d'objets)... Eh bien, ce calvaire
 * est termin� et le polymorphisme va pouvoir r�appara�tre, plus puissant que jamais !
 * R�f main 184 voir comment utiliser la g�n�ricit� avec les collections
 * Attention, la g�n�ricit� sur les listes est r�gie par les lois vues pr�c�demment : pas de type float (par ex) 
 * dans un ArrayList<String>.
 * 
 * 2.) H�ritage et g�n�ricit�
 * 
 * L� o� les choses sont pernicieuses, c'est quand on emploie des classes usant de la g�n�ricit� avec des objets
 * comprenant la notion d'h�ritage. L'h�ritage dans la g�n�ricit� est l'un des concepts les plus complexes en Java.
 * Pourquoi ? Tout simplement parce qu'il va � l'encontre de ce que j'ai appris jusqu'� pr�sent.
 * Prenons un exemple : j'ai une classe Voiture dont h�rite une classe VoitureSansPermis
 * L� o� �a se complique :
 * 		--> dans main, je fais :
 * 		List<Voiture> listeVoiture = new ArrayList<Voiture>();
 * 		List<VoitureSansPermis> listeVoitureSP = new ArrayList<VoitureSansPermis>();
 * 		listeVoiture = listeVoitureSP; //INTERDIT !!
 * 
 * Si j'ai l'habitude de la covariance des variables, je risque d'�tre d��u car cela n'existe pas avec la 
 * g�n�ricit�. En tout cas, pas sous la m�me forme.
 * Imaginons deux secondes que l'instruction interdite soit permise : dans listeVoiture, j'ai le contenu de la 
 * liste des voitures sans permis, et rien ne m'emp�che d'y ajouter une voiture. L� o� le probl�me prend
 * toute sont envergure, c'est lorsque je voudrais sortir toutes les voitures sans permis de ma variable 
 * listeVoiture. Eh oui ! J'y ai ajout� une voiture ! Lors du balayage de la liste, j'aurai, � un moment,
 * une r�f�rence de type VoitureSansPermis � laquelle je tente d'affecter une r�f�rence de type Voiture. Voil�
 * pourquoi c'est interdit.
 * 3.) Elargir le contenu des collections (pour la lecture) avec le wildcard
 * Une des solutions consiste � utiliser le wildcard : "?". Le fait de d�clarer une collection avec le wildcard,
 * comme ceci :
 * ArrayList<?> list;
 * ...revient � indiquer que notre collection accepte n'importe quel type d'objet. Cependant, y aura une 
 * restriction (voir plus loin).
 * Avec la g�n�ricit�, on peut aller encore plus loin, au lieu de restreindre le contenu de ses listes, on peut
 * aussi l'�largir ! Si je veux par exemple qu'une ArrayList puisse avoir toutes les instances de Voiture et de 
 * ses filles... comment faire?
 * Note : ce qui suit s'applique aussi aux interfaces susceptibles d'�tre impl�ment�es.
 * Liste n'acceptant que des instances de Voiture ou de ses sous-classes :
 * List<? extends Voiture> listeVoitureSP = new ArrayList<VoitureSansPermis>();
 * Une application de ceci consiste � �crire des m�thodes g�n�riques, par exemple une m�thode qui permet
 * de lister toutes les valeurs de l'ArrayList, cit� pr�c�demment. R�f main 230
 * Mais, y a un gros MAIS : d�s que l'on utilise le wildcard, les listes sont v�rouill�es en insertion ==> elles
 * se transforment en collection en lecture seule..
 * 
 * En fait, il faut savoir que c'est � la compilation du programme que Java ne me laisse pas faire : le wildcard
 * signifie "tout objet", et d�s l'utilisation de celui-ci, la JVM verrouillera la compilation du programme afin de 
 * pr�venir les risques d'erreur. Dans mon exemple, il est combin� avec extends, mais cela n'a pas d'incidence
 * directe : c'est le wildcard la cause du verrou (un objet g�n�rique comme tout objet Mono d�clar� Mono<?> mono;
 * sera �galement bloqu� en �criture.
 * Avant que je ne pose la question que je n'allais pas poser, non, d�clarer la m�thode 
 * afficher(List<Voiture> list(){...} ne me permet pas de parcourir des listes de voitures sans permis, m�me si
 * celles-ci h�ritent de la classe Voiture.
 * Les m�thodes d�clar�es avec un type g�n�rique sont verrouill�es afin de n'�tre utilis�es qu'avec ce type bien 
 * pr�cis, toujours pour les m�mes raisons !
 * 
 * 4.) Restreindre les collections accept�es par les m�thodes
 * 
 * La m�thode :
 * static void afficher(List<? extends Voiture> list){
 * 		for(Voiture v : list)
 * 			System.out.println(v.toString());
 * ...autorise n'importe quel objet de type List dont Voiture est la superclasse (y compris Voiture elle-m�me).
 * La signication de l'instruction suivante est donc que la m�thode autorise un objet de type List de n'importe
 * quelle superclasse de la classe Voiture (y compris Voiture elle-m�me) :
 * static void afficher(List<? super Voiture> list){
 * 		for(Object obj : list)
 * 			System.out.println(obj.toString());
 * 
 * L'utilit� du wildcard est surtout de permettre de retrouver le polymorphisme avec les collections. Afin de
 * mieux cerner tout cela, voici un petit exemple de code : R�f Garage. R�f main 224 ==> montre que le polymorphisme
 * est possible avec les collections.
 * 
 * 
 * 
 * 
 * CONCLUSION : La g�n�ricit� est un concept tr�s utile pour d�velopper des objets travaillant avec plusieurs
 * types de donn�es. Je passerai donc moins de temps � d�velopper des classes traitant de fa�on identique
 * des donn�es diff�rentes. La g�n�ricit� permet de r�utiliser sans risque le polymorphisme avec les 
 * collections. Cela conf�re plus de robustesse � mon code. On peut coupler les collections avec la g�n�ricit� !
 * Le wildcard (?) permet d'indiquer que n'importe quel type peut �tre tra�t� et donc accept�. D�s que le
 * wildcard(?) est utilis�, cela revient � rendre ladite collection en lecture seule. On peut �largir le champ
 * d'acceptation d'une collection g�n�rique gr�ce au mot cl� extends. L'instruction <? extends MaClasse> autorise
 * toutes les collections de classes ayant pour supertype MaClasse. L'instruction <? super MaClasse> autorise
 * toutes les collections de classes ayant pour type MaClasse et tous ses supertypes. Pour ce genre de cas,
 * les m�thodes g�n�riques sont particuli�rement adapt�es et permettent d'utiliser le polymorphisme dans toute
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
		listeString.add("Quatri�me mot");
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
		//listeVoitureSansP.add(new VoitureSansPermis()); // liste verrouill�e en insertion
		
		//Par contre, ce type d'utilisation fonctionne � merveille :
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
	//Avec cette m�thode, on accepte aussi bien les collections de Voiture que les collections de VoitureSansPermis
	static void afficher(List<? extends Voiture> list){
		for(Voiture v : list)
			System.out.println(v.toString());
	}
	//Avec cette m�thode, on accepte aussi bien les collections de Voiture que les les collections d'Object : la 
	//superclasse de toutes les classes
	static void afficherSuper(List<? super Voiture> list){
		for(Object obj : list)
			System.out.println(obj.toString());
	}
	
}
