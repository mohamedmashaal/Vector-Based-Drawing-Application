package eg.edu.alexu.csd.oop.draw.cs60.view;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SaveChooser extends JFileChooser {

    View view;
    File fileLoaded;

    JRadioButtonMenuItem xmlBtn = new JRadioButtonMenuItem("xml");
    JRadioButtonMenuItem jsonBtn = new JRadioButtonMenuItem("xml");

    public SaveChooser(View view){
        super();
        this.view = view;

        //TextField tf = (TextField) getComponent(1);

        /*for(Component c : getComponents()){
            System.out.println(c);
            for(Component d : getComponents()){
                System.out.println(d);

            }
            System.out.println();
        }*/

        setSelectedFile(new File("drawing"));

        setAcceptAllFileFilterUsed(false);
        addChoosableFileFilter(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if(file.isDirectory())
                    return true;
                if(file.getName().endsWith(".xml")) {
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
                if(file.isDirectory())
                    return true;
                if(file.getName().endsWith(".json")) {
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
            File file = new File( getCurrentDirectory().getPath() + "/" + getSelectedFile().getName() + getFileFilter().getDescription());
            view.getModel().save(file.getPath());
            System.out.println(file.getPath());
        }
    }

}
