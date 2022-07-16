
public class ClasseMain {

	public static void main(String[] args) {
		/*
		 * Généralement, il n'y a qu'une seule méthode main par projet. Cependant, plusieurs méthodes main peuvent 
		 * cohabiter mais une seule sera considérée comme le point de départ de mon projet.
		 */
		Ville ville = new Ville();
		Ville ville1 = new Ville("Nice", 343304, "France");
		//ville1.nomVille = "Brice"; // je peux (non, pouvais) changer directement les valeurs de mes instances.
		//System.out.println(ville1.nomVille); invalide car j'ai rendu mes variables d'instance private
		Ville ville2 = new Ville("Marseille", 850726, "France");
		Ville ville3 = new Ville("Rio", 6320000, "Brésil");
		System.out.println("\n ville = "+ville.getNom()+", ville de "+ville.getNbHabitants()+" habitants se "
				+ "situant en "+ville.getNomPays());
		System.out.println("\n ville1 = "+ville1.getNom()+", ville de "+ville1.getNbHabitants()+" habitants se "
				+ "situant en "+ville1.getNomPays());
		System.out.println("\n ville3 = "+ville3.getNom()+", ville de "+ville3.getNbHabitants()+" habitants se "
				+ "situant en "+ville3.getNomPays());
		//JE VAIS INTERCHANGER LES Villes ville1 ET ville3, par l'intermédiaire d'un autre objet Ville
		Ville temp = new Ville();
		temp = ville1;
		ville1 = ville3;
		ville3 = temp;
		System.out.println("\n ville1 = "+ville1.getNom()+", ville de "+ville1.getNbHabitants()+" habitants se "
				+ "situant en "+ville1.getNomPays());
		System.out.println("\n ville3 = "+ville3.getNom()+", ville de "+ville3.getNbHabitants()+" habitants se "
				+ "situant en "+ville3.getNomPays());
		// JE VAIS INTERCHANGER LEUR NOM PAR LE BIAIS DE LEUR MUTATEUR
		Ville tempe = new Ville();
		tempe.setNom(ville1.getNom());
		ville1.setNom(ville3.getNom());
		ville3.setNom(tempe.getNom());
		// Bon, je reviens à la normale:
		ville1.setNom("Rio");
		ville3.setNom("Nice");
		System.out.println("\n ville1 = "+ville1.getNom()+", ville de "+ville1.getNbHabitants()+" habitants se "
				+ "situant en "+ville1.getNomPays());
		System.out.println("\n ville3 = "+ville3.getNom()+", ville de "+ville3.getNbHabitants()+" habitants se "
				+ "situant en "+ville3.getNomPays());
		System.out.println("Test des méthodes d'instance");
		System.out.println("\n"+ville.decrisToi());
		System.out.println("\n\n"+ville1.decrisToi());
		System.out.println("\n"+ville3.decrisToi()+"\n");
		System.out.println(ville1.comparer(ville3));
		//Ville.nbInstances = 9;
		System.out.println("Le nombre d'instances de la classe Ville est " + Ville.nbInstances);
		System.out.println("Le nombre d'instances de la classe Ville est " + Ville.getNbInstancesBis());
		
		
		
		
		
		
		
		
		
		
	}

}
