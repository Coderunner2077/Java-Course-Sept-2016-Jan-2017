/*
 * 											Les flux entr�e / sortie
 * 
 * Une entr�e / sortie en Java consiste en un �change de donn�es entre le programme et une autre source, par exemple
 * la m�moire, un fichier, le programme lui-m�me... Pour r�aliser cela, Java emploie ce qu'on appelle un stream 
 * (qui signifie flux). Celui-ci joue le r�le de m�diateur entre la source de donn�es et sa destination. Java met
 * � notre disposition toute une panoplie d'objets permettant de communiquer de la sorte. Toute op�ration sur les
 * entr�e/sortie doit suivre le sch�ma suivant : ouverture, lecture, fermeture du flux.
 * Il existe une foule d'objets qui ont chacun sa fa�on de travailler avec les flux. 
 * Java a d�compos� les objets traitant les flux en deux cat�gories :
 * 		-	les objets travaillant avec des flux d'entr�e (in), pour la lecture de flux
 * 		-	les objets travaillant avec des flux de sortie (out), pour l'�criture de flux
 * 
 * I./ Utilisation de java.io
 * 1.) L'objet File
 * 
 * Je me cr�e un fichier test.txt � la racine de mon projet.
 * R�f main
 * J'instancie l'objet File de cette mani�re :
 * File file = new File("nomdufichier.extension");
 * Les m�thodes de l'objet File :
 * 		-	getAbsolutePath() : renvoie le lien absolu du fichier
 * 		-	getName() ==> nom du fichier
 * 		-	exists() ==> true/false
 * 		-	isDirectory() ==> idem
 * 		-	isFile() ==> idem
 * 		-	listRoots() ==> une liste des lecteurs du PC (les disques durs)
 * 		-	listFiles() ==> une liste des fichiers et r�pertoires contenus dans le dossier (file). Cette instruction
 * 		est susceptible de g�n�rer une NullPointerException (si l'objet appelant n'est pas un dossier ou n'a pas de 
 *		sous-fichiers)
 *		-	delete() : efface le fichier d�finitivement 
 *		-	mkdir() : cr�e un r�pertoire (le nom donn� � ce r�pertoire ne pourra cependant pas contenir de ".")
 * 		
 * 2.) Les objets FileInputStream et FileOutputStream
 * 
 * C'est par le biais des objets FileInputStream et FileOutputStream que l'on va pouvoir :
 * 		-	lire dans un fichier
 * 		-	�crire dans un fichier
 * 
 * Ces classes h�ritent des classes abstraites InputStream et OutputStream, pr�sentes dans le package java.io
 * Il existe une hi�rarchie des classes pour les traitements in et une autre pour les traitements out.
 * Attention : ce sont les classes h�ritant d'InputStream qui sont destin�es � la lecture, et celles h�ritant 
 * d'OutputStream qui sont charg�es de l'�criture, et non l'inverse !
 * ==> il fo se placer par rapport au programme : lorsque ce dernier lit des info dans un fichier, ce sont des
 * info qu'il re�oit, et par cons�quent, elles s'apparentent � une entr�e (in).
 * ==>	Au contraire, lorsqu'il va �crire dans un fichier, ce sont des info qu'il fait sortir : donc pour lui ce
 * flux de donn�es correspond � une sortie (out).
 * 
 * Je vais maintenant travailler avec mon fichier : le but est de lire le contenu du fichier et de le copier dans
 * un autre, dont je sp�cifierai le nom dans mon programme :
 * R�f main 407-433
 * Le bloc finally permet de s'assurer que mes objets ont bien ferm� leurs liens avec leurs fichiers respectifs,
 * ceci afin de permettre � Java de d�truire ces objets pour ainsi lib�rer un peu de m�moire � mon ordi.
 * 
 * Les objets FileInputStream et FileOutputStream sont assez rudimentaires, car ils travaillent avec un nombre
 * d�termin� d'octets � lire. Cela explique pourquoi la condition de la boucle est aussi tordue...
 * Au d�but, seuls les 127 premiers caract�res de la table ASCII �taient cod�s (UNICODE 1), correspondant aux
 * caract�res se trouvant dans la langue anglaise. Mais ce codage s'est rapidement av�r� trop limit� pour des
 * langues comportant des caract�res accentu�s. Un jeu de codage de caract�res �tendu a donc �t� mis en place
 * afin de pallier ce probl�me.
 * Chaque code binaire Unicode 1 est cod� sur 8 bits, soit 1 octet. Une variable de type byte, en Java correspond
 * � 1 octet et non un bit. 
 * Les objets que je viens d'utiliser emploient la 1re version d'UNICODE 1 qui ne 
 * comprend pas les caract�res accentu�s, c'est pourquoi ces caract�res ont un code d�cimal n�gatif dans mon
 * fichier. Lorsque je d�finis un tableau de byte � 8 entr�es, cela signifie que je vais lire 8 octets � la fois.
 * 
 * Ainsi, les traitements des flux suivent une logique et une syntaxe pr�cises. Lorsque j'ai copi� un fichier,
 * j'ai r�cup�r� un certain nombre d'octets dans un flux entrant que j'ai pass� � un flux sortant. A chaque tour
 * de boucle, les donn�es lues dans le fichier source sont �crites dans le fichier d�fini comme copie.
 * 
 * Il existe � pr�sent des objets bcp plus faciles � utiliser, mais qui travaillent n�anmoins avec les deux objets
 * que je viens d'�tudier. Ces objets font �galement partie de la hi�rarchie cit�e pr�c�demment. Seulement, il
 * existe une superclasse qui les d�finit.
 * 
 * 3.) Objets FilterInputStream et FilterOutputStream
 * 
 * Ces deux classes sont en fait des classes abstraites. Elles d�finissent un comportement global pour leurs 
 * classes filles qui, elles, permettent d'ajouter des fonctionnalit�s aux flux d'entr�e/sortie.
 * Voir figure r�f diagramme.png
 * On peut voir qu'il existe quatre classes filles h�ritant de FilterInputStream (de m�me pour FilterOutputStream
 * => les classes d�rivant de celle-ci ont les m�mes fonctionnalit�s, mais en �criture) :
 * 		-	DataInputStream : offre la possibilit� de lire directement des types primitifs (double, char, int) 
 * 		gr�ce � des m�thodes comme readDouble(), readInt()...
 * 		-	BufferedInputStream : cette classe permet d'avoir un tampon � disposition dans la lecture de flux. En
 * 		gros, les donn�es vont tout d'abord remplir le tampon, et d�s que celui-ci est plein, le prog acc�de aux
 * 		donn�es
 * 		-	PushbackInputStream : permet de remettre un octet d�j� lu dans le flux entrant
 * 		-	LinebackInputStream : cette classe offre la possibilit� de r�cup�rer le num�ro de la ligne lue � un
 * 		instant T
 * Ces classes prennent en param�tre une instance d�rivant des classes InputStream (pour les classes h�ritant de
 * FilterInputStream) ou de OutputStream (pour les classes h�ritant de FilterOutputStream).
 * Puisque ces classes acceptent une instance de leur superclasse en param�tre, on peut cumuler les filtres et
 * obtenir des choses de ce genre :
 * FileInputStream fis = new FileInputStream(new File("test.txt"));
 * DataInputStream dis = new DataInputStream(fis);
 * BufferedInputStream bis = new BufferedInputStream(dis);
 	//ou en condens� :
 * BufferedInputStream bis = new BufferedInputStream(
 * 								new DataInputStream(
 * 									new FileInputStream(
 * 										new File("test.txt))));
 * Testons et comparons maintenant le temps d'ex�cution d'un long fichier texte avec la m�thode conventionnelle 
 * vue pr�c�demment, puis avec un buffer. 
 * R�f console : la diff�rence est vraiment �norme 1,743 secondes avec la 1re m�thode et 0,035 secondes pour la 
 * seconde ==> l'utilisation d'un buffer permet une nette am�lioration des performances de mon code.
 * A pr�sent : le test pour l'�criture. R�f console : la diff�rence est encore plus nette (pour lecture + �criture)
 * => 4,86 secondes avec FileInputStream et FileOutputStream
 * 	  0,061 secondes avec BufferedInputStream et BufferedOutputStream
 * 
 * Je vais rapidement aborder les objets Data(Input/Output)Stream ==> ils s'utilisent comme les objets 
 * BufferedInputStream, sauf qu'ils ont des m�thodes de lecture pour chaque type primitif. IL faut donc que le 
 * fichier soit g�n�r� par la biais d'un DataOutputStream pour que les m�thodes fonctionnent correctement.
 * R�f main 601-634
 * J'ai bien vu que ce type d'objet ne manque pas de fonctionnalit�s
 * Remarque : ces caract�res �crits dans le fichier avec DataOutputStream sont, comment dirais-je, asiatiques (m�me 
 * les chiffres... ) !
 * 
 * Jusqu'ici, je n'ai travaill� qu'avec des types primitifs, mais il est �galement possible de travailler avec
 * des objets !
 * 
 * 4.) Les objets ObjectInputStream et ObjectOutputStream
 * 
 * Il faut savoir que lorsqu'on veut �crire des objets dans des fichiers, cela s'appelle la "s�rialisation" : c'est
 * le nom que porte l'action de sauvegarder des objets. Voyons d'abord comment s�rialiser un objet de ma 
 * composition.
 * R�f Game : cette classe impl�mente l'interface Serializable (qui n'a aucune m�thode � red�finir ==> cette 
 * interface est une "interface marqueur"). Rien qu'en impl�mentant cette interface dans un objet, Java sait
 * que cet objet peut �tre s�rialis�. Attention, si je n'impl�mente pas cette interface dans mes objets, ceux-ci
 * ne pourront pas �tre s�rialis�s. En revanche, si une superclasse impl�mente Serializable, ses enfants seront
 * consid�r�s comme s�rialisables.
 * Voici ce que je fais :
 * 		-	je cr�e deux ou trois objets Game
 * 		-	je les s�rialise dans un fichier de mon choix
 * 		-	ensuite, je les d�s�rialise afin de pouvoir les r�utiliser
 * 
 * Attention, la d�s�rialisation d'un objet peut engendrer une ClassNotFoundException, il faut
 * penser � la capturer
 * Remarque : encore des caract�res bizarres dans le fichier !!
 * En effet, les donn�es de mes objets sont enregistr�s dans le fichier. Mais que ce passerait-il
 * si mon objet G avait un autre objet de ma composition en son sein ? ==> je cr�e la classe Notice,
 * et j'impl�mente une notice par d�faut dans mon objet G. R�f G.
 * Et lorsque j'ex�cute le prog ==> mon code ne compile plus, et c'est normal, puisque mon objet
 * Notice n'est pas s�rialisable, une erreur de compilation est donc lev�e. 
 * Maintenant, deux choix s'offrent � moi :
 * 		-	soit je fais en sorte de rendre mon objet s�rialisable
 * 		-	soit je sp�cifie dans ma classe G que la variable notice n'a pas � �tre s�rialis�e.
 * Pour la 1re option, j'impl�mente tout simplement l'interface Serializable dans ma classe Notice.
 * Pour la seconde, il suffit de d�clarer ma variable transient. R�f G 5.
 * Attention, dans le second cas, � l'invocation de la m�thode toString() sur les objets d�s�rialis�s, 
 * je ne verrais pas les �ventuelles r�f�rences aux variables transcient figurant dans ladite m�thode, car
 * la machine virtuelle les a tout bonnement ignor�es lors de la s�rialisation.
 * 
 *  
 * 5.) Les objets CharArray(Writer/Reader) et String(Writer/Reader)
 * 
 * Ces deux types jouent quasiment le m�me r�le : �crire et lire un flux de texte dans un tampon de m�moire. De plus,
 * ils ont les m�mes m�thodes que leur classe m�re. Ces deux objets n'ajoutent donc aucune fonctionnalit� � leur 
 * objet m�re !
 * Leur principale fonction est de permettre d'�crire un flux de caract�res dans un buffer adaptatif:
 * un emplacement en m�moire qui peut changer de taille selon les besoins (par ailleurs il existe
 * des classes remplissant le m�me r�le que ces classes-ci : ByteArray(Input/Output)Stream).
 * Les objets String(Writer/Reader) fonctionnent de la m�me mani�re que CharArray(Writer/Reader). En fait, il s'agit
 * du m�me code, mais avec des objets diff�rents.
 * R�f main 700-743
 * 
 * Voyons maintenant comment traiter les fichiers de texte avec des flux de caract�res
 * 
 * 6.) Les classes File(Write/Reader) et Print(Writer/Reader)
 * 
 * Les objets �tudi�es pr�c�demment traiteront mon fichier de la m�me fa�on que s'il contenait des donn�es binaires.
 * Ces deux objets, pr�sents dans le package java.io, servent � lire et �crire des donn�es dans un fichier texte.
 * R�f main 745-800
 * Remarque : PrintReader semble ne pas exister
 * 
 * Depuis le JDK 1.4, le package java.nio a vu le jour ==> les performances des flux, buffers, trait�s par 
 * java.io sont nettement am�lior�es.
 * 
 * II./ L'utilisation de java.nio
 * 
 * 1.) FileChannel
 * 
 * nio signifie "New I/O". Ce package a �t� cr�� afin d'am�liorer les tratements des fichiers, du r�seau et des
 * buffers. Il permet de lire les donn�es (je m'int�ressarai uniquement � l'aspect fichier) d'une fa�on diff�rente.
 * Tandis que les objets du package java.io traitaient les donn�es par octets, les objets du package java.nio les
 * traitent par blocs de donn�es : la lecture est donc acc�l�r�e.
 * Tout repose sur deux objets de ce nouveau package : les channels et les buffers. Les channels sont en fait des
 * flux, tout comme dans l'ancien package, mais ils sont amen�s � travailler avec un buffer dont je d�finis la taille.
 * Pour simplifier au maximum, lorsque j'ouvre un flux vers un fichier avec un objet FileInputStream, je peux 
 * r�cup�rer un canal vers ce fichier. Celui-ci, combin� � un buffer, me permet de lire mon fichier encore plus vite
 * qu'avec un BufferedInputStream. 
 * R�f main 813-847 comparaison de lecture : buffer conventionnel vs nouveau buffer
 * ==> 0,03 secondes avec le buffer conventionnel, et 0,009 secondes avec le nouveau buffer
 * Ce nouveau package est le plus souvent utilis� pour traiter les flux sur les r�seaux. Ce package offre un buffer
 * par type primitif pour la lecture sur le channel. Voici ces classes :
 * 		-	IntBuffer
 * 		-	CharBuffer
 * 		-	ShortBuffer
 * 		-	ByteBuffer
 * 		-	DoubleBuffer
 * 		-	FloatBuffer
 * 		-	LongBuffer
 * Il faudra penser � les importer en cas d'utilisation dans java.nio
 * 
 * 2.) Une fermeture des flux moins compliqu�e
 * 
 * Avec l'arriv�e de Java 7, qq nouveaut�s ont vu le jour pour la gestion des exceptions sur les flux. Contrairement
 * � la gestion de la m�moire (mes variables, mes classes, etc.) qui est d�l�gu�e au garbage collector (ramasse
 * miette), plusieurs types de ressources doivent �tre g�r�s manuellement. Les flux sur des fichiers en font partie,
 * et, d'un point de vue plus g�n�ral, toutes les ressources que je dois fermer manuellement(les flux r�seaux, 
 * les connexions � une base de donn�es...). Pour ce genre de flux, j'ai vu qu'il fo d�clarer une variable en dehors
 * du bloc try/catch afin qu'elle soit accessible dans les autres blocs d'instruction, notamment le bloc finally.
 * Java 7 initie ce qu'on appelle vulgairement le "try-with-resources". Ceci me permet de d�clarer mes ressources
 * utilis�es directement dans le bloc try(...), ces derni�res seront automatiquement ferm�es � la fin du bloc
 * d'instructions ! Ainsi, r�f main 873, je n'ai plus � me soucier de la fermeture avec le bloc finally.
 * Note : les diff�rentes ressources utilis�es sont s�par�es par un ";" dans le bloc try 
 * Cependant, il faut prendre quelques pr�cautions, notamment pour ce genre de d�claration :
 * try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream("test.txt"))){...}
 * Le fait d'avoir des ressources encapsul�es ne rend pas "visible" les ressources encapsul�es. Dans ce cas-l�, si
 * une exception est lev�e, le flux correspondant � FileInputStream ne sera pas ferm�. Pour pallier ce probl�me, il
 * suffit de bien d�couper toutes les ressources � utiliser, comme ceci :
 * try(FileInputStream fis = new FileInputStream("test.txt"); ObjectInputStream ois = new ObjectInputStream(fis)){
 * ...}
 * MAIIS, o� est pass� le File, que je mettais d'habitude dans l'instanciation de FileInputStream ?!!
 * ==> en fait, il y a une restriction sur ce mode de fonctionnement. Pour rendre la fermeture possible, les 
 * d�veloppeurs de la plateforme Java 7 ont cr�� une nouvelle interface : java.lang.AutoCloseable. Seuls les 
 * objets impl�mentant cette interface peuvent �tre utilis�s de la sorte ! Et la classe File n'en fait pas parti.
 * 
 * III./ Depuis Java 7 : nio II
 * 
 * 1.) introduction au nio II
 * L'une des grandes nouveaut�s de Java 7 r�side dans NIO 2.0 avec un nouveau package java.nio.file en remplacement
 * de la classe java.io.File
 * Voici un bref listing de quelques nouveaut�s:
 * 		-	une meilleure gestion des exceptions : la plupart des m�thodes de la classe File se contentent de 
 * 		renvoyer une valeur nulle en cas de probl�me, avec ce nouveau package, des exceptions seront lev�es afin de
 * 		mieux cibler la cause du (ou des) probl�me(s)
 * 		-	un acc�s complet au syst�me de fichiers (support des liens/liens symboliques, etc.)
 * 		-	l'ajout de m�thodes utilitaires tels que le d�placement/copie de fichier, la lecture/�criture binaire ou
 * 		texte...
 * 		-	r�cup�rer la liste des fichiers d'un r�pertoire via un flux
 * 		-	remplacement de la classe java.io.File par java.nio.file.Path
 * 
 * Afin d'�tre le plus souple et complet possible, les d�veloppeurs de la plateforme ont cr�� une interface
 * java.nio.file.Path dont le r�le est de r�cup�rer et de manipuler des chemins de fichiers de dossier et
 * une classe java.nio.file.Files qui contient tout un tas de m�thodes qui simplifient certaines actions (copie,
 * d�placement...) et permet aussi de r�cup�rer tout un tas d'informations sur un chemin.
 * R�f main 892
 * Remarque : java.nio.file.Paths � importer �galement (en plus du ..Path)
 * La classe Files permet �galement de lister le contenu d'un r�pertoire mais via un objet DirectoryStream qui
 * est un it�rateur. Cela �vite de charger tous les fichiers en m�moire pour r�cup�rer leurs informations. 
 * R�f main 900
 * Note : j'ai �galement la possibilit� de rajouter un filtre � mon listing de r�pertoire afin qu'il ne liste que
 * certains fichiers, comme ceci :
 * try(DirectoryStream<Path> listing = Files.newDirectoryStream(chemin, "*.txt")){...} => ne prendra en compte
 * que les fichiers ayant l'extension ".txt"
 * 
 * Plus loin, quelques m�thodes int�ressantes...
 * 
 * 2.) La copie du fichier
 * C'est aussi simple que �a :
 * Path cible = Paths.get("test.txt");
 * Path source = Paths.get("test2.txt");
 * try{
 * 		Files.copy(source, cible, StandardCopyOption.REPLACE_EXISTING);
 * }catch(IOException e){
 * 		e.printStackTrace();
 * }
 * Le troisi�me argument permet de sp�cifier les options de copie. Voici celles qui sont disponibles : 
 * 		-	StandardCopyOption.REPLACE_EXISTING : remplace le fichier cible m�me s'il existe d�j�
 * 		-	StandardCopyOption.COPY_ATTRIBUTES : copie les attributs du fichier source sur le fichier cible (droits
 * 		en lecture, etc.)
 * 		-	StandardCopyOption.ATOMIC_MOVE : copie atomique
 * 		-	LinkOption.NOFOLLOW_LINKS : ne prendra pas en compte les liens
 * 
 * 3.) Le d�placement de fichier et autres m�thodes
 * 
 * Pour d�placer le fichier "test2.txt" vers un fichier "test3.txt", il suffit de faire :
 * Path source = Paths.get("test2.txt");
 * Path cible = Paths.get("test3.txt");
 * try{
 * 		Files.move(source, cible, StandardCopyOption.REPLACE_EXISTING);
 * }catch(IOException e) { e.printStackTrace();}
 * 
 * Remarque : il va de soi que le fichier "test2.txt" sera effac� � la fin de l'instruction
 * 
 * Dans le m�me genre, j'ai aussi :
 * 		-	une m�thode Files.delete(path) qui supprime d�finitivement un fichier
 * 		-	une m�thode Files.createFile(path) qui permet de cr�er un fichier vide.
 * 4.) Ouvrir des flux
 * 
 * La classe Files propose plusieurs m�thodes pour faciliter la lecture ou l'�criture de fichiers et de flux
 * selon les besoins allant des plus simples aux plus complexes :
 * Besoins simples<-------------------------------------------------------------------------------->Besoins complexes
 * 					readAllBytes()		newBufferedReader()		newInputStream()	newByteChannel()
 * 					readAllLines()		newBufferedWriter()		newOutputStream()		  ||
 * 						||					   ||					  ||			  Channel et 
 * 					Lecture int�g-		  Fichier texte			Flux sans buffer	  ByteBuffer
 *					 ralit� du
 * 					  fichier
 * 
 * Les m�thodes readAllBytes() et readAllLines() permettent de lire l'int�gralit� du contenu d'un fichier
 * respectivement d'octets et et texte. Deux surchages de la m�thode write() permettent d'�crire l'int�gralit�
 * d'un fichier. Ces m�thodes sont � r�server pour de petits fichier.
 * Les m�thodes newBufferedReader() et newBufferedWriter() sont des helpers pour faciliter la cr�ation d'objet 
 * de types BufferedReader et BufferedWriter permettant la lecture et l'�criture de fichiers de type texte en
 * utilisant un tampon.
 * Les m�thodes newInputStream() et newOutputStream() sont des helpers pour faciliter la cr�ation d'objets permettant
 * la lecture et l'�criture de fichiers d'octets.
 * Ces quatre m�thodes sont des helpers pour cr�er des objets du package java.io
 * La m�thode newByteChannel() est un helper pour cr�er un objet de type SeekableByteChannel.
 * La classe FileChannel propose des fonctionnalit�s avanc�es sur l'utilisation d'un fichier (verrous, mapping 
 * direct � une zone de m�moire, ...) : cette classe a �t� enrichie pour fonctionner avec NIO2
 * R�f main 942-972 : �criture/lecture de fichier sans et avec buffer
 * 
 * 5.) G�rer les fichiers Zip
 * 
 * R�f main 977-990
 * 
 * 6.) Autres objets utiles
 * 
 * Il est �galement possible d'�tre averti via l'objet WatchService lorsqu'un fichier est modifi�, de g�rer les 
 * entr�es/sorties asynchrones via les objets AsynchronousFileChannel, AsynchronousServerSocketChannel. Ceci 
 * permet de faire les actions en t�che de fond sans bloquer le code pendant l'ex�cution. Il est aussi possible
 * d'avoir acc�s aux attributs gr�ce � 6 vues permettant de voir plus ou moins d'informations, � savoir :
 * 		-	BasicFileAttributeView : permet un acc�s aux propri�t�s g�n�ralement communes � tous les syst�mes de 
 * 		fichiers
 * 		-	DosFileAttributeView : ajoute le support des attributs MS-DOS (readonly, hidden, system, archive) � 
 * 		l'objet ci-dessus
 * 		-	PosixFileAttributeView : permet de manipuler le propri�taire du fichier
 * 		-	AclFileAttributeView : permet de manipuler les droits d'acc�s au fichier
 * 		-	UserDefinedFileAttributeView : permet de d�finir des attributs personnalis�s
 * 
 */
