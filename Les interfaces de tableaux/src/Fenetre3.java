import java.awt.BorderLayout;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Fenetre3 extends JFrame {
	  private JTable tableau;
	  private JButton change = new JButton("Changer la taille");
	  private String[] comboData = {"Grand", "Moyen", "Petit"};

	  public Fenetre3(){
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("JTable");
	    this.setSize(600, 180);

	    Object[][] data = {   
	      {"Cysboy", "6boy", "Combo", new Boolean(true)},
	      {"BZHHydde", "BZH", "Combo", new Boolean(false)},
	      {"IamBow", "BoW", "Combo", new Boolean(false)},
	      {"FunMan", "Year", "Combo", new Boolean(true)}
	    };

	    String  title[] = {"Pseudo", "Age", "Taille", "OK ?"};
	    JComboBox combo = new JComboBox(comboData);
	    this.tableau = new JTable(data, title);
	    this.tableau.getColumn("Age").setCellRenderer(new ButtonRenderer());
	    this.tableau.getColumn("Taille").setCellRenderer(new ComboRenderer());
	    this.tableau.getColumn("Age").setCellEditor(new ButtonEditor(new JCheckBox()));
	    //On définit l'éditeur par défaut pour la cellule en lui spécifiant quel type 
	    //d'affichage prendre en compte
	    this.tableau.getColumn("Taille").setCellEditor(new DefaultCellEditor(combo));
	    this.tableau.setRowHeight(30);
	    this.getContentPane().add(new JScrollPane(tableau), BorderLayout.NORTH);
	    
	  }

	public static void main(String[] args) {
		Fenetre3 fen = new Fenetre3();
		fen.setVisible(true);
	}

}
