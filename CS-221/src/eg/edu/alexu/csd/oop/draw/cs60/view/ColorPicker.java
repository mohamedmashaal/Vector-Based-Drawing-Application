package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.LineBorder;

public class ColorPicker extends JButton {
	private View view ;
	public ColorPicker([final View view) {
		this.view = view ;
        setFocusPainted(false);
        setContentAreaFilled(false);
        setOpaque(true);

        setBackground(view.getController().getFill_color());
        setBorder(javax.swing.BorderFactory.createLineBorder(view.getController().getColor(), 5));

        addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new ColorChoser(view, true);
				}
		});
	}
}
