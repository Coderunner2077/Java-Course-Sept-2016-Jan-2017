import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanneauCL extends JPanel {
	CardLayout cl = new CardLayout();
	String[] listContent = {"CARD_1", "CARD_2", "CARD_3"};
	JPanel content = new JPanel();
	int indice = 0;
	public PanneauCL(){
		JPanel c1 = new JPanel();
		c1.setBackground(Color.orange);
		JPanel c2 = new JPanel();
		c2.setBackground(Color.yellow);
		JPanel c3 = new JPanel();
		c3.setBackground(Color.green);
		
		JPanel boutPane = new JPanel();
		boutPane.setBackground(Color.white);
		JButton bNext = new JButton("Suivant");
		bNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				cl.next(content);
			}
		});
		
		JButton bPrevious = new JButton("Précédent");
		bPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				cl.previous(content);
			}
		});
		
		JButton bIndice = new JButton("Par indice");
		bIndice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(++indice > 2)
					indice = 0;
				cl.show(content, listContent[indice]);
			}
		});
		
		boutPane.add(bPrevious);
		boutPane.add(bNext);
		boutPane.add(bIndice);
		
		content.setLayout(cl);
		content.add(c1, listContent[0]);
		content.add(c2, listContent[1]);
		content.add(c3, listContent[2]);
		
		this.setLayout(new BorderLayout());
		this.add(boutPane, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		
		
	}
}
