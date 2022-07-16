
public class ZDialogInfo {
	private String nom, genre, age, cheveux, taille;
	public ZDialogInfo(){ }
	public ZDialogInfo(String nom, String genre, String age, String cheveux, String taille){
		this.nom = nom;
		this.genre = genre;
		this.age = age;
		this.cheveux = cheveux;
		this.taille = taille;
	}
	
	public String toString(){
		String str;
		if(this.nom != null && !this.nom.equals("") && this.genre != null && this.age != null 
				&& this.cheveux !=null && this.taille != null){
			str = "Description de l'objet ZDialogInfo\n";
			str += "Nom : " +this.nom;
			str += "\nGenre : " + this.genre;
			str += "\nÂge : " + this.age;
			str += "\nCheveux : " + this.cheveux;
			str += "\nTaille : " + this.taille;
		}
		else{
			str = "Aucune information !";
		}
		return str;
	}
}
