import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class BorderDemo extends JFrame{
	private String[] list = {
		"Bevel Border",
		"Etched Border",
		"Line Border",
		"Matte Border",
		"Raised Bevel Border",
		"Titled Border",
		"Compound Border"
	};
	
	private Border[] listBorder = {
			BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.red),
			BorderFactory.createEtchedBorder(Color.blue, Color.yellow),
			BorderFactory.createLineBorder(Color.green),
			BorderFactory.createMatteBorder(5, 2, 5, 2, Color.magenta),
			BorderFactory.createRaisedBevelBorder(),
			BorderFactory.createTitledBorder("Titre"),
			BorderFactory.createCompoundBorder(
					BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.black, Color.blue),
					BorderFactory.createMatteBorder(5, 2, 5, 2, Color.magenta)
					)
	};
	
	public BorderDemo(){
		this.setTitle("Les bordures font la fête !");
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setSize(550, 200);
	    
	    JPanel pan = new JPanel();
	    for(int i = 0; i < list.length; i++){
	    	JLabel lib = new JLabel(list[i]);
	    	lib.setPreferredSize(new Dimension(150, 50));
	    	lib.setBorder(listBorder[i]);
	    	lib.setAlignmentX(JLabel.CENTER);
	    	lib.setHorizontalAlignment(JLabel.CENTER);
	    	pan.add(lib);
	    }
	    
	    this.getContentPane().add(pan, BorderLayout.CENTER);

	}
	
	
	public static void main(String[] args) {
		BorderDemo fen = new BorderDemo();
		fen.setVisible(true);
	}

}
