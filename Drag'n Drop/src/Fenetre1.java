import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Fenetre1 extends JFrame {
	public Fenetre1(){
		super("Test de Drag'n Drop");
	    setSize(300, 200);
	    
	    JPanel pan = new JPanel();
	 
	    pan.setBackground(Color.white);
	    pan.setLayout(new BorderLayout());
	      
	    //Notre textearea avec son contenu déplaçable
	    JTextArea label = new JTextArea("Texte déplaçable !");
	    label.setPreferredSize(new Dimension(300, 130));
	    //--------------------------------------------------------------------------------------
	    //C'est cette instruction qui permet le déplacement de son contenu
	    label.setDragEnabled(true);
	    //--------------------------------------------------------------------------------------
	    pan.add(new JScrollPane(label));
	    
	    JPanel pan2 = new JPanel();
	    pan2.setBackground(Color.white);
	    pan2.setLayout(new BorderLayout());
	    
	    //Je crée le premier textfield avec son contenu déplaçable
	    JTextField text = new JTextField();
	    //----------------------------------------------------------------
	    text.setDragEnabled(true);
	    //----------------------------------------------------------------
	    //et le second sans
	    JTextField text2 = new JTextField();
	    pan2.add(text, BorderLayout.NORTH);
	    pan2.add(text2, BorderLayout.SOUTH);
	    pan.add(pan2, BorderLayout.SOUTH);
	    add(pan, BorderLayout.CENTER);
	    this.setVisible(true);
	    
	}
	
	public static void main(String[] args){
	    new Fenetre1();
	  }  


}
