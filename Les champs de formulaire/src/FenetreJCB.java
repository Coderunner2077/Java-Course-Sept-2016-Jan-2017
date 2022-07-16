import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FenetreJCB extends JFrame{
	private JPanel container = new JPanel();
	private JComboBox combo = new JComboBox();
	private JLabel label = new JLabel("Une ComboBox");
	
	public FenetreJCB(){
		this.setTitle("Une JComboBox");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		combo.setPreferredSize(new Dimension(100, 20));
		combo.addItem("Option 1");
		combo.addItem("Option 2");
		combo.addItem("Option 3");
		combo.addItem("Option 4");
		combo.setSelectedIndex(3);
		//Ajout du listener
		combo.addItemListener(new ItemState());
		combo.addActionListener(new ItemAction());
		combo.setForeground(Color.blue);
		
		JPanel top = new JPanel();
		top.add(label);
		top.add(combo);
		container.add(top, BorderLayout.NORTH);
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	//classe interne implémentant l'interface ItemListener
	class ItemState implements ItemListener{
		public void itemStateChanged(ItemEvent e){
			System.out.println("événement déclenche sur : " + e.getItem());
		}
	}
	
	class ItemAction implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("ActionListener : action sur " + combo.getSelectedItem());
			System.out.println("ActionListener : action sur " + ((JComboBox)e.getSource()).getSelectedItem());
		}
	}
	public static void main(String[] args){
		FenetreJCB fen = new FenetreJCB();
	}
}
