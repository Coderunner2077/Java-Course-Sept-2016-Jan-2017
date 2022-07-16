import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FenetreJChB extends JFrame{
	private JPanel container = new JPanel();
	private JCheckBox check1 = new JCheckBox("Case 1");
	private JCheckBox check2 = new JCheckBox("Case 2");
	
	public FenetreJChB(){
		this.setTitle("Fenetre avec des JCheckBox");
	    this.setSize(300, 300);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(new BorderLayout());
	    JPanel top = new JPanel();
	    check1.addActionListener(new StateListener());
	    check2.addActionListener(new StateListener());
	    top.add(check1);
	    top.add(check2);
	    container.add(top, BorderLayout.NORTH);
	    this.setContentPane(container);
	    this.setVisible(true);
	}
	
	class StateListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(((JCheckBox)e.getSource()).isSelected())
				System.out.println("La case chochée : " 
			+ ((JCheckBox)e.getSource()).getText());
			else
				System.out.println("La case décochée : "
			+ ((JCheckBox)e.getSource()).getText());
		}
	}
	
	
	
	public static void main(String[] args) {
		FenetreJChB fen = new FenetreJChB();
	}
}
