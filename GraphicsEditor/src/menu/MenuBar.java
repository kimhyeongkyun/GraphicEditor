package menu;
import javax.swing.JMenuBar;

import drawingpanel.DrawingPanel;
import global.Constants.EMenu;

public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;
	
	//components
	private FileMenu fileMenu;
	private EditMenu editMenu;
	
	//association
	private DrawingPanel drawingPanel;
	public void associate(DrawingPanel drawingPanel) {
		this.drawingPanel = new DrawingPanel();
	}
	public MenuBar() {
		this.fileMenu = new FileMenu(EMenu.fileMenu.getText());
		this.add(this.fileMenu);
//		this.editMenu = new EditMenu(EMenu.editMenu.getText());
//		this.add(this.editMenu);
	}
	public void initialize() {
		this.fileMenu.initialize();
	}	

}	
