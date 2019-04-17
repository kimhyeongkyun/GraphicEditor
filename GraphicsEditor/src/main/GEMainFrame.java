package main;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import drawingpanel.DrawingPanel;
import global.Constants.EMainFrame;
import menu.MenuBar;
import toolbar.GEToolBar;

public class GEMainFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	// components - mainframe�� �� �ڽĵ��� new�Ѵ�.
	private MenuBar menuBar;
	private GEToolBar toolBar;
	private DrawingPanel drawingPanel;
	
	public GEMainFrame(){
		//attributes �Ӽ� ��
		this.setLocation(EMainFrame.x.getValue(), EMainFrame.y.getValue());
		this.setSize(EMainFrame.w.getValue(), EMainFrame.h.getValue());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setLayout(new BorderLayout());
	
		//components �ڽĵ� ���
		this.menuBar = new MenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.toolBar = new GEToolBar();
		this.add(this.toolBar, BorderLayout.NORTH);
		
		this.drawingPanel = new DrawingPanel();
		this.add(this.drawingPanel, BorderLayout.CENTER);
		
		//association �ڽİ� �ڽĻ��̿� ������ ����
		this.menuBar.associate(this.drawingPanel);
		this.toolBar.associate(this.drawingPanel);
	}

	public void initialize() {
		this.menuBar.initialize();
		this.toolBar.initialize();
		this.drawingPanel.initialize();
	}
}
