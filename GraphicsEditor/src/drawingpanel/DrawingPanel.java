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

	// ���� �׸��׸�
	private void drawShape() {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		currentTool.draw(graphics);
	}

	private void initDrawing(int x, int y) {
		this.currentTool.setOrigin(x, y);
		this.drawShape();
	}

	// ����� �����̰� �׸���
	private void keepDrawing(int x, int y) {
		this.drawShape();
		this.currentTool.setPoint(x, y);
		this.drawShape();
	}

	private void continueDrawing(int x, int y) {
		this.currentTool.addPoint(x, y);
	}

	private void finishDrawing(int x, int y) {

	}

	private class MouseHandler implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent event) {
			if(event.getClickCount() == 1) {
				initDrawing(event.getX(), event.getY());
			}else if(event.getClickCount() == 2) {
				finishDrawing(event.getX(), event.getY());
			}
		}

		@Override
		public void mousePressed(MouseEvent event) {
			initDrawing(event.getX(), event.getY());
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			finishDrawing(event.getX(), event.getY());
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			keepDrawing(event.getX(), event.getY());
		}

		@Override
		public void mouseEntered(MouseEvent event) {
		}

		@Override
		public void mouseExited(MouseEvent event) {
		}

		@Override
		public void mouseMoved(MouseEvent event) {
			keepDrawing(event.getX(), event.getY());
		}
	}
}
