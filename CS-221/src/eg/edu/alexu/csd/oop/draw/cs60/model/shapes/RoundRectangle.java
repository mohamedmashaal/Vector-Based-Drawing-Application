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

    protected Point p;
    protected Map<String, Double> prop;
    protected Color c;
    protected Color fc;

    public RoundRectangle() {
        prop = new HashMap<>();
        prop.put("Width", 0.0);
        prop.put("Length", 0.0);
        prop.put("ArcWidth", 0.0);
        prop.put("ArcLength", 0.0);
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
        prop = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return prop;
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
                (int) prop.get("Width").intValue(),
                (int) prop.get("Length").intValue(), 
                (int) prop.get("ArcWidth").intValue(), 
                (int) prop.get("ArcLength").intValue());

        ((Graphics2D) canvas).setStroke(new BasicStroke(2));
        ((Graphics2D) canvas).setColor(getColor());
        ((Graphics2D) canvas).drawRoundRect((int) p.getX(),
                (int) p.getY(),
                (int) prop.get("Width").intValue(),
                (int) prop.get("Length").intValue(), 
                (int) prop.get("ArcWidth").intValue(), 
                (int) prop.get("ArcLength").intValue());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Shape r = new RoundRectangle();
        r.setColor(c);
        r.setFillColor(fc);
        r.setPosition(p);
        Map newprop = new HashMap<>();
        for (Map.Entry s: prop.entrySet())
            newprop.put(s.getKey(), s.getValue());
        r.setProperties(newprop);
        return r;
    }
}