import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {
	private Panneau pan = new Panneau();
	private JPanel container = new JPanel();
	private JButton bouton = new JButton("Mon bouton");
	public Fenetre(){
		this.setTitle("Animation Java");
		this.setSize(600, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		container.add(pan, BorderLayout.CENTER);
		container.add(bouton, BorderLayout.SOUTH);
		this.setContentPane(container);
		this.setVisible(true);
		go();
	}
	/*
	//partie I
	private void go(){
		for(int i = - 50; i<pan.getWidth(); i++){
			int x = pan.getPosX();
			int y = pan.getPosY();
			x++;
			y++;
			pan.setPosX(x);
			pan.setPosY(y);
			pan.repaint();
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	*/
	
	/*
	//Partie II
	private void go(){
		for(;;){
			int x = pan.getPosX();
			int y = pan.getPosY();
			x++;
			y++;
			pan.setPosX(x);
			pan.setPosY(y);
			pan.repaint();
			//si mes coordonnées arrivent au bord du composant, je les réinitialise
			if(x == this.getWidth() || y == this.getHeight()){
				pan.setPosX(-50);
				pan.setPosY(-50);
			}
			try{
				Thread.sleep(10);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	*/
	
	// Partie II : ricochets
	private void go(){
		
		while(true){
			int x = pan.getPosX();
			int y = pan.getPosY();
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
	//La méthode co
	 private void go() {
		    // Les coordonnées de départ de notre rond
		    int x = pan.getPosX(), y = pan.getPosY();
		    // Le booléen pour savoir si l'on recule ou non sur l'axe x
		    boolean backX = false;
		    // Le booléen pour savoir si l'on recule ou non sur l'axe y
		    boolean backY = false;

		    // Dans cet exemple, j'utilise une boucle while
		    // Vous verrez qu'elle fonctionne très bien
		    while (true) {
		      // Si la coordonnée x est inférieure à 1, on avance
		      if (x < 1)
		        backX = false;
		      // Si la coordonnée x est supérieure à la taille du Panneau moins la taille du rond, on recule
		      if (x > pan.getWidth() - 50)
		        backX = true;
		      // Idem pour l'axe y
		      if (y < 1)
		        backY = false;
		      if (y > pan.getHeight() - 50)
		        backY = true;

		      // Si on avance, on incrémente la coordonnée
		      // backX est un booléen, donc !backX revient à écrire
		      // if (backX == false)
		      if (!backX)
		        pan.setPosX(++x);
		      // Sinon, on décrémente
		      else
		        pan.setPosX(--x);
		      // Idem pour l'axe Y
		      if (!backY)
		        pan.setPosY(++y);
		      else
		        pan.setPosY(--y);

		      // On redessine notre Panneau
		      pan.repaint();
		      // Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
		      try {
		        Thread.sleep(3);
		      } catch (InterruptedException e) {
		        e.printStackTrace();
		      }
		    }
	 }
	 */
}
