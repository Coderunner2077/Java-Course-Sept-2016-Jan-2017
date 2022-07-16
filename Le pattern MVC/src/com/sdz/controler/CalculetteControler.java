package com.sdz.controler;

import com.sdz.model.AbstractModel;

public class CalculetteControler extends AbstractControler {
	public CalculetteControler(AbstractModel cal){
		super(cal);
	}
	
	public void control(){
		//on notifie le mod�le d'une action si le contr�le est bon
		//--------------------------------------------------------
		//Si l'op�rateur est dans la liste
		if(this.listOperateur.contains(this.operateur)){
			//si l'op�rateur est :
			if(this.operateur.equals("="))
				this.calc.getResultat(); //on ordonne au mod�le d'afficher le r�sultat
			//sinon, on passe l'op�rateur au mod�le
			else
				this.calc.setOperateur(this.operateur);
		}
		
		//si le nombre est conforme
		if(this.nbre.matches("^[0-9.]+$"))
			this.calc.setNombre(this.nbre);
		this.operateur = "";
		this.nbre = "";
	}
	
	
}
