import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

public class Fenetre4 extends JFrame {
	private JTable tableau;
	private JButton change = new JButton("Changer la taille");
	//contenu du combo
	private String[] comboData = {"Grand", "Moyen", "Petit"};
	private JButton ajouter = new JButton("Ajouter une ligne");
	
	public Fenetre4(){
		this.setSize(600, 180);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("JTable");
		
		//Données de notre tableau
		Object[][] data = {   
		      {"Cysboy", "6boy", comboData[0], new Boolean(true), "Supprimer la ligne"},
		      {"BZHHydde", "BZH", comboData[0], new Boolean(false), "Supprimer la ligne"},
		      {"IamBow", "BoW", comboData[0], new Boolean(false), "Supprimer la ligne"},
		      {"FunMan", "Year", comboData[0], new Boolean(true), "Supprimer la ligne"}
	    };
		String  title[] = {"Pseudo", "Age", "Taille", "OK ?", "Suppression"};
		
		JComboBox combo = new JComboBox(comboData);
		
		//JE DOIS UTILISER UN MODELE D'AFFICHAGE SPECIFIQUE POUR PALLIER LES BUGS D'AFFICHAGE
		
		ZModel model = new ZModel(data, title);
		
		this.tableau = new JTable(model);
		//this.tableau = new JTable(new DefaultTableModel(data, title));
		this.tableau.setRowHeight(30);
		this.tableau.getColumn("Age").setCellRenderer(new ButtonRenderer());
		this.tableau.getColumn("Age").setCellEditor(new ButtonEditor4(new JCheckBox()));
		this.tableau.getColumn("Taille").setCellRenderer(new ComboRenderer());
		//On définit l'éditeur par défaut pour la cellule
		//En lui spécifiant quel type d'affichage prendre en compte
		this.tableau.getColumn("Taille").setCellEditor(new DefaultCellEditor(combo));
		//je définis l'éditeur pour la colonne suppression
		this.tableau.getColumn("Suppression").setCellEditor(new ButtonEditor4(new JCheckBox()));
		this.getContentPane().add(new JScrollPane(tableau));
		ajouter.addActionListener(new MoreListener());
		this.getContentPane().add(ajouter, BorderLayout.SOUTH);
	}
	
	class MoreListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			Object[] ligne = {"Mago", "Mag", comboData[0], new Boolean(false), 
			"Supprimer la ligne"};
			((ZModel)tableau.getModel()).addRow(ligne);
			
		}
	}
	/*
	class ZModel extends AbstractTableModel {
		private Object[][] data;
		private String[] title;
		//Constructeur
		public ZModel(Object[][] data, String[] title){
			this.data = data;
			this.title = title;
		}
		
		//retourne le titre de la colonne à l'indice spécifié
		public String getColumnName(int col){
			return this.title[col];
		}
		
		//retourne le nombre de colonnes
		public int getColumnCount(){
			return this.title.length;
		}
		
		//retourne le nombre de lignes
		public int getRowCount(){
			return this.data.length;
		}
		
		//retourne la valeur à l'emplacement spécifié
		public Object getValueAt(int row, int col){
			return this.data[row][col];
		}
		
		//définit la valeur à l'emplacement spécifié
		public void setValueAt(Object value, int row, int col){
			//on interdit la modification sur certaines colonnes
			if(!this.getColumnName(col).equals("Age") && 
					!this.getColumnName(col).equals("Suppression"))
			this.data[row][col] = value;
		}
		
		//retourne la classe de la donnée de la colonne
		public Class getColumnClass(int col){
			return this.data[0][col].getClass();
		}
		
		public boolean isCellEditable(int row, int col){
			return true;
		}
		
		public void addRow(Object[] data){
			int indice = 0, rowCount = this.getRowCount() + 1;
			int columnCount = this.getColumnCount();
			Object[][] temp = this.data;
			this.data = new Object[rowCount][columnCount];
			for(Object[] ligne : temp)
				this.data[indice++] = ligne;
			this.data[indice] = data;
			
			this.fireTableDataChanged();
		}
		
		public void removeRow(int row){
			int indice = 0, rowCount = this.getRowCount(), columnCount = this.getColumnCount();
			Object[][] temp = this.data;
			this.data = new Object[rowCount - 1][columnCount];
			for(Object[] ligne : temp){
				indice++;
				if(indice != row)
					this.data[indice] = ligne;
			}
			
			this.fireTableDataChanged();
			
		}
		
		
	}
	*/
	

	public static void main(String[] args) {
		Fenetre4 fen = new Fenetre4();
		fen.setVisible(true);
	}

}
