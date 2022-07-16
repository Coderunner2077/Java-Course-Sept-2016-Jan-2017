import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;

public class Fenetre extends JFrame{
	public Fenetre(){
		this.setTitle("Ma première fenêtre Java");
		this.setSize(600, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*réf PARTIE II
		//Instanciation d'un objet JPanel
		JPanel pan = new JPanel();
		//Définition de sa couleur de fond
		pan.setBackground(Color.ORANGE);
		//Je préviens ma JFrame que ma JPanel sera son content pane
		this.setContentPane(pan);
		*/
		Panneau pan = new Panneau();
		this.setContentPane(pan); // j'aurai pu faire : this.setContentPane(new Panneau());
		this.setVisible(true);
		
	}
}
