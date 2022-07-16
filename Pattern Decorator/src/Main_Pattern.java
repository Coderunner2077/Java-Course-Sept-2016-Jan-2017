/*
 * 												Le pattern decorator
 * Dans le chapitre sur les flux, j'ai vu des objets qui utilisent des instances d'objets de leur supertype dans
 * leur constructeur, comme ceci :
 * DataInputStream dis = new DataInputStream(
 * 							new BufferedInputStream(
 * 								new FileInputStream(
 * 									new File("test.txt"))));
 * La raison d'agir de la sorte est simple : c'est pour ajouter de façon dynamique des fonctionnalités à un objet. En
 * fait, au moment de récupérer des données de l'objet DataInputStream, celles-ci vont d'abord transiter par les
 * objets passés en paramètre. Ce mode de fonctionnement suit une certaine structure et une certaine hiérarchie de
 * classes : c'est le pattern decorator.
 * Ce pattern decorator permet d'ajouter des fonctionnalités à un objet sans avoir à modifier son code source.
 * Pour mieux comprendre ce qu'est le pattern decorator, voici un exemple :
 * Je crée une classe Gateau qui héritera de la classe abstraite Patisserie, le but étant de pouvoir ajouter des
 * couches à mon gateau sans avoir à modifier son code source.
 * Tout comme le pattern strategy, ce pattern utilise aussi la composition comme principe de base : mes objets
 * serons composés d'autres objets. La différence réside dans le fait que mes nouvelles fonctionnalités ne seront
 * pas obtenues uniquement en créant de nouveaux objets, mais en associant ceux-ci à des objets existants. Ce sera
 * cette association qui créera de nouvelles fonctionnalités.
 * Voici comme je vais procéder :
 * 		-	je crée un objet Gateau
 * 		-	je lui ajoute une CoucheChocolat
 * 		-	je lui ajoute aussi une CoucheCaramel
 * 		-	je lui ajoute aussi une CoucheBiscuit
 * 		-	j'appelerai le méthode qui confectionnera mon gateau
 * 
 * Tout cela démarre avec un concept fondamental : l'objet de base et les objets qui le décorent doivent être du même 
 * type, et ce, toujours pour la même raison => le polymorphisme.
 * En fait, les objets qui vont décorer mon gateau posséderont la même méthode preparer() que mon objet principal, 
 * et je vais faire fondre cet objet dans les autres. Cela signifie que mes objets qui vont servir de décorateurs
 * comporteront une instance de type Patisserie; ils vont englober les instances les unes après les autres et du
 * coup, je pourrai appeler la méthode preparer() de façon récursive.
 * On peut voir les décorateurs commes des poupées russes : l'instance de l'objet gateau est contenue dans l'objet 
 * CoucheChocolat, qui lui-même est contenu dans CoucheCaramel, qui lui-même est contenu dans CoucheBiscuit.
 * En fait, je vais passer mon instance d'objet en objet. Je vais ajouter les fonctionnalités des objets
 * "décorants" en appelant la méthode preparer() de l'instance se trouvant dans l'objet avant d'effectuer les
 * traitements de la même méthode de l'objet courant. 
 * Note : ce système ressemble fortement à la pile d'invocations de méthodes (réf Thread... à découvrir).
 * Réf voir diagramme.png
 * Sur ce diagramme, on peut remarquer que la classe mère Patisserie est en fait la strategy (une classe
 * encapsulant un comportement fait référence au pattern strategy : on peut dire qu'elle est la strategy de notre
 * hiérarchie) de notre structure, c'est pour cela que je pourrai appeler la méthode preparer() de façon récursive
 * afin d'ajouter des fonctionnalités à mes objets.
 * Réf main
 * Tout cela est assez représentatif de la façon dont fonctionnent des flux d'entrée/sortie en Java.
 * Note : L'invocation se fait en allant d'abord jusqu'au dernier élément pour remonter ensuite la pile 
 * d'invocations. Pour inverser ce fonctionnement, il me suffit (paraît-il) d'inverser les appels dans la méthode
 * preparer() : affecter d'abord le nom de la couche et ensuite le nom du decorateur
 */
public class Main_Pattern {

	public static void main(String[] args) {
		Patisserie pat = new CoucheChocolat(
							new CoucheCaramel(
								new CoucheBiscuit(
									new CoucheChocolat(
										new Gateau()))));
		System.out.println(pat.preparer());

	}

}
