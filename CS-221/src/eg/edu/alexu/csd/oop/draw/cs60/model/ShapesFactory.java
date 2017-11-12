package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.*;

public class ShapesFactory {

	public static Shape CreateShape(String shapeName) {

		Shape shape = null;

		if (shapeName.equalsIgnoreCase("circle"))
			shape = new Circle();
		if (shapeName.equalsIgnoreCase("ellipse"))
			shape = new Ellipse();
		if (shapeName.equalsIgnoreCase("rectangle"))
			shape = new Rectangle();
		if (shapeName.equalsIgnoreCase("line"))
			shape = new Line();
		if (shapeName.equalsIgnoreCase("square"))
			shape = new Square();
		if (shapeName.equalsIgnoreCase("triangle"))
			shape = new Triangle();

		return shape;
	}

	public static Shape CreateShape(String shapeName, Point p1, Point p2) {

		Shape shape = null;

		if (shapeName.equalsIgnoreCase("circle"))
			shape = new Circle(p1, Math.sqrt(Math.pow(Math.abs(p1.x - p2.x), 2) + Math.pow(Math.abs(p1.y - p2.y), 2)));
		else if (shapeName.equalsIgnoreCase("ellipse")) {
			int x = Math.min(p1.x, p2.x);
			int y = Math.min(p1.y, p2.y);
			shape = new Ellipse(new Point(x, y), Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
		} else if (shapeName.equalsIgnoreCase("rectangle")) {
			int x = Math.min(p1.x, p2.x);
			int y = Math.min(p1.y, p2.y);
			shape = new Rectangle(new Point(x, y), Math.max(p1.x, p2.x) - x, Math.max(p1.y, p2.y) - y);
		} else if (shapeName.equalsIgnoreCase("line"))
			shape = new Line(p1, p2);
		else if (shapeName.equalsIgnoreCase("square")) {
			int width = Math.max(Math.abs(p1.x - p2.x), Math.abs(p1.y - p2.y));
			int x = p1.x > p2.x ? p1.x - width : p1.x;
			int y = p1.y > p2.y ? p1.y - width : p1.y;
			shape = new Square(new Point(x, y), width);
		} else if (shapeName.equalsIgnoreCase("triangle")) {
			int width = Math.abs(p1.x - p2.x);
			int x = p1.x < p2.x ? p1.x + width / 2 : p1.x - width / 2;
			int x2 = p1.x < p2.x ? p2.x - width : p2.x + width;
			shape = new Triangle(new Point(x, p1.y), p2, new Point(x2, p2.y));
		} else {
			DrawEngineImp engine = DrawEngineImp.getUniqueInstance();
			List<Class<? extends Shape>> supported = engine.getSupportedShapes();
			for (Class<? extends Shape> x : supported) {
				if (x.getSimpleName().equalsIgnoreCase(shapeName)) {
					try {
						// Constructor<? extends Shape> ctor =
						// x.getConstructor();
						// Shape doRun = ctor.newInstance();
						Constructor<? extends Shape> cons = x.getConstructor(Point.class, double.class);
						return cons.newInstance(p1,
								Math.sqrt(Math.pow(Math.abs(p1.x - p2.x), 2) + Math.pow(Math.abs(p1.y - p2.y), 2)));
					} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
							| IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
					}

				}
			}
		}
		return shape;
	}

}
