package eg.edu.alexu.csd.oop.draw.cs60.main;

import eg.edu.alexu.csd.oop.draw.cs60.controller.Controller;
import eg.edu.alexu.csd.oop.draw.cs60.model.DrawEngineImp;

public class MainEntry {
	public static void main(String[] args) {
		new Controller(DrawEngineImp.getUniqueInstance());
	}
}
