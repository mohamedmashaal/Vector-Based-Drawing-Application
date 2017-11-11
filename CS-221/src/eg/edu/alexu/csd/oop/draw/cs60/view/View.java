package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

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
    private JMenu PlugninMenu;
    private JMenuItem importMenuItem;
    private JMenu helpMenu;
    private JMenuItem aboutMenuItem;
    private JMenuItem contentsMenuItem;
    private ShapeList<Shape> shapesList;
    private JScrollPane jScrollPane1;
    private StrokeSlider strokeSlider;
    private JLabel strokeLabel ;
    private ColorPicker colorPicker ;
	private JPanel leftContainer ;
	
    
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
        createColorSlider();
        createLeftContainer();
        setupMainWindow();
    }
    
    private void createLeftContainer() {
    	leftContainer = new JPanel();
    	javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(leftContainer);
        leftContainer.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(strokeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(strokeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addComponent(colorPicker, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(colorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(strokeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(strokeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
        );
		
	}

	private void createColorSlider() {
		colorPicker = new ColorPicker(this);
		strokeSlider = new StrokeSlider(this);
		strokeLabel = new JLabel("Storke");
		strokeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		strokeLabel.setFocusable(false);

		strokeSlider.setValue(20);
		strokeSlider.addChangeListener(changeEvent -> {
            controller.changeStroke(strokeSlider.getValue()/10.0);
            System.out.println(strokeSlider.getValue()/10.0);
        });

	}

	private void setupMainWindow() {
    	 mainWindow.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         mainWindow.setJMenuBar(menuBar);
         setupMainWindowLayout();
         mainWindow.pack();
         mainWindow.setVisible(true);
	}

    
    private void setupMainWindowLayout() {
    	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(mainWindow.getContentPane());
        mainWindow.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(leftContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                    .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(leftContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
	}

	private void setupShapesList() {
    	jScrollPane1 = new javax.swing.JScrollPane();
        shapesList = new ShapeList<>(this);
        jScrollPane1.setViewportView(shapesList);
	}

	private void setupBtnContainer() {
    	btnContainer = new javax.swing.JPanel();
    	btnContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    	for(CustomButton x : btnList) {
    		btnContainer.add(x);
    	}
    	//btnContainer.revalidate();
    	/*javax.swing.GroupLayout btnContainerLayout = new javax.swing.GroupLayout(btnContainer);
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
        );*/
		
	}

	private void setupCanvas() {
    	canvas = new Canvas(this);
    	/*javax.swing.GroupLayout canvasLayout = new javax.swing.GroupLayout(canvas);
    	canvasLayout.setHorizontalGroup(
    			canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 668, Short.MAX_VALUE)
            );
    	canvasLayout.setVerticalGroup(
            	canvasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGap(0, 535, Short.MAX_VALUE)
            );*/
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
        PlugninMenu = new javax.swing.JMenu();
        importMenuItem = new JMenuItem();
        
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
        deleteMenuItem.setText("Delete Shape");
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
        PlugninMenu.setMnemonic('p');
        PlugninMenu.setText("Plugins");
        
        importMenuItem.setText("Import");
        importMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,ActionEvent.CTRL_MASK));
        importMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PluginImporter(getView());
			}
		});
        PlugninMenu.add(importMenuItem);
        
        menuBar.add(PlugninMenu);
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
	
	public Frame getMainWindow() {
		return mainWindow;
	}
	public ColorPicker getColorPicker() {
		return colorPicker;
	}
	
	private View getView() {
		return this;
	}

	@Override
	public void updateSupportedShapes(Class<? extends Shape> shape) {
		// TODO Auto-generated method stub
		CustomButton plugin = new CustomButton(shape.getName(), this);
		btnList.add(plugin);
		btnContainer.add(plugin);
		btnContainer.revalidate();
	}

}