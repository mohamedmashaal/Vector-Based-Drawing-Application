package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.MainShape;

public class Rectangle extends MainShape {
	private float STROKE = 2;
	private double width ;
	private double height ;
	public Rectangle() {
		super();
	}
	
	public Rectangle(Point position , double width , double height) {
		super();
		setPosition(position);
		this.width = width ;
		this.height = height ;
		getProperties().put("x", position.getX());
		getProperties().put("y", position.getY());
		getProperties().put("width", new Double(width));
		getProperties().put("height", new Double(height));
		getProperties().put("bond_1_x", getBonds()[0].getX());
		getProperties().put("bond_1_y", getBonds()[0].getY());
		getProperties().put("bond_2_x", getBonds()[1].getX());
		getProperties().put("bond_2_y", getBonds()[1].getY());
	}



	
	@Override
	public void draw(Graphics canvas) {
		Graphics2D g = (Graphics2D)canvas ;
		g.setColor(new Color(getProperties().get("fill_color").intValue()));
		g.fillRect(getProperties().get("x").intValue(), getProperties().get("y").intValue(), 
				   getProperties().get("width").intValue(), getProperties().get("height").intValue());
		g.setStroke( new BasicStroke(STROKE));
		g.setColor(new Color(getProperties().get("color").intValue()));
		g.drawRect(getProperties().get("x").intValue(), getProperties().get("y").intValue(), 
				   getProperties().get("width").intValue(), getProperties().get("height").intValue());
		if(getProperties().get("selected").intValue() == 1) {
			drawBonds(canvas);
		}
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

	@Override
	public Point[] getBonds() {
		Point p1 = new Point(getProperties().get("x").intValue(),getProperties().get("y").intValue());
		Point p2 = new Point(p1.x+getProperties().get("width").intValue(),p1.y+getProperties().get("height").intValue());
		return new Point[] {p1,p2};
	}
	

}
