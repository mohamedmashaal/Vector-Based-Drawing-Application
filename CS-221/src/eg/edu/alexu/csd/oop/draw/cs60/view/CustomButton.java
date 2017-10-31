package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class CustomButton extends JButton {
    private Color pressedColor = Color.RED;
    private Color normalColor = Color.BLUE;
    private Boolean state = false ;
    
    public CustomButton (String text) {
        super(text);
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
				if(!state) {
					state = true;
					setBackground(pressedColor);
				}
				else {
					state = false;
					setBackground(normalColor);
				}
			}
		});
    }
}