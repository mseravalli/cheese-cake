package test;

import junit.framework.TestCase;
import grid.Cell;
import grid.Grid;
import grid.Grid2D;

import org.junit.Test;

public class Grid2DTest extends TestCase {

	private final boolean O = false;
	private final boolean X = true;
	
	@Test
	public void testIsTraversable() {
		boolean onlyClosest;

		Cell[][] cells1 = {{new Cell(X), new Cell(X), new Cell(X), new Cell(X), new Cell(O) },
						   {new Cell(O), new Cell(O), new Cell(O), new Cell(X), new Cell(O) },
						   {new Cell(O), new Cell(X), new Cell(X), new Cell(X), new Cell(O) },
						   {new Cell(O), new Cell(X), new Cell(O), new Cell(O), new Cell(O) },
						   {new Cell(O), new Cell(X), new Cell(X), new Cell(X), new Cell(X) },
						   {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(O) }};		
		
		Grid g1 = new Grid2D(cells1);
		onlyClosest = true;
		assertEquals(true, g1.isTraversable(onlyClosest));
		
		Cell[][] cells2 = {{new Cell(X), new Cell(X), new Cell(X), new Cell(X), new Cell(O) },
						   {new Cell(O), new Cell(O), new Cell(O), new Cell(X), new Cell(O) },
						   {new Cell(O), new Cell(X), new Cell(X), new Cell(X), new Cell(O) },
						   {new Cell(O), new Cell(X), new Cell(O), new Cell(O), new Cell(O) },
						   {new Cell(O), new Cell(X), new Cell(X), new Cell(X), new Cell(O) },
						   {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) }};		

		Grid g2 = new Grid2D(cells2);
		onlyClosest = true;
		assertEquals(false, g2.isTraversable(onlyClosest));
		
		g2.cleanGrid();
		
		onlyClosest = false;
		assertEquals(true, g2.isTraversable(onlyClosest));
		
	}

}
