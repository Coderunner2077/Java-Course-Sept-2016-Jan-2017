package com.sdz.model;

public class Calculator extends AbstractModel {
	//définit l'opérateur
	public void setOperateur(String ope){
		//on lance la calcul
		calcul();
		
		//on stocke l'opérateur
		this.operateur = ope;
		
		//si l'opérateur n'est pas :
		if(!ope.equals("="))
			this.operande = ""; // ==> on réinitialise l'opérande
		
	}
	
	//définit le nombre
	public void setNombre(String result){
		//on concatène le nombre
		this.operande += result;
		//on met à jour
		notifyObserver(this.operande);
	}
	
	//Force le calcul
	public void getResultat(){
		calcul();
	}
	
	//réinitialise tout
	public void reset(){
		this.result = 0;
		this.operande = "";
		this.operateur = "";
		//Mise à jour !
		notifyObserver(String.valueOf(this.result));
	}
	
	//Calcul
	public void calcul(){
		//s'il n'y a pas d'opérateur, le résultat est le nombre saisi
		if(this.operateur.equals(""))
			this.result = Double.parseDouble(this.operande);
		else{
			//si l'opérande n'est pas vide, on calcul avec l'opérateur de calcul
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
		//on lance aussi la mise à jour
		notifyObserver(String.valueOf(this.result));
	}
}
