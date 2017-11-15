package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eg.edu.alexu.csd.oop.draw.Shape;

public class EditPluginShape extends CreateDialogue{
	public EditPluginShape(Shape shape, String title, View view, ArrayList<String> filters) {
		super(shape, title, view, filters);
	}

	@Override
	public void addPosition() {
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Position - X");
		JTextField textfield = new JTextField(new Double(get_Shape().getPosition().getX()).toString() , 5);
		getLabels().add(label);
		panel.add(label);
		getTextFields().add(textfield);
		panel.add(textfield);
		getContentPane().add(panel);
		panel = new JPanel();
		label = new JLabel("Position - Y");
		textfield = new JTextField(new Double(get_Shape().getPosition().getY()).toString() , 5);
		getLabels().add(label);
		panel.add(label);
		getTextFields().add(textfield);
		panel.add(textfield);
		getContentPane().add(panel);
	}

	@Override
	public void addButtonListener() {
		Shape newShape = null;
		try {
			newShape = (Shape) get_Shape().clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		int i = 2 ;
		if(newShape != null) {
			get_Shape().setPosition(new Point(new Double(Double.parseDouble(getTextFields().get(0).getText())).intValue(),new Double(Double.parseDouble(getTextFields().get(1).getText())).intValue()));
			for(JLabel x : getLabels()) {
				newShape.getProperties().put(x.getText(), Double.parseDouble(getTextFields().get(i).getText()));
				i++ ;
			}
		}
		newShape.setColor(getColor());
		newShape.setFillColor(getFill_color());
		getView().getController().updateShape(get_Shape() ,newShape);
		dispose();
	}

	@Override
	public void setupKeySetandShape() {
		setSet(get_Shape().getProperties().keySet());
	}

}
