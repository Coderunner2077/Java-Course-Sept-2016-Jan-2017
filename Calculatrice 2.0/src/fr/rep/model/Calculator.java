package fr.rep.model;

import java.math.BigDecimal;
import java.math.MathContext;

public class Calculator extends AbstractModel {
	public void reset(){
		this.go = false;
		this.operande = "";
		this.operateur = "";
		this.nombre = "";
		this.number = new BigDecimal("0");
		notifyObserver(String.valueOf(this.number));
	}
	
	public void setOperateur(String ope){
		if(!go){
			operateur = ope;
			go = true;
		}
		else{
			calcul();
			operateur = ope;
			if(ope.equals("=")){
				operande = "";
				go = false;
			}
		}
	}
	
	public void setNumber(String nbre){
		if(operande.length() < 12 && nombre.length() < 12){
			if(!go){
				operande += nbre;
				if(operande.matches("^.[0-9]+$")){
					String nb = operande;
					operande = "0" + nb;
				} 
				number = new BigDecimal(operande);
				nombre = "";
				notifyObserver(operande);
			}
			else{
				nombre += nbre;
				notifyObserver(nombre);
			}
		}
		
	}
	
	public void getResult(){
		calcul();
		go = false;
		operande = "";
	}
	
	public void calcul(){
		if(operateur.equals(""))
			number = new BigDecimal(nombre);
		else{
			if(!nombre.equals("")){
				if(operateur.equals("+"))
					number = number.add(new BigDecimal(nombre));
				if(operateur.equals("-"))
					number = number.subtract(new BigDecimal(nombre));
				if(operateur.equals("*"))
					number = number.multiply(new BigDecimal(nombre));
				if(operateur.equals("/")){
					try{
						number = number.divide(new BigDecimal(nombre), MathContext.DECIMAL32);
					} catch(ArithmeticException e){
						number = new BigDecimal("0");
					}
				}
			}
		}
		
		nombre = "";
		notifyObserver(String.valueOf(number));
	}

}
