import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class Fenetre2 extends JFrame {
	private JTable tableau;
	private JButton change = new JButton("Changer la taille");
	
	public Fenetre2(){
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("JTable");
	    this.setSize(600, 200);
	    
	    /*
	    Object[][] data = {
	    		{"Cysboy", new JButton("6boy"), new Double(1.80), new Boolean(true)},
	    		{"BZHHydde", new JButton("BZH"), new Double(1.78), new Boolean(false)},
	    	    {"IamBow", new JButton("BoW"), new Double(1.90), new Boolean(false)},
	    	    {"FunMan", new JButton("Year"), new Double(1.85), new Boolean(true)}
	    };
	    */
	    Object[][] data = {
	    		{"Cysboy", new JButton("6boy"), new JComboBox(new String[]{"Grand", "Moyen", "Petit"}), new Boolean(true)},
	    		{"BZHHydde", new JButton("BZH"), new JComboBox(new String[]{"Grand", "Moyen", "Petit"}), new Boolean(false)},
	    	    {"IamBow", new JButton("BoW"), new JComboBox(new String[]{"Grand", "Moyen", "Petit"}), new Boolean(false)},
	    	    {"FunMan", new JButton("Year"), new JComboBox(new String[]{"Grand", "Moyen", "Petit"}), new Boolean(true)}
	    };
	    
	    
	    String title[] = {"Pseudo", "Age", "Taille", "Ok?"};
	    ZModel model = new ZModel(data, title);
	    System.out.println("Nombre de colonnes : " + model.getColumnCount());
	    System.out.println("Nombre de lignes : " + model.getRowCount());
	    
	    this.tableau = new JTable(model);
	    this.tableau.setDefaultRenderer(JComponent.class, new TableComponent());
	    this.getContentPane().add(new JScrollPane(tableau));
	    this.setVisible(true);
	    
	}
	//Classe modèle personnalisée
	class ZModel extends AbstractTableModel {
		private Object[][] data;
		private String[] title;
		
		//Constructeur 
		public ZModel(Object[][] data, String[] title){
			this.data = data;
			this.title = title;
		}
		
		//retourne le nombre de colonnes
		public int getColumnCount(){
			return this.title.length;
		}
		
		//retourne le nb de lignes
		public int getRowCount(){
			return this.data.length;
		}
		
		//retourne la valeur à l'emplacement spécifié
		public Object getValueAt(int row, int col){
			return this.data[row][col];
		}
		
		//Retourne le titre de la colonne à l'indice demandé => pour faire apparaître les titres
		public String getColumnName(int col){
			return this.title[col];
		}
		
		//Retourne la classe de la donnée de la colonne
		public Class getColumnClass(int col){ // ==> transforme les boléens en cases à cocher
			//on retourne le type de la cellule à la colonne demandée
			//on se moque de la ligne puisque les types de données sont les mêmes quelque soit
			//la ligne
			return this.data[0][col].getClass();
		}
		
		public boolean isCellEditable(int row, int col){
			if(getValueAt(row, col) instanceof JButton)
				return false;
			return true;
		}
	}
	
	
	
	public static void main(String[] args) {
		Fenetre2 fen = new Fenetre2();

	}

}
