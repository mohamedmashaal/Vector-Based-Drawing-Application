package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.MainShape;

public class Rectangle extends MainShape {
	private float STROKE = 2;
	private int width ;
	private int height ;
	public Rectangle() {
		super();
	}
	
	public Rectangle(Point position , int width , int height) {
		super();
		setPosition(position);
		this.width = width ;
		this.height = height ;
		getProperties().put("x", position.getX());
		getProperties().put("y", position.getY());
		getProperties().put("width", new Double(width));
		getProperties().put("height", new Double(height));

	}


	/*@Override
	public void draw(Graphics canvas) {
		Graphics2D g = (Graphics2D)canvas ;
		g.setColor(getFillColor());
		g.fillRect(getPosition().x, getPosition().y, width, height);
		g.setStroke( new BasicStroke(STROKE));
		g.drawRect(getPosition().x, getPosition().y, width, height);
	}*/
	
	@Override
	public void draw(Graphics canvas) {
		System.out.println(canvas);
		Graphics2D g = (Graphics2D)canvas ;
		g.setColor(getFillColor());
		g.fillRect(getProperties().get("x").intValue(), getProperties().get("y").intValue(), 
				   getProperties().get("width").intValue(), getProperties().get("height").intValue());
		g.setStroke( new BasicStroke(STROKE));
		g.drawRect(getProperties().get("x").intValue(), getProperties().get("y").intValue(), 
				   getProperties().get("width").intValue(), getProperties().get("height").intValue());
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clone = new Rectangle(getPosition() , this.width , this.height); // needs some adjustment
		clone.setColor(this.getColor());
		clone.setFillColor(this.getFillColor());
		clone.setPosition(this.getPosition());
		Map<String,Double> clone_prop = new HashMap<>();
		clone_prop.putAll(this.getProperties());
		clone.setProperties(clone_prop);
		return clone;
	}

	

}
