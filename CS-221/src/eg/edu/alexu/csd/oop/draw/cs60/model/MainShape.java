package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public abstract class MainShape implements Shape {
	private Map<String, Double> properties;
	private Point position;
	private float default_stroke = 0;
	private Color default_color = Color.BLUE;
	private Color default_fill_color = Color.RED;

	public MainShape() {
		properties = new HashMap<String, Double>();
		properties.put("stroke", new Double(default_stroke));
		properties.put("selected", 0.0);
		properties.put("color", default_color.getRGB() * 1.0);
		properties.put("fill_color", default_fill_color.getRGB() * 1.0);
		properties.put("bond_1_x", -1.0);
		properties.put("bond_1_y", -1.0);
		properties.put("bond_2_x", -1.0);
		properties.put("bond_2_y", -1.0);
		properties.put("bond_3_x", -1.0);
		properties.put("bond_3_y", -1.0);
		properties.put("bond_4_x", -1.0);
		properties.put("bond_4_y", -1.0);
	}

	@Override
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	public Point getPosition() {
		return position;
	}

	@Override
	public void setProperties(Map<String, Double> properties) {
		this.properties = properties;
	}

	@Override
	public Map<String, Double> getProperties() {
		return properties;
	}

	@Override
	public void setColor(Color color) {
		properties.put("color", color.getRGB() * 1.0);
	}

	@Override
	public Color getColor() {
		return new Color(properties.get("color").intValue());
	}

	@Override
	public void setFillColor(Color color) {
		properties.put("fill_color", color.getRGB() * 1.0);
	}

	@Override
	public Color getFillColor() {
		return new Color(getProperties().get("fill_color").intValue());
	}

	@Override
	public abstract void draw(Graphics canvas);

	public abstract Object clone() throws CloneNotSupportedException;

	public void drawBonds(Graphics canvas) {
		int margin = 5;
		float dash1[] = { 10.0f };
		BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		Graphics2D g = (Graphics2D) canvas;
		Point[] bonds = getBonds();
		g.setStroke(dashed);
		g.drawRect(bonds[0].x - margin, bonds[0].y - margin, bonds[3].x - bonds[0].x + 2 * margin,
				bonds[3].y - bonds[0].y + 2 * margin);
	}

	public abstract Point[] getBonds();
}
