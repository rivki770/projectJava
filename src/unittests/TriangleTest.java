/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import geometries.*;
import primitives.*;

/**
 * Unit test for geometries.Triangle class
 * @author Rivki_Kanterovich
 *
 */
public class TriangleTest {

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		// ========== Equivalence Partition Test ==========
		Triangle t1 = new Triangle(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0));
		double sqrt3 = Math.sqrt(1d / 3);
		assertEquals("getNormal() result is not a solution", new Vector(sqrt3, sqrt3, sqrt3), t1.getNormal(Point3D.ZERO));
	}
	/**
	 * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testfindIntersectionsRay() {
		
		// ========== Equivalence Partition Test ==========
		Triangle t1 = new Triangle(new Point3D(3, 0, 0), new Point3D(1, 0, 0), new Point3D(-2, 0, 5));
		
		// TC01: The Point inside triangle (1 point)
		Ray r1 = new Ray(new Point3D(2, 1, 0), new Vector(0, -4, 1));
		assertEquals("findIntersections() result isn't inside of a triangle", t1.findIntersections(r1), List.of(new Point3D(2, 0, 0.25)));
		
		// TC02: The Point outside against edge (0 point)
		Ray r2 = new Ray(new Point3D(2, 1, 0), new Vector(2, -1, 5));
		assertEquals("findIntersections() result isn't outside against edge", t1.findIntersections(r2), null); //כנגד הצלע
		
		// TC03: The Point outside against vertex (0 point)
		Ray r3 = new Ray(new Point3D(0, 2, 3), new Vector(4, -2, -3.5));
		assertEquals("findIntersections() result isn't outside against vertex", t1.findIntersections(r3), null); //כנגד הקודקוד
		
		// ========== Boundary Values Tests ==========

		// TC11: The Point on edge (0 point)
		Ray r4 = new Ray(new Point3D(2, 1, 0), new Vector(4, -4, 0)); 
		assertEquals("findIntersections() result isn't on edge", t1.findIntersections(r4), null); //נפגש בקודקוד
		
		// TC12: The Point in vertex (0 point)
		Ray r5 = new Ray(new Point3D(2, 1, 0), new Vector(0, -2, 0));
		assertEquals("findIntersections() result isn't in vertex", t1.findIntersections(r5), null); //נפגש בצלע
		
		// TC13: The Point on edge's continuation (0 point)
		Ray r6 = new Ray(new Point3D(2, 1, 0), new Vector(6, -3, 0));
		assertEquals("findIntersections() result isn't on edge's continuation", t1.findIntersections(r6), null); //חותך בהמשך הצלע
	}

}
