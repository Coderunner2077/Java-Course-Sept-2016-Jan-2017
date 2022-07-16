import java.awt.Color;

import javax.swing.JFrame;

public class Fenetre extends JFrame{
	public Fenetre(){
		this.setTitle("Le drapeau de la République");
		this.setSize(900, 600);
		this.setLocationRelativeTo(null);
		//this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Panneau pan = new Panneau();
		this.setBackground(Color.white);
		this.setContentPane(pan);
		this.setVisible(true);
	}
}
