import java.util.ArrayList;
import java.util.List;

public class Garage {
	List<Voiture> list = new ArrayList<Voiture>();
	
	public void add(List<? extends Voiture> listeVoiture){
		for(Voiture v : listeVoiture)
			list.add(v);
		
		System.out.println("Contenu de notre garage :");
		for(Voiture v : list)		
			System.out.println(v.toString());
	}
}
