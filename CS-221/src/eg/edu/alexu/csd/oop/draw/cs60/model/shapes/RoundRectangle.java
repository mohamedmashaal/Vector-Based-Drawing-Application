package eg.edu.alexu.csd.oop.draw.cs60.model.shapes;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class RoundRectangle implements Shape {

    private Point p;
    private Map<String, Double> properties;
    private float default_stroke = 0;
	private Color default_color = Color.BLUE;
	private Color default_fill_color = Color.RED;
    private Color c;
    private Color fc;

    public RoundRectangle() {
    	properties = new HashMap<>();
    	properties.put("Width", 0.0);
    	properties.put("Length", 0.0);
    	properties.put("ArcWidth", 0.0);
    	properties.put("ArcLength", 0.0);
    }
    public RoundRectangle(Point p1 , Point p2) {
    	properties = new HashMap<String, Double>();
		properties.put("stroke", new Double(default_stroke));
		properties.put("selected", 0.0);
		properties.put("default_x", 0.0);
		properties.put("default_y", 0.0);
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
        p = position;
    }

    @Override
    public Point getPosition() {
        return p;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
    	properties = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }

    @Override
    public void setColor(Color color) {
        c = color;
    }

    @Override
    public Color getColor() {
        return c;
    }

    @Override
    public void setFillColor(Color color) {
        fc = color;
    }

    @Override
    public Color getFillColor() {
        return fc;
    }

    @Override
    public void draw(Graphics canvas) {
        ((Graphics2D) canvas).setColor(getFillColor());
        ((Graphics2D) canvas).fillRoundRect((int) p.getX(),
                (int) p.getY(),
                (int) properties.get("Width").intValue(),
                (int) properties.get("Length").intValue(), 
                (int) properties.get("ArcWidth").intValue(), 
                (int) properties.get("ArcLength").intValue());

        ((Graphics2D) canvas).setStroke(new BasicStroke(2));
        ((Graphics2D) canvas).setColor(getColor());
        ((Graphics2D) canvas).drawRoundRect((int) p.getX(),
                (int) p.getY(),
                (int) properties.get("Width").intValue(),
                (int) properties.get("Length").intValue(), 
                (int) properties.get("ArcWidth").intValue(), 
                (int) properties.get("ArcLength").intValue());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Shape r = new RoundRectangle();
        r.setColor(c);
        r.setFillColor(fc);
        r.setPosition(p);
        Map newprop = new HashMap<>();
        for (Map.Entry s: properties.entrySet())
            newprop.put(s.getKey(), s.getValue());
        r.setProperties(newprop);
        return r;
    }
}