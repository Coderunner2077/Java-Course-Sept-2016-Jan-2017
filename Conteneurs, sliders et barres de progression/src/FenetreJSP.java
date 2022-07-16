import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class FenetreJSP extends JFrame {
	private JSplitPane split;
	
	public FenetreJSP(){
		this.setLocationRelativeTo(null);
	    this.setTitle("Gérer mes conteneurs : JSplitPane");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(400, 400);
	    //Je crée 2 conteneurs de couleurs différents
	    JPanel pan = new JPanel();
	    pan.setBackground(Color.blue);
	    JPanel pan2 = new JPanel();
	    pan2.setBackground(Color.red);
	    
	    JPanel pan3 = new JPanel();
	    JPanel pan4 = new JPanel();
	    pan4.setBackground(Color.yellow);
	    
	    //je construis mon séparateur
	    split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan, pan2);
	    //je place le séparateur 
	    split.setDividerLocation(180);
	    
	    split.setOneTouchExpandable(true);
	    split.setDividerSize(35);
	    JSplitPane split2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pan3, pan4);
	    split2.setDividerLocation(200);
	    JSplitPane split3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT, split, split2);
	    split3.setDividerLocation(220);
	    //Je le place ensuite au content pane de ma fenêtre, au centre
	    this.getContentPane().add(split3, BorderLayout.CENTER);
	    this.setVisible(true);
	}
	
	public static void main(String[] args) {
		FenetreJSP fen = new FenetreJSP();
	}

}
