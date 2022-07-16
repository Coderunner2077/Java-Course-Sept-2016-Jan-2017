import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class TreeTransferHandler extends TransferHandler {
	JTree tree;
	public TreeTransferHandler(JTree tree){
		this.tree = tree;
	}
	
	public boolean canImport(TransferHandler.TransferSupport info){
		if(!info.isDataFlavorSupported(DataFlavor.stringFlavor))//!!!!!!
			return false;
		return true;
	}
	
	public boolean importData(TransferHandler.TransferSupport support){
		if(!canImport(support))
			return false;
		
		//On r�cup�re l'endroit du drop via un objet appropri�
		JTree.DropLocation dl = (JTree.DropLocation)support.getDropLocation();
		//on r�cup�re les info afin de pouvoir cr�er un nouvel �l�ment
		TreePath path = dl.getPath();
		int index = dl.getChildIndex(); //!!!!
		
		//Comme pour le JLabel, on r�cup�re les donn�es
		Transferable data = support.getTransferable();
		String str = "";
		try{
			str = (String)data.getTransferData(DataFlavor.stringFlavor);
		}catch(UnsupportedFlavorException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		//On peut maintenant ajouter le noeud
		DefaultMutableTreeNode nouveau = new DefaultMutableTreeNode(str);
		//On d�duit le noeud parent via le chemin
		DefaultMutableTreeNode parent = (DefaultMutableTreeNode)path.getLastPathComponent();//!!
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		index = (index == -1) ? model.getChildCount(path.getLastPathComponent()) : index;
		model.insertNodeInto(nouveau, parent, index);
		
		tree.makeVisible(path.pathByAddingChild(nouveau)); //!!!!!!!!!
		tree.scrollPathToVisible(path); //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		
		return true;		
	}
	
	public int getSourceActions(JComponent c){
		return COPY_OR_MOVE;
	}

}
