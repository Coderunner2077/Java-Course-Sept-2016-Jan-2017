import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Fenetre extends JFrame {
	/*
	// Positionner un bouton grâce à l'objet d'agencement par défaut du JPanel
	private JPanel pan = new JPanel();
	private JButton bouton = new JButton("Mon premier bouton");
	private JButton bouton2 = new JButton();
	
	public Fenetre(){
		this.setTitle("Positionner des boutons");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		bouton2.setText("Mon deuxième bouton");
		//ajout du bouton à mon content pane
		pan.add(bouton);
		//pan.add(bouton2);
		this.setContentPane(pan);
		this.setVisible(true);
	}
	*/
	
	/*
	 //// Positionner un bouton grâce à l'objet d'agencement par défaut du JFrame
		private JButton bouton = new JButton("Mon premier bouton");	
		private JButton bout = new JButton("mon bout");
		public Fenetre(){
			this.setTitle("Positionner des boutons");
			this.setSize(400, 400);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//ajout du bouton à mon content pane
			this.getContentPane().add(bouton);
			this.getContentPane().add(bout, BorderLayout.EAST);
	
			this.setVisible(true);
		}
	*/
	
////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	// Le BorderLayout
	public Fenetre(){
		this.setTitle("BorderLayout");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//On définit le layout à utiliser sur le content pane
		this.setLayout(new BorderLayout());// facultatif ici => ... par défaut
		
		//ajout des boutons à mon content pane
		this.getContentPane().add(new JButton("NORTH"), BorderLayout.NORTH);
		this.getContentPane().add(new JButton("SOUTH"), BorderLayout.SOUTH);
		this.getContentPane().add(new JButton("WEST"), BorderLayout.WEST);
		this.getContentPane().add(new JButton("EAST"), BorderLayout.EAST);
		this.getContentPane().add(new JButton("CENTRE"), BorderLayout.CENTER);

		this.setVisible(true);
	}
	*/
////////////////////////////////////////////////////////////////////////////////////////////////
	
	/*
	//Le GridLayout
	public Fenetre(){
		this.setTitle("GridLayout");
		this.setSize(400, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//On définit le layout à utiliser sur le content pane
		GridLayout gl = new GridLayout(3, 2);
		gl.setHgap(5);// espacement entre les colonnes
		gl.setVgap(5);// espacement entre les lignes
		this.setLayout(gl);
		
		//ajout des boutons à mon content pane
		this.getContentPane().add(new JButton("1"));
		this.getContentPane().add(new JButton("2"));
		this.getContentPane().add(new JButton("3"));
		this.getContentPane().add(new JButton("4"));
		this.getContentPane().add(new JButton("5"));

		this.setVisible(true);
	}
	*/
	
	/*
	public Fenetre(){
		this.setTitle("Box Layout");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel b1 = new JPanel();
		//On définit le layout en lui indiquant qu'il travaillera en ligne
		b1.setLayout(new BoxLayout(b1, BoxLayout.LINE_AXIS));
		b1.add(new JButton("Bouton 1"));
		
		JPanel b2 = new JPanel();
		//On définit le layout en lui indiquant qu'il travaillera en ligne 
		b2.setLayout(new BoxLayout(b2, BoxLayout.LINE_AXIS));
		b2.add(new JButton("Bouton 2"));
		b2.add(new JButton("Bouton 3"));
		
		JPanel b3 = new JPanel();
		//Idem pour cette ligne
		b3.setLayout(new BoxLayout(b3, BoxLayout.LINE_AXIS));
		b3.add(new JButton("Bouton 4"));
		b3.add(new JButton("Bouton 5"));
		b3.add(new JButton("Bouton 6"));
		
		JPanel b4 = new JPanel();
		//on positionne maintenant ces 3 lignes en colonne		
		b4.setLayout(new BoxLayout(b4, BoxLayout.PAGE_AXIS));
		b4.add(b1);
		b4.add(b2);
		b4.add(b3);
		
		this.getContentPane().add(b4);
		this.setVisible(true);
	}
	*/
///////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	//Autre variante du BoxLayout : avec utilisation du Box à la place du JPanel
	public Fenetre(){
		this.setTitle("Box Layout");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//On crée un conteneur avec gestion horizontale
		Box b1 = Box.createHorizontalBox();
		b1.add(new JButton("Bouton 1"));
		
		//Idem
		Box b2 = Box.createHorizontalBox();
		b2.add(new JButton("Bouton 2"));
		b2.add(new JButton("Bouton 3"));
		
		//Idem
		Box b3 = Box.createHorizontalBox();
		b3.add(new JButton("Bouton 4"));
		b3.add(new JButton("Bouton 5"));
		b3.add(new JButton("Bouton 6"));
		
		//on crée un conteneur avec gestion verticale
		Box b4 = Box.createVerticalBox();
		b4.add(b1);
		b4.add(b2);
		b4.add(b3);
		
		this.getContentPane().add(b4);
		this.setVisible(true);
	}
	*/
//////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	// Le CardLayout
	CardLayout cl = new CardLayout();
	JPanel content = new JPanel();
	//liste des noms de mes conteneurs pour la pile des cartes
	String[] listContent = {"CARD_1", "CARD_2", "CARD_3"};
	int indice = 0;
	
	public Fenetre(){
		this.setTitle("CardLayout");
		this.setSize(300, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//On crée 3 conteneurs de couleurs différentes
		JPanel card1 = new JPanel();
		card1.setBackground(Color.blue);
		JPanel card2 = new JPanel();
		card2.setBackground(Color.red);
		JPanel card3 = new JPanel();
		card3.setBackground(Color.green);
		
		JPanel boutonPane = new JPanel();
		JButton bouton = new JButton("Contenu suivant");
		//Définition de l'action du bouton
		bouton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				//Via cette instruction, on passe au prochain conteneur de la pile 
				cl.next(content);
			}
		});
		
		JButton bouton2 = new JButton("Contenu par indice");
		//Définition de l'action du bouton2
		bouton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				if(++indice > 2)
					indice = 0;
				//Via cette instruction, on passe au conteneur correspondant au nom
				//fourni en paramètre
				cl.show(content, listContent[indice]);
			}
		});
		boutonPane.add(bouton);
		boutonPane.add(bouton2);
		//On définit le layout
		content.setLayout(cl);
		//on ajoute les cartes à la pile avec un nom pour les retrouver
		content.add(card1, listContent[0]);
		content.add(card2, listContent[1]);
		content.add(card3, listContent[2]);
		
		this.getContentPane().add(boutonPane, BorderLayout.NORTH);
		this.getContentPane().add(content, BorderLayout.CENTER);
		this.setVisible(true);
	}
	*/
