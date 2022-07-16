package com.pendu.panneaux;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanPropos extends JPanel {
	private JTextArea textArea;
	private Path file;
	private String line = "", text = "";
	
	public PanPropos(){
		file = Paths.get("a_propos.txt");
		try(InputStream input = Files.newInputStream(file);
				BufferedReader reader = new BufferedReader(new InputStreamReader(input))){
				while(line != null){
					line = reader.readLine();
					if(line != null)
						text += line + "\n";
				}
			
		}catch(IOException e){}
		textArea = new JTextArea(text);
		textArea.setEditable(false);
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.add(textArea);
	}
}
