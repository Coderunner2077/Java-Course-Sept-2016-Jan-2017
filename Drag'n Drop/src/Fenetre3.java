import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class Fenetre3 extends JFrame {
	public Fenetre3(){
		setTitle("Drag'n Drop avec un JLabel !");
	    setSize(300, 100);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	    JPanel pan = new JPanel();
	    pan.setLayout(new GridLayout(2,2));
	    pan.setBackground(Color.white);
	      
	    JLabel srcLib = new JLabel("Source de drag : ", JLabel.RIGHT);
	    JLabel src = new JLabel("Texte à déplacer !");
	    
	    //J'utilise mon objet MyTransferHandler
	    src.setTransferHandler(new MyTransferHandler());
	    
	    src.addMouseListener(new MouseAdapter(){
	    	public void mousePressed(MouseEvent e){
	    		System.out.println("EVENT !");
	    		JLabel lab = (JLabel)e.getSource();
	    		TransferHandler handler = lab.getTransferHandler();
	    		handler.exportAsDrag(lab, e, TransferHandler.MOVE);
	    	}
	    });
	   
	    JLabel destLib = new JLabel("Destination de drag : ", JLabel.RIGHT);
	    JTextField dest = new JTextField();

	    dest.setDragEnabled(true);

	    pan.add(srcLib);
	    pan.add(src);
	    pan.add(destLib);
	    pan.add(dest);

	    setContentPane(pan);
	    setVisible(true);
	}
	public static void main(String[] args) {
		new Fenetre3();

	}

}
