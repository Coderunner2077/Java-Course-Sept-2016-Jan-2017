package com.sdz.model;

import java.util.ArrayList;
import java.util.Calendar;

import com.sdz.observer.Observable;
import com.sdz.observer.Observateur;

public class Horloge implements Observable{
	//Objet calendrier pour récupérer l'heure courante
	private Calendar cal;
	private String hour = "";
	//ma collection d'observateurs
	private ArrayList<Observateur> listeObservateur = new ArrayList<Observateur>();
	public void run(){
		while(true){
			//On récupére l'instance d'un calendrier à chaque tour de boucle
			//Elle va me permetre de récupérer l'heure actuelle
			this.cal = Calendar.getInstance();
			this.hour = //Les heures
					this.cal.get(Calendar.HOUR_OF_DAY) + " : "
					+	//les minutes
					(
					  this.cal.get(Calendar.MINUTE) < 10 
					  ? "0" + this.cal.get(Calendar.MINUTE)
					  : this.cal.get(Calendar.MINUTE)
					)
					+ " : "
					+
					(
					  this.cal.get(Calendar.SECOND) < 10
					  ? "0" + this.cal.get(Calendar.SECOND)
					  : this.cal.get(Calendar.SECOND)
					);
			//On avertit les observateurs que l'heure a été mise à jour
			this.updateObservateur();
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
					
		}
	}
	
	//Ajoute un Observateur à la liste
	public void addObservateur(Observateur obs){
		this.listeObservateur.add(obs);
	}
	//Retire tous les Observateurs de la liste
	public void delObservateur(){
		this.listeObservateur = new ArrayList<Observateur>();
	}
	//Avertit les observateurs que l'objet observable a changé et invoque la méthode
	//update() de chaque observateur
	public void updateObservateur(){
		for(Observateur obs : this.listeObservateur){
			obs.update(this.hour);
		}
	}
}
