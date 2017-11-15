package eg.edu.alexu.csd.oop.draw.cs60.controller;

import java.awt.*;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarFile;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import eg.edu.alexu.csd.oop.draw.IController;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.*;
import eg.edu.alexu.csd.oop.draw.cs60.view.CustomButton;
import eg.edu.alexu.csd.oop.draw.cs60.view.View;
import eg.edu.alexu.csd.oop.draw.cs60.view.Canvas;
import eg.edu.alexu.csd.oop.draw.cs60.view.StrokeSlider;

public class Controller implements IController {
	private View view;
	private DrawEngineImp model;
	private ArrayList<CustomButton> btnList;
	private Shape currentDraw;
	private Color fill_color = Color.RED;
	private Color color = Color.BLUE;
	private double stroke = 2;
	private ArrayList<Shape> clipBoard;

	public Controller(DrawEngineImp model) {
		this.model = model;
		view = new View(this, model);
		view.createView();
		model.addObserver(view);
		clipBoard = new ArrayList<>();
	}

	public Color getFill_color() {
		return fill_color;
	}

	public void setFill_color(Color fill_color) {
		this.fill_color = fill_color;
		view.getColorPicker().setBackground(fill_color);
		getBtnList();
		for (CustomButton x : btnList) {
			x.setPressedColor(fill_color);
		}
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
		view.getColorPicker().setBorder(new LineBorder(color, new Double(stroke).intValue()));
		getBtnList();
		for (CustomButton x : btnList) {
			x.setNormalColor(color);
		}
	}

	public void getBtnList() {
		this.btnList = view.getBtnList();
	}

	public void draw(Point p1, Point p2) {
		currentDraw = ShapesFactory.CreateShape(getCurrentActive(), p1, p2);
		currentDraw.setColor(color);
		currentDraw.setFillColor(fill_color);
		currentDraw.getProperties().put("stroke", new Double(stroke));
		if (p1.equals(p2)) {
			model.addShapeDrag(currentDraw);
		} else {
			model.addShape(currentDraw);
		}
	}

	public void dragDraw(Point p1, Point p2) {
		Shape newCurrent = ShapesFactory.CreateShape(getCurrentActive(), p1, p2);
		newCurrent.setColor(color);
		newCurrent.setFillColor(fill_color);
		newCurrent.getProperties().put("stroke", new Double(stroke));
		model.dragDrawShape(currentDraw, newCurrent);
		currentDraw = newCurrent;
	}

	private String getCurrentActive() {
		getBtnList();
		String shape = null;
		for (CustomButton x : btnList) {
			if (x.getState()) {
				shape = x.getText();
			}
		}
		return shape;
	}

	public void undo() {
		model.undo();
	}

	public void redo() {
		model.redo();
	}

	public void load(String path) {
		model.load(path);
	}

	public void save(String path) {
		model.save(path);
	}

	public void delete() {
		model.removeShapes(view.getShapeList().getSelectedIndices());
	}

	public void copy(){
		clipBoard = new ArrayList<>();
		for(Shape shape : model.getShapes()){
			if(shape.getProperties().get("selected").intValue() == 1)
				clipBoard.add(shape);
		}
	}

	public void paste() throws CloneNotSupportedException {
		int[] selectedIndices = new int[clipBoard.size()];
		ArrayList<Shape> shapesToAdd = new ArrayList<>();
		int i=0;
		for(Shape shape : clipBoard){
			PasteHandler pasteHandler = new PasteHandler();
			Shape shapeToPaste = pasteHandler.handle(shape);
			//model.addShape(shapeToPaste); // chage to add shapes
			shapesToAdd.add(shapeToPaste);
			//selectedIndices[i++] = model.getArrayListOfShapes().indexOf(shapeToPaste);
		}
		model.addShapes(shapesToAdd);
		for(int x : selectedIndices) {
			selectedIndices[i] = model.getArrayListOfShapes().indexOf(shapesToAdd.get(i++));
		}
		model.setSelected(view.getShapeList().getSelectedIndices());
		view.getShapeList().setSelectedIndices(selectedIndices);
	}

	public void removeClipBoard(){
		clipBoard = new ArrayList<>();
	}

	public void shapeSelected() {
		model.setSelected();
		model.setSelected(view.getShapeList().getSelectedIndices());
	}

	public void removeCurrentDraw() {
		model.removeShapeDrag(currentDraw);
	}

	public void imp(JarFile jarFile) {
		if (jarFile != null
				&& jarFile.getName().substring(jarFile.getName().length() - 3).toLowerCase().equals("jar")) {
			ClassGetter getter = ClassGetter.newInstance(jarFile);
			model.addSupportedShape(getter.getClasses());
		}
	}

	public void strokeChange(int value) {
		stroke = value;
		view.getColorPicker().setBorder(new LineBorder(color, new Double(stroke).intValue()));
		Graphics2D g = (Graphics2D) view.getCanvas().getGraphics();
		g.setStroke(new BasicStroke(value));
	}

	public int getStroke() {
		return new Double(stroke).intValue();
	}

	public void resizeSelected(Point p1, Point p2, int resize_corner) {
		for(Shape x :model.getShapes()) {
			if(x.getProperties().get("selected").intValue() == 1) {
				ResizeHandler handler = new ResizeHandler(model);
				handler.resize(x , p1 , p2 , resize_corner);
			}
		}
	}

	public void moveSelected(Point p1, Point p2) {
		for (Shape x : model.getShapes()) {
			if (x.getProperties().get("selected").intValue() == 1) {
				MoveHandler handler = new MoveHandler(model);
				handler.move(x, p1, p2);
			}
		}
	}

	public void updateSelectedShapes(Color fill_color, Color color) {
		if(view.getShapeList().getSelectedIndices().length > 0)
			model.updateSelectedShapes(fill_color , color);
	}

	public void updateMoveResize() {
		model.updateMoveResize();
	}

	public void draw(Shape shape) {
		model.addShape(shape);
	}

}
