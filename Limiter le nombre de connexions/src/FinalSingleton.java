
public class FinalSingleton {
	private volatile static FinalSingleton single;
	private String name = "";
	
	private FinalSingleton(){
		name = "Mon singleton final";
		System.out.println("\t\t * Création de l'instance ! *");
	}
	
	public static FinalSingleton getInstance(){
		if(single == null){
			synchronized(FinalSingleton.class){
				if(single == null)
					single = new FinalSingleton();
			}
		}
		return single;
	}
	
	public String getName(){
		return name;
	}
}
