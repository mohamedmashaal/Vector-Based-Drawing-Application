package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class CustomButton extends JButton {
	private Color pressedColor = Color.RED;
	private Color normalColor = Color.BLUE;
	private Boolean state = false;

	public CustomButton(final View view, String text) {
		super(text);
		setNormalColor(view.getController().getColor());
		setPressedColor(view.getController().getFill_color());
		setBorderPainted(false);
		setFocusPainted(false);
		setContentAreaFilled(false);
		setOpaque(true);
		setBackground(normalColor);
		setForeground(Color.WHITE);
		setFont(new Font("Tahoma", Font.BOLD, 12));
	}

	public CustomButton(String text, final View view) {
		super(text);
		setNormalColor(view.getController().getColor());
		setPressedColor(view.getController().getFill_color());
		setBorderPainted(false);
		setFocusPainted(false);

		setContentAreaFilled(false);
		setOpaque(true);

		setBackground(normalColor);
		setForeground(Color.WHITE);
		setFont(new Font("Tahoma", Font.BOLD, 12));
		setText(text);

		addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!state) {
					for (CustomButton x : view.getBtnList()) {
						x.resetState();
					}
					view.setActiveBtns(0);
					view.setActiveBtns(view.getActiveBtns() + 1);
					state = true;
					setBackground(pressedColor);
				} else {
					if (state)
						view.setActiveBtns(view.getActiveBtns() - 1);
					state = false;
					setBackground(normalColor);
				}
			}
		});
	}

	public void setPressedColor(Color pressedColor) {
		this.pressedColor = pressedColor;
		if (state) {
			this.setBackground(pressedColor);
		}
	}

	public void setNormalColor(Color normalColor) {
		this.normalColor = normalColor;
		if (!state) {
			this.setBackground(normalColor);
		}
	}

	public Boolean getState() {
		return state;
	}

	public void resetState() {
		this.state = false;
		setBackground(normalColor);
	}
}