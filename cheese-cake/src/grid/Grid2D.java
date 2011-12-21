package grid;

import java.util.Random;

public class Grid2D extends Grid {

	private Cell[][] g;

	public Grid2D(double percFilled, int rows, int cols) {
		super(percFilled);
		g = new Cell[rows][cols];

		Random randomGenerator = new Random();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (randomGenerator.nextDouble() < super.percFilled) {
					g[i][j] = new Cell(true);
				} else {
					g[i][j] = new Cell(false);
				}
			}
		}

	}
	
	public Grid2D(Cell[][] g){
		this.g = g;
	}

	@Override
	public boolean isTraversable(boolean onlyClosest) {
		boolean isTrav = false;

		for (int i = 0; i < g.length; i++) {
			isTrav = isTrav || isTraversable(i, 0, onlyClosest);
		}

		return isTrav;
	}

	private boolean isTraversable(int x, int y, boolean onlyClosest) {
		if (x < 0 || x >= g.length || y < 0 || y >= g[0].length) {
			return false;
		}

		if (!g[x][y].isCrossable() || g[x][y].getStatus() != Cell.WHITE) {
			return false;
		}

		if (y == g[0].length - 1) {
			g[x][y].setSolution(true);
			return true;
		}

		g[x][y].setStatus(Cell.GREY);

		boolean isTrav = false;

		if (!isTrav)
			isTrav = isTrav || isTraversable(x, y + 1, onlyClosest);
		if (!isTrav)
			isTrav = isTrav || isTraversable(x - 1, y, onlyClosest);
		if (!isTrav)
			isTrav = isTrav || isTraversable(x + 1, y, onlyClosest);
		if (!isTrav)
			isTrav = isTrav || isTraversable(x, y - 1, onlyClosest);

		if (!onlyClosest) {
			if (!isTrav)
				isTrav = isTrav || isTraversable(x - 1, y + 1, onlyClosest);
			if (!isTrav)
				isTrav = isTrav || isTraversable(x + 1, y + 1, onlyClosest);
			if (!isTrav)
				isTrav = isTrav || isTraversable(x + 1, y - 1, onlyClosest);
			if (!isTrav)
				isTrav = isTrav || isTraversable(x - 1, y - 1, onlyClosest);
		}

		g[x][y].setStatus(Cell.BLACK);

		if (isTrav) {
			g[x][y].setSolution(true);
		}

		return isTrav;
	}

	@Override
	public void cleanGrid() {
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				g[i][j].setSolution(false);
				g[i][j].setStatus(Cell.WHITE);
			}
		}
	}

}
