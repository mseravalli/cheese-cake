package grid;

public abstract class Grid {

	/**
	 * Initialises an empty grid
	 */
	public Grid() {
		super();
	}

	/**
	 * Determines whether a Grid is traversable
	 * 
	 * @param onlyClosest
	 *            specifies if only immediate neighbours will be taken into
	 *            account
	 * @return
	 */
	public abstract boolean isTraversable(boolean onlyClosest);

	/**
	 * Sets the status of every cell to non traversed this needs to be done
	 * before starting the traversal again
	 */
	public abstract void cleanGrid();

}
