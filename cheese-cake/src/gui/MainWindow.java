package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.grid = grid;
		
		xgrid = new XGrid(this.grid);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setSize(749,180);
		
		//create the right column for the settings
		Container settingsPane = new Container();
		settingsPane.setLayout(new GridLayout(0,2));
		
		settingsPane.add(new JLabel("number of rows"));
		settingsPane.add(new JTextField(String.valueOf(grid.length)));
		settingsPane.add(new JLabel("number of cols"));
		settingsPane.add(new JTextField(String.valueOf(grid[0].length)));
		settingsPane.add(new JLabel("percentage filled"));
		settingsPane.add(new JTextField(String.valueOf(0.6)));
		settingsPane.add(new JButton("start traversal"));
		
		
		
		// set layout and place the various components inside the window
		this.getContentPane().setLayout(new BorderLayout(5,5));
		
		this.getContentPane().add(xgrid, BorderLayout.CENTER);
		
		this.getContentPane().add(settingsPane, BorderLayout.EAST);
		
		this.setVisible(true);
		
	}
	
	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
		xgrid.setGrid(this.grid);
	}

	
}
