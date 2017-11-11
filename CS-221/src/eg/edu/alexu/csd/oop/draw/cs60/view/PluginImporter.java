package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class PluginImporter extends JFileChooser{
	View view ;
	public PluginImporter(View view) {
		super();
		this.view = view ;
		setAcceptAllFileFilterUsed(false);
		addChoosableFileFilter(new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Just .class Files";
			}
			
			@Override
			public boolean accept(File f) {
				 if (f.isDirectory()) {
				        return true;
				    }
				 if(f.getName().substring(f.getName().length()-5).toLowerCase().equals("class")) {
					 return true;
				 }
				return false;
			}
		});
		int returnVal = showDialog(view.getMainWindow(), "Import");
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			try {
				view.getController().imp(getSelectedFile());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
