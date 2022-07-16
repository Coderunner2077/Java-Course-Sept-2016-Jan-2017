
public class Ville {
/*
 * INTRO:
 * Ici, ma classe Ville est pr�c�d�e du mot public. Ce mot cl� correspond � la port�e de la classe.
 * En programmation, la port�e d�termine QUI peut faire appel � une classe, une m�thode ou une variable. La port�e
 * public signifie que tout le monde peut faire appel � l'�l�ment. Ici, dans le cas qui nous int�resse, il s'agit 
 * d'une m�thode. Une m�thode marqu�e comme public peut �tre appel�e depuis n'importe quel endroit du prog.
 * Je vais ici utiliser une autre port�e : private.
 * private : les m�thodes d�clar�es private correspondent souvent � des m�canismes internes � une classe que les 
 * d�veloppeurs souhaitent "cacher" ou simplement ne pas rendre accessible depuis l'ext�rieur de la classe...
 * Note : il en va de m�me pour les variables. Je peux prot�ger ces derni�res gr�ce au mot cl� private, en les 
 * rendant accessibles seulement � l'int�rieur de la classe o� elles sont cr��es.
 */
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// I./ LES CONSTRUCTEURS
//Avec des variables d'instances, je vais d�finir les donn�es que je vais attribuer � mon objet Ville.
	private String nomVille;
	private String nomPays;
	private int nbHabitants;
	private char categorie;
/*
 * Contrairement aux classes, les variables d'instances pr�sentes dans une classe sont public si je ne leur sp�cifie
 * pas de port�e. Il y a trois grands types de variables dans une classe objet:
 * 	-	les variables d'instances : d�finissent les caract�ristiques de mon objet
 * 	-	les variables de classe  : celles-ci sont communes � toutes les instances de ma classe
 *  -	les variables locales : celles que j'utiliserai pour travailler dans mon objet
 *  Pour cr�er mon projet, je vais devoir utiliser ce qu'on appelle des constructeurs. 
 *  Un constructeur est une m�thode d'instance qui va se charger de cr�er un objet et, le cas �ch�ant, d'initialiser
 *  ses variables de classe. Cette m�thode a pour r�le de signaler � la JVM qu'il faut r�server de la m�moire
 *  pour mon futur objet, et donc, par extension, d'en r�server pour toutes ses variables.
 *  Mon premier constructeur sera ce qu'on appelle un constructeur par d�faut, i.e. il ne prendra aucun param�tre,
 *  mais permettra tout de m�me d'instancier un objet (allocation + initialisation).
 *  */
	//constructeur par d�faut :
	public Ville(){
		System.out.println("Cr�ation d'une ville par d�faut !");
		nomVille = "inconnu";
		nomPays = "inconnu";
		nbHabitants = 0;
		this.setCategorie();
		nbInstances++;
		nbInstancesBis++;
	}
/*REMARQUE : le constructeur est une m�thode qui n'a aucun type de retour (void, double, ...) et qui porte le m�me 
 * nom que ma classe. Ceci est une r�gle immuable : le(s) constructeur(s) doit porter le m�me nom que la classe !
 * Son corrolaire est qu'un objet peut avoir plusieurs constructeurs. Il s'agit de la m�me m�thode, mais surcharg�e.
 * Je peux d'ores et d�j� cr�er une instance de Ville. Attention � ne pas oublier qu'une instance d'objet se fait
 * gr�ce au mot cl� new.
 */
	
