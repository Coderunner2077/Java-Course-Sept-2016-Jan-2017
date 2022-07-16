
public class Paire {
	private String valeur1, valeur2;
	private int nb = 0;
	public Paire(){
		valeur1 = null;
		valeur2 = null;
		System.out.println("Instanciation !");
	}
	
	public Paire(String val1, String val2){
		valeur1 = val1;
		valeur2 = val2;
		System.out.println("Instanciation avec des paramètres !");
	}
	public Paire(String val1, String val2, Integer nb){
		valeur1 = val1;
		valeur2 = val2;
		this.nb = nb;
		System.out.println("Instanciation avec nombre à la fin");
	}
	//getters
	public String getValeur1(){
		return this.valeur1;
	}
	public String getValeur2(){
		return this.valeur2;
	}
	//setters
	public void setValeur1(String val1){
		this.valeur1 = val1;
	}
	public void setValeur2(String val2){
		this.valeur2 = val2;
	}
	public void setValeurs(String val1, String val2){
		this.valeur1 = val1;
		this.valeur2 = val2;
	}
	//toString
	public String toString(){
		if(nb == 0)
			return "Ceci est un objet " +this.getClass().getName()+", il est constitué de : valeur1 = "
				+ this.valeur1 +", valeur2 = "+ this.valeur2;
		else 
			return "Ceci est un objet " +this.getClass().getName()+", il est constitué de : valeur1 = "
			+ this.valeur1 +", valeur2 = "+ this.valeur2 +", nombre " + nb;
	}
}
