import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class Bouton extends JButton implements MouseListener {
	private String name;
	private Image img;
	private Graphics g;
	public Bouton(String str){
		super(str);
		this.name = str;
		try{
			img = ImageIO.read(new File("fondBouton.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		//Gr�ce � cette instruction, mon objet va s'�couter
		//d�s qu'un �v�nement de la souris sera intercept�, il en sera averti
		this.addMouseListener(this);
	}
	
	
	public void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
		g2d.setPaint(gp);
		//g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		g2d.setColor(Color.black);
		
		//Objet permettant de conna�tre les propri�t�s d'une police, dont la taille
		FontMetrics fm = g2d.getFontMetrics();
		//Hauteur de la police d'�criture
		int height = fm.getHeight();
		//Largeur totale de la cha�ne pass�e en param�tre
		int width = fm.stringWidth(this.name);
		
		//on calcule alors la position du texte, et le tour est jou�
		g2d.drawString(name, this.getWidth()/2 - (width/2), this.getHeight()/2
				+ height / 4);
	}
	
	//M�thode appel�e lors du clic de la souris
	public void mouseClicked(MouseEvent event){ }
	
	//M�thode appel�e lors du survol de la souris
	public void mouseEntered(MouseEvent event){
		//je change le fond de mon bouton pour le jaune lors du survol
		try{
			img = ImageIO.read(new File("fondJauneHover.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// M�thode appel�e lorsque la souris sort de la zone du bouton
	public void mouseExited(MouseEvent event){
		//je changerai le fond de mon bouton pour le vert lorsque je quitterai la zone du bouton
		try{
			img = ImageIO.read(new File("fondBouton.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	// M�thode appel�e lorsque l'on presse le bouton gauche de la souris
	public void mousePressed(MouseEvent event){
		//je changerai le fond du bouton pour le d�grad� bleu/cyan lors du clic gauche
		try{
			img = ImageIO.read(new File("fondBleuCyan.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	// M�thode appel�e lorsque l'on rel�che le clic de la souris
	public void mouseReleased(MouseEvent event){
		//je changerai le fond du bouton pour l'orang� lors du rel�chement du clic
		//si la souris est toujours dans la zone du bouton
		if((event.getY() > 0 && event.getY() < this.getHeight()) && (event.getX() > 0
				&& event.getX() < this.getWidth())){
			try{
				img = ImageIO.read(new File("fondOrangeClic.png"));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		//si on se trouve � l'ext�rieur, on dessine le fond par d�faut
		else{
			try{
				img = ImageIO.read(new File("fondBouton.png"));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}
	
	
}
