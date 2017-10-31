package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.Graphics;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.Stack;


import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Ellipse;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Line;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Square;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Triangle;

public class DrawEngineImp implements DrawingEngine {
	private List<Class<? extends Shape>> supportedShapes ;
	private Stack<ArrayList<Shape>> shapes ;
	private Stack<ArrayList<Shape>> redoShapes ;
	private static DrawEngineImp uniqueInstance = new DrawEngineImp() ;
	private DrawEngineImp() {
		shapes = new Stack<ArrayList<Shape>>();
		shapes.push(new ArrayList<Shape>());
		redoShapes = new Stack<ArrayList<Shape>>();
		initSupportedShapes();
	}
	
	private void initSupportedShapes() {
		supportedShapes = new ArrayList<>();
		supportedShapes.add(Line.class);
		supportedShapes.add(Rectangle.class);
		supportedShapes.add(Square.class);
		supportedShapes.add(Circle.class);
		supportedShapes.add(Ellipse.class);
		supportedShapes.add(Triangle.class);
	}

	public static DrawEngineImp getUniqueInstance() {
		return uniqueInstance;
	}
	
	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub
		for(Shape x : shapes.peek()) {
			x.draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		if(shapes.size() <= 20)
			shapes.push(new ArrayList<Shape>(shapes.peek()));
		else {
			shapes.remove(0);
			shapes.push(new ArrayList<Shape>(shapes.peek()));
		}	
		shapes.peek().add(shape);
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		int index = shapes.peek().indexOf(shape);
		if(index >= 0) {
			if(shapes.size() <= 20) {
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			}
			else {
				shapes.remove(0);
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			}	
			shapes.peek().remove(index);
		}
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		int index = shapes.peek().indexOf(oldShape);
		if(index >= 0){
			if(shapes.size() <= 20)
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			else {
				shapes.remove(0);
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			}	
			shapes.peek().set(index, newShape);
		}
	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		return shapes.peek().toArray(new Shape[0]);
	}
	
	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		//Reflections reflections = new Reflections();
		//List<Class<? extends Shape>> list = new LinkedList<>(reflections.getSubTypesOf(MainShape.class));
		//return list ;
		return supportedShapes;
	}

	@Override
	public void undo() {
		if(shapes.size() > 1)
			redoShapes.push(shapes.pop());
		
	}

	@Override
	public void redo() {
		if(!redoShapes.empty())
			shapes.push(redoShapes.pop());
	}

	@Override
	public void save(String path) {
		Properties prop = new Properties();
		for(int i = 0; i < shapes.peek().size(); i++) {
			prop.setProperty(shapes.peek().get(i).getClass().getSimpleName().toString(),shapes.peek().get(i).getProperties().toString());
			/*for(Map.Entry<String, Double> entry : shapes.peek().get(i).getProperties().entrySet()) {
				prop.setProperty(this.getClass().getSimpleName().toString(),entry.toString());
			}*/
			System.out.println(prop);
			//System.out.println("----------------");
		}
		System.out.println("###################################");
		try {
			File xmlFile = new File("properities.xml");
			FileOutputStream xmlFileStream = new FileOutputStream(xmlFile);;
			Date now = new Date();
			prop.storeToXML(xmlFileStream, "Created on " + now);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub

	}

}
