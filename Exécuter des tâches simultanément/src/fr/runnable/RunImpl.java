package fr.runnable;

public class RunImpl implements Runnable {
	private CompteEnBanque cb;
	private String name;
	
	public RunImpl(CompteEnBanque cb, String name){
		this.cb = cb;
		this.name = name;
	}
	
	public void run(){
		while(cb.getSolde() > 0){
			cb.retraitArgent(2);
			System.out.println("Retrait effectué par : " + this.name);
		}
	}
}
