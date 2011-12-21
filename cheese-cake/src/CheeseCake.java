import gui.MainWindow;

import solver.CheeseCakeSolver;

public class CheeseCake {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
                MainWindow.runMainWindow();
            }
        });
	}
	

	public static void runSimulation() {
		int dimX = 10;
		int dimY = 10;
		boolean onlyClosest = true;

		double nOfSim = 10000;
		double numOfSteps = 100;
		double percentageFilled = 0.0;

		double sum = 0.0;
		double average = 0.0;

		System.out.printf("Dimension of the matix: %d x %d \n", dimX, dimY);

		for (int i = 1; i <= numOfSteps; i++) {

			percentageFilled = i / numOfSteps;

			System.out.printf("if the %.2f%% of the matrix is filled ",
					100 * percentageFilled);

			sum = 0;

			for (int j = 0; j < nOfSim; j++) {

				if (CheeseCakeSolver.isTraversable(
						CheeseCakeSolver.createGrid(percentageFilled, dimX, dimY), onlyClosest)) {
					sum++;
				}

			}

			average = 100 * sum / nOfSim;

			System.out.printf(
					"the %.2f%% of the generated matrices is traversable \n",
					average);

		}
	}

}
