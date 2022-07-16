import java.util.Scanner;
public class MyC {

	public static void main(String[] args) {
		
		char reponse = 'O';
		
		do
		{
			int choix = 0;
			double celcius = 0.0, fahren = 0.0;
			System.out.println("CONVERTISSEUR DEGRES CELCIUS ET DEGRES FAHRENHEIT");
			System.out.println("-------------------------------------------------");
			System.out.println("Choisissez le mode de conversion :");
			System.out.println("1 - Convertisseur Fahrenheit - Celcius");
			System.out.println("1 - Convertisseur Celcius - Fahrenheit");
			Scanner sc = new Scanner(System.in);
			while(choix != 1 && choix != 2)
			{
				choix = sc.nextInt();
				if(choix != 1 && choix != 2)
					System.out.println("---Entree invalide !---");
			}
			
			System.out.println("Temperature à convertir :");
			if(choix == 1)
			{
				fahren = sc.nextDouble();
				celcius = ((fahren - 32) * 5) / 9;					
				fahren = arrondi(fahren, 2);
				celcius = arrondi(celcius, 2);
				System.out.println(fahren +" °F correspond à : "+ celcius + " °C.");
			}
			else if(choix == 2)			
			{
				celcius = sc.nextDouble();
				fahren = (9.0 / 5.0) * celcius + 32; // PAS : (9/5) * celcius + 32 !!!
				celcius = arrondi(celcius, 2);
				fahren = arrondi(fahren, 2);
				System.out.println(celcius + " °C correspond à " + fahren + " °F.");
			}
			sc.nextLine();
			do
			{
				System.out.println("Souhaitez-vous convertir une autre temperature? (O/N)");
				
				reponse = sc.nextLine().charAt(0);
			}while(reponse != 'O' && reponse != 'N');
			
		}while(reponse == 'O');
		System.out.println("Au revoir");

		double al = arrondi(3.64455111, 4);
		System.out.println(al + ", ");
	}
	
	public static double arrondi(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	}
}
