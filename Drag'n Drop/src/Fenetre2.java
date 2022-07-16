import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.TransferHandler;

public class Fenetre2 extends JFrame{
	public Fenetre2(){
		setTitle("Drag'n Drop avec un JLabel !");
	    setSize(300, 100);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	    JPanel pan = new JPanel();
	    pan.setLayout(new GridLayout(2,2));
	    pan.setBackground(Color.white);
	      
	    JLabel srcLib = new JLabel("Source de drag : ", JLabel.RIGHT);
	    JLabel src = new JLabel("Texte à déplacer !");
	    
	    //--------------------------------------------------------------------------
	    //je crée le nouvel objet pour activer le drag'n drop
	    src.setTransferHandler(new TransferHandler("text"));
	    
	    //on spécifie au composant qu'il doit envoyer ses données via son objet TransferHandler
	    src.addMouseListener(new MouseAdapter(){
	    	//on utilise cet événement pour que les actions soient visibles dès le clic de souris
	    	//Avec mouseReleased, niveau IHM, on aurait rien vu
	    	public void mousePressed(MouseEvent event){
	    		//on récupère le JComponent
	    		JComponent comp = (JComponent)event.getSource();
	    		//du composant, je récupère l'objet de transfert
	    		TransferHandler handle = comp.getTransferHandler();
	    		//je lui ordonne d'amorcer la procédure de drag'n drop
	    		handle.exportAsDrag(comp, event, TransferHandler.COPY);
	    	}
	    });
	    
	    //-------------------------------------------------------------------------
	    
	    JLabel destLib = new JLabel("Destination de drag : ", JLabel.RIGHT);
	    JTextField dest = new JTextField();
	    //j'active le comportement par défaut de ce composant
	    dest.setDragEnabled(true);
	    
	    pan.add(srcLib);
	    pan.add(src);
	    pan.add(destLib);
	    pan.add(dest);
	    setContentPane(pan);
	    setVisible(true);
	}
	public static void main(String[] args) {
		new Fenetre2();

	}

}
