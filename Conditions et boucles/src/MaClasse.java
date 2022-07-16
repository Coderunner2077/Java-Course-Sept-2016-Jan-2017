
public class MaClasse {

	public static void main(String[] args) {
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//CONDITIONS TERNIAIRES
		int x = 10, y = 20;
		int max = (x < y) ? (y < 15) ? y % 10 : y * 2 : x;
		System.out.println("max : " + max);
		// j'ai inséré une condition terniaire dans une autre. Autre écriture, plus claire :
		int min = (x < y) ? ((x < 10) ? x + 1 : x) : y;
		System.out.println("min : " + min);
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		// BOUCLES
		int a = 0, b = 5;
		while(a++ < b)
			System.out.println("Coucou " + a + " fois !");
		a = 0;
		System.out.println("Nouvelle boucle, subtilement differente");
		while(++a < b)
			System.out.println("Coucou " + a + " fois !");
		/* TOUT EST QUESTION DE REGLES DE PRIORITE DES OPERATEURS : dans le 1er cas, la boucle while teste d'abord
		la condition et ensuite incremente la variable a, et dans le 2nd cas, elle fait l'inverse, d'où un tour de
		boucle en moins
		*/
		for(int i = 1; i <= 10; i++)
			System.out.println("Voici la ligne " + i);
		//REMARQUE: j'ai créé une variable locale dans la boucle for, je ne pouvais pas le faire en C (à voir...)
		//NOTE : JE PEUX EGALEMENT CUMULER LES DECLARATIONS, LES CONDITIONS ET LES INSTRUCTIONS DE FIN DE BOUCLE !
		for(int i = 1, j = 2; (i < 10 && j < 17); i++, j+=2)
			System.out.println("i = " + i + ", j = " + j);
		
		
		
		
	}

}
