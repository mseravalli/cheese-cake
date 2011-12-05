package test;

import static org.junit.Assert.*;

import org.junit.Test;

import solver.Cell;
import solver.CheeseCakeSolver;

public class CheeseCakeSolverTest {

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
		assertEquals(true, CheeseCakeSolver.isTraversable(grid1, onlyClosest));
		
		Cell[][] grid2 = {{new Cell(X), new Cell(O), new Cell(O), new Cell(O), new Cell(O) },
				  		  {new Cell(X), new Cell(O), new Cell(O), new Cell(O), new Cell(O) },
				  		  {new Cell(X), new Cell(O), new Cell(X), new Cell(X), new Cell(X) },
				  		  {new Cell(X), new Cell(X), new Cell(O), new Cell(O), new Cell(X) },
				  		  {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) },
				  		  {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) }};		

		onlyClosest = true;
		assertEquals(false, CheeseCakeSolver.isTraversable(grid2, onlyClosest));
		
		Cell[][] grid3 = {{new Cell(X), new Cell(O), new Cell(O), new Cell(O), new Cell(O) },
		  		  		  {new Cell(X), new Cell(O), new Cell(O), new Cell(O), new Cell(O) },
		  		  		  {new Cell(X), new Cell(O), new Cell(X), new Cell(X), new Cell(X) },
		  		  		  {new Cell(X), new Cell(X), new Cell(O), new Cell(O), new Cell(X) },
		  		  		  {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) },
		  		  		  {new Cell(O), new Cell(O), new Cell(O), new Cell(O), new Cell(X) }};
		
		onlyClosest = false;
		assertEquals(true, CheeseCakeSolver.isTraversable(grid3, onlyClosest));
		
	}
	
	@Test
	public void testIsTraversableCellArrayArrayArrayBoolean() {
		
		boolean onlyClosest;

		Cell[][][] grid1 = {{{new Cell(X), new Cell(X), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)}} ,
						     
						     
						    {{new Cell(O), new Cell(X), new Cell(O)},
				 		     {new Cell(O), new Cell(O), new Cell(O)},
				 		     {new Cell(O), new Cell(O), new Cell(O)},
				 		     {new Cell(X), new Cell(O), new Cell(O)},
				 		     {new Cell(X), new Cell(O), new Cell(O)},
				 		     {new Cell(X), new Cell(X), new Cell(X)}},
						     
					  	   	
						    {{new Cell(O), new Cell(X), new Cell(O)},
				 		     {new Cell(O), new Cell(X), new Cell(O)},
				 		     {new Cell(O), new Cell(X), new Cell(O)},
				 		     {new Cell(X), new Cell(X), new Cell(O)},
				 		     {new Cell(O), new Cell(O), new Cell(O)},
				 		     {new Cell(O), new Cell(O), new Cell(O)}}};		
		
		onlyClosest = true;
		assertEquals(true, CheeseCakeSolver.isTraversable(grid1, onlyClosest));
	
	
		Cell[][][] grid2 = {{{new Cell(X), new Cell(X), new Cell(O)},
		     				 {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)}} ,
		     
		     
						    {{new Cell(O), new Cell(X), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(X), new Cell(O), new Cell(O)},
						     {new Cell(X), new Cell(X), new Cell(X)}},
		     
	  	   	
						    {{new Cell(O), new Cell(X), new Cell(O)},
						     {new Cell(O), new Cell(X), new Cell(O)},
						     {new Cell(O), new Cell(X), new Cell(O)},
						     {new Cell(X), new Cell(X), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)}}};		
		
		onlyClosest = true;
		assertEquals(false, CheeseCakeSolver.isTraversable(grid2, onlyClosest));
		
		
		Cell[][][] grid3 = {{{new Cell(X), new Cell(X), new Cell(O)},
							 {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)}} ,
				
				
						    {{new Cell(O), new Cell(X), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(X), new Cell(O), new Cell(O)},
						     {new Cell(X), new Cell(X), new Cell(X)}},
				
				
						    {{new Cell(O), new Cell(X), new Cell(O)},
						     {new Cell(O), new Cell(X), new Cell(O)},
						     {new Cell(O), new Cell(X), new Cell(O)},
						     {new Cell(X), new Cell(X), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)},
						     {new Cell(O), new Cell(O), new Cell(O)}}};		

		onlyClosest = false;
		assertEquals(true, CheeseCakeSolver.isTraversable(grid3, onlyClosest));
	
	
	}

}