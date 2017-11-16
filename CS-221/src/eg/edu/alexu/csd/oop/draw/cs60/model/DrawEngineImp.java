package eg.edu.alexu.csd.oop.draw.cs60.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.*;
import java.util.*;

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

public class DrawEngineImp implements DrawingEngine, Subject {
	private List<Observer> observers;
	private List<Class<? extends Shape>> supportedShapes;
	private Stack<ArrayList<Shape>> shapes;
	private Stack<ArrayList<Shape>> redoShapes;
	private static DrawEngineImp uniqueInstance = new DrawEngineImp();
	private JoeSONParser JSONParser = new JoeSONParser();
	private XMLParser xmlParser = new XMLParser();
	private Color full_border = Color.BLACK;
	private Color corner_color = Color.BLUE;
	private Point[] full_bonds = new Point[4];
	private int size_corner = 5;
	private int margin_bonds = 10;
	private int builtInShapes;

	private DrawEngineImp() {
		shapes = new Stack<ArrayList<Shape>>();
		shapes.push(new ArrayList<Shape>());
		redoShapes = new Stack<ArrayList<Shape>>();
		observers = new ArrayList<Observer>();
		initSupportedShapes();
	}

	private void clear() {
		shapes = new Stack<ArrayList<Shape>>();
		redoShapes = new Stack<ArrayList<Shape>>();
	}

	private void initSupportedShapes() {
		supportedShapes = new ArrayList<>();
		supportedShapes.add(Line.class);
		supportedShapes.add(Rectangle.class);
		supportedShapes.add(Square.class);
		supportedShapes.add(Circle.class);
		supportedShapes.add(Ellipse.class);
		supportedShapes.add(Triangle.class);
		builtInShapes = supportedShapes.size();
	}

	public static DrawEngineImp getUniqueInstance() {
		return uniqueInstance;
	}

	@Override
	public void refresh(Graphics canvas) {
		for (Shape x : shapes.peek()) {
			x.draw(canvas);
		}
		drawFullBonds(canvas);
	}

	private void drawFullBonds(Graphics canvas) {
		Point p1 = null;
		Point p2 = null;
		Point p3 = null;
		Point p4 = null;
		for (Shape x : getShapes()) {
			boolean plugin = isPlugin(x);
			if (!plugin && x.getProperties().get("selected") != null
					&& x.getProperties().get("selected").intValue() == 1) {
				Point[] bonds = new Point[] {
						new Point(x.getProperties().get("bond_1_x").intValue(),
								x.getProperties().get("bond_1_y").intValue()),
						new Point(x.getProperties().get("bond_4_x").intValue(),
								x.getProperties().get("bond_4_y").intValue()) };
				if (p1 == null) {
					p1 = bonds[0];
					p4 = bonds[1];
				} else {
					p1.x = Math.min(p1.x, bonds[0].x);
					p1.y = Math.min(p1.y, bonds[0].y);
					p4.x = Math.max(p4.x, bonds[1].x);
					p4.y = Math.max(p4.y, bonds[1].y);
				}
			}
		}
		if (p1 != null) {
			p2 = new Point(p1.x + (p4.x - p1.x) + margin_bonds, p1.y - margin_bonds);
			p3 = new Point(p1.x - margin_bonds, p1.y + (p4.y - p1.y) + margin_bonds);
			full_bonds[0] = new Point(p1.x - margin_bonds, p1.y - margin_bonds);
			full_bonds[1] = p2;
			full_bonds[2] = p3;
			full_bonds[3] = new Point(p4.x + margin_bonds, p4.y + margin_bonds);
			Graphics2D g = (Graphics2D) canvas;
			g.setStroke(new BasicStroke(2));
			g.setColor(full_border);
			g.drawRect(p1.x - margin_bonds, p1.y - margin_bonds, p4.x - p1.x + 2 * margin_bonds,
					p4.y - p1.y + 2 * margin_bonds);
			for (Point x : full_bonds) {
				g.setColor(corner_color);
				g.fillRect(x.x - size_corner, x.y - size_corner, 2 * size_corner, 2 * size_corner);
				g.setStroke(new BasicStroke(new Float(1).floatValue()));
				g.setColor(full_border);
				g.drawRect(x.x - size_corner, x.y - size_corner, 2 * size_corner, 2 * size_corner);
			}
		} else {
			full_bonds[0] = p1;
			full_bonds[1] = p2;
			full_bonds[2] = p3;
			full_bonds[3] = p4;
		}
	}

