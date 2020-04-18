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
		// ========== Equivalence Partition Test ==========
		Plane p1 = new Plane(new Point3D(0, 0, 0), new Point3D(1, 0, 0), new Point3D(0, 0, 1));
		assertEquals("getNormal() result is not a solution" , p1.getNormal(), new Vector(0,-1,0));
	}

	/**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testfindIntersectionsRay() {
		
		// ========== Equivalence Partition Test ==========
		
		Plane p1 = new Plane(new Point3D(3, 0, 4), new Point3D(0, 0, 1), new Point3D(1, 0, 0));
		
		// TC01: Ray intersects the plane (1 point)
		Ray r1 = new Ray(new Point3D(-2, -2, -2), new Vector(3, 3 ,2)); 
		assertEquals("findIntersections() result isn't intersects the plane", p1.findIntersections(r1), List.of(new Point3D(0, 0, -2d/3)));
		assertEquals("findIntersections() result isn't intersects the plane", p1.findIntersections(r1).size(), 1);
	
		// TC02: Ray dosen't intersect the plane (0 point)
		Ray r2 = new Ray(new Point3D(2, 2, 2), new Vector(3, 3 ,2)); 
		assertEquals("findIntersections() result intersects the plane", p1.findIntersections(r2), null);
		
		// ========== Boundary Values Tests ==========
		
		// TC11: Ray is parallel to the plan, the ray included in the plane (0 point)
		Ray r3 = new Ray(new Point3D(2, 0, 2), new Vector(3, 0 ,3));
		assertEquals("findIntersections() result isn't include to the plane", p1.findIntersections(r3), null); 
		
		// TC12: Ray is parallel to the plan, the ray isn't included in the plane (0 point)
		Ray r4 = new Ray(new Point3D(2, 4, 2), new Vector(3, 0 ,3));
		assertEquals("findIntersections() result isn't include to the plane", p1.findIntersections(r4), null); 
		
		
		// TC13: Ray is orthogonal to the plane (before the plane) (1 point)
		Ray r5 = new Ray(new Point3D(1, 5, 3), new Vector(0, -5 ,0));
		assertEquals("findIntersections() result isn't meeting in the plane", p1.findIntersections(r5), List.of(new Point3D(1, 0, 3)));
		assertEquals("findIntersections() result isn't meeting in the plane", p1.findIntersections(r5).size(), 1);
		
		// TC14: Ray is orthogonal to the plane (in the plane) (0 point)
		Ray r6 = new Ray(new Point3D(1, 0, 3), new Vector(0, -5 ,0));
		assertEquals("findIntersections() result is head's ray", p1.findIntersections(r6), null);
		
		// TC15: Ray is orthogonal to the plane (after the plane) (0 point)
		Ray r7 = new Ray(new Point3D(1, -3, 3), new Vector(0, -2 ,0));
		assertEquals("findIntersections() result isn't meeting the plane", p1.findIntersections(r7), null); 
		
		// TC16: Ray begins at the plane, but not in the ray (0 point)
		Ray r8 = new Ray(new Point3D(4, 0, 4), new Vector(-3, 2 ,-1));
		assertEquals("findIntersections() result is head's ray", p1.findIntersections(r8), null); 
		
		// TC17: Ray begins in the same point which appears as reference point in the plane (0 point)
		Ray r9 = new Ray(new Point3D(3, 0, 4), new Vector(-2, 2 ,-1));
		assertEquals("findIntersections() result is head's ray", p1.findIntersections(r9), null); 
		
	}
}
