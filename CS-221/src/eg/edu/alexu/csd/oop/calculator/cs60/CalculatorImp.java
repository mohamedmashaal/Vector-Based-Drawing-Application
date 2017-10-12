package eg.edu.alexu.csd.oop.calculator.cs60;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

import eg.edu.alexu.csd.oop.calculator.Calculator;

/**
 * @author Mohamed Mashaal.
 *
 */
public class CalculatorImp implements Calculator {
  private LinkedList<String> history = new LinkedList<String>();
  private int currentOperation = 0;
  private final static int HISTORYALLOWEDSIZE = 5;

  @Override
  public final void input(final String s) {
    if (history.size() >= HISTORYALLOWEDSIZE) {
      history.poll();
      history.add(s);
    } else {
      history.add(s);
      currentOperation = history.size() - 1;
    }
  }

  @Override
  public String getResult() {
    // TODO Auto-generated method stub
    try {
      final String[] formula = current().split("[-+*/]");
      final int operatorsNum = countOperators(current());
      if (formula.length == 2 && operatorsNum == 1) {
        return calulate(Double.parseDouble(formula[0]),
            Double.parseDouble(formula[1]),
            current());
      }
      throw new RuntimeException();
    } catch (final Exception e) {
      throw new RuntimeException();
    }
  }

  private String calulate(final Double num1,
      final Double num2,
      final String formula) {
    try {
      if (formula.indexOf('+') != -1) {
        return new Double(num1 + num2).toString();
      } else if (formula.indexOf('-') != -1) {
        return new Double(num1 - num2).toString();
      } else if (formula.indexOf('*') != -1) {
        return new Double(num1 * num2).toString();
      } else if (formula.indexOf('/') != -1) {
        return new Double(num1 / num2).toString();
      }
      return null;
    } catch (final Exception e) {
      throw new RuntimeException();
    }
  }

  @Override
  public String current() {
    // TODO Auto-generated method stub
    if (!history.isEmpty()) {
      return history.get(currentOperation);
    }
    return null;
  }

  @Override
  public String prev() {
    // TODO Auto-generated method stub
    if (!history.isEmpty() && currentOperation > 0) {
      currentOperation--;
      return history.get(currentOperation);
    }
    return null;
  }

  @Override
  public String next() {
    // TODO Auto-generated method stub
    if (!history.isEmpty() && currentOperation < history.size() - 1) {
      currentOperation++;
      return history.get(currentOperation);
    }
    return null;
  }

  @Override
  public void save() {
    // TODO Auto-generated method stub
    final File file = new File("history.txt");
    try {
      final FileWriter writer = new FileWriter(file);
      for (final String x : history) {
        writer.write(x + System.getProperty("line.separator"));
      }
      writer.close();
    } catch (final Exception e) {
      throw new RuntimeException();
    }
  }

  @Override
  public void load() {
    // TODO Auto-generated method stub
    final File file = new File("history.txt");
    final LinkedList<String> x = new LinkedList<String>();
    try {
      final Scanner sc = new Scanner(file);
      while (sc.hasNext()) {
        x.add(sc.nextLine());
      }
    } catch (final Exception e) {
      throw new RuntimeException();
    }
    history = new LinkedList<>();
    history.addAll(x);
    if (x.size() == HISTORYALLOWEDSIZE) {
      currentOperation = history.size() - 1;
    } else {
      currentOperation = 0;
    }
  }

  private int countOperators(final String formula) {
    final String operators = "+-*/";
    int count = 0;
    for (int i = 0; i < formula.length(); i++) {
      if (operators.indexOf(formula.charAt(i)) != -1) {
        count++;
      }
    }
    return count;
  }
}