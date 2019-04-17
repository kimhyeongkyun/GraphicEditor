package toolbar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import drawingpanel.DrawingPanel;
import global.Constants.EDataFileName;
import global.Constants.EToolBar;

public class GEToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;

	private Vector<JRadioButton> buttons;
	
	private ButtonGroup buttonGroup;
	
	// association 형제 mainframe drawing panel을 만들어서 친구를 만들어줌
	private DrawingPanel drawingPanel;

	public void associate(DrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public GEToolBar() {
		this.buttonGroup = new ButtonGroup();
		this.buttons = new Vector<JRadioButton>();
		JRadioButton radioButton  = null;
		
		ActionHandler actionHandler = new ActionHandler();
	
		for(EToolBar eToolBar : EToolBar.values()) {
			radioButton = new JRadioButton();
			radioButton.setIcon(new ImageIcon(EDataFileName.path.getValue()+eToolBar.getButtonImage()));
			radioButton.setSelectedIcon(new ImageIcon(EDataFileName.path.getValue()+eToolBar.getSelectedButtonImage()));
			radioButton.setActionCommand(eToolBar.name());
			radioButton.addActionListener(actionHandler);
			buttonGroup.add(radioButton);
			this.buttons.add(radioButton);
			this.add(radioButton);
		}
	}
	public void initialize() {
		this.buttons.get(EToolBar.polygon.ordinal()).doClick();
	}
	private class ActionHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			drawingPanel.setCurrentTool(EToolBar.valueOf(actionEvent.getActionCommand()).getShape());
		}
	}

}
