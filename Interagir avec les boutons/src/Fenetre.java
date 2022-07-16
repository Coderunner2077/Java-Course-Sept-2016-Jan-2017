import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;



public class Fenetre extends JFrame {
	private Panneau pan = new Panneau();
	private JPanel container = new JPanel();
	private JPanel boutonPane = new JPanel();
	private Bouton bouton = new Bouton("Go");
	private Bouton bouton2 = new Bouton("Stop");
	private Bouton bouton3 = new Bouton("Inverser");
	private JLabel label = new JLabel("La forme souhaitée ");
	private int compteur = 0;
	private int x, y;
	private boolean animated = true;
	private Thread t;
	public Fenetre(){
		this.setTitle("Interagir avec les boutons");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);
		boutonPane.add(bouton);
		boutonPane.add(bouton3);
		boutonPane.add(bouton2);
		/*
		//j'ajoute ma Fenetre à la liste des auditeurs du bouton
		bouton.addActionListener(this);
		bouton2.addActionListener(this);
		*/
		//Ce sont maintenant mes classes internes qui écoutent mes boutons
		bouton.addActionListener(new BoutonListener());
		//Deuxième classe écoutant mon 1er bouton
		//bouton.addActionListener(new Bouton3Listener());
		bouton2.addActionListener(new Bouton2Listener());
		bouton3.addActionListener(new Bouton3Listener());
		
		container.add(boutonPane, BorderLayout.SOUTH);
		
		//Définition d'une police d'écriture
		Font police = new Font("Tahoma", Font.BOLD, 16);
		//je l'applique au label
		label.setFont(police);
		//Définition de la couleur du texte
		label.setForeground(Color.blue);
		//définition de la couleur de fond du label : marche pas
		//label.setBackground(Color.red);
		//je modifie l'alignement du texte grâce aux attributs statiques de la classe JLabel
		label.setHorizontalAlignment(JLabel.CENTER);
		container.add(label, BorderLayout.NORTH);
		this.setContentPane(container);
		this.setVisible(true);
		go();
	}
	
	private void go(){
		
		while(animated){
			x = pan.getPosX();
			y = pan.getPosY();
			if(x > pan.getWidth() - 50)
				pan.setADroite(false);
			else if(x < 1)
				pan.setADroite(true);
			if(y > pan.getHeight() - 50)
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
	/*
	//Méthode qui sera appelée lors d'un clic sur le bouton
	public void actionPerformed(ActionEvent arg0){
		this.compteur++;
		label.setText("Vous avez cliqué "+ this.compteur+ " fois !");
	}
	*/
/////////////////////////////////////////////////////////////////////////////////////////////////	
	/*
	// Déterminer le bouton grâce à la méthode getSource de l'objet ActionEvent
	//Méthode qui sera appelée lors d'un clic sur les boutons
		public void actionPerformed(ActionEvent arg0){
			if(arg0.getSource() == bouton)
				label.setText("Vous avez cliqué le bouton 1 !");
			else if(arg0.getSource() == bouton2)
				label.setText("Vous avez cliqué le bouton 2 !");
		}
	*/
////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	//Classe écoutant mon premier bouton
	class BoutonListener implements ActionListener{
		//Redéfinition de la méthode actionPerformed
		public void actionPerformed(ActionEvent arg0){
			label.setText("Vous avez cliqué le bouton 1 !");
		}
	}
	
	//Classe écoutant le bouton 2
	class Bouton2Listener implements ActionListener{
		//Redéfinition de la méthode actionPerformed
		public void actionPerformed(ActionEvent arg0){
			label.setText("Vous avez cliqué le bouton 2 !");
		}
	}
	
	class Bouton3Listener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			System.out.println("Ma classe interne numéro 3 écoute bien !");
		}
	}
	*/
/////////////////////////////////////////////////////////////////////////////////////////////////
	//Classe écoutant mon premier bouton Go
		class BoutonListener implements ActionListener{
			//Redéfinition de la méthode actionPerformed
			public void actionPerformed(ActionEvent arg0){
				animated = true;
				t = new Thread(new PlayAnimation());
				t.start();
				bouton.setEnabled(false);
				bouton2.setEnabled(true);				
			}
		}
		
		//Classe écoutant le Stop
		class Bouton2Listener implements ActionListener{
			//Redéfinition de la méthode actionPerformed
			public void actionPerformed(ActionEvent arg0){
				animated = false;
				bouton.setEnabled(true);
				bouton2.setEnabled(false);
			}
		}
		//Classe écoutant le bouton Inverser
		class Bouton3Listener implements ActionListener{
			public void actionPerformed(ActionEvent arg0){
				if(pan.getEnBas())
					pan.setEnBas(false);
				else
					pan.setEnBas(true);
				if(pan.getADroite())
					pan.setADroite(false);
				else
					pan.setADroite(true);
			}
		}
		class PlayAnimation implements Runnable{
			public void run(){
				go();
			}
		}
}
