
public class Duo<T, S> {
	// variable d'instance de type T
	private T valeur1;
	//variable d'instance de type S
	private S valeur2;
	//constructeur par défaut
	public Duo(){
		this.valeur1 = null;
		this.valeur2 = null;
	}
	// constructeur avec paramètres
	public Duo(T val1, S val2){
		this.valeur1 = val1;
		this.valeur2 = val2;
	}
	// méthodes d'initialisation des deux valeurs
	public void setValeurs(T val1, S val2){
		this.valeur1 = val1;
		this.valeur2 = val2;
	}
	public void setValeur1(T val1){
		this.valeur1 = val1;
	}
	public void setValeur2(S val2){
		this.valeur2 = val2;
	}
	// les getters
	public T getValeur1(){
		return valeur1;
	}
	public S getValeur2(){
		return valeur2;
	}
}
