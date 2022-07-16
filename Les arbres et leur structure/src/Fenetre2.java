import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class Fenetre2 extends JFrame{
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	public Fenetre2(String lookAndFeel){
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		String title = (lookAndFeel.split("\\."))[(lookAndFeel.split("\\.").length - 1)];
		this.setTitle("Nom du look and feel : " + title);
		
		listRoot();
		
		//on force l'utilisation d'un lookAndFeel
		try{
			UIManager.setLookAndFeel(lookAndFeel);
			SwingUtilities.updateComponentTreeUI(this);
		}
		catch(InstantiationException e) {}
		catch(ClassNotFoundException e) {}
		catch(UnsupportedLookAndFeelException e) {}
		catch(IllegalAccessException e) {}
		
		this.setVisible(true);
	}
	
	private void listRoot(){
		this.racine = new DefaultMutableTreeNode();
		int count = 0;
		for(File file : File.listRoots()){
			DefaultMutableTreeNode lecteur = 
					new DefaultMutableTreeNode(file.getAbsolutePath());
			try{
				for(File nom : file.listFiles()){
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName()+"\\");
					lecteur.add(this.listFile(nom, node));
				}
			} catch(NullPointerException e){
				e.printStackTrace();
			}
			this.racine.add(lecteur);
		}
		//je crée avec ma hiérarchie un arbre
		arbre = new JTree(racine);
		//je rends le "dossier" racine invisible
		arbre.setRootVisible(false);
		//J'ajoute un listener
		arbre.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent event){
				if(arbre.getLastSelectedPathComponent() != null)
					//System.out.println(arbre.getLastSelectedPathComponent().toString());
					System.out.println(getAbsolutePath(event.getPath()));
				
			}
			
			private String getAbsolutePath(TreePath treePath){
				String str = "";
				//On balaie le contenu de l'objet TreePath
				for(Object name : treePath.getPath()){
					//Si l'objet a un nom, on l'ajoute au chemin
					if(name.toString() != null)
						str += name.toString();
				}
				return str;
			}
		});
		//que je place sur le content pane avec un scroll
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
				count++;
				//pas plus de 5 enfants par noeud
				if(count < 5){
					DefaultMutableTreeNode subnode;
					if(nom.isDirectory()){
						subnode = new DefaultMutableTreeNode(nom.getName()+"\\");
						node.add(this.listFile(nom, subnode));
					}
					else
						node.add(new DefaultMutableTreeNode(nom.getName()));
					
					
				}
			}
			return node;
		}
	}

	public static void main(String[] args) {
		
		//Je vais créer des fenêtres avec des looks différents
		//cette instruction permet de récupérer tous les looks du système
		UIManager.LookAndFeelInfo[] looks = UIManager.getInstalledLookAndFeels();
		Fenetre2 fen;
		//on parcourt tout le tableau en passant le nom du look à utiliser
		for(int i = 0; i < looks.length; i++)
			fen = new Fenetre2(looks[i].getClassName());
		

	}

}
