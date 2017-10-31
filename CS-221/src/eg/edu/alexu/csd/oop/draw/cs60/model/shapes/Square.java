package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.MainShape;

public class Square extends MainShape {
	private float STROKE = 2;
	private double width ;
	public Square() {
		super();
	}
	
	public Square(Point position , double width) {
		super();
		setPosition(position);
		this.width = width ;
		getProperties().put("x", position.getX());
		getProperties().put("y", position.getY());
		getProperties().put("width", new Double(width));
	}


	@Override
	public void draw(Graphics canvas) {
		Graphics2D g = (Graphics2D)canvas ;
		g.setColor(getFillColor());
		g.fillRect(getPosition().x, getPosition().y, (int)width, (int)width);
		g.setStroke( new BasicStroke(STROKE));
		g.setColor(getColor());
		g.drawRect(getPosition().x, getPosition().y, (int)width, (int)width);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clone = new Rectangle(getPosition() , this.width , this.width); // needs some adjustment
		clone.setColor(this.getColor());
		clone.setFillColor(this.getFillColor());
		clone.setPosition(this.getPosition());
		Map<String,Double> clone_prop = new HashMap<>();
		clone_prop.putAll(this.getProperties());
		clone.setProperties(clone_prop);
		return clone;
	}
}
