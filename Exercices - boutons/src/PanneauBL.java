import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PanneauBL extends JPanel{
	public PanneauBL(){
		this.setBackground(Color.white);
		/*
		//1re méthode :
		JPanel p1 = new JPanel();
		p1.setLayout(new BoxLayout(p1, BoxLayout.LINE_AXIS));
		JButton b1 = new JButton("Un");
		p1.add(b1);
		
		JPanel p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.LINE_AXIS));
		JButton b2 = new JButton("Deux");
		JButton b3 = new JButton("Trois");
		JButton b4 = new JButton("Quatre");
		p2.add(b2);
		p2.add(b3);
		p2.add(b4);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.LINE_AXIS));
		JButton b5 = new JButton("Cinq");
		JButton b6 = new JButton("Six");
		JButton b7 = new JButton("Sept");
		JButton b8 = new JButton("Huit");
		JButton b9 = new JButton("Neuf");
		p3.add(b5);
		p3.add(b6);
		p3.add(b7);
		p3.add(b8);
		p3.add(b9);
		
		JPanel p4 = new JPanel();
		p4.setLayout(new BoxLayout(p4, BoxLayout.PAGE_AXIS));
		p4.add(p1);
		p4.add(p2);
		p4.add(p3);
		this.setLayout(new BorderLayout());
		this.add(p4);
		
		*/
		// Variante :
		Box p1 = Box.createHorizontalBox();
		JButton b1 = new JButton("Un");
		p1.add(b1);
		
		Box p2 = Box.createHorizontalBox();
		JButton b2 = new JButton("Deux");
		JButton b3 = new JButton("Trois");
		JButton b4 = new JButton("Quatre");
		p2.add(b2);
		p2.add(b3);
		p2.add(b4);
		
		Box p3 = Box.createHorizontalBox();
		JButton b5 = new JButton("Cinq");
		JButton b6 = new JButton("Six");
		JButton b7 = new JButton("Sept");
		JButton b8 = new JButton("Huit");
		JButton b9 = new JButton("Neuf");
		p3.add(b5);
		p3.add(b6);
		p3.add(b7);
		p3.add(b8);
		p3.add(b9);
		
		Box p4 = Box.createVerticalBox();
		p4.add(p1);
		p4.add(p2);
		p4.add(p3);
		
		this.setLayout(new BorderLayout());
		this.add(p4);
	}
}
