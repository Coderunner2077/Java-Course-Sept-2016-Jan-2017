package fr.first.step;

public class Main {

	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		// aucun probl�me ici
		a.protectedString = "attr protected accessibles seulement � l'int�rieur du package";
		System.out.println(a.protectedString);
		System.out.println(a.defaultStr);

	}

}
