
public enum Langage {
	//Objets directement construits
	JAVA("Langage Java", "Eclipse"), //s'utilisent commes des variables static d�clar�es public
	C("Langage C", "Code Blocks"),
	CPlus("Langage C++", "Visual Studio"),
	PHP("Langage PHP", "PS Pad"), // UN POINT VIRGULE A LA FIN, contrairement � C (ou pas?)
	auTre,
	biduleConstruitDirectementALinterieurDeL�num�ration("strordinaire", "");
	
	private String name = "";
	private String editor = "";
	
	Langage() {
		name = "Autre Langage";
		editor = "Inconnu";
	}
	
	//Constructeur (d�clar� private de fa�on implicite)
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
