package fr.rep.controler;

import fr.rep.model.AbstractModel;

public class CalculetteControler extends AbstractControler {
	public CalculetteControler(AbstractModel cal){
		super(cal);
	}
	
	public void control(){
		if(operateurs.contains(operateur)){
			if(operateur.equals("=")) // !!!!!!!!
				this.calc.getResult();
			else
				this.calc.setOperateur(operateur);
		}
			
		if(nombre.matches("^[0-9.]+$"))
			this.calc.setNumber(nombre);
		
		operateur = ""; // !!!!!!!!
		nombre = ""; // !!!!!!!!
	}

}
