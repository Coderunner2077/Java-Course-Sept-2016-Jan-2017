import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class Fenetre extends JFrame {
	private Panneau pan = new Panneau();
	private JPanel container = new JPanel();
	private int compteur = 0;
	private int x, y;
	private boolean animated = false;
	private Thread t;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mAnimation = new JMenu("Animation"),
			mForme = new JMenu("Forme"), 
			mTypeForme = new JMenu("Type de forme"),
			mAPropos = new JMenu("A propos");
	
	private JMenuItem aPropos = new JMenuItem("?"),
			lancement = new JMenuItem("Lancement"),
			arret = new JMenuItem("Arrêt"),
			quitter = new JMenuItem("Quitter"),
			inverser = new JMenuItem("Inverser"),
			init = new JMenuItem("Taille normale");
	
	private JCheckBoxMenuItem morph = new JCheckBoxMenuItem("Mode morphing");
	
	private JRadioButtonMenuItem rond = new JRadioButtonMenuItem("Rond"),
				carre = new JRadioButtonMenuItem("Carré"),
				triangle = new JRadioButtonMenuItem("Triangle"),
				etoile = new JRadioButtonMenuItem("Etoile");
	
	private ButtonGroup bg = new ButtonGroup();
	//La déclaration pour le menu contextuel 
	private JPopupMenu menuCon = new JPopupMenu();
	private JMenu background = new JMenu("Couleur de fond");
	private JMenu couleur = new JMenu("Couleur de la forme");
	
	private JMenuItem launch = new JMenuItem("Lancer l'animation"),
					  stop = new JMenuItem("Arrêter l'animation");
	private JMenuItem rouge = new JMenuItem("Rouge"),
					  bleu = new JMenuItem("Bleu"),
					  vert = new JMenuItem("Vert"),
					  blanc = new JMenuItem("Blanc"),
					  rougeBack = new JMenuItem("Rouge"),
					  bleuBack = new JMenuItem("Bleu"),
					  vertBack = new JMenuItem("Vert"),
					  blancBack = new JMenuItem("Blanc");
	
	private JToolBar toolBar = new JToolBar();
	private JButton bGo = new JButton(new ImageIcon("images/start.jpg")),
					bStop = new JButton(new ImageIcon("images/stop.jpg")),
					bRond = new JButton(new ImageIcon("images/rond.jpg")),
					bCarre = new JButton(new ImageIcon("images/carre.jpg")),
					bTriangle = new JButton(new ImageIcon("images/triangle.jpg")),
					bEtoile = new JButton(new ImageIcon("images/etoile.jpg"));
	
	private Color fondBouton = Color.white;
	//On crée des listeners globaux
	private LancementListener startAnimation = new LancementListener();
	private ArretListener stopAnimation = new ArretListener();
	
	//pour les couleurs aussi
	private CouleurFondListener cFondL = new CouleurFondListener();
	private CouleurFormeListener cFormeL = new CouleurFormeListener();
	
	//pour les formes aussi
	private FormeListener fl = new FormeListener();
	
	public Fenetre(){
		this.setTitle("Animation Java");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		container.setBackground(Color.white);
		container.setLayout(new BorderLayout());
		
		//j'initialise le menu stop
		stop.setEnabled(false);
		//On affecte les listeners
		launch.addActionListener(startAnimation);
		stop.addActionListener(stopAnimation);
		
		//on affecte les écouteurs aux points de menu (couleurs)
		rougeBack.addActionListener(cFondL);
		bleuBack.addActionListener(cFondL);
		vertBack.addActionListener(cFondL);
		blancBack.addActionListener(cFondL);
		
		blanc.addActionListener(cFormeL);
		rouge.addActionListener(cFormeL);
		bleu.addActionListener(cFormeL);
		vert.addActionListener(cFormeL);
		pan.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				//Seulement s'il s'agit du clic droit
				//if(event.getButton() ==MouseEvent.BUTTON3)
				if(e.isPopupTrigger()){
					background.add(blancBack);
					background.add(rougeBack);
					background.add(bleuBack);
					background.add(vertBack);
					
					couleur.add(blanc);
					couleur.add(rouge);
					couleur.add(bleu);
					couleur.add(vert);
					
					menuCon.add(launch);
					menuCon.add(stop);
					menuCon.add(background);
					menuCon.add(couleur);
					
					//La méthode qui va afficher le menu contextuel
					menuCon.show(pan, e.getX(), e.getY());
					
				}
			}
		});
		initMenu();
		initToolBar();
		container.add(pan, BorderLayout.CENTER);
		this.setContentPane(container);
		this.setVisible(true);
		go();
	}
	
	private void initMenu(){
		//Ajout d'un accélérateur
		lancement.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_MASK));
		//ajout du mnénonique
		mAnimation.setMnemonic('A');
		mAnimation.add(lancement);
		//Ajout du listener
		//ATTENTION, LE LISTENER EST GLOBAL
		lancement.addActionListener(startAnimation);
		arret.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK 
				+ KeyEvent.SHIFT_DOWN_MASK));
		mAnimation.add(arret);
		arret.setEnabled(false);
		//ATTENTION, LE LISTENER EST GLOBAL
		arret.addActionListener(stopAnimation);
		mAnimation.add(inverser);
		inverser.addActionListener(new InverserListener());
		mAnimation.addSeparator();
		mAnimation.add(quitter);
		quitter.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		mForme.setMnemonic('F');
		mForme.add(mTypeForme);
		rond.setSelected(true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rond);
		bg.add(carre);
		bg.add(triangle);
		bg.add(etoile);
		mTypeForme.add(rond);
		mTypeForme.add(carre);
		mTypeForme.add(triangle);
		mTypeForme.add(etoile);
		
		rond.addActionListener(fl);
		carre.addActionListener(fl);
		triangle.addActionListener(fl);
		etoile.addActionListener(fl);
		mForme.addSeparator();
		mForme.add(morph);
		morph.addActionListener(new MorphListener());
		mForme.add(init);
		init.addActionListener(new InitListener());
		
		//Menu A propos
		aPropos.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JOptionPane jop = new JOptionPane();
				ImageIcon img = new ImageIcon("images/apropos.gif");
				String mes = "Voilà ! J'ai oublié comment fallait ajouter une icône...\n";
				mes += "Il est temps d'ajouter des accélérateurs et des mnémoniques dans"
						+ " tout ça. Mnémonique... quel drôle de nom. \n";
				jop.showMessageDialog(null, mes, "A propos", 
						JOptionPane.INFORMATION_MESSAGE, img);
			}
		});
		mAPropos.setMnemonic('P');
		mAPropos.add(aPropos);
		
		menuBar.add(mAnimation);
		menuBar.add(mForme);
		menuBar.add(mAPropos);
		this.setJMenuBar(menuBar);
	}
	public void initToolBar(){
		toolBar.setPreferredSize(new Dimension(300, 40));
		toolBar.setBackground(Color.lightGray);
		bGo.setBackground(fondBouton);
		toolBar.add(bGo);
		toolBar.add(bStop);
		bStop.setBackground(fondBouton);
		bStop.setEnabled(false);
		toolBar.addSeparator();
		
		toolBar.add(bRond);
		toolBar.add(bTriangle);
		toolBar.add(bCarre);
		toolBar.add(bEtoile);
		
		bRond.setBackground(fondBouton);
		bCarre.setBackground(fondBouton);
		bTriangle.setBackground(fondBouton);
		bEtoile.setBackground(fondBouton);
		
		//Ajout des Listeners
		bGo.addActionListener(startAnimation);
		bStop.addActionListener(stopAnimation);
		bRond.addActionListener(fl);
		bCarre.addActionListener(fl);
		bTriangle.addActionListener(fl);
		bEtoile.addActionListener(fl);
		
		container.add(toolBar, BorderLayout.NORTH);
	}
	private void go(){
		while(animated){
			x = pan.getPosX();
			y = pan.getPosY();
			if(x > pan.getWidth() - pan.getLargeurForme())
				pan.setADroite(false);
			else if(x < 1)
				pan.setADroite(true);
			if(y > pan.getHeight() - pan.getHauteurForme())
				pan.setEnBas(false);
			else if(y < 1)
				pan.setEnBas(true);
			
			if(pan.getADroite())
				pan.setPosX(++x);
			else
				pan.setPosX(--x);
			if(pan.getEnBas())
				pan.setPosY(++y);
			else
				pan.setPosY(--y);
			pan.repaint();
			System.out.println("Dans le go : " + Thread.currentThread());
			try{
				Thread.sleep(3);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	
	//Création des classes internes pour les implémentations
	class LancementListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane jop = new JOptionPane();
			int option = jop.showConfirmDialog(null, "Voulez-vous lancer l'animation ?", 
					"Lancement", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(option == JOptionPane.YES_OPTION){
				animated = true;
				t = new Thread(new PlayAnimation());
				t.start();
				
				lancement.setEnabled(false);
				arret.setEnabled(true);
				//idem pour le menu contextuel
				launch.setEnabled(false);
				stop.setEnabled(true);
				//idem pour le toolBar
				bGo.setEnabled(false);
				bStop.setEnabled(true);
			}
		}
	}
	
	class ArretListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JOptionPane jop = new JOptionPane();
			int option;
			option = jop.showConfirmDialog(null, "Voulez-vous arrêter l'animation", "Arrêt", 
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(option != JOptionPane.NO_OPTION && option != JOptionPane.CANCEL_OPTION 
					&& option != JOptionPane.CLOSED_OPTION){
				animated = false;
				arret.setEnabled(false);
				lancement.setEnabled(true);
				//Idem pour le menu contextuel
				launch.setEnabled(true);
				stop.setEnabled(false);
				//Idem pour le toolBar
				bGo.setEnabled(true);
				bStop.setEnabled(false);
				
			}
		}
	}
	
	class InverserListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(pan.getADroite())
				pan.setADroite(false);
			else
				pan.setADroite(true);
			if(pan.getEnBas())
				pan.setEnBas(false);
			else
				pan.setEnBas(true);
		}
	}
	
	class PlayAnimation implements Runnable{
		public void run(){
			go();
		}
	}
	
	class FormeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource().getClass().getName().equals("javax.swing.JRadioButtonMenuItem"))
				pan.setForme(((JRadioButtonMenuItem)e.getSource()).getText());
			else{
				if(e.getSource() == bRond)
					rond.doClick();
				else if(e.getSource() == bCarre)
					carre.doClick();
				else if(e.getSource() == bTriangle)
					triangle.doClick();
				else 
					etoile.doClick();
			}
		}
	}
	
	class MorphListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			//si la case est cochée, on active le mode morphing
			if(morph.isSelected())
				pan.setMorph(true);
			else
				pan.setMorph(false);
		}
	}
	
	class InitListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			pan.setLargeurForme(50);
			pan.setHauteurForme(50);
		}
	}
	
	class CouleurFondListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String couleur = ((JMenuItem)e.getSource()).getText().toUpperCase();
			if(couleur.equals("BLEU"))
				pan.setCouleurFond(Color.blue);
			else if(couleur.equals("ROUGE"))
				pan.setCouleurFond(Color.red);
			else if(e.getSource() == vertBack)
				pan.setCouleurFond(Color.green);
			else
				pan.setCouleurFond(Color.white);
		}
	}
	
	class CouleurFormeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String couleur = ((JMenuItem)e.getSource()).getText().toUpperCase();
			if(couleur.equals("BLEU"))
				pan.setCouleurForme(Color.blue);
			else if(couleur.equals("ROUGE"))
				pan.setCouleurForme(Color.red);
			else if(couleur.equals("VERT"))
				pan.setCouleurForme(Color.green);
			else
				pan.setCouleurForme(Color.white);
		}
	}
	
	public static void main(String[] args) {
		Fenetre fen = new Fenetre();
	}
	
}
	
