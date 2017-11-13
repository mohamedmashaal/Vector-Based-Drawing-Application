package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.Point;

import javax.management.modelmbean.ModelMBean;

import eg.edu.alexu.csd.oop.draw.Shape;

public class ResizeHandler {
	private DrawEngineImp model;
	public ResizeHandler(DrawEngineImp model) {
		this.model = model ;
	}
	public void resize(Shape x, Point p1, Point p2, int resize_corner) {
		int xToUpdate = p2.x - p1.x;
		int ytoUpdate = p2.y - p1.y;
		if(x.getClass().getSimpleName().equalsIgnoreCase("circle")) {
			Shape newShape = null ;
			if(resize_corner == 1)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue()+xToUpdate,x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
					new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 2)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
						new Point(x.getProperties().get("bond_4_x").intValue()+xToUpdate,x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 3)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue()+xToUpdate,x.getProperties().get("bond_1_y").intValue()),
						new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			else if (resize_corner == 4)
			newShape = ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()),
					new Point(x.getProperties().get("bond_4_x").intValue()+xToUpdate,x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			newShape.setColor(x.getColor());
			newShape.setFillColor(x.getFillColor());
			newShape.getProperties().put("stroke", x.getProperties().get("stroke"));
			newShape.getProperties().put("selected", 1.0);
			model.dragDrawShape(x, newShape);
		}
		else if(x.getClass().getSimpleName().equalsIgnoreCase("ellipse")) {
			Shape newShape = null ;
			if(resize_corner == 1)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue()+xToUpdate,x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
					new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 2)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
						new Point(x.getProperties().get("bond_4_x").intValue()+xToUpdate,x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 3)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue()+xToUpdate,x.getProperties().get("bond_1_y").intValue()),
						new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			else if (resize_corner == 4)
			newShape = ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()),
					new Point(x.getProperties().get("bond_4_x").intValue()+xToUpdate,x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			newShape.setColor(x.getColor());
			newShape.setFillColor(x.getFillColor());
			newShape.getProperties().put("stroke", x.getProperties().get("stroke"));
			newShape.getProperties().put("selected", 1.0);
			model.dragDrawShape(x, newShape);
		}
	}
	
}
