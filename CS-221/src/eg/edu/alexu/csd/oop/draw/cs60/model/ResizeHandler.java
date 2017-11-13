package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.Point;

import javax.management.modelmbean.ModelMBean;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Triangle;

public class ResizeHandler {
	private DrawEngineImp model;
	public ResizeHandler(DrawEngineImp model) {
		this.model = model ;
	}
	public void resize(Shape x, Point p1, Point p2, int resize_corner) {
		int xtoUpdate = p2.x - p1.x;
		int ytoUpdate = p2.y - p1.y;
		if(x.getClass().getSimpleName().equalsIgnoreCase("circle")) {
			Shape newShape = null ;
			if(resize_corner == 1)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue()+xtoUpdate,x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
					new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 2)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
						new Point(x.getProperties().get("bond_4_x").intValue()+xtoUpdate,x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 3)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue()+xtoUpdate,x.getProperties().get("bond_1_y").intValue()),
						new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			else if (resize_corner == 4)
			newShape = ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()),
					new Point(x.getProperties().get("bond_4_x").intValue()+xtoUpdate,x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			newShape.setColor(x.getColor());
			newShape.setFillColor(x.getFillColor());
			newShape.getProperties().put("stroke", x.getProperties().get("stroke"));
			newShape.getProperties().put("selected", 1.0);
			model.dragDrawShape(x, newShape);
		}
		else if(x.getClass().getSimpleName().equalsIgnoreCase("ellipse")) {
			Shape newShape = null ;
			if(resize_corner == 1)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue()+xtoUpdate,x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
					new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 2)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
						new Point(x.getProperties().get("bond_4_x").intValue()+xtoUpdate,x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 3)
				newShape =ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue()+xtoUpdate,x.getProperties().get("bond_1_y").intValue()),
						new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			else if (resize_corner == 4)
			newShape = ShapesFactory.CreateShape("ellipse", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()),
					new Point(x.getProperties().get("bond_4_x").intValue()+xtoUpdate,x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			newShape.setColor(x.getColor());
			newShape.setFillColor(x.getFillColor());
			newShape.getProperties().put("stroke", x.getProperties().get("stroke"));
			newShape.getProperties().put("selected", 1.0);
			model.dragDrawShape(x, newShape);
		}
		else if(x.getClass().getSimpleName().equalsIgnoreCase("rectangle")) {
			Shape newShape = null ;
			if(resize_corner == 1)
				newShape =ShapesFactory.CreateShape("rectangle", new Point(x.getProperties().get("bond_1_x").intValue()+xtoUpdate,x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
					new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 2)
				newShape =ShapesFactory.CreateShape("rectangle", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
						new Point(x.getProperties().get("bond_4_x").intValue()+xtoUpdate,x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 3)
				newShape =ShapesFactory.CreateShape("rectangle", new Point(x.getProperties().get("bond_1_x").intValue()+xtoUpdate,x.getProperties().get("bond_1_y").intValue()),
						new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			else if (resize_corner == 4)
			newShape = ShapesFactory.CreateShape("rectangle", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()),
					new Point(x.getProperties().get("bond_4_x").intValue()+xtoUpdate,x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			newShape.setColor(x.getColor());
			newShape.setFillColor(x.getFillColor());
			newShape.getProperties().put("stroke", x.getProperties().get("stroke"));
			newShape.getProperties().put("selected", 1.0);
			model.dragDrawShape(x, newShape);
		}
		else if(x.getClass().getSimpleName().equalsIgnoreCase("square")) {
			Shape newShape = null ;
			if(resize_corner == 1)
				newShape =ShapesFactory.CreateShape("rectangle", new Point(x.getProperties().get("bond_1_x").intValue()+xtoUpdate,x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
					new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 2)
				newShape =ShapesFactory.CreateShape("rectangle", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()+ytoUpdate),
						new Point(x.getProperties().get("bond_4_x").intValue()+xtoUpdate,x.getProperties().get("bond_4_y").intValue()));
			else if(resize_corner == 3)
				newShape =ShapesFactory.CreateShape("rectangle", new Point(x.getProperties().get("bond_1_x").intValue()+xtoUpdate,x.getProperties().get("bond_1_y").intValue()),
						new Point(x.getProperties().get("bond_4_x").intValue(),x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			else if (resize_corner == 4)
			newShape = ShapesFactory.CreateShape("rectangle", new Point(x.getProperties().get("bond_1_x").intValue(),x.getProperties().get("bond_1_y").intValue()),
					new Point(x.getProperties().get("bond_4_x").intValue()+xtoUpdate,x.getProperties().get("bond_4_y").intValue()+ytoUpdate));
			newShape.setColor(x.getColor());
			newShape.setFillColor(x.getFillColor());
			newShape.getProperties().put("stroke", x.getProperties().get("stroke"));
			newShape.getProperties().put("selected", 1.0);
			model.dragDrawShape(x, newShape);
		}
		else if(x.getClass().getSimpleName().equalsIgnoreCase("line")) {
			Shape newShape = null ;
			if(resize_corner == 1) {
				Point num1 = new Point(x.getProperties().get("x1").intValue() ,x.getProperties().get("y1").intValue());
				Point num2 = new Point(x.getProperties().get("x2").intValue() ,x.getProperties().get("y2").intValue());
				if(num1.x < num2.x) {
					num1.x = num1.x + xtoUpdate; 
				}
				else {
					num2.x = num2.x + xtoUpdate;
				}
				if(num1.y < num2.y) {
					num1.y = num1.y + ytoUpdate; 
				}
				else {
					num2.y = num2.y + ytoUpdate;
				}
				newShape =ShapesFactory.CreateShape("line", num1 , num2);}
			else if(resize_corner == 2) {
				Point num1 = new Point(x.getProperties().get("x1").intValue() ,x.getProperties().get("y1").intValue());
				Point num2 = new Point(x.getProperties().get("x2").intValue() ,x.getProperties().get("y2").intValue());
				if(num1.x > num2.x) {
					num1.x = num1.x + xtoUpdate; 
				}
				else {
					num2.x = num2.x + xtoUpdate;
				}
				if(num1.y < num2.y) {
					num1.y = num1.y + ytoUpdate; 
				}
				else {
					num2.y = num2.y + ytoUpdate;
				}
				newShape =ShapesFactory.CreateShape("line", num1 , num2);}
			else if(resize_corner == 3) {
				Point num1 = new Point(x.getProperties().get("x1").intValue() ,x.getProperties().get("y1").intValue());
				Point num2 = new Point(x.getProperties().get("x2").intValue() ,x.getProperties().get("y2").intValue());
				if(num1.x < num2.x) {
					num1.x = num1.x + xtoUpdate; 
				}
				else {
					num2.x = num2.x + xtoUpdate;
				}
				if(num1.y > num2.y) {
					num1.y = num1.y + ytoUpdate; 
				}
				else {
					num2.y = num2.y + ytoUpdate;
				}
				newShape =ShapesFactory.CreateShape("line", num1 , num2);}
			else if (resize_corner == 4) {
				Point num1 = new Point(x.getProperties().get("x1").intValue() ,x.getProperties().get("y1").intValue());
				Point num2 = new Point(x.getProperties().get("x2").intValue() ,x.getProperties().get("y2").intValue());
				if(num1.x > num2.x) {
					num1.x = num1.x + xtoUpdate; 
				}
				else {
					num2.x = num2.x + xtoUpdate;
				}
				if(num1.y > num2.y) {
					num1.y = num1.y + ytoUpdate; 
				}
				else {
					num2.y = num2.y + ytoUpdate;
				}
			newShape =ShapesFactory.CreateShape("line", num1 , num2);}
			newShape.setColor(x.getColor());
			newShape.setFillColor(x.getFillColor());
			newShape.getProperties().put("stroke", x.getProperties().get("stroke"));
			newShape.getProperties().put("selected", 1.0);
			model.dragDrawShape(x, newShape);
		}
		else if(x.getClass().getSimpleName().equalsIgnoreCase("triangle")) {
			Shape newShape = null ;
			Point num1 = new Point(x.getProperties().get("x1").intValue(),x.getProperties().get("y1").intValue());
			Point num2 = new Point(x.getProperties().get("x2").intValue(),x.getProperties().get("y2").intValue());
			Point num3 = new Point(x.getProperties().get("x3").intValue(),x.getProperties().get("y3").intValue());
			if(num1.y < num2.y) {
			if(resize_corner == 1) {
				newShape = new Triangle(new Point(num1.x+xtoUpdate,num1.y+ytoUpdate),new Point(num2.x+xtoUpdate,num2.y),num3);}
			else if(resize_corner == 2) {
				newShape =new Triangle(new Point(num1.x+xtoUpdate,num1.y+ytoUpdate),num2 , new Point(num3.x+xtoUpdate,num3.y));}
			else if(resize_corner == 3) {
				newShape =new Triangle(num1,new Point(num2.x+xtoUpdate,num2.y+ytoUpdate) , new Point(num3.x,num3.y+ytoUpdate));}
			else if (resize_corner == 4) {
			newShape = new Triangle(num1,new Point(num2.x,num2.y+ytoUpdate) , new Point(num3.x+xtoUpdate,num3.y+ytoUpdate));}
			}
			else {
				if(resize_corner == 1) {
					newShape = new Triangle(num1,new Point(num2.x,num2.y+ytoUpdate) , new Point(num3.x+xtoUpdate,num3.y+ytoUpdate));}
				else if(resize_corner == 2) {
					newShape =new Triangle(num1,new Point(num2.x+xtoUpdate,num2.y+ytoUpdate) , new Point(num3.x,num3.y+ytoUpdate));}
				else if(resize_corner == 3) {
					newShape =new Triangle(new Point(num1.x+xtoUpdate,num1.y+ytoUpdate),num2 , new Point(num3.x+xtoUpdate,num3.y));}
				else if (resize_corner == 4) {
					newShape = new Triangle(new Point(num1.x+xtoUpdate,num1.y+ytoUpdate),new Point(num2.x+xtoUpdate,num2.y),num3);}
				}
			newShape.setColor(x.getColor());
			newShape.setFillColor(x.getFillColor());
			newShape.getProperties().put("stroke", x.getProperties().get("stroke"));
			newShape.getProperties().put("selected", 1.0);
			model.dragDrawShape(x, newShape);
		}
	}
	
}
