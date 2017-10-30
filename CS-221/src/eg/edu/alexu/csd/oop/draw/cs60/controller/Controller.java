package eg.edu.alexu.csd.oop.draw.cs60.controller;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs60.model.DrawEngineImp;
import eg.edu.alexu.csd.oop.draw.cs60.view.ViewTryout;

public class Controller {
	private ViewTryout viewTryout = new ViewTryout();
	private DrawEngineImp model = DrawEngineImp.getUniqueInstance();
	
}
