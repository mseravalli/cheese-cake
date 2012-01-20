package gui;

import grid.Grid2D;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MainWindow extends JFrame implements ActionListener,
		ChangeListener, KeyListener {

	private static final long serialVersionUID = 2740437090361841747L;

	private static MainWindow mainWindow = null;

	private static final String ACTION_CREATE = "Create Grid";
	private static final String ACTION_CLEAN = "Clean Grid";
	private static final String ACTION_SIMULATION = "Start Simulation";

	private XGrid xgrid;

	// initial grid values
	private int cols = 10;
	private int rows = 10;
	private double percF = 0.5;
	private int size = 1000;
	private boolean onlyClosest;

	private int traversableGrids = 0;

	private Grid2D grid;

	private ArrayList<Grid2D> gridList;

	private JTextField nOfRows;
	private JTextField nOfCols;
	private JTextField percFilled;
	private JTextField simSize;
	private JCheckBox onlyClosestCB;

	private JSlider selectGridSlide;
	private JTextField selectGridField;

	private JButton createGrid;
	private JButton cleanGrid;
	private JButton startTraversal;

	private JLabel travResult;

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

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Initialise object attributes
		this.grid = new Grid2D(percF, rows, cols);
		xgrid = new XGrid(this.grid);

		this.gridList = new ArrayList<Grid2D>();

		this.nOfRows = new JTextField(String.valueOf(rows));
		this.nOfCols = new JTextField(String.valueOf(cols));
		this.percFilled = new JTextField(String.valueOf(percF));
		this.simSize = new JTextField(String.valueOf(size));
		this.onlyClosestCB = new JCheckBox();

		this.selectGridSlide = new JSlider(JSlider.HORIZONTAL, 1, size, 1);
		this.selectGridSlide.addChangeListener(this);
		this.selectGridField = new JTextField("1");
		this.selectGridField.addKeyListener(this);

		this.createGrid = new JButton(ACTION_CREATE);
		this.createGrid.addActionListener(this);
		this.cleanGrid = new JButton(ACTION_CLEAN);
		this.cleanGrid.addActionListener(this);
		this.startTraversal = new JButton(ACTION_SIMULATION);
		this.startTraversal.addActionListener(this);

		this.travResult = new JLabel("");

		// window settings
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(700, 300);

		// create the right column for the settings
		Container settingsPane = new Container();
		settingsPane.setLayout(new GridLayout(0, 2));

		settingsPane.add(new JLabel("number of rows"));
		settingsPane.add(nOfCols);
		settingsPane.add(new JLabel("number of cols"));
		settingsPane.add(nOfRows);
		settingsPane.add(new JLabel("percentage filled"));
		settingsPane.add(percFilled);
		settingsPane.add(new JLabel("simulation size"));
		settingsPane.add(simSize);
		settingsPane.add(new JLabel("only closest"));
		settingsPane.add(onlyClosestCB);

		settingsPane.add(startTraversal);
		settingsPane.add(travResult);

		settingsPane.add(new JLabel("choose a grid"));
		settingsPane.add(new JLabel(""));

		settingsPane.add(selectGridSlide);
		settingsPane.add(selectGridField);

		settingsPane.add(createGrid);
		settingsPane.add(cleanGrid);

		// set layout and place the various components inside the window
		this.getContentPane().setLayout(new BorderLayout(5, 5));

		this.getContentPane().add(xgrid, BorderLayout.CENTER);

		this.getContentPane().add(settingsPane, BorderLayout.EAST);

		this.setVisible(true);

	}

	// public Cell[][] getGrid() {
	// return grid;
	// }

	public void setGrid(Grid2D grid) {
		this.grid = grid;
		xgrid.setGrid(this.grid);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals(ACTION_CREATE)) {
			this.createGrid();
			this.travResult.setText("");
			this.repaint();
		}

		if (arg0.getActionCommand().equals(ACTION_CLEAN)) {
			grid.cleanGrid();
			this.travResult.setText("");
			this.repaint();
		}

		if (arg0.getActionCommand().equals(ACTION_SIMULATION)) {

			int size = 0;

			traversableGrids = 0;

			this.gridList.clear();

			try {
				size = Integer.valueOf(this.simSize.getText());
			} catch (Exception e) {
				System.err.println("the size of the simulation is not valid");
			}

			for (int i = 0; i < size; i++) {
				Grid2D g = new Grid2D(percF, rows, cols);
				if (g.isTraversable(onlyClosest)) {
					++traversableGrids;
				}
				this.gridList.add(g);
			}

			this.travResult.setText(100.0 * traversableGrids / size
					+ "% of traversable grids");

			// old stuff need to delete it
			/*
			 * grid.cleanGrid(); boolean isTrav =
			 * grid.isTraversable(onlyClosestCB.isSelected());
			 * 
			 * if(isTrav){ this.travResult.setText("Traversable"); } else {
			 * this.travResult.setText("NOT traversable"); }
			 * 
			 * this.repaint();
			 */
		}

	}

	private void createGrid() {
		double percentageFilled = Double.valueOf(this.percFilled.getText());
		int dimX = Integer.valueOf(this.nOfRows.getText());
		int dimY = Integer.valueOf(this.nOfCols.getText());
		this.setGrid(new Grid2D(percentageFilled, dimX, dimY));
	}

	private void syncSelFieldSlide() {

		if(containsInteger(selectGridField) ){
			int selectedGrid = Integer.valueOf(selectGridField.getText());
			if(selectedGrid > 0 && selectedGrid <= size){
				this.selectGridSlide.setValue(selectedGrid);
				blackJFieldText(selectGridField);
			} else {
				redJFieldText(selectGridField);
			}
		}

	}

	private void redJFieldText(JTextField aField) {
		aField.setForeground(new Color(255, 0, 0));
	}

	private void blackJFieldText(JTextField aField) {
		aField.setForeground(new Color(0, 0, 0));
	}

	/**
	 * The method changes the colour of the font of the JText field if the value
	 * in the text is not an integer
	 * 
	 * @param aField
	 * @return
	 */
	private boolean containsInteger(JTextField aField) {

		boolean isInt = false;

		try {
			Integer.parseInt(this.selectGridField.getText());
			blackJFieldText(selectGridField);
			isInt = true;

		} catch (Exception e) {
			redJFieldText(selectGridField);
		} 
		
		return isInt;
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {

		// TODO be sure that the component generating the change is the
		// selectGridSlide

		this.selectGridField.setText(String.valueOf(this.selectGridSlide
				.getValue()));

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		if (this.selectGridField.equals(arg0.getSource())) {
			this.syncSelFieldSlide();
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
