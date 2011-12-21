package gui;

import grid.Cell;
import grid.Grid2D;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;


public class XGrid extends JComponent {

	private static final long serialVersionUID = 7162799381588745624L;

	private Grid2D grid = null;

	private int height = 20;
	private int width = 20;

	public XGrid(Grid2D grid) {

		this.grid = grid;

	}

	public void paint(Graphics g) {

		width = this.getSize().width / (grid.getRows());
		height = this.getSize().height / (grid.getCols());

		for (int i = 0; i < grid.getRows(); i++) {
			for (int j = 0; j < grid.getCols(); j++) {

				if (grid.isSolution(i, j)) {
					g.setColor(Color.blue);
				} else if (grid.isCrossable(i, j)) {
					g.setColor(Color.yellow);
				} else {
					g.setColor(Color.gray);
				}

				g.fillRect((width) * j, (height) * i, width - 1, height - 1);
			}
		}

	}

	public void setGrid(Grid2D grid) {
		this.grid = grid;
	}

}
