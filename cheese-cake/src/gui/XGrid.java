package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;

import solver.Cell;

public class XGrid extends JComponent {

	private static final long serialVersionUID = 7162799381588745624L;

	private Cell[][] grid = null;

	private final int HEIGHT = 20;
	private final int WIDTH = 20;

	public XGrid(Cell[][] grid) {

		this.grid = grid;
		
	}

	public void paint(Graphics g) {
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {

				if (this.grid[i][j].isSolution()) {
					g.setColor(Color.blue);
				} else if (this.grid[i][j].isCrossable())  {
					g.setColor(Color.yellow);
				} else {
					g.setColor(Color.gray);
				}

				g.fillRect((HEIGHT + 1) * i, (WIDTH + 1) * j, HEIGHT, WIDTH);
			}
		}

	}
	
	public void setGrid(Cell[][] grid){
		this.grid = grid;
	}

}
