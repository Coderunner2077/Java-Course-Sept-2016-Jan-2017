package fr.groupe.personnage;
import fr.comportement.*;

public class Civil extends Personnage{
	public Civil(){}
	
	public Civil(EspritCombatif esprit, Soin soin, Deplacement dep){
		super(esprit, soin, dep);
	}
}
