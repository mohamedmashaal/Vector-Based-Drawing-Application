package eg.edu.alexu.csd.oop.draw;

public interface MinorShape {
	public java.awt.Point[] getBonds();

	public boolean isSelected();

	public void setSelected(boolean selected);

	public void drawBonds(java.awt.Graphics canvas);
}
