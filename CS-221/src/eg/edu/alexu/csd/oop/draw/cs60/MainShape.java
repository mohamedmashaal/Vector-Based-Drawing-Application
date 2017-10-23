package eg.edu.alexu.csd.oop.draw.cs60;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public abstract class MainShape implements Shape {
	
	@Override
	public void setPosition(Point position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<String, Double> getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFillColor(Color color) {
		// TODO Auto-generated method stub

	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(Graphics canvas) {
		// TODO Auto-generated method stub

	}
	
	public Object clone() {
		return null;
		
	}
}
