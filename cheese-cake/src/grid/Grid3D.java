package grid;

import java.util.Random;

public class Grid3D extends Grid {

	private Cell[][][] g;

	/**
	 * Constructs a 3D grid of Cell with the given dimensions
	 * 
	 * @param percFilled
	 *            percentage of non empty (crossable) Cell
	 * @param x
	 * @param y
	 * @param z
	 */
	public Grid3D(double percFilled, int x, int y, int z) {
		super();
		g = new Cell[x][y][z];

		Random randomGenerator = new Random();

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				for (int k = 0; k < z; k++) {
					if (randomGenerator.nextDouble() < percFilled) {
						g[i][j][k] = new Cell(true);
					} else {
						g[i][j][k] = new Cell(false);
					}
				}
			}
		}

	}

	/**
	 * Constructs a 2D grid from the given Cell array
	 * 
	 * @param g
	 */
	public Grid3D(Cell[][][] g) {
		this.g = g;
	}

	@Override
	public boolean isTraversable(boolean onlyClosest) {
		boolean isTrav = false;

		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				isTrav = isTrav || isTraversable(onlyClosest, i, j, 0);
			}
		}

		return isTrav;
	}

	/**
	 * This method starts looking at the solution starting from the given
	 * coordinates
	 * 
	 * @param onlyClosest
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	protected boolean isTraversable(boolean onlyClosest, int x, int y, int z) {
		if (x < 0 || x >= g.length || y < 0 || y >= g[0].length || z < 0
				|| z >= g[0][0].length) {
			return false;
		}

		if (!g[x][y][z].isCrossable() || g[x][y][z].getStatus() != Cell.WHITE) {
			return false;
		}

		if (z == g[0][0].length - 1) {
			return true;
		}

		g[x][y][z].setStatus(Cell.GREY);

		boolean isTrav = false;

		isTrav = isTrav || isTraversable(onlyClosest, x - 1, y, z);
		isTrav = isTrav || isTraversable(onlyClosest, x + 1, y, z);
		isTrav = isTrav || isTraversable(onlyClosest, x, y - 1, z);
		isTrav = isTrav || isTraversable(onlyClosest, x, y + 1, z);
		isTrav = isTrav || isTraversable(onlyClosest, x, y, z - 1);
		isTrav = isTrav || isTraversable(onlyClosest, x, y, z + 1);

		if (!onlyClosest) {
			isTrav = isTrav || isTraversable(onlyClosest, x - 1, y - 1, z);
			isTrav = isTrav || isTraversable(onlyClosest, x - 1, y + 1, z);
			isTrav = isTrav || isTraversable(onlyClosest, x + 1, y - 1, z);
			isTrav = isTrav || isTraversable(onlyClosest, x + 1, y + 1, z);

			isTrav = isTrav || isTraversable(onlyClosest, x - 1, y - 1, z - 1);
			isTrav = isTrav || isTraversable(onlyClosest, x - 1, y + 1, z - 1);
			isTrav = isTrav || isTraversable(onlyClosest, x + 1, y - 1, z - 1);
			isTrav = isTrav || isTraversable(onlyClosest, x + 1, y + 1, z - 1);

			isTrav = isTrav || isTraversable(onlyClosest, x - 1, y - 1, z + 1);
			isTrav = isTrav || isTraversable(onlyClosest, x - 1, y + 1, z + 1);
			isTrav = isTrav || isTraversable(onlyClosest, x + 1, y - 1, z + 1);
			isTrav = isTrav || isTraversable(onlyClosest, x + 1, y + 1, z + 1);

			isTrav = isTrav || isTraversable(onlyClosest, x, y - 1, z - 1);
			isTrav = isTrav || isTraversable(onlyClosest, x, y - 1, z + 1);
			isTrav = isTrav || isTraversable(onlyClosest, x, y + 1, z - 1);
			isTrav = isTrav || isTraversable(onlyClosest, x, y + 1, z + 1);

			isTrav = isTrav || isTraversable(onlyClosest, x - 1, y, z - 1);
			isTrav = isTrav || isTraversable(onlyClosest, x - 1, y, z + 1);
			isTrav = isTrav || isTraversable(onlyClosest, x + 1, y, z - 1);
			isTrav = isTrav || isTraversable(onlyClosest, x + 1, y, z + 1);
		}

		g[x][y][z].setStatus(Cell.BLACK);

		return isTrav;
	}

	@Override
	public void cleanGrid() {
		for (int i = 0; i < g.length; i++) {
			for (int j = 0; j < g[0].length; j++) {
				for (int k = 0; k < g[0][0].length; k++) {
					g[i][j][k].setSolution(false);
					g[i][j][k].setStatus(Cell.WHITE);
				}
			}
		}

	}

}
