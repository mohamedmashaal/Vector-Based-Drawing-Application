package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

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
			if(((MainShape)x).isSelected()) {
				((MainShape)x).drawBonds(canvas);
			}
		}
		drawFullBonds(canvas);
	}
	
	private void drawFullBonds(Graphics canvas) {
		Point p1 = null;
		Point p2 = null;
		for(Shape x : getShapes()) {
			if(((MainShape)x).isSelected()) {
				Point [] bonds = ((MainShape)x).getBonds();
				if(p1 == null ) {
				p1 = bonds[0];
				p2= bonds [1];
				}
				else {
					p1.x = Math.min(p1.x, bonds[0].x);
					p1.y = Math.min(p1.y, bonds[0].y);
					p2.x = Math.max(p2.x, bonds[1].x);
					p2.y = Math.max(p2.y, bonds[1].y);
				}
			}
		}
		int margin = 10 ;
		Graphics2D g = (Graphics2D)canvas ;
		if(p1 != null) {
			g.setStroke( new BasicStroke(2));
			g.drawRect(p1.x - margin, p1.y - margin ,p2.x - p1.x + 2 *margin, p2.y - p1.y + 2 * margin);
		}
		}

	public void setSelected() {
		for(Shape x : getShapes()) {
			((MainShape)x).setSelected(false);
		}
	}
	
	public void setSelected(int [] indices) {
		for(Integer x : indices)
		System.out.print(x+" ");
		System.out.println();
		Shape [] shapes = getShapes();
		for(Integer x : indices) {
			((MainShape)shapes[x]).setSelected(true);
		}
		notifyObserversSelection();
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

	public void removeShapes(int [] indices) {
		// TODO Auto-generated method stub
		Arrays.sort(indices);
		if(indices.length > 0) {
			if(shapes.size() <= 20) {
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			}
			else {
				shapes.remove(0);
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			}
			for(int i = indices.length-1 ; i >=0 ; i --) {
			shapes.peek().remove(getShapes()[indices[i]]);}
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
	        notifyObservers();
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

	@Override
	public void notifyObserversSelection() {
		for(Observer x : observers) {
			x.updateSelected();
		}
	}

}
