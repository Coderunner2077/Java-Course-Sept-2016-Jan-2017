import fr.rep.controler.AbstractControler;
import fr.rep.controler.CalculetteControler;
import fr.rep.model.AbstractModel;
import fr.rep.model.Calculator;
import fr.rep.vue.Calculatrice;

public class Test {

	public static void main(String[] args) {
		
		AbstractModel calc = new Calculator();
		//AbstractControler controler = new CalculetteControler(calc);
		//Calculatrice calculette = new Calculatrice(controler);
		//calc.addObserver(calculette);
		Calculatrice calculette = new Calculatrice(new CalculetteControler(calc));
		calc.addObserver(calculette);

	}

}
