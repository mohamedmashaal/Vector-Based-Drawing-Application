package eg.edu.alexu.csd.oop.draw.cs60.model;



import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.controller.Controller;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Triangle;

public class Testing {
	public static void main(String [] args) {
		DrawEngineImp engine = DrawEngineImp.getUniqueInstance();
		//List<Class<? extends Shape>> list = engine.getSupportedShapes();
		Shape Z = new Circle();
		/*List<Class<? extends Shape>> list = engine.getSupportedShapes();
		for(Class x :list) {
			System.out.println(x.getName());
		}*/
		Circle circle = new Circle();
		engine.save("file");
		engine.clear();
		engine.load("file");
		Shape [] shapes = engine.getShapes();
		for(Shape x : shapes) {
			System.out.println(x);
		}
	}
}
