package com.pendu.panneaux;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import com.interfaces.observe.Observateur;
import com.pendu.observable.Sesame;
import com.pendu.observable.TopScore;

public class PenduPanel extends JPanel implements Observateur{
	private JPanel panActuel = new JPanel(),
				   panLettres, 
				   panG = new JPanel(),
				   panD = new JPanel();
	private JPanel pan;
	private String motCache = "", lettre, motSecret, message = "", message2 = "";
	private String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", 
			"M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	private List<JButton> bLettres;
	
	private int nbMots = 0, score = 0, erreur = 0;
	private boolean motTrouve = false, isTop = false;
	private Panneau pAccueil = new Panneau();
	private PanTop topPan;
	private ImageIcon icon = new ImageIcon("pendu0.jpg");
	private JLabel labImage,
				   labNbMots = new JLabel("Nombre de mots trouvés : " + nbMots),
				   labScore = new JLabel("Votre score actuel est de : " + score),
				   labMot = new JLabel(motCache);
	
	private JSplitPane split;
	private BoutonListener bl = new BoutonListener();
	
	private char[] charCache, charSecret;
	
	private Sesame sezam;
	private TopScore topScore;
	private List<Observateur> listObs;
	private JPanel content;
	private String pseudo;
	public PenduPanel(){
		listObs = new ArrayList<Observateur>();
		topPan = new PanTop();
		message = topPan.getMessage();
		initClavier();
		initPanneau();
		initSesame();
	}
	private void initClavier(){
		bLettres = new ArrayList<JButton>();
		for(String lettre : alphabet){
			JButton b = new JButton(lettre);
			b.setMinimumSize(new Dimension(45, 30));
			b.addActionListener(bl);
			bLettres.add(b);
		}
		panLettres = new JPanel();
		panLettres.setBackground(Color.white);
		panLettres.setPreferredSize(new Dimension(380, 200));
		
		
		GridLayout gl = new GridLayout(4, 7);
		gl.setHgap(5);
		gl.setVgap(5);
		
		panLettres.setLayout(gl);
		panLettres.setBackground(Color.white);
		for(int i = 0; i < 26; i++){
			panLettres.add(bLettres.get(i));
			if(i == 20){
				JButton butt = new JButton();
				butt.setVisible(false);
				panLettres.add(butt);
			}
		}
	}
	
