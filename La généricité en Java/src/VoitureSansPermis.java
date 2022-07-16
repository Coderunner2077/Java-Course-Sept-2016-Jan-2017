
public class VoitureSansPermis extends Voiture{
	private String permis;
public VoitureSansPermis(){
	super();
	permis = "sans permis";
}
public String toString(){
	String str = super.toString() + " " + this.permis;
	return str;
}
}
