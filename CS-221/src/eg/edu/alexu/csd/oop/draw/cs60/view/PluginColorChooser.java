package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PluginColorChooser extends ColorChoser {
	CreateDialoguePlugin dialogue ;
	public PluginColorChooser(View view ,CreateDialoguePlugin dialogue) {
		super(view);
		this.dialogue = dialogue;
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
				dialogue.setColor(getColor());
				dialogue.setFillColor(getFill_color());
				dispose();
			}
		});
	}
}
