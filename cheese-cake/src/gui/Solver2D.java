package gui;

import grid.Grid2D;

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
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Solver2D extends OptAndDisp implements KeyListener,
		ChangeListener, ActionListener {

	private static final long serialVersionUID = -2041823208493812545L;

	private static final String ACTION_SOLVE = "Solve the grids";

	// initial simulation values
	private int cols = 12;
	private int rows = 12;
	private double percF = 0.5;
	private int repPStep = 100;
	private boolean onlyClosest;

	private Grid2D[] gridList;

	private JTextField nOfRows;
	private JTextField nOfCols;
	private JTextField percFilled;
	private JTextField repPerStep;
	private JCheckBox onlyClosestCB;

	private JSlider selectGridSlide;
	private JTextField selectGridField;

	private JButton startTraversal;

	private JLabel travResult;

	private XGrid xgrid;

	/**
	 * Constructs the Solver2D by initialising all the attributes and by placing
	 * all the components
	 */
	public Solver2D() {

		super();

		// Initialise object attributes
		this.nOfRows = new JTextField(String.valueOf(rows));
		this.nOfRows.addKeyListener(this);
		blackJFieldText(nOfRows);

		this.nOfCols = new JTextField(String.valueOf(cols));
		this.nOfCols.addKeyListener(this);
		blackJFieldText(nOfCols);

		this.percFilled = new JTextField(String.valueOf(percF));
		this.percFilled.addKeyListener(this);
		blackJFieldText(percFilled);

		this.repPerStep = new JTextField(String.valueOf(repPStep));
		this.repPerStep.addKeyListener(this);
		blackJFieldText(repPerStep);

		this.onlyClosestCB = new JCheckBox();
		this.onlyClosestCB.addChangeListener(this);

		this.selectGridSlide = new JSlider(JSlider.HORIZONTAL, 1, repPStep, 1);
		this.selectGridSlide.addChangeListener(this);
		this.selectGridField = new JTextField("1");
		this.selectGridField.addKeyListener(this);
		this.selectGridSlide.setEnabled(false);
		this.selectGridField.setEnabled(false);

		this.startTraversal = new JButton(ACTION_SOLVE);
		this.startTraversal.addActionListener(this);

		this.travResult = new JLabel("");

		xgrid = new XGrid();

		// create the right column for the settings
		Container settingsPane = new Container();
		settingsPane.setLayout(new GridLayout(0, 2));

		settingsPane.add(new JLabel("number of rows (int > 0)"));
		settingsPane.add(nOfCols);
		settingsPane.add(new JLabel("number of cols (int > 0)"));
		settingsPane.add(nOfRows);
		settingsPane.add(new JLabel("percentage filled (double 0.0 - 1.0)"));
		settingsPane.add(percFilled);
		settingsPane.add(new JLabel("repetitions per step (int > 0)"));
		settingsPane.add(repPerStep);
		settingsPane.add(new JLabel("only closest neighbours"));
		settingsPane.add(onlyClosestCB);

		settingsPane.add(startTraversal);
		settingsPane.add(travResult);

		settingsPane.add(new JLabel("choose a grid"));
		settingsPane.add(new JLabel(""));

		settingsPane.add(selectGridSlide);
		settingsPane.add(selectGridField);

		// set layout and place the various components inside the window
		mainContainer = new Container();
		mainContainer.setLayout(new GridLayout(0, 2));

		mainContainer.add(settingsPane);
		mainContainer.add(xgrid);

		this.add(mainContainer);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		if (arg0.getActionCommand().equals(ACTION_SOLVE)) {
			if (areParametersCorrect()) {
				solve();
			} else {
				this.travResult.setText("Please correct the wrong parameters");
			}
		}

	}

	/**
	 * Solves all the set of the grids
	 */
	private void solve() {

		disableSelection();

		this.travResult.setText("Calculating...");

		int traversableGrids = 0;

		this.gridList = new Grid2D[repPStep];

		for (int i = 0; i < repPStep; i++) {
			Grid2D g = new Grid2D(percF, rows, cols);
			if (g.isTraversable(onlyClosest)) {
				++traversableGrids;
			}
			this.gridList[i] = g;
		}

		String res = String.format("%.2f%% of traversable grids", 100.0
				* traversableGrids / repPStep);

		this.travResult.setText(res);

		this.selectGridSlide.setMaximum(repPStep);
		this.selectGridSlide.setValue(1);
		this.xgrid.setGrid(gridList[0]);
		this.xgrid.repaint();

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

	private void syncSelSlideField() {

		this.selectGridField.setText(String.valueOf(this.selectGridSlide
				.getValue()));
		blackJFieldText(selectGridField);
		this.xgrid.setGrid(gridList[this.selectGridSlide.getValue() - 1]);
		this.xgrid.repaint();

	}

	private void enableSelection() {
		selectGridField.setEnabled(true);
		selectGridSlide.setEnabled(true);
	}

	private void disableSelection() {
		selectGridField.setEnabled(false);
		selectGridSlide.setEnabled(false);
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {

		if (arg0.getSource().equals(this.onlyClosestCB)) {
			this.onlyClosest = onlyClosestCB.isSelected();
		}

		if (arg0.getSource().equals(this.selectGridSlide)) {
			syncSelSlideField();
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

	@Override
	protected boolean areParametersCorrect() {
		boolean areCorrect = true;

		areCorrect &= nOfCols.getForeground().equals(Color.BLACK);
		areCorrect &= nOfRows.getForeground().equals(Color.BLACK);
		areCorrect &= percFilled.getForeground().equals(Color.BLACK);
		areCorrect &= repPerStep.getForeground().equals(Color.BLACK);

		return areCorrect;
	}

}
