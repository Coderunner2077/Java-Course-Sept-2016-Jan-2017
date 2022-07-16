import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FenetreJTF extends JFrame{
	private JPanel container = new JPanel();
	private JTextField jtf;
	private JLabel label = new JLabel("Téléphone FR");
	private JButton b = new JButton("OK");
	
	public FenetreJTF(){
		this.setTitle("Fenetre avec JTextField et KeyListener");
	    this.setSize(300, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    
	    jtf = new JTextField();
	    JPanel top = new JPanel();
	    Font police = new Font("Arial", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    jtf.setForeground(Color.blue);
	    //j'ajoute l'écouteur à mon composant
	    jtf.addKeyListener(new ClavierListener());
	    
	    top.add(label);
	    top.add(jtf);
	    top.add(b);
	    container.add(top, BorderLayout.NORTH);
	    this.setContentPane(container);
	    this.setVisible(true);
	}
	
	class ClavierListener implements KeyListener{
		/*
		public void keyPressed(KeyEvent event){
			System.out.println("Code touche pressée : " + event.getKeyCode() 
			+ "\t - caractère touche pressée : " + event.getKeyChar());
			pause();
		}
		
		public void keyReleased(KeyEvent event){
			System.out.println("Code touche relâchée : " + event.getKeyCode()
			+ "\t - caractère touche relâchée : " + event.getKeyChar());
			pause();
		}
		
		public void keyTyped(KeyEvent event){
			System.out.println("Code touche tapée : " + event.getKeyCode()
			+ "\t - caractère touche tapée : " + event.getKeyChar());
			pause();
		}
		
		*/
		//inutile de redéfinir ces 2 méthodes ici
		public void keyPressed(KeyEvent event){ }
		public void keyTyped(KeyEvent event){ }
		
		public void keyReleased(KeyEvent event){
			if(!isNumeric(event.getKeyChar()))
				jtf.setText(jtf.getText().replace(String.valueOf(event.getKeyChar()), ""));
		}
		
		//retourne true si le paramètre est numérique, false dans le cas contraire
		public boolean isNumeric(char carac){
			try{
				Integer.parseInt(String.valueOf(carac));
			}catch(NumberFormatException e){
				return false;
			}
			return true;
			/*
			//Variante :
			try{
				Integer.valueOf(String.valueOf(carac)).intValue();
			}catch(NumberFormatException e){
				return false;
			}
			return true; 
			*/
		}
		public void pause(){
			try{
				Thread.sleep(1000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	
	public static void main(String[] args) {
		FenetreJTF fen = new FenetreJTF();
	}

}
