package shape;

import java.awt.Graphics;

public class Select extends Shape{

	public void draw(Graphics graphics, int x, int y, int width, int height){
		graphics.clearRect(x, y, width, height);
	}

	@Override
	public void draw(Graphics graphics) {
		// TODO Auto-generated method stub
		
	}
}
