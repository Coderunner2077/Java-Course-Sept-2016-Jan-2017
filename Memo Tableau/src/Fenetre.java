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
import javax.swing.table.TableColumn;

public class Fenetre extends JFrame {
	private JTable tableau;
	private JButton ajouter = new JButton("Ajouter une fenetre");
	private String suppr = "Supprimer la ligne";
	private JComboBox combo;
	private String[] comboData = {"Grand", "Moyen", "Petit"};
	
	public Fenetre(){
		this.setTitle("Interface de tableau");
		this.setSize(600, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		Object[][] data = { 
				{"Yeahman", "yMan", comboData[0], new Boolean(true), suppr}, // !! boolean
				{"NoMan", "nMan", comboData[0], new Boolean(false), suppr},
				{"PtetMan", "pMan", comboData[0], new Boolean(true), suppr},
				{"Avtomatitchk", "avto", comboData[0], new Boolean(true), suppr}
		};
		String[] title = {"Pseudo", "bouton", "Taille", "OK?", "Suppression"};
		combo = new JComboBox(comboData);
		
		ZModel zModel = new ZModel(data, title);
		tableau = new JTable(zModel);
		tableau.setRowHeight(50);
		TableColumn col = tableau.getColumn("OK?");
		col.setPreferredWidth(5);
		TableColumn col2 = tableau.getColumn("Suppression");
		col2.setPreferredWidth(100);
		
		tableau.getColumn("bouton").setCellRenderer(new ButtonRenderer());
		tableau.getColumn("Taille").setCellRenderer(new ComboRenderer());
		tableau.getColumn("bouton").setCellEditor(new ButtonEditor(new JCheckBox()));
		tableau.getColumn("Taille").setCellEditor(new DefaultCellEditor(combo));
		tableau.getColumn("Suppression").setCellRenderer(new ButtonRenderer());
		tableau.getColumn("Suppression").setCellEditor(new ButtonEditor(new JCheckBox()));
		
		
		ajouter.addActionListener(new MoreListener());
		this.getContentPane().add(new JScrollPane(tableau));
		this.getContentPane().add(ajouter, BorderLayout.SOUTH);
		
	}
	class MoreListener implements ActionListener {
		public void actionPerformed(ActionEvent event){
			Object[] newData = {"MagoBoy", "mBoy", comboData[2], new Boolean(true), suppr};
			((ZModel)tableau.getModel()).addRow(newData);
		}
	}

	public static void main(String[] args) {
		Fenetre fen = new Fenetre();
		fen.setVisible(true);

	}

}
