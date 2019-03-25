package drawingpanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import global.Constants.EToolBar;
import shape.Shape;


public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private MouseHandler mouseHandler;
	private Shape currentTool;

	public void setCurrentTool(EToolBar currentTool) {
		this.currentTool = currentTool.getShape();
	}

	public DrawingPanel() {
		this.setBackground(Color.white);

		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler); // 버튼이벤트
		this.addMouseMotionListener(this.mouseHandler); // 마우스의 움직임을 인지하는 이벤트
		currentTool = EToolBar.select.getShape();
	}
	//원점
	private void drawShape(int x, int y) {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		currentTool.setOrigin(x, y);
		currentTool.draw(graphics);
	}
	// 지우고 움직이고 그리고
	private void moveShape(int x, int y) {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		currentTool.draw(graphics);
		currentTool.setPoint(x, y);
		currentTool.draw(graphics);
	}


	private class MouseHandler implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent event) {
		}
		@Override
		public void mousePressed(MouseEvent event) {
			drawShape(event.getX(),event.getY());
		}

		@Override
		public void mouseReleased(MouseEvent event) {
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			moveShape(event.getX(),event.getY());
		}

		@Override
		public void mouseEntered(MouseEvent event) {
		}

		@Override
		public void mouseExited(MouseEvent event) {
		}

		@Override
		public void mouseMoved(MouseEvent e) {
		}
	}
}
