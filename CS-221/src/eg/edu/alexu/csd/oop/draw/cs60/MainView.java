package eg.edu.alexu.csd.oop.draw.cs60;

import java.awt.*;
import javax.swing.*;

public class MainView extends JApplet{
    
    
    public void init() {
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);
    }
    
    public void start() {
        initComponents();
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame("Weather Wizard");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JApplet ap = new JApplet(); 
        //ap.init();
        //ap.start();
        f.setBackground(Color.BLACK);
        f.setSize(500, 500);
        f.add("Center", ap);
        f.pack();
        f.setVisible(true);
        
    }
    
    public void initComponents() {
        setLayout(new BorderLayout());
    }
}