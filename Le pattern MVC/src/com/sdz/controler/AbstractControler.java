package com.sdz.controler;

import java.util.ArrayList;

import com.sdz.model.AbstractModel;

public abstract class AbstractControler {
	protected AbstractModel calc;
	protected String operateur = "", nbre = "";
	protected ArrayList<String> listOperateur = new ArrayList<String>();
	
	public AbstractControler(AbstractModel cal){
		this.calc = cal;
		//on d�finit la liste des op�rateurs afin de s'assurer qu'ils soient corrects
		this.listOperateur.add("+");
		this.listOperateur.add("-");
		this.listOperateur.add("*");
		this.listOperateur.add("/");
		this.listOperateur.add("=");		
	}
	
	//d�finit l'op�rateur
	public void setOperateur(String ope){
		this.operateur = ope;
		control();
	}
	
	//d�finir le nombre
	public void setNombre(String nbre){
		this.nbre = nbre;
		control();
	}
	
	//Efface
	public void reset(){
		this.calc.reset();
	}
	
	//M�thode de contr�le
	abstract void control();
}
