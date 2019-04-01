package drawingpanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import global.Constants.EToolBar;
import shape.Shape;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private MouseHandler mouseHandler;
	private Vector<Shape> shapeVector;
	private Shape currentShape;
	private Shape currentTool;
	private enum EActionState {
		eReady, e2PDrawing, eNPDrawing
	};

	private EActionState eActionState;

	public void setCurrentTool(EToolBar currentTool) {
		this.currentTool = currentTool.getShape();
	}

	public DrawingPanel() {
		this.eActionState = EActionState.eReady;
		this.setBackground(Color.white);
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler); // ��ư�̺�Ʈ
		this.addMouseMotionListener(this.mouseHandler); // ���콺�� �������� �����ϴ� �̺�Ʈ

		this.shapeVector = new Vector<Shape>();
		this.currentTool = EToolBar.select.getShape();
	}

	// ���� �׸��׸�
	private void drawShape() {
		Graphics graphics = this.getGraphics();
		graphics.setXORMode(getBackground());
		currentShape.draw(graphics);
	}

	private void initDrawing(int x, int y) {
		this.currentShape = this.currentTool.clone();
		this.currentShape.setOrigin(x, y);
		this.drawShape();
	}

	// ����� �����̰� �׸���
	private void keepDrawing(int x, int y) {
		this.drawShape();
		this.currentShape.setPoint(x, y);
		this.drawShape();
	}

	private void continueDrawing(int x, int y) {
		this.currentShape.addPoint(x, y);
	}

	private void finishDrawing(int x, int y) {
		this.shapeVector.add(currentShape);
	}

	public void paint(Graphics graphics) {
		super.paint(graphics);
		for (Shape shape : this.shapeVector) {
			shape.draw(graphics);
		}
	}

	private class MouseHandler implements MouseListener, MouseMotionListener {
		@Override
		public void mouseClicked(MouseEvent event) {
			if (event.getClickCount() == 1) {
				mouse1Clicked(event);
			} else if (event.getClickCount() == 2) {
				mouse2Clicked(event);
			}
		}

		private void mouse1Clicked(MouseEvent event) {
			if (eActionState == EActionState.eReady) {
				initDrawing(event.getX(), event.getY());
				eActionState = EActionState.eNPDrawing;
			} else if (eActionState == EActionState.eNPDrawing) {
				continueDrawing(event.getX(), event.getY());
			}
		}

		private void mouse2Clicked(MouseEvent event) {
			if (eActionState == EActionState.eNPDrawing) {
				finishDrawing(event.getX(), event.getY());
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mouseMoved(MouseEvent event) {
			if (eActionState == EActionState.eNPDrawing) {
				keepDrawing(event.getX(), event.getY());
			}
		}

		@Override
		public void mousePressed(MouseEvent event) {
			if (eActionState == EActionState.eReady) {
				initDrawing(event.getX(), event.getY());
				eActionState = EActionState.e2PDrawing;
			}
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if (eActionState == EActionState.e2PDrawing) {
				finishDrawing(event.getX(), event.getY());
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			if (eActionState == EActionState.e2PDrawing) {
				keepDrawing(event.getX(), event.getY());
			}
		}

		@Override
		public void mouseEntered(MouseEvent event) {
		}

		@Override
		public void mouseExited(MouseEvent event) {
		}

	}
}
