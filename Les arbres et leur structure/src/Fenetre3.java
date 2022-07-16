import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class Fenetre3 extends JFrame {
	private JTree arbre;
	private DefaultMutableTreeNode racine;
	private JSplitPane split;
	private JPanel pan = new JPanel();
	private JScrollPane scroll;
	private JTextArea textArea = new JTextArea();
	private String chemin = new String();
	
	public Fenetre3(){
		this.setSize(800, 400);
		this.setTitle("Les arbres");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		listRoot();
		
		this.setVisible(true);
	}
	
	private void listRoot(){
		racine = new DefaultMutableTreeNode();
		for(File file : File.listRoots()){
			DefaultMutableTreeNode lecteur = new DefaultMutableTreeNode(file.getAbsolutePath());
			try{
				for(File nom : file.listFiles()){
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(nom.getName() + "\\");
					lecteur.add(this.listFile(nom, node));
				}
			}catch(NullPointerException e){
				e.printStackTrace();
			}
			racine.add(lecteur);
		}
		arbre = new JTree(racine);
		arbre.setRootVisible(false);
		arbre.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent event){
				if(arbre.getLastSelectedPathComponent() != null){
					File file = new File(getAbsolutePath(event.getPath()));
					textArea.setText(getDescription(file));
				}
				
			}
			private String getAbsolutePath(TreePath treePath){
				chemin = "";
				Object[] obj = treePath.getPath();
				for(Object name : obj){
					if(name.toString() != null)
						chemin += name;
				}
				return chemin;
			}
			
			private String getDescription(File file){
				String str = "Chemin d'accès sur le disque :\n\t";
				str += chemin;
				if(file.isDirectory())
					str += "\nJe suis un dossier";
				else
					str += "\nJe suis un fichier (taille : " + file.length() + " ko)";
				str += "\nJ'ai des droits :\n\ten lecture : ";
				str += (file.canRead()) ? "Oui\n" : "Non\n";
				str += "\n\ten écriture : ";
				str += (file.canWrite()) ? "Oui" : "Non";
				return str;
			}
		});
		
		pan.setLayout(new BorderLayout());
		pan.add(textArea);
		scroll = new JScrollPane(arbre);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, new JScrollPane(pan));
		split.setDividerLocation(200);
		
		this.getContentPane().add(split, BorderLayout.CENTER);		
	}
	
	private DefaultMutableTreeNode listFile(File file, DefaultMutableTreeNode node){
		if(file.isFile())
			return new DefaultMutableTreeNode(file.getName());
		else{
			if(file.listFiles() == null)
				return new DefaultMutableTreeNode(file.getName());
			for(File nom : file.listFiles()){
				int count = 0;
				if(count++ < 5){
					DefaultMutableTreeNode subnode;
					if(nom.isDirectory()){
						subnode = new DefaultMutableTreeNode(nom.getName()+"\\");
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
		Fenetre3 fen = new Fenetre3();
	}

}
