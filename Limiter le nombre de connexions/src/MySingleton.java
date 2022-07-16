
public class MySingleton {
	//Le singleton
	private static MySingleton single;
	//Variable d'instance
	private String name ="";
	
	//Constructeur privé
	private MySingleton(){
		name = "Mon singleton";
		System.out.println("\t\t CREATION DE L'INSTANCE !!!");
	}
	
	//Méthode d'accès au singleton
	public static MySingleton getInstance(){
		if(single == null)
			single = new MySingleton();
		return single;
	}
	
	//Accesseur
	public String getName(){
		return name;
	}
}
