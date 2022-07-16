package fr.effet.dep;

import java.awt.AlphaComposite;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MyGlassPane extends JPanel {
	
	//l'image qui sera dessinée
	private BufferedImage img;
	//Les coordonnées de l'image
	private Point location;
	//La transparence de mon glass pane
	private Composite transparence;
	
	public MyGlassPane(){
		//Afin de ne peindre que ce qui m'intéresse
		setOpaque(false);
		//On définit la transparence
		transparence = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
	}
	
	public void setLocation(Point location){
		this.location = location;
	}
	
	public void setImage(BufferedImage im){
		img = im;
	}
	
	public void paintComponent(Graphics g){
		//S'il n'y a pas d'images à dessiner, on ne fait rien...
		if(img == null)
			return;

		//Dans le cas contraire, on dessine l'image souhaitée
		Graphics2D g2d = (Graphics2D)g;	
		g2d.setComposite(transparence);
		g2d.drawImage(img, (int)(location.getX() - (img.getWidth()/2)), (int)
				(location.getY() - (img.getHeight()/2)), null);
		
	}
}
