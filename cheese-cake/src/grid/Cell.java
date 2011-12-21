package grid;
public class Cell {

	// BLACK means that the node was already visited
	public static final byte BLACK = 0;

	// GREY means that the node is being visited
	public static final byte GREY = 1;

	// WHITE means not visited
	public static final byte WHITE = 2;

	private byte status;

	private boolean isCrossable;
	
	private boolean isSolution;

	public Cell(boolean isCrossable) {
		super();
		this.isCrossable = isCrossable;
		this.status = WHITE;
		this.isSolution = false;
	}

	public byte getStatus() {
		return status;
	}

	public void setStatus(byte status) {
		this.status = status;
	}

	public boolean isCrossable() {
		return isCrossable;
	}
	
	public boolean isSolution() {
		return isSolution;
	}

	public void setSolution(boolean isSolution) {
		this.isSolution = isSolution;
	}

}
