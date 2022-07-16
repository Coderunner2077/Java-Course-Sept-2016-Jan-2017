import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	private Color couleurFond = Color.white;
	private Color couleurForme = Color.black;
	private List<Point> points = new ArrayList<Point>();
	private boolean isCarre = false;
	private int size = 10;
	private boolean isGomme = false;
	private Point point = new Point();
	
	public Panneau(){
		this.addMouseMotionListener(new MouseMotionListener(){
			public void mouseMoved(MouseEvent e){}
			public void mouseDragged(MouseEvent e){
				if(isGomme){
					point = new Point(e.getX() - 10,
							  e.getY() - 10,
							  Color.white, true, 20);
				}
				else{
					point = new Point(e.getX() - size/2,
							  e.getY() - size/2,
							  couleurForme, isCarre, size);
				}
				points.add(point);
				repaint();
			}
		});
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(e.getButton() == MouseEvent.BUTTON1){
				if(isGomme){
					point = new Point(e.getX() - 10,
							  e.getY() - 10,
							  Color.white, true, 20);
				}
				else{
					point = new Point(e.getX() - size/2,
							  e.getY() - size/2,
							  couleurForme, isCarre, size);
				}
				points.add(point);
				repaint();
				}

			}
		});
		
		
		this.setCursor(Cursor.getDefaultCursor());
	}
	
	public void paintComponent(Graphics g){
		g.setColor(couleurFond);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		for(Point p : points){
			g.setColor(p.getCouleur());
			if(p.isCarre()){
				g.fillRect(p.getX(), p.getY(), p.getTaille(), p.getTaille());
			}
			else
				g.fillOval(p.getX(), p.getY(), p.getTaille(), p.getTaille());
		}
	}
	
	public int getPointsSize(){
		return size;
	}
	
	public void effacer(){
		points = new LinkedList<Point>();
	}
	
	public void setCouleurForme(Color color){
		this.couleurForme = color;
	}
	
	public void setCouleurFond(Color color){
		this.couleurFond = color;
	}
	
	public void setCarre(boolean bool){
		isCarre = bool;
	}
	
	public void setPointsSize(int s){
		size = s;
	}
	
	public void setIsGomme(boolean bool){
		isGomme = bool;
	}
	
}
