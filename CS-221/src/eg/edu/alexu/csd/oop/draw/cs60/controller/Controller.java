package eg.edu.alexu.csd.oop.draw.cs60.controller;

import eg.edu.alexu.csd.oop.draw.cs60.model.DrawEngineImp;
import eg.edu.alexu.csd.oop.draw.cs60.view.View;

public class Controller {
	private View view ;
	private DrawEngineImp model = DrawEngineImp.getUniqueInstance();
	
	public Controller() {
		 view = new View();
	}
	
}
