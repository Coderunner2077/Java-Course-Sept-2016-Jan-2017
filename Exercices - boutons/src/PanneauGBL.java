import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

public class PanneauGBL extends JPanel {
	public PanneauGBL(){
		//je crée un tableau de 4 lignes sur 4 colonnes 
		this.setBackground(Color.white);
		this.setPreferredSize(new Dimension(500, 470));
		
		Dimension d = new Dimension(80, 80);
		JPanel cell1 = new JPanel();
		cell1.setBackground(Color.blue);
		cell1.setPreferredSize(d);
		JPanel cell2 = new JPanel();
		cell2.setBackground(Color.yellow);
		cell2.setPreferredSize(d);
		JPanel cell3 = new JPanel();
		cell3.setBackground(Color.green);
		cell3.setPreferredSize(d);
		JPanel cell4 = new JPanel();
		cell4.setBackground(Color.red);
		cell4.setPreferredSize(d);
		JPanel cell4bis = new JPanel();
		cell4bis.setBackground(Color.gray);
		cell4bis.setPreferredSize(d);
		JPanel cell5 = new JPanel();
		cell5.setBackground(Color.orange);
		cell5.setPreferredSize(d);
		JPanel cell6 = new JPanel();
		cell6.setBackground(Color.cyan);
		cell6.setPreferredSize(d);
		JPanel cell7 = new JPanel();
		cell7.setBackground(Color.pink);
		cell7.setPreferredSize(d);
		JPanel cell8 = new JPanel();
		cell8.setBackground(Color.black);
		cell8.setPreferredSize(d);
		JPanel cell9 = new JPanel();
		cell9.setBackground(Color.MAGENTA);
		cell9.setPreferredSize(d);
		
		this.setPreferredSize(new Dimension(500, 480));
		JPanel container = new JPanel();
		container.setPreferredSize(new Dimension(420, 420));
		container.setBackground(Color.white);
		container.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.ipadx = 5;
		gbc.ipady = 5;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		container.add(cell1, gbc);
		//----------------------------
		gbc.gridx = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		container.add(cell2, gbc);
		// ---------------------------
		gbc.gridx = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		container.add(cell3, gbc);
		// ---------------------------
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.VERTICAL;
		container.add(cell4, gbc);
		// ---------------------------
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		gbc.fill = GridBagConstraints.VERTICAL;
		container.add(cell4bis, gbc);
		// ---------------------------
		gbc.gridx = 2;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		container.add(cell5, gbc);
		// ---------------------------
		gbc.gridx = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		container.add(cell6, gbc);
		// ---------------------------
		gbc.gridx = 2;
		gbc.gridy = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		container.add(cell7, gbc);
		// ---------------------------
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		container.add(cell8, gbc);
		// ---------------------------
		gbc.gridx = 2;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		container.add(cell9, gbc);
		this.add(container);
	}
}
