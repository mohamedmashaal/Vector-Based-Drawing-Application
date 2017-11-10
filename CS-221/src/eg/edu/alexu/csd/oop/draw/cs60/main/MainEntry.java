package eg.edu.alexu.csd.oop.draw.cs60.main;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs60.controller.Controller;
import eg.edu.alexu.csd.oop.draw.cs60.model.DrawEngineImp;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Line;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Triangle;

public class MainEntry {
	public static void main(String [] args) {
		Controller controller = new Controller(DrawEngineImp.getUniqueInstance());

		/*DrawingEngine de = new DrawEngineImp();
		de.addShape(new Circle());
		de.addShape(new Rectangle());
		de.addShape(new Triangle());
		de.addShape(new Line());
		de.addShape(new Rectangle());

		System.out.println(de.getShapes().length);

		de.save("test.XmL");

		DrawingEngine de2 = new DrawEngineImp();
		de2.load("test.XmL");
		System.out.println(de.getShapes().length);*/

	}
}
