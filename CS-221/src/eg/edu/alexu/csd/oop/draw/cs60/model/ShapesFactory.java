package eg.edu.alexu.csd.oop.draw.cs60.model;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.*;

public class ShapesFactory {
		
	public static Shape CreateShape(String shapeName){
		
		Shape shape = null;
		
		if(shapeName.equalsIgnoreCase("circle"))
			shape = new Circle();
		if(shapeName.equalsIgnoreCase("ellipse"))
			shape = new Ellipse();
		if(shapeName.equalsIgnoreCase("rectangle"))
			shape = new Rectangle();
		if(shapeName.equalsIgnoreCase("line"))
			shape = new Line();
		if(shapeName.equalsIgnoreCase("square"))
			shape = new Square();
		if(shapeName.equalsIgnoreCase("triangle"))
			shape = new Triangle();
		
		return shape;
	}

}
