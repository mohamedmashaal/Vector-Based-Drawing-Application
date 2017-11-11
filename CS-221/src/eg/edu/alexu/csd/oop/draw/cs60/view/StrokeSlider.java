package eg.edu.alexu.csd.oop.draw.cs60.view;

import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class StrokeSlider extends JSlider {
	private View view ;
	public StrokeSlider(View view) {
		super();
		this.view = view;
	}

	public void changeStroke(float value){
		//(Graphics2D)(view.getCanvas().getGraphics());
	}
}
