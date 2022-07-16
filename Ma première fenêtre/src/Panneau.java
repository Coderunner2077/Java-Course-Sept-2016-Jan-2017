import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	public void paintComponent(Graphics g){
		// la 1re partie (ou presque) du III./
		//Je verrai cette phrase chaque fois que la méthode sera invoquée
		System.out.println("Je suis exécutée !");
		int x1 = this.getWidth()/4; // résultat de getWidth()/2 - ((getWidth()/2) / 2) 
		int y1 = this.getHeight()/4;
		//remplir un rond vide au centre
		g.fillOval(x1, y1, this.getWidth()/2, this.getHeight()/2);
		
		//dessiner un rond vide
		g.drawOval(this.getWidth()/2 - 36, 0, 75, 75);
		//dessiner un rectangle vide
		g.drawRect(0, this.getHeight()/2 - 36, 75, 75);
		
		//dessiner un rectangle aux ongles arrondis
		g.drawRoundRect(this.getWidth() - 85, this.getHeight()/2 - 40, 80, 80, 30, 15);
		//dessiner des lignes
		g.drawLine(0, 0, this.getWidth(), this.getHeight());
		g.drawLine(this.getWidth(), 0, 0, this.getHeight());
		//modifier la police
		Font font = new Font("Courrier", Font.BOLD, 20);
		g.setFont(font);
		//modifier la couleur
		g.setColor(Color.RED);
		//dessiner du texte
		g.drawString("Une phrase sortie du rien !", this.getWidth()/3, this.getHeight() - 30);
//////////////////////////////////////////////////////////////////////////////////////////////////
		/*
		//partie image du Graphics
		try{
			Image img = ImageIO.read(new File("images.png"));
			g.drawImage(img, 0, 0, this);
			
			//pour une image de fond
			//g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
		}catch(IOException e){
			e.printStackTrace();
		}
		*/
///////////////////////////////////////////////////////////////////////////////////////////////////		
		/*
		//Partie sur Graphics2D
		Graphics2D g2d = (Graphics2D)g;
		
		GradientPaint gp = new GradientPaint(0, 0, Color.RED, 30, 30, Color.cyan, false);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
		*/
/////////////////////////////////////////////////////////////////////////////////////////////////////	
		/*
		GradientPaint gp, gp2, gp3, gp4, gp5, gp6; 
		gp = new GradientPaint(0, 0, Color.RED, 20, 0, Color.magenta, true);
	    gp2 = new GradientPaint(20, 0, Color.magenta, 40, 0, Color.blue, true);
	    gp3 = new GradientPaint(40, 0, Color.blue, 60, 0, Color.green, true);
	    gp4 = new GradientPaint(60, 0, Color.green, 80, 0, Color.yellow, true);
	    gp5 = new GradientPaint(80, 0, Color.yellow, 100, 0, Color.orange, true);
	    gp6 = new GradientPaint(100, 0, Color.orange, 120, 0, Color.red, true);

	    g2d.setPaint(gp);
	    g2d.fillRect(0, 0, 20, this.getHeight());               
	    g2d.setPaint(gp2);
	    g2d.fillRect(20, 0, 20, this.getHeight());
	    g2d.setPaint(gp3);
	    g2d.fillRect(40, 0, 20, this.getHeight());
	    g2d.setPaint(gp4);
	    g2d.fillRect(60, 0, 20, this.getHeight());
	    g2d.setPaint(gp5);
	    g2d.fillRect(80, 0, 20, this.getHeight());
	    g2d.setPaint(gp6);
	    g2d.fillRect(100, 0, 40, this.getHeight());
	    */
	}
}
