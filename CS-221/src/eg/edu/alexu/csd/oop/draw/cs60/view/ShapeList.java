package eg.edu.alexu.csd.oop.draw.cs60.view;

import javax.swing.JList;

import eg.edu.alexu.csd.oop.draw.Shape;

public class ShapeList<T> extends JList<Shape> {
	
	public ShapeList() {
		super();
		setListData(new Shape [0]);
	}
	
	public void update(Shape[] shapes) {
		setListData(shapes);
	}
}
