package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.model.MainShape;

public class EditBuiltInDialogue extends CreateDialogue {
	public EditBuiltInDialogue(Shape shape, String title, View view, ArrayList<String> filters) {
		super(shape, title, view, filters);
	}

	@Override
	public void addPosition() {
	}

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
				int i = 0;
				if (newShape != null)
					for (JLabel x : getLabels()) {
						newShape.getProperties().put(x.getText(), Double.parseDouble(getTextFields().get(i).getText()));
						i++;
					}
				newShape.setColor(getColor());
				newShape.setFillColor(getFill_color());
				updateBonds(newShape);
				getView().getController().updateShape(get_Shape(), newShape);
				dispose();
			}
		});
	}

	private void updateBonds(Shape shape) {
		MainShape newShape = (MainShape) shape;
		newShape.getProperties().put("bond_1_x", newShape.getBonds()[0].getX());
		newShape.getProperties().put("bond_1_y", newShape.getBonds()[0].getY());
		newShape.getProperties().put("bond_2_x", newShape.getBonds()[1].getX());
		newShape.getProperties().put("bond_2_y", newShape.getBonds()[1].getY());
		newShape.getProperties().put("bond_3_x", newShape.getBonds()[2].getX());
		newShape.getProperties().put("bond_3_y", newShape.getBonds()[2].getY());
		newShape.getProperties().put("bond_4_x", newShape.getBonds()[3].getX());
		newShape.getProperties().put("bond_4_y", newShape.getBonds()[3].getY());
	}

	@Override
	public void setupKeySetandShape() {
		setSet(get_Shape().getProperties().keySet());
	}

}