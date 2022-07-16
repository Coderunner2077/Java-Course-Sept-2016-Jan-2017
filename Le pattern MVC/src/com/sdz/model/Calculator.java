package com.sdz.model;

public class Calculator extends AbstractModel {
	//d�finit l'op�rateur
	public void setOperateur(String ope){
		//on lance la calcul
		calcul();
		
		//on stocke l'op�rateur
		this.operateur = ope;
		
		//si l'op�rateur n'est pas :
		if(!ope.equals("="))
			this.operande = ""; // ==> on r�initialise l'op�rande
		
	}
	
	//d�finit le nombre
	public void setNombre(String result){
		//on concat�ne le nombre
		this.operande += result;
		//on met � jour
		notifyObserver(this.operande);
	}
	
	//Force le calcul
	public void getResultat(){
		calcul();
	}
	
	//r�initialise tout
	public void reset(){
		this.result = 0;
		this.operande = "";
		this.operateur = "";
		//Mise � jour !
		notifyObserver(String.valueOf(this.result));
	}
	
	//Calcul
	public void calcul(){
		//s'il n'y a pas d'op�rateur, le r�sultat est le nombre saisi
		if(this.operateur.equals(""))
			this.result = Double.parseDouble(this.operande);
		else{
			//si l'op�rande n'est pas vide, on calcul avec l'op�rateur de calcul
			if(!this.operande.equals("")){
				if(this.operateur.equals("+"))
					this.result += Double.parseDouble(this.operande);
				if(this.operateur.equals("-"))
					this.result -= Double.parseDouble(this.operande);
				if(this.operateur.equals("*"))
					this.result *= Double.parseDouble(this.operande);
				if(this.operateur.equals("/")){
					try{
						this.result /= Double.parseDouble(this.operande);
					}catch(ArithmeticException e){
						this.result = 0;
					}
				}
					
			}
		}
		this.operande = "";
		//on lance aussi la mise � jour
		notifyObserver(String.valueOf(this.result));
	}
}
