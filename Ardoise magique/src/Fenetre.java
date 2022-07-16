import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;

public class Fenetre extends JFrame {
	private Panneau pan = new Panneau();
	private JPanel content = new JPanel();
	private JMenuBar menu = new JMenuBar();
	private JMenu	mFichier = new JMenu("Fichier"),
					mEdition = new JMenu("Edition"),
					mForme = new JMenu("Forme du pointeur"),
					mCouleur = new JMenu("Couleur du pointeur");
	private JRadioButtonMenuItem rond = new JRadioButtonMenuItem("Rond"),
								 carre = new JRadioButtonMenuItem("Carré");
	private JMenuItem gomme = new JMenuItem("Gomme"),
					  effacer = new JMenuItem("Effacer"),
					  quitter = new JMenuItem("Quitter"),
					  rouge = new JMenuItem("Rouge"),
					  vert = new JMenuItem("Vert"),
					  bleu = new JMenuItem("Bleu"),
					  noir = new JMenuItem("Noir");
	private JToolBar toolBar = new JToolBar();
	private JButton bRond = new JButton(new ImageIcon("images/rond.jpg")),
					bCarre = new JButton(new ImageIcon("images/carre.jpg")),
					bRouge = new JButton(new ImageIcon("images/rouge.jpg")),
					bVert = new JButton(new ImageIcon("images/vert.jpg")),
					bBleu = new JButton(new ImageIcon("images/bleu.jpg")),
					bNoir = new JButton(new ImageIcon("images/noir.jpg")),
					bGomme = new JButton(new ImageIcon("images/gomme.png"));
	private List<JButton> colorList = new ArrayList<JButton>();
	
	private JPopupMenu popMenu = new JPopupMenu();
	private JMenu couleurFond = new JMenu("Couleur de fond");
	private JMenuItem delete = new JMenuItem("Effacer"),
					  fBleu = new JMenuItem("Bleu"),
			          fRouge = new JMenuItem("Rouge"),
			          fGris = new JMenuItem("Gris clair"),
			          fNoir = new JMenuItem("Noir"),
			          fBlanc = new JMenuItem("Blanc"),
			          quit = new JMenuItem("Quitter");
	private List<JMenuItem> fondList = new ArrayList<JMenuItem>();
	
	private FormeListener fl = new FormeListener();
	private CouleurListener cl = new CouleurListener();
	private GommeListener gl = new GommeListener();
	private CouleurFondListener cfl = new CouleurFondListener();
	private QuitterListener ql = new QuitterListener();
	private EffacerListener el = new EffacerListener();
	
	private Color fondBouton = Color.white;
	
