
public class Ville {
	protected String nomVille;
	protected String nomPays;
	protected int nbHabitants;
	protected char categorie;
	public static int nbInstances = 0;
	protected static int nbInstancesBis = 0;
	private final String attribut;
	

	
	//Constructeur par d�faut
	public Ville()
	{
		System.out.println("Cr�ation d'une ville par d�faut !");
		nomVille = "inconnu";
		nomPays = "inconnu";
		nbHabitants = 0;
		nbInstances++;
		nbInstancesBis++;
		this.setCategorie();
		nbInstances = nbInstancesBis;
		attribut = "Attribut";
	}
	//Constructeur avec param�tres
	public Ville(String pNom, int pNbre, String pPays)
	{
		System.out.println("Cr�ation d'une ville sp�cifique !");
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
	 * @return char retourne la cat�gorie
	 */
	public char getCategorie()
	{
		return categorie;
	}
	
	/**
	 * Accesseur & m�thode de classe  nombre d'instances Ville
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
	
	//d�termine la cat�gorie
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
	 * M�thode d'instance
	 * @return String retourne la description de l'objet
	 */
	public String decrisToi()//!!!!!!!!
	{
		return "\t "+this.nomVille+" est une ville de "+this.nomPays+". Sa population est de "
	+this.nbHabitants+" habitants ==> elle est donc de categorie "+this.categorie;
	}
	/**
	 * M�thode d'instance
	 * @return String retourne la description de l'objet
	 */
	public String toString()
	{
		return "\t "+this.nomVille+" est une ville de "+this.nomPays+". Sa population est de "
				+this.nbHabitants+" habitants ==> elle est donc de categorie "+this.categorie;
	}
	
	/**
	 * M�thode d'instance
	 * @param v2 
	 * @return String retourne le r�sultat de la comparaison entre les populations des deux villes 
	 */
	public String comparer(Ville v2)
	{
		String str = new String();
		if(this.nbHabitants > v2.getNbreHabitants())
			str = this.nbHabitants +" est plus peupl�e que "+ v2.getNbreHabitants();
		else if(this.nbHabitants < v2.getNbreHabitants())
			str = v2.getNbreHabitants()+" est plus peupl�e que "+this.nbHabitants;
		else
			str = this.nbHabitants+" et "+ v2.getNbreHabitants()+" ont le m�me nombre d'habitants";
		return str;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		//On d�finit un multiplicateur impair, de pr�f�rence un nombre premier (pour avoir l'unicite du r�sultat final
		final int prime = 31;
		//On d�finit un r�sultat qui sera envoy� au final
		int result = 1;
		//On ajoute entre eux la multiplication des attributs et du multiplicateur
		result = prime * result + categorie;
		result = prime * result + nbHabitants;
		//Lorsque l'on doit g�rer des hashcode avec des objets dans le mode de calcul
		//on doit v�rifier si l'objet n'est pas null, sinon on aura une erreur
		result = prime * result + ((nomPays == null) ? 0 : nomPays.hashCode());
		result = prime * result + ((nomVille == null) ? 0 : nomVille.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		//on v�rifie si les r�f�rences d'objets sont identiques
		if (this == obj)
			return true;
		//on v�rifie si l'objet pass� en param�tre est null
		if (obj == null)
			return false;
		//On s'assure que les objets compar�s sont du m�me type, ici de type Ville
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

