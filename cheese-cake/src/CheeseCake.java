import gui.MainWindow;

public class CheeseCake {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
                MainWindow.runMainWindow();
            }
        });
	}

}
