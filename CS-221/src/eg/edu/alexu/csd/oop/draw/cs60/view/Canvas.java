package eg.edu.alexu.csd.oop.draw.cs60.view;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseMotionListener, MouseListener {
	private View view ;
	private Point p1 ;
	private Point p2 ;
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
	}
	
	public Canvas(View view) {
		super();
		this.view = view ;
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
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("I was here");
		if(view.getActiveBtns() == 1) {
			System.out.println(p1);
			p1 = getMousePosition() ;
			view.getController().draw(p1 , p1);
			}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		if(view.getActiveBtns() == 1) {
		p2 = getMousePosition();
		getGraphics().clearRect(getX(), getY(), getWidth(), getHeight());
		view.getController().draw(p1 , p2);
		repaint();}
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return super.getX();
	}
	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return super.getY();
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
