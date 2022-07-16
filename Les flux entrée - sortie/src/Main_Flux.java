/*
 * 											Les flux entrée / sortie
 * 
 * Une entrée / sortie en Java consiste en un échange de données entre le programme et une autre source, par exemple
 * la mémoire, un fichier, le programme lui-même... Pour réaliser cela, Java emploie ce qu'on appelle un stream 
 * (qui signifie flux). Celui-ci joue le rôle de médiateur entre la source de données et sa destination. Java met
 * à notre disposition toute une panoplie d'objets permettant de communiquer de la sorte. Toute opération sur les
 * entrée/sortie doit suivre le schéma suivant : ouverture, lecture, fermeture du flux.
 * Il existe une foule d'objets qui ont chacun sa façon de travailler avec les flux. 
 * Java a décomposé les objets traitant les flux en deux catégories :
 * 		-	les objets travaillant avec des flux d'entrée (in), pour la lecture de flux
 * 		-	les objets travaillant avec des flux de sortie (out), pour l'écriture de flux
 * 
 * I./ Utilisation de java.io
 * 1.) L'objet File
 * 
 * Je me crée un fichier test.txt à la racine de mon projet.
 * Réf main
 * J'instancie l'objet File de cette manière :
 * File file = new File("nomdufichier.extension");
 * Les méthodes de l'objet File :
 * 		-	getAbsolutePath() : renvoie le lien absolu du fichier
 * 		-	getName() ==> nom du fichier
 * 		-	exists() ==> true/false
 * 		-	isDirectory() ==> idem
 * 		-	isFile() ==> idem
 * 		-	listRoots() ==> une liste des lecteurs du PC (les disques durs)
 * 		-	listFiles() ==> une liste des fichiers et répertoires contenus dans le dossier (file). Cette instruction
 * 		est susceptible de générer une NullPointerException (si l'objet appelant n'est pas un dossier ou n'a pas de 
 *		sous-fichiers)
 *		-	delete() : efface le fichier définitivement 
 *		-	mkdir() : crée un répertoire (le nom donné à ce répertoire ne pourra cependant pas contenir de ".")
 * 		
 * 2.) Les objets FileInputStream et FileOutputStream
 * 
 * C'est par le biais des objets FileInputStream et FileOutputStream que l'on va pouvoir :
 * 		-	lire dans un fichier
 * 		-	écrire dans un fichier
 * 
 * Ces classes héritent des classes abstraites InputStream et OutputStream, présentes dans le package java.io
 * Il existe une hiérarchie des classes pour les traitements in et une autre pour les traitements out.
 * Attention : ce sont les classes héritant d'InputStream qui sont destinées à la lecture, et celles héritant 
 * d'OutputStream qui sont chargées de l'écriture, et non l'inverse !
 * ==> il fo se placer par rapport au programme : lorsque ce dernier lit des info dans un fichier, ce sont des
 * info qu'il reçoit, et par conséquent, elles s'apparentent à une entrée (in).
 * ==>	Au contraire, lorsqu'il va écrire dans un fichier, ce sont des info qu'il fait sortir : donc pour lui ce
 * flux de données correspond à une sortie (out).
 * 
 * Je vais maintenant travailler avec mon fichier : le but est de lire le contenu du fichier et de le copier dans
 * un autre, dont je spécifierai le nom dans mon programme :
 * Réf main 407-433
 * Le bloc finally permet de s'assurer que mes objets ont bien fermé leurs liens avec leurs fichiers respectifs,
 * ceci afin de permettre à Java de détruire ces objets pour ainsi libérer un peu de mémoire à mon ordi.
 * 
 * Les objets FileInputStream et FileOutputStream sont assez rudimentaires, car ils travaillent avec un nombre
 * déterminé d'octets à lire. Cela explique pourquoi la condition de la boucle est aussi tordue...
 * Au début, seuls les 127 premiers caractères de la table ASCII étaient codés (UNICODE 1), correspondant aux
 * caractères se trouvant dans la langue anglaise. Mais ce codage s'est rapidement avéré trop limité pour des
 * langues comportant des caractères accentués. Un jeu de codage de caractères étendu a donc été mis en place
 * afin de pallier ce problème.
 * Chaque code binaire Unicode 1 est codé sur 8 bits, soit 1 octet. Une variable de type byte, en Java correspond
 * à 1 octet et non un bit. 
 * Les objets que je viens d'utiliser emploient la 1re version d'UNICODE 1 qui ne 
 * comprend pas les caractères accentués, c'est pourquoi ces caractères ont un code décimal négatif dans mon
 * fichier. Lorsque je définis un tableau de byte à 8 entrées, cela signifie que je vais lire 8 octets à la fois.
 * 
 * Ainsi, les traitements des flux suivent une logique et une syntaxe précises. Lorsque j'ai copié un fichier,
 * j'ai récupéré un certain nombre d'octets dans un flux entrant que j'ai passé à un flux sortant. A chaque tour
 * de boucle, les données lues dans le fichier source sont écrites dans le fichier défini comme copie.
 * 
 * Il existe à présent des objets bcp plus faciles à utiliser, mais qui travaillent néanmoins avec les deux objets
 * que je viens d'étudier. Ces objets font également partie de la hiérarchie citée précédemment. Seulement, il
 * existe une superclasse qui les définit.
 * 
 * 3.) Objets FilterInputStream et FilterOutputStream
 * 
 * Ces deux classes sont en fait des classes abstraites. Elles définissent un comportement global pour leurs 
 * classes filles qui, elles, permettent d'ajouter des fonctionnalités aux flux d'entrée/sortie.
 * Voir figure réf diagramme.png
 * On peut voir qu'il existe quatre classes filles héritant de FilterInputStream (de même pour FilterOutputStream
 * => les classes dérivant de celle-ci ont les mêmes fonctionnalités, mais en écriture) :
 * 		-	DataInputStream : offre la possibilité de lire directement des types primitifs (double, char, int) 
 * 		grâce à des méthodes comme readDouble(), readInt()...
 * 		-	BufferedInputStream : cette classe permet d'avoir un tampon à disposition dans la lecture de flux. En
 * 		gros, les données vont tout d'abord remplir le tampon, et dès que celui-ci est plein, le prog accède aux
 * 		données
 * 		-	PushbackInputStream : permet de remettre un octet déjà lu dans le flux entrant
 * 		-	LinebackInputStream : cette classe offre la possibilité de récupérer le numéro de la ligne lue à un
 * 		instant T
 * Ces classes prennent en paramètre une instance dérivant des classes InputStream (pour les classes héritant de
 * FilterInputStream) ou de OutputStream (pour les classes héritant de FilterOutputStream).
 * Puisque ces classes acceptent une instance de leur superclasse en paramètre, on peut cumuler les filtres et
 * obtenir des choses de ce genre :
 * FileInputStream fis = new FileInputStream(new File("test.txt"));
 * DataInputStream dis = new DataInputStream(fis);
 * BufferedInputStream bis = new BufferedInputStream(dis);
 	//ou en condensé :
 * BufferedInputStream bis = new BufferedInputStream(
 * 								new DataInputStream(
 * 									new FileInputStream(
 * 										new File("test.txt))));
 * Testons et comparons maintenant le temps d'exécution d'un long fichier texte avec la méthode conventionnelle 
 * vue précédemment, puis avec un buffer. 
 * Réf console : la différence est vraiment énorme 1,743 secondes avec la 1re méthode et 0,035 secondes pour la 
 * seconde ==> l'utilisation d'un buffer permet une nette amélioration des performances de mon code.
 * A présent : le test pour l'écriture. Réf console : la différence est encore plus nette (pour lecture + écriture)
 * => 4,86 secondes avec FileInputStream et FileOutputStream
 * 	  0,061 secondes avec BufferedInputStream et BufferedOutputStream
 * 
 * Je vais rapidement aborder les objets Data(Input/Output)Stream ==> ils s'utilisent comme les objets 
 * BufferedInputStream, sauf qu'ils ont des méthodes de lecture pour chaque type primitif. IL faut donc que le 
 * fichier soit généré par la biais d'un DataOutputStream pour que les méthodes fonctionnent correctement.
 * Réf main 601-634
 * J'ai bien vu que ce type d'objet ne manque pas de fonctionnalités
 * Remarque : ces caractères écrits dans le fichier avec DataOutputStream sont, comment dirais-je, asiatiques (même 
 * les chiffres... ) !
 * 
 * Jusqu'ici, je n'ai travaillé qu'avec des types primitifs, mais il est également possible de travailler avec
 * des objets !
 * 
 * 4.) Les objets ObjectInputStream et ObjectOutputStream
 * 
 * Il faut savoir que lorsqu'on veut écrire des objets dans des fichiers, cela s'appelle la "sérialisation" : c'est
 * le nom que porte l'action de sauvegarder des objets. Voyons d'abord comment sérialiser un objet de ma 
 * composition.
 * Réf Game : cette classe implémente l'interface Serializable (qui n'a aucune méthode à redéfinir ==> cette 
 * interface est une "interface marqueur"). Rien qu'en implémentant cette interface dans un objet, Java sait
 * que cet objet peut être sérialisé. Attention, si je n'implémente pas cette interface dans mes objets, ceux-ci
 * ne pourront pas être sérialisés. En revanche, si une superclasse implémente Serializable, ses enfants seront
 * considérés comme sérialisables.
 * Voici ce que je fais :
 * 		-	je crée deux ou trois objets Game
 * 		-	je les sérialise dans un fichier de mon choix
 * 		-	ensuite, je les désérialise afin de pouvoir les réutiliser
 * 
 * Attention, la désérialisation d'un objet peut engendrer une ClassNotFoundException, il faut
 * penser à la capturer
 * Remarque : encore des caractères bizarres dans le fichier !!
 * En effet, les données de mes objets sont enregistrés dans le fichier. Mais que ce passerait-il
 * si mon objet G avait un autre objet de ma composition en son sein ? ==> je crée la classe Notice,
 * et j'implémente une notice par défaut dans mon objet G. Réf G.
 * Et lorsque j'exécute le prog ==> mon code ne compile plus, et c'est normal, puisque mon objet
 * Notice n'est pas sérialisable, une erreur de compilation est donc levée. 
 * Maintenant, deux choix s'offrent à moi :
 * 		-	soit je fais en sorte de rendre mon objet sérialisable
 * 		-	soit je spécifie dans ma classe G que la variable notice n'a pas à être sérialisée.
 * Pour la 1re option, j'implémente tout simplement l'interface Serializable dans ma classe Notice.
 * Pour la seconde, il suffit de déclarer ma variable transient. Réf G 5.
 * Attention, dans le second cas, à l'invocation de la méthode toString() sur les objets désérialisés, 
 * je ne verrais pas les éventuelles références aux variables transcient figurant dans ladite méthode, car
 * la machine virtuelle les a tout bonnement ignorées lors de la sérialisation.
 * 
 *  
 * 5.) Les objets CharArray(Writer/Reader) et String(Writer/Reader)
 * 
 * Ces deux types jouent quasiment le même rôle : écrire et lire un flux de texte dans un tampon de mémoire. De plus,
 * ils ont les mêmes méthodes que leur classe mère. Ces deux objets n'ajoutent donc aucune fonctionnalité à leur 
 * objet mère !
 * Leur principale fonction est de permettre d'écrire un flux de caractères dans un buffer adaptatif:
 * un emplacement en mémoire qui peut changer de taille selon les besoins (par ailleurs il existe
 * des classes remplissant le même rôle que ces classes-ci : ByteArray(Input/Output)Stream).
 * Les objets String(Writer/Reader) fonctionnent de la même manière que CharArray(Writer/Reader). En fait, il s'agit
 * du même code, mais avec des objets différents.
 * Réf main 700-743
 * 
 * Voyons maintenant comment traiter les fichiers de texte avec des flux de caractères
 * 
 * 6.) Les classes File(Write/Reader) et Print(Writer/Reader)
 * 
 * Les objets étudiées précédemment traiteront mon fichier de la même façon que s'il contenait des données binaires.
 * Ces deux objets, présents dans le package java.io, servent à lire et écrire des données dans un fichier texte.
 * Réf main 745-800
 * Remarque : PrintReader semble ne pas exister
 * 
 * Depuis le JDK 1.4, le package java.nio a vu le jour ==> les performances des flux, buffers, traités par 
 * java.io sont nettement améliorées.
 * 
 * II./ L'utilisation de java.nio
 * 
 * 1.) FileChannel
 * 
 * nio signifie "New I/O". Ce package a été créé afin d'améliorer les tratements des fichiers, du réseau et des
 * buffers. Il permet de lire les données (je m'intéressarai uniquement à l'aspect fichier) d'une façon différente.
 * Tandis que les objets du package java.io traitaient les données par octets, les objets du package java.nio les
 * traitent par blocs de données : la lecture est donc accélérée.
 * Tout repose sur deux objets de ce nouveau package : les channels et les buffers. Les channels sont en fait des
 * flux, tout comme dans l'ancien package, mais ils sont amenés à travailler avec un buffer dont je définis la taille.
 * Pour simplifier au maximum, lorsque j'ouvre un flux vers un fichier avec un objet FileInputStream, je peux 
 * récupérer un canal vers ce fichier. Celui-ci, combiné à un buffer, me permet de lire mon fichier encore plus vite
 * qu'avec un BufferedInputStream. 
 * Réf main 813-847 comparaison de lecture : buffer conventionnel vs nouveau buffer
 * ==> 0,03 secondes avec le buffer conventionnel, et 0,009 secondes avec le nouveau buffer
 * Ce nouveau package est le plus souvent utilisé pour traiter les flux sur les réseaux. Ce package offre un buffer
 * par type primitif pour la lecture sur le channel. Voici ces classes :
 * 		-	IntBuffer
 * 		-	CharBuffer
 * 		-	ShortBuffer
 * 		-	ByteBuffer
 * 		-	DoubleBuffer
 * 		-	FloatBuffer
 * 		-	LongBuffer
 * Il faudra penser à les importer en cas d'utilisation dans java.nio
 * 
 * 2.) Une fermeture des flux moins compliquée
 * 
 * Avec l'arrivée de Java 7, qq nouveautés ont vu le jour pour la gestion des exceptions sur les flux. Contrairement
 * à la gestion de la mémoire (mes variables, mes classes, etc.) qui est déléguée au garbage collector (ramasse
 * miette), plusieurs types de ressources doivent être gérés manuellement. Les flux sur des fichiers en font partie,
 * et, d'un point de vue plus général, toutes les ressources que je dois fermer manuellement(les flux réseaux, 
 * les connexions à une base de données...). Pour ce genre de flux, j'ai vu qu'il fo déclarer une variable en dehors
 * du bloc try/catch afin qu'elle soit accessible dans les autres blocs d'instruction, notamment le bloc finally.
 * Java 7 initie ce qu'on appelle vulgairement le "try-with-resources". Ceci me permet de déclarer mes ressources
 * utilisées directement dans le bloc try(...), ces dernières seront automatiquement fermées à la fin du bloc
 * d'instructions ! Ainsi, réf main 873, je n'ai plus à me soucier de la fermeture avec le bloc finally.
 * Note : les différentes ressources utilisées sont séparées par un ";" dans le bloc try 
 * Cependant, il faut prendre quelques précautions, notamment pour ce genre de déclaration :
 * try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"))){...}
 * Le fait d'avoir des ressources encapsulées ne rend pas "visible" les ressources encapsulées. Dans ce cas-là, si
 * une exception est levée, le flux correspondant à FileInputStream ne sera pas fermé. Pour pallier ce problème, il
 * suffit de bien découper toutes les ressources à utiliser, comme ceci :
 * try(FileInputStream fis = new FileInputStream("test.txt"); ObjectInputStream ois = new ObjectInputStream(fis)){
 * ...}
 * MAIIS, où est passé le File, que je mettais d'habitude dans l'instanciation de FileInputStream ?!!
 * ==> en fait, il y a une restriction sur ce mode de fonctionnement. Pour rendre la fermeture possible, les 
 * développeurs de la plateforme Java 7 ont créé une nouvelle interface : java.lang.AutoCloseable. Seuls les 
 * objets implémentant cette interface peuvent être utilisés de la sorte ! Et la classe File n'en fait pas parti.
 * 
 * III./ Depuis Java 7 : nio II
 * 
 * 1.) introduction au nio II
 * L'une des grandes nouveautés de Java 7 réside dans NIO 2.0 avec un nouveau package java.nio.file en remplacement
 * de la classe java.io.File
 * Voici un bref listing de quelques nouveautés:
 * 		-	une meilleure gestion des exceptions : la plupart des méthodes de la classe File se contentent de 
 * 		renvoyer une valeur nulle en cas de problème, avec ce nouveau package, des exceptions seront levées afin de
 * 		mieux cibler la cause du (ou des) problème(s)
 * 		-	un accès complet au système de fichiers (support des liens/liens symboliques, etc.)
 * 		-	l'ajout de méthodes utilitaires tels que le déplacement/copie de fichier, la lecture/écriture binaire ou
 * 		texte...
 * 		-	récupérer la liste des fichiers d'un répertoire via un flux
 * 		-	remplacement de la classe java.io.File par java.nio.file.Path
 * 
 * Afin d'être le plus souple et complet possible, les développeurs de la plateforme ont créé une interface
 * java.nio.file.Path dont le rôle est de récupérer et de manipuler des chemins de fichiers de dossier et
 * une classe java.nio.file.Files qui contient tout un tas de méthodes qui simplifient certaines actions (copie,
 * déplacement...) et permet aussi de récupérer tout un tas d'informations sur un chemin.
 * Réf main 892
 * Remarque : java.nio.file.Paths à importer également (en plus du ..Path)
 * La classe Files permet également de lister le contenu d'un répertoire mais via un objet DirectoryStream qui
 * est un itérateur. Cela évite de charger tous les fichiers en mémoire pour récupérer leurs informations. 
 * Réf main 900
 * Note : j'ai également la possibilité de rajouter un filtre à mon listing de répertoire afin qu'il ne liste que
 * certains fichiers, comme ceci :
 * try(DirectoryStream<Path> listing = Files.newDirectoryStream(chemin, "*.txt")){...} => ne prendra en compte
 * que les fichiers ayant l'extension ".txt"
 * 
 * Plus loin, quelques méthodes intéressantes...
 * 
 * 2.) La copie du fichier
 * C'est aussi simple que ça :
 * Path cible = Paths.get("test.txt");
 * Path source = Paths.get("test2.txt");
 * try{
 * 		Files.copy(source, cible, StandardCopyOption.REPLACE_EXISTING);
 * }catch(IOException e){
 * 		e.printStackTrace();
 * }
 * Le troisième argument permet de spécifier les options de copie. Voici celles qui sont disponibles : 
 * 		-	StandardCopyOption.REPLACE_EXISTING : remplace le fichier cible même s'il existe déjà
 * 		-	StandardCopyOption.COPY_ATTRIBUTES : copie les attributs du fichier source sur le fichier cible (droits
 * 		en lecture, etc.)
 * 		-	StandardCopyOption.ATOMIC_MOVE : copie atomique
 * 		-	LinkOption.NOFOLLOW_LINKS : ne prendra pas en compte les liens
 * 
 * 3.) Le déplacement de fichier et autres méthodes
 * 
 * Pour déplacer le fichier "test2.txt" vers un fichier "test3.txt", il suffit de faire :
 * Path source = Paths.get("test2.txt");
 * Path cible = Paths.get("test3.txt");
 * try{
 * 		Files.move(source, cible, StandardCopyOption.REPLACE_EXISTING);
 * }catch(IOException e) { e.printStackTrace();}
 * 
 * Remarque : il va de soi que le fichier "test2.txt" sera effacé à la fin de l'instruction
 * 
 * Dans le même genre, j'ai aussi :
 * 		-	une méthode Files.delete(path) qui supprime définitivement un fichier
 * 		-	une méthode Files.createFile(path) qui permet de créer un fichier vide.
 * 4.) Ouvrir des flux
 * 
 * La classe Files propose plusieurs méthodes pour faciliter la lecture ou l'écriture de fichiers et de flux
 * selon les besoins allant des plus simples aux plus complexes :
 * Besoins simples<-------------------------------------------------------------------------------->Besoins complexes
 * 					readAllBytes()		newBufferedReader()		newInputStream()	newByteChannel()
 * 					readAllLines()		newBufferedWriter()		newOutputStream()		  ||
 * 						||					   ||					  ||			  Channel et 
 * 					Lecture intég-		  Fichier texte			Flux sans buffer	  ByteBuffer
 *					 ralité du
 * 					  fichier
 * 
 * Les méthodes readAllBytes() et readAllLines() permettent de lire l'intégralité du contenu d'un fichier
 * respectivement d'octets et et texte. Deux surchages de la méthode write() permettent d'écrire l'intégralité
 * d'un fichier. Ces méthodes sont à réserver pour de petits fichier.
 * Les méthodes newBufferedReader() et newBufferedWriter() sont des helpers pour faciliter la création d'objet 
 * de types BufferedReader et BufferedWriter permettant la lecture et l'écriture de fichiers de type texte en
 * utilisant un tampon.
 * Les méthodes newInputStream() et newOutputStream() sont des helpers pour faciliter la création d'objets permettant
 * la lecture et l'écriture de fichiers d'octets.
 * Ces quatre méthodes sont des helpers pour créer des objets du package java.io
 * La méthode newByteChannel() est un helper pour créer un objet de type SeekableByteChannel.
 * La classe FileChannel propose des fonctionnalités avancées sur l'utilisation d'un fichier (verrous, mapping 
 * direct à une zone de mémoire, ...) : cette classe a été enrichie pour fonctionner avec NIO2
 * Réf main 942-972 : écriture/lecture de fichier sans et avec buffer
 * 
 * 5.) Gérer les fichiers Zip
 * 
 * Réf main 977-990
 * 
 * 6.) Autres objets utiles
 * 
 * Il est également possible d'être averti via l'objet WatchService lorsqu'un fichier est modifié, de gérer les 
 * entrées/sorties asynchrones via les objets AsynchronousFileChannel, AsynchronousServerSocketChannel. Ceci 
 * permet de faire les actions en tâche de fond sans bloquer le code pendant l'exécution. Il est aussi possible
 * d'avoir accès aux attributs grâce à 6 vues permettant de voir plus ou moins d'informations, à savoir :
 * 		-	BasicFileAttributeView : permet un accès aux propriétés généralement communes à tous les systèmes de 
 * 		fichiers
 * 		-	DosFileAttributeView : ajoute le support des attributs MS-DOS (readonly, hidden, system, archive) à 
 * 		l'objet ci-dessus
 * 		-	PosixFileAttributeView : permet de manipuler le propriétaire du fichier
 * 		-	AclFileAttributeView : permet de manipuler les droits d'accès au fichier
 * 		-	UserDefinedFileAttributeView : permet de définir des attributs personnalisés
 * 
 */
