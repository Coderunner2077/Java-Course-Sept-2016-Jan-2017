package fr.runnable;

public class CompteEnBanque {
	private int solde;
	public CompteEnBanque(){
		solde = 100;
	}
	public synchronized void retraitArgent(int retrait){
		if(this.solde > 0 && this.solde >= retrait){
			this.solde -= retrait;
			System.out.println("Le nouveau solde est : " + this.solde);
		}
		else
			System.out.println("Fonds insuffisants");
	}
	
	public synchronized int getSolde(){
		return this.solde;
	}
}
