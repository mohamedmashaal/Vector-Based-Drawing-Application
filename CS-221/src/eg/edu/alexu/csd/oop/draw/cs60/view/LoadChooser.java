package eg.edu.alexu.csd.oop.draw.cs60.view;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class LoadChooser extends JFileChooser {
	View view;
	File fileLoaded;

	public LoadChooser(View view) {
		super();
		this.view = view;
		setAcceptAllFileFilterUsed(false);
		addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File file) {
				if (file.isDirectory())
					return true;
				if (file.getName().endsWith(".json") || file.getName().endsWith(".xml")) {
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				return ".xml, .json";
			}
		});
		int returnVal = showDialog(view.getMainWindow(), "Load");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			fileLoaded = getSelectedFile();
			view.getController().load(fileLoaded.getPath());
		}
	}

}
