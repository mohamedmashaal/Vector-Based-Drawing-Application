package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

public class PluginImporter extends JFileChooser{
	View view ;
	public PluginImporter(View view) {
		super();
		this.view = view ;
		int returnVal = showOpenDialog(view.getMainWindow());
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			view.getController().imp(getSelectedFile());
		}
	}
	
	
}
