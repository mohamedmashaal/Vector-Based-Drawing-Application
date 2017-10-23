package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.Graphics;
import java.awt.Point;

import eg.edu.alexu.csd.oop.draw.cs60.MainShape;

public class Rectangle extends MainShape {
	
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
		canvas.drawRect(getPosition().x, getPosition().y, Math.abs(getPosition().x-getPosition2().x), Math.abs(getPosition().x-getPosition2().x));
	}

}
