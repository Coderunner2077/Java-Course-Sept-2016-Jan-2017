import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {
	protected JButton button;
	private boolean isPushed;
	private ButtonListener bListener = new ButtonListener();
	
	//constructeur avec une JCheckBox
	public ButtonEditor(JCheckBox checkBox){
		//Par d�faut, ce type d'objet travaille avec un JCheckBox
		super(checkBox);
		//On cr�e de nouveau un bouton
		button = new JButton();
		button.setOpaque(true);
		//On lui attribue un listener
		button.addActionListener(bListener);
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
			int row, int column){
		//Je pr�cise le num�ro de ligne � mon listener
		bListener.setRow(row);
		//Idem pour le num�ro de colonne
		bListener.setColumn(column);
		//On passe aussi le tableau en param�tre pour des actions potentielles
		bListener.setTable(table);
		
		//On r�affecte le libell� du bouton
		button.setText((value == null) ? "voila" : value.toString());
		//On renvoie le bouton
		return button;
	}

	//Le listener pour le bouton
	class ButtonListener implements ActionListener {
		private int column, row;
		private JTable table;
		private int nbre = 0;
		
		private void setRow(int row){ this.row = row; }
		private void setColumn(int column){ this.column = column; }
		private void setTable(JTable table){ this.table = table; }
		
		public void actionPerformed(ActionEvent event){
			//On affiche un message, mais je pourrai effectuer les traitements que je veux
			System.out.println("coucou du bouton : " + ((JButton)event.getSource()).getText());
			//on affecte un nouveau libell� � une autre cellule de la ligne
			table.setValueAt("New Value " + (++nbre), this.row, (this.column - 1));
		}
		
	}
}
