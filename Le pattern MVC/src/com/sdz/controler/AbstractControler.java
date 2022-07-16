package com.sdz.controler;

import java.util.ArrayList;

import com.sdz.model.AbstractModel;

public abstract class AbstractControler {
	protected AbstractModel calc;
	protected String operateur = "", nbre = "";
	protected ArrayList<String> listOperateur = new ArrayList<String>();
	
	public AbstractControler(AbstractModel cal){
		this.calc = cal;
		//on définit la liste des opérateurs afin de s'assurer qu'ils soient corrects
		this.listOperateur.add("+");
		this.listOperateur.add("-");
		this.listOperateur.add("*");
		this.listOperateur.add("/");
		this.listOperateur.add("=");		
	}
	
	//définit l'opérateur
	public void setOperateur(String ope){
		this.operateur = ope;
		control();
	}
	
	//définir le nombre
	public void setNombre(String nbre){
		this.nbre = nbre;
		control();
	}
	
	//Efface
	public void reset(){
		this.calc.reset();
	}
	
	//Méthode de contrôle
	abstract void control();
}
