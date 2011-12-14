package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import solver.Cell;

public class XGrid extends JComponent {

	private static final long serialVersionUID = 7162799381588745624L;

	private Cell[][] grid = null;

	private int height = 20;
	private int width = 20;

	public XGrid(Cell[][] grid) {

		this.grid = grid;

	}

	public void paint(Graphics g) {

		width = this.getSize().width / (grid[0].length);
		height = this.getSize().height / (grid.length);

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {

				if (this.grid[i][j].isSolution()) {
					g.setColor(Color.blue);
				} else if (this.grid[i][j].isCrossable()) {
					g.setColor(Color.yellow);
				} else {
					g.setColor(Color.gray);
				}

				g.fillRect((width) * j, (height) * i, width - 1, height - 1);
			}
		}

	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}

}
