/*
 * 									Lecture et �criture d'un fichier avec un channel
 * I./ Les options d'ouverture d'un fichier
 * 
 * L'�num�ration StandardOpenOption impl�mente OpenOption et d�finit les options d'ouverture standard d'un fichier:
 * Valeur   ====>    R�le
 * 
 * APPEND			-> 	Si le fichier est ouvert en �criture, alors les donn�es sont ajout�es au fichier. Cette option 
 * 						doit �tre utilis�e avec les options CREATE ou WRITE
 * CREATE			-> 	Cr�er un nouveau fichier s'il n'existe pas, sinon le fichier est ouvert
 * CREATE_NEW		-> 	Cr�er un nouveau fichier : si le fichier existe d�j�, alors une exception est lev�e
 * DELETE_ON_CLOSE	->	Supprimer le fichier lorsque son flux associ� est ferm� : cette option est utile pour des 
 * 						fichiers temporaires
 * DSYNC			->	Demander l'�criture synchronis�e des donn�es dans le syst�me de stockage sous-jacent (pas
 * 						d'utilisation des tampons du syst�me)
 * READ				->	Ouvrir le fichier en lecture
 * SPARSE			->	Indiquer au syst�me que le fichier est clairsem�, ce qui peut lui permettre de r�aliser 
 * 						certaines optimisations si l'option est support�e par le syst�me de fichiers (c'est notamment
 * 						le cas avec NTFS)
 * SYNC				->	Demander l'�criture synchronis�e des donn�es et des m�tadonn�es dans le syst�me de stockage
 * 						sous-jacent
 * TRUNCATE_EXISTING ->	Si le fichier existe et qu'il est ouvert en �criture alors il est vid�. Cette option doit
 * 						�tre utilis�e avec l'option WRITE
 * WRITE			->	Ouvre le fichier en �criture
 * 
 * Ces options sont utilisables avec toutes les m�thodes qui ouvrent des fichiers. Elles ne sont pas toutes
 * mutuellement exclusives.
 * 
 * II./ Lecture et �criture d'un fichier ave un channel
 * 1.) Mentions l�gales
 * L'API Java NIO propose de r�aliser des op�rations d'entr�es/sorties utilisant des channels et des tampons (ByteBuffer)
 * ce qui am�liore les performances par rapport � l'API Java IO.
 * Par d�faut, les flux de java.io lisent ou �crivent uniquement un octet ou un caract�re � la fois.
 * Les op�rations d'�critures/lectures de java.nio utilise un tampon (ByteBuffer). L'interface ByteBuffer propose
 * des fonctionnalit�s de base pour de telles lectures ou �critures.
 * La m�thode newByteChannel() de la classe Files renvoie une instance d'un channel NIO de type SeekableByteChannel.
 * Elle poss�de deux surcharges :
 	-	SeekableByteChannel newByteChannel(Path path, OpenOptions...options)
  	-	SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs)
  
   Ces deux surchages permettent d'ouvrir ou de cr�er un fichier et de lui associer un channel en fonction des 
   param�tres d'ouverture de type OpenOptions fournis. Par d�faut, le channel est ouvert en lecture (READ)
   Exemple :
   Path path = Paths.get("test.txt");
   try(SeekableByteChannel sbc = Files.newByteChannel(path, StandardOpenOption.WRITE, StandardOpenOption.SYNC)){
   //...
   }
   
   L'interface java.nio.channels.SeekableByteChannel ajoute � ByteChannel la possibilit� de g�rer une position
   dans le channel, de vider un channel et d'obtenir la taille du fichier associ� au channel. Cela permet de se 
   d�placer dans le channel pour r�aliser une op�ration de lecture ou d'�criture sans avoir � parcourir les donn�es
   jusqu'� la position d�sir�e. Un SeekableByteChannel est donc un channel qui poss�de des fonctionnalit�s 
   similaires � celles propos�es par la classe java.io.RandomAccessFile
   L'interface SeekableByteChannel impl�mente les interfaces : AutoCloseable, ByteChannel, Channel, Closeable, 
   ReadableByteChannel et WritableByteChannel.
   Elle propose plusieurs m�thodes pour permettre de se d�placer dans le fichier avant de r�aliser une op�ration de 
   lecture ou d'�criture
   
   2.) Les m�thodes de SeekableByteChannel
   
   M�thode					=============>	R�le
   
   long position()						->	Retourner la position courante dans le channel
   
   SeekableByteChannel position(long	->	Changer la position dans le channel
   new Position)
   
   int read(ByteBuffer dst)				->	Lire un ensemble d'octets du channel dans le tampon fourni en param�tre.
   											Retourne le nombre d'octets lu ou -1 si la fin du channel est atteinte.
   
   long size()							->	Retourner la taille en octets du flux auquel le channel est connect�
   
   SeekableByteChannel truncate(long	->	Tronquer le contenu de l'�l�ment sur lequel le channel est connect� � la
   size)									taille fournie en param�tre. Cela permet de redimensionner la taille
   											du flux associ� au channel avec la valeur fournie en param�tre
   
   int write(ByteBuffer src)			->	Ecrire les octets fournis en param�tre � la position courante dans le 
   											channel
   
   
   La m�thode read() tente une lecture pour remplir le nombre d'octets du tampon pass� en param�tre. La position 
   courante dans le channel est augment�e de la taille des donn�es lues. 
   La m�thode write() �crit les octets du tampon pass� en param�tre � partir de la position courante dans le channel.
   Si le fichier est ouvert avec l'option APPEND, alors la position courante est situ�e � la fin du fichier. Elle
   renvoie le nombre d'octets �crits. La position courante dans le channel est augment�e de la taille des donn�es 
   �crites.
   La surchage de la m�thode position() qui attend un param�tre de type long permet de d�placer la position courante
   dans le channel. Elle renvoie le channel lui-m�me pour permettre un cha�nage des appels de cette m�thode. La 
   taille du flux connact� au channel n'est pas modifi�e si la valeur fournie en param�tre est sup�rieure � sa taille
   totale. L'utilisation de cette m�thode n'est pas recommand�e avec un channel ouvert avec l'option APPEND.
   La m�thode truncate() permet de r�duire la taille totale du flux connect� au channel. Si la taille fournie en 
   param�tre est inf�rieure � la taille totale courante, alors les octets entre la taille fournie et la taille
   totale sont perdus. Si la taille fournie en param�tre est sup�rieure � la taille totale courante, alors 
   l'invocation de la m�thode n'a aucun effet. Une impl�mentation de cette interface peut interdire l'utilisation
   de cette m�thode si le channel est ouvert avec l'option APPEND.
   Exemple : r�f main
 * 
 */
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class Main_Channel {
	public static void main(String[] args){
		final ByteBuffer donneesBonjour = ByteBuffer.wrap("\nBonjour !\n".getBytes());
		final ByteBuffer donneesBonsoir = ByteBuffer.wrap("\nBonsoir !\nComment n'alliez-vous pas ?".getBytes());
		
		final Path path = Paths.get("test.txt");
		try(FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE)){
			
			
			fileChannel.write(donneesBonjour);
			fileChannel.write(donneesBonjour);
		}catch(IOException e){
			e.printStackTrace();
		}
		try(SeekableByteChannel sbc = Files.newByteChannel(path, StandardOpenOption.WRITE, StandardOpenOption.SYNC,
				StandardOpenOption.APPEND)){
			sbc.position(11); // n'a aucun effet ici � cause de APPEND
			sbc.write(donneesBonsoir);
			sbc.write(donneesBonsoir);
			sbc.write(donneesBonsoir);
			
			sbc.write(donneesBonsoir);
			sbc.write(donneesBonsoir);
			
		}catch(IOException e){
			e.printStackTrace();
		}
		try(SeekableByteChannel sbc = Files.newByteChannel(path, StandardOpenOption.READ, StandardOpenOption.SYNC)){
			int i = 0;
			String str = "";
			int size = (int)sbc.size();
			ByteBuffer bBuff = ByteBuffer.allocate(size);
			sbc.read(bBuff);
			bBuff.flip();
			byte[] bytes = bBuff.array();
			for(byte bit : bytes)
				str += (char) bit;
			System.out.println(str);
		}catch(IOException e){
			e.printStackTrace();
		}
		Path pathDico = Paths.get("dictionnaire.txt");
		Path pathCopie = Paths.get("dicoCopie.txt");
		
		try(SeekableByteChannel sbc = Files.newByteChannel(pathDico, StandardOpenOption.READ); 
			SeekableByteChannel sbc2 = Files.newByteChannel(pathCopie, StandardOpenOption.WRITE, 
				StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			
			int size = (int) sbc.size();
			ByteBuffer bBuff = ByteBuffer.allocate(size);
			long start = System.currentTimeMillis();
			sbc.read(bBuff);
			System.out.println("Temps de lecture SeekableByteChannel : " + (System.currentTimeMillis() - start));
			bBuff.flip();
			start = System.currentTimeMillis();
			sbc2.write(bBuff);
			bBuff.flip();
			System.out.println("Temps d'�criture avec SeekableByteChannel : " + (System.currentTimeMillis() - start));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Path copie2 = Paths.get("dico2.txt");
		try(FileChannel fl = FileChannel.open(pathDico, StandardOpenOption.READ);
			FileChannel fl2 = FileChannel.open(copie2, StandardOpenOption.WRITE, 
					StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
			
			int size = (int) fl.size();
			ByteBuffer bBuff = ByteBuffer.allocate(size);
			long start = System.currentTimeMillis();
			fl.read(bBuff);
			System.out.println("Temps de lecture FileChannel : " + (System.currentTimeMillis() - start));
			bBuff.flip();
			start = System.currentTimeMillis();
			fl2.write(bBuff);
			bBuff.flip();
			System.out.println("Temps d'�criture avec FileChannel : " + (System.currentTimeMillis() - start));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		Path copie3 = Paths.get("dico3.txt");
		try {
			long start = System.currentTimeMillis();
			Files.copy(pathDico, copie3, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Temps de copie avec Files.copy : " + (System.currentTimeMillis() - start));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("D'apr�s mes observations, FileChannel reste le plus performant");
		System.out.println("Nombre de processeurs : " + Runtime.getRuntime().availableProcessors());
	}
	
	
}
