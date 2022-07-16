package fr.effet.dep;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.TransferHandler;

public class MouseGlassListener extends MouseAdapter {
	private MyGlassPane myGlass;
	private BufferedImage img;
	
	public MouseGlassListener(MyGlassPane glass){
		myGlass = glass;
	}
	
	public void mousePressed(MouseEvent event){
		//On récupère le composant pour en déduire sa forme
		Component composant = event.getComponent();
		Point location = (Point)event.getPoint().clone();
		
		//Les méthodes ci-dessous permettent dans l'ordre
		//de convertir un point en coordonnées d'écran
		//et de reconvertir ce point en coordonnées fenêtre
		SwingUtilities.convertPointToScreen(location, composant);
		SwingUtilities.convertPointFromScreen(location, myGlass);
		
		//les instruction ci-dessous permettent de redessiner le composant
		img = new BufferedImage(composant.getWidth(), composant.getHeight(), 
				BufferedImage.TYPE_INT_ARGB); // => type avec alpha 
		Graphics g = img.getGraphics();
		composant.paint(g);
		
		//On passe les données qui vont bien à mon GlassPane
		myGlass.setLocation(location);
		myGlass.setImage(img);
		//on n'oublie pas de dire au glassPane de s'afficher
		myGlass.setVisible(true);		
	}
	
	public void mouseReleased(MouseEvent event){
		long start = System.currentTimeMillis();
		new Thread(new Runnable(){
			public void run(){
				System.out.println("dans le thread : " + Thread.currentThread());
				//-----------------------------------------------------------------------
				//On récupère le composant pour en déduire sa position
				Component composant = event.getComponent();
				Point location = (Point)event.getPoint().clone();
				
				//Les méthods ci-dessous permettent dans l'ordre
				//de convertir un Point en coordonnées d'écran
				//Et de reconvertir ce Point en coordonnées fenêtre
				SwingUtilities.convertPointToScreen(location, composant);
				SwingUtilities.convertPointFromScreen(location, myGlass);
				
				//On passe les données qui vont bien à mon GlassPane
				myGlass.setLocation(location);
				myGlass.setImage(img);
				//Et on n'oublie pas de ne plus l'afficher
				myGlass.setVisible(false);
				
				Thread t = new Thread(new Runnable(){
					public void run(){
						//On implémente le transfert lorsqu'on relâche le bouton de la souris
						//Ceci afin de ne pas supplanter le fonctionnement du déplacement
						JComponent comp = (JComponent)event.getSource();
						TransferHandler handler = comp.getTransferHandler();
						handler.exportAsDrag(comp, event, TransferHandler.COPY);
						System.out.println("dans le t : " + Thread.currentThread());
						long end = System.currentTimeMillis();
						long st = end - start;
						System.out.println(st);
					}
				});
				
				SwingUtilities.invokeLater(t);
			}
		}).start();
		
	}
}
