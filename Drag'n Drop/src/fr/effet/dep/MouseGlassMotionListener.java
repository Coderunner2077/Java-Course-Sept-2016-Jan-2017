package fr.effet.dep;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

public class MouseGlassMotionListener extends MouseAdapter {
	private MyGlassPane myGlass;
	public MouseGlassMotionListener(MyGlassPane glass){
		myGlass = glass;
	}
	/**
	 * Méthode fonctionnant sur le même principe que la classe précédente
	 * mais cette fois sur l'action de déplacement
	 */
	public void mouseDragged(MouseEvent event){
		Component composant = event.getComponent();
		Point location = (Point)event.getPoint().clone();
		
		SwingUtilities.convertPointToScreen(location, composant);
		SwingUtilities.convertPointFromScreen(location, myGlass);
		
		myGlass.setLocation(location);
		myGlass.repaint(); // !!!!!!!!!
	}
}
