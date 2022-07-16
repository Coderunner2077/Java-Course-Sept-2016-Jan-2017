import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanneauGL extends JPanel{
	
	public PanneauGL(){
		this.setBackground(Color.blue);
		GridLayout gl = new GridLayout(3, 3);
		gl.setHgap(50);//marse pas
		gl.setVgap(30);
		this.setLayout(gl);
		this.add(new JButton("Un"));
		this.add(new JButton("Deux"));
		this.add(new JButton("Trois"));
		this.add(new JButton("Quatre"));
		this.add(new JButton("Cinq"));
		this.add(new JButton("Six"));
		this.add(new JButton("Sept"));
		this.add(new JButton("Huit"));
		//this.add(new JButton("Neuf"));
	}
}
