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
  /**
   * variable setting the history size.
   */
  private static final int HISTORYALLOWEDSIZE = 5;
  /**
   * the history linkedlist.
   */
  private LinkedList<String> history = new LinkedList<String>();
  /**
   * a variable keeping track of the current opereation.
   */
  private int currentOperation = 0;

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
  public final String getResult() {
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

  /**
   * returns the calculated result as a string.
   */
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
  public final String current() {
    if (!history.isEmpty()) {
      return history.get(currentOperation);
    }
    return null;
  }

  @Override
  public final String prev() {
    if (!history.isEmpty() && currentOperation > 0) {
      currentOperation--;
      return history.get(currentOperation);
    }
    return null;
  }

  @Override
  public final String next() {
    if (!history.isEmpty() && currentOperation < history.size() - 1) {
      currentOperation++;
      return history.get(currentOperation);
    }
    return null;
  }

  @Override
  public final void save() {
    final File file = new File("history.txt");
    try {
      final FileWriter writer = new FileWriter(file);
      writer.write(currentOperation + System.getProperty("line.separator"));
      for (final String x : history) {
        writer.write(x + System.getProperty("line.separator"));
      }
      writer.close();
    } catch (final Exception e) {
      throw new RuntimeException();
    }
  }

  @Override
  public final void load() {
    final File file = new File("history.txt");
    final LinkedList<String> x = new LinkedList<String>();
    try {
      final Scanner sc = new Scanner(file);
      currentOperation = Integer.parseInt(sc.nextLine());
      while (sc.hasNext()) {
        x.add(sc.nextLine());
      }
    } catch (final Exception e) {
      throw new RuntimeException();
    }
    history = new LinkedList<>();
    history.addAll(x);
/*    if (x.size() == HISTORYALLOWEDSIZE) {
      currentOperation = history.size() - 1;
    } else {
      currentOperation = 0;
    }*/
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
