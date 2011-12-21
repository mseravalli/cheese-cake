package grid;

import java.util.Random;

public class CheeseCakeSolver {

	public static boolean isTraversable(Cell[][] grid, boolean onlyClosest) {

		boolean isTrav = false;

		for (int i = 0; i < grid.length; i++) {
			isTrav = isTrav || isTraversable(grid, i, 0, onlyClosest);
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

		if (y == grid[0].length - 1) {
			grid[x][y].setSolution(true);
			return true;
		}

		grid[x][y].setStatus(Cell.GREY);

		boolean isTrav = false;

		if (!isTrav)
			isTrav = isTrav || isTraversable(grid, x, y + 1, onlyClosest);
		if (!isTrav)
			isTrav = isTrav || isTraversable(grid, x - 1, y, onlyClosest);
		if (!isTrav)
			isTrav = isTrav || isTraversable(grid, x + 1, y, onlyClosest);
		if (!isTrav)
			isTrav = isTrav || isTraversable(grid, x, y - 1, onlyClosest);

		if (!onlyClosest) {
			if (!isTrav)
				isTrav = isTrav
						|| isTraversable(grid, x - 1, y + 1, onlyClosest);
			if (!isTrav)
				isTrav = isTrav
						|| isTraversable(grid, x + 1, y + 1, onlyClosest);
			if (!isTrav)
				isTrav = isTrav
						|| isTraversable(grid, x + 1, y - 1, onlyClosest);
			if (!isTrav)
				isTrav = isTrav
						|| isTraversable(grid, x - 1, y - 1, onlyClosest);
		}

		grid[x][y].setStatus(Cell.BLACK);

		if (isTrav) {
			grid[x][y].setSolution(true);
		}

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

	public static Cell[][] createGrid(double percentageFilled, int dimX,
			int dimY) {

		Cell[][] grid = new Cell[dimX][dimY];

		Random randomGenerator = new Random();

		for (int i = 0; i < dimX; i++) {

			for (int j = 0; j < dimY; j++) {

				if (randomGenerator.nextDouble() < percentageFilled) {
					grid[i][j] = new Cell(true);
				} else {
					grid[i][j] = new Cell(false);
				}

			}

		}

		return grid;

	}

	public static Cell[][][] createGrid(double percentageFilled, int dimX,
			int dimY, int dimZ) {

		Cell[][][] grid = new Cell[dimX][dimY][dimZ];

		Random randomGenerator = new Random();

		for (int i = 0; i < dimX; i++) {

			for (int j = 0; j < dimY; j++) {

				for (int k = 0; k < dimZ; k++) {

					if (randomGenerator.nextDouble() < percentageFilled) {
						grid[i][j][k] = new Cell(true);
					} else {
						grid[i][j][k] = new Cell(false);
					}

				}

			}

		}

		return grid;

	}

	public static void cleanGrid(Cell[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				grid[i][j].setSolution(false);
				grid[i][j].setStatus(Cell.WHITE);
			}
		}
	}
}
