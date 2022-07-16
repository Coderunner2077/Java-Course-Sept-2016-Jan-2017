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
	 * M�thode qui se charge de scanner les dossiers de fa�on r�cursive
	 * c'est la m�thode de scan en mode mono thread
	 * @return
	 * @throws ScanException
	 */
	public long sequentialScan() throws ScanException {
		//Si le chemin n'est pas valide, on l�ve une exception
		if(path == null || path.equals(""))
			throw new ScanException("Chemin � scanner non valide (vide ou null) !");
		System.out.println("Scan du dossier : " + path + " � la recherche des fichiers "
				+ "portant l'extension " + this.filter);
		
		//On liste maintenant le contenu du r�pertoire pour traiter les sous-dossiers
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			for(Path nom : listing){
				//S'il s'agit d'un dossier, on le scanne gr�ce � notre objet
				if(Files.isDirectory(nom.toAbsolutePath())){
					FolderScanner f = new FolderScanner(nom.toAbsolutePath(), this.filter);
					result += f.sequentialScan();
				}
			}
		} catch (IOException e){
			e.printStackTrace();
		}
		
		//Maintenant, on filtre le contenu de ce m�me dossier sur le filtre d�fini
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, this.filter)){
			for(Path nom : listing){
				//Pour chaque fichier correspondant, on incr�mente notre compteur
				result++;
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * M�thode que je vais utiliser pour les traitements en mode parall�le
	 * @return
	 * @throws ScanException
	 */
	public long parallelScan() throws ScanException{
		//List d'objet qui contiendra les sous-t�ches cr��es et lanc�es
		List<FolderScanner> list = new ArrayList<>();
		
		//Si le chemin n'est pas valide
		if(path == null || path.equals(""))
			throw new ScanException("Chemin � scanner non valide (vide ou null ) !");
		
		System.out.println("Scan du dossier : " + path + " � la recherche des fichiers "
				+ "portant l'extension " + this.filter);
		
		//Je liste, comme pr�c�demment, le contenu du repertoire
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			//on parcourt le contenu
			for(Path nom : listing){
				//s'il s'agit d'un dossier, on cr�e une sous-t�che
				if(Files.isDirectory(nom.toAbsolutePath())){
					//je cr�e donc un nouvel objet FolderScanner 
					//qui se chargera de scanner ce dossier
					FolderScanner f = new FolderScanner(nom.toAbsolutePath(), this.filter);
					//je l'ajoute � la liste des t�ches en cours pour r�cup�rer le r�sultat
					//plus tard
					list.add(f);
					//C'est cette instruction qui lance l'action en t�che de fond
					f.fork();
				}
			}
		} catch(IOException e){
			e.printStackTrace();
		}
		
		//On compte maintenant les fichiers, correspondant au filtre, pr�sents dans ce dossier
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, this.filter)){
			for(Path nom : listing){
				result++;
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		//Et enfin, je r�cup�re le r�sultat de toutes les t�ches de fond
		for(FolderScanner f : list)
			result += f.join();
		
		//Et je renvoie le r�sultat final
		return result;
	}
	/**
	 * M�thode qui d�finit l'action � faire
	 * dans mon cas, je lance le scan en mode parall�le
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
