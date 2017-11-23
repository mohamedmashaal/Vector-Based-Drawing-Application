package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ColorChooserDialogue extends ColorChoser {
	View view;

	public ColorChooserDialogue(View view) {
		super(view);
		this.view = view;
		addSelectCancelListeners();
	}

	private void addSelectCancelListeners() {

		getCancel().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getSelect().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.getController().updateSelectedShapes(getFill_color(), getColor());
				dispose();
			}
		});
	}
}
