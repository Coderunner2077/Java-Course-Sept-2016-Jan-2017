
class Capitale extends Ville{
	private String monument;
	public Capitale(){
		super();
		monument = "inconnu";
	}
	
	public Capitale(String nom, int hab, String pays, String pMonument)
		throws NombreHabitantsException, NomVilleException
		{
			super(nom, hab, pays); // Si pas de paramètres ==> il renverra le constructeur par défaut de la classe Ville
			this.monument = pMonument; //Remarque: sans this c'est bon aussi
		}
	
	public String getMonument()
	{
		return monument;
	}
	
	public void setMonument(String pMonument)
	{
		this.monument = pMonument;
	}
	public String decrisToi()
	{
		String str = super.decrisToi() + "\n\t ==> "+ this.monument + " en est un monument.";
		return str;
	}
	
	public String toString()
	{
		String str = super.toString() + "\n\t ==> "+ this.monument + " en est un monument.";
		return str;
	}
	
	
}
