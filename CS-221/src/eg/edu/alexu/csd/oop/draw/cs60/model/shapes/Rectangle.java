package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.Graphics;

public class Rectangle extends MainShape {

	@Override
	public void draw(Graphics canvas) {
		canvas.setColor(getColor());
		canvas.drawLine(getPosition().x, getPosition().y, getPosition().x+400, getPosition().y+400);
	}

}
