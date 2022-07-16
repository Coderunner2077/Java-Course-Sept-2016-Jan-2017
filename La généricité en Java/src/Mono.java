
public class Mono<T> {
	//variable d'instance
	private T valeur;
	// constructeur par défaut
	public Mono(){
		this.valeur = null;
	}
	//constructeur avec paramètre inconnu pour l'instant
	public Mono(T val){
		this.valeur = val;
	}
	// définit la valeur avec le paramètre
	public void setValeur(T val){
		this.valeur = val;
	}
	// retourne la valeur déjà "castée" par la signature de la méthode
	public T getValeur(){
		return valeur;
	}

}
