
public class Chien extends Canin implements Rintintin{ // l'ordre des d�clarations est primordial
	public Chien(){
	}
	public Chien(String couleur, int poids){
		this.couleur = couleur;
		this.poids = poids;
	}
	void crier(){
		System.out.println("J'aboie sans raison");
	}
	public void faireLeBeau(){
		System.out.println("Wesch wesch ouav ouav regardez-moi !");
	}
	public void faireCalin(){
		System.out.println("Moi chien faire c�lin toi !");
	}
	public void faireLechouille(){
		System.out.println("L�������chouuuuille!");
	}
}
