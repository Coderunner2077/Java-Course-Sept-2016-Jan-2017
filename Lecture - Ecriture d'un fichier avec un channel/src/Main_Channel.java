/*
 * 									Lecture et écriture d'un fichier avec un channel
 * I./ Les options d'ouverture d'un fichier
 * 
 * L'énumération StandardOpenOption implémente OpenOption et définit les options d'ouverture standard d'un fichier:
 * Valeur   ====>    Rôle
 * 
 * APPEND			-> 	Si le fichier est ouvert en écriture, alors les données sont ajoutées au fichier. Cette option 
 * 						doit être utilisée avec les options CREATE ou WRITE
 * CREATE			-> 	Créer un nouveau fichier s'il n'existe pas, sinon le fichier est ouvert
 * CREATE_NEW		-> 	Créer un nouveau fichier : si le fichier existe déjà, alors une exception est levée
 * DELETE_ON_CLOSE	->	Supprimer le fichier lorsque son flux associé est fermé : cette option est utile pour des 
 * 						fichiers temporaires
 * DSYNC			->	Demander l'écriture synchronisée des données dans le système de stockage sous-jacent (pas
 * 						d'utilisation des tampons du système)
 * READ				->	Ouvrir le fichier en lecture
 * SPARSE			->	Indiquer au système que le fichier est clairsemé, ce qui peut lui permettre de réaliser 
 * 						certaines optimisations si l'option est supportée par le système de fichiers (c'est notamment
 * 						le cas avec NTFS)
 * SYNC				->	Demander l'écriture synchronisée des données et des métadonnées dans le système de stockage
 * 						sous-jacent
 * TRUNCATE_EXISTING ->	Si le fichier existe et qu'il est ouvert en écriture alors il est vidé. Cette option doit
 * 						être utilisée avec l'option WRITE
 * WRITE			->	Ouvre le fichier en écriture
 * 
 * Ces options sont utilisables avec toutes les méthodes qui ouvrent des fichiers. Elles ne sont pas toutes
 * mutuellement exclusives.
 * 
 * II./ Lecture et écriture d'un fichier ave un channel
 * 1.) Mentions légales
 * L'API Java NIO propose de réaliser des opérations d'entrées/sorties utilisant des channels et des tampons (ByteBuffer)
 * ce qui améliore les performances par rapport à l'API Java IO.
 * Par défaut, les flux de java.io lisent ou écrivent uniquement un octet ou un caractère à la fois.
 * Les opérations d'écritures/lectures de java.nio utilise un tampon (ByteBuffer). L'interface ByteBuffer propose
 * des fonctionnalités de base pour de telles lectures ou écritures.
 * La méthode newByteChannel() de la classe Files renvoie une instance d'un channel NIO de type SeekableByteChannel.
 * Elle possède deux surcharges :
 	-	SeekableByteChannel newByteChannel(Path path, OpenOptions...options)
  	-	SeekableByteChannel newByteChannel(Path path, Set<? extends OpenOption> options, FileAttribute<?>... attrs)
  
   Ces deux surchages permettent d'ouvrir ou de créer un fichier et de lui associer un channel en fonction des 
   paramètres d'ouverture de type OpenOptions fournis. Par défaut, le channel est ouvert en lecture (READ)
   Exemple :
   Path path = Paths.get("test.txt");
   try(SeekableByteChannel sbc = Files.newByteChannel(path, StandardOpenOption.WRITE, StandardOpenOption.SYNC)){
   //...
   }
   
   L'interface java.nio.channels.SeekableByteChannel ajoute à ByteChannel la possibilité de gérer une position
   dans le channel, de vider un channel et d'obtenir la taille du fichier associé au channel. Cela permet de se 
   déplacer dans le channel pour réaliser une opération de lecture ou d'écriture sans avoir à parcourir les données
   jusqu'à la position désirée. Un SeekableByteChannel est donc un channel qui possède des fonctionnalités 
   similaires à celles proposées par la classe java.io.RandomAccessFile
   L'interface SeekableByteChannel implémente les interfaces : AutoCloseable, ByteChannel, Channel, Closeable, 
   ReadableByteChannel et WritableByteChannel.
   Elle propose plusieurs méthodes pour permettre de se déplacer dans le fichier avant de réaliser une opération de 
   lecture ou d'écriture
   
   2.) Les méthodes de SeekableByteChannel
   
   Méthode					=============>	Rôle
   
   long position()						->	Retourner la position courante dans le channel
   
   SeekableByteChannel position(long	->	Changer la position dans le channel
   new Position)
   
   int read(ByteBuffer dst)				->	Lire un ensemble d'octets du channel dans le tampon fourni en paramètre.
   											Retourne le nombre d'octets lu ou -1 si la fin du channel est atteinte.
   
   long size()							->	Retourner la taille en octets du flux auquel le channel est connecté
   
   SeekableByteChannel truncate(long	->	Tronquer le contenu de l'élément sur lequel le channel est connecté à la
   size)									taille fournie en paramètre. Cela permet de redimensionner la taille
   											du flux associé au channel avec la valeur fournie en paramètre
   
   int write(ByteBuffer src)			->	Ecrire les octets fournis en paramètre à la position courante dans le 
   											channel
   
   
   La méthode read() tente une lecture pour remplir le nombre d'octets du tampon passé en paramètre. La position 
   courante dans le channel est augmentée de la taille des données lues. 
   La méthode write() écrit les octets du tampon passé en paramètre à partir de la position courante dans le channel.
   Si le fichier est ouvert avec l'option APPEND, alors la position courante est située à la fin du fichier. Elle
   renvoie le nombre d'octets écrits. La position courante dans le channel est augmentée de la taille des données 
   écrites.
   La surchage de la méthode position() qui attend un paramètre de type long permet de déplacer la position courante
   dans le channel. Elle renvoie le channel lui-même pour permettre un chaînage des appels de cette méthode. La 
   taille du flux connacté au channel n'est pas modifiée si la valeur fournie en paramètre est supérieure à sa taille
   totale. L'utilisation de cette méthode n'est pas recommandée avec un channel ouvert avec l'option APPEND.
   La méthode truncate() permet de réduire la taille totale du flux connecté au channel. Si la taille fournie en 
   paramètre est inférieure à la taille totale courante, alors les octets entre la taille fournie et la taille
   totale sont perdus. Si la taille fournie en paramètre est supérieure à la taille totale courante, alors 
   l'invocation de la méthode n'a aucun effet. Une implémentation de cette interface peut interdire l'utilisation
   de cette méthode si le channel est ouvert avec l'option APPEND.
   Exemple : réf main
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
			sbc.position(11); // n'a aucun effet ici à cause de APPEND
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
			System.out.println("Temps d'écriture avec SeekableByteChannel : " + (System.currentTimeMillis() - start));
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
			System.out.println("Temps d'écriture avec FileChannel : " + (System.currentTimeMillis() - start));
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
		
		System.out.println("D'après mes observations, FileChannel reste le plus performant");
		System.out.println("Nombre de processeurs : " + Runtime.getRuntime().availableProcessors());
	}
	
	
}
