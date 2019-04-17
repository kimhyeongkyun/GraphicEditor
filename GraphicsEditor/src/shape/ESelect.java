package shape;

import java.awt.Dimension;
import java.awt.Rectangle;

public class ESelect extends GEShape{
	private Rectangle select;
	public ESelect() {
		super();
		this.myShape = new Rectangle();
		this.select = (Rectangle) this.myShape; 
	}
	
	@Override
	public void setOrigin(int x, int y) {
		this.select.setBounds(x, y, 0, 0);
	}

	@Override
	public void setPoint(int x, int y) {
		this.select.setSize(new Dimension(x-this.select.x, y-this.select.y));
	}
	
	@Override
	public void addPoint(int x, int y) {
	}



	@Override
	public void keepMove(int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finishmove(int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
