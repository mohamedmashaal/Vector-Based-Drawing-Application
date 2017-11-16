package eg.edu.alexu.csd.oop.draw.cs60.model;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.*;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Rectangle;

import java.awt.*;

public class MoveHandler {
	private DrawEngineImp model;

	public MoveHandler(DrawEngineImp model) {
		this.model = model;
	}

	public void move(Shape shape, Point p1, Point p2) {
		int xtoUpdate = p2.x - p1.x;
		int ytoUpdate = p2.y - p1.y;

		if (shape.getClass().getSimpleName().equalsIgnoreCase("circle")) {
			Circle newShape = null;
			try {
				newShape = (Circle) shape.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			newShape.getProperties().put("x", newShape.getProperties().get("x") + xtoUpdate);
			newShape.getProperties().put("y", newShape.getProperties().get("y") + ytoUpdate);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			model.dragDrawShape(shape, newShape);
		}

		if (shape.getClass().getSimpleName().equalsIgnoreCase("ellipse")) {
			Ellipse newShape = null;
			try {
				newShape = (Ellipse) shape.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			newShape.getProperties().put("x", newShape.getProperties().get("x") + xtoUpdate);
			newShape.getProperties().put("y", newShape.getProperties().get("y") + ytoUpdate);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			model.dragDrawShape(shape, newShape);
		}

		if (shape.getClass().getSimpleName().equalsIgnoreCase("rectangle")) {
			Rectangle newShape = null;
			try {
				newShape = (Rectangle) shape.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			newShape.getProperties().put("x", newShape.getProperties().get("x") + xtoUpdate);
			newShape.getProperties().put("y", newShape.getProperties().get("y") + ytoUpdate);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			model.dragDrawShape(shape, newShape);
		}

		if (shape.getClass().getSimpleName().equalsIgnoreCase("square")) {
			Square newShape = null;
			try {
				newShape = (Square) shape.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			newShape.getProperties().put("x", newShape.getProperties().get("x") + xtoUpdate);
			newShape.getProperties().put("y", newShape.getProperties().get("y") + ytoUpdate);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			model.dragDrawShape(shape, newShape);
		}

		if (shape.getClass().getSimpleName().equalsIgnoreCase("line")) {
			Line newShape = null;
			try {
				newShape = (Line) shape.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			newShape.getProperties().put("x1", newShape.getProperties().get("x1") + xtoUpdate);
			newShape.getProperties().put("y1", newShape.getProperties().get("y1") + ytoUpdate);
			newShape.getProperties().put("x2", newShape.getProperties().get("x2") + xtoUpdate);
			newShape.getProperties().put("y2", newShape.getProperties().get("y2") + ytoUpdate);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			model.dragDrawShape(shape, newShape);
		}

		if (shape.getClass().getSimpleName().equalsIgnoreCase("triangle")) {
			Triangle newShape = null;
			try {
				newShape = (Triangle) shape.clone();
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
			newShape.getProperties().put("x1", newShape.getProperties().get("x1") + xtoUpdate);
			newShape.getProperties().put("y1", newShape.getProperties().get("y1") + ytoUpdate);
			newShape.getProperties().put("x2", newShape.getProperties().get("x2") + xtoUpdate);
			newShape.getProperties().put("y2", newShape.getProperties().get("y2") + ytoUpdate);
			newShape.getProperties().put("x3", newShape.getProperties().get("x3") + xtoUpdate);
			newShape.getProperties().put("y3", newShape.getProperties().get("y3") + ytoUpdate);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			model.dragDrawShape(shape, newShape);
		}

	}

}
