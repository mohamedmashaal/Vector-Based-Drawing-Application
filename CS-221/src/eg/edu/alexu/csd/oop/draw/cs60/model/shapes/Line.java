package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.Graphics;
import java.util.Map;

public class Line extends MainShape {
	
	
	@Override
	public void draw(Graphics canvas) {
		canvas.drawLine(getPosition().x, getPosition().y, getPosition().x+5, getPosition().y+5);
	}
}
