package gui;

import grid.Grid2D;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

public class XGrid extends JComponent {

	private static final long serialVersionUID = 7162799381588745624L;

	private Grid2D grid = null;

	private int height = 20;
	private int width = 20;

	/**
	 * Constructs the grid;
	 */
	public XGrid() {
		super();
	}

	/**
	 * Paints the grid. The crossable cells are displayed as yellow, while the
	 * non crossable ones as grey. Is a solution is found the cells that are
	 * part of the solution are painted in blue.
	 */
	public void paint(Graphics g) {

		if (this.grid == null) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, this.getSize().width, this.getSize().height);
			return;
		}

		width = this.getSize().width / (grid.getRows());
		height = this.getSize().height / (grid.getCols());

		// width = this.getSize().width / (grid.getCols());
		// height = this.getSize().height / (grid.getRows());

		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {

				if (grid.isSolution(i, j)) {
					g.setColor(Color.blue);
				} else if (grid.isCrossable(i, j)) {
					g.setColor(Color.yellow);
				} else {
					g.setColor(Color.gray);
				}

				g.fillRect((width) * i, (height) * j, width - 1, height - 1);
			}
		}

	}

	/**
	 * Sets the grid that will be displayed
	 * 
	 * @param grid
	 */
	public void setGrid(Grid2D grid) {
		this.grid = grid;
	}

}
