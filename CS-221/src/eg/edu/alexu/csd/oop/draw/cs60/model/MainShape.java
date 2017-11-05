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
	private Map<String, Double> properties ;
	private Point position ;
	private Color color ;
	private Color fill_color ;
	private boolean selected = false ;
	public MainShape() {
		properties = new HashMap<String, Double>();
		properties.put("default_x", 0.0);
		properties.put("default_y", 0.0);
		properties.put("default_color", Color.BLACK.getRGB()*1.0);
		properties.put("default_fill_color", Color.RED.getRGB()*1.0);
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
		properties.put("color", color.getRGB()*1.0);
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
		properties.put("fill_color", color.getRGB()*1.0);
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		//return new Color(properties.get("fill-color").intValue());
		return fill_color ;
	}

	
	public boolean isSelected() {
		return selected;
	}
	
	
	public void setSelected(boolean selected) {
		this.selected = selected ;
	}
	
	@Override
	public abstract void draw(Graphics canvas);
	
	public abstract Object clone() throws CloneNotSupportedException;
	
	public void drawBonds(Graphics canvas) {
		int margin = 5 ;
		float dash1[] = { 10.0f };
		  BasicStroke dashed = new BasicStroke(1.0f,
		      BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash1, 0.0f);
		Graphics2D g = (Graphics2D)canvas;
		Point[] bonds = getBonds();
		g.setStroke(dashed);
		g.drawRect(bonds[0].x-margin, bonds[0].y-margin, bonds[1].x - bonds[0].x + 2 *margin, bonds[1].y - bonds[0].y + 2 * margin );
	}
	public abstract Point [] getBonds();
}
