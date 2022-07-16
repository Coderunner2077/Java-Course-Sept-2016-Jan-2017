
public class Ville {
	protected String nomVille;
	protected String nomPays;
	protected int nbHabitants;
	protected char categorie;
	public static int nbInstances = 0;
	protected static int nbInstancesBis = 0;
	private final String attribut;
	

	
	//Constructeur par défaut
	public Ville()
	{
		System.out.println("Création d'une ville par défaut !");
		nomVille = "inconnu";
		nomPays = "inconnu";
		nbHabitants = 0;
		nbInstances++;
		nbInstancesBis++;
		this.setCategorie();
		nbInstances = nbInstancesBis;
		attribut = "Attribut";
	}
	//Constructeur avec paramètres
	public Ville(String pNom, int pNbre, String pPays)
	{
		System.out.println("Création d'une ville spécifique !");
		nomVille = pNom;
		nomPays = pPays;
		nbHabitants = pNbre;
		nbInstances++;
		nbInstancesBis++;
		this.setCategorie();	
		nbInstances = nbInstancesBis;
		attribut = "Attribut";
	}
	//Les accesseurs
	/**
	 * Accesseur nom ville
	 * @return String retourne le nom de la ville
	 */
	public String getNomVille()
	{
		return nomVille;
	}
	/**
	 * Accesseur nom pays
	 * @return String retourne le nom du pays
	 */
	public String getNomPays()
	{
		return nomPays;
	}
	/**
	 * Accesseur nombre habitants
	 * @return int retourne le nombre d'habitants
	 */
	public int getNbreHabitants()
	{
		return nbHabitants;
	}
	/**
	 * Accesseur categorie
	 * @return char retourne la catégorie
	 */
	public char getCategorie()
	{
		return categorie;
	}
	
	/**
	 * Accesseur & méthode de classe  nombre d'instances Ville
	 * @return int retourne le nombre d'instances Ville
	 */
	public static int getNbreInstancesBis()
	{
		return nbInstancesBis;
	}
	
	//Setters
	/**
	 * Setter nom ville
	 * @param pNom
	 */
	public void setNomVille(String pNom)
	{
		nomVille = pNom;
	}
	/**
	 * Setter nom pays
	 * @param pPays
	 */
	public void setNomPays(String pPays)
	{
		nomPays = pPays;
	}
	/**
	 * Setter nombre instances ville
	 * @param pNbre
	 */
	public void setNbreHabitants(int pNbre)
	{
		nbHabitants = pNbre;
	}
	
	//détermine la catégorie
	protected void setCategorie()
	{
		int bornesSuperieures[] = {0, 1000, 10000, 100000, 500000, 1000000, 5000000, 10000000};
		char categorie[] = {'?', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
		int i =0;
		while(i < bornesSuperieures.length && this.nbHabitants > bornesSuperieures[i])
			i++;
		this.categorie = categorie[i];
	}
	/**
	 * Méthode d'instance
	 * @return String retourne la description de l'objet
	 */
	public String decrisToi()//!!!!!!!!
	{
		return "\t "+this.nomVille+" est une ville de "+this.nomPays+". Sa population est de "
	+this.nbHabitants+" habitants ==> elle est donc de categorie "+this.categorie;
	}
	/**
	 * Méthode d'instance
	 * @return String retourne la description de l'objet
	 */
	public String toString()
	{
		return "\t "+this.nomVille+" est une ville de "+this.nomPays+". Sa population est de "
				+this.nbHabitants+" habitants ==> elle est donc de categorie "+this.categorie;
	}
	
	/**
	 * Méthode d'instance
	 * @param v2 
	 * @return String retourne le résultat de la comparaison entre les populations des deux villes 
	 */
	public String comparer(Ville v2)
	{
		String str = new String();
		if(this.nbHabitants > v2.getNbreHabitants())
			str = this.nbHabitants +" est plus peuplée que "+ v2.getNbreHabitants();
		else if(this.nbHabitants < v2.getNbreHabitants())
			str = v2.getNbreHabitants()+" est plus peuplée que "+this.nbHabitants;
		else
			str = this.nbHabitants+" et "+ v2.getNbreHabitants()+" ont le même nombre d'habitants";
		return str;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		//On définit un multiplicateur impair, de préférence un nombre premier (pour avoir l'unicite du résultat final
		final int prime = 31;
		//On définit un résultat qui sera envoyé au final
		int result = 1;
		//On ajoute entre eux la multiplication des attributs et du multiplicateur
		result = prime * result + categorie;
		result = prime * result + nbHabitants;
		//Lorsque l'on doit gérer des hashcode avec des objets dans le mode de calcul
		//on doit vérifier si l'objet n'est pas null, sinon on aura une erreur
		result = prime * result + ((nomPays == null) ? 0 : nomPays.hashCode());
		result = prime * result + ((nomVille == null) ? 0 : nomVille.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		//on vérifie si les références d'objets sont identiques
		if (this == obj)
			return true;
		//on vérifie si l'objet passé en paramètre est null
		if (obj == null)
			return false;
		//On s'assure que les objets comparés sont du même type, ici de type Ville
		//Maintenant, on compare les attributs de nos objets
		if (getClass() != obj.getClass())
			return false;
		Ville other = (Ville) obj;
		if (categorie != other.categorie)
			return false;
		if (nbHabitants != other.nbHabitants)
			return false;
		if (nomPays == null) {
			if (other.nomPays != null)
				return false;
		} else if (!nomPays.equals(other.nomPays))
			return false;
		if (nomVille == null) {
			if (other.nomVille != null)
				return false;
		} else if (!nomVille.equals(other.nomVille))
			return false;
		return true;
	}
	
}

