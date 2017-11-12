package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseMotionListener, MouseListener {
	private View view;
	private Point p1;
	private Point p2;
	private float stroke = 0;

	@Override
	protected void paintComponent(Graphics g) {
		//Graphics2D canvas = (Graphics2D) g;
		//canvas.setStroke(new BasicStroke(stroke));
		super.paintComponent(g);
		g.clearRect(0, 0, getWidth(), getHeight());
		view.getModel().setStroke(stroke);
		view.getModel().refresh(g);
	}

	public Canvas(View view) {
		super();
		this.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 5, true));
		this.view = view;
		setBackground(new java.awt.Color(255, 255, 255));
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (view.getActiveBtns() == 1) {
			p1 = e.getPoint();
			view.getController().draw(p1, p1);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (view.getActiveBtns() == 1) {
			p2 = e.getPoint();
			if (p1.equals(p2)) {
				view.getController().removeCurrentDraw();
			} else {
				view.getController().removeCurrentDraw();
				view.getController().draw(p1, p2);
			}
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if (view.getActiveBtns() == 1) {
			p2 = e.getPoint();
			view.getController().dragDraw(p1, p2);
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void setStroke(float value) {
		this.stroke = value;
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return super.getWidth();
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return super.getHeight();
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return super.getGraphics();
	}

}
