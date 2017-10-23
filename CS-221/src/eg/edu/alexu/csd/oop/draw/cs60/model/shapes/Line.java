package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;
import java.util.Random;

import eg.edu.alexu.csd.oop.draw.cs60.MainShape;

public class Line extends MainShape {
	
	public void setPosition2(Point position) {
		properties.put("x2", position.getX());
		properties.put("y2", position.getY());
	}

	public Point getPosition2() {
		Point point = new Point(properties.get("x2").intValue(),properties.get("y2").intValue());
		return point;
	}
		
	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(getColor());
		canvas.drawLine(getPosition().x, getPosition().y, getPosition2().x, getPosition2().y);
	}
}
