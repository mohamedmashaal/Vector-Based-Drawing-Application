package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public abstract class MainShape implements Shape {
	private Map<String, Double> properties ;
	private Point position ;
	private Color color ;
	private Color fill_color ;
	
	public MainShape() {
		properties = new HashMap<String, Double>();	
	}
	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		/*properties.put("x1", position.getX());
		properties.put("y1", position.getY());*/
		this.position = position ;
	}

	@Override
	public Point getPosition() {
		//Point point = new Point(properties.get("x1").intValue(),properties.get("y1").intValue());
		//return point;
		return position ;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub
		this.properties = properties ;
	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return properties;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		//properties.put("color", new Double(color.getRGB()));
		this.color = color ;
	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		//return new Color(properties.get("color").intValue());
		return color ;
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub
		//properties.put("fill-color", new Double(color.getRGB()));
		this.fill_color = color ;
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		//return new Color(properties.get("fill-color").intValue());
		return fill_color ;
	}

	@Override
	public abstract void draw(Graphics canvas);
	
	public abstract Object clone() throws CloneNotSupportedException;
}
