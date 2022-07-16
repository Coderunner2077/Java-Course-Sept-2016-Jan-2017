
public enum Langage {
	//Objets directement construits
	JAVA("Langage Java", "Eclipse"), //s'utilisent commes des variables static déclarées public
	C("Langage C", "Code Blocks"),
	CPlus("Langage C++", "Visual Studio"),
	PHP("Langage PHP", "PS Pad"), // UN POINT VIRGULE A LA FIN, contrairement à C (ou pas?)
	auTre,
	biduleConstruitDirectementALinterieurDeLénumération("strordinaire", "");
	
	private String name = "";
	private String editor = "";
	
	Langage() {
		name = "Autre Langage";
		editor = "Inconnu";
	}
	
	//Constructeur (déclaré private de façon implicite)
	Langage(String name, String editor){
		this.name = name;
		this.editor = editor;
	}
	public String toString(){
		return name;
	}
	public void getEditor(){
		System.out.println("Editeur : " + editor);
	}
}
