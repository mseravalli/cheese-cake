package solver;
import java.util.Random;


public class CheeseCake {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int dimX = 10;
		int dimY = 6;
		boolean onlyClosest = false;

		Cell[][] grid = createGrid(dimX, dimY);

		for (int i = 0; i < dimX; i++) {

			for (int j = 0; j < dimY; j++) {

				System.out.printf("%b ", grid[i][j].isCrossable());

			}

			System.out.println();

		}

		boolean isTrav = false;

		for (int j = 0; j < dimY; j++) {
			isTrav = isTrav || isTraversable(grid, 0, j, onlyClosest);
		}

		System.out.println("is crossable: " + isTrav);

	}

	public static Cell[][] createGrid(int dimX, int dimY) {

		Cell[][] grid = new Cell[dimX][dimY];

		Random randomGenerator = new Random();

		for (int i = 0; i < dimX; i++) {

			for (int j = 0; j < dimY; j++) {

				if (randomGenerator.nextDouble() > 0.5) {
					grid[i][j] = new Cell(true);
				} else {
					grid[i][j] = new Cell(false);
				}

			}

		}

		return grid;

	}

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

	public static boolean isTraversable(Cell[][][] grid, int x, int y, int z,
			boolean onlyClosest) {

		if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || z < 0
				|| z >= grid[0][0].length) {
			return false;
		}

		if (!grid[x][y][z].isCrossable()
				|| grid[x][y][z].getStatus() != Cell.WHITE) {
			return false;
		}

		if (x == grid.length - 1) {
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
					|| isTraversable(grid, x + 1, y - 1, z, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x - 1, y - 1, z, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x + 1, y + 1, z, onlyClosest);
			isTrav = isTrav
					|| isTraversable(grid, x - 1, y + 1, z, onlyClosest);
		}

		grid[x][y][z].setStatus(Cell.BLACK);

		return isTrav;

	}

}
