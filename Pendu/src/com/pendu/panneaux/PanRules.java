package com.pendu.panneaux;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class PanRules extends JPanel {
	public PanRules(){
		setBackground(Color.white);
	}
	private String info = "";
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.black);
		Font policeTitre = new Font("Arial", Font.BOLD, 40);
		g.setFont(policeTitre);
		g.drawString("le jeu du PENDU", 300, 50);
		
		Font police = new Font("Arial", Font.BOLD, 15);
		g.setFont(police);
		String info = "Vous avez 7 coups pour trouver le mot caché, et si vous réussissez..."
				+ " et bien on recommence !";
		g.drawString(info, 100, 160);
		info = "Plus vous avez trouvé de mots, plus votre score grandira ! Alors à vous de jouer";
		g.drawString(info, 100, 180);
		info = "PROVERBE : \"Pas vu, pas pris ! \nPris, PENDU !!!!!!";
		g.drawString(info, 100, 250);
		info = "Blabla, blablabla, blablablabla :..........100 pts";
		g.drawString(info, 180, 300);
	}
}
