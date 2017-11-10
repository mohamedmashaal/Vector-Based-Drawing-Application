package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.MainShape;

public class Triangle extends MainShape{
	private Point p2 ;
	private Point p3 ;
	private int[] xPoints = new int [3];
	private int[] yPoints = new int [3];
	
	public Triangle() {
		super();
	}
	
	public Triangle(Point p1 , Point p2 , Point p3 ) {
		super();
		setPosition(p1);
		this.p2 = p2 ;
		this.p3 = p3 ;
		xPoints[0] = getPosition().x;
		xPoints[1] = p2.x;
		xPoints[2] = p3.x;
		yPoints[0] = getPosition().y;
		yPoints[1] = p2.y;
		yPoints[2] = p3.y;
		this.getProperties().put("x1", getPosition().getX());
		this.getProperties().put("y1", getPosition().getY());
		this.getProperties().put("x2", p2.getX());
		this.getProperties().put("y2", p2.getY());
		this.getProperties().put("x3", p3.getX());
		this.getProperties().put("y3", p3.getY());
		getProperties().put("bond_1_x", getBonds()[0].getX());
		getProperties().put("bond_1_y", getBonds()[0].getY());
		getProperties().put("bond_2_x", getBonds()[1].getX());
		getProperties().put("bond_2_y", getBonds()[1].getY());
	}
	
	
	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub
		Graphics2D g = (Graphics2D)canvas ;
		g.setColor(new Color(getProperties().get("fill_color").intValue()));
		g.fillPolygon(new int [] {getProperties().get("x1").intValue(),getProperties().get("x2").intValue(),getProperties().get("x3").intValue()},
				new int [] {getProperties().get("y1").intValue(),getProperties().get("y2").intValue(),getProperties().get("y3").intValue()},
				3);
		g.setStroke( new BasicStroke(getStorke()));
		g.setColor(new Color(getProperties().get("color").intValue()));
		g.drawPolygon(new int [] {getProperties().get("x1").intValue(),getProperties().get("x2").intValue(),getProperties().get("x3").intValue()},
				new int [] {getProperties().get("y1").intValue(),getProperties().get("y2").intValue(),getProperties().get("y3").intValue()},
				3);
		if(getProperties().get("selected").intValue() == 1) {
			drawBonds(canvas);
		}
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape clone = new Triangle(getPosition() , this.p2 , this.p3); // needs some adjustment
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
		int x1 =getProperties().get("x1").intValue();
		int x2 =getProperties().get("x2").intValue();
		int x3 =getProperties().get("x3").intValue();
		int y1 =getProperties().get("y1").intValue();
		int y2 =getProperties().get("y2").intValue();
		int y3 =getProperties().get("y3").intValue();
		Point p1 = new Point(Math.min(Math.min(x1, x2), x3),Math.min(Math.min(y1, y2), y3));
		Point p2 = new Point(Math.max(Math.max(x1, x2), x3),Math.max(Math.max(y1, y2), y3));
		return new Point[] {p1,p2};
	}

}
