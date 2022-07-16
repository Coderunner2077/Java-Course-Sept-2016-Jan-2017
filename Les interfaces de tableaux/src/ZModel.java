import javax.swing.table.AbstractTableModel;

class ZModel extends AbstractTableModel {
		private Object[][] data;
		private String[] title;
		//Constructeur
		public ZModel(Object[][] data, String[] title){
			this.data = data;
			this.title = title;
		}
		
		//retourne le titre de la colonne � l'indice sp�cifi�
		public String getColumnName(int col){
			return this.title[col];
		}
		
		//retourne le nombre de colonnes
		public int getColumnCount(){
			return this.title.length;
		}
		
		//retourne le nombre de lignes
		public int getRowCount(){
			return this.data.length;
		}
		
		//retourne la valeur � l'emplacement sp�cifi�
		public Object getValueAt(int row, int col){
			return this.data[row][col];
		}
		
		//d�finit la valeur � l'emplacement sp�cifi�
		public void setValueAt(Object value, int row, int col){
			//on interdit la modification sur certaines colonnes
			if(!this.getColumnName(col).equals("Age") && 
					!this.getColumnName(col).equals("Suppression"))
			this.data[row][col] = value;
		}
		
		//retourne la classe de la donn�e de la colonne
		public Class getColumnClass(int col){
			return this.data[0][col].getClass();
		}
		
		
		public boolean isCellEditable(int row, int col){
			return true;
		}
		
		public void addRow(Object[] data){
			int indice = 0, rowCount = this.getRowCount() + 1;
			int columnCount = this.getColumnCount();
			Object[][] temp = this.data;
			this.data = new Object[rowCount][columnCount];
			for(Object[] ligne : temp)
				this.data[indice++] = ligne;
			this.data[indice] = data;
			
			temp = null;
			//Cette m�thode permet d'avertir le tableau que les donn�es ont chang�
			//ce qui permet une mise � jour compl�te du tableau
			this.fireTableDataChanged();
		}
		
		public void removeRow(int row){
			int indice = 0, rowCount = this.getRowCount(), columnCount = this.getColumnCount(),
					indice2 = 0;
			Object[][] temp = new Object[rowCount - 1][columnCount];
			
			for(Object[] ligne : this.data){
				if(indice != row){
					System.out.println("Ligne copi�e : " + indice2);
					temp[indice2++] = ligne;
				}
				else
					System.out.println("Ligne supprim�e : " + indice);
				indice++;
					
			}
			this.data = temp;
			temp = null;
			
			this.fireTableDataChanged();
			
		}
		
		
	}
