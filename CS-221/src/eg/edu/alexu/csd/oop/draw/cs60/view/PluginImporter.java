package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.io.File;
import java.io.IOException;
import java.util.jar.JarFile;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class PluginImporter extends JFileChooser {
	View view;

	public PluginImporter(View view) {
		super();
		this.view = view;
		setAcceptAllFileFilterUsed(false);
		addChoosableFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "Just .jar Files";
			}

			@Override
			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				}
				if (f.getName().endsWith("jar")) {
					return true;
				}
				return false;
			}
		});
		int returnVal = showDialog(view.getMainWindow(), "Import");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				view.getController().imp(new JarFile(getSelectedFile()));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
