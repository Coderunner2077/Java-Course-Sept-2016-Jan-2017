import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	private int posX = - 50, posY = - 50;
	private boolean aDroite = true, enBas = true;
	private int width = 50, height = 50;
	private String forme = "ROND", couleur = "blue";
	private int drawSize = 50;
	//Un booléen pour le mode morphing, un autre pour savoir si la taille doit être réduite
	//ou pas
	private boolean morphing = false, reduce = false;
	//Et le compteur de rafraîchissements
	private int increment = 0;
	
	private Color couleurForme = Color.blue;
	private Color couleurFond = Color.white;
	public void paintComponent(Graphics g){
		g.setColor(couleurFond);
		//SDL_FillRect
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		//je choisis la couleur de la forme
		g.setColor(couleurForme);
		//on délègue la méthode de dessin aux méthodes draw() ou drawMorph()
		//Si le morphing est activé, on peint le morphing
		if(morphing)
			drawMorph(g);
		//Sinon, on peint le mode normal
		else
			draw(g);
	}
	
	public void draw(Graphics g){
		if(forme.equals("ROND"))
			g.fillOval(posX, posY, width, height);
		else if(forme.equals("CARRE") || forme.equals("CARRÉ"))
			g.fillRect(posX, posY, width, height);
		else if(forme.equals("TRIANGLE")){
			int[] ptsX = {posX + width/2, posX + width, posX};
			int[] ptsY = {posY, posY + height, posY + height};
			g.fillPolygon(ptsX, ptsY, 3);
		}
		else if(forme.equals("ETOILE")){
			int[] ptsX = {posX + width/2, posX + width, posX, posX + width, posX, posX + width/2};
			int[] ptsY = {posY, posY+height, posY + height/3, posY + height/3, posY + height, posY};
			g.drawPolyline(ptsX, ptsY, 6);
		}
	}
	
	public void drawMorph(Graphics g){
		//j'initialise drawSize
		drawSize = width;
		//on incrémente 
		increment++;
		//on regarde si on doit réduire ou non
		if(drawSize >= 50) reduce = true;
		else if(drawSize <= 10) reduce = false;
		if(reduce)
			drawSize = drawSize - getUsedSize();
		else
			drawSize = drawSize + getUsedSize();
		
		if(forme.equals("ROND"))
			g.fillOval(posX, posY, drawSize, drawSize);
		else if(forme.equals("CARRE") || forme.equals("CARRÉ"))
			g.fillRect(posX, posY, drawSize, drawSize);
		else if(forme.equals("TRIANGLE")){
			int[] ptsX = {posX + drawSize/2, posX + drawSize, posX};
			int[] ptsY = {posY, posY + drawSize, posY + drawSize};
			g.fillPolygon(ptsX, ptsY, 3);
		}
		else if(forme.equals("ETOILE")){
			int[] ptsX = {posX + drawSize/2, posX + drawSize, posX, posX + drawSize, posX, posX + drawSize/2};
			int[] ptsY = {posY, posY+drawSize, posY + drawSize/3, posY + drawSize/3, posY + drawSize, posY};
			g.drawPolyline(ptsX, ptsY, 6);
		}
		width = drawSize;
		height = drawSize;
	}
	
	//Retourne le nombre à retrancher ou à ajouter pour le morphing
	private int getUsedSize(){
		int res = 0;
		//Si le nombre de tours est de dix, on réinitialise l'incrément et on retourne 1
		if(increment / 10 == 1){
			increment = 0;
			res = 1;
		}
		return res;
	}
	
	//Les getters
	public int getDrawSize(){
		return drawSize;
	}
	
	public boolean isMorph(){
		return morphing;
	}
	public int getPosX(){
		return posX;
	}
	
	public int getPosY(){
		return posY;
	}
	
	public boolean getADroite(){
		return aDroite;
	}
	
	public boolean getEnBas(){
		return enBas;
	}
	
	public int getLargeurForme(){
		return width;
		
	}
	
	public int getHauteurForme(){
		return height;
	}
	//Les setters
	public void setMorph(boolean bool){
		morphing = bool;
		//On synchronise la taille
		drawSize = width;
	}
	
	public void setPosX(int x){
		posX = x;
	}
	
	public void setPosY(int y){
		posY = y;
	}
	
	
	public void setADroite(Boolean d){
		this.aDroite = d;
	}
	
	public void setEnBas(Boolean b){
		this.enBas = b;
	}
	
	public void setLargeurForme(int w){
		width = w;
	}
	
	public void setHauteurForme(int h){
		height = h;
	}
	
	public void setForme(String form){
		forme = form.toUpperCase();
	}
	
	public void setCouleurFond(Color color){
		this.couleurFond = color;
	}
	
	public void setCouleurForme(Color color){
		this.couleurForme = color;
	}
}
