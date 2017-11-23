package eg.edu.alexu.csd.oop.draw;

public interface Subject {
	public void addObserver(Observer observer);

	public void removeObserver(Observer observer);

	public void notifyObservers();

	public void notifyObserversSelection();
}
