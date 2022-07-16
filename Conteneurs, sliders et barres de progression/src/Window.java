import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class Window extends JWindow {
	public Window(){
		setSize(700, 700);
		setLocationRelativeTo(null);
		JPanel pan = new JPanel();
		JLabel img = new JLabel(new ImageIcon("planete.jpg"));
		img.setVerticalAlignment(JLabel.CENTER);
		img.setHorizontalAlignment(JLabel.CENTER);
		pan.setBorder(BorderFactory.createLineBorder(Color.blue));
		pan.setLayout(new BorderLayout());
		pan.add(img);
		getContentPane().add(pan, BorderLayout.CENTER);
	}
	
	
	
	public static void main(String[] args) {
		Window wind = new Window();
		wind.setVisible(true);
		try{
			Thread.sleep(10000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		wind.setVisible(false);
	}

}