	public Fenetre(){
		this.setTitle("Ardoise magique");
		this.setSize(800, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
				
		initMenu();
		initToolBar();
		popUpMenu();
		
		content.setLayout(new BorderLayout());
		content.add(pan, BorderLayout.CENTER);
		content.add(toolBar, BorderLayout.NORTH);
		this.setContentPane(content);
		this.setVisible(true);
	}
	
	public void popUpMenu(){
		delete.addActionListener(el);
		quit.addActionListener(ql);
		fondList.add(fBlanc);
		fondList.add(fRouge);
		fondList.add(fBleu);
		fondList.add(fGris);
		fondList.add(fNoir);
		for(JMenuItem jmi : fondList){
			couleurFond.add(jmi);
			jmi.addActionListener(cfl);
		}
		pan.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent e){
				if(e.isPopupTrigger()){
					popMenu.add(delete);
					popMenu.add(couleurFond);
					popMenu.addSeparator();
					popMenu.add(quit);
					popMenu.show(pan, e.getX(), e.getY());
				}
				
			}
		});
	}
	
	public void initMenu(){
		//définition des mnémoniques et accélerateurs
		mFichier.setMnemonic('F');
		mEdition.setMnemonic('E');
		carre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK 
				+ KeyEvent.SHIFT_DOWN_MASK));
		noir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_MASK));
		gomme.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_MASK));
		
		//configuration de la disposition
		mFichier.add(effacer);
		mFichier.addSeparator();
		mFichier.add(quitter);
		
		mEdition.add(gomme);
		mEdition.add(mForme);
		mForme.add(rond);
		rond.setSelected(true);
		mForme.add(carre);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rond);
		bg.add(carre);
		mEdition.add(mCouleur);
		mCouleur.add(rouge);
		mCouleur.add(bleu);
		mCouleur.add(vert);
		mCouleur.add(noir);
		
		//ajout des listeners
		rond.addActionListener(fl);
		carre.addActionListener(fl);
		
		rouge.addActionListener(cl);
		bleu.addActionListener(cl);
		vert.addActionListener(cl);
		noir.addActionListener(cl);
		
		quitter.addActionListener(ql);
		effacer.addActionListener(el);
		gomme.addActionListener(gl);
		
		//finition
		menu.add(mFichier);
		menu.add(mEdition);
		this.setJMenuBar(menu);
	}
	
	public void initToolBar(){
		//Disposition
		toolBar.add(bRond);
		toolBar.add(bCarre);
		toolBar.add(bGomme);
		toolBar.addSeparator();
		colorList.add(bVert);
		colorList.add(bRouge);
		colorList.add(bBleu);
		colorList.add(bNoir);
		for(JButton butt : colorList){
			toolBar.add(butt);
			//Couleur arrière-plan boutons
			butt.setBackground(fondBouton);
			//ajout des listeners
			butt.addActionListener(cl);
		}
		
		//Couleur arrière-plan boutons
		bRond.setBackground(fondBouton);
		bCarre.setBackground(fondBouton);
		bNoir.setBackground(Color.lightGray);
		
		//ajout des listeners
		bRond.addActionListener(fl);
		bCarre.addActionListener(fl);
		bGomme.addActionListener(gl);
	}
	class FormeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			pan.setIsGomme(false);
			if(e.getSource() == rond)
				pan.setCarre(false); 
			else if(e.getSource() == carre)
				pan.setCarre(true);
			else if(e.getSource() == bRond){
				rond.doClick();
			}
			else if (e.getSource() == bCarre){
				carre.doClick();
			}			
		}
	}
	
	class CouleurListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource().getClass().getName().equals("javax.swing.JButton")){
				if(e.getSource() == bRouge)
					pan.setCouleurForme(Color.red);
				else if(e.getSource() == bBleu)
					pan.setCouleurForme(Color.blue);
				else if(e.getSource() == bVert)
					pan.setCouleurForme(Color.green);
				else
					pan.setCouleurForme(Color.black);
				for(JButton butt : colorList){
					if(e.getSource() == butt)
						butt.setBackground(Color.lightGray);
					else
						butt.setBackground(fondBouton);
				}
			}
			else{
				if(e.getSource() == rouge)
					bRouge.doClick();
				else if(e.getSource() == bleu)
					bBleu.doClick();
				else if(e.getSource() == vert)
					bVert.doClick();
				else if(e.getSource() == noir)
					bNoir.doClick();
			}
		}
	}
	
	class GommeListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			pan.setIsGomme(true);
		}
	}
	
	class CouleurFondListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == fRouge)
				pan.setCouleurFond(Color.red);
			else if(e.getSource() == fBleu)
				pan.setCouleurFond(Color.blue);
			else if(e.getSource() == fNoir)
				pan.setCouleurFond(Color.black);
			else if(e.getSource() == fGris)
				pan.setCouleurFond(Color.lightGray);
			else
				pan.setCouleurFond(Color.white);
		}
	}
	
	class QuitterListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	
	class EffacerListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			pan.effacer();
			pan.repaint();
		}
	}
	
	public static void main(String[] args) {
		Fenetre fen = new Fenetre();
	}

}
