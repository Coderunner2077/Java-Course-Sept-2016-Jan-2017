
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class FenetreJRB extends JFrame{
	private JPanel container = new JPanel();
	private JRadioButton jr1 = new JRadioButton("Radio 1");
	private JRadioButton jr2 = new JRadioButton("Radio 2");
	private ButtonGroup bg = new ButtonGroup();
	
	public FenetreJRB(){
		this.setTitle("Fenetre avec des JRadioButton");
	    this.setSize(300, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    JPanel top = new JPanel();
	    
	    jr1.setSelected(true);
	    jr1.addActionListener(new StateListener());
	    jr2.addActionListener(new StateListener());
	    //j'ajoute les boutons au groupe
	    bg.add(jr1);
	    bg.add(jr2);
	    
	    top.add(jr1);
	    top.add(jr2);
	    container.add(top, BorderLayout.NORTH);
	    this.setContentPane(container);
	    this.setVisible(true);
	}
	
	class StateListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("Source : " + ((JRadioButton)e.getSource()).getText()
			+ "\t- état : "+ ((JRadioButton)e.getSource()).isSelected());
		}
	}
	public static void main(String[] args) {
		FenetreJRB fen = new FenetreJRB();

	}

}
