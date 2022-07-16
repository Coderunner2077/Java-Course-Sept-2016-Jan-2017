package com.pendu.panneaux;

import javax.swing.KeyStroke;

public class test {

	public static void main(String[] args) {
		/*
		String lettre = "T";
		String motSecret = "equitation", motCache = "**********";
		motSecret = motSecret.toUpperCase();
		char[] motMagic = motSecret.toCharArray();
		char laLettre = lettre.charAt(0);
		char[] lettres = motCache.toCharArray();
		
		for(int i = 0; i < motSecret.length(); i++){
			if(motMagic[i] == laLettre)
				lettres[i] = laLettre;
		}
		motCache = motCache.copyValueOf(lettres);
		
		System.out.println("laLettre : " + laLettre);
		System.out.println("motCache : " + motCache);
		KeyStroke.getKeyStroke('A');
		*/
		String motSecret = "a-b-b-a\\tt-o-i-r";
		while(motSecret.contains("\\") || motSecret.contains("-")){
			if(motSecret.contains("\\"))
				motSecret = motSecret.substring(0, motSecret.indexOf("\\")) +
				motSecret.substring(motSecret.indexOf("\\") + 1, motSecret.length());
			if(motSecret.contains("-"))
				motSecret = motSecret.substring(0, motSecret.indexOf("-")) +
				motSecret.substring(motSecret.indexOf("-") + 1, motSecret.length());
		}
		
		
		System.out.println(motSecret);
	}

}
