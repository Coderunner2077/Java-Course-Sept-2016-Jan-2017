package fr.folder.scanner;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderScan extends RecursiveTask<Long>{
	private Path path = null;
	private String filter = "";
	private long result = 0;
	
	public FolderScan(){ }
	public FolderScan(Path p, String f){
		path = p;
		filter = f;
	}
	
	public long monoScan() throws ScanException{
		if(path == null || path.equals(""))
			throw new ScanException("Le chemin est invalide (vide ou null) !");
		System.out.println("Scan du dossier " + path + "à la recherche de fichiers "
				+ "portant l'extension " + filter);
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			for(Path nom : listing){
				if(Files.isDirectory(nom.toAbsolutePath())){
					FolderScan fs = new FolderScan(nom.toAbsolutePath(), this.filter);
					result += fs.monoScan();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, filter)){
			for(Path nom : listing){
				result++;
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}
	
	public long parallelScan() throws ScanException{
		List<FolderScan> list = new ArrayList<>();
		if(path == null || path.equals(""))
			throw new ScanException("Chemin invalide (vide ou null) !");
		System.out.println("Scan du dossier : "+path+" en mode parallèle à la recherche"
				+" de fichiers portant l'extension "+ filter);
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path)){
			for(Path nom : listing){
				if(Files.isDirectory(nom.toAbsolutePath())){
					FolderScan fs = new FolderScan(nom.toAbsolutePath(), this.filter);
					list.add(fs);
					fs.fork();
				}
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		
		try(DirectoryStream<Path> listing = Files.newDirectoryStream(path, this.filter)){
			for(Path nom : listing)
				result++;
		}catch(IOException e){
			e.printStackTrace();
		}
		
		for(FolderScan fs : list){
			result += fs.join();
		}
		return result;
	}
	
	public Long compute(){
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