// Package � importer afin d'utiliser l'objet File
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
		// Cr�ation de l'objet File
		File f = new File("test.txt");
		System.out.println("Chemin absolu du fichier :" + f.getAbsolutePath());
		System.out.println("Nom du fichier : "+ f.getName());
		System.out.println("Est-ce qu'il existe : " + f.exists());
		System.out.println("Est-ce un repertoire : "+ f.isDirectory());
		System.out.println("Est-ce un fichier : "+ f.isFile());
		
		System.out.println("Affichage des lecteurs � la racine du PC : ");
		for(File file : f.listRoots())
		{
			System.out.println(file.getAbsolutePath());
			try{
				int i = 1;
				// On parcourt la liste des fichiers et r�pertoires
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
				//L'instruction peut g�n�rer Une NullPointerException s'il n'y a pas de sous-fichiers
			}
		}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//File(Input/Output)Stream
		
		//Je d�clare mes objets en dehors du bloc try/catch
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try{
			//j'instancie mes objets
			//fis va lire le fichier
			//fos va �crire dans le nouveau !
			fis = new FileInputStream(new File("test.txt"));
			fos = new FileOutputStream(new File("test2.txt"));
			//Je cr�e un tableau de byte pour indiquer le nombre de bytes lus � chaque tour de boucle
			byte[] buf = new byte[8];
			//on cr�e une variable de type int afin d'y affecter le r�sultat de la lecture ==> -1 quand fini 
			int n = 0;
			// Tant que l'affectation dans la variable est possible, on boucle.
			//Lorsque la lecture du fichier est termin�e, l'affectation n'est plus possible ==> on sort de la boucle
			while((n = fis.read(buf)) >= 0){
				//j'�cris dans mon deuxi�me fichier avec l'objet ad�quat
				fos.write(buf);
				//j'affiche ce qu'a lu ma boucle au format byte et au format char
				for(byte bit : buf){
					System.out.print("\t"+ bit +"(" + (char) bit + ")");
				}
				System.out.println("");
				//Je r�utiliserai le buffer � vide, au cas o� les derniers byte lus ne soient pas un multiple de 8
				//Ceci permet d'avoir un buffer vierge � chaque lecture et ne pas avoir un doublon en fin de 
				//fichier
				buf = new byte[8];
			}
			System.out.println("Copie termin�e");
		}catch (FileNotFoundException e){
			//Cette exception est lev�e si FileInputStream ne trouve aucun fichier
			e.printStackTrace();
		} catch (IOException e){
			//Celle-ci se produit lors d'une erreur d'�criture ou de lecture
			e.printStackTrace();
		}finally{
			//Je ferme mes flux de donn�es dans un bloc finally pour m'assurer que ces instructions seront 
			//ex�cut�es dans tous les cas (m�me si une exception est lev�e)
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
		
		// test des deux m�thodes
		FileInputStream fIs = null;
		BufferedInputStream bis = null;
		try{
			fIs = new FileInputStream(new File("dictionnaire.txt"));
			bis = new BufferedInputStream(new FileInputStream(new File("dictionnaire.txt")));
			byte[] buf = new byte[8];
			//on r�cup�re le temps du syst�me
			long startTime = System.currentTimeMillis();
			//inutile d'effectuer des traitements dans ma boucle
			while(fIs.read(buf) != -1);
			//on affiche le temps d'ex�cution
			System.out.println("Temps de lecture avec FileInputStream : " + 
			(System.currentTimeMillis() - startTime));
			// on r�initialise
			startTime = System.currentTimeMillis();
			buf = new byte[8];
			// je boucle avec l'autre m�thode
			while(bis.read(buf) != -1);
			//on r�affiche
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
		//Comparaison lecture + �criture : File(Input/Output)Stream vs Buffered(Input/Output)Stream
		
		// test lecture + �criture pour les m�thodes FileInputStream et BufferedInputStream (+ les output respectifs)
		
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
			System.out.println("Temps de lecture + �criture avec FileInputStream et FileOutputStream : " +
				(System.currentTimeMillis() - startTime));
			// je r�initialise
			startTime = System.currentTimeMillis();
			buf = new byte[8];
			// je fais le test avec le buffer
			while(bis.read(buf) != -1)
				bos.write(buf);
			System.out.println("Temps de lecture + �criture avec le buffer : " +
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
		// je d�clare les objets en dehors du bloc try/catch
		DataInputStream dis = null;
		DataOutputStream dos = null;
		try{
			//je cr�e D'ABORD mon fichier par le biais du DataOutputStream
			dos = new DataOutputStream(
					new BufferedOutputStream(
						new FileOutputStream(
							new File("chintok.txt"))));
			// je vais �crire chaque type primitif
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
			// je r�cup�re maintenant les donn�es
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
			// attention : le type de donn�e lue doit correspondre � celui attendu par chaque m�thode
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
			// je vais �crire mes objets Game dans le fichier
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
			// on r�cup�re maintenant les donn�es
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
		//CharArray(Writer/Reader) pour �crire et lire dans le buffer
		CharArrayWriter caw = new CharArrayWriter();
		CharArrayReader car;
		try{
			caw.write("Hello le monde !");
			//appel � la m�thode toString de mon objet de mani�re tacite
			System.out.println(caw);
			//caw.close() n'a aucun effet sur le flux
			// Seul caw.reset() peut tout effacer
			caw.close();
			
			//on passe un tableau de caract�res � l'objet qui va lire le tampon
			car = new CharArrayReader(caw.toCharArray());
			int i;
			//On remet tous les caract�res lus dans un String
			String str = "";
			while((i = car.read()) != -1) //les caract�res lus sont donc renvoy�s dans i
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
			//Appel � la m�thode toString() de mon objet de mani�re tacite
			System.out.println(sw);
			sw.close();
			//on passe un tableau de caract�res � l'objet qui va lire le tampon
			sr = new StringReader(sw.toString());
			int i = 0;
			//on remet tous les caract�res lus dans un String
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
			//cr�ation de l'objet
			fw = new FileWriter(file);
			String str = "Voici une ligne de texte";
			str += "\nVoici une autre ligne de texte. Cet �t� la p�te, cha�ne, accents";
			// on �crit la cha�ne dans le fichier
			fw.write(str);
			//on ferme le flux
			try{
				if(fw != null)
					fw.close();
			}catch(IOException e){
				e.printStackTrace();
			}
			//cr�ation de l'objet de lecture
			fr = new FileReader(file);
			int i = 0;
			str = new String();
			//lecture de donn�es
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
			String str = "J'essaie d'�crire avec PrintWriter";
			str += "\nDieu sait si j'ai r�ussi... accents : �, �, �, �, �, �, �, �";
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
			//cr�ation des objets
			fis = new FileInputStream(new File("dictionnaire.txt"));
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(new FileOutputStream(new File("dicoBuffer.txt")));
			byte[] buf = new byte[8];
			//D�marrage du chrono
			long startTime = System.currentTimeMillis();
			//lecture avec le buffer conventionnel
			while(bis.read(buf) != -1) 
				bos.write(buf);
			//Temps d'ex�cution
			System.out.println("Temps d'ex�cution (lecture + �criture) avec un buffer conventionnel : " +
			(System.currentTimeMillis() - startTime));
			bis.close();
			fis.close();
			//cr�ation d'un nouveau flux de fichier
			fis = new FileInputStream(new File("dictionnaire.txt"));
			//on r�cup�re le canal
			fc = fis.getChannel();
			// on en d�duit la taille
			int size = (int)fc.size();
			//on cr�e un buffer correspondant � la taille du fichier
			ByteBuffer bBuff = ByteBuffer.allocate(size);
			//d�marrage du chrono
			startTime = System.currentTimeMillis();
			//D�marrage de la lecture
			fc.read(bBuff);
			// on pr�pare � la lecture avec l'appel � flip
			bBuff.flip();
			//affichage du temps d'ex�cution
			System.out.println("Temps d'ex�cution avec le nouveau buffer : " +
			(System.currentTimeMillis() - startTime));
			/*Puisque j'ai utilis� un buffer de byte afin de r�cup�rer les donn�es
			 * On peut utiliser un tableau de byte
			 * La m�thode array retourne un tableau de byte
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
			System.out.println("Temps d'�criture avec le nouveau buffer : " +
					(System.currentTimeMillis() - startTime));
			
			// Remarque : apr�s observation, le r�sultat d'�criture de FileChannel est plus pr�cis et moins al�atoire qu'avec fos et 
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
		// Cr�ation de l'objet Path
		Path path = Paths.get("test.txt");
		System.out.println("Chemin absolu du fichier :" + path.toAbsolutePath());
		System.out.println("Nom du fichier : "+ path.getFileName());
		System.out.println("Est-ce qu'il existe : " + Files.exists(path));
		System.out.println("Est-ce un repertoire : "+ Files.isDirectory(path));
		// on r�cup�re maintenant la liste de r�pertoires dans une collection typ�e
		// via l'objet FileSystem qui repr�sente le syst�me de fichiers de l'OS h�bergeant la JVM
		Iterable<Path> roots = FileSystems.getDefault().getRootDirectories();
		//maintenant, il ne me reste plus qu'� parcourir
		for(Path chemin : roots){
			System.out.println(chemin);
			//pour lister un r�pertoire, il faut utiliser l'objet DirectoryStream
			//l'objet Files permet de cr�er ce type d'objet afin de pouvoir l'utiliser
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
		//D�placement d'un fichier
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
			String message = "J'essaye d'�crire avec Files ... \n�, �, �, �, �, �, �, � ! ";
			output.write('A');
			output.write(message.getBytes());
			
		}catch(IOException e) { e.printStackTrace(); }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Lecture d'un flux d'octets (avec newInputStream() combin� � un BufferedReader)
		try(InputStream input = Files.newInputStream(srce); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
			String line = null;
			while((line = reader.readLine()) != null)
				System.out.println(line);			
		}catch(IOException e) { e.printStackTrace(); }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Ecriture buff�ris�e d'un fichier (avec newBufferedWriter)
		Path sourcePath = Paths.get("testNewBuffered.txt");
		String contenu = "J'essaye d'�crire avec newBufferedWriter ... \n �, �, �, �, �, �";
		try(BufferedWriter writer = Files.newBufferedWriter(sourcePath, StandardCharsets.UTF_8)){
			writer.write(contenu, 0, contenu.length());
		}catch(IOException e){ e.printStackTrace(); }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Lecture buff�ris�e d'un fichier (avec newBufferedReader)
		//sourcePath = Paths.get("dictionnaire.txt");
		try(BufferedReader reader = Files.newBufferedReader(sourcePath, StandardCharsets.UTF_8)){
			String line = null;
			long start = System.currentTimeMillis();
			while((line = reader.readLine()) != null)
				System.out.println(line);
			System.out.println("Temps de lecture avec Files.newBufferedReader : exception lev�e � la lecutre" 
				+ (System.currentTimeMillis() - start));
		}catch(IOException e){ e.printStackTrace();}
		
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// G�rer les fichiers ZIP
		//Cr�ation d'un syst�me de fichiers en fonction d'un fichier ZIP
		try(FileSystem zipFS = FileSystems.newFileSystem(Paths.get("monfichier.zip"), null)){
			//Suppression d'un fichier � l'int�rieur du ZIP
			Files.deleteIfExists(zipFS.getPath("test3.txt"));
			//Cr�ation d'un fichier � l'int�rieur du ZIP
			Path pathZip = zipFS.getPath("nouveau.txt");
			String message = "Hello the world !!! ";
			Files.write(pathZip,message.getBytes());
			//Parcours des �l�ments � l'int�rieur du ZIP
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
