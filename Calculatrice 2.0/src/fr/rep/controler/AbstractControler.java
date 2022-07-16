package fr.rep.controler;

import java.math.BigDecimal;
import java.util.ArrayList;

import fr.rep.model.AbstractModel;

public abstract class AbstractControler {
	protected AbstractModel calc;
	protected String operateur = "", nombre = "";
	protected BigDecimal number;
	protected ArrayList<String> operateurs;
	
	public AbstractControler(AbstractModel cal){
		this.calc = cal;
		this.operateurs = new ArrayList<String>();
		operateurs.add("+");
		operateurs.add("-");
		operateurs.add("*");
		operateurs.add("/");
		operateurs.add("=");
	}
	
	public void setOperateur(String ope){
		this.operateur = ope;
		control();
		
	}
	
	public void setNumber(String nbre){
		this.nombre = nbre;
		control();
	}
	
	public void reset(){
		calc.reset();
	}
	
	public abstract void control();
}
