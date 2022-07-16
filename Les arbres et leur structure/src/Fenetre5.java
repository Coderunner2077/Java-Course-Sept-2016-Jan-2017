import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class Fenetre5 extends JFrame {
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	//Mon objet mod�le
	private DefaultTreeModel model;
	private JButton bouton = new JButton("Ajouter");
	private JButton bouton2 = new JButton("Supprimer");
	public Fenetre5(){
		this.setSize(200, 300);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setTitle("JTree");   
	    
	    listRoot();      
	    bouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if(arbre.getLastSelectedPathComponent() != null){
	    			JOptionPane jop = new JOptionPane();
	    			String nodeName = jop.showInputDialog("Saisir un nom");
	    			if(nodeName != null && !nodeName.trim().equals("")){
	    				DefaultMutableTreeNode parentNode = 
	    					(DefaultMutableTreeNode)arbre.getLastSelectedPathComponent();
	    				DefaultMutableTreeNode childNode =
	    						new DefaultMutableTreeNode(nodeName);
	    				parentNode.add(childNode);
	    				model.insertNodeInto(childNode, parentNode, parentNode.getChildCount()-1);
	    				model.nodeChanged(parentNode);
	    				
	    			}
	    		}
	    		else
	    			System.out.println("Aucune s�lection !");
	    	}
	    });
	    bouton2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		if(arbre.getLastSelectedPathComponent() != null){
	    			JOptionPane jop = new JOptionPane();
	    			int option = jop.showConfirmDialog(null, "Voulez-vous supprimer ce fichier"
	 + "ind�finivement ?", "Supprimer un fichier", JOptionPane.YES_NO_CANCEL_OPTION,
	 						JOptionPane.QUESTION_MESSAGE);
	    			if(option != JOptionPane.CANCEL_OPTION && option != JOptionPane.NO_OPTION 
	    					&& option != JOptionPane.CLOSED_OPTION){
	    				DefaultMutableTreeNode aSupprimer = (DefaultMutableTreeNode)arbre.getLastSelectedPathComponent();
	    				model.removeNodeFromParent(aSupprimer);
	    				
	    			}
	    		}
	    	}
	    });
	    JPanel panB = new JPanel();
	    panB.add(bouton);
	    panB.add(bouton2);
	    this.getContentPane().add(panB, BorderLayout.SOUTH);
	    this.setVisible(true);
	}
	
	private void listRoot(){
		this.racine = new DefaultMutableTreeNode();
		for(File file : File.listRoots()){
			DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getAbsolutePath());
			try{
				for(File nom : file.listFiles()){
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName()+ "\\");
					lecteur.add(listFile(nom, node));
				}
			}catch(NullPointerException e) {}
			
			racine.add(lecteur);
		}
		//je cr�e mon mod�le
		this.model = new DefaultTreeModel(racine);
		//Et je vais �couter ce que mon mod�le a � me dire
		this.model.addTreeModelListener(new TreeModelListener(){
			/**
			 * M�thode appel�e lorsqu'un noeud a chang�
			 */
			public void treeNodesChanged(TreeModelEvent evt){
				System.out.println("Changement dans l'arbre");
				Object[] listNoeuds = evt.getChildren();
				int[] listIndices = evt.getChildIndices();
				for(int i = 0; i < listNoeuds.length; i++)
					System.out.println("Index " + listIndices[i] + ", nouvelle valeur : "
							+ listNoeuds[i]);
			}
			/**
			 * M�thode appel�e lorsqu'un noeud est ins�r�
			 */
			public void treeNodesInserted(TreeModelEvent evt){
				System.out.println("Un noeud a �t� ins�r�");
			}
			/**
			 * M�thode appel�e lorsqu'un noeud est supprim�
			 */
			public void treeNodesRemoved(TreeModelEvent evt){
				System.out.println("Un noeud est supprim�");
			}
			/**
			 * M�thode appel�e lorsque la structure d'un noeud est modifi�e
			 */
			public void treeStructureChanged(TreeModelEvent evt){
				System.out.println("La structure d'un noeud a chang�");
			}
		});
		//je cr�e, avec ma hi�rarchie, un arbre
		arbre = new JTree();
		//Je passe mon mod�le � mon arbre
		//On pouvait aussi passer l'objet TreeModel au constructeur du JTree
		arbre.setModel(model);
		arbre.setRootVisible(false);
		//je rends mon arbre �ditable
		arbre.setEditable(true);
		this.getContentPane().add(new JScrollPane(arbre));
	}
	
	private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node){
		int count = 0;
		
		if(file.isFile())
			return new DefaultMutableTreeNode(file.getName());
		else{
			File[] list = file.listFiles();
			if(list == null)
				return new DefaultMutableTreeNode(file.getName());
			for(File nom : list){
				//pas plus de 10 enfants par noeud
				if(count++ < 20){
					DefaultMutableTreeNode subnode;
					if(nom.isDirectory()){
						subnode = new DefaultMutableTreeNode(nom.getName() + "\\");
						node.add(listFile(nom, subnode));
					}
					else
						node.add(new DefaultMutableTreeNode(nom.getName()));
				}
			}
			return node;
		}
	}
	public static void main(String[] args) {
		//cette instruction permet de r�cup�rer le look du syst�me
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(InstantiationException e) {}
		catch(ClassNotFoundException e) {}
		catch(UnsupportedLookAndFeelException e) {}
		catch(IllegalAccessException e) {}
		Fenetre5 fen = new Fenetre5();

	}

}
