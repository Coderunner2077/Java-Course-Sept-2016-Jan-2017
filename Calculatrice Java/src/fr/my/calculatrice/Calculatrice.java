package fr.my.calculatrice;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculatrice extends JFrame{
	private JPanel content = new JPanel();
	private JPanel panNombres = new JPanel();
	private PanLabel panLabel = new PanLabel();
	private JPanel panOperateurs = new JPanel();
	private JLabel label = new JLabel();
	private List<JButton> boutonsNombres = new ArrayList<JButton>();
	private JButton bPoint = new JButton(".");
	private JButton bEgal = new JButton("=");
	private JButton boutonC = new JButton("C");
	private List<JButton> boutonsOperateurs = new ArrayList<JButton>();
	private JButton bPlus = new JButton("+");
	private JButton bMoins = new JButton("-");
	private JButton bMultiplier = new JButton("*");
	private JButton bDiviser = new JButton("/");
	private String[] boutonsNombresStr = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0"};
	private BigDecimal nombre, nombre2, resultat;
	private boolean go = false;
	private String strNb1 = "";
	private String strNb = "";
	private String signe = "";
	
	private BoutonOperateursListener bol = new BoutonOperateursListener();
	private BoutonNombresListener bnl = new BoutonNombresListener();
	
	public Calculatrice(){
		this.setTitle("My Calculette");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 400);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		initComponents();
		this.setVisible(true);
	}
	
	private void initComponents(){
		//Définition du label
		label.setHorizontalAlignment(JLabel.RIGHT);
		label.setText("0");
		Font police = new Font("DS-digital", Font.BOLD, 30);
		label.setPreferredSize(new Dimension(280, 50));
		label.setFont(police);
		panLabel.add(label);
		panLabel.setBorder(BorderFactory.createLineBorder(Color.black));	
		content.setLayout(new BorderLayout());
		content.setPreferredSize(new Dimension(300, 400));		
		content.add(panLabel, BorderLayout.NORTH);
		//Définition panOperateurs
		panOperateurs.setPreferredSize(new Dimension(75, 300));
		boutonC.setPreferredSize(new Dimension(70, 70));
		boutonC.setForeground(Color.red);
		panOperateurs.add(boutonC);
		boutonsOperateurs.add(bPlus);
		boutonsOperateurs.add(bMoins);
		boutonsOperateurs.add(bMultiplier);
		boutonsOperateurs.add(bDiviser);
		Dimension d2 = new Dimension(70, 51);
		for(int i = 0; i < 4; i++){
			boutonsOperateurs.get(i).setPreferredSize(d2);
			panOperateurs.add(boutonsOperateurs.get(i));
			boutonsOperateurs.get(i).addActionListener(bol);
		}
		content.add(panOperateurs, BorderLayout.EAST);
		
		//ajout des écouteurs au boutonC
		boutonC.addActionListener(new BoutonCListener());
		
		//définition des boutons des nombres
		panNombres.setPreferredSize(new Dimension(200, 300));
		Dimension d = new Dimension(65, 70);
		for(int i = 0; i < 10; i++){
			boutonsNombres.add(new JButton(boutonsNombresStr[i]));
			boutonsNombres.get(i).setPreferredSize(d);
			boutonsNombres.get(i).addActionListener(bnl);
		}
		
		bPoint.addActionListener(new BoutonPointListener());
		bPoint.setPreferredSize(d);
		bEgal.addActionListener(new BoutonEgalListener());
		bEgal.setPreferredSize(d);
		for(int i = 0; i < 10; i++)
			panNombres.add(boutonsNombres.get(i));
		panNombres.add(bPoint);
		panNombres.add(bEgal);
		content.add(panNombres, BorderLayout.CENTER);
				
		this.setContentPane(content);
	}
	
	class BoutonNombresListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			if(!go){
				if(strNb1.length() < 16){
					strNb1 += ((JButton)arg0.getSource()).getText();
					strNb = strNb1;
					label.setText(strNb);
					nombre = new BigDecimal(strNb);
				}
			}
			else{
				if(strNb1.length() < 16){
					strNb1 += ((JButton)arg0.getSource()).getText();
					strNb = strNb1;
					label.setText(strNb);
					nombre2 = new BigDecimal(strNb);
				}
			}
			
		}	
	}
	
	class BoutonOperateursListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){			
			if(!go){
				signe = ((JButton)arg0.getSource()).getText();
				strNb1 = "";
				go = true;
			}
			else{
				nombre2 = operation();
				strNb = strNb.valueOf(nombre2);
				strNb = arrondir(strNb, nombre2);
				label.setText(strNb);
				signe = ((JButton)arg0.getSource()).getText();
				strNb1 = "";
				nombre = nombre2;
			}
		}
	}
		
	class BoutonCListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			strNb = "0";
			strNb1 = "";
			nombre = new BigDecimal("0");
			nombre2 = new BigDecimal("0");
			label.setText("0");
			go = false;
		}
	}
	
	class BoutonPointListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			if(!strNb1.contains(".")){
				strNb1 += ".";
				strNb = strNb1;
				label.setText(strNb);		
			}
		}
	}
	
	class BoutonEgalListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			nombre2 = operation();
			strNb = strNb.valueOf(nombre2);
			strNb = arrondir(strNb, nombre2);
			label.setText(strNb);
			strNb1 = "";
			go = false;
			nombre = nombre2;
		}
	}
	
	private BigDecimal operation(){
		if(signe.equals("+"))
			resultat = nombre.add(nombre2);
		else if(signe.equals("-"))
			resultat = nombre.subtract(nombre2);
		else if(signe.equals("*"))
			resultat = nombre.multiply(nombre2);
		else if(signe.equals("/")){
			if(nombre2 == new BigDecimal("0"))
				resultat = new BigDecimal("0");
			else{
				try{
					resultat = nombre.divide(nombre2, MathContext.DECIMAL64);
				}catch(ArithmeticException e){
					resultat = new BigDecimal("0");
				}
			}
				
		}
		return resultat;
	}
	
	private String arrondir(String str, BigDecimal nb){
		if(str.length() > 16){
			if(str.contains(".") 
			&& str.length() - str.substring(str.indexOf(".") + 1, str.length()).length() <= 16)
			{
				boolean ok = false;
				int i = 16;
				while(!ok){
					i--;
					BigDecimal val;
					int chiffreFinal;
					String c = str.substring(str.length() - 1, str.length());
					chiffreFinal = Integer.valueOf(c).intValue();
					if(chiffreFinal >= 5)
						val = (new BigDecimal(str)).setScale(i, BigDecimal.ROUND_CEILING);
					else
						val = (new BigDecimal(str)).setScale(i, BigDecimal.ROUND_DOWN);
					str = str.valueOf(val);
					if(str.length() <= 16)
						ok = true;
				}
					
					nb = new BigDecimal(str);
				}
				else{
					str = str.substring(0, 16);
					nb = new BigDecimal(str);
				}
			}
		
		return str;
	}
}
