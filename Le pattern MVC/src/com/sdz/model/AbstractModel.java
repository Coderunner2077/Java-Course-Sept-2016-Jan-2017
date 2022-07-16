package com.sdz.model;

import java.util.ArrayList;

import com.sdz.observer.Observable;
import com.sdz.observer.Observer;

public abstract class AbstractModel implements Observable {
	protected double result = 0;
	protected String operateur = "", operande = "";
	private ArrayList<Observer> listObserver = new ArrayList<Observer>();
	//efface
	public abstract void reset();
	
	//effectue le calcul
	public abstract void calcul();
	
	//Affichage forc� du r�sultat
	public abstract void getResultat();
	
	//D�finit l'op�rateur de l'op�ration 
	public abstract void setOperateur(String operateur);
	
	//d�finir le nombre � utiliser pour l'op�ration
	public abstract void setNombre(String nbre);
	
	//Implm�netation du pattern observer
	public void addObserver(Observer obs){
		listObserver.add(obs);
	}
	
	public void notifyObserver(String str){
		if(str.matches("^0[0-9]+"))
			str = str.substring(1, str.length());
		for(Observer obs : listObserver)
			obs.update(str);
	}
	
	public void removeObserver(){
		listObserver = new ArrayList<Observer>();
	}

}
