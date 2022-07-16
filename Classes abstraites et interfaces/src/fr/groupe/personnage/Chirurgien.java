package fr.groupe.personnage;
import fr.comportement.*;

public class Chirurgien extends Personnage{
	public Chirurgien (){
		this.soin = new Operation();
	}
	public Chirurgien(EspritCombatif esprit, Soin soin, Deplacement dep){
		super(esprit, soin, dep);
	}
}
