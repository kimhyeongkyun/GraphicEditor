package shape;

import java.awt.Dimension;
import java.awt.Rectangle;

public class ERectangle extends GEShape {
	private Rectangle rectangle;

	public ERectangle() {
		super();
		this.myShape = new Rectangle();
		this.rectangle = (Rectangle) this.myShape;
	}

	@Override
	public void setOrigin(int x, int y) {
		this.rectangle.setBounds(x, y, 0, 0);
		this.px = x;
		this.py = y;
	}

	@Override
	public void setPoint(int x, int y) {
		this.rectangle.setSize(new Dimension(x - this.rectangle.x, y - this.rectangle.y));
	}

	@Override
	public void addPoint(int x, int y) {
	}

	@Override
	public void keepMove(int x, int y) {
		int dw = x - px;
		int dh = y - py;
		this.rectangle.setLocation(this.rectangle.x + dw, this.rectangle.y + dh);
		this.px = x;
		this.py = y;

	}

	@Override
	public GEShape clone() {
		return new ERectangle();
	}

	@Override
	public void finishmove(int x, int y) {

	}

}
