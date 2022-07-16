import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class ButtonEditor extends DefaultCellEditor {

	private JButton button;
	private boolean isPushed;
	
	private ButtonListener bListener = new ButtonListener();
	
	public ButtonEditor(JCheckBox checkBox){
		super(checkBox);
		button = new JButton(); // !!!!!!!
		button.setOpaque(true);// !!!!!!!!!
		button.addActionListener(bListener);
	}
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
			int row, int column){
		bListener.setRow(row);
		bListener.setColumn(column);
		bListener.setTable(table);
		button.setText((value.toString() == null) ? "": value.toString());// !!!!!!!!!!!!!
		return button;
	}
	
	class ButtonListener implements ActionListener {
		private int row, column;
		private JTable table;
		private JButton button;
		private int nbre = 0;
		
		public void setRow(int row) { this.row = row; }
		public void setColumn(int column) { this.column = column; }
		public void setTable(JTable table) { this.table = table; }
		
		public JButton getButton(){
			return this.button;
		}
		
		public void actionPerformed(ActionEvent event){
			if(column == 1){
				System.out.println("Bouton appuyé : " + ((JButton)event.getSource()).getText());
				((AbstractTableModel)table.getModel()).setValueAt("New Value "+(++nbre), row, 
						column - 1);
				((AbstractTableModel)table.getModel()).fireTableCellUpdated(row, column - 1); // !!!!!!!
			}
			else if(column != 1 && ((ZModel)table.getModel()).getRowCount() > 0){
				((ZModel)table.getModel()).removeRow(row);
			}
			
			button = (JButton)event.getSource(); // vraiment nécessaire ??
		}
	}
	 
}
