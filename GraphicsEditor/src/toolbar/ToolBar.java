package toolbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JToolBar;

import drawingpanel.DrawingPanel;
import global.Constants.EToolBar;

public class ToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	private Vector<JButton> buttons;
	
	//association 형제  mainframe drawing panel을 만들어서 친구를 만들어줌
	private DrawingPanel drawingPanel;
	
	public void associate(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;	
	}
	
	public ToolBar() {
		this.buttons = new Vector<JButton>();
		ActionHandler actionHandler = new ActionHandler();
		for(EToolBar eToolBar:EToolBar.values()) {
			JButton button = new JButton(eToolBar.getText());
			button.setActionCommand(eToolBar.name());
			button.addActionListener(actionHandler);
			this.buttons.add(button);
			this.add(button);
		}
	}
	
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			drawingPanel.setCurrentTool(EToolBar.valueOf(actionEvent.getActionCommand()));
		}
	}


}
