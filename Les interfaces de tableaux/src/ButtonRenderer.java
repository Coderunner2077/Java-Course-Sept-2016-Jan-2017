import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean 
			isSelected, boolean hasFocus, int row, int column){
		//On écrit dans le bouton ce que contient la cellule
		setText((value != null) ? value.toString() : "");
		//je retourne mon bouton
		return this;
	}

}
