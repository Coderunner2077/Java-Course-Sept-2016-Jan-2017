package fr.effet.dep;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.TransferHandler;

public class Fenetre extends JFrame{
	private MyGlassPane myGlass = new MyGlassPane();
	public Fenetre(){
		super("Test de GlassPane");
	    setSize(400, 200);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JPanel pan = new JPanel();
	    JPanel pan2 = new JPanel();
	      
	    //On crée un composant
	    JButton bouton1 = new JButton("Bouton N°1");
	    //on y attache les écouteurs qui auront pour rôle d'initialiser mon glassPane et
	    //d'y affecter les données qui permettront de simuler le déplacement
	    bouton1.addMouseListener(new MouseGlassListener(myGlass));
	    bouton1.addMouseMotionListener(new MouseGlassMotionListener(myGlass));
	    //On affecte maintenant un TransferHandler spécifique initialisé avec la
	    //propriété JavaBean "text"
	    bouton1.setTransferHandler(new TransferHandler("text"));
	    
	    JButton bouton2 = new JButton("Bouton N°2");
	    bouton2.addMouseListener(new MouseGlassListener(myGlass));
	    bouton2.addMouseMotionListener(new MouseGlassMotionListener(myGlass));
	    bouton2.setTransferHandler(new TransferHandler("text"));
	    
	    JLabel text = new JLabel("Deuxième texte statique");
	    text.addMouseListener(new MouseGlassListener(myGlass));
	    text.addMouseMotionListener(new MouseGlassMotionListener(myGlass));
	    text.setTransferHandler(new TransferHandler("text"));
	    
	    JLabel label = new JLabel("Text statique");
	    label.addMouseListener(new MouseGlassListener(myGlass));
	    label.addMouseMotionListener(new MouseGlassMotionListener(myGlass));
	    label.setTransferHandler(new TransferHandler("text"));
	    
	    pan.add(bouton1);
	    pan.add(label);
	    add(pan, BorderLayout.CENTER);
	    
	    pan2.add(text);
	    pan2.add(bouton2);
	    add(pan2, BorderLayout.SOUTH);
	    
	    setGlassPane(myGlass);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    setVisible(true);
	}
	
	public static void main(String[] args) {
		new Fenetre();

	}

}
