package fr.first.step;

public class A {
	public B b = new B();
	protected String protectedString = "Attr protected pas visible depuis l'extérieur du package !!";
	String defaultStr = "Attr default (ie sans déclaration explicite de la portée) n'est pas"
			+ " visible depuis l'extérieur du package";
}
