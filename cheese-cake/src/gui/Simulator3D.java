package gui;

import grid.Grid;
import grid.Grid3D;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Simulator3D extends OptAndDisp implements KeyListener, ChangeListener, ActionListener {
	
	private static final long serialVersionUID = -2041823208493812545L;
	
	private static final String ACTION_SIMULATION = "Start Simulation";

	// initial simulation values
	private int cols = 4;
	private int rows = 4;
	private int pages = 4;
	private int steps = 100;
	private int repPStep = 100;
	private boolean onlyClosest;

	private int[] solutions;
	
	private JTextField nOfRows;
	private JTextField nOfCols;
	private JTextField nOfPages;
	private JTextField nOfSteps;
	private JTextField repPerStep;
	private JCheckBox onlyClosestCB;

	private JButton startTraversal;

	private JLabel travResult;
	
	private Graph graph;
	
	public Simulator3D(){
		
		super();
		
		// Initialise object attributes
		this.nOfRows = new JTextField(String.valueOf(rows));
		this.nOfRows.addKeyListener(this);
		blackJFieldText(nOfRows);

		this.nOfCols = new JTextField(String.valueOf(cols));
		this.nOfCols.addKeyListener(this);
		blackJFieldText(nOfCols);
		
		this.nOfPages = new JTextField(String.valueOf(pages));
		this.nOfPages.addKeyListener(this);
		blackJFieldText(nOfPages);

		this.nOfSteps = new JTextField(String.valueOf(steps));
		this.nOfSteps.addKeyListener(this);
		blackJFieldText(nOfSteps);

		this.repPerStep = new JTextField(String.valueOf(repPStep));
		this.repPerStep.addKeyListener(this);
		blackJFieldText(repPerStep);

		this.onlyClosestCB = new JCheckBox();
		this.onlyClosestCB.addChangeListener(this);

		this.startTraversal = new JButton(ACTION_SIMULATION);
		this.startTraversal.addActionListener(this);

		this.travResult = new JLabel("");
		
		graph = new Graph();
		
		// create the right column for the settings
		Container settingsPane = new Container();
		settingsPane.setLayout(new GridLayout(0, 2));

		settingsPane.add(new JLabel("number of rows (int > 0)"));
		settingsPane.add(nOfCols);
		settingsPane.add(new JLabel("number of cols (int > 0)"));
		settingsPane.add(nOfRows);
		settingsPane.add(new JLabel("number of pages (int > 0)"));
		settingsPane.add(nOfPages);
		settingsPane.add(new JLabel("number of steps (int > 0)"));
		settingsPane.add(nOfSteps);
		settingsPane.add(new JLabel("repetitions per step (int > 0)"));
		settingsPane.add(repPerStep);
		settingsPane.add(new JLabel("only closest"));
		settingsPane.add(onlyClosestCB);

		settingsPane.add(startTraversal);
		settingsPane.add(travResult);

		// set layout and place the various components inside the window
		mainContainer.setLayout(new GridLayout(0, 2));
		
		mainContainer.add(settingsPane);
		mainContainer.add(graph);
		
		this.add(mainContainer);
	}
	
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

		this.travResult.setText("Calculating...");
		this.repaint();
		
		double percF = 0.0;
		
		this.solutions = new int[steps];

		for (int i = 0; i < steps; i++) {
			int traversableGrids = 0;
			for (int j = 0; j < repPStep; j++) {
				Grid g = new Grid3D(percF, rows, cols, pages);
				if (g.isTraversable(onlyClosest)) {
					++traversableGrids;
				}
			}
			percF += 1.0 / steps;
			solutions[i] = traversableGrids;
		}
		
		graph.setSolutions(solutions, steps, repPStep);
		
		graph.repaint();

		System.gc();
		
		this.travResult.setText("Done");

	}


	@Override
	public void stateChanged(ChangeEvent arg0) {

		if (arg0.getSource().equals(this.onlyClosestCB)) {
			this.onlyClosest = onlyClosestCB.isSelected();
		}

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent arg0) {

		if (this.nOfCols.equals(arg0.getSource())) {
			setCols((JTextField) (arg0.getSource()));
		}

		if (this.nOfRows.equals(arg0.getSource())) {
			setRows((JTextField) (arg0.getSource()));
		}
		
		if (this.nOfPages.equals(arg0.getSource())) {
			setPages((JTextField) (arg0.getSource()));
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
	
	private void setPages(JTextField aField) {
		if (containsInteger(aField)) {
			int tmp = Integer.parseInt(aField.getText());
			if (tmp > 0) {
				pages = tmp;
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

	protected boolean areParametersCorrect() {
		boolean areCorrect = true;

		areCorrect &= nOfCols.getForeground().equals(Color.BLACK);
		areCorrect &= nOfRows.getForeground().equals(Color.BLACK);
		areCorrect &= nOfSteps.getForeground().equals(Color.BLACK);
		areCorrect &= repPerStep.getForeground().equals(Color.BLACK);

		return areCorrect;
	}
	
}
