package com.pendu.observable;

import java.io.Serializable;

public class Score implements Serializable{
	private String info;
	private String pseudo;
	private int score;
	private int nbMots;
	public Score(String pseudo, int score, int nbMots){
		this.pseudo = pseudo;
		this.score = score;
		this.nbMots = nbMots;
	}
	
	public String getPseudo(){
		return pseudo;
	}
	
	public int getScore(){
		return score;
	}
	
	public int getNbMots(){
		return nbMots;
	}
	
	public void setPseudo(String pseudo){
		this.pseudo = pseudo;
	}
	
	public void setScore(int score){
		this.score = score;
	}
	
	public void setNbMots(short nbMots){
		this.nbMots = nbMots;
	}
	
	public String toString(){
		info = this.pseudo + " -> " + this.score + " Pts " + "(" + this.nbMots + " mots)\n";
		return info;
	}
}
