package eg.edu.alexu.csd.oop.draw.cs60.view;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import eg.edu.alexu.csd.oop.draw.Shape;

public class ShapeList<T> extends JList<Shape> {
	View view ;
	public ShapeList(final View view) {
		super();
		this.view = view ;
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
		setListData(new Shape [0]);
		addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				view.getController().shapeSelected();
			}
		});
	}
	
	public void update(Shape[] shapes) {
		setListData(shapes);
	}
}
