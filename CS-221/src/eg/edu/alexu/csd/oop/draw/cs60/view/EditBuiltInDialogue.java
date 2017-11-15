package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.ShapesFactory;

public class EditBuiltInDialogue extends CreateDialogue{
	public EditBuiltInDialogue(Shape shape ,String title, View view , ArrayList<String> filters ) {
		super(shape, title, view, filters);
	}

	@Override
	public void addPosition() {}

	@Override
	public void addButtonListener() {
		getDraw().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Shape newShape = null;
				try {
					newShape = (Shape) get_Shape().clone();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				int i = 0 ;
				if(newShape != null)
				for(JLabel x : getLabels()) {
					System.out.println("Property :" + x.getText());
					newShape.getProperties().put(x.getText(), Double.parseDouble(getTextFields().get(i).getText()));
					i++ ;
				}
				newShape.setColor(getColor());
				newShape.setFillColor(getFill_color());
				getView().getController().updateShape(get_Shape() ,newShape);
				dispose();
			}
		});
	}

	@Override
	public void setupKeySetandShape() {
		setSet(get_Shape().getProperties().keySet());
	}
	
}