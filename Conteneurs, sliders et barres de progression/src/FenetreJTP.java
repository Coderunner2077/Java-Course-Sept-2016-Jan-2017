import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class FenetreJTP extends JFrame{
	private JTabbedPane onglet;
	private int i = 0;
	public FenetreJTP(){
		this.setLocationRelativeTo(null);
	    this.setTitle("G�rer vos conteneurs : JTabbedPane");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(400, 200);
	    
	    //Cr�ation de plusieurs Panneau
	    Panneau[] tPan = {new Panneau(Color.red), new Panneau(Color.green), new Panneau(Color.blue)};
	    //Cr�ation de mon conteneur d'onglets
	    onglet = new JTabbedPane();
	    
	    for(Panneau pan : tPan){
	    	//M�thode d'ajout d'onglet
	    	onglet.add("Onglet n�" +(++i), pan);
	    	//Je peux aussi utiliser la m�thode addTab :
	    	//onglet.addTab("Onglet n�" +(++i), pan);
	    	
	    	//les index d'onglets commencent � 0
	    	onglet.setIconAt((i - 1), new ImageIcon("java.png"));
	    }
	    //On passe ensuite les onglets au content pane
	    this.getContentPane().add(onglet);
	    
	    //ajout du bouton pour ajouter des onglets
	    JButton bAjouter = new JButton("Ajouter un onglet");
	    bAjouter.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		onglet.addTab("Onglet n�" +(++i), new ImageIcon("java.png"), new Panneau(Color.darkGray));
	    	}
	    });
	    //ajout du bouton pour supprimer l'onglet s�lectionn�
	    JButton bDelete = new JButton("Effacer l'onglet");
	    bDelete.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		//je r�cup�re l'index de l'onglet s�lectionn�
	    		int selected = onglet.getSelectedIndex();
	    		//s'il n'y a plus d'onglets, la m�thode ci-dessus renvoie -1
	    		if(selected >= 0){
	    			onglet.remove(selected);
	    			i--;
	    		}
	    	}
	    });
	    JPanel pan = new JPanel();
	    pan.add(bAjouter);
	    pan.add(bDelete);
	    
	    this.getContentPane().add(pan, BorderLayout.SOUTH);
	    this.setVisible(true);
	}
	public static void main(String[] args) {
		FenetreJTP fen = new FenetreJTP();
	}

}
