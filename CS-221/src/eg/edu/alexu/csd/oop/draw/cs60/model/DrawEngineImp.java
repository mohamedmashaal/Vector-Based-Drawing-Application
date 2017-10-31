package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.Graphics;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.beans.*;

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
		//throw new RuntimeException(path);
		saveXML(path);
		saveJSON(path);
	}

	@Override
	public void load(String path) {
		//throw new RuntimeException(path);
		loadXML(path);
		loadJSON(path);
	}
	
	private void saveXML(String path){
		if(shapes.peek().isEmpty())
			return;
        String mapToString = objectToString(shapes.peek());
        //System.out.println("Map to XML: \n" + mapToString);
        File outputXML = new File(path);
        try {
			PrintWriter pw = new PrintWriter(outputXML);
			pw.print(mapToString);
			pw.close();
		}
        catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void loadXML(String path){
		File inputXML = new File(path);
		StringBuilder shapesXMLContent = new StringBuilder();
		Scanner in;
		try {
			in = new Scanner(inputXML);
			while(in.hasNextLine()) {
				shapesXMLContent.append(in.nextLine());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        ArrayList<Shape> parsedMap = (ArrayList<Shape>) stringToObject(shapesXMLContent.toString());
        shapes.push(parsedMap);
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
        XMLDecoder xmlDecoder = new XMLDecoder(new ByteArrayInputStream(string.getBytes()));
        return xmlDecoder.readObject();
    }

}
