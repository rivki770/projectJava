/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import geometries.*;
import primitives.*;

/**
 * Unit test for geometries.Tube class
 * @author Rivki_Kanterovich
 */
public class TubeTest {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Tube tb = new Tube(5, new Vector(1, 1, 1), new Point3D(0, 0, 0));
	}

}
