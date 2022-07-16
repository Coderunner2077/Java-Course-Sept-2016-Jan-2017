import java.awt.Component;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ComboRenderer extends JComboBox implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean 
			isSelected, boolean hasFocus, int row, int column){
		this.addItem("Grand");
		this.addItem("Moyen");
		this.addItem("Petit");
		
		Object selected = ((ZModel)table.getModel()).getValueAt(row, column);
		if(selected != null)
			this.setSelectedItem(selected);
		return this;
	}

}
