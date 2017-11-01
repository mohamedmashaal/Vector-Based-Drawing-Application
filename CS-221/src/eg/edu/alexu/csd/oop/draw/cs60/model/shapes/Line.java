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

public class Line extends MainShape {
	private float STROKE = 2;
	private Point end ;
	public Line() {
		super();
	}
	
	public Line(Point p1 , Point p2) {
		super();
		setPosition(p1);
		end = new Point (p2);
		this.getProperties().put("x1", p1.getX());
		this.getProperties().put("y1", p1.getY());
		this.getProperties().put("x2", p2.getX());
		this.getProperties().put("y2", p2.getY());
	}
	
	
	@Override
	public void draw(Graphics canvas) {
		Graphics2D g = (Graphics2D)canvas ;
		g.setColor(new Color(getProperties().get("color").intValue()));
		g.setStroke( new BasicStroke(STROKE));
		g.setColor(new Color(getProperties().get("fill_color").intValue()));
		g.drawLine(getPosition().x, getPosition().y, end.x, end.y);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clone = new Line(getPosition() , this.end); // needs some adjustment
		clone.setColor(this.getColor());
		clone.setFillColor(this.getFillColor());
		clone.setPosition(this.getPosition());
		Map<String,Double> clone_prop = new HashMap<>();
		clone_prop.putAll(this.getProperties());
		clone.setProperties(clone_prop);
		return clone;
	}
}
