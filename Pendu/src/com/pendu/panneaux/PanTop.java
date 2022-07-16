package com.pendu.panneaux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.pendu.observable.Score;
import com.pendu.observable.TopScore;

public class PanTop extends  JPanel {
	
	private TopScore topScore = new TopScore();
	private JLabel[] labels;
	private Font font;
	private int size = 35;
	private int indice = 0;
	private String message = "";
	public PanTop(){
		this.setBackground(Color.white);
		labels = new JLabel[((ArrayList<Score>)topScore.getListe()).size()];
		for(Score score : topScore.getListe()){
			font = new Font("Arial", Font.BOLD, size);
			labels[indice] = new JLabel(score.toString());
			labels[indice].setPreferredSize(new Dimension(600, 35));
			labels[indice].setFont(font);
			labels[indice].setHorizontalAlignment(JLabel.CENTER);
			this.add(labels[indice]);
			indice++;
			size--;
		}
	}
	
	public String getMessage(){
		for(JLabel label : labels)
			message += label.getText();
		
		return message;
	}
}
