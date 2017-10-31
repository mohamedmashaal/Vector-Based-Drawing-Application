package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.Point;

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
	
	public static Shape CreateShape(String shapeName, Point p1, Point p2){
		
		Shape shape = null;
		
		if(shapeName.equalsIgnoreCase("circle"))
			shape = new Circle(p1, Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)-(p1.y-p2.y)*(p1.y-p2.y)));
		if(shapeName.equalsIgnoreCase("ellipse"))
			shape = new Ellipse(p1, Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)-(p1.y-p2.y)*(p1.y-p2.y)),
								Math.sqrt((p1.x-p2.x)*(p1.x-p2.x)-(p1.y-p2.y)*(p1.y-p2.y)));
		if(shapeName.equalsIgnoreCase("rectangle")){
			if(p1.x > p2.x || p1.y > p2.y) { // TODO needs more handling
				Point temp = new Point(p1);
				p1 = new Point(p2);
				p2 = temp;
			}
			shape = new Rectangle(p1,Math.abs(p2.x-p1.x),Math.abs(p2.y-p1.y));
		}
		if(shapeName.equalsIgnoreCase("line"))
			shape = new Line(p1,p2);
		if(shapeName.equalsIgnoreCase("square")){
			if(p1.x > p2.x || p1.y > p2.y) { // TODO needs more handling
				Point temp = new Point(p1);
				p1 = new Point(p2);
				p2 = temp;
			}
			shape = new Square(p1,Math.abs(p2.x-p1.x));
		}
		if(shapeName.equalsIgnoreCase("triangle")){
			Point p3 = new Point(p2.x + 150, p2.y);
			shape = new Triangle(p1, p2, p3);
		}
		
		return shape;
	}

}
