package fr.rep.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.rep.controler.AbstractControler;
import fr.rep.observer.Observer;

public class Calculatrice extends JFrame implements Observer {
	private String[] tab_string = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ".", "=",
			"C", "+", "-", "*", "/"};
	private JButton[] button = new JButton[tab_string.length];
	private JLabel ecran = new JLabel();
	private JPanel container = new JPanel();
	private Dimension dim = new Dimension(50, 45),
					  dim2 = new Dimension(50, 32);
	private AbstractControler controler;
	
	public Calculatrice(AbstractControler controler){
		this.setTitle("Calculatrice 2.0");
		this.setSize(240, 270);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		this.controler = controler;
		initComponent();
		this.setContentPane(container);
		this.setVisible(true);
	}
	
	private void initComponent(){
		Font police = new Font("Arial", Font.BOLD, 30);
		ecran.setPreferredSize(new Dimension(220, 30));
		ecran.setFont(police);
		ecran.setHorizontalAlignment(JLabel.RIGHT);
		ecran.setBorder(BorderFactory.createLineBorder(Color.black));
		ecran.setForeground(Color.black);
		ecran.setText("0");
		JPanel panEcran = new JPanel();
		panEcran.setPreferredSize(new Dimension(240, 35));
		panEcran.add(ecran);
		
		JPanel operateur = new JPanel();
		operateur.setPreferredSize(new Dimension(60, 220));
		JPanel chiffre = new JPanel();
		chiffre.setPreferredSize(new Dimension(180, 220));
		
		OperateurListener opeListener = new OperateurListener();
		
		for(int i = 0; i < tab_string.length; i++){
			button[i] = new JButton(tab_string[i]);
			button[i].setPreferredSize(dim);
			
			switch(i){
				case 11 :
					button[i].addActionListener(opeListener);
					chiffre.add(button[i]);
					break;
				case 12 :
					button[i].addActionListener(new ResetListener());
					button[i].setForeground(Color.red);
					operateur.add(button[i]);
					break;
				case 13 :
				case 14 :
				case 15 : 
				case 16 :
					button[i].addActionListener(opeListener);
					button[i].setPreferredSize(dim2);
					operateur.add(button[i]);
					break;
				default :
					button[i].addActionListener(new ChiffreListener());
					chiffre.add(button[i]);
					break;
			}
			
			container.setLayout(new BorderLayout());
			container.add(panEcran, BorderLayout.NORTH);
			container.add(operateur, BorderLayout.EAST);
			container.add(chiffre, BorderLayout.CENTER);
		}
	}
	
	class ResetListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			controler.reset();
		}
	}
	
	class OperateurListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			controler.setOperateur(((JButton)e.getSource()).getText());
		}
	}
	
	class ChiffreListener implements ActionListener {
		public void actionPerformed(ActionEvent e){
			controler.setNumber(((JButton)e.getSource()).getText());
		}
	}
	
	public void update(String str){
		ecran.setText(str);
	}

}
