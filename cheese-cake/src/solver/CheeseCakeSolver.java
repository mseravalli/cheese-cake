package solver;


public class CheeseCakeSolver {

	public static boolean isTraversable(Cell[][] grid, boolean onlyClosest) {

		boolean isTrav = false;

		for (int j = 0; j < grid[0].length; j++) {
			isTrav = isTrav || isTraversable(grid, 0, j, onlyClosest);
		}

		return isTrav;

	}

	private static boolean isTraversable(Cell[][] grid, int x, int y,
			boolean onlyClosest) {

		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
			return false;
		}

		if (!grid[x][y].isCrossable() || grid[x][y].getStatus() != Cell.WHITE) {
			return false;
		}

		if (x == grid.length - 1) {
			return true;
		}

		grid[x][y].setStatus(Cell.GREY);

		boolean isTrav = false;

		isTrav = isTrav || isTraversable(grid, x - 1, y, onlyClosest);
		isTrav = isTrav || isTraversable(grid, x + 1, y, onlyClosest);
		isTrav = isTrav || isTraversable(grid, x, y + 1, onlyClosest);
		isTrav = isTrav || isTraversable(grid, x, y - 1, onlyClosest);

		if (!onlyClosest) {
			isTrav = isTrav || isTraversable(grid, x - 1, y - 1, onlyClosest);
			isTrav = isTrav || isTraversable(grid, x - 1, y + 1, onlyClosest);
			isTrav = isTrav || isTraversable(grid, x + 1, y + 1, onlyClosest);
			isTrav = isTrav || isTraversable(grid, x + 1, y - 1, onlyClosest);
		}

		grid[x][y].setStatus(Cell.BLACK);

		return isTrav;

	}

	public static boolean isTraversable(Cell[][][] grid, boolean onlyClosest) {
		boolean isTrav = false;

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				isTrav = isTrav || isTraversable(grid, 0, j, i, onlyClosest);
			}
		}

		return isTrav;
	}

	private static boolean isTraversable(Cell[][][] grid, int x, int y, int z,
			boolean onlyClosest) {

		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || z < 0
				|| z >= grid[0][0].length) {
			return false;
		}

		if (!grid[x][y][z].isCrossable()
				|| grid[x][y][z].getStatus() != Cell.WHITE) {
			return false;
		}

		if (z == grid[0][0].length - 1) {
			return true;
		}

		grid[x][y][z].setStatus(Cell.GREY);

		boolean isTrav = false;

		isTrav = isTrav || isTraversable(grid, x - 1, y, z, onlyClosest);
		isTrav = isTrav || isTraversable(grid, x + 1, y, z, onlyClosest);
		isTrav = isTrav || isTraversable(grid, x, y - 1, z, onlyClosest);
		isTrav = isTrav || isTraversable(grid, x, y + 1, z, onlyClosest);
		isTrav = isTrav || isTraversable(grid, x, y, z - 1, onlyClosest);
		isTrav = isTrav || isTraversable(grid, x, y, z + 1, onlyClosest);

		if (!onlyClosest) {
			isTrav = isTrav
					|| isTraversable(grid, x - 1, y - 1, z, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x - 1, y + 1, z, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x + 1, y + 1, z, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x + 1, y - 1, z, onlyClosest);

			isTrav = isTrav
					|| isTraversable(grid, x - 1, y - 1, z - 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x - 1, y + 1, z - 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x + 1, y + 1, z - 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x + 1, y - 1, z - 1, onlyClosest);

			isTrav = isTrav
					|| isTraversable(grid, x - 1, y - 1, z + 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x - 1, y + 1, z + 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x + 1, y + 1, z + 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x + 1, y - 1, z + 1, onlyClosest);

			isTrav = isTrav
					|| isTraversable(grid, x, y - 1, z + 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x, y + 1, z + 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x, y - 1, z - 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x, y - 1, z - 1, onlyClosest);

			isTrav = isTrav
					|| isTraversable(grid, x + 1, y, z + 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x - 1, y, z + 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x + 1, y, z - 1, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x - 1, y, z - 1, onlyClosest);
		}

		grid[x][y][z].setStatus(Cell.BLACK);

		return isTrav;

	}

}
