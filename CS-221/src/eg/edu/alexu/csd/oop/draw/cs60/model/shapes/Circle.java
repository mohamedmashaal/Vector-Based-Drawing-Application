package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.MainShape;

public class Circle extends MainShape{
	private static final float STROKE = 2;
	private int radius ;
	
	public Circle() {
		
	}
	public Circle(Point position , int radius) {
		super();
		setPosition(position);
		this.radius = radius ;
		getProperties().put("x", getPosition().getX());
		getProperties().put("y", getPosition().getY());
		getProperties().put("radius", new Double(radius));
	}
	
	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D)canvas ;
		g.setColor(getFillColor());
		g.fillOval(getPosition().x, getPosition().y, radius*2, radius*2);
		g.setStroke( new BasicStroke(STROKE));
		g.setColor(getColor());
		g.drawOval(getPosition().x, getPosition().y, radius*2, radius*2);

	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clone = new Circle(getPosition() , this.radius); // needs some adjustment
		clone.setColor(this.getColor());
		clone.setFillColor(this.getFillColor());
		clone.setPosition(this.getPosition());
		Map<String,Double> clone_prop = new HashMap<>();
		clone_prop.putAll(this.getProperties());
		clone.setProperties(clone_prop);
		return clone;
	}
}
