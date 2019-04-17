package global;

//import shape.Line;
//import shape.Ellipse;
//import shape.Line;
import shape.EPolygon;
import shape.ERectangle;
import shape.ESelect;
import shape.GEShape;

public class Constants {
	public static enum EDataFileName {
		path("rsc/");

		private String value;

		private EDataFileName(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum EMainFrame {
		x(200), y(100), w(400), h(600);
		private int value;

		private EMainFrame(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public enum EToolBar {
		select("Select.png", "Select_Selected.png", new ESelect()),
		rectangle("rectangle.gif", "rectangleSLT.gif", new ERectangle()),
//		ellipse("ellipse.gif", "ellipseSLT.gif", new Ellipse()), 
//		line("line.gif", "lineSLT.gif", new Line()),
		polygon("polygon.png", "Polygon_Selected.png", new EPolygon());
		private String buttonImage, selectedbuttonImage;
		private GEShape shape;

		private EToolBar(String buttonImage, String selectedbuttonImage, GEShape shape) {
			this.buttonImage = buttonImage;
			this.selectedbuttonImage = selectedbuttonImage;
			this.shape = shape;
		}

		public String getButtonImage() {
			return this.buttonImage;
		}

		public String getSelectedButtonImage() {
			return this.selectedbuttonImage;
		}

		public GEShape getShape() {
			return this.shape;
		}
	}

	public enum EMenu {
		fileMenu("File"), editMenu("Edit");
		private String text;

		private EMenu(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}
	}

	public enum EFileMenu {
		newItem("new"), openItem("open");
		private String text;

		private EFileMenu(String text) {
			this.text = text;
		}

		public String getText() {
			return this.text;
		}
	}

}
