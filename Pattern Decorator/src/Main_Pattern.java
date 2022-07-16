/*
 * 												Le pattern decorator
 * Dans le chapitre sur les flux, j'ai vu des objets qui utilisent des instances d'objets de leur supertype dans
 * leur constructeur, comme ceci :
 * DataInputStream dis = new DataInputStream(
 * 							new BufferedInputStream(
 * 								new FileInputStream(
 * 									new File("test.txt"))));
 * La raison d'agir de la sorte est simple : c'est pour ajouter de fa�on dynamique des fonctionnalit�s � un objet. En
 * fait, au moment de r�cup�rer des donn�es de l'objet DataInputStream, celles-ci vont d'abord transiter par les
 * objets pass�s en param�tre. Ce mode de fonctionnement suit une certaine structure et une certaine hi�rarchie de
 * classes : c'est le pattern decorator.
 * Ce pattern decorator permet d'ajouter des fonctionnalit�s � un objet sans avoir � modifier son code source.
 * Pour mieux comprendre ce qu'est le pattern decorator, voici un exemple :
 * Je cr�e une classe Gateau qui h�ritera de la classe abstraite Patisserie, le but �tant de pouvoir ajouter des
 * couches � mon gateau sans avoir � modifier son code source.
 * Tout comme le pattern strategy, ce pattern utilise aussi la composition comme principe de base : mes objets
 * serons compos�s d'autres objets. La diff�rence r�side dans le fait que mes nouvelles fonctionnalit�s ne seront
 * pas obtenues uniquement en cr�ant de nouveaux objets, mais en associant ceux-ci � des objets existants. Ce sera
 * cette association qui cr�era de nouvelles fonctionnalit�s.
 * Voici comme je vais proc�der :
 * 		-	je cr�e un objet Gateau
 * 		-	je lui ajoute une CoucheChocolat
 * 		-	je lui ajoute aussi une CoucheCaramel
 * 		-	je lui ajoute aussi une CoucheBiscuit
 * 		-	j'appelerai le m�thode qui confectionnera mon gateau
 * 
 * Tout cela d�marre avec un concept fondamental : l'objet de base et les objets qui le d�corent doivent �tre du m�me 
 * type, et ce, toujours pour la m�me raison => le polymorphisme.
 * En fait, les objets qui vont d�corer mon gateau poss�deront la m�me m�thode preparer() que mon objet principal, 
 * et je vais faire fondre cet objet dans les autres. Cela signifie que mes objets qui vont servir de d�corateurs
 * comporteront une instance de type Patisserie; ils vont englober les instances les unes apr�s les autres et du
 * coup, je pourrai appeler la m�thode preparer() de fa�on r�cursive.
 * On peut voir les d�corateurs commes des poup�es russes : l'instance de l'objet gateau est contenue dans l'objet 
 * CoucheChocolat, qui lui-m�me est contenu dans CoucheCaramel, qui lui-m�me est contenu dans CoucheBiscuit.
 * En fait, je vais passer mon instance d'objet en objet. Je vais ajouter les fonctionnalit�s des objets
 * "d�corants" en appelant la m�thode preparer() de l'instance se trouvant dans l'objet avant d'effectuer les
 * traitements de la m�me m�thode de l'objet courant. 
 * Note : ce syst�me ressemble fortement � la pile d'invocations de m�thodes (r�f Thread... � d�couvrir).
 * R�f voir diagramme.png
 * Sur ce diagramme, on peut remarquer que la classe m�re Patisserie est en fait la strategy (une classe
 * encapsulant un comportement fait r�f�rence au pattern strategy : on peut dire qu'elle est la strategy de notre
 * hi�rarchie) de notre structure, c'est pour cela que je pourrai appeler la m�thode preparer() de fa�on r�cursive
 * afin d'ajouter des fonctionnalit�s � mes objets.
 * R�f main
 * Tout cela est assez repr�sentatif de la fa�on dont fonctionnent des flux d'entr�e/sortie en Java.
 * Note : L'invocation se fait en allant d'abord jusqu'au dernier �l�ment pour remonter ensuite la pile 
 * d'invocations. Pour inverser ce fonctionnement, il me suffit (para�t-il) d'inverser les appels dans la m�thode
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
