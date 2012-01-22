import gui.MainWindow;

/*! \mainpage 
 * The project is divided into two parts the 2D solver and the 3D
 * simulator. <br />
 * In the first one, the 2D solver, the user is allowed to test and visualise
 * the solution for 2D matrices. The parameters that can be set are the
 * dimensions of the matrix, the percentage of traversable cells, how the
 * traversal will be performed and how many repetitions should be computed. Once
 * the calculations are performed all the computed solutions are available to
 * the user and can be visualised. <br />
 * In the second part instead, the 3D simulator, the user can perform a
 * simulation of the behaviour of a matrix of a determined dimension. The
 * simulation checks whether a matrix is crossable taking into account all
 * the different percentages of crossable cells. Once the simulation is
 * performed the result is displayed to the user through a graph. By moving the
 * mouse on the graph is possible to obtain more information about single
 * solutions. The users can specify the dimensions of the matrix, how the
 * traversal will be performed, how many different percentages of traversable
 * cells should be taken into account and how many repetitions per step should
 * be computed.
 */

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

}
