package fr.folder.scanner;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderScanner extends RecursiveTask<Long>{
	private Path path = null;
	private String filter = "";
	private long result = 0;
	
	public FolderScanner(){ }
	public FolderScanner(Path p, String f){
		path = p;
		filter = f;
	}
	/**
	 * Méthode qui se charge de scanner les dossiers de façon récursive
	 * c'est la méthode de scan en mode mono thread
	 * @return
	 * @throws ScanException
	 */
	public long sequentialScan() throws ScanException {
		//Si le chemin n'est pas valide, on lève une exception
		if(path == null || path.equals(""))
			throw new ScanException("Chemin à scanner non valide (vide ou null) !");
		System.out.println("Scan du dossier : " + path + " à la recherche des fichiers "
				+ "portant l'extension " + this.filter);
		
		//On liste maintenant le contenu du répertoire pour traiter les sous-dossiers
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			for(Path nom : listing){
				//S'il s'agit d'un dossier, on le scanne grâce à notre objet
				if(Files.isDirectory(nom.toAbsolutePath())){
					FolderScanner f = new FolderScanner(nom.toAbsolutePath(), this.filter);
					result += f.sequentialScan();
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		
		//Maintenant, on filtre le contenu de ce même dossier sur le filtre défini
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, this.filter)){
			for(Path nom : listing){
				//Pour chaque fichier correspondant, on incrémente notre compteur
				result++;
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * Méthode que je vais utiliser pour les traitements en mode parallèle
	 * @return
	 * @throws ScanException
	 */
	public long parallelScan() throws ScanException{
		//List d'objet qui contiendra les sous-tâches créées et lancées
		List<FolderScanner> list = new ArrayList<>();
		
		//Si le chemin n'est pas valide
		if(path == null || path.equals(""))
			throw new ScanException("Chemin à scanner non valide (vide ou null ) !");
		
		System.out.println("Scan du dossier : " + path + " à la recherche des fichiers "
				+ "portant l'extension " + this.filter);
		
		//Je liste, comme précédemment, le contenu du repertoire
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			//on parcourt le contenu
			for(Path nom : listing){
				//s'il s'agit d'un dossier, on crée une sous-tâche
				if(Files.isDirectory(nom.toAbsolutePath())){
					//je crée donc un nouvel objet FolderScanner 
					//qui se chargera de scanner ce dossier
					FolderScanner f = new FolderScanner(nom.toAbsolutePath(), this.filter);
					//je l'ajoute à la liste des tâches en cours pour récupérer le résultat
					//plus tard
					list.add(f);
					//C'est cette instruction qui lance l'action en tâche de fond
					f.fork();
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		
		//On compte maintenant les fichiers, correspondant au filtre, présents dans ce dossier
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, this.filter)){
			for(Path nom : listing){
				result++;
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		//Et enfin, je récupère le résultat de toutes les tâches de fond
		for(FolderScanner f : list)
			result += f.join();
		
		//Et je renvoie le résultat final
		return result;
	}
	/**
	 * Méthode qui définit l'action à faire
	 * dans mon cas, je lance le scan en mode parallèle
	 */
	protected Long compute(){
		long resultat = 0;
		try{
			resultat = this.parallelScan();
		}catch(ScanException e){
			e.printStackTrace();
		}
		return resultat;
	}
	
	public long getResultat(){
		return this.result;
	}
}
