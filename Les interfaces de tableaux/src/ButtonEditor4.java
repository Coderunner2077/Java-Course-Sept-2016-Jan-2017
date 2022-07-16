import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ButtonEditor4 extends DefaultCellEditor {
	protected JButton button;
	private boolean isPushed;
	private ButtonListener bListener = new ButtonListener();
	

	public ButtonEditor4(JCheckBox checkBox){
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(bListener);
	}
	
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
			int row, int column){
		bListener.setRow(row);
		bListener.setColumn(column);
		//On passe le tableau en paramètre pour des actions potentielles
		bListener.setTable(table);
		//On réaffecte le libellé du bouton
		button.setText((value == null) ? "" : value.toString());
		//on renoie le bouton
		return button;
	}
	
	//Mon listener pour le bouton
	class ButtonListener implements ActionListener {
		private int column, row;
		private JTable table;
		private int nbre = 0;
		private JButton button;
		
		public void setColumn(int column){ this.column = column; }
		public void setRow(int row){ this.row = row; }
		public void setTable(JTable table){ this.table = table; }
		
		public JButton getButton(){ return this.button; }
		
		public void actionPerformed(ActionEvent event){
			if(this.column == 1){
				System.out.println("Coucou du bouton : " + ((JButton)event.getSource()).getText());
				//On affecte un nouveau libellé à une cellule de la ligne
				((AbstractTableModel)table.getModel()).setValueAt("New value " + (++nbre),this.row,
						(this.column - 1));
				//Permet de dire à mon tableau qu'une valeur a changé à l'emplacement déterminé
				//par les valeurs passées en paramètres
				((AbstractTableModel)table.getModel()).fireTableCellUpdated(this.row, 
						this.column - 1);
			}else if(this.column != 1 && table.getRowCount() > 0) {
				((ZModel)table.getModel()).removeRow(this.row);
			}
			this.button = (JButton)event.getSource();
			
		}
	
	}
}
