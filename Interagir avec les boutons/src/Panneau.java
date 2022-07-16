import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	private int posX = - 50, posY = - 50;
	private boolean aDroite = true;
	private boolean enBas = true;
	public void paintComponent(Graphics g){
		//je choisis une couleur de fond pour le rectangle
		g.setColor(Color.white);
		//je dessine le rectange pour effacer les traces de l'ancien écran
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		// je choisis une couleur pour la boule
		g.setColor(Color.blue);
		//je la dessine aux coordonnées souhaitées
		g.fillOval(posX, posY, 50, 50);
	}
	
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public void setPosX(int x){
		posX = x;
	}
	
	public void setPosY(int y){
		posY = y;
	}
	
	public boolean getADroite(){
		return aDroite;
	}
	
	public boolean getEnBas(){
		return enBas;
	}
	
	public void setADroite(Boolean d){
		this.aDroite = d;
	}
	
	public void setEnBas(Boolean b){
		this.enBas = b;
	}
}