	// constructeur avec des param�tres
	public Ville(String pNom,  int pNbre, String pPays)
	{
		System.out.println("Cr�ation d'une ville sp�cifique !");
		nomVille = pNom;
		nomPays = pPays;
		nbHabitants = pNbre;
		this.setCategorie();
		nbInstances++;
		nbInstancesBis++;
	}
	/*PAR CONTRE, mon objet pr�sente un gros d�faut, les variables d'instance qui le caract�risent sont accessibles
	dans la classe contenant mon main (je ne les avais pas d�clar�es private au d�but). Cela impique que je peux
	directement modifier les attributs de la classe. C'est tr�s risqu�, car un code ext�rieur pourrait faire 
	n'importe quoi avec mes objets, ou bien je pourrais	vouloir faire quelque chose � chaque fois qu'une valeur 
	change. Sans prot�ger mes donn�es, cela serait impossible � r�aliser.
	C'est pour cela qu'il fo prot�ger les variables d'instance en les d�clarant private.
	D�sormais, ces attributs ne seront plus acc�ssibles en dehors de la classe o� ils sont d�clar�s. Mais comment
	pourrait-on acc�der quand m�me � ces donn�es nom de Dieu?	
	*/
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// II./ LES ACCESSEURS ET LES MUTATEURS
	/*
	 * Un accesseur est une m�thode qui permet d'acc�der aux variables de mes objets en lecture. 
	 * Un mutateur permet d'en faire de m�me en �criture. Gr�ce aux accesseurs, qu'on appelle aussi getters, 
	 * je peux afficher les variables de mes objets, et gr�ce aux mutateurs (setters), je peux les modifier.
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
	 * Mes assesseurs sont bien des m�thodes, et elles sont public pour que je puisse y acc�der depuis une autre
	 *  classe que celle-ci : depuis le main par exemple. Les accesseurs sont du m�me type que la variable qu'ils
	 *  doivent retourner. Les mutateurs, par contre, sont de type void.
	 * Note: g�n�ralement, quand on parle d'accesseurs, ce terme inclut aussi les mutateurs. Autre chose, le fait 
	 * de mettre get et set devant est une convention de nommage (d'o� ==> "Getters" & "Setters")
	 * Aller dans main, pour les voir � l'oeuvre (r�f main: 21-41)
	 * Ca fait beaucoup de r�p�tition pour faire afficher les attributs pour chaque instance. On pourrait faire 
	 * plus simple : en cr�ant des m�thodes d'instance.
	 */
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// III./ LES METHODES D'INSTANCE
	/*
	 * ATTENTION, il fo bien distinguer les diff�rents types de m�thodes dans un objet :
	 * 		-	les constructeurs : m�thodes servant � cr�er des objets;
	 * 		-	les accesseurs : m�thodes servant � acc�der aux donn�es des objets;
	 * 		-	les m�thodes d'instance : m�thodes servant � la gestion des objets
	 * 
	 * Je peux cr�er les m�thodes d'instance suivantes pour mon objet Ville:
	 * 		-	faire un syst�me de cat�gorisation de villes en fct de leur population. Ceci est d�termin� � la 
	 * construction ou � la red�finition du nb d'habitants: j'ajoute donc une variable d'instance de type char.
	 * 		-	faire une m�thode de description de mon objet Ville.
	 * 		-	une m�thode de comparaison de la population de deux villes
	 * 
	 * Note : il vaut mieux que la classe Ville g�re la fa�on de d�terminer la cat�gorie elle-m�me, et non que
	 * cette action puisse �tre op�r�e de l'ext�rieur. La m�thode qui fera ceci sera donc appel�e private.
	 * Par contre, afin de pouvoir travailler avec les donn�es de l'objet courant et appeler d'autres m�thodes � 
	 * l'int�rieur m�me d'une m�thode, il faut mettre le mot cl� "this" devant la variable ou la m�thode.
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
			str = v2.getNom() + " est une ville plus peupl�e que "+this.nomVille;
		else if(v2.getNbHabitants() < this.nbHabitants)
			str = this.nomVille+" est une ville plus peupl�e que "+v2.getNom();
		else
			str = v2.getNom()+" et "+ this.nomVille+" ont le m�me nombre d'habitants";
		return str;
	}
	//COMMENT FAIRE pour savoir combien de villes j'ai cr��es? ==> avec les variables de classe
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// IV./ LES VARIABLES DE CLASSE
	/*
	 * La particularit� d'une variable de classe, c'est qu'elle est commune � toutes les instances de la classe.
	 * Je vais donc cr�er mon compteur d'instances. Il s'agira d'une variable de type int que j'appelerai 
	 * nbInstances, et qui sera public. Je mettrai aussi son homologue private en place et l'appelerai 
	 * nbInstancesBis (il sera n�cessaire de mettre un accesseur en place pour cette variable). Afin qu'une 
	 * variable soit une variable de classe, elle doit �tre pr�c�d�e du mot cl� static.
	 */
	public static int nbInstances = 0;
	private static int nbInstancesBis = 0;
	//R�f. actuelle : 43, 44, 61, 62
	//accesseur et m�thode de classe
	public static int getNbInstancesBis()
	{
		return nbInstancesBis;
	}
	/*
	 * REMARQUE : l'accesseur de ma variable de classe d�clar�e public est aussi d�clar� static. Ceci est une
	 * r�gle ! ==> Toutes les m�thodes de classe n'utilisant que des variables de classe doivent �tre d�clar�es
	 * static. On les appelle des m�thodes de classe, car il n'y en a qu'une pour toutes mes instances. Par contre,
	 * ce n'est plus une m�thode de classe, si elle utilise des variables d'instance en plus des variable de classe
	 */
	//PETIT BEMOL
	/*
	 * Dans la partie sur les m�thodes de classe, j'avais d�clar� ces derni�res public. J'aurais �galement pu les 
	 * d�clarer private, mais attention, dans les deux cas, il faut qu'elles soient static, car elles sont ex�cut�es
	 * dans un contexte static : la m�thode main.
	 */
	
	// V./ LE PRINCIPE D'ENCAPSULATION
	/*
	 * Je viens de construire mon premier objet "maison". Cependant, sans le savoir, j'ai fait plus que �a : j'ai
	 * cr�� un objet dont les variables sont prot�g�es de l'ext�rieur. En effet, depuis l'ext�rieur de la classe,
	 * elles ne sont accessibles que via les accesseurs et les mutateurs que j'ai d�finis. C'est le principe
	 * d'encapsulation.
	 * En fait, lorsqu'on proc�de de la sorte, on s'assure que le fonctionnement interne � l'objet est int�gre,
	 * car toute modification de l'objet est maitris�e. J'ai d�velopp� des m�thodes qui s'assurent qu'on ne modifie
	 * pas n'importe comment les variables.
	 * Prenons l'exemple de la variable nbHabitants. L'encapsuler me permet, lors de son affectation, de d�duire
	 * automatiquement la cat�gorie de l'objet Ville, chose qui n'est pas facilement faisable sans encapsulation 
	 * (car l'utilisateur pourrait changer directement la valeur de la variable nbHabitants...). Par extension,
	 * si j'ai besoin d'effectuer des op�rations d�termin�es lors de l'affectation du nom de ville par exemple, 
	 * je n'aurai pas � passer en revue tous les codes sources utilisant l'objet Ville : je n'aurais qu'� modifier
	 * l'objet (ou la m�thode) en question, et le tour sera jou�.
	 * Java est souple parce qu'il offre bcp de fonctionnalit�s qui peuvent �tre retravaill�es selon les besoins,
	 * mais Java est aussi s�curis�e car d'autres choses sont volontairement inaccessibles, pour �viter que l'on 
	 * ne "casse" quelque chose. 
	 */
	
	
	
}

