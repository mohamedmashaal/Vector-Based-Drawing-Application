package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;
import java.util.Random;

public class Line extends MainShape {
	
	public void setPosition2(Point position) {
		
	}
		
	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(getColor());
		canvas.drawLine(getPosition().x, getPosition().y, getPosition().x+400, getPosition().y+400);
	}
}
