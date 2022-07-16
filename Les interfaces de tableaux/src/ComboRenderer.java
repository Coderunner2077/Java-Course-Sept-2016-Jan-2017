import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComboRenderer extends JComboBox implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean 
			isSelected, boolean hasFocus, int row, int column){
		addItem("Grand");
		addItem("Moyen");
		addItem("Petit");
		//je synchronise l'affichage des combo : 
		Object selected = ((ZModel)table.getModel()).getValueAt(row, column);
		this.setSelectedItem(selected);
		return this;
	}

	

}
