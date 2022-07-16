package fr.groupe.personnage;
import fr.comportement.*;

public abstract class Personnage {
	// mes instances de comportement
	protected EspritCombatif espritCombatif = new Pacifiste();
	protected Soin soin = new AucunSoin();
	protected Deplacement deplacement = new Marcher();
	
	// constructeur par d�faut
	public Personnage(){}
	
	// constructeur avec param�tres
	public Personnage(EspritCombatif espritCombatif, Soin soin, Deplacement deplacement){
		this.espritCombatif = espritCombatif;
		this.soin = soin;
		this.deplacement = deplacement;
	}
	
	// m�thode de d�placement du personnage
	public void seDeplacer(){
		//on utilise les objets de deplacement de fa�on polymorphe
		deplacement.deplacer();
	}
	// m�thode de combat du personnage
	public void combattre(){
		//on utilise les objets de espritCombatif de fa�on polymorphe
		espritCombatif.combat();
	}
	
	//M�thode de soin
	public void soigner(){
		// ob utiliser les objets de soin de fa�on polymorphe
		soin.soigne();
	}
	
	//red�finit le comportement au combat
	public void setEspritCombatif(EspritCombatif espritCombatif) {
		this.espritCombatif = espritCombatif;
	}
	//red�finit le comportement de soin
	public void setSoin(Soin soin){
		this.soin = soin;
	}
	//red�finit le comportement de d�placement
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