	private void initPanneau(){
		panG.removeAll();
		panD.removeAll();
		labNbMots = new JLabel("Nombre de mots trouvés : " + nbMots);
		labScore = new JLabel("Votre score actuel est de : " + score);
		labImage = new JLabel(icon);
		panG.setPreferredSize(new Dimension(400, 400));
		panG.setBackground(Color.white);
		Dimension d = new Dimension(400, 30);
		labNbMots.setPreferredSize(d);
		labNbMots.setHorizontalAlignment(JLabel.CENTER);
		labScore.setPreferredSize(d);
		labScore.setHorizontalAlignment(JLabel.CENTER);
		labMot.setPreferredSize(new Dimension(400, 60));
		labMot.setFont(new Font("Arial", Font.BOLD, 40));
		labMot.setForeground(Color.blue);
		labMot.setHorizontalAlignment(JLabel.CENTER);
		
		panD.setBackground(Color.white);
		panG.add(labNbMots);
		panG.add(labScore);
		panG.add(labMot);
		panG.add(panLettres);
	
		panD.add(labImage);
		split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panG, panD);
		split.setDividerSize(0);
		split.setDividerLocation(420);
		split.setBorder(null);
		content = new JPanel();
		content.add(split);
		this.add(content);
	}
	
	public void initSesame(){
		sezam = new Sesame();
		sezam.addObservateur(this);
		sezam.updateObservateur();
		for(int i = 0; i < motSecret.length(); i++){
			motCache += "*";
		}
		charSecret = this.motSecret.toCharArray();
		labMot.setText(motCache);
	}
	
	class BoutonListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			String check = motCache;
			charCache = motCache.toCharArray();
			JButton bouton = (JButton)e.getSource();
			lettre = bouton.getText();
			bouton.setEnabled(false);
			if(erreur < 7 && (motSecret.contains(lettre)|| motSecret.contains("É") || 
				motSecret.contains("È")|| motSecret.contains("Ê") || motSecret.contains("Î") ||
				motSecret.contains("Ï") || motSecret.contains("Â") || motSecret.contains("À") ||
				motSecret.contains("Ô") || motSecret.contains("Ù") || motSecret.contains("Û")
				|| motSecret.contains("Ç"))){
				
				char laLettre = lettre.charAt(0);
				for(int i = 0; i < motSecret.length(); i++){
					if(charSecret[i] == laLettre)
						charCache[i] = laLettre;
					else if(charSecret[i] == 'É' && laLettre == 'E')
						charCache[i] = 'É';
					else if(charSecret[i] == 'Ê' && laLettre == 'E')
						charCache[i] = 'Ê';
					else if(charSecret[i] == 'È' && laLettre == 'E')
						charCache[i] = 'È';
					else if(charSecret[i] == 'Ï' && laLettre == 'I')
						charCache[i] = 'Ï';
					else if(charSecret[i] == 'Î' && laLettre == 'I')
						charCache[i] = 'Î';
					else if(charSecret[i] == 'À' && laLettre == 'A')
						charCache[i] = 'À';
					else if(charSecret[i] == 'Â' && laLettre == 'A')
						charCache[i] = 'Â';
					else if(charSecret[i] == 'Ô' && laLettre == 'O')
						charCache[i] = 'Ô';
					else if(charSecret[i] == 'Û' && laLettre == 'U')
						charCache[i] = 'Û';
					else if(charSecret[i] == 'Ù' && laLettre == 'U')
						charCache[i] = 'Ù';
					else if(charSecret[i] == 'Ç' && laLettre == 'C')
						charCache[i] = 'Ç';
				}
				motCache = motCache.copyValueOf(charCache);			
				labMot.setText(motCache);
				if(!motCache.contains("*")){
					updateScore();
				}
			}
			
			if(check.equals(motCache)){
				erreur++;
				updatePendu();
			}
		}
	}
	
	private void updateScore(){
		isTop = false;
		if(erreur == 0)
			score += 100;
		else if(erreur == 1)
			score += 50;
		else if(erreur == 2)
			score += 35;
		else if(erreur == 3)
			score += 25;
		else if(erreur == 4)
			score += 15;
		else if(erreur == 5)
			score += 10;
		else if(erreur == 6)
			score += 5;
		this.removeAll();
		motCache = "";
		icon = new ImageIcon("pendu0.jpg");
		erreur = 0;
		nbMots++;
		initClavier();
		initPanneau();
		initSesame();
		this.revalidate();
	}
	
	public void update(String motSecret){
		this.motSecret = motSecret.toUpperCase();
	}
	
	private void updatePendu(){
		panD.removeAll();
		if(erreur == 1){
			icon = new ImageIcon("pendu1.jpg");
		}
		else if(erreur == 2){
			icon = new ImageIcon("pendu2.jpg");
		}
		else if(erreur == 3){
			icon = new ImageIcon("pendu3.jpg");
		}
		else if(erreur == 4){
			icon = new ImageIcon("pendu4.jpg");
		}
		else if(erreur == 5){
			icon = new ImageIcon("pendu5.jpg");
		}
		else if(erreur == 6){
			icon = new ImageIcon("pendu6.jpg");
		}
		else if(erreur == 7){
			icon = new ImageIcon("pendu7.jpg");
		}
		else{
			topScore = new TopScore();
			if(topScore.isTopScore(score)){
				JOptionPane jop = new JOptionPane();
				pseudo = jop.showInputDialog(null, "Quel est votre pseudo ?", "Ligue des"
						+ " champions", JOptionPane.QUESTION_MESSAGE);
				if(!pseudo.equals("") && pseudo != null)
					topScore.addScore(pseudo, score, nbMots);
				this.removeAll();
				this.add(topPan);
				this.revalidate();
			}
			else{
				Thread t2 = new Thread(new TraitementAccueil());
				t2.start();
				this.revalidate();
			}
			
		}
			
		
		labImage = new JLabel(icon);
		panD.add(labImage);
		this.revalidate();
	}
	
	public int getError(){
		return erreur;
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
	}
	
	class TraitementScore implements Runnable{
		public void run(){
			topPan = new PanTop();
			message2 = topPan.getMessage();
			System.out.println(message);
			System.out.println("\n\n" +message2);
			
				content.removeAll();
				content.add(topPan);
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
	
}
