
public class NombreHabitantsException extends Exception{
	public NombreHabitantsException (){
		super();
		System.out.println("Vous essayez d'instancier une classe Ville avec un nombre d'habitants n�gatif !");
	}
	public NombreHabitantsException(int nbre){
		super("Nombre d'habitants rejet� : " + nbre);
		System.out.println("Vous essayez d'instancier une classe Ville avec un nombre d'habitants n�gatif !");
	}

}
