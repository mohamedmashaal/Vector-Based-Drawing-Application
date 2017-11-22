package eg.edu.alexu.csd.oop.db.utils;

public class Comparator<T> {
	public boolean compare(T elem1 , T elem2 , String operator) {
		if(elem1 instanceof Integer && elem2 instanceof Integer) {
			Integer element1 = (Integer)elem1;
			Integer element2 = (Integer)elem2;
			if(operator.equals("=")) {
				return Integer.compare(element1.intValue(), element2.intValue()) == 0 ? true : false ;
			}
			else if(operator.equals(">")) {
				return Integer.compare(element1.intValue(), element2.intValue()) > 0  ? true : false ;
			}
			else if(operator.equals("<")) {
				return Integer.compare(element1.intValue(), element2.intValue()) < 0 ? true : false ;
			}
		}
		else if(elem1 instanceof String && elem2 instanceof String) {
			String element1 = (String)elem1;
			String element2 = (String)elem2;
			if(operator.equals("=")) {
				return element1.compareToIgnoreCase(element2) == 0 ? true : false ;
			}
			else if(operator.equals(">")) {
				return element1.compareTo(element2) > 0 ? true : false ;
			}
			else if(operator.equals("<")) {
				return element1.compareTo(element2) < 0 ? true : false ;
			}
		}
		return false ;
	}
}
