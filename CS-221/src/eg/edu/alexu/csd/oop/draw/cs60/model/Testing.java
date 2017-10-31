package eg.edu.alexu.csd.oop.draw.cs60.model;



import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.controller.Controller;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Rectangle;

public class Testing {
	public static void main(String [] args) {
		DrawingEngine engine = DrawEngineImp.getUniqueInstance();
		Controller cont = new Controller();
		/*Shape Z = new Circle();
		List<Class<? extends Shape>> list = engine.getSupportedShapes();
		for(Class x :list) {
			System.out.println(x.getName());
		}*/
		/*Circle circle = new Circle();
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
		}*/
	}
}
