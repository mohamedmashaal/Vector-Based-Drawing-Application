package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.Graphics;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.Map;
import java.util.Scanner;
import java.beans.*;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Observer;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.Subject;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Circle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Ellipse;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Line;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Rectangle;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Square;
import eg.edu.alexu.csd.oop.draw.cs60.model.shapes.Triangle;

public class DrawEngineImp implements DrawingEngine , Subject {
	private List<Observer> observers ;
	private List<Class<? extends Shape>> supportedShapes ;
	private Stack<ArrayList<Shape>> shapes ;
	private Stack<ArrayList<Shape>> redoShapes ;
	private static DrawEngineImp uniqueInstance = new DrawEngineImp() ;
	private DrawEngineImp() {
		shapes = new Stack<ArrayList<Shape>>();
		shapes.push(new ArrayList<Shape>());
		redoShapes = new Stack<ArrayList<Shape>>();
		observers = new ArrayList<Observer>();
		initSupportedShapes();
	}
	
	private void clear() {
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
		notifyObservers();
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
		notifyObservers();
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
		notifyObservers();
	}
	
	public void dragDrawShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		int index = shapes.peek().indexOf(oldShape);
		if(index >= 0){
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
		notifyObservers();
	}

	@Override
	public void redo() {
		if(!redoShapes.empty())
			shapes.push(redoShapes.pop());
		notifyObservers();
	}

	@Override
	public void save(String path) {
		//throw new RuntimeException(path);
		if(path.substring(path.length()-3).equalsIgnoreCase("xml")){
			saveXML(path);
		}
		else if (path.substring(path.length()-4).equalsIgnoreCase("json")){
			saveJSON(path);
		}
		else {
			throw new RuntimeException(path);
		}
	}

	@Override
	public void load(String path) {
		//throw new RuntimeException(path);
		if(path.substring(path.length()-3).equalsIgnoreCase("xml")){
			loadXML(path);
		}
		else if (path.substring(path.length()-4).equalsIgnoreCase("json")){
			loadJSON(path);
		}
		else {
			throw new RuntimeException(path);
		}
	}
	
	private void saveXML(String path){
		/*if(shapes.peek().isEmpty())
			return;*/
        String objToString = objectToString(shapes);
        File outputXML = new File(path);
        try {
			FileWriter pw = new FileWriter(outputXML);
			pw.write(objToString);
			pw.close();
		}
        catch (Exception e) {
			throw new RuntimeException();
		}
	}
	
	@SuppressWarnings("unchecked")
	private void loadXML(String path){
		File inputXML = new File(path);
		StringBuilder shapesXMLContent = new StringBuilder();
		Scanner in;
		try {
			in = new Scanner(inputXML);
			while(in.hasNextLine()) {
				shapesXMLContent.append(in.nextLine());
			}
			in.close();
	        Stack<ArrayList<Shape>> parsedObj = (Stack<ArrayList<Shape>>) stringToObject(shapesXMLContent.toString());
	        shapes = parsedObj;
	        }
		catch(Exception e) {
			throw new RuntimeException(path);
		}
	}
	
	private void saveJSON(String path){
		// TODO to be implemented
	}
	
	private void loadJSON(String path){
		// TODO to be implemented
	}
	
	private String objectToString(Object hashMap) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(bos);
        xmlEncoder.writeObject(hashMap);
        xmlEncoder.close();
        return bos.toString();
    }

    private Object stringToObject(String string) {
        @SuppressWarnings("resource")
		XMLDecoder xmlDecoder = new XMLDecoder(new ByteArrayInputStream(string.getBytes()));
        return xmlDecoder.readObject();
    }


	@Override
	public void notifyObservers() {
		for(Observer x : observers) {
			x.update();
		}
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer observer) {
		int index = observers.indexOf(observer);
		if(index >= 0) {
			observers.remove(observer);
		}
	}

}
