package eg.edu.alexu.csd.oop.draw.cs60.controller;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.IController;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.DrawEngineImp;
import eg.edu.alexu.csd.oop.draw.cs60.model.ShapesFactory;
import eg.edu.alexu.csd.oop.draw.cs60.view.CustomButton;
import eg.edu.alexu.csd.oop.draw.cs60.view.View;

public class Controller implements IController {
	private View view ;
	private DrawEngineImp model ;
	private ArrayList<CustomButton> btnList ;
	private Shape currentDraw ;
	
	public Controller(DrawEngineImp model) {
		 this.model = model ;
		 view = new View(this , model);
		 view.createView();
		 model.addObserver(view);
	}
	
	public void getBtnList () {
		this.btnList = view.getBtnList();
	}
	public void draw(Point p1, Point p2) {
		currentDraw = ShapesFactory.CreateShape(getCurrentActive(), p1, p2);
		currentDraw.setColor(Color.BLUE);
		currentDraw.setFillColor(Color.RED);
		model.addShape(currentDraw);
	}
	
	public void dragDraw(Point p1, Point p2) {
		Shape newCurrent = ShapesFactory.CreateShape(getCurrentActive(), p1, p2);
		newCurrent.setColor(Color.BLUE);
		newCurrent.setFillColor(Color.RED);
		model.dragDrawShape(currentDraw, newCurrent);
		currentDraw = newCurrent ;
	}
	
	
	private String getCurrentActive() {
		getBtnList();
		String shape = null ;
		for(CustomButton x : btnList) {
			if(x.getState()) {
				shape = x.getText();
			}
		}
		return shape ;
	}

	public void undo() {
		model.undo();
	}

	public void redo() {
		model.redo();
	}

	public void load() {
		model.load("file.xml");
	}

	public void save() {
		model.save("file.xml");
	}

	public void delete() {
		model.removeShapes(view.getShapeList().getSelectedIndices());
	}

	public void shapeSelected() {
		model.setSelected();
		model.setSelected(view.getShapeList().getSelectedIndices());
	}
	
}