	public boolean isPlugin(Shape shape) {
		boolean plugin = true;
		for (int i = 0; i < builtInShapes; i++) {
			if (getSupportedShapes().get(i).getSimpleName().equals(shape.getClass().getSimpleName())) {
				plugin = false;
				break;
			}
		}
		return plugin;
	}

	public Point[] getFull_bonds() {
		return full_bonds;
	}

	public int getSize_corner() {
		return size_corner;
	}

	public void setSelected() {
		for (Shape x : getShapes()) {
			x.getProperties().put("selected", 0.0);
		}
	}

	public void setSelected(int[] indices) {
		Shape[] shapes = getShapes();
		for (Integer x : indices) {
			shapes[x].getProperties().put("selected", 1.0);
		}
		notifyObserversSelection();
	}

	@Override
	public void addShape(Shape shape) {
		redoShapes = new Stack<>();
		if (shapes.size() <= 20)
			shapes.push(new ArrayList<Shape>(shapes.peek()));
		else {
			shapes.remove(0);
			shapes.push(new ArrayList<Shape>(shapes.peek()));
		}
		shapes.peek().add(shape);
		notifyObservers();
	}

	public void addShapes(ArrayList<Shape> shapesToAdd) {
		redoShapes = new Stack<>();
		if (shapes.size() <= 20)
			shapes.push(new ArrayList<Shape>(shapes.peek()));
		else {
			shapes.remove(0);
			shapes.push(new ArrayList<Shape>(shapes.peek()));
		}
		shapes.peek().addAll(shapesToAdd);
		notifyObservers();
	}

	@Override
	public void removeShape(Shape shape) {
		int index = shapes.peek().indexOf(shape);
		if (index >= 0) {
			redoShapes = new Stack<>();
			if (shapes.size() <= 20) {
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			} else {
				shapes.remove(0);
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			}
			shapes.peek().remove(index);
		}
		notifyObservers();
	}

	public void removeShapes(int[] indices) {
		Arrays.sort(indices);
		if (indices.length > 0) {
			redoShapes = new Stack<>();
			if (shapes.size() <= 20) {
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			} else {
				shapes.remove(0);
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			}
			for (int i = indices.length - 1; i >= 0; i--) {
				shapes.peek().remove(getShapes()[indices[i]]);
			}
		}
		notifyObservers();
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		int index = shapes.peek().indexOf(oldShape);
		if (index >= 0) {
			redoShapes = new Stack<>();
			if (shapes.size() <= 20)
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			else {
				shapes.remove(0);
				shapes.push(new ArrayList<Shape>(shapes.peek()));
			}
			shapes.peek().set(index, newShape);
		}
		notifyObservers();
	}

	public void addShapeDrag(Shape shape) {
		shapes.peek().add(shape);
	}

	public void removeShapeDrag(Shape shape) {
		int index = shapes.peek().indexOf(shape);
		shapes.peek().remove(index);
	}

	public void dragDrawShape(Shape oldShape, Shape newShape) {
		int index = shapes.peek().indexOf(oldShape);
		if (index >= 0) {
			shapes.peek().set(index, newShape);
		}
	}

	public ArrayList<Shape> getArrayListOfShapes() {
		return shapes.peek();
	}

	@Override
	public Shape[] getShapes() {
		return shapes.peek().toArray(new Shape[0]);
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		return supportedShapes;
	}

