package fr.folder.scanner;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ForkJoinPool;

public class Main_Folder {

	public static void main(String[] args) {
		/*
		//EN MODE MODE THREAD
		Path chemin = Paths.get("C:\\Users\\Harry");
		String filtre = "*.jpg";
		FolderScanner fs = new FolderScanner(chemin, filtre);
		try{
			long start = System.currentTimeMillis();
			long resultat = fs.sequentialScan();
			long end = System.currentTimeMillis();
			System.out.println("Il y a " + resultat + " fichier(s) portant l'extension "
					+ filtre);
			System.out.println("Temps de traitement : " + (end - start));
		} catch (ScanException e){
			e.printStackTrace();
		}
		
		*/
		//EN MODE SCAN PARALLELE
		Path chemin = Paths.get("C:\\Users\\Harry");
		String filtre = "*.jpg";
		//Création de ma tâche principale qui se chargera de découper son travail en sous-
		//tâches
		FolderScanner fs = new FolderScanner(chemin, filtre);
		
		//Je récupère le nombre de processeurs disponibles
		int processeurs = Runtime.getRuntime().availableProcessors();
		//Je crée mon pool de thread pour mes tâches de fond
		ForkJoinPool pool = new ForkJoinPool(processeurs);
		long start = System.currentTimeMillis();
		
		//Je lance le traitement de ma tâche principale via le pool
		pool.invoke(fs);
		
		Long end = System.currentTimeMillis();
		System.out.println("Il y a "+ fs.getResultat() + " fichier(s) portant l'extension "
				+ filtre);
		System.out.println("Temps de traitement : " + (end - start));
		
		/*
		//un petit test de connaissances
		Path path = Paths.get("C:\\Users\\Harry\\Downloads");
		String filter = "*.png";
		long resultat = 0, debut = 0, fin = 0;
		FolderScan f = new FolderScan(path, filter);
		try{
			debut = System.currentTimeMillis();
			resultat = f.monoScan();
			fin = System.currentTimeMillis();
		}catch(ScanException e){
			e.printStackTrace();
		}
		
		
		int processors = Runtime.getRuntime().availableProcessors();
		ForkJoinPool pool2 = new ForkJoinPool(processors);
		FolderScan fS = new FolderScan(path, filter);
		pool2.invoke(fS);
		resultat = fS.getResultat();
		*/	
	}

}
