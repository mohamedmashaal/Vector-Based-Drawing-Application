package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import eg.edu.alexu.csd.oop.draw.Shape;

public class Test implements Shape {
	private double radius;
	private Map<String, Double> properties;
	private Point position;
	private float storke = 2;
	private Color default_color = Color.BLUE;
	private Color default_fill_color = Color.RED;

	// private boolean selected = false ;
	public Test() {
		super();
		properties = new HashMap<String, Double>();
		properties.put("selected", 0.0);
		properties.put("default_x", 0.0);
		properties.put("default_y", 0.0);
		properties.put("color", default_color.getRGB() * 1.0);
		properties.put("fill_color", default_fill_color.getRGB() * 1.0);

		setPosition(position);
		this.radius = radius;
		getProperties().put("x", getPosition().getX());
		getProperties().put("y", getPosition().getY());
		getProperties().put("radius", new Double(radius));
		getProperties().put("color", Color.BLACK.getRGB() * 1.0);
		getProperties().put("fill_color", Color.RED.getRGB() * 1.0);
		getProperties().put("bond_1_x", getBonds()[0].getX());
		getProperties().put("bond_1_y", getBonds()[0].getY());
		getProperties().put("bond_2_x", getBonds()[1].getX());
		getProperties().put("bond_2_y", getBonds()[1].getY());
	}
	
	public Test(Point position , double radius) {
		super();
		setPosition(position);
		this.radius = radius ;
		getProperties().put("x", getPosition().getX());
		getProperties().put("y", getPosition().getY());
		getProperties().put("radius", new Double(radius));
		getProperties().put("color", Color.BLACK.getRGB()*1.0);
		getProperties().put("fill_color", Color.RED.getRGB()*1.0);
		getProperties().put("bond_1_x", getBonds()[0].getX());
		getProperties().put("bond_1_y", getBonds()[0].getY());
		getProperties().put("bond_2_x", getBonds()[1].getX());
		getProperties().put("bond_2_y", getBonds()[1].getY());
	}
	public Test(Point position , int radius, Color color, Color fillColor) {
		super();
		setPosition(position);
		this.radius = radius ;
		getProperties().put("x", getPosition().getX());
		getProperties().put("y", getPosition().getY());
		getProperties().put("radius", new Double(radius));
		getProperties().put("color", color.getRGB()*1.0);
		getProperties().put("fill_color", fillColor.getRGB()*1.0);
		getProperties().put("bond_1_x", getBonds()[0].getX());
		getProperties().put("bond_1_y", getBonds()[0].getY());
		getProperties().put("bond_2_x", getBonds()[1].getX());
		getProperties().put("bond_2_y", getBonds()[1].getY());
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

	public float getStorke() {
		return storke;
	}

	public void setStorke(float storke) {
		this.storke = storke;
	}

	/*
	 * public boolean isSelected() { return selected; }
	 */

	/*
	 * public void setSelected(boolean selected) { this.selected = selected ; }
	 */

	@Override
	public void draw(Graphics canvas) {
		Graphics2D g = (Graphics2D) canvas;
		g.setColor(new Color(getProperties().get("fill_color").intValue()));
		g.fillOval(getProperties().get("x").intValue() - getProperties().get("radius").intValue(),
				getProperties().get("y").intValue() - getProperties().get("radius").intValue(),
				getProperties().get("radius").intValue() * 2, getProperties().get("radius").intValue() * 2);
		g.setStroke(new BasicStroke(getStorke()));
		g.setColor(new Color(getProperties().get("color").intValue()));
		g.drawOval(getProperties().get("x").intValue() - getProperties().get("radius").intValue(),
				getProperties().get("y").intValue() - getProperties().get("radius").intValue(),
				getProperties().get("radius").intValue() * 2, getProperties().get("radius").intValue() * 2);
		if (getProperties().get("selected").intValue() == 1) {
			drawBonds(canvas);
		}
	};

	public Object clone() throws CloneNotSupportedException {
		Shape clone = new Circle(new Point(getProperties().get("x").intValue(), getProperties().get("x").intValue()),
				getProperties().get("radius").intValue(), new Color(getProperties().get("color").intValue()),
				new Color(getProperties().get("fill_color").intValue())); // needs
																			// some
																			// adjustment
		Map<String, Double> clone_prop = new HashMap<>();
		clone_prop.putAll(this.getProperties());
		clone.setProperties(clone_prop);
		return clone;
	}

	public void drawBonds(Graphics canvas) {
		int margin = 5;
		float dash1[] = { 10.0f };
		BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		Graphics2D g = (Graphics2D) canvas;
		Point[] bonds = getBonds();
		g.setStroke(dashed);
		g.drawRect(bonds[0].x - margin, bonds[0].y - margin, bonds[1].x - bonds[0].x + 2 * margin,
				bonds[1].y - bonds[0].y + 2 * margin);
	}

	public Point[] getBonds() {
		Point p1 = new Point(getProperties().get("x").intValue() - getProperties().get("radius").intValue(),
				getProperties().get("y").intValue() - getProperties().get("radius").intValue());
		Point p2 = new Point(getProperties().get("x").intValue() + getProperties().get("radius").intValue(),
				getProperties().get("y").intValue() + getProperties().get("radius").intValue());
		return new Point[] { p1, p2 };
	}

}