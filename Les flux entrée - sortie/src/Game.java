import java.io.Serializable;
public class Game implements Serializable{
	private String nom, style;
	private double prix;
	private transient Notice notice;// variable non sérialisée, tout bonnement ignorée
	public Game(String nom, String style, double prix){
		this.nom = nom;
		this.style = style;
		this.prix = prix;
		this.notice = new Notice();
	}
	public String toString(){
		return "Nom du jeu : " +this.nom +"\nStyle de jeu : "+ this.style + "\nPrix du jeu : "+this.prix+
				"€\n Notice : " + this.notice + "\n";
	}
}
