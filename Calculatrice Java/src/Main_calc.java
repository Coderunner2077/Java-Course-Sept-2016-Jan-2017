import java.math.BigDecimal;

import fr.my.calculatrice.Calculatrice;

public class Main_calc {
	
	public static void main(String[] args) {
		Calculatrice c = new Calculatrice();
		double nb = 1.1 - 3.3;
		System.out.println(nb);
		String n = "1.1";
		BigDecimal nb1 = new BigDecimal(n);
		BigDecimal nb2 = new BigDecimal("3.3");
		
		BigDecimal nb3 = nb2.subtract(nb1);
		System.out.println(nb3);
		String str = new String();
		str = str.valueOf(nb3);
		System.out.println(str);
	}
	public static double arrondi(double A, int B) {
		  return (double) ( (int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
	}
}
