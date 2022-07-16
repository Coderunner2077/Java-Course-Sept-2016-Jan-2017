
public class Avant_Enumeration {
	public static final int PARAM1 = 1;
	public static final int PARAM2 = 2;
	public void fait(int param)
	{
		if(param == PARAM1)
			System.out.println("Fait à la façon N°1");
		else if(param == PARAM2)
			System.out.println("Fait à la façon N°2");
		else
			System.out.println("Fait d'une autre façon");
	}
}
