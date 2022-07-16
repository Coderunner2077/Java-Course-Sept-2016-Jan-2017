import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Panneau extends JPanel{
	public void paintComponent(Graphics g){
		System.out.println("Je suis exécuté !");
		Graphics2D g2d = (Graphics2D)g;
		GradientPaint gp, gp2;
		int X0 = this.getWidth()/10;
		int X1 =  this.getWidth()/3 - X0;
		int X2 = this.getWidth()/3;
		int X3 = this.getWidth()*2/3;
		int X4 = this.getWidth()*2/3 + X0;

		g2d.setPaint(Color.red);
		g2d.fillRect(0, 0, X1, this.getHeight());
		gp = new GradientPaint(X1, 0, Color.red, X2, 0, Color.white, false);//le dernier paramètre ne change rien ici
		g2d.setPaint(gp);
		g2d.fillRect(X1, 0, X0, this.getHeight());
		
		g2d.setPaint(Color.blue);
		g2d.fillRect(X4, 0, X1, this.getHeight());
		gp2 = new GradientPaint(X4, 0, Color.blue, X3, 0, Color.white, true);
		g2d.setPaint(gp2);
		g2d.fillRect(X3, 0, X0, this.getHeight());
		Font police = new Font("Courrier", Font.BOLD, 40);
		g2d.setFont(police);
		g2d.setColor(Color.black);
		g2d.drawString("Liberté", X2 + X0, X0);
		g2d.drawString("Égalité", X2 + X0, this.getHeight()/2);
		g2d.drawString("Fraternité", X2 +X0*2/3 + X0/10, this.getHeight() - X0);
	}
}
