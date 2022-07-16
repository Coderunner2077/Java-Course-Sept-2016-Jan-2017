import javax.swing.table.AbstractTableModel;

public class ZModel extends AbstractTableModel {
	private Object[][] data;
	private String[] title;
	
	public ZModel(Object[][] data, String[] title){
		this.data = data;
		this.title = title;
	}
	
	public int getRowCount(){
		return this.data.length;
	}
	
	public int getColumnCount(){
		return this.title.length;
	}
	
	public Object getValueAt(int row, int column){
		return this.data[row][column];
	}
	
	public Class getColumnClass(int col){
		return this.data[0][col].getClass();
	}
	
	public String getColumnName(int column){
		return this.title[column];
	}
	
	public boolean isCellEditable(int row, int column){
		return true;
	}
	
	public void setValueAt(Object value, int row, int column){
		if(!this.getColumnName(column).equals("bouton")
				&& !this.getColumnName(column).equals("Suppression"))
		this.data[row][column] = value;
	}
	
	public void addRow(Object[] ligne){ // !!!! pas int row
		int indice = 0;
		int rowCount = this.getRowCount() + 1;
		int columnCount = this.getColumnCount();
		Object[][] temp = new Object[rowCount][columnCount];
		for(Object[] line : this.data){
			temp[indice++] = line;
		}
		temp[indice] = ligne;
		this.data = temp;
		temp = null;
		this.fireTableDataChanged();
	}
	
	public void removeRow(int row){
		int indice = 0, indice2 = 0;
		int rowCount = this.getRowCount();
		int columnCount = this.getColumnCount();
		Object[][] temp = new Object[rowCount - 1][columnCount];
		for(Object[] line : this.data){
			if(indice++ != row)
				temp[indice2++] = line;
			
		}
		this.data = temp;
		temp = null;
		this.fireTableDataChanged();
	}
	
	
	
}
