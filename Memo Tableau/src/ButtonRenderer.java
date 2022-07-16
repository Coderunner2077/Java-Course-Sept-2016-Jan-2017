import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean 
			isSelected, boolean hasFocus, int row, int column){
		setText((value.toString() == null) ? "" : value.toString());
		
		return this;
		// !!!!! pas de condition du genre if(value instanceof JButton) ici !!!
	}
}
