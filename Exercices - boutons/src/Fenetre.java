import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/*
 * Le projet :
 * Je vais créer un assemblage de trois "pages" à l'aide du CardLayout.
 * Et dans chaque page, je vais utiliser un layout différent pour positionner des boutons
 */
public class Fenetre extends JFrame{
	CardLayout cl = new CardLayout();
	String[] listContent = {"GridLayout", "BoxLayout", "GridBagLayout", "CardLayout"};
	JPanel content = new JPanel();
	int indice = 0;
	
	public Fenetre(){
		this.setTitle("Un journal");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);
		
		PanneauGL card1 = new PanneauGL();
		PanneauBL card2 = new PanneauBL();
		card2.setBackground(Color.white);
		PanneauGBL card3 = new PanneauGBL();
		PanneauCL card4 = new PanneauCL();
		card4.setBackground(Color.cyan);
		
		JPanel boutonPane = new JPanel();
		JButton bPrevious = new JButton("Previous");
		bPrevious.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				cl.previous(content);
			}
		});
		
		JButton bNext = new JButton("Next");
		bNext.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				cl.next(content);
			}
		});
		
		JButton bIndice = new JButton("Par indice croissant");
		bIndice.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(++indice > 3)
					indice = 0;
				
				cl.show(content, listContent[indice]);
			}
		});
		
		boutonPane.add(bPrevious);
		boutonPane.add(bNext);
		boutonPane.add(bIndice);
		content.setLayout(cl);
		content.add(card1, listContent[0]);
		content.add(card2, listContent[1]);
		content.add(card3, listContent[2]);
		content.add(card4, listContent[3]);
		
		this.getContentPane().add(boutonPane, BorderLayout.NORTH);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
	public static void main(String[] args){
		Fenetre fen = new Fenetre();
	}
	
}