	public void addSupportedShape(ArrayList<Class<? extends Shape>> arrayList) {
		for (Class<? extends Shape> x : arrayList) {
			if (!supportedShapes.contains(x))
				supportedShapes.add(x);
		}
		notifyObserversPlugin();
	}

	private void notifyObserversPlugin() {
		for (Observer x : observers) {
			x.updateSupportedShapes();
		}
	}

	@Override
	public void undo() {
		if (shapes.size() > 1)
			redoShapes.push(shapes.pop());
		notifyObservers();
	}

	@Override
	public void redo() {
		if (!redoShapes.empty())
			shapes.push(redoShapes.pop());
		notifyObservers();
	}

	@Override
	public void save(String path) {
		if (path.substring(path.length() - 3).equalsIgnoreCase("xml")) {
			saveXML(path);
		} else if (path.substring(path.length() - 4).equalsIgnoreCase("json")) {
			saveJSON(path);
		} else {
			throw new RuntimeException(path);
		}
	}

	@Override
	public void load(String path) {
		if (path.substring(path.length() - 3).equalsIgnoreCase("xml")) {
			loadXML(path);
			;
		} else if (path.substring(path.length() - 4).equalsIgnoreCase("json")) {
			loadJSON(path);
		} else {
			throw new RuntimeException(path);
		}
	}

