package eg.edu.alexu.csd.oop.draw.cs60.view;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

public class SaveChooser extends JFileChooser {

	View view;
	File fileLoaded;

	JRadioButtonMenuItem xmlBtn = new JRadioButtonMenuItem("xml");
	JRadioButtonMenuItem jsonBtn = new JRadioButtonMenuItem("xml");

	public SaveChooser(View view) {
		super();
		this.view = view;
		setSelectedFile(new File("drawing"));
		setAcceptAllFileFilterUsed(false);
		addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File file) {
				if (file.isDirectory())
					return true;
				if (file.getName().endsWith(".xml")) {
					fileLoaded = file;
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				return ".xml";
			}

		});
		addChoosableFileFilter(new FileFilter() {
			@Override
			public boolean accept(File file) {
				if (file.isDirectory())
					return true;
				if (file.getName().endsWith(".json")) {
					fileLoaded = file;
					return true;
				}
				return false;
			}

			@Override
			public String getDescription() {
				return ".json";
			}

		});

		int returnVal = showDialog(view.getMainWindow(), "Save");
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = new File(getCurrentDirectory().getPath() + "/" + getSelectedFile().getName()
					+ getFileFilter().getDescription());
			view.getModel().save(file.getPath());
		}
	}

}
