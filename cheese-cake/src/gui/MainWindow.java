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

	private static final String ACTION_SIMULATION = "Start Simulation";

	// initial simulation values
	private int cols = 10;
	private int rows = 10;
	private double percF = 0.5;
	private int steps = 100;
	private int repPStep = 100;
	private boolean onlyClosest;

	private int[] solutions;

	private JTextField nOfRows;
	private JTextField nOfCols;
	private JTextField percFilled;
	private JTextField nOfSteps;
	private JTextField repPerStep;
	private JCheckBox onlyClosestCB;

	private JSlider selectGridSlide;
	private JTextField selectGridField;

	private JButton startTraversal;

	private JLabel travResult;
	
	private Graph graph;
	
	private XGrid xgrid;

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
		this.nOfRows = new JTextField(String.valueOf(rows));
		this.nOfRows.addKeyListener(this);

		this.nOfCols = new JTextField(String.valueOf(cols));
		this.nOfCols.addKeyListener(this);

		this.percFilled = new JTextField(String.valueOf(percF));
		this.percFilled.addKeyListener(this);

		this.nOfSteps = new JTextField(String.valueOf(steps));
		this.nOfSteps.addKeyListener(this);

		this.repPerStep = new JTextField(String.valueOf(repPStep));
		this.repPerStep.addKeyListener(this);

		this.onlyClosestCB = new JCheckBox();
		this.onlyClosestCB.addChangeListener(this);

		this.selectGridSlide = new JSlider(JSlider.HORIZONTAL, 1, repPStep, 1);
		this.selectGridSlide.addChangeListener(this);
		this.selectGridField = new JTextField("1");
		this.selectGridField.addKeyListener(this);
		this.selectGridSlide.setEnabled(false);
		this.selectGridField.setEnabled(false);

		this.startTraversal = new JButton(ACTION_SIMULATION);
		this.startTraversal.addActionListener(this);

		this.travResult = new JLabel("");
		
		graph = new Graph();
		xgrid = new XGrid();

		// window settings
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(700, 300);

		// create the right column for the settings
		Container settingsPane = new Container();
		settingsPane.setLayout(new GridLayout(0, 2));

		settingsPane.add(new JLabel("number of rows (int > 0)"));
		settingsPane.add(nOfCols);
		settingsPane.add(new JLabel("number of cols (int > 0)"));
		settingsPane.add(nOfRows);
		settingsPane.add(new JLabel("percentage filled (double 0.0 - 1.0)"));
		settingsPane.add(percFilled);
		settingsPane.add(new JLabel("number of steps (int > 0)"));
		settingsPane.add(nOfSteps);
		settingsPane.add(new JLabel("repetitions per step (int > 0)"));
		settingsPane.add(repPerStep);
		settingsPane.add(new JLabel("only closest"));
		settingsPane.add(onlyClosestCB);

		settingsPane.add(startTraversal);
		settingsPane.add(travResult);

		settingsPane.add(new JLabel("choose a grid"));
		settingsPane.add(new JLabel(""));

		settingsPane.add(selectGridSlide);
		settingsPane.add(selectGridField);

		// set layout and place the various components inside the window
		this.getContentPane().setLayout(new BorderLayout(5, 5));

		this.getContentPane().add(settingsPane, BorderLayout.WEST);

		this.getContentPane().add(graph, BorderLayout.CENTER);
		this.getContentPane().add(xgrid, BorderLayout.EAST);

		this.setVisible(true);

	}

	// public Cell[][] getGrid() {
	// return grid;
	// }

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals(ACTION_SIMULATION)) {
			if (areParametersCorrect()) {
				startSimulation();
			} else {
				this.travResult.setText("Please correct the wrong parameters");
			}
		}

	}

	private void startSimulation() {

		disableSelection();

		this.travResult.setText("Calculating...");
		this.repaint();
		
		percF = 0.0;
		
		this.solutions = new int[steps];

		for (int i = 0; i < steps; i++) {
			int traversableGrids = 0;
			percF += 1.0 / steps;
			for (int j = 0; j < repPStep; j++) {
				Grid2D g = new Grid2D(percF, rows, cols);
				if (g.isTraversable(onlyClosest)) {
					++traversableGrids;
				}
			}
			solutions[i] = traversableGrids;
		}
		
		graph.setSolutions(solutions, steps, repPStep);
		
		graph.repaint();

		this.selectGridSlide.setMaximum(repPStep);
		this.selectGridSlide.setValue(1);

		System.gc();
		
		this.travResult.setText("Done");
		enableSelection();

	}

	private void syncSelFieldSlide() {

		if (containsInteger(selectGridField)) {
			int selectedGrid = Integer.valueOf(selectGridField.getText());
			if (selectedGrid > 0 && selectedGrid <= repPStep) {
				this.selectGridSlide.setValue(selectedGrid);
				blackJFieldText(selectGridField);
			} else {
				redJFieldText(selectGridField);
			}
		}

	}

	private void redJFieldText(JTextField aField) {
		aField.setForeground(Color.RED);
	}

	private void blackJFieldText(JTextField aField) {
		aField.setForeground(Color.BLACK);
	}

	private void enableSelection() {
		selectGridField.setEnabled(true);
		selectGridSlide.setEnabled(true);
	}

	private void disableSelection() {
		selectGridField.setEnabled(false);
		selectGridSlide.setEnabled(false);
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
			Integer.parseInt(aField.getText());
			blackJFieldText(aField);
			isInt = true;

		} catch (Exception e) {
			redJFieldText(aField);
		}

		return isInt;
	}

	private boolean containsDouble(JTextField aField) {

		boolean isDouble = false;

		try {
			Double.parseDouble(aField.getText());
			blackJFieldText(aField);
			isDouble = true;

		} catch (Exception e) {
			redJFieldText(aField);
		}

		return isDouble;
	}

	private boolean isInRange(double x, double min, double max) {
		if (x >= min && x <= max) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {

		if (arg0.getSource().equals(this.onlyClosestCB)) {
			this.onlyClosest = onlyClosestCB.isSelected();
		}

		if (arg0.getSource().equals(this.selectGridSlide)) {
			this.selectGridField.setText(String.valueOf(this.selectGridSlide
					.getValue()));
			this.xgrid.repaint();
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		if (this.selectGridField.equals(arg0.getSource())) {
			this.syncSelFieldSlide();
		}

		if (this.nOfCols.equals(arg0.getSource())) {
			setCols((JTextField) (arg0.getSource()));
		}

		if (this.nOfRows.equals(arg0.getSource())) {
			setRows((JTextField) (arg0.getSource()));
		}

		if (this.percFilled.equals(arg0.getSource())) {
			setPercF((JTextField) (arg0.getSource()));
		}

		if (this.repPerStep.equals(arg0.getSource())) {
			setRepPStep((JTextField) (arg0.getSource()));
		}

		if (this.nOfSteps.equals(arg0.getSource())) {
			setSteps((JTextField) (arg0.getSource()));
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	private void setCols(JTextField aField) {
		if (containsInteger(aField)) {
			int tmp = Integer.parseInt(aField.getText());
			if (tmp > 0) {
				cols = tmp;
			} else {
				redJFieldText(aField);
			}
		}
	}

	private void setRows(JTextField aField) {
		if (containsInteger(aField)) {
			int tmp = Integer.parseInt(aField.getText());
			if (tmp > 0) {
				rows = tmp;
			} else {
				redJFieldText(aField);
			}
		}
	}

	private void setRepPStep(JTextField aField) {
		if (containsInteger(aField)) {
			int tmp = Integer.parseInt(aField.getText());
			if (tmp > 0) {
				repPStep = tmp;
			} else {
				redJFieldText(aField);
			}
		}
	}
	
	private void setSteps(JTextField aField) {
		if (containsInteger(aField)) {
			int tmp = Integer.parseInt(aField.getText());
			if (tmp > 0) {
				steps = tmp;
			} else {
				redJFieldText(aField);
			}
		}
	}

	private void setPercF(JTextField aField) {
		if (containsDouble(aField)) {
			double tmp = Double.parseDouble(aField.getText());
			if (isInRange(tmp, 0, 1)) {
				percF = tmp;
			} else {
				redJFieldText(aField);
			}
		}
	}

	private boolean areParametersCorrect() {
		boolean areCorrect = true;

		areCorrect &= nOfCols.getForeground().equals(Color.BLACK);
		areCorrect &= nOfRows.getForeground().equals(Color.BLACK);
		areCorrect &= percFilled.getForeground().equals(Color.BLACK);
		areCorrect &= nOfSteps.getForeground().equals(Color.BLACK);
		areCorrect &= repPerStep.getForeground().equals(Color.BLACK);

		return areCorrect;
	}

}
