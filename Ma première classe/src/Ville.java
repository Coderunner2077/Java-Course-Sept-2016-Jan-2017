
public class Ville {
/*
 * INTRO:
 * Ici, ma classe Ville est précédée du mot public. Ce mot clé correspond à la portée de la classe.
 * En programmation, la portée détermine QUI peut faire appel à une classe, une méthode ou une variable. La portée
 * public signifie que tout le monde peut faire appel à l'élément. Ici, dans le cas qui nous intéresse, il s'agit 
 * d'une méthode. Une méthode marquée comme public peut être appelée depuis n'importe quel endroit du prog.
 * Je vais ici utiliser une autre portée : private.
 * private : les méthodes déclarées private correspondent souvent à des mécanismes internes à une classe que les 
 * développeurs souhaitent "cacher" ou simplement ne pas rendre accessible depuis l'extérieur de la classe...
 * Note : il en va de même pour les variables. Je peux protéger ces dernières grâce au mot clé private, en les 
 * rendant accessibles seulement à l'intérieur de la classe où elles sont créées.
 */
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// I./ LES CONSTRUCTEURS
//Avec des variables d'instances, je vais définir les données que je vais attribuer à mon objet Ville.
	private String nomVille;
	private String nomPays;
	private int nbHabitants;
	private char categorie;
/*
 * Contrairement aux classes, les variables d'instances présentes dans une classe sont public si je ne leur spécifie
 * pas de portée. Il y a trois grands types de variables dans une classe objet:
 * 	-	les variables d'instances : définissent les caractéristiques de mon objet
 * 	-	les variables de classe  : celles-ci sont communes à toutes les instances de ma classe
 *  -	les variables locales : celles que j'utiliserai pour travailler dans mon objet
 *  Pour créer mon projet, je vais devoir utiliser ce qu'on appelle des constructeurs. 
 *  Un constructeur est une méthode d'instance qui va se charger de créer un objet et, le cas échéant, d'initialiser
 *  ses variables de classe. Cette méthode a pour rôle de signaler à la JVM qu'il faut réserver de la mémoire
 *  pour mon futur objet, et donc, par extension, d'en réserver pour toutes ses variables.
 *  Mon premier constructeur sera ce qu'on appelle un constructeur par défaut, i.e. il ne prendra aucun paramètre,
 *  mais permettra tout de même d'instancier un objet (allocation + initialisation).
 *  */
	//constructeur par défaut :
	public Ville(){
		System.out.println("Création d'une ville par défaut !");
		nomVille = "inconnu";
		nomPays = "inconnu";
		nbHabitants = 0;
		this.setCategorie();
		nbInstances++;
		nbInstancesBis++;
	}
/*REMARQUE : le constructeur est une méthode qui n'a aucun type de retour (void, double, ...) et qui porte le même 
 * nom que ma classe. Ceci est une règle immuable : le(s) constructeur(s) doit porter le même nom que la classe !
 * Son corrolaire est qu'un objet peut avoir plusieurs constructeurs. Il s'agit de la même méthode, mais surchargée.
 * Je peux d'ores et déjà créer une instance de Ville. Attention à ne pas oublier qu'une instance d'objet se fait
 * grâce au mot clé new.
 */
	