/////////////////////////////////////////////////////////////////////////////////////////////////
	/*
	// Le GridBagLayout
	public Fenetre(){
		this.setTitle("GridBagLayout");
		this.setSize(300, 160);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//On crée les différents conteneurs de couleur différente
		JPanel cell1 = new JPanel();
		cell1.setBackground(Color.YELLOW);
		cell1.setPreferredSize(new Dimension(60, 40));
		JPanel cell2 = new JPanel();
		cell2.setBackground(Color.red);
		cell2.setPreferredSize(new Dimension(60, 40));
		JPanel cell3 = new JPanel();
		cell3.setBackground(Color.green);
		cell3.setPreferredSize(new Dimension(60, 40));
		JPanel cell4 = new JPanel();
		cell4.setBackground(Color.black);
		cell4.setPreferredSize(new Dimension(60, 40));
		JPanel cell5 = new JPanel();
		cell5.setBackground(Color.cyan);
		cell5.setPreferredSize(new Dimension(60, 40));
		JPanel cell6 = new JPanel();
		cell6.setBackground(Color.BLUE);
		cell6.setPreferredSize(new Dimension(60, 40));
		JPanel cell7 = new JPanel();
		cell7.setBackground(Color.ORANGE);
		cell7.setPreferredSize(new Dimension(60, 40));
		JPanel cell8 = new JPanel();
		cell8.setBackground(Color.DARK_GRAY);
		cell8.setPreferredSize(new Dimension(60, 40));
		
		//Le conteneur principal
		JPanel content = new JPanel();
		content.setPreferredSize(new Dimension(300, 120));
		content.setBackground(Color.white);
		//On définit le layout manager
		content.setLayout(new GridBagLayout());
		
		//L'objet servant à positionner les composants
		GridBagConstraints gbc = new GridBagConstraints();
		
		//On positionne la case de départ du composant
		gbc.gridx = 0;
		gbc.gridy = 0;
		// la taille en hauter et en largeur
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		content.add(cell1, gbc);
		//-----------------------------------
		gbc.gridx = 1;
		content.add(cell2, gbc);
		//-----------------------------------
		gbc.gridx = 2;
		content.add(cell3, gbc);
		//-----------------------------------
		// cette instruction informe le layout que c'est une fin de ligne
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridx = 3;
		content.add(cell4, gbc);
		//-----------------------------------
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.gridheight = 2;
		//Celle-ci indique que la cellule se réplique de façon verticale
		gbc.fill = GridBagConstraints.VERTICAL;
		content.add(cell5, gbc);
		//-----------------------------------
		gbc.gridx = 1;
		gbc.gridheight = 1;
		//Celle-ci indique que la cellule se réplique de façon horizontale
		gbc.fill = GridBagConstraints.HORIZONTAL;
		// ..fin de ligne
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		content.add(cell6, gbc);
		//-----------------------------------
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;//facultatif ici
		content.add(cell7, gbc);
		//-----------------------------------
		gbc.gridx = 3;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		content.add(cell8, gbc);
		
		//on ajoute le conteneur
		this.setContentPane(content);
		this.setVisible(true);
	}
	*/
/////////////////////////////////////////////////////////////////////////////////////////////////
	// Le FlowLayout
	public Fenetre(){
		this.setTitle("FlowLayout");
		this.setSize(150, 200);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//On définit le layout à utiliser sur le content pane
		FlowLayout fl = new FlowLayout();
		this.setLayout(fl);
		
		//ajout des boutons à mon content pane
		this.getContentPane().add(new JButton("1"));
		this.getContentPane().add(new JButton("2"));
		this.getContentPane().add(new JButton("3"));
		this.getContentPane().add(new JButton("4"));
		this.getContentPane().add(new JButton("5"));
		this.setVisible(true);
	}
}
