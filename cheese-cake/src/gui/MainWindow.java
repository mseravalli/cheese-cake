package gui;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class MainWindow extends JFrame{

	private static final long serialVersionUID = 2740437090361841747L;

	private static MainWindow mainWindow = null;

	private Solver2D solver2D = null;
	private Simulator3D simulator3D = null;
	
	/**
	 * Singleton pattern applied
	 */
	public static void runMainWindow() {

		if (mainWindow == null) {

			mainWindow = new MainWindow();

		}

	}

	private MainWindow() {

		super();
		
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		//initialise attributes
		this.solver2D = new Solver2D();
		this.simulator3D = new Simulator3D();

		// window settings
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(1100, 500);
		
		
		//add all elements
		
		JTabbedPane tab = new JTabbedPane();
		tab.add("2D solver", solver2D);
		tab.add("3D simulator", simulator3D);
		
		this.add(tab);

		//this.pack();
		
		this.setVisible(true);

	}
	
}
