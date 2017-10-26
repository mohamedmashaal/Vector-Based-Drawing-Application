package eg.edu.alexu.csd.oop.draw.cs60.model;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Rectangle;

public class Testing {
	public static void main(String [] args) {
		DrawingEngine engine = new DrawEngineImp();
		Circle circle = new Circle();
		engine.addShape(circle);
		engine.addShape(new Rectangle());
		Shape [] shapes =engine.getShapes();
		for(Shape x : shapes) {
			System.out.println(x.getClass().getName());
		}System.out.println();
		engine.removeShape(circle);
		shapes =engine.getShapes();
		for(Shape x : shapes) {
			System.out.println(x.getClass().getName());
		}System.out.println();
		engine.undo();
		shapes =engine.getShapes();
		for(Shape x : shapes) {
			System.out.println(x.getClass().getName());
		}
	}
}
