package test;

import junit.framework.TestCase;
import grid.Cell;
import grid.Grid;
import grid.Grid3D;

import org.junit.Test;

public class Grid3DTest extends TestCase{

	private final boolean O = false;
	private final boolean X = true;
	
	@Test
	public void testIsTraversableBoolean() {
		boolean onlyClosest;

        Cell[][][] cells1 = {{{new Cell(X), new Cell(X), new Cell(O)},
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
        
        Grid g1 = new Grid3D(cells1);
        onlyClosest = true;
        assertEquals(true, g1.isTraversable(onlyClosest));

        //----------
        
        Cell[][][] cells2 = {{{new Cell(X), new Cell(X), new Cell(X), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(O), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(O), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(O), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(O), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(O), new Cell(O)}} ,
             
             
                             {{new Cell(O), new Cell(O), new Cell(X), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(O), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(O), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(O), new Cell(O)},
                              {new Cell(O), new Cell(X), new Cell(O), new Cell(O)},
                              {new Cell(O), new Cell(X), new Cell(X), new Cell(X)}},
             
                
                             {{new Cell(O), new Cell(O), new Cell(X), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(X), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(X), new Cell(O)},
                              {new Cell(O), new Cell(X), new Cell(X), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(O), new Cell(O)},
                              {new Cell(O), new Cell(O), new Cell(O), new Cell(O)}}};  
        
        Grid3D g2 = new Grid3D(cells2);
        onlyClosest = true;
        assertEquals(false, g2.isTraversable(onlyClosest));
        
        g2.cleanGrid();      

        onlyClosest = false;
        assertEquals(true, g2.isTraversable(onlyClosest));
        
        //-------------------
        
        Cell[][][] cells3 = {{{   new Cell(O), new Cell(O), new Cell(X), new Cell(O)}},
				                {{new Cell(O), new Cell(O), new Cell(X), new Cell(X)}},
				                {{new Cell(O), new Cell(O), new Cell(X), new Cell(X)}},
				                {{new Cell(O), new Cell(X), new Cell(O), new Cell(O)}}};
        
        Grid3D g3 = new Grid3D(cells3);
        onlyClosest = true;
        assertEquals(false, g3.isTraversable(onlyClosest));
	}

}
