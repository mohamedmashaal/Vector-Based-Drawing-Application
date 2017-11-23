package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import eg.edu.alexu.csd.oop.draw.Observer;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.cs60.controller.Controller;
import eg.edu.alexu.csd.oop.draw.cs60.model.DrawEngineImp;

public class View implements Observer {
	private DrawEngineImp model;

	private Controller controller;

	private ArrayList<CustomButton> btnList;
	private Integer activeBtns = 0;

	private JFrame mainWindow;
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
	private JMenuItem removeClipBoardMenuItem;
	private JMenuItem copyMenuItem;
	private JMenuItem pasteMenuItem;
	private JMenu fileMenu;
	private JMenuItem newMenuItem;
	private JMenuItem loadMenuItem;
	private JMenuItem saveMenuItem;
	private JMenuItem exitMenuItem;
	private JMenu PlugninMenu;
	private JMenuItem importMenuItem;
	private JMenu AboutMenu;
	private JMenuItem aboutMenuItem;
	private ShapeList<Shape> shapesList;
	private JScrollPane jScrollPane1;
	private StrokeSlider strokeSlider;
	private JLabel strokeLabel;
	private ColorPicker colorPicker;
	private JPanel leftContainer;

	public View(Controller controller, DrawEngineImp model) {
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
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
				.addComponent(strokeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
				.addComponent(colorPicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						Short.MAX_VALUE)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
						jPanel2Layout.createSequentialGroup().addContainerGap()
								.addComponent(strokeLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addComponent(colorPicker, javax.swing.GroupLayout.PREFERRED_SIZE, 72,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(11, 11, 11)
						.addComponent(strokeSlider, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(strokeLabel)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)));

	}

	private void createColorSlider() {
		colorPicker = new ColorPicker(this);
		strokeSlider = new StrokeSlider(this);
		strokeLabel = new JLabel("Stroke");
		strokeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		strokeLabel.setFocusable(false);
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
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(leftContainer, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(btnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
								.addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				layout.createSequentialGroup().addContainerGap().addGroup(layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(btnContainer, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(canvas, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addComponent(leftContainer, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap()));
	}

	private void setupShapesList() {
		jScrollPane1 = new javax.swing.JScrollPane();
		shapesList = new ShapeList<>(this);
		jScrollPane1.setViewportView(shapesList);
	}

	private void setupBtnContainer() {
		btnContainer = new javax.swing.JPanel();
		btnContainer.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		for (CustomButton x : btnList) {
			btnContainer.add(x);
		}
	}

	private void setupCanvas() {
		canvas = new Canvas(this);
	}

	private void createMenuBar() {
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		newMenuItem = new javax.swing.JMenuItem();
		loadMenuItem = new javax.swing.JMenuItem();
		saveMenuItem = new javax.swing.JMenuItem();
		exitMenuItem = new javax.swing.JMenuItem();
		editMenu = new javax.swing.JMenu();
		redoMenuItem = new javax.swing.JMenuItem();
		undoMenuItem = new javax.swing.JMenuItem();
		deleteMenuItem = new JMenuItem();
		removeClipBoardMenuItem = new JMenuItem();
		copyMenuItem = new JMenuItem();
		pasteMenuItem = new JMenuItem();
		AboutMenu = new javax.swing.JMenu();
		aboutMenuItem = new javax.swing.JMenuItem();
		PlugninMenu = new javax.swing.JMenu();
		importMenuItem = new JMenuItem();

		fileMenu.setMnemonic('f');
		fileMenu.setText("File");
		
		newMenuItem.setMnemonic('n');
		newMenuItem.setText("New");
		newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		newMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getController().reset();
			}
		});
		fileMenu.add(newMenuItem);
		
		loadMenuItem.setMnemonic('o');
		loadMenuItem.setText("Load");
		loadMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
		loadMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new LoadChooser(getView());
			}
		});
		fileMenu.add(loadMenuItem);

		saveMenuItem.setMnemonic('s');
		saveMenuItem.setText("Save");
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new SaveChooser(getView());
			}
		});
		fileMenu.add(saveMenuItem);

		exitMenuItem.setMnemonic('x');
		exitMenuItem.setText("Exit");
		exitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				System.exit(0);
			}
		});

		fileMenu.add(exitMenuItem);

		menuBar.add(fileMenu);

		editMenu.setMnemonic('e');
		editMenu.setText("Edit");

		undoMenuItem.setMnemonic('u');
		undoMenuItem.setText("Undo");
		undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, ActionEvent.CTRL_MASK));
		undoMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.undo();
			}
		});
		editMenu.add(undoMenuItem);

		redoMenuItem.setMnemonic('r');
		redoMenuItem.setText("Redo");
		redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, ActionEvent.CTRL_MASK));
		redoMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.redo();
			}
		});
		editMenu.add(redoMenuItem);

		deleteMenuItem.setMnemonic('d');
		deleteMenuItem.setText("Delete Shape(s)");
		deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		deleteMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.delete();
			}
		});
		editMenu.add(deleteMenuItem);

		copyMenuItem.setMnemonic('c');
		copyMenuItem.setText("Copy Shape(s)");
		copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.SHIFT_MASK));
		copyMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.copy();
			}
		});
		editMenu.add(copyMenuItem);

		pasteMenuItem.setMnemonic('v');
		pasteMenuItem.setText("Paste Shape(s)");
		pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.SHIFT_MASK));
		pasteMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.paste();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
			}
		});
		editMenu.add(pasteMenuItem);

		removeClipBoardMenuItem.setMnemonic('l');
		removeClipBoardMenuItem.setText("Empty Clipboard");
		removeClipBoardMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.removeClipBoard();
			}
		});
		editMenu.add(removeClipBoardMenuItem);

		menuBar.add(editMenu);
		PlugninMenu.setMnemonic('p');
		PlugninMenu.setText("Plugins");

		importMenuItem.setText("Import");
		importMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		importMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PluginImporter(getView());
			}
		});
		PlugninMenu.add(importMenuItem);

		menuBar.add(PlugninMenu);

		AboutMenu.setMnemonic('h');
		AboutMenu.setText("Help");

		aboutMenuItem.setMnemonic('a');
		aboutMenuItem.setText("About");
		aboutMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutDialogue(getView());
			}
		});
		AboutMenu.add(aboutMenuItem);
		menuBar.add(AboutMenu);
	}

	private void createBtns() {
		btnList = new ArrayList<>();
		circleButton = new CustomButton("Circle", this);
		btnList.add(circleButton);
		ellipseButton = new CustomButton("Ellipse", this);
		btnList.add(ellipseButton);
		lineButton = new CustomButton("Line", this);
		btnList.add(lineButton);
		rectButton = new CustomButton("Rectangle", this);
		btnList.add(rectButton);
		squareButton = new CustomButton("Square", this);
		btnList.add(squareButton);
		triangleButton = new CustomButton("Triangle", this);
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
		this.activeBtns = activeBtns;
	}

	public ArrayList<CustomButton> getBtnList() {
		return btnList;
	}

	public ShapeList<Shape> getShapeList() {
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
	public void updateSupportedShapes() {
		List<Class<? extends Shape>> supported = getModel().getSupportedShapes();
		for (int i = model.getBuiltInShapes(); i < supported.size(); i++) {
			if (i > btnList.size() - 1) {
				PluginButton plugin = new PluginButton(this, supported.get(i).getSimpleName());
				btnList.add(plugin);
				btnContainer.add(plugin);
				btnContainer.revalidate();
			}
		}
	}

}