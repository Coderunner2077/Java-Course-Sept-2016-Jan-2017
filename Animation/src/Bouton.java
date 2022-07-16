import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Bouton extends JButton implements MouseListener{
	private String name;
	private Image img;
	public Bouton(String str){
		super(str);
		this.name  = str;
		try{
			img = ImageIO.read(new File("fondBouton.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		//Pour que mon bouton s'écoute dès qu'un événement de la souris est intercepté :
		this.addMouseListener(this);
	}
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		g2d.setColor(Color.black);
		//Objet permettant de connaître les propriétés d'une police, dont la taille
		FontMetrics fm = g2d.getFontMetrics();
		int height = fm.getHeight();
		int width = fm.stringWidth(this.name);
		//Je définis la position du texte, et le tour est joué
		g2d.drawString(name, this.getWidth()/2 - width/2, this.getHeight()/2
				+ height/4 );
	}
	
	public void mouseClicked(MouseEvent e){ }
	public void mouseEntered(MouseEvent e){
		try{
			img = ImageIO.read(new File("fondJauneHover.png"));
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public void mouseExited(MouseEvent e){
		try{
			img = ImageIO.read(new File("fondBouton.png"));
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public void mousePressed(MouseEvent e){
		try{
			img = ImageIO.read(new File("fondBleuCyan.png"));
		}catch(IOException ex){
			ex.printStackTrace();
		}
	}
	
	public void mouseReleased(MouseEvent e){
		if((e.getX() > 0 && e.getX() < this.getWidth()) 
				&& (e.getY() > 0 && e.getY() < this.getHeight())){
			try{
				img = ImageIO.read(new File("fondOrangeClic.png"));
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		else{
			try{
				img = ImageIO.read(new File("fondBouton.png"));
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
}
