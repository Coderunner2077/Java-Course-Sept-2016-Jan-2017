package fr.rep.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import fr.rep.observer.Observable;
import fr.rep.observer.Observer;

public abstract class AbstractModel implements Observable {
	protected ArrayList<Observer> listObserver = new ArrayList<Observer>();
	protected String operande = "", operateur = "", nombre = "";
	protected boolean go = false;
	protected BigDecimal number;
	
	public abstract void reset(); // !!!!!
	
	public abstract void setNumber(String nbre);
	
	public abstract void setOperateur(String ope);
	
	public abstract void getResult();
	
	public abstract void calcul();
	
	public void addObserver(Observer obs){
		listObserver.add(obs);
	}
	
	public void removeObserver(){
		listObserver = new ArrayList<Observer>();
	}
	
	public void notifyObserver(String str){
		if(str.matches("^0[0-9]+"))
			str = str.substring(1, str.length());
		for(Observer obs : listObserver)
			obs.update(str);
	}
}
