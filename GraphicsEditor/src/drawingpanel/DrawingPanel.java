package drawingpanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import shape.EPolygon;
import shape.GEShape;
import shape.GEShape.EOnState;

public class DrawingPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private MouseHandler mouseHandler;
	private Vector<GEShape> shapeVector;
	private GEShape currentShape;
	private GEShape currentTool;

	private enum EActionState {
		eReady, eDrawing, eMoving, eResizing, eRotating
	};

	private EActionState eActionState;

	public void setCurrentTool(GEShape currentTool) {
		this.currentTool = currentTool;
	}

	public DrawingPanel() {
		this.eActionState = EActionState.eReady;
		this.setForeground(Color.black);
		this.setBackground(Color.white);
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler); // 버튼이벤트
		this.addMouseMotionListener(this.mouseHandler); // 마우스의 움직임을 인지하는 이벤트
		this.shapeVector = new Vector<GEShape>();
	}

	public void initialize() {

	}

	// 원점 그림그릴
	private void drawShape() {
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		graphics2D.setXORMode(this.getBackground());
		currentShape.draw(graphics2D);
	}

	private EOnState onShape(int x, int y) {
		this.currentShape = null;
		for (GEShape shape : this.shapeVector) {
			EOnState eOnState = shape.onShape(x, y);
			if (eOnState != null) {
				this.currentShape = shape;
				return eOnState;
			}
		}
		return null;
	}

	private void defineActionState(int x, int y) {
		EOnState eOnState = onShape(x, y);
		if (eOnState == null) {
			this.eActionState = EActionState.eDrawing;
		} else {
			switch (eOnState) {
			case eOnResize:
				this.eActionState = EActionState.eResizing;
				break;
			case eOnShape:
				this.eActionState = EActionState.eMoving;
				break;
			case eOnRotate:
				this.eActionState = EActionState.eRotating;
				break;
			default:
				// exception
				this.shapeVector = null;
				break;
			}
		}
	}

	private void initDrawing(int x, int y) {
		this.currentShape = this.currentTool.clone();
		this.currentShape.setOrigin(x, y);
		this.drawShape();
	}

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

	private void initMoving(int x, int y) {
		
		Graphics2D graphics2D = (Graphics2D) this.getGraphics();
		graphics2D.setXORMode(this.getBackground());
		this.currentShape.initmove(graphics2D, x, y);
	}

	private void keepMoving(int x, int y) {
		this.drawShape();
		this.currentShape.keepMove(x, y);
		this.drawShape();
	}

	private void finishMoving(int x, int y) {
		this.currentShape.finishmove(x, y);
	}

	public void paint(Graphics2D graphics2D) {
		super.paint(graphics2D);
		for (GEShape shape : this.shapeVector) {
			shape.draw(graphics2D);
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
			if (eActionState == EActionState.eDrawing) {
				if (currentTool instanceof EPolygon) {
					continueDrawing(event.getX(), event.getY());
				}
			} else if (eActionState == EActionState.eReady) {
				if (currentTool instanceof EPolygon) {
					initDrawing(event.getX(), event.getY());
					eActionState = EActionState.eDrawing;
				}
			}
		}

		private void mouse2Clicked(MouseEvent event) {
			if (eActionState == EActionState.eDrawing) {
				if (currentTool instanceof EPolygon) {
					finishDrawing(event.getX(), event.getY());
					eActionState = EActionState.eReady;
				}
			}
		}

		@Override
		public void mouseMoved(MouseEvent event) {
			if (eActionState == EActionState.eDrawing) {
				if (currentTool instanceof EPolygon) {
					keepDrawing(event.getX(), event.getY());
				}
			}
		}

		@Override
		public void mousePressed(MouseEvent event) {
			if (eActionState == EActionState.eReady) {
				defineActionState(event.getX(), event.getY());
				if (eActionState == EActionState.eDrawing) {
					if (!(currentTool instanceof EPolygon)) {
						initDrawing(event.getX(), event.getY());
					} else {
						eActionState = EActionState.eReady;
					}
				} else if (eActionState == EActionState.eMoving) {
					initMoving(event.getX(), event.getY());
				} else if (eActionState == EActionState.eResizing) {
					System.out.println(eActionState);
				} else if (eActionState == EActionState.eRotating) {
					System.out.println(eActionState);
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent event) {
			if (eActionState == EActionState.eDrawing) {
				if (!(currentTool instanceof EPolygon)) {
					finishDrawing(event.getX(), event.getY());
					eActionState = EActionState.eReady;
				}
			} else if (eActionState == EActionState.eMoving) {
				finishMoving(event.getX(), event.getY());
				eActionState = EActionState.eReady;
			} else if (eActionState == EActionState.eResizing) {
				eActionState = EActionState.eReady;
			} else if (eActionState == EActionState.eRotating) {
				eActionState = EActionState.eReady;
			}
		}

		@Override
		public void mouseDragged(MouseEvent event) {
			if (eActionState == EActionState.eDrawing) {
				if (!(currentTool instanceof EPolygon)) {
					keepDrawing(event.getX(), event.getY());
				}
			} else if (eActionState == EActionState.eMoving) {
				keepMoving(event.getX(), event.getY());
			} else if (eActionState == EActionState.eResizing) {
			} else if (eActionState == EActionState.eRotating) {
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
