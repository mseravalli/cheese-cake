package test;

import static org.junit.Assert.*;

import org.junit.Test;

import solver.Cell;
import solver.CheeseCake;

public class CheeseCakeTest {

	private final boolean O = false;
	private final boolean X = true;
	
	@Test
	public void testIsTraversableCellArrayArrayBoolean() {
		
		boolean onlyClosest;

		Cell[][] grid1 = {{new Cell(X), new Cell(O), new Cell(O), new Cell(O), new Cell(O) },
						  {new Cell(X), new Cell(O), new Cell(O), new Cell(O), new Cell(O) },
						  {new Cell(X), new Cell(O), new Cell(X), new Cell(X), new Cell(X) },
						  {new Cell(X), new Cell(X), new Cell(X), new Cell(O), new Cell(X) },
						  {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) },
						  {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) }};		
		
		onlyClosest = true;
		assertEquals(true, CheeseCake.isTraversable(grid1, onlyClosest));
		
		Cell[][] grid2 = {{new Cell(X), new Cell(O), new Cell(O), new Cell(O), new Cell(O) },
				  		  {new Cell(X), new Cell(O), new Cell(O), new Cell(O), new Cell(O) },
				  		  {new Cell(X), new Cell(O), new Cell(X), new Cell(X), new Cell(X) },
				  		  {new Cell(X), new Cell(X), new Cell(O), new Cell(O), new Cell(X) },
				  		  {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) },
				  		  {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) }};		

		onlyClosest = true;
		assertEquals(false, CheeseCake.isTraversable(grid2, onlyClosest));
		
		Cell[][] grid3 = {{new Cell(X), new Cell(O), new Cell(O), new Cell(O), new Cell(O) },
		  		  		  {new Cell(X), new Cell(O), new Cell(O), new Cell(O), new Cell(O) },
		  		  		  {new Cell(X), new Cell(O), new Cell(X), new Cell(X), new Cell(X) },
		  		  		  {new Cell(X), new Cell(X), new Cell(O), new Cell(O), new Cell(X) },
		  		  		  {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) },
		  		  		  {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) }};
		
		onlyClosest = false;
		assertEquals(true, CheeseCake.isTraversable(grid3, onlyClosest));
		
	}

}