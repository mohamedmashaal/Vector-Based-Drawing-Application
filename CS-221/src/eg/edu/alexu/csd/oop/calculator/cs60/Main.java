package eg.edu.alexu.csd.oop.calculator.cs60;

public class Main {
	public static void main(String [] args) {
		CalculatorImp x = new CalculatorImp();
		x.input("5.2-231");
		x.input("5.2-20");
		x.input("5.2-3123123");
		x.input("5.2-3.41245");
		x.save();
		x.load();
	}
}
