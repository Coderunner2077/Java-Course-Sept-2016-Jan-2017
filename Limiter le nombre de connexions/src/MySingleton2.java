
public class MySingleton2 {
	//Le sinlgeton
	private static MySingleton2 single = new MySingleton2();
	//Variable d'instance
	private String name = "";
	
	//Constructeur priv�
	private MySingleton2(){
		name = "Mon singleton2";
		System.out.println("\t\t Cr�ation de l'instance !!!");
	}
	
	//M�thodes d'acc�s au singleton
	public static MySingleton2 getInstance(){
		if(single == null)
			single = new MySingleton2();
		return single;
	}
	
	//Accesseur
	public String getName(){
		return name;
	}
}
