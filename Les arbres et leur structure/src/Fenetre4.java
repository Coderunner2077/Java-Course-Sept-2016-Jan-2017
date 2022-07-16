import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class Fenetre4 extends JFrame {
	private JTree arbre, arbre2, arbre3;
	private DefaultMutableTreeNode racine;
	//Je vais créer 3 modèles d'affichage
	private DefaultTreeCellRenderer[] tCellRenderer = new DefaultTreeCellRenderer[2];
	public Fenetre4() {
		 this.setSize(600, 350);
		 this.setLocationRelativeTo(null);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 this.setTitle("Les arbres");
		 
		 initRenderer();
		 listRoot();
		 
		 try{
			 //On force à utiliser le "look and feel" du système
			 UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			 //Je force tous les composants de ma fenêtre (this) à utiliser le look and feel
			 //du système
			 SwingUtilities.updateComponentTreeUI(this);
			 
		 }
		 catch(InstantiationException e) {}
		 catch(ClassNotFoundException e) {}
		 catch(UnsupportedLookAndFeelException e) {}
		 catch(IllegalAccessException e) {}
	
		 this.setVisible(true);
	}
	
	private void initRenderer(){
		//Instanciation
		this.tCellRenderer[0] = new DefaultTreeCellRenderer();
		//Initialisation des images pour les actions ouvrir, fermer et pour les feuilles
		this.tCellRenderer[0].setClosedIcon(new ImageIcon("ferme.jpg"));
		this.tCellRenderer[0].setOpenIcon(new ImageIcon("ouvert.jpg"));
		this.tCellRenderer[0].setLeafIcon(new ImageIcon("feuille.jpg"));
		
		this.tCellRenderer[1] = new DefaultTreeCellRenderer();
		this.tCellRenderer[1].setClosedIcon(null);
		this.tCellRenderer[1].setOpenIcon(null);
		this.tCellRenderer[1].setLeafIcon(null);
	}
	
	private void listRoot(){
		int count = 0;
		this.racine = new DefaultMutableTreeNode();
		Iterable<Path> roots = FileSystems.getDefault().getRootDirectories();
		for(Path file : roots){
			DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.toAbsolutePath());
			try(DirectoryStream<Path> list = Files.newDirectoryStream(file)){
				for(Path nom : list){
					if(count++ < 30){
						DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getFileName() + "\\");
						lecteur.add(listFile(nom, node));
					}
				}
			} catch(IOException e){
				e.printStackTrace();
			}
			racine.add(lecteur);
		}
		arbre = new JTree(racine);
		arbre.setRootVisible(false);
		//Je définis le rendu pour cet arbre
		arbre.setCellRenderer(this.tCellRenderer[0]);
		
		arbre2 = new JTree(racine);
		arbre2.setRootVisible(false);
		arbre2.setCellRenderer(this.tCellRenderer[1]);
		
		arbre3 = new JTree(racine);
		arbre3.setRootVisible(false);
		
		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(arbre),
				new JScrollPane(arbre2));
		split.setDividerLocation(200);
		JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, split,
				new JScrollPane(arbre3));
		split2.setDividerLocation(400);
		this.getContentPane().add(split2);
		
	}
	
	private DefaultMutableTreeNode listFile(Path file, DefaultMutableTreeNode node){
		int count = 0;
		if(!Files.isDirectory(file))
			return new DefaultMutableTreeNode(file.getFileName());
		else{
			try(DirectoryStream<Path> list = Files.newDirectoryStream(file)){
				for(Path nom : list){
					if(count++ < 50){
						DefaultMutableTreeNode subnode;
						if(Files.isDirectory(nom)){
							subnode = new DefaultMutableTreeNode(nom.getFileName() + "\\");
							node.add(listFile(nom, subnode));
						}
						else
							node.add(new DefaultMutableTreeNode(nom.getFileName()));
					}
				}
				return node;
			}catch(IOException e){
				return new DefaultMutableTreeNode(file.getFileName());
			}
		}
	}
	
	public static void main(String[] args) {
		Fenetre4 fen = new Fenetre4();

	}

}
