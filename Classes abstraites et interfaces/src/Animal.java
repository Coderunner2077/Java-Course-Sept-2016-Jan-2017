
public abstract class Animal {
	protected int poids;
	protected String couleur;
	
	abstract void crier(); // une méthode abstraite
	abstract void deplacement();
	protected void manger(){
		System.out.println("Je mange de la viande");
	}
	protected void boire(){
		System.out.println("Je bois de l'eau");
	}
	public String toString(){
		String str = "Je suis un objet de la "+this.getClass()+", je suis "+this.couleur+" et je pèse "+
	this.poids+" kg.";
		return str;
	}
	
	

}
