package fr.first.step;

public class A {
	public B b = new B();
	protected String protectedString = "Attr protected pas visible depuis l'ext�rieur du package !!";
	String defaultStr = "Attr default (ie sans d�claration explicite de la port�e) n'est pas"
			+ " visible depuis l'ext�rieur du package";
}
