package eg.edu.alexu.csd.oop.draw.cs60.model;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.*;

public class PasteHandler {

	private int offset = 50;

	public Shape handle(Shape shape) throws CloneNotSupportedException {
		if (shape.getClass().getSimpleName().equalsIgnoreCase("circle")) {
			Circle newShape = (Circle) shape.clone();
			newShape.getProperties().put("x", shape.getProperties().get("x") + offset);
			newShape.getProperties().put("y", shape.getProperties().get("y") + offset);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			newShape.getProperties().put("selected", 1.0);
			return newShape;
		}

		if (shape.getClass().getSimpleName().equalsIgnoreCase("ellipse")) {
			Ellipse newShape = (Ellipse) shape.clone();
			newShape.getProperties().put("x", shape.getProperties().get("x") + offset);
			newShape.getProperties().put("y", shape.getProperties().get("y") + offset);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			newShape.getProperties().put("selected", 1.0);
			return newShape;
		}

		if (shape.getClass().getSimpleName().equalsIgnoreCase("rectangle")) {
			Rectangle newShape = (Rectangle) shape.clone();
			newShape.getProperties().put("x", shape.getProperties().get("x") + offset);
			newShape.getProperties().put("y", shape.getProperties().get("y") + offset);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			newShape.getProperties().put("selected", 1.0);
			return newShape;
		}

		if (shape.getClass().getSimpleName().equalsIgnoreCase("square")) {
			Square newShape = (Square) shape.clone();
			newShape.getProperties().put("x", shape.getProperties().get("x") + offset);
			newShape.getProperties().put("y", shape.getProperties().get("y") + offset);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			newShape.getProperties().put("selected", 1.0);
			return newShape;
		}

		if (shape.getClass().getSimpleName().equalsIgnoreCase("line")) {
			Line newShape = (Line) shape.clone();
			newShape.getProperties().put("x1", shape.getProperties().get("x1") + offset);
			newShape.getProperties().put("y1", shape.getProperties().get("y1") + offset);
			newShape.getProperties().put("x2", shape.getProperties().get("x2") + offset);
			newShape.getProperties().put("y2", shape.getProperties().get("y2") + offset);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			newShape.getProperties().put("selected", 1.0);
			return newShape;
		}

		if (shape.getClass().getSimpleName().equalsIgnoreCase("triangle")) {
			Triangle newShape = (Triangle) shape.clone();
			newShape.getProperties().put("x1", shape.getProperties().get("x1") + offset);
			newShape.getProperties().put("y1", shape.getProperties().get("y1") + offset);
			newShape.getProperties().put("x2", shape.getProperties().get("x2") + offset);
			newShape.getProperties().put("y2", shape.getProperties().get("y2") + offset);
			newShape.getProperties().put("x3", shape.getProperties().get("x3") + offset);
			newShape.getProperties().put("y3", shape.getProperties().get("y3") + offset);
			newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
			newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
			newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
			newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
			newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
			newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
			newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
			newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
			return newShape;
		}

		return null;
	}
}
