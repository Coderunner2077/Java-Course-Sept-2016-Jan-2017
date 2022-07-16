import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class ZDialog extends JDialog{
	private ZDialogInfo zInfo = new ZDialogInfo();
	private JLabel nomLabel, genreLabel, cheveuxLabel, ageLabel, tailleLabel, taille2Label,
		icon;
	private JRadioButton tranche1, tranche2, tranche3, tranche4;
	private JComboBox genre, cheveux;
	private JTextField nom, taille;
	
	public ZDialog(JFrame parent, String title, boolean modal){
		//On appelle le constructeur de JDialog correspondant
		super(parent, title, modal);
		//On spécifie une taille
		this.setSize(600, 270);
		//La position
		this.setLocationRelativeTo(null);
		//La boîte ne devra pas être redimensionnable
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		//Enfin, on l'affiche PAS
		//this.setVisible(true);
		this.initComponent();
	}
	
	
	public ZDialogInfo showZDialog(){
		this.setVisible(true);
	
		return this.zInfo;
	}
	
	private void initComponent(){
		//Icône
		icon = new JLabel(new ImageIcon("images/icone.png"));
		JPanel panIcon = new JPanel();
		panIcon.setBackground(Color.white);
		panIcon.setLayout(new BorderLayout());
		panIcon.add(icon);
		
		//Le nom
		JPanel panNom = new JPanel();
		panNom.setBackground(Color.white);
		panNom.setPreferredSize(new Dimension(220, 60));
		panNom.setBorder(BorderFactory.createTitledBorder("Nom du personnage"));
		nomLabel = new JLabel("Saisir un nom : ");
		nom = new JTextField();
		nom.setPreferredSize(new Dimension(100, 25));
		panNom.add(nomLabel);
		panNom.add(nom);
		
		//Le genre
		JPanel panGenre = new JPanel();
		panGenre.setBackground(Color.white);
		panGenre.setPreferredSize(new Dimension(220, 60));
		panGenre.setBorder(BorderFactory.createTitledBorder("Le genre du personnage"));
		genreLabel = new JLabel("Le sexe : ");
		genre = new JComboBox();
		genre.addItem("Masculin");
		genre.addItem("Féminin");
		panGenre.add(genreLabel);
		panGenre.add(genre);
		
		//L'âge
		JPanel panAge = new JPanel();
		panAge.setBackground(Color.white);
		panAge.setPreferredSize(new Dimension(440, 60));
		panAge.setBorder(BorderFactory.createTitledBorder("L'âge du personnage"));
		tranche1 = new JRadioButton("15-25 ans");
		tranche1.setSelected(true);
		tranche2 = new JRadioButton("26-35 ans");
		tranche3 = new JRadioButton("36-50 ans");
		tranche4 = new JRadioButton("+ de 50 ans");
		ButtonGroup bg = new ButtonGroup();
		bg.add(tranche1);
		bg.add(tranche2);
		bg.add(tranche3);
		bg.add(tranche4);
		panAge.add(tranche1);
		panAge.add(tranche2);
		panAge.add(tranche3);
		panAge.add(tranche4);
		
		//La taille
		JPanel panTaille = new JPanel();
		panTaille.setBackground(Color.white);
		panTaille.setPreferredSize(new Dimension(220, 60));
		panTaille.setBorder(BorderFactory.createTitledBorder("La taille du personnage"));
		tailleLabel = new JLabel("La taille : ");
		taille = new JTextField("180");
		taille.setPreferredSize(new Dimension(90, 25));
		taille2Label = new JLabel("cm");
		panTaille.add(tailleLabel);
		panTaille.add(taille);
		panTaille.add(taille2Label);
		
		//La couleur de cheveux
		JPanel panCheveux = new JPanel();
		panCheveux.setBackground(Color.white);
		panCheveux.setPreferredSize(new Dimension(220, 60));
		panCheveux.setBorder(BorderFactory.createTitledBorder("La couleur de cheveux"));
		cheveuxLabel = new JLabel("Cheveux : ");
		String[] cheveuxStr = {"Blond", "Roux", "Brun", "Blanc", "Chauve", "Iroquois"};
		cheveux = new JComboBox(cheveuxStr);
		panCheveux.add(cheveuxLabel);
		panCheveux.add(cheveux);
		
		JPanel content = new JPanel();
		content.setBackground(Color.white);
		content.add(panNom);
		content.add(panGenre);
		content.add(panAge);
		content.add(panTaille);
		content.add(panCheveux);
		
		JPanel control = new JPanel();
		JButton okBouton = new JButton("OK");
		
		okBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				zInfo = new ZDialogInfo(nom.getText(), (String)genre.getSelectedItem(), 
						getAge(), (String)cheveux.getSelectedItem(), getTaille());
				
				setVisible(false);
			}
		});
		
		JButton cancelBouton = new JButton("Cancel");
		cancelBouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});
		
		control.add(okBouton);
		control.add(cancelBouton);
		this.setBackground(Color.white);
		this.getContentPane().add(icon, BorderLayout.WEST);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.getContentPane().add(control, BorderLayout.SOUTH);
	}
	
	public String getAge(){
		return	(tranche1.isSelected()) ? tranche1.getText() :
				(tranche2.isSelected())? tranche2.getText() :
				(tranche3.isSelected())? tranche3.getText() :
				(tranche4.isSelected())? tranche4.getText() :
				 tranche1.getText();
	}
	
	public String getTaille(){
		return (taille.getText().equals("")) ? "180" : taille.getText();
	}
	
}
