package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ColorPicker extends JButton {

	public ColorPicker(final View view) {
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);

		setBackground(view.getController().getFill_color());
		setBorder(javax.swing.BorderFactory.createLineBorder(view.getController().getColor(),
				view.getController().getStroke()));

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new ColorChoser(view, false);
			}
		});
	}
}
