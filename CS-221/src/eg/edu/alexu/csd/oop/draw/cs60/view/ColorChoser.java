package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.border.LineBorder;

public class ColorChoser extends JDialog {
	private JButton select;
	private JButton cancel;
	private JButton selectFillColor;
	private JButton selectBorderColor;
	private ColorPicker preview;
	private JColorChooser colorChooser;
	private View view;
	private Color color;
	private Color fill_color;

	public ColorChoser(View view) {
		super(view.getMainWindow(), false);
		this.view = view;
		color = view.getController().getColor();
		fill_color = view.getController().getFill_color();
		initElements();
		addColorChooserListeners();
		setupColorChoser();
	}

	public ColorChoser(View view, boolean modal) {
		super(view.getMainWindow(), modal);
		this.view = view;
		color = view.getController().getColor();
		fill_color = view.getController().getFill_color();
		initElements();
		addColorChooserListeners();
		addSelectCancelListeners();
		setupColorChoser();
	}

	private void addSelectCancelListeners() {
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getController().setColor(color);
				view.getController().setFill_color(fill_color);
				dispose();
			}
		});
	}

	private void addColorChooserListeners() {
		selectFillColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fill_color = colorChooser.getColor();
				preview.setBackground(fill_color);
			}
		});
		selectBorderColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				color = colorChooser.getColor();
				preview.setBorder(new LineBorder(color, view.getController().getStroke()));
			}
		});
	}

	private void initElements() {
		colorChooser = new JColorChooser(Color.RED);
		preview = new ColorPicker(view);
		preview.setEnabled(false);
		cancel = new JButton("Cancel");
		select = new JButton("Select");
		selectFillColor = new JButton("Select Color");
		selectBorderColor = new JButton("Select Border");
	}

	private void setupColorChoser() {
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(colorChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 648,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createSequentialGroup().addGap(160, 160, 160)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(selectFillColor).addComponent(selectBorderColor))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(preview, javax.swing.GroupLayout.PREFERRED_SIZE, 69,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(141, 141, 141).addComponent(cancel).addGap(7, 7, 7).addComponent(select)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addComponent(colorChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 347,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGap(7, 7, 7)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addComponent(selectFillColor).addGap(7, 7, 7)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(selectBorderColor).addComponent(cancel).addComponent(select)))
						.addGroup(layout.createSequentialGroup()
								.addComponent(preview, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
								.addContainerGap()))));

		pack();
		setVisible(true);
	}

	public JButton getSelect() {
		return select;
	}

	public JButton getCancel() {
		return cancel;
	}

	public Color getColor() {
		return color;
	}

	public Color getFill_color() {
		return fill_color;
	}

}
