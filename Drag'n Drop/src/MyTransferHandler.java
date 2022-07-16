import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class MyTransferHandler extends TransferHandler {
	
	/**
	  * M�thode permettant � l'objet de savoir si les donn�es re�ues
	  * via un drop sont autoris�es � �tre import�es
	  * @param info
	  * @return boolean
	  */
	  public boolean canImport(TransferHandler.TransferSupport info) {
		//Nous contr�lons si les donn�es re�ues sont d'un type autoris�, ici String
		  if(!info.isDataFlavorSupported(DataFlavor.stringFlavor))
			  return false;
		  return true;
	  }

	  /**
	  * C'est ici que l'insertion des donn�es dans notre composant est r�alis�e
	  * @param support
	  * @return boolean
	  */
	  public boolean importData(TransferHandler.TransferSupport support){
		  //je contr�le si les donn�es re�ues sont d'un type autoris�
		  if(!canImport(support))
			  return false;
		  
		  //Je r�cup�re mon objet Transferable, celui qui contient les donn�es en transit
		  Transferable data = support.getTransferable();
		  String str = "";
		  try{
			  //je r�cup�re les donn�es en sp�cifiant ce que j'attends
			  str = (String)data.getTransferData(DataFlavor.stringFlavor);
		  } catch(UnsupportedFlavorException e){
			  e.printStackTrace();
		  }catch(IOException e){
			  e.printStackTrace();
		  }
		  
		  //Via le TransfertSupport, je peux r�cup�rer mon composant
		  JLabel lab = (JLabel)support.getComponent(); //!!!!!!
		  //afin de lui affecter sa nouvelle valeur
		  lab.setText(str);
		  
		  return false;
	  }

	  /**
	  * Cette m�thode est invoqu�e � la fin de l'action DROP
	  * Si des actions sont � faire ensuite, c'est ici qu'il faudra coder le comportement d�sir�
	  * @param c
	  * @param t
	  * @param action
	  */
	  protected void exportDone(JComponent c, Transferable t, int action){
		  // Une fois le drop effectu�, j'efface le contenu de mon JLabel
		  if(action == MOVE)
			  ((JLabel)c).setText("");
	  }
	  
	 
	  /**
	  * Dans cette m�thode, nous allons cr�er l'objet utilis� par le syst�me de drag'n drop
	  * afin de faire circuler les donn�es entre les composants
	  * Vous pouvez voir qu'il s'agit d'un objet de type Transferable
	  * @param c
	  * @return
	  */
	  protected Transferable createTransferable(JComponent c){
		  //on retourne un nouvel objet impl�mentant l'interface Transferable 
		  //StringSelection impl�mente cette interface
		  
		  return new StringSelection(((JLabel)c).getText()); // !!!!!!!!!
		  
	  } 

	  /**
	  * Cette m�thode est utilis�e afin de d�terminer le comportement 
	  * du composant vis-�-vis du drag'n drop : nous retrouverons
	  * nos variables statiques COPY, MOVE, COPY_OR_MOVE, LINK ou NONE 
	  * @param c
	  * @return int
	  */
	  public int getSourceActions(JComponent c){
		  //Je n'autorise que le d�placement ici
		  return MOVE;
	  }
	  

}
