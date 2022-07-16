package fr.groupe.personnage;
import fr.comportement.*;

public class Sniper extends Personnage {
	public Sniper(){
		this.espritCombatif = new CombatSniper();
	}
	public Sniper(EspritCombatif esprit, Soin soin, Deplacement dep){
		super(esprit, soin, dep);
	}

}
