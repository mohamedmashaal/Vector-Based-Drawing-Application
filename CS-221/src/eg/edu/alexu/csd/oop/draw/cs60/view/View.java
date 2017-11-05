package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

import eg.edu.alexu.csd.oop.draw.Observer;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.controller.Controller;
import eg.edu.alexu.csd.oop.draw.cs60.model.DrawEngineImp;

public class View implements Observer{
	private DrawEngineImp model ;

	private Controller controller;
	
	private ArrayList<CustomButton> btnList ;
	private Integer activeBtns = 0 ;
	
	private JFrame mainWindow ;
	private CustomButton circleButton;
	private CustomButton ellipseButton;
	private CustomButton lineButton;
	private CustomButton rectButton;
	private CustomButton squareButton;
	private CustomButton triangleButton;
	private JPanel canvas;
	private JPanel btnContainer;
	private JMenuBar menuBar;
    private JMenu editMenu;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;
    private JMenuItem deleteMenuItem;
    private JMenu fileMenu;
    private JMenuItem loadMenuItem;
    private JMenuItem saveAsMenuItem;
    private JMenuItem saveMenuItem;
    private JMenuItem exitMenuItem;
    private JMenu helpMenu;
    private JMenuItem aboutMenuItem;
    private JMenuItem contentsMenuItem;
    private ShapeList<Shape> shapesList;
    private JScrollPane jScrollPane1;
	
    
    public View(Controller controller , DrawEngineImp model) {
    	this.model = model;
    	this.controller = controller;
    }
    
    public void createView() {
    	mainWindow = new JFrame("Vector Paint");
    	setupCanvas();
    	createBtns();
    	setupBtnContainer();
    	setupShapesList();
        createMenuBar();
        setupMainWindow();
    }
    
    private void setupMainWindow() {
    	 mainWindow.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         mainWindow.setJMenuBar(menuBar);
         setupMainWindowLayout();
         mainWindow.pack();
         mainWindow.setVisible(true);
	}

	public void createControls() {
    	
    }
    
    private void setupMainWindowLayout() {
    	javax.swing.GroupLayout mainWindowLayout = new javax.swing.GroupLayout(mainWindow.getContentPane());
        mainWindow.getContentPane().setLayout(mainWindowLayout);
        mainWindowLayout.setHorizontalGroup(
            mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainWindowLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainWindowLayout.setVerticalGroup(
            mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainWindowLayout.createSequentialGroup()
                .addComponent(btnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(mainWindowLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE))
                .addContainerGap())
        );	
	}

	private void setupShapesList() {
    	jScrollPane1 = new javax.swing.JScrollPane();
        shapesList = new ShapeList<>(this);
        /*shapesList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });*/
        jScrollPane1.setViewportView(shapesList);
	}

	private void setupBtnContainer() {
    	btnContainer = new javax.swing.JPanel();
    	javax.swing.GroupLayout btnContainerLayout = new javax.swing.GroupLayout(btnContainer);
        btnContainer.setLayout(btnContainerLayout);
        btnContainerLayout.setHorizontalGroup(
            btnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btnContainerLayout.createSequentialGroup()
                .addComponent(circleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ellipseButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lineButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rectButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(squareButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(triangleButton)
                .addGap(0, 217, Short.MAX_VALUE))
        );
        btnContainerLayout.setVerticalGroup(
            btnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(circleButton, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
            .addComponent(ellipseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lineButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(rectButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(squareButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(triangleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
		
	}

	private void setupCanvas() {
    	canvas = new Canvas(this);
    	javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(canvas);
        canvas.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
	}

	private void createMenuBar() {
    	menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        loadMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        redoMenuItem = new javax.swing.JMenuItem();
        undoMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();
        
        fileMenu.setMnemonic('f');
        fileMenu.setText("File");

        loadMenuItem.setMnemonic('o');
        loadMenuItem.setText("Load");
        loadMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.load();
			}
		});
        fileMenu.add(loadMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Save");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.save();
			}
		});
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Save As ...");
        saveAsMenuItem.setDisplayedMnemonicIndex(5);
        fileMenu.add(saveAsMenuItem);

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Exit");
        
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Edit");

        
        undoMenuItem.setMnemonic('u');
        undoMenuItem.setText("Undo");
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,ActionEvent.CTRL_MASK));
        undoMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
        editMenu.add(undoMenuItem);
        
        redoMenuItem.setMnemonic('r');
        redoMenuItem.setText("Redo");
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,ActionEvent.CTRL_MASK));
        redoMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
        editMenu.add(redoMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Dlete Shape");
        deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
        deleteMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
        editMenu.add(deleteMenuItem);
        
        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);
	}

	private void createBtns() {
		btnList = new ArrayList<>();
        circleButton = new CustomButton("Circle" , this);
        btnList.add(circleButton);
        ellipseButton = new CustomButton("Ellipse", this);
        btnList.add(ellipseButton);
        lineButton = new CustomButton("Line" , this );
        btnList.add(lineButton);
        rectButton = new CustomButton("Rectangle" , this);
        btnList.add(rectButton);
        squareButton = new CustomButton("Square" , this);
        btnList.add(squareButton);
        triangleButton = new CustomButton("Triangle" , this);
        btnList.add(triangleButton);
	}

	public JPanel getCanvas() {
		return canvas;
	}
    
	public DrawEngineImp getModel() {
		return model;
	}
	
	public Controller getController() {
		return controller;
	}
	
	public Integer getActiveBtns() {
		return activeBtns;
	}

	public void setActiveBtns(Integer activeBtns) {
		this.activeBtns = activeBtns ;
	}

	public ArrayList<CustomButton> getBtnList() {
		// TODO Auto-generated method stub
		return btnList;
	}
	
	public ShapeList<Shape> getShapeList() {
		// TODO Auto-generated method stub
		return shapesList;
	}

	@Override
	public void update() {
		shapesList.update(model.getShapes());
		canvas.repaint();
	}

	@Override
	public void updateSelected() {
		canvas.repaint();
	}
    
}