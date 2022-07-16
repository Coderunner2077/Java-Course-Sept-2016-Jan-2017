import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class Fenetre1 extends JFrame{
	private JTree arbre;
	public Fenetre1(){
		this.setSize(300, 300);
		this.setTitle("Arbres et leur structure");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		//on invoque la méthode construction de mon arbre
		buildTree();
		
		this.setVisible(true);
	}
	
	private void buildTree(){
		//Création d'une racine 
		DefaultMutableTreeNode racine = new DefaultMutableTreeNode("Racine");
		
		//J'ajoute des branches et des feuilles à ma racine
		for(int i = 1; i < 12; i++){
			DefaultMutableTreeNode rep = new DefaultMutableTreeNode("Noeud n°" + i);
			
			//S'il s'agit d'un nombre pair, je rajoute une branche
			if((i % 2) == 0){
				for(int j = 1; j < 5; j++){
					//j'ajoute des branches
					DefaultMutableTreeNode rep2 = new DefaultMutableTreeNode("Fichier enfant "
							+ "n°" + j);
					
					//j'ajoute des feuilles
					for(int k = 1; k < 5; k++)
						rep2.add(new DefaultMutableTreeNode("Sous-fichier enfant n°" + k));
					
					rep.add(rep2);
				}
			}
			//On rajoute la feuille ou la branche à la racine
			racine.add(rep);
		}
		//Je crée avec ma hiérarchie un arbre
		arbre = new JTree(racine);
		
		//que je place sur le content pane de ma JFrame à l'aide d'un scroll
		this.getContentPane().add(new JScrollPane(arbre), BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		Fenetre1 fen = new Fenetre1();

	}

}
