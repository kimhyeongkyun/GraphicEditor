package shape;

import java.awt.Graphics2D;
import java.awt.Shape;

import shape.GEAnchors.EAnchors;

public abstract class GEShape implements Cloneable {
	public enum EOnState {
		eOnShape, eOnResize, eOnRotate
	};

	protected Shape myShape;
	protected int px;
	protected int py;
	protected GEAnchors anchors;
	private boolean selected;
	public GEShape() {
		this.selected = false;
		this.anchors = new GEAnchors();
	}
	public boolean getSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
		if (this.selected) {

		}else {
			this.anchors = null;
		}
	}


	public abstract void setOrigin(int x, int y);

	abstract public void setPoint(int x, int y);

	abstract public void addPoint(int x, int y);

	public void initmove(Graphics2D graphics2D, int x, int y) {
		this.px = x;
		this.py = y;
		this.selected = true;
//		if(!selected) {
//			this.anchors.setBoundingRect(myShape.getBounds());
//			this.anchors.draw(graphics2D);
//		}
	}

	abstract public void keepMove(int x, int y);

	public void draw(Graphics2D graphics2D) {
		graphics2D.draw(myShape);
		this.anchors.draw(graphics2D);
		if(this.selected) {
			this.anchors.setBoundingRect(myShape.getBounds());
			this.anchors.draw(graphics2D);
		}
	}

	public EOnState onShape(int x, int y) {
		if (this.selected) {
			EAnchors eAnchors = this.anchors.onShape(x, y);
			if (eAnchors == EAnchors.RR) { // rotate
				return EOnState.eOnRotate;
			} else if (eAnchors == null) { //
				if (this.myShape.contains(x, y)) {
					return EOnState.eOnShape;
				}
			} else { // resize
				return EOnState.eOnResize;
			}
		} else {
			if (this.myShape.contains(x, y)) {
				return EOnState.eOnShape;
			}
		}
		return null;
	}

	public GEShape clone() {
		try {
			return (GEShape) this.getClass().newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}

	}

	public abstract void finishmove(int x, int y);

}