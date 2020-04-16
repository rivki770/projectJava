/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import geometries.*;
import primitives.*;

/**
 * Unit test for geometries.TriangleTest class
 * @author Rivki_Kanterovich
 *
 */
public class TriangleTest {

	/**
	 * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ========== Equivalence Partition Test ==========
		Triangle t1 = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		double sqrt3 = Math.sqrt(1d / 3);
		assertEquals("getNormal() result is not a solution", new Vector(sqrt3, sqrt3, sqrt3), t1.getNormal(Point3D.ZERO));
	}

}
