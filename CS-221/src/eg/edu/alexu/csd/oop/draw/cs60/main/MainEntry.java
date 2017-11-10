package eg.edu.alexu.csd.oop.draw.cs60.main;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs60.controller.Controller;
import eg.edu.alexu.csd.oop.draw.cs60.model.DrawEngineImp;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Line;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Triangle;

public class MainEntry {
	public static void main(String [] args) {
		Controller controller = new Controller(DrawEngineImp.getUniqueInstance());
	}
}
