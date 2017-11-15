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

public class CreateDialogue extends JDialog {
	private View view ;
	private String class_text ;
	private Shape shape ;
	private Set<String> set ;
	private ArrayList<JLabel> labels ;
	private ArrayList<JTextField> textFields ;
	private JButton draw ;
	private JButton cancel ;
	private Color color ;
	private Color fill_color ;
	
	public CreateDialogue(View view,String text) {
		super(view.getMainWindow() , text);
		this.view = view ;
		class_text = text ;
		color = view.getController().getColor();
		fill_color = view.getController().getFill_color();
		getandCreateInstance();
		createView();
	}
	private void createView() {
		getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));
		labels = new ArrayList<>();
		textFields = new ArrayList<>();
		JPanel panel = new JPanel();
		JLabel label = new JLabel("Position - X");
		JTextField textfield = new JTextField(5);
		labels.add(label);
		panel.add(label);
		textFields.add(textfield);
		panel.add(textfield);
		getContentPane().add(panel);
		panel = new JPanel();
		label = new JLabel("Position - Y");
		textfield = new JTextField(5);
		labels.add(label);
		panel.add(label);
		textFields.add(textfield);
		panel.add(textfield);
		getContentPane().add(panel);
		for(String x : set) {
			panel = new JPanel();
			label = new JLabel(x);
			labels.add(label);
			panel.add(label);
			textfield = new JTextField(5);
			textFields.add(textfield);
			panel.add(textfield);
			getContentPane().add(panel);
		}
		JButton colorChooser = new JButton("Color Chooser");
		colorChooser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PluginColorChooser(view, getThis());
			}
		});
		getContentPane().add(new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT)).add(colorChooser));
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		draw = new JButton("Draw");
		draw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shape.setPosition(new Point(new Double(Double.parseDouble(textFields.get(0).getText())).intValue(),new Double(Double.parseDouble(textFields.get(1).getText())).intValue()));
				shape.setColor(color);
				shape.setFillColor(fill_color);
				int i = 2 ;
				for(String x : set) {
					shape.getProperties().put(x,Double.parseDouble(textFields.get(i).getText()));
					i++;
				}
				view.getController().draw(shape);
				dispose();
			}
		});
		panel = new JPanel();
		panel.add(cancel);
		panel.add(draw);
		getContentPane().add(panel);
		pack();
		setVisible(true);
	}
	private void getandCreateInstance() {
		shape = ShapesFactory.CreateShape(class_text);
		set = shape.getProperties().keySet();
	}
	public void setColor(Color color) {
		this.color = color ;
	}
	public void setFillColor(Color fill_color) {
		this.fill_color = fill_color ;
	}
	private CreateDialogue getThis() {
		return this ;
	}
}
