/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.Point3D;
import primitives.Vector;

/**
 * Unit test for geometries.Plane class
 * @author Rivki_Kanterovich
 *
 */
public class PlaneTest {

	/**
	 * Test method for {@link geometries.Plane#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormalPoint3D() {
		Plane p1 = new Plane(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 0, 1));
		assertEquals("getNormal() result is not a solution" , p1.getNormal(), new Vector(0,1,0));
	}

}
