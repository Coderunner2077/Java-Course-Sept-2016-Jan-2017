
public class Mono<T> {
	//variable d'instance
	private T valeur;
	// constructeur par d�faut
	public Mono(){
		this.valeur = null;
	}
	//constructeur avec param�tre inconnu pour l'instant
	public Mono(T val){
		this.valeur = val;
	}
	// d�finit la valeur avec le param�tre
	public void setValeur(T val){
		this.valeur = val;
	}
	// retourne la valeur d�j� "cast�e" par la signature de la m�thode
	public T getValeur(){
		return valeur;
	}

}
