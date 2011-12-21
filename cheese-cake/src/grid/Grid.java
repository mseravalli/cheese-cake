package grid;

public abstract class Grid {

	protected double percFilled;
	
	public Grid(){
		super();
	}
	
	public Grid(double percFilled){
		super();
		this.percFilled = percFilled;
	}
	
	public abstract boolean isTraversable(boolean onlyClosest);
	
	public abstract void cleanGrid();
	
}