	// constructeur avec des paramètres
	public Ville(String pNom,  int pNbre, String pPays)
	{
		System.out.println("Création d'une ville spécifique !");
		nomVille = pNom;
		nomPays = pPays;
		nbHabitants = pNbre;
		this.setCategorie();
		nbInstances++;
		nbInstancesBis++;
	}
	/*PAR CONTRE, mon objet présente un gros défaut, les variables d'instance qui le caractérisent sont accessibles
	dans la classe contenant mon main (je ne les avais pas déclarées private au début). Cela impique que je peux
	directement modifier les attributs de la classe. C'est très risqué, car un code extérieur pourrait faire 
	n'importe quoi avec mes objets, ou bien je pourrais	vouloir faire quelque chose à chaque fois qu'une valeur 
	change. Sans protéger mes données, cela serait impossible à réaliser.
	C'est pour cela qu'il fo protéger les variables d'instance en les déclarant private.
	Désormais, ces attributs ne seront plus accéssibles en dehors de la classe où ils sont déclarés. Mais comment
	pourrait-on accéder quand même à ces données nom de Dieu?	
	*/
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// II./ LES ACCESSEURS ET LES MUTATEURS
	/*
	 * Un accesseur est une méthode qui permet d'accéder aux variables de mes objets en lecture. 
	 * Un mutateur permet d'en faire de même en écriture. Grâce aux accesseurs, qu'on appelle aussi getters, 
	 * je peux afficher les variables de mes objets, et grâce aux mutateurs (setters), je peux les modifier.
	 * Les voici :
	 */
	//ACCESSEURS
	public String getNom()
	{
		return nomVille;
	}
	
	public String getNomPays()
	{
		return nomPays;
	}
	
	public int getNbHabitants()
	{
		return nbHabitants;
	}
	
	public char getCategorie()
	{
		return categorie;
	}
	
	//MUTATEURS
	public void setNom(String pNom)
	{
		nomVille = pNom;
	}
	
	public void setNomPays(String pNomPays)
	{
		nomPays = pNomPays;
	}

