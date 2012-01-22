package grid;

public class Cell {

	// BLACK means that the node was already visited
	public static final byte BLACK = 0;

	// GREY means that the node is being visited
	public static final byte GREY = 1;

	// WHITE means not visited
	public static final byte WHITE = 2;

	// Determines if the cell was alredy crossed or not
	private byte status;

	private boolean isCrossable;

	// Determines whether the solution crosses this cell
	private boolean isSolution;

	/**
	 * Constructs a cell by specifying whether it could be crossed or not
	 * 
	 * @param isCrossable
	 */
	public Cell(boolean isCrossable) {
		super();
		this.isCrossable = isCrossable;
		this.status = WHITE;
		this.isSolution = false;
	}

	/**
	 * Return the status
	 * 
	 * @return
	 */
	public byte getStatus() {
		return status;
	}

	/**
	 * Sets the status
	 * 
	 * @param status
	 */
	public void setStatus(byte status) {
		this.status = status;
	}

	/**
	 * Returns if it is crossable
	 * 
	 * @return
	 */
	public boolean isCrossable() {
		return isCrossable;
	}

	/**
	 * Returns if it is part of the path to the solution
	 * 
	 * @return
	 */
	public boolean isSolution() {
		return isSolution;
	}

	/**
	 * Sets whether is is part of the solution
	 * 
	 * @param isSolution
	 */
	public void setSolution(boolean isSolution) {
		this.isSolution = isSolution;
	}

}
