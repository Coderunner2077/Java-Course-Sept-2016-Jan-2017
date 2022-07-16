package fr.groupe.personnage;
import fr.comportement.*;

public abstract class Personnage {
	// mes instances de comportement
	protected EspritCombatif espritCombatif = new Pacifiste();
	protected Soin soin = new AucunSoin();
	protected Deplacement deplacement = new Marcher();
	
	// constructeur par défaut
	public Personnage(){}
	
	// constructeur avec paramètres
	public Personnage(EspritCombatif espritCombatif, Soin soin, Deplacement deplacement){
		this.espritCombatif = espritCombatif;
		this.soin = soin;
		this.deplacement = deplacement;
	}
	
	// méthode de déplacement du personnage
	public void seDeplacer(){
		//on utilise les objets de deplacement de façon polymorphe
		deplacement.deplacer();
	}
	// méthode de combat du personnage
	public void combattre(){
		//on utilise les objets de espritCombatif de façon polymorphe
		espritCombatif.combat();
	}
	
	//Méthode de soin
	public void soigner(){
		// ob utiliser les objets de soin de façon polymorphe
		soin.soigne();
	}
	
	//redéfinit le comportement au combat
	public void setEspritCombatif(EspritCombatif espritCombatif) {
		this.espritCombatif = espritCombatif;
	}
	//redéfinit le comportement de soin
	public void setSoin(Soin soin){
		this.soin = soin;
	}
	//redéfinit le comportement de déplacement
	public void setDeplacament(Deplacement deplacement){
		this.deplacement = deplacement;
	}
	
	public String toString(){
		String str = "Je suis un " + this.getNom() + ", mon mode de combat : "+this.espritCombatif
				+", je peux " + this.deplacement+ ", et je ";
		str += this.soin instanceof AucunSoin ? "ne peux" : "peux";
		str += this.soin instanceof Operation ? " effectuer" : " apporter";
		str += " " + this.soin;
		return str;		
	}
	
	public String getNom() {
		String str = this.getClass().getName();
		str = str.substring(str.lastIndexOf('.') + 1);
		
		return str;
	}
}
