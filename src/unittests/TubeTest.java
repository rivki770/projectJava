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
		// ========== Equivalence Partition Test ==========
		Tube tube1 = new Tube(5, new Ray(new Point3D(0, 0, 0), new Vector(0, 0, 1)));
		Point3D p1 = new Point3D(0, 3, 8);
		
		Vector result1 = new Vector (0, 1, 0); //o=(0,0,8), vector po=(0,3,8)-(0,0,8)=(0,3,0), po.normalize=(0,1,0)
		assertEquals(tube1.getNormal(p1), result1);
	}

}
