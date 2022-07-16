import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class ZFenetre extends JFrame{
	private JMenuBar menuBar = new JMenuBar();
	private JMenu test1 = new JMenu("Fichier");
	private JMenu test1_2 = new JMenu("Sous fichier");
	private JMenu test2 = new JMenu("Edition");
	
	private JMenuItem item1 = new JMenuItem("Ouvrir");
	private JMenuItem item2 = new JMenuItem("Fermer");
	private JMenuItem item3 = new JMenuItem("Lancer");
	private JMenuItem item4 = new JMenuItem("Arr�ter");
	
	private JCheckBoxMenuItem jcmi1 = new JCheckBoxMenuItem("Choix 1");
	private JCheckBoxMenuItem jcmi2 = new JCheckBoxMenuItem("Choix 2");
	
	private JRadioButtonMenuItem jrmi1 = new JRadioButtonMenuItem("Radio 1");
	private JRadioButtonMenuItem jrmi2 = new JRadioButtonMenuItem("Radio 2");
	
	public static void main(String[] args){
		ZFenetre fen = new ZFenetre();
	}
	
	public ZFenetre(){
		this.setSize(400, 200);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    
	    //On initialise les menus
	    this.test1.add(item1);
	    
	    //On ajoute les �l�ments dans le sous-menu
	    this.test1_2.add(jcmi1);
	    this.test1_2.add(jcmi2);
	    //Ajout d'un s�parateur
	    this.test1_2.addSeparator();
	    //On met les radios dans un ButtonGroup
	    ButtonGroup bg = new ButtonGroup();
	    jrmi1.setSelected(true);
	    bg.add(jrmi1);
	    bg.add(jrmi2);
	    
	    this.test1_2.add(jrmi1);
	    this.test1_2.add(jrmi2);
	    
	    //Ajout du sous-menu dans mon menu
	    this.test1.add(test1_2);
	    this.test1.addSeparator();
	    
	    item2.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		System.exit(0);
	    	}
	    });
	    
	    this.test1.add(item2);
	    this.test2.add(item3);
	    this.test2.add(item4);
	    
	    //L'ordre d'ajout va d�terminer l'ordre d'apparition dans le menu de gauche � 
	    //droite. Le 1er ajout� sera tout � gauche de la barre de menu et inversement
	    //pour le dernier
	    
	    this.menuBar.add(test1);
	    this.menuBar.add(test2);
	    this.setJMenuBar(menuBar);
	    this.setVisible(true);
	}

}
