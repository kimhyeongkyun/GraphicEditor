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
		this.addMouseListener(this.mouseHandler); // ��ư�̺�Ʈ
		this.addMouseMotionListener(this.mouseHandler); // ���콺�� �������� �����ϴ� �̺�Ʈ
		currentTool = EToolBar.select.getShape();
	}
	//����
	private void drawShape(int x, int y) {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		currentTool.setOrigin(x, y);
		currentTool.draw(graphics);
	}
	// ����� �����̰� �׸���
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
