package eg.edu.alexu.csd.oop.draw.cs60.view;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import eg.edu.alexu.csd.oop.draw.Shape;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class ShapeList<T> extends JList<String> {
	View view;

	public ShapeList(final View view) {
		super();
		this.view = view;
		this.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
		setSelectionModel(new DefaultListSelectionModel() {
			public void setSelectionInterval(int index0, int index1) {
				if (index0 == index1) {
					if (isSelectedIndex(index0)) {
						removeSelectionInterval(index0, index0);
						return;
					}
				}
				super.setSelectionInterval(index0, index1);
			}

			@Override
			public void addSelectionInterval(int index0, int index1) {
				if (index0 == index1) {
					if (isSelectedIndex(index0)) {
						removeSelectionInterval(index0, index0);
						return;
					}
					super.addSelectionInterval(index0, index1);
				}
			}

		});

		setListData(new String[0]);
		addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				view.getController().shapeSelected();
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					new ColorChooserDialogue(view);
				}
				super.mousePressed(e);
			}
		});
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_D) {
					view.getController().showEditDialouge(getSelectedIndices());
				} else if (e.getKeyCode() == KeyEvent.VK_F) {
					new ColorChooserDialogue(view);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
	}

	public void update(Shape[] shapes) {
		String[] indexedShapes = new String[shapes.length];
		Map<String, Integer> freqOfShapes = new HashMap<String, Integer>();
		int i = 0;
		for (Shape x : shapes) {
			String shapeName = x.getClass().getSimpleName();
			try {
				freqOfShapes.put(shapeName, freqOfShapes.get(shapeName) + 1);
			} catch (Exception e) {
				freqOfShapes.put(shapeName, 1);
			}
			indexedShapes[i++] = shapeName + freqOfShapes.get(shapeName);
		}
		setListData(indexedShapes);
	}
}
