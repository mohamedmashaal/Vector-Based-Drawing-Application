package eg.edu.alexu.csd.oop.draw.cs60;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import eg.edu.alexu.csd.oop.draw.DrawEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class DrawEngineImp implements DrawEngine {
	private ArrayList<Shape> data ;
	private Graphics canvas ;
	public DrawEngineImp() {
		data = new ArrayList<>();;
	}

	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub
		for(Shape x : data) {
			x.draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		data.add(shape);
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub

	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		return null;
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
