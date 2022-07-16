package fr.my.calculatrice;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class PanLabel extends JPanel {
	public void paintComponent(Graphics g){
		g.setColor(Color.lightGray);
		g.fillRect(1, 1, this.getWidth()-2, this.getHeight()-2);
		g.setColor(Color.black);
		g.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
	}

}