// Package à importer afin d'utiliser l'objet File
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.CharArrayReader;
import java.io.CharArrayWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.io.BufferedWriter;
public class Main_Flux {

	public static void main(String[] args) {
		// Création de l'objet File
		File f = new File("test.txt");
		System.out.println("Chemin absolu du fichier :" + f.getAbsolutePath());
		System.out.println("Nom du fichier : "+ f.getName());
		System.out.println("Est-ce qu'il existe : " + f.exists());
		System.out.println("Est-ce un repertoire : "+ f.isDirectory());
		System.out.println("Est-ce un fichier : "+ f.isFile());
		
		System.out.println("Affichage des lecteurs à la racine du PC : ");
		for(File file : f.listRoots())
		{
			System.out.println(file.getAbsolutePath());
			try{
				int i = 1;
				// On parcourt la liste des fichiers et répertoires
				for(File nom : file.listFiles()){
					//S'il s'agit d'un dossier, on ajoute un '/'
					System.out.print("\t\t" + ((nom.isDirectory()) ? nom.getName() + "/" : nom.getName()));
					if((i%4)==0){
						System.out.print("\n");
					}
					i++;
				}
				System.out.println("\n");
			}
			catch(NullPointerException e){
				//L'instruction peut générer Une NullPointerException s'il n'y a pas de sous-fichiers
			}
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//File(Input/Output)Stream
		
		//Je déclare mes objets en dehors du bloc try/catch
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try{
			//j'instancie mes objets
			//fis va lire le fichier
			//fos va écrire dans le nouveau !
			fis = new FileInputStream(new File("test.txt"));
			fos = new FileOutputStream(new File("test2.txt"));
			//Je crée un tableau de byte pour indiquer le nombre de bytes lus à chaque tour de boucle
			byte[] buf = new byte[8];
			//on crée une variable de type int afin d'y affecter le résultat de la lecture ==> -1 quand fini 
			int n = 0;
			// Tant que l'affectation dans la variable est possible, on boucle.
			//Lorsque la lecture du fichier est terminée, l'affectation n'est plus possible ==> on sort de la boucle
			while((n = fis.read(buf)) >= 0){
				//j'écris dans mon deuxième fichier avec l'objet adéquat
				fos.write(buf);
				//j'affiche ce qu'a lu ma boucle au format byte et au format char
				for(byte bit : buf){
					System.out.print("\t"+ bit +"(" + (char) bit + ")");
				}
				System.out.println("");
				//Je réutiliserai le buffer à vide, au cas où les derniers byte lus ne soient pas un multiple de 8
				//Ceci permet d'avoir un buffer vierge à chaque lecture et ne pas avoir un doublon en fin de 
				//fichier
				buf = new byte[8];
			}
			System.out.println("Copie terminée");
		}catch (FileNotFoundException e){
			//Cette exception est levée si FileInputStream ne trouve aucun fichier
			e.printStackTrace();
		} catch (IOException e){
			//Celle-ci se produit lors d'une erreur d'écriture ou de lecture
			e.printStackTrace();
		}finally{
			//Je ferme mes flux de données dans un bloc finally pour m'assurer que ces instructions seront 
			//exécutées dans tous les cas (même si une exception est levée)
			try{
				if(fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try{
				if(fos != null)
					fos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////
		//idem
		FileInputStream fiS = null;
		FileOutputStream foS = null;
		try{
			fiS = new FileInputStream(new File("test.txt"));
			foS = new FileOutputStream(new File("test3.txt"));
			byte[] buF = new byte[8];
			int n = 0;
			while((n = fiS.read(buF)) >= 0){
				foS.write(buF);
				for(byte bit : buF){
					System.out.print("\t"+ bit + "("+ (char)bit + ")");
				}
				System.out.print("\n");
				buF = new byte[8];	
			}
			System.out.println("Copie finito");
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try{
				if(fiS != null)
					fiS.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
			try{
				if(foS != null)
					foS.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Comparaison lecture : FileInputStream vs BufferedInputStream
		
		// test des deux méthodes
		FileInputStream fIs = null;
		BufferedInputStream bis = null;
		try{
			fIs = new FileInputStream(new File("dictionnaire.txt"));
			bis = new BufferedInputStream(new FileInputStream(new File("dictionnaire.txt")));
			byte[] buf = new byte[8];
			//on récupère le temps du système
			long startTime = System.currentTimeMillis();
			//inutile d'effectuer des traitements dans ma boucle
			while(fIs.read(buf) != -1);
			//on affiche le temps d'exécution
			System.out.println("Temps de lecture avec FileInputStream : " + 
			(System.currentTimeMillis() - startTime));
			// on réinitialise
			startTime = System.currentTimeMillis();
			buf = new byte[8];
			// je boucle avec l'autre méthode
			while(bis.read(buf) != -1);
			//on réaffiche
			System.out.println("Temps de lecture avec BufferedInputStream : " +
			(System.currentTimeMillis() - startTime));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(fIs != null)
					fIs.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			try{
				if(bis != null)
					bis.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		//Comparaison lecture + écriture : File(Input/Output)Stream vs Buffered(Input/Output)Stream
		
		// test lecture + écriture pour les méthodes FileInputStream et BufferedInputStream (+ les output respectifs)
		
		fis = null;
		fos = null;
		bis = null;
		BufferedOutputStream bos = null;
		try{
			fis = new FileInputStream(new File("dictionnaire.txt"));
			fos = new FileOutputStream(new File("dico.txt"));
			bis = new BufferedInputStream(new FileInputStream(new File("dictionnaire.txt")));
			bos = new BufferedOutputStream(new FileOutputStream(new File("dico2.txt")));
			byte[] buf = new byte[8];
			long startTime = System.currentTimeMillis();
			while(fis.read(buf) != -1)
				fos.write(buf);
			System.out.println("Temps de lecture + écriture avec FileInputStream et FileOutputStream : " +
				(System.currentTimeMillis() - startTime));
			// je réinitialise
			startTime = System.currentTimeMillis();
			buf = new byte[8];
			// je fais le test avec le buffer
			while(bis.read(buf) != -1)
				bos.write(buf);
			System.out.println("Temps de lecture + écriture avec le buffer : " +
				(System.currentTimeMillis() - startTime));
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(fis != null)
					fis.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			try{
				if(fos != null)
					fos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			try{
				if(bis != null)
					bis.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			try{
				if(bos != null)
					bos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Data(Input/Output)Stream
		// je déclare les objets en dehors du bloc try/catch
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try{
			//je crée D'ABORD mon fichier par le biais du DataOutputStream
			dos = new DataOutputStream(
					new BufferedOutputStream(
						new FileOutputStream(
							new File("chintok.txt"))));
			// je vais écrire chaque type primitif
			dos.writeBoolean(true);
			dos.writeByte(100);
			dos.writeChar('A');
			dos.writeChar('B');
			dos.writeDouble(434.9999999999d);
			dos.writeDouble(343.232323d);
			dos.writeFloat(12.34f);
			dos.writeInt(12122323);
			dos.writeLong(1223230403433435L);
			dos.writeShort(235);
			try{
				if(dos != null)
					dos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			// je récupère maintenant les données
			dis = new DataInputStream(
					new BufferedInputStream(
						new FileInputStream(
							new File("chintok.txt"))));
	
			System.out.println(dis.readBoolean());
			System.out.println(dis.readByte());
			System.out.println(dis.readChar() + ", " + dis.readChar());
			System.out.println(dis.readDouble() + ", " + dis.readDouble());
			System.out.println(dis.readFloat());
			System.out.println(dis.readInt());
			System.out.println(dis.readLong());
			System.out.println(dis.readShort());
			// attention : le type de donnée lue doit correspondre à celui attendu par chaque méthode
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(dis != null)
					dis.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Object(Input/Output)Stream
		
		ObjectInputStream ois = null;
		ObjectOutputStream oos = null;
		try{
			oos = new ObjectOutputStream(
					new BufferedOutputStream(
						new FileOutputStream(
								new File("emag.txt"))));
			// je vais écrire mes objets Game dans le fichier
			oos.writeObject(new Game("Assassin's Creed", "Aventure", 45.99));
			oos.writeObject(new Game("Tomb Raider", "Plateforme", 24.99));
			oos.writeObject(new Game("Tetris", "Strategie", 1.99));
			//ne pas oublier de fermer le flux !
			try{
				if(oos != null)
					oos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			// on récupère maintenant les données
			ois = new ObjectInputStream(
					new BufferedInputStream(
						new FileInputStream(
							new File("emag.txt"))));
			try{
				System.out.println("Affichage des emag : ");
				System.out.println("************************");
				Game ass = (Game)ois.readObject();
				System.out.println(ass.toString());
				System.out.println(((Game)ois.readObject()).toString());
				System.out.println(((Game)ois.readObject()).toString());
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(ois != null)
					ois.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		Game myGame = new Game("Jeu test", "genre genre", 23.99);
		System.out.println("TEST DE TRANSCIENT DANS toString() : " + myGame);
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//CharArray(Writer/Reader) pour écrire et lire dans le buffer
		CharArrayWriter caw = new CharArrayWriter();
		CharArrayReader car;
		try{
			caw.write("Hello le monde !");
			//appel à la méthode toString de mon objet de manière tacite
			System.out.println(caw);
			//caw.close() n'a aucun effet sur le flux
			// Seul caw.reset() peut tout effacer
			caw.close();
			
			//on passe un tableau de caractères à l'objet qui va lire le tampon
			car = new CharArrayReader(caw.toCharArray());
			int i;
			//On remet tous les caractères lus dans un String
			String str = "";
			while((i = car.read()) != -1) //les caractères lus sont donc renvoyés dans i
				str += (char) i;
			System.out.println(str);
			car.close();
		}catch (IOException e){
			e.printStackTrace();
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// String(Writer/Reader)
		StringWriter sw = new StringWriter();
		StringReader sr;
		try{
			sw.write("Hello the worldish !" );
			//Appel à la méthode toString() de mon objet de manière tacite
			System.out.println(sw);
			sw.close();
			//on passe un tableau de caractères à l'objet qui va lire le tampon
			sr = new StringReader(sw.toString());
			int i = 0;
			//on remet tous les caractères lus dans un String
			String str = "";
			while((i = sr.read()) != -1)
				str += (char)i;
			System.out.println(str);
			sr.close();
		}catch(IOException e){
			e.printStackTrace();
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// File(Writer/Reader)
		File file = new File("testFileWriter.txt");
		FileWriter fw;
		FileReader fr;
		try{
			//création de l'objet
			fw = new FileWriter(file);
			String str = "Voici une ligne de texte";
			str += "\nVoici une autre ligne de texte. Cet été la pâte, chaîne, accents";
			// on écrit la chaîne dans le fichier
			fw.write(str);
			//on ferme le flux
			try{
				if(fw != null)
					fw.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			//création de l'objet de lecture
			fr = new FileReader(file);
			int i = 0;
			str = new String();
			//lecture de données
			while((i = fr.read()) != -1)
				str += (char) i;
			//affichage
			System.out.println(str);
			
			try {
				if(fr != null)
					fr.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
			long start = System.currentTimeMillis();
			fr = new FileReader(new File("dictionnaire.txt"));
			while((i = fr.read()) != -1);
			System.out.println("Voici le temps de lecture avec FileReader : " + (System.currentTimeMillis() - start));
			fr.close();
			try{
				if(fr != null)
					fr.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//PrintWriter et FileReader
		File file2 = new File("testPrintWriter.txt");
		PrintWriter pw;
		fr = null;
		try{
			pw = new PrintWriter(file2);
			String str = "J'essaie d'écrire avec PrintWriter";
			str += "\nDieu sait si j'ai réussi... accents : ê, é, è, â, ù, ï, à, ç";
			pw.write(str);
			pw.close();
			fr = new FileReader(file2);
			int i = 0;
			str = "";
			while((i = fr.read()) != -1)
				str += (char) i;
			System.out.println(str);
			fr.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// PARTIE 2 : UTILISATION DE java.nio
		//Comparaison de lecture : buffer conventionnel vs nouveau buffer
		fis = null;
		bis = null;
		FileChannel fc = null;
		fos = null;
		bos = null;
		try{
			//création des objets
			fis = new FileInputStream(new File("dictionnaire.txt"));
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(new FileOutputStream(new File("dicoBuffer.txt")));
			byte[] buf = new byte[8];
			//Démarrage du chrono
			long startTime = System.currentTimeMillis();
			//lecture avec le buffer conventionnel
			while(bis.read(buf) != -1) 
				bos.write(buf);
			//Temps d'exécution
			System.out.println("Temps d'exécution (lecture + écriture) avec un buffer conventionnel : " +
			(System.currentTimeMillis() - startTime));
			bis.close();
			fis.close();
			//création d'un nouveau flux de fichier
			fis = new FileInputStream(new File("dictionnaire.txt"));
			//on récupère le canal
			fc = fis.getChannel();
			// on en déduit la taille
			int size = (int)fc.size();
			//on crée un buffer correspondant à la taille du fichier
			ByteBuffer bBuff = ByteBuffer.allocate(size);
			//démarrage du chrono
			startTime = System.currentTimeMillis();
			//Démarrage de la lecture
			fc.read(bBuff);
			// on prépare à la lecture avec l'appel à flip
			bBuff.flip();
			//affichage du temps d'exécution
			System.out.println("Temps d'exécution avec le nouveau buffer : " +
			(System.currentTimeMillis() - startTime));
			/*Puisque j'ai utilisé un buffer de byte afin de récupérer les données
			 * On peut utiliser un tableau de byte
			 * La méthode array retourne un tableau de byte
			 */ 
			byte[] tabByte = bBuff.array();
			for(int i = 0; i < 20; i++) {
				System.out.print((char) tabByte[i] + " + ");
			}
			System.out.println("");
			fc.close();
			fos = new FileOutputStream("dicoBis.txt");
			fc = fos.getChannel();
			startTime = System.currentTimeMillis();
			fc.write(bBuff);
			bBuff.flip();
			System.out.println("Temps d'écriture avec le nouveau buffer : " +
					(System.currentTimeMillis() - startTime));
			
			// Remarque : après observation, le résultat d'écriture de FileChannel est plus précis et moins aléatoire qu'avec fos et 
			// bos
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		}finally{
			try{
				if(fis != null)
					fis.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			try{
				if(bis != null)
					bis.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			try{
				if(fc != null)
					fc.close();
				if(bos != null) // lazy me
					bos.close();
				if(fos != null)
					fos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//FileChannel (avec fermeture automatique)
		try(FileInputStream fis2 = new FileInputStream("test.txt"); FileChannel fc2 = fis2.getChannel()){
			int size = (int)fc2.size();
			ByteBuffer bBuff = ByteBuffer.allocate(size);
			fc2.read(bBuff);
			bBuff.flip();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// PARTIE 3 : nio II
		// Création de l'objet Path
		Path path = Paths.get("test.txt");
		System.out.println("Chemin absolu du fichier :" + path.toAbsolutePath());
		System.out.println("Nom du fichier : "+ path.getFileName());
		System.out.println("Est-ce qu'il existe : " + Files.exists(path));
		System.out.println("Est-ce un repertoire : "+ Files.isDirectory(path));
		// on récupère maintenant la liste de répertoires dans une collection typée
		// via l'objet FileSystem qui représente le système de fichiers de l'OS hébergeant la JVM
		Iterable<Path> roots = FileSystems.getDefault().getRootDirectories();
		//maintenant, il ne me reste plus qu'à parcourir
		for(Path chemin : roots){
			System.out.println(chemin);
			//pour lister un répertoire, il faut utiliser l'objet DirectoryStream
			//l'objet Files permet de créer ce type d'objet afin de pouvoir l'utiliser
			try(DirectoryStream<Path> listing = Files.newDirectoryStream(chemin)){
				int i = 0;
				for(Path nom : listing){
					System.out.print("\t\t" + ((Files.isDirectory(nom)) ? nom + "/" : nom));
					i++;
					if((i % 4) == 0)
						System.out.println("\n");
				}
			}catch(IOException e){
				e.printStackTrace();
			}
			System.out.println("");
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Copie de fichier
		Path source = Paths.get("dictionnaire.txt");
		Path cible = Paths.get("dico3.txt");
		try{
			long startTime = System.currentTimeMillis();
			Files.copy(source, cible, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Temps de copie du dico avec Files : " + (System.currentTimeMillis() - startTime));
		}catch(IOException e){
			e.printStackTrace();
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Déplacement d'un fichier
		Path source2 = Paths.get("dico3.txt");
		Path cible2 = Paths.get("dico4.txt");
		try{
			Files.move(source2,  cible2, StandardCopyOption.REPLACE_EXISTING);
		} catch(IOException e) { e.printStackTrace(); }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Suppression de fichiers
		Path[] rubbish = {Paths.get("dico.txt"), Paths.get("dico4.txt") }; 
		try{
			for(Path chemin : rubbish)
				Files.delete(chemin);
		}catch(IOException e) { e.printStackTrace(); }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Ecriture d'un flux d'octets (avec newOutputStream())
		Path srce = Paths.get("testNewOutputStream.txt");
		try(OutputStream output = Files.newOutputStream(srce)) {
			String message = "J'essaye d'écrire avec Files ... \né, à, â, ê, è, ù, ï, ç ! ";
			output.write('A');
			output.write(message.getBytes());
			
		}catch(IOException e) { e.printStackTrace(); }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Lecture d'un flux d'octets (avec newInputStream() combiné à un BufferedReader)
		try(InputStream input = Files.newInputStream(srce); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
			String line = null;
			while((line = reader.readLine()) != null)
				System.out.println(line);			
		}catch(IOException e) { e.printStackTrace(); }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Ecriture bufférisée d'un fichier (avec newBufferedWriter)
		Path sourcePath = Paths.get("testNewBuffered.txt");
		String contenu = "J'essaye d'écrire avec newBufferedWriter ... \n é, à, ç, ê, â, ù";
		try(BufferedWriter writer = Files.newBufferedWriter(sourcePath, StandardCharsets.UTF_8)){
			writer.write(contenu, 0, contenu.length());
		}catch(IOException e){ e.printStackTrace(); }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Lecture bufférisée d'un fichier (avec newBufferedReader)
		//sourcePath = Paths.get("dictionnaire.txt");
		try(BufferedReader reader = Files.newBufferedReader(sourcePath, StandardCharsets.UTF_8)){
			String line = null;
			long start = System.currentTimeMillis();
			while((line = reader.readLine()) != null)
				System.out.println(line);
			System.out.println("Temps de lecture avec Files.newBufferedReader : exception levée à la lecutre" 
				+ (System.currentTimeMillis() - start));
		}catch(IOException e){ e.printStackTrace();}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Gérer les fichiers ZIP
		//Création d'un système de fichiers en fonction d'un fichier ZIP
		try(FileSystem zipFS = FileSystems.newFileSystem(Paths.get("monfichier.zip"), null)){
			//Suppression d'un fichier à l'intérieur du ZIP
			Files.deleteIfExists(zipFS.getPath("test3.txt"));
			//Création d'un fichier à l'intérieur du ZIP
			Path pathZip = zipFS.getPath("nouveau.txt");
			String message = "Hello the world !!! ";
			Files.write(pathZip,message.getBytes());
			//Parcours des éléments à l'intérieur du ZIP
			try(DirectoryStream<Path> stream = Files.newDirectoryStream(zipFS.getPath("/"))){
				for(Path entry : stream)
					System.out.println(entry);
			}
			//Copie d'un fichier du disque dur vers l'archive ZIP
			Files.copy(Paths.get("test.txt"), zipFS.getPath("fichierDansZIP.txt"));
		}catch(IOException e) { e.printStackTrace(); }
		catch(FileSystemNotFoundException e){ e.printStackTrace();}
	}
}
