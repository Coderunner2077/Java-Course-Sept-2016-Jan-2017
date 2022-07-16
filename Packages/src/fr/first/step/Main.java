package fr.first.step;

public class Main {

	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		// aucun problème ici
		a.protectedString = "attr protected accessibles seulement à l'intérieur du package";
		System.out.println(a.protectedString);
		System.out.println(a.defaultStr);

	}

}
