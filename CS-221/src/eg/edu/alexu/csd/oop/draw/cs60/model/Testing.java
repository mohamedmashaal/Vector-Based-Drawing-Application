package eg.edu.alexu.csd.oop.draw.cs60.model;



import java.awt.Point;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Circle;

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
		circle.setPosition(new Point(2,2));
		engine.addShape(circle);
		engine.save("file");
		engine.clear();
		engine.load("file");
		Shape [] shapes = engine.getShapes();
		for(Shape x : shapes) {
			System.out.println(x);
			System.out.println(x.getPosition());
		}
	}
}
