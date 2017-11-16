package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.MainShape;

public class Rectangle extends MainShape {

	public Rectangle() {
		super();
	}

	public Rectangle(Point position, double width, double height) {
		super();
		setPosition(position);
		getProperties().put("x", position.getX());
		getProperties().put("y", position.getY());
		getProperties().put("width", new Double(width));
		getProperties().put("height", new Double(height));
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
		Graphics2D g = (Graphics2D) canvas;
		g.setColor(new Color(getProperties().get("fill_color").intValue()));
		g.fillRect(getProperties().get("x").intValue(), getProperties().get("y").intValue(),
				getProperties().get("width").intValue(), getProperties().get("height").intValue());
		g.setStroke(new BasicStroke(getProperties().get("stroke").floatValue()));
		g.setColor(new Color(getProperties().get("color").intValue()));
		g.drawRect(getProperties().get("x").intValue(), getProperties().get("y").intValue(),
				getProperties().get("width").intValue(), getProperties().get("height").intValue());
		/*
		 * if (getProperties().get("selected").intValue() == 1) {
		 * drawBonds(canvas); }
		 */
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clone = new Rectangle();
		Map<String, Double> clone_prop = new HashMap<>();
		clone_prop.putAll(this.getProperties());
		clone.setProperties(clone_prop);
		return clone;
	}

	@Override
	public Point[] getBonds() {
		Point p1 = new Point(getProperties().get("x").intValue(), getProperties().get("y").intValue());
		Point p2 = new Point(p1.x + getProperties().get("width").intValue(), p1.y);
		Point p3 = new Point(p1.x, p1.y + getProperties().get("height").intValue());
		Point p4 = new Point(p1.x + getProperties().get("width").intValue(),
				p1.y + getProperties().get("height").intValue());
		return new Point[] { p1, p2, p3, p4 };
	}

}
