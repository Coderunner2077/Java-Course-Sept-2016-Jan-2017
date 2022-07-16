package com.pendu.observable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.interfaces.observe.Observable;
import com.interfaces.observe.Observateur;

public class TopScore {
	private File file;
	private List<Score> scoreList;
	private List<Observateur> listObs;
	private Score score;
	private boolean isTop = false;
	private String message = "";
	public TopScore(){
		file = new File("top_score.txt");
		if(file.exists()){
			try(FileInputStream fis = new FileInputStream(file);
					ObjectInputStream ois = new ObjectInputStream(fis)){
					scoreList = (ArrayList<Score>)ois.readObject();
				}catch(IOException e){}
				catch(ClassNotFoundException e){}
		}
		else
			scoreList = new ArrayList<Score>();
	}
	public List<Score> getListe(){
		return scoreList;
	}
	public void addScore(String pseudo, int scor, int nbMots){
		score = new Score(pseudo, scor, nbMots);
		int indice = 0;
		if(!scoreList.isEmpty()){
			for(Score s : scoreList){
				if(s.getScore() < score.getScore()){
					scoreList.add(indice, score);
					isTop = true;
					break;
				}
				indice++;
			}
		}
		else
			scoreList.add(score);		
		
		for(int i = 10; i < scoreList.size(); i++)
			scoreList.remove(i);
		try(FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos)){
				oos.writeObject(scoreList);
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	
	public boolean isTopScore(int score){
		isTop = false;
		if(!scoreList.isEmpty()){
			for(Score s : scoreList){
				if(s.getScore() < score){
					isTop = true;
					break;
				}
			
			}
		}
		else
			isTop = true;
		
		return isTop;
	}
}
