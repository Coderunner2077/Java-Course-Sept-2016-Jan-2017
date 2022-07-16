package com.pendu.observable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.interfaces.observe.Observable;
import com.interfaces.observe.Observateur;

public class Sesame implements Observable {
	private String motSecret;
	private int numero = 0;
	private List<Observateur> listObs = new ArrayList<Observateur>();
	public Sesame(){
		Path chemin = Paths.get("dictionnaire.txt");
		try(InputStream input = Files.newInputStream(chemin);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			LineNumberReader lnr = new LineNumberReader(br)){
				numero = (int)(Math.random()*336530) + 1;
				while(lnr.getLineNumber() != numero)
					motSecret = lnr.readLine();
				System.out.println("sesame : " + motSecret);
			}catch(IOException e){e.printStackTrace(); }
	}
	
	public void addObservateur(Observateur obs){
		listObs.add(obs);
	}
	
	public void delObservateur(){
		listObs = new ArrayList<Observateur>();
	}
	
	public void updateObservateur(){
		for(Observateur obs : listObs){
			obs.update(motSecret);
		}
	}
	
	
	
	public static void main(String[] args) {
		
	}
	}
	

