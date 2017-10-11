package eg.edu.alexu.csd.oop.calculator.cs60;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

import eg.edu.alexu.csd.oop.calculator.Calculator;

public class CalculatorImp implements Calculator {
	private LinkedList<String> history = new LinkedList<String>();
	private int currentOperation = 0;
	private final static int historyAllowedSize = 5 ;
	
        
	@Override
	public void input(String s) {
		if(history.size() >= historyAllowedSize) {
			history.poll();
			history.add(s);
		}
		else {
			history.add(s);
			currentOperation = history.size();
		}
	}

	@Override
	public String getResult() {
		// TODO Auto-generated method stub
                try{
		String [] formula = current().split("[-+*/]");
                int operatorsNum = countOperators(current());
		if(formula.length == 2 && operatorsNum == 1) {
			return calulate(Double.parseDouble(formula[0]),Double.parseDouble(formula[1]) , current());
		}
		throw new RuntimeException();}
                catch(Exception e){
                    throw new RuntimeException();
                }
	}

	private String calulate(Double num1, Double num2 , String formula) {
		try{if(formula.indexOf('+') != -1)
			return new Double(num1+num2).toString() ;
		else if(formula.indexOf('-') != -1)
			return new Double(num1-num2).toString() ;
		else if(formula.indexOf('*') != -1)
			return new Double(num1*num2).toString() ;
		else if(formula.indexOf('/') != -1)
			return new Double(num1/num2).toString() ;
                        return null ;
                }
                catch (Exception e){
		throw new RuntimeException();
                }
	}

	@Override
	public String current() {
		// TODO Auto-generated method stub
		if(!history.isEmpty())
			return history.get(currentOperation-1);
		return null;
	}

	@Override
	public String prev() {
		// TODO Auto-generated method stub
		if(!history.isEmpty() && currentOperation -1 > 0) {
                    currentOperation -- ;
                    return history.get(currentOperation-1);
		}
                else if (!history.isEmpty() && currentOperation -1 == 0){
                    return history.get(currentOperation-1);
                }
		return null;
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		if(!history.isEmpty() && currentOperation < history.size()) {
                    currentOperation ++ ;
                    return history.get(currentOperation-1);
		}
                else if (!history.isEmpty() && currentOperation == history.size()){
                    return history.get(currentOperation-1);
                }
		return null;
	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		File file = new File("history.txt");
		try {
			FileWriter writer = new FileWriter(file);
			for(String x : history)
				writer.write(x+System.getProperty("line.separator"));
			writer.close();
		}
		catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		File file = new File("history.txt");
		LinkedList<String> x = new LinkedList<String>() ;
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {
				x.add(sc.nextLine());
			}
		}
		catch (Exception e) {
			throw new RuntimeException();
		}
        history = new LinkedList<>();
		history.addAll(x);
        currentOperation = history.size();
                
	}

    private int countOperators(String formula) {
        String operators = "+-*/";
        int count = 0 ;
        for(int i = 0 ; i < formula.length() ; i ++)
            if(operators.indexOf(formula.charAt(i)) != -1)
                count ++ ;
        return count ;
    }
}