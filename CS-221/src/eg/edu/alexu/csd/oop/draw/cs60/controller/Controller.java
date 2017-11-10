package eg.edu.alexu.csd.oop.draw.cs60.controller;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

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
	private Color fill_color = Color.RED;
	private Color color = Color.BLUE;
	
	public Controller(DrawEngineImp model) {
		 this.model = model ;
		 view = new View(this , model);
		 view.createView();
		 model.addObserver(view);
	}
	
	public Color getFill_color() {
		return fill_color;
	}

	public void setFill_color(Color fill_color) {
		this.fill_color = fill_color;
		view.getColorPicker().setBackground(fill_color);
		getBtnList();
		for(CustomButton x : btnList) {
			x.setPressedColor(fill_color);
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		view.getColorPicker().setBorder(new LineBorder(color,5));
		getBtnList();
		for(CustomButton x : btnList) {
			x.setNormalColor(color);
		}
	}
	
	public void getBtnList () {
		this.btnList = view.getBtnList();
	}

	public void draw(Point p1, Point p2) {
		currentDraw = ShapesFactory.CreateShape(getCurrentActive(), p1, p2);
		currentDraw.setColor(color);
		currentDraw.setFillColor(fill_color);
		if(p1.equals(p2)) {
			model.addShapeDrag(currentDraw);}
		else {
			model.addShape(currentDraw);
		}
	}
	
	public void dragDraw(Point p1, Point p2) {
		Shape newCurrent = ShapesFactory.CreateShape(getCurrentActive(), p1, p2);
		newCurrent.setColor(color);
		newCurrent.setFillColor(fill_color);
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
	
	public void removeCurrentDraw() {
		model.removeShapeDrag(currentDraw);
	}

	public void imp(File file) throws MalformedURLException, ClassNotFoundException {
		if(file != null && file.getName().substring(file.getName().length()-5).toLowerCase().equals("class")) {
			URL classUrl;
			System.out.println(file.toURI().toURL());
//	        classUrl = new URL(file.toURI().toURL());
	        URLClassLoader loader = new URLClassLoader(new URL[]{new File(file.getParent()).toURI().toURL()});
	        Class <? extends Shape> cs = (Class<? extends Shape>) loader.loadClass("Test");
	        System.out.println(cs.getName());
		}
	}
	
}
