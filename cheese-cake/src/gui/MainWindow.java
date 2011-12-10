package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;

import solver.Cell;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 2740437090361841747L;

	private static MainWindow mainWindow = null;
	
	private XGrid xgrid;
	
	private Cell[][] grid;
	
	/**
	 * Singleton pattern applied
	 */
	public static void runMainWindow(Cell[][] grid){
		
		if(mainWindow == null){
			
			mainWindow = new MainWindow(grid);
			
		}
		
	}
	
	private MainWindow(Cell[][] grid){
		
		super();
		
		this.grid = grid;
		
		xgrid = new XGrid(this.grid);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(400,400);
		
		this.getContentPane().add(xgrid);
		
		this.setVisible(true);
		
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
		xgrid.setGrid(this.grid);
	}

}
