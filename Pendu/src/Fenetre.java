import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.pendu.panneaux.PanPropos;
import com.pendu.panneaux.PanRules;
import com.pendu.panneaux.PanTop;
import com.pendu.panneaux.Panneau;
import com.pendu.panneaux.PenduPanel;

public class Fenetre extends JFrame {
	private JMenuBar menuBar = new JMenuBar();
	private JMenu	fichier = new JMenu("Fichier"),
					aPropos = new JMenu("A propos");
	
	private JMenuItem	nouveau = new JMenuItem("Nouveau"),
						score = new JMenuItem("Score"),
						regles = new JMenuItem("Règles"),
						aProposItem = new JMenuItem("Tout à propos du pendu");
	private JPanel content = new JPanel();
	private JPanel pan = new Panneau();
	private PanTop topPan = new PanTop();
	private PanPropos panPropos;
	private Thread t, t2, t3;
	
	private String message = "", message2 = "";
	
	public Fenetre(){
		this.setTitle("LE PENDU !");
		this.setSize(900, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		initMenu();
		content.setLayout(new BorderLayout());
		content.add(pan);
		this.setContentPane(content);
	}
	
	
	private void initMenu() {
		CountDownLatch doneSignal = new CountDownLatch(1);
		message = topPan.getMessage();
		nouveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				
				Thread t = new Thread(new TraitementPendu());
				t.start();
				
			}
		});
		regles.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				content.removeAll();
				pan = new PanRules();
				content.add(pan);
				content.revalidate();
				
			}
		});
		
		score.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				content.removeAll();
				pan = new PanTop();
				content.add(pan);
				content.revalidate();
			}
		});
		
		aProposItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				content.removeAll();
				panPropos = new PanPropos();
				content.add(panPropos);
				content.revalidate();
			}
		});
		fichier.add(nouveau);
		fichier.add(score);
		fichier.add(regles);
		
		aPropos.add(aProposItem);
		
		menuBar.add(fichier);
		menuBar.add(aPropos);
		
		this.setJMenuBar(menuBar);
	}
	
	class TraitementPendu implements Runnable{	
		public void run(){
			content.removeAll();
			pan = new PenduPanel();
			content.add(pan);
			content.revalidate();
		}
	}
	
	class TraitementAccueil implements Runnable{
		public void run(){
			content.removeAll(); 
			pan = new Panneau();
			content.add(pan);
			content.revalidate();				
		}
	}
	
	class TraitementScore implements Runnable{
		public void run(){
			topPan = new PanTop();
			message2 = topPan.getMessage();
			System.out.println(message);
			System.out.println("\n\n" +message2);
			if(!message.equals(message2)){
				content.removeAll();
				content.add(topPan);
				content.revalidate();
			}
			
		}
	}
	
	public static void main(String[] args) {
		Fenetre fen = new Fenetre();
		fen.setVisible(true);
	}

}
