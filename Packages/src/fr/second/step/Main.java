package fr.second.step;
import fr.first.step.A;
import fr.first.step.B;


public class Main {

	public static void main(String[] args) {
		A a = new A();
		
		System.out.println(a.protectedString);
		System.out.println(a.defaultStr);
		B b = new B();
		a.b.str = "toto";

	}

}
