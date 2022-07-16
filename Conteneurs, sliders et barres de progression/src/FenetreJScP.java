import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class FenetreJScP extends JFrame {
	private JTextArea textPane = new JTextArea();
	private JScrollPane scroll = new JScrollPane(textPane);
	public FenetreJScP(){
		this.setLocationRelativeTo(null);
	    this.setTitle("Gérer mes conteneurs : JScrollPane");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(400, 400);
	    
	    JButton bouton = new JButton("Bouton");
	    bouton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent e){
	    		System.out.println("Text écrit dans le JTextArea :");
	    		System.out.println("-------------------------------");
	    		System.out.println(textPane.getText());
	    	}
	    });
	    //on ajoute l'objet au content pane de ma fenêtre
	    //this.getContentPane().add(textPane, BorderLayout.CENTER);
	    
	    this.getContentPane().add(scroll);
	    //On aurait pu écrire : 
	    //this.getContentPane().add(new JScrollPane(textPane), BorderLayout.CENTER);
	    this.getContentPane().add(bouton, BorderLayout.SOUTH);
	    this.setVisible(true);
	}
	

	public static void main(String[] args) {
		FenetreJScP fen = new FenetreJScP();
	}

}
