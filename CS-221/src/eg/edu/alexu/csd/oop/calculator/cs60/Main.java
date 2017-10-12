package eg.edu.alexu.csd.oop.calculator.cs60;

public class Main {
	public static void main(String [] args) {
		CalculatorImp x = new CalculatorImp();
		x.input("2+3");
		x.input("3+4");
		x.input("4+5");
		x.input("5+6");
		x.input("6+7");
		x.save();
		x.load();
		for(int i = 0 ; i < 5 ; i++)
			System.out.println(x.prev());
	}
}
