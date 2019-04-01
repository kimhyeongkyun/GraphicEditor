package shape;

import java.awt.Graphics;

public abstract class Shape {
	protected int x1, y1, x2, y2;

	public void setOrigin(int x, int y) {
		this.x1 = x;
		this.y1 = y;
		this.x2 = x;
		this.y2 = y;
	}
	public void setPoint(int x, int y) {
		this.x2 = x;
		this.y2 = y;
	}
	public void addPoint(int x, int y) {
		
	}
	public Shape clone() {
		try {
			return (Shape)this.getClass().newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	abstract public void draw(Graphics graphics);
}