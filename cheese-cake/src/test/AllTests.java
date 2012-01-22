package test;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTests.class.getName());
		//$JUnit-BEGIN$
		suite.addTestSuite(Grid2DTest.class);
		suite.addTestSuite(Grid3DTest.class);
		//$JUnit-END$
		return suite;
	}

}
