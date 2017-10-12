package eg.edu.alexu.csd.oop.calculator;

public interface Calculator {
	/* Take input from user */
	public void input(String s);
	/* Return the result of the current operations or throws a runtime
	 exception */
	public String getResult();
	/* return the current formula */
	public String current ();
	/* return the last operation in String format, or Null if no more history
	 available */
	public String prev();
	/* return the next operation in String format, or Null if no more history
	 available */
	public String next();
	/* Save in file the last 5 done Operations */
	public void save();
	/* Load from file the saved operations */
	public void load();
}
