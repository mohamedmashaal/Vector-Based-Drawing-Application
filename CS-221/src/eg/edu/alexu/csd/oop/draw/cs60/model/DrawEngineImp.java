package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class DrawEngineImp implements DrawingEngine {
	private ArrayList<Shape> shapes ;
	public DrawEngineImp() {
		shapes = new ArrayList<>();;
	}

	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub
		for(Shape x : shapes) {
			x.draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		shapes.add(shape);
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		int index = shapes.indexOf(shape);
		if(index >= 0)
			shapes.remove(index);
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		int index = shapes.indexOf(oldShape);
		if(index >= 0)
			shapes.set(index, newShape) ;
	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		return shapes.toArray(new Shape [0]);
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		return null ;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub

	}

}
