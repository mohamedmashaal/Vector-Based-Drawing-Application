package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.MainShape;

public class Circle extends MainShape {

	public Circle() {
		super();
	}

	public Circle(Point position, double radius) {
		super();
		setPosition(position);
		getProperties().put("x", getPosition().getX());
		getProperties().put("y", getPosition().getY());
		getProperties().put("radius", new Double(radius));
		getProperties().put("bond_1_x", getBonds()[0].getX());
		getProperties().put("bond_1_y", getBonds()[0].getY());
		getProperties().put("bond_2_x", getBonds()[1].getX());
		getProperties().put("bond_2_y", getBonds()[1].getY());
		getProperties().put("bond_3_x", getBonds()[2].getX());
		getProperties().put("bond_3_y", getBonds()[2].getY());
		getProperties().put("bond_4_x", getBonds()[3].getX());
		getProperties().put("bond_4_y", getBonds()[3].getY());
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D) canvas;
		g.setColor(new Color(getProperties().get("fill_color").intValue()));
		g.fillOval(getProperties().get("x").intValue() - getProperties().get("radius").intValue(),
				getProperties().get("y").intValue() - getProperties().get("radius").intValue(),
				getProperties().get("radius").intValue() * 2, getProperties().get("radius").intValue() * 2);
		g.setStroke(new BasicStroke(getProperties().get("stroke").floatValue()));
		g.setColor(new Color(getProperties().get("color").intValue()));
		g.drawOval(getProperties().get("x").intValue() - getProperties().get("radius").intValue(),
				getProperties().get("y").intValue() - getProperties().get("radius").intValue(),
				getProperties().get("radius").intValue() * 2, getProperties().get("radius").intValue() * 2);
		/*
		 * if (getProperties().get("selected").intValue() == 1) {
		 * drawBonds(canvas); }
		 */
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clone = new Circle();
		Map<String, Double> clone_prop = new HashMap<>();
		clone_prop.putAll(this.getProperties());
		clone.setProperties(clone_prop);
		return clone;
	}

	public Point[] getBonds() {
		Point p1 = new Point(getProperties().get("x").intValue() - getProperties().get("radius").intValue(),
				getProperties().get("y").intValue() - getProperties().get("radius").intValue());
		Point p2 = new Point(getProperties().get("x").intValue() + getProperties().get("radius").intValue(),
				getProperties().get("y").intValue());
		Point p3 = new Point(getProperties().get("x").intValue(),
				getProperties().get("y").intValue() + getProperties().get("radius").intValue());
		Point p4 = new Point(getProperties().get("x").intValue() + getProperties().get("radius").intValue(),
				getProperties().get("y").intValue() + getProperties().get("radius").intValue());
		return new Point[] { p1, p2, p3, p4 };
	}
}
