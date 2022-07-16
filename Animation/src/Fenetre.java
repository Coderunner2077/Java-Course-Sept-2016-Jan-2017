import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	private Panneau pan = new Panneau();
	private JPanel container = new JPanel();
	private JPanel boutonPane = new JPanel();
	private JPanel top = new JPanel();
	private Bouton boutonG = new Bouton("Go");
	private Bouton boutonS = new Bouton("Stop");
	private Bouton boutonI = new Bouton("Inverser");
	private Bouton boutonInit = new Bouton("Réinitialiser");
	private JLabel label = new JLabel("Le choix de la forme ");
	private int compteur = 0;
	private int x, y;
	private boolean animated = false;
	private Thread t;
	private JComboBox combo;
	private String[] formes = {"ROND", "CARRE", "TRIANGLE", "ETOILE"};
	private JCheckBox morph = new JCheckBox("Morphing");
	
	public Fenetre(){
		this.setTitle("Animation Java");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);
		
		boutonPane.add(boutonG);
		boutonPane.add(boutonI);
		boutonPane.add(boutonS);
		boutonPane.add(boutonInit);
		
		// J'ajoute les BoutonListener
		boutonG.addActionListener(new BoutonGListener());
		boutonI.addActionListener(new BoutonIListener());
		boutonS.addActionListener(new BoutonSListener());
		boutonInit.addActionListener(new BoutonInitListener());
		
		boutonS.setEnabled(false);
		container.add(boutonPane, BorderLayout.SOUTH);
		
		combo = new JComboBox(formes);
		combo.addActionListener(new FormeListener());
		morph.addActionListener(new MorphListener());
		
		//Définition du label
		Font police = new Font("Tahoma", Font.BOLD, 16);
		label.setFont(police);
		label.setForeground(Color.blue);
		label.setHorizontalAlignment(JLabel.CENTER);
		top.add(label);
		top.add(combo);
		top.add(morph);
		container.add(top, BorderLayout.NORTH);
		
		this.setContentPane(container);
		this.setVisible(true);
		go();
	}
	
	private void go(){
		while(animated){
			x = pan.getPosX();
			y = pan.getPosY();
			if(x > pan.getWidth() - pan.getLargeurForme())
				pan.setADroite(false);
			else if(x < 1)
				pan.setADroite(true);
			if(y > pan.getHeight() - pan.getHauteurForme())
				pan.setEnBas(false);
			else if(y < 1)
				pan.setEnBas(true);
			
			if(pan.getADroite())
				pan.setPosX(++x);
			else
				pan.setPosX(--x);
			if(pan.getEnBas())
				pan.setPosY(++y);
			else
				pan.setPosY(--y);
			pan.repaint();
			try{
				Thread.sleep(3);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	//Création des classes internes pour les implémentations
	class BoutonGListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane jop = new JOptionPane();
			int option = jop.showConfirmDialog(null, "Voulez-vous lancer l'animation ?", 
					"Lancement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.YES_OPTION){
				animated = true;
				t = new Thread(new PlayAnimation());
				t.start();
				boutonG.setEnabled(false);
				boutonS.setEnabled(true);
			}
		}
	}
	
	class BoutonSListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane jop = new JOptionPane();
			int option;
			option = jop.showConfirmDialog(null, "Voulez-vous arrêter l'animation", "Arrêt", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION 
					&& option != JOptionPane.CLOSED_OPTION){
				animated = false;
				boutonS.setEnabled(false);
				boutonG.setEnabled(true);
			}
		}
	}
	
	class BoutonIListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(pan.getADroite())
				pan.setADroite(false);
			else
				pan.setADroite(true);
			if(pan.getEnBas())
				pan.setEnBas(false);
			else
				pan.setEnBas(true);
		}
	}
	
	class PlayAnimation implements Runnable{
		public void run(){
			go();
		}
	}
	
	class FormeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String name = "";
			name = combo.getSelectedItem().toString();
			pan.setForme(name);
			 /* La méthode getSelectedItem() retourne un Object ==> toString() pour 
			 * retourner un String. 
			 * Ou alors ==> utiliser un cast.
			 */
		}
	}
	
	class MorphListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//si la case est cochée, on active le mode morphing
			if(morph.isSelected())
				pan.setMorph(true);
			else
				pan.setMorph(false);
		}
	}
	
	class BoutonInitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			pan.setLargeurForme(50);
			pan.setHauteurForme(50);
		}
	}
	
}
	
