import java.util.concurrent.RecursiveTask;

public class CalculSuite extends RecursiveTask<Long>{
	
	private long debut = 0, fin = 0, resultat;
	private int SEUIL = 1_000;
	
	public CalculSuite(long debut, long fin){
		this.debut = debut;
		this.fin = fin;
	}
	
	protected Long compute(){
		long nombreDeChosesAFaire = fin - debut;
		if(nombreDeChosesAFaire < SEUIL){
			System.out.println("Passage au mode mono thread ou le d�coupage calcul le "
					+ "r�sultat");
			resultat = calculer();
		}
		else{
			System.out.println("Passage en mode Fork/Join");
			//On d�coupe la t�che en deux
			long milieu = nombreDeChosesAFaire / 2;
			CalculSuite calcul1 = new CalculSuite(debut, (debut + milieu) - 1);
			calcul1.fork();
			
			CalculSuite calcul2 = new CalculSuite(debut+ milieu, fin);
			resultat = calcul2.compute() + calcul1.join();
		}
		return resultat;
	}
	public long calculer(){
		for(long i = debut; i<= fin; i++){
			System.out.println(resultat + " + " + i);
			resultat += i;
		}
		return resultat;
	}
	
	public long getResultat(){
		return resultat;
	}
}
