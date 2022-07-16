
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

public class FenetreJFTF extends JFrame{
	private JPanel container = new JPanel();
	private JFormattedTextField jtf = new 
			JFormattedTextField(NumberFormat.getIntegerInstance());
	private JFormattedTextField jtf2 = new
			JFormattedTextField(NumberFormat.getPercentInstance());
	private JLabel label = new JLabel("Un JFormattedTextField");
	private JButton b = new JButton("OK");
	private JFormattedTextField jtf3, jtf4;
	
	public FenetreJFTF(){
		this.setTitle("Fenetre avec JFormattedTextField");
	    this.setSize(500, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    JPanel top = new JPanel();
	    top.setPreferredSize(new Dimension(300, 100));
	    Font police = new Font("Arial", Font.BOLD, 14);
	    jtf.setFont(police);
	    jtf.setPreferredSize(new Dimension(150, 30));
	    jtf.setForeground(Color.blue);
	    jtf2.setPreferredSize(new Dimension(150, 30));
	    jtf2.setFont(police);
	    jtf2.setForeground(Color.black);
	    b.addActionListener(new BoutonListener());
	    top.add(label);
	    top.add(jtf);
	    top.add(jtf2);
	    top.add(b);
	    container.add(top, BorderLayout.NORTH);
	    
	    JPanel south = new JPanel();
	    try{
	    	MaskFormatter tel = new MaskFormatter("### ### ### ###");
	    	MaskFormatter tel2 = new MaskFormatter("###-###-###-###");
	    	jtf3 = new JFormattedTextField(tel);
	    	jtf4 = new JFormattedTextField(tel2);
	    }catch(ParseException e){
	    	e.printStackTrace();
	    }
	    jtf3.setPreferredSize(new Dimension(150, 30));
	    jtf4.setPreferredSize(new Dimension(150, 30));
	    jtf3.setFont(police);
	    jtf4.setFont(police);
	    jtf3.setForeground(Color.blue);
	    jtf4.setForeground(Color.red);
	    south.add(jtf3);
	    south.add(jtf4);
	    container.add(south, BorderLayout.SOUTH);
	    this.setContentPane(container);
	    this.setVisible(true);
	}
	
	class BoutonListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Text : jtf => " + jtf.getText());
			System.out.println("Text : jtf2 => " + jtf2.getText());
			System.out.println("Text : jtf3 => " + jtf3.getText());
			System.out.println("Text : jtf4 => " + jtf4.getText());

		}
	}
	
	public static void main(String[] args) {
		FenetreJFTF fen = new FenetreJFTF();
	}

}
