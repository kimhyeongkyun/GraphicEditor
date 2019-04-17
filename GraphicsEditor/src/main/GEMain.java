package main;

public class GEMain {
	static private GEMainFrame mainFrame; 
	public static void main(String[] args) {
		mainFrame = new GEMainFrame();
		mainFrame.initialize();
		mainFrame.setVisible(true);
	}

}
