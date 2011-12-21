package gui;

import grid.Grid2D;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 2740437090361841747L;

	private static MainWindow mainWindow = null;

	private static final String ACTION_CREATE = "Create Grid";
	private static final String ACTION_CLEAN = "Clean Grid";
	private static final String ACTION_TRAVERSAL = "Start Traversal";

	private XGrid xgrid;

	private Grid2D grid;

	private JTextField nOfRows;
	private JTextField nOfCols;
	private JTextField percFilled;
	private JCheckBox onlyClosest;

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

		// initial grid values
		int cols = 10;
		int rows = 10;
		double percF = 0.5;

		// initialize object attributes
		this.grid = new Grid2D(percF, rows, cols);
		xgrid = new XGrid(this.grid);

		this.nOfRows = new JTextField(String.valueOf(rows));
		this.nOfCols = new JTextField(String.valueOf(cols));
		this.percFilled = new JTextField(String.valueOf(0.5));
		this.onlyClosest = new JCheckBox();

		this.createGrid = new JButton(ACTION_CREATE);
		this.createGrid.addActionListener(this);
		this.cleanGrid = new JButton(ACTION_CLEAN);
		this.cleanGrid.addActionListener(this);
		this.startTraversal = new JButton(ACTION_TRAVERSAL);
		this.startTraversal.addActionListener(this);

		this.travResult = new JLabel("");

		// window settings
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setSize(450, 300);

		// create the right column for the settings
		Container settingsPane = new Container();
		settingsPane.setLayout(new GridLayout(0, 2));

		settingsPane.add(new JLabel("number of rows"));
		settingsPane.add(nOfCols);
		settingsPane.add(new JLabel("number of cols"));
		settingsPane.add(nOfRows);
		settingsPane.add(new JLabel("percentage filled"));
		settingsPane.add(percFilled);
		settingsPane.add(new JLabel("only closest"));
		settingsPane.add(onlyClosest);

		settingsPane.add(createGrid);
		settingsPane.add(cleanGrid);
		settingsPane.add(startTraversal);
		settingsPane.add(travResult);

		// set layout and place the various components inside the window
		this.getContentPane().setLayout(new BorderLayout(5, 5));

		this.getContentPane().add(xgrid, BorderLayout.CENTER);

		this.getContentPane().add(settingsPane, BorderLayout.EAST);

		this.setVisible(true);

	}

//	public Cell[][] getGrid() {
//		return grid;
//	}

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

		if (arg0.getActionCommand().equals(ACTION_TRAVERSAL)) {

			grid.cleanGrid();
			boolean isTrav = grid.isTraversable(onlyClosest.isSelected());
			
			if(isTrav){
				this.travResult.setText("Traversable");
			} else {
				this.travResult.setText("NOT traversable");
			}
			
			this.repaint();
		}

	}

	private void createGrid() {
		double percentageFilled = Double.valueOf(this.percFilled.getText());
		int dimX = Integer.valueOf(this.nOfRows.getText());
		int dimY = Integer.valueOf(this.nOfCols.getText());
		this.setGrid(new Grid2D(percentageFilled, dimX, dimY));
	}

}
