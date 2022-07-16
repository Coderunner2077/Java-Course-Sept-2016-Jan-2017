package fr.comportement;

public abstract class Comportement {
	public String toString() {
		String str = this.getClass().getName();
		str = str.substring(str.lastIndexOf('.') + 1);
	
		return str;
	}
}
