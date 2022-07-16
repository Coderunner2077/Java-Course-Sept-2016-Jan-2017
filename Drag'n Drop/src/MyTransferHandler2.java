import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class MyTransferHandler2 extends TransferHandler {
	public boolean canImport(TransferHandler.TransferSupport info){
		if(!info.isDataFlavorSupported(DataFlavor.stringFlavor))
			return false;
		return true;
	}
	
	public boolean importData(TransferHandler.TransferSupport support){
		if(!canImport(support))
			return false;
		
		Transferable data = support.getTransferable();
		String str = "";
		try{
			str = (String)data.getTransferData(DataFlavor.stringFlavor);
		}catch(UnsupportedFlavorException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		JLabel lab = (JLabel)support.getComponent();
		lab.setText(lab.getText() + str);
		
		return false;
	}
	
	protected void exportDone(JComponent c, Transferable t, int action){
		if(action == MOVE){
			JLabel lab = (JLabel)c;
			String text = lab.getText();
			int indice = 0;
			try{
				indice = Integer.parseInt(text.substring(text.length() - 1, text.length()));
				lab.setText(text.substring(0, text.length() - 1) + (++indice));
			}catch(NumberFormatException e){
				e.printStackTrace();
				lab.setText(lab.getText() + " " + (++indice));
			}
		}
	}
	
	protected Transferable createTransferable(JComponent c){
		return new StringSelection(((JLabel)c).getText());
	}
	
	public int getSourceActions(JComponent c){ //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! actionS !!!!!!!
		return COPY_OR_MOVE;
	}
}
