import java.util.Random;

import solver.Cell;
import solver.CheeseCakeSolver;

public class CheeseCake {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int dimX = 4;
		int dimY = 4;
		boolean onlyClosest = true;

		double nOfSim = 10000;
		double numOfSteps = 100;
		double percentageFilled = 0.0;

		double sum = 0.0;
		double average = 0.0;

		for (int i = 1; i <= numOfSteps; i++) {

			percentageFilled = i / numOfSteps;

			System.out.printf("if the %.2f%% of the matrix is filled ",
					100 * percentageFilled);

			sum = 0;

			for (int j = 0; j < nOfSim; j++) {

				if (CheeseCakeSolver.isTraversable(
						createGrid(percentageFilled, dimX, dimY), onlyClosest)) {
					sum++;
				}

			}

			average = 100 * sum / nOfSim;

			System.out.printf(
					"the %.2f%% of the generated matrices is traversable \n",
					average);

		}

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

}
