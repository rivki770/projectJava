/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.Sphere;
import primitives.*;

/**
 * Unit test for geometries.Sphere class
 * @author Rivki_Kanterovich
 */
public class SphereTest {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		
		// ========== Equivalence Partition Test ==========
		Sphere sp1 = new Sphere(4, new Point3D(0,0,0));
		Sphere sp2 = new Sphere(1, new Point3D(1,1,1));
		
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(1, 1, 5);
		
		assertTrue(sp1.getNormal(new Point3D(0, 0, 4)).equals(new Vector(new Point3D(0, 0, 1))));
		assertTrue(sp1.getNormal(new Point3D(0, 0, -4)).equals(new Vector(new Point3D(0, 0, -1))));
		assertTrue(sp1.getNormal(new Point3D(0, 4, 0)).equals(new Vector(new Point3D(0, 1, 0))));
		assertTrue(sp1.getNormal(new Point3D(0, -4, 0)).equals(new Vector(new Point3D(0, -1, 0))));
		assertTrue(sp1.getNormal(new Point3D(4, 0, 0)).equals(new Vector(new Point3D(1, 0, 0))));
		assertTrue(sp1.getNormal(new Point3D(-4, 0, 0)).equals(new Vector(new Point3D(-1, 0, 0))));
		
		assertTrue(sp2.getNormal(new Point3D(1, 1, 0)).equals(new Vector(new Point3D(0, 0, -1))));
		assertTrue(sp2.getNormal(new Point3D(0, 1, 1)).equals(new Vector(new Point3D(-1, 0, 0))));
		assertTrue(sp2.getNormal(new Point3D(1, 0, 1)).equals(new Vector(new Point3D(0, -1, 0))));
		assertEquals("getNormal() result is not a solution" , sp1.getNormal(p1), new Vector(1, 0, 0));
		assertEquals("getNormal() result is not a solution", sp2.getNormal(p2), new Vector(0, 0, 1));
	}

}
