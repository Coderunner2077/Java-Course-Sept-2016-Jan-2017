import java.awt.Color;

public class Point {
	private int x, y, taille;
	private Color couleur;
	private boolean carre;
	
	public Point(){}
	public Point(int x, int y, Color color){
		this.x = x;
		this.y = y;
		this.couleur = color;
		this.taille = 10;
		carre = false;
	}
	
	public Point(int x, int y, Color color, boolean carre, int taille){
		this.x = x;
		this.y = y;
		this.couleur = color;
		this.taille = taille;
		this.carre = carre;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getTaille(){
		return taille;
	}
	
	public Color getCouleur(){
		return couleur;
	}
	
	public boolean isCarre(){
		return this.carre;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setCouleur(Color color){
		this.couleur = color;
	}
	
	public void setCarre(boolean bool){
		carre = bool;
	}
	
}
