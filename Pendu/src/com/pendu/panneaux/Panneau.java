package com.pendu.panneaux;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panneau extends JPanel {
	private Image img;
	public Panneau(){
		try{
			img = ImageIO.read(new File("accueil.jpg"));
		} catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		g.setColor(Color.black);
		Font policeTitre = new Font("Arial", Font.BOLD, 40);
		g.setFont(policeTitre);
		g.drawString("Bienvenue dans le jeu du PENDU", 150, 50);
		g.drawImage(img, 200, 100, this);
		Font police = new Font("Arial", Font.BOLD, 15);
		g.setFont(police);
		String info = "Vous avez 7 coups pour trouver le mot caché, et si vous réussissez..."
				+ " et bien on recommence !";
		g.drawString(info, 130, 560);
		info = "Plus vous avez trouvé de mots, plus votre score grandira ! Alors à vous de jouer";
		g.drawString(info, 130, 580);
		info = "PROVERBE : \"Pas vu, pas pris ! \nPris, PENDU !!!!!!";
		g.drawString(info, 130, 600);
		
	}
}