	public void setNbHabitants(int pNbre)
	{
		nbHabitants = pNbre;
		this.setCategorie();
	}
	/*
	 * Mes assesseurs sont bien des méthodes, et elles sont public pour que je puisse y accéder depuis une autre
	 *  classe que celle-ci : depuis le main par exemple. Les accesseurs sont du même type que la variable qu'ils
	 *  doivent retourner. Les mutateurs, par contre, sont de type void.
	 * Note: généralement, quand on parle d'accesseurs, ce terme inclut aussi les mutateurs. Autre chose, le fait 
	 * de mettre get et set devant est une convention de nommage (d'où ==> "Getters" & "Setters")
	 * Aller dans main, pour les voir à l'oeuvre (réf main: 21-41)
	 * Ca fait beaucoup de répétition pour faire afficher les attributs pour chaque instance. On pourrait faire 
	 * plus simple : en créant des méthodes d'instance.
	 */
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// III./ LES METHODES D'INSTANCE
	/*
	 * ATTENTION, il fo bien distinguer les différents types de méthodes dans un objet :
	 * 		-	les constructeurs : méthodes servant à créer des objets;
	 * 		-	les accesseurs : méthodes servant à accéder aux données des objets;
	 * 		-	les méthodes d'instance : méthodes servant à la gestion des objets
	 * 
	 * Je peux créer les méthodes d'instance suivantes pour mon objet Ville:
	 * 		-	faire un système de catégorisation de villes en fct de leur population. Ceci est déterminé à la 
	 * construction ou à la redéfinition du nb d'habitants: j'ajoute donc une variable d'instance de type char.
	 * 		-	faire une méthode de description de mon objet Ville.
	 * 		-	une méthode de comparaison de la population de deux villes
	 * 
	 * Note : il vaut mieux que la classe Ville gère la façon de déterminer la catégorie elle-même, et non que
	 * cette action puisse être opérée de l'extérieur. La méthode qui fera ceci sera donc appelée private.
	 * Par contre, afin de pouvoir travailler avec les données de l'objet courant et appeler d'autres méthodes à 
	 * l'intérieur même d'une méthode, il faut mettre le mot clé "this" devant la variable ou la méthode.
	 */
	private void setCategorie()
	{
		int bornesSuperieures[] = {0, 1000, 10000, 100000, 500000, 1000000, 5000000, 10000000};
		char categorie[] = {'?', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
		int i = 0;
		while(i < bornesSuperieures.length && this.nbHabitants > bornesSuperieures[i])
			i++;
		this.categorie = categorie[i];
	}
	
	public String decrisToi()
	{
		return "\t"+this.nomVille+" est une ville de "+this.nomPays+", elle comporte "+this.nbHabitants+" habitants"
				+ "==> elle est donc de categorie "+this.categorie;
	}
	
	public String comparer(Ville v2)
	{
		String str = new String();
		if(v2.getNbHabitants() > this.nbHabitants)
			str = v2.getNom() + " est une ville plus peuplée que "+this.nomVille;
		else if(v2.getNbHabitants() < this.nbHabitants)
			str = this.nomVille+" est une ville plus peuplée que "+v2.getNom();
		else
			str = v2.getNom()+" et "+ this.nomVille+" ont le même nombre d'habitants";
		return str;
	}
	//COMMENT FAIRE pour savoir combien de villes j'ai créées? ==> avec les variables de classe
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// IV./ LES VARIABLES DE CLASSE
	/*
	 * La particularité d'une variable de classe, c'est qu'elle est commune à toutes les instances de la classe.
	 * Je vais donc créer mon compteur d'instances. Il s'agira d'une variable de type int que j'appelerai 
	 * nbInstances, et qui sera public. Je mettrai aussi son homologue private en place et l'appelerai 
	 * nbInstancesBis (il sera nécessaire de mettre un accesseur en place pour cette variable). Afin qu'une 
	 * variable soit une variable de classe, elle doit être précédée du mot clé static.
	 */
	public static int nbInstances = 0;
	private static int nbInstancesBis = 0;
	//Réf. actuelle : 43, 44, 61, 62
	//accesseur et méthode de classe
	public static int getNbInstancesBis()
	{
		return nbInstancesBis;
	}
	/*
	 * REMARQUE : l'accesseur de ma variable de classe déclarée public est aussi déclaré static. Ceci est une
	 * règle ! ==> Toutes les méthodes de classe n'utilisant que des variables de classe doivent être déclarées
	 * static. On les appelle des méthodes de classe, car il n'y en a qu'une pour toutes mes instances. Par contre,
	 * ce n'est plus une méthode de classe, si elle utilise des variables d'instance en plus des variable de classe
	 */
	//PETIT BEMOL
	/*
	 * Dans la partie sur les méthodes de classe, j'avais déclaré ces dernières public. J'aurais également pu les 
	 * déclarer private, mais attention, dans les deux cas, il faut qu'elles soient static, car elles sont exécutées
	 * dans un contexte static : la méthode main.
	 */
	
	// V./ LE PRINCIPE D'ENCAPSULATION
	/*
	 * Je viens de construire mon premier objet "maison". Cependant, sans le savoir, j'ai fait plus que ça : j'ai
	 * créé un objet dont les variables sont protégées de l'extérieur. En effet, depuis l'extérieur de la classe,
	 * elles ne sont accessibles que via les accesseurs et les mutateurs que j'ai définis. C'est le principe
	 * d'encapsulation.
	 * En fait, lorsqu'on procède de la sorte, on s'assure que le fonctionnement interne à l'objet est intègre,
	 * car toute modification de l'objet est maitrisée. J'ai développé des méthodes qui s'assurent qu'on ne modifie
	 * pas n'importe comment les variables.
	 * Prenons l'exemple de la variable nbHabitants. L'encapsuler me permet, lors de son affectation, de déduire
	 * automatiquement la catégorie de l'objet Ville, chose qui n'est pas facilement faisable sans encapsulation 
	 * (car l'utilisateur pourrait changer directement la valeur de la variable nbHabitants...). Par extension,
	 * si j'ai besoin d'effectuer des opérations déterminées lors de l'affectation du nom de ville par exemple, 
	 * je n'aurai pas à passer en revue tous les codes sources utilisant l'objet Ville : je n'aurais qu'à modifier
	 * l'objet (ou la méthode) en question, et le tour sera joué.
	 * Java est souple parce qu'il offre bcp de fonctionnalités qui peuvent être retravaillées selon les besoins,
	 * mais Java est aussi sécurisée car d'autres choses sont volontairement inaccessibles, pour éviter que l'on 
	 * ne "casse" quelque chose. 
	 */
	
	
	
}

