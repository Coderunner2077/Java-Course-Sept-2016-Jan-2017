import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class Fenetre1 extends JFrame {
	private JTable tableau;
	private JButton change = new JButton("Changer la taille"),
					retablir = new JButton("Rétablir");
	
	public Fenetre1(){
		this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("JTable");
	    this.setSize(300, 300);
	    
	    //Les données du tableau
	    Object[][] data = {
	    	 {"Cysboy", "28 ans", "1.80 m"},
	         {"BZHHydde", "28 ans", "1.80 m"},
	         {"IamBow", "24 ans", "1.90 m"},
	         {"FunMan", "32 ans", "1.85 m"}
	    };
	    
	    //Les titres des colonnes
	    String[] title = {"Pseudo", "Age", "Taille"};
	    tableau = new JTable(data, title);
	    
	    JPanel pan = new JPanel();
	    
	    change.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		changeSize(200, 80);
	    		change.setEnabled(false);
	    		retablir.setEnabled(true);
	    	}
	    });
	    
	    retablir.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		changeSize(75, 16);
	    		change.setEnabled(true);
	    		retablir.setEnabled(false);
	    	}
	    });
	    
	    retablir.setEnabled(false);
	    pan.add(change);
	    pan.add(retablir);  
	    
	    //J'ajoute mon tableau au content pane dans un scroll
	    //SINON LES TITRES DES COLONNES NE S'AFFICHERONT PAS
	    this.getContentPane().add(new JScrollPane(tableau));
	    this.getContentPane().add(pan, BorderLayout.SOUTH);
	    this.setVisible(true);

	}
	/**
	 * Change la taille d'une ligne et d'une colonne 
	 */
	public void changeSize(int width, int height){
		//Je crée un objet TableColumn afin de travailler sur ma colonne
		TableColumn col;
		for(int i = 0; i < tableau.getColumnCount(); i++){
			if(i == 1){
				//On récupère le modèle de la colonne
				col = tableau.getColumnModel().getColumn(i);
				//Je lui affecte la nouvelle valeur
				col.setPreferredWidth(width);
			}
		}
		for(int i = 0; i < tableau.getRowCount(); i++){
			//On affecte la taille de la ligne à l'indice spécifié !
			if(i == 1)
				tableau.setRowHeight(i, height);
		}
	}
	
	
	public static void main(String[] args) {
		Fenetre1 fen = new Fenetre1();

	}

}
