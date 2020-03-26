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
		Triangle t1 = new Triangle(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 0, 1));
		assertEquals("getNormal() result is not a solution" , t1.getNormal(Point3D.ZERO), new Vector(0,1,0));
	}

}
