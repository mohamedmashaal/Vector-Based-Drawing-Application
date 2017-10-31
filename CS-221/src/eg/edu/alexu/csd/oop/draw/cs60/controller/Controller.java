package eg.edu.alexu.csd.oop.draw.cs60.controller;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs60.model.DrawEngineImp;
import eg.edu.alexu.csd.oop.draw.cs60.view.View;

public class Controller {
	private View view ;
	private DrawingEngine model ;
	
	public Controller(DrawingEngine model) {
		 this.model = model ;
		 view = new View(this , model);
	}
	
}