	private void saveXML(String path) {
		ArrayList<Map<String, String>> arrayListofShapeMap = new ArrayList<>();
		Map<String, Integer> freqOfShapes = new HashMap<>();
		for (Shape shape : shapes.peek()) {
			// calculation of frequency (for indexing purpose)
			String shapeName = shape.getClass().getSimpleName();
			try {
				freqOfShapes.put(shapeName, freqOfShapes.get(shapeName) + 1);
			} catch (Exception e) {
				freqOfShapes.put(shapeName, 1);
			}

			Map<String, String> newMap = new HashMap<String, String>();
			if (shape.getProperties() != null)
				for (Map.Entry<String, Double> entry : shape.getProperties().entrySet()) {
					newMap.put(entry.getKey().toString(), entry.getValue().toString());
				}
			newMap.put("id", shapeName + freqOfShapes.get(shapeName));
			arrayListofShapeMap.add(newMap);
		}
		try {
			xmlParser.saveToXML(path, arrayListofShapeMap);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void loadXML(String path) {
		File inputXML = new File(path);
		StringBuilder shapesJSONContent = new StringBuilder();
		Scanner in = null;
		try {
			in = new Scanner(inputXML);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (in.hasNextLine()) {
			shapesJSONContent.append(in.nextLine() + "\n");
		}
		ArrayList<Map<String, String>> parsedObj = xmlParser.readXML(new File(path));
		ArrayList<Shape> loadedShapes = new ArrayList<>();
		for (Map<String, String> map : parsedObj) {
			Map<String, Double> tempMap = new HashMap<>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (entry.getKey().toString().equals("id"))
					continue;
				tempMap.put(entry.getKey().toString(), Double.parseDouble(entry.getValue().toString()));
			}
			String tempShapeName = map.get("id");
			String shapeName = "";
			for (int i = 0; i < tempShapeName.length(); i++) {
				if (tempShapeName.charAt(i) >= '0' && tempShapeName.charAt(i) <= '9')
					break;
				shapeName += tempShapeName.charAt(i);
			}
			Shape loadedShape = ShapesFactory.CreateShape(shapeName);
			if (loadedShape != null)
				loadedShape.setProperties(tempMap);
			loadedShapes.add(loadedShape);
		}
		clear();
		shapes.push(loadedShapes);
		notifyObservers();
	}

	private void saveJSON(String path) {
		ArrayList<Map<String, String>> arrayListofShapeMap = new ArrayList<>();
		Map<String, Integer> freqOfShapes = new HashMap<>();
		for (Shape shape : shapes.peek()) {
			// calculation of frequency (for indexing purpose)
			String shapeName = shape.getClass().getSimpleName();
			try {
				freqOfShapes.put(shapeName, freqOfShapes.get(shapeName) + 1);
			} catch (Exception e) {
				freqOfShapes.put(shapeName, 1);
			}
			Map<String, String> newMap = new HashMap<String, String>();
			if (shape.getProperties() != null)
				for (Map.Entry<String, Double> entry : shape.getProperties().entrySet()) {
					newMap.put(entry.getKey().toString(), entry.getValue().toString());
				}
			newMap.put("id", shapeName + freqOfShapes.get(shapeName));
			arrayListofShapeMap.add(newMap);
		}
		String parsedObject = JSONParser.parseArrayOfMapsIntoJSON(arrayListofShapeMap);
		File outputJSON = new File(path);
		try {
			FileWriter pw = new FileWriter(outputJSON);
			pw.write(parsedObject);
			pw.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		JSONParser.parseJSONIntoArrayOfMaps(parsedObject);
	}

	private void loadJSON(String path) {
		File inputJSON = new File(path);
		StringBuilder shapesJSONContent = new StringBuilder();
		Scanner in = null;
		try {
			in = new Scanner(inputJSON);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (in.hasNextLine()) {
			shapesJSONContent.append(in.nextLine() + "\n");
		}
		ArrayList<Map<String, String>> parsedObj = JSONParser.parseJSONIntoArrayOfMaps(shapesJSONContent.toString());
		ArrayList<Shape> loadedShapes = new ArrayList<>();
		for (Map<String, String> map : parsedObj) {
			Map<String, Double> tempMap = new HashMap<>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (entry.getKey().toString().equals("id"))
					continue;
				tempMap.put(entry.getKey().toString(), Double.parseDouble(entry.getValue().toString()));
			}
			String tempShapeName = map.get("id");
			String shapeName = "";
			for (int i = 0; i < tempShapeName.length(); i++) {
				if (tempShapeName.charAt(i) >= '0' && tempShapeName.charAt(i) <= '9')
					break;
				shapeName += tempShapeName.charAt(i);
			}
			Shape loadedShape = ShapesFactory.CreateShape(shapeName);
			if (loadedShape != null)
				loadedShape.setProperties(tempMap);
			loadedShapes.add(loadedShape);
		}
		clear();
		shapes.push(loadedShapes);
		notifyObservers();
	}

	@Override
	public void notifyObservers() {
		for (Observer x : observers) {
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
		if (index >= 0) {
			observers.remove(observer);
		}
	}

	@Override
	public void notifyObserversSelection() {
		for (Observer x : observers) {
			x.updateSelected();
		}
	}

	public void updateSelectedShapes(Color fill_color, Color color) {
		redoShapes = new Stack<>();
		if (shapes.size() <= 20)
			shapes.push(new ArrayList<Shape>(shapes.peek()));
		else {
			shapes.remove(0);
			shapes.push(new ArrayList<Shape>(shapes.peek()));
		}
		for (int i = 0; i < getShapes().length; i++) {
			if (getShapes()[i].getProperties().get("selected").intValue() == 1) {
				try {
					shapes.peek().set(i, (Shape) shapes.peek().get(i).clone());
				} catch (CloneNotSupportedException e) {
					e.printStackTrace();
				}
				shapes.peek().get(i).setFillColor(fill_color);
				shapes.peek().get(i).setColor(color);
			}
		}
		notifyObservers();
	}

	public void updateMoveResize() {
		if (shapes.size() <= 20)
			shapes.push(new ArrayList<Shape>(shapes.peek()));
		else {
			shapes.remove(0);
			shapes.push(new ArrayList<Shape>(shapes.peek()));
		}
	}

	public int getBuiltInShapes() {
		return builtInShapes;
	}

	public void reset() {
		clear();
		shapes.push(new ArrayList<>());
		notifyObservers();
	}
}
