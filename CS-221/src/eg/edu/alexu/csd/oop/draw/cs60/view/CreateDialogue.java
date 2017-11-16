package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Color;
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

public abstract class CreateDialogue extends JDialog {
	private View view;
	private String class_text;
	private Shape shape;
	private Set<String> set;
	private ArrayList<JLabel> labels;
	private ArrayList<JTextField> textFields;
	private JButton draw;
	private JButton cancel;
	private Color color;
	private Color fill_color;
	private ArrayList<String> filters = null;

	public CreateDialogue(View view, String text) {
		super(view.getMainWindow(), text);
		this.view = view;
		class_text = text;
		color = view.getController().getColor();
		fill_color = view.getController().getFill_color();
		setupKeySetandShape();
		createView();
	}

	public CreateDialogue(Shape shape, String title, View view, ArrayList<String> filters) {
		super(view.getMainWindow(), title);
		this.view = view;
		this.shape = shape;
		this.filters = filters;
		color = shape.getColor();
		fill_color = shape.getFillColor();
		setupKeySetandShape();
		createView();
	}

	private void createView() {
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));
		labels = new ArrayList<>();
		textFields = new ArrayList<>();
		JPanel panel = new JPanel();
		JLabel label = new JLabel();
		JTextField textfield = new JTextField();
		addPosition();
		for (String x : set) {
			if (filters != null && filters.contains(x)) {
				continue;
			}
			panel = new JPanel();
			label = new JLabel(x);
			labels.add(label);
			panel.add(label);
			textfield = new JTextField(Double.toString(shape.getProperties().get(x)), 5);
			textFields.add(textfield);
			panel.add(textfield);
			getContentPane().add(panel);
		}
		JButton colorChooser = new JButton("Color Chooser");
		colorChooser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new EditBoxColorChooser(view, getThis());
			}
		});
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		draw = new JButton("Draw");
		addButtonListener();
		panel = new JPanel();
		panel.add(cancel);
		panel.add(colorChooser);
		panel.add(draw);
		getContentPane().add(panel);
		pack();
		setVisible(true);
	}

	public abstract void addPosition();

	public abstract void addButtonListener();

	public abstract void setupKeySetandShape();

	public void setColor(Color color) {
		this.color = color;
	}

	public void setFillColor(Color fill_color) {
		this.fill_color = fill_color;
	}

	private CreateDialogue getThis() {
		return this;
	}

	public JButton getDraw() {
		return draw;
	}

	public ArrayList<JLabel> getLabels() {
		return labels;
	}

	public ArrayList<JTextField> getTextFields() {
		return textFields;
	}

	public Color getColor() {
		return color;
	}

	public Color getFill_color() {
		return fill_color;
	}

	public Shape get_Shape() {
		return shape;
	}

	public Set<String> getSet() {
		return set;
	}

	public void setSet(Set<String> set) {
		this.set = set;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public View getView() {
		return view;
	}

	public String getClass_text() {
		return class_text;
	}
}
