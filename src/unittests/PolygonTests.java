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
 * Testing Polygons
 * @author Dan
 *
 */
public class PolygonTests {

    /**
     * Test method for
     * {@link geometries.Polygon#Polygon(primitives.Point3D, primitives.Point3D, primitives.Point3D, primitives.Point3D)}.
     */
    @Test
    public void testConstructor() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }

        // TC02: Wrong vertices order
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(0, 1, 0),
                    new Point3D(1, 0, 0), new Point3D(-1, 1, 1));
            fail("Constructed a polygon with wrong order of vertices");
        } catch (IllegalArgumentException e) {}

        // TC03: Not in the same plane
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 2, 2));
            fail("Constructed a polygon with vertices that are not in the same plane");
        } catch (IllegalArgumentException e) {}

        // TC04: Concave quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0.5, 0.25, 0.5));
            fail("Constructed a concave polygon");
        } catch (IllegalArgumentException e) {}

        // =============== Boundary Values Tests ==================

        // TC10: Vertix on a side of a quadrangular
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0.5, 0.5));
            fail("Constructed a polygon with vertix on a side");
        } catch (IllegalArgumentException e) {}

        // TC11: Last point = first point
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 0, 1));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

        // TC12: Collocated points
        try {
            new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0),
                    new Point3D(0, 1, 0), new Point3D(0, 1, 0));
            fail("Constructed a polygon with vertice on a side");
        } catch (IllegalArgumentException e) {}

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point3D)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals("Bad normal to trinagle", new Vector(sqrt3, sqrt3, sqrt3), pl.getNormal(new Point3D(0, 0, 1)));
    }
    
    /**
	 * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testfindIntersectionsRay() {
		
		// ========== Equivalence Partition Test ==========
		
		Polygon p1 = new Polygon(new Point3D(0, 0, 1), new Point3D(1, 0, 0), new Point3D(0, 1, 0),
                new Point3D(-1, 1, 1));
		
		// TC01: The Point inside Polygon
		Ray r1 = new Ray(new Point3D(2, 1, 0), new Vector(0, -4, 1));
		assertEquals("findIntersections() result isn't inside of a triangle", p1.findIntersections(r1), List.of(new Point3D(2, 0, 0.25)));
	
		// TC02: Ray dosen't intersect the plane
		Ray r2 = new Ray(new Point3D(2, 2, 2), new Vector(3, 3 ,2));
		assertEquals("findIntersections() result intersects the plane", p1.findIntersections(r2), null);
		
		// ========== Boundary Values Tests ==========
		
		// TC11: Ray is parallel to the plan, the ray included in the plane
		Ray r3 = new Ray(new Point3D(2, 0, 2), new Vector(3, 0 ,3));
		assertEquals("findIntersections() result isn't include to the plane", p1.findIntersections(r3), null); //t = 0
		
		// TC12: Ray is parallel to the plan, the ray isn't included in the plane
		Ray r4 = new Ray(new Point3D(2, 4, 2), new Vector(3, 4 ,3));
		assertEquals("findIntersections() result isn't include to the plane", p1.findIntersections(r4), null); //t = -1 < 0
		
		
		// TC13: Ray is orthogonal to the plane (before the plane)
		Ray r5 = new Ray(new Point3D(1, 5, 3), new Vector(1, -5 ,3));
		assertEquals("findIntersections() result isn't meeting in the plane", p1.findIntersections(r5), List.of(new Point3D(2, 0, 6)));
		assertEquals("findIntersections() result isn't meeting in the plane", p1.findIntersections(r5).size(), 1);
		
		// TC14: Ray is orthogonal to the plane (in the plane)
		Ray r6 = new Ray(new Point3D(1, 0, 3), new Vector(1, -5 ,3));
		assertEquals("findIntersections() result is head's ray", p1.findIntersections(r6), null); //t = 0 
		
		// TC15: Ray is orthogonal to the plane (after the plane)
		Ray r7 = new Ray(new Point3D(1, -3, 3), new Vector(1, -5 ,3));
		assertEquals("findIntersections() result isn't meeting the plane", p1.findIntersections(r7), null); //t = -3/5 < 0 
		
		// TC16: Ray begins in the same point which appears as reference point in the plane (Q) 
		Ray r8 = new Ray(new Point3D(4, 0, 4), new Vector(1, 2 ,3));
		assertEquals("findIntersections() result is head's ray", p1.findIntersections(r8), null); //t = 0 
		
		// TC17: Ray begins in the same point which appears as reference point in the plane (Q) 
		Ray r9 = new Ray(new Point3D(3, 0, 4), new Vector(1, 2 ,3));
		assertEquals("findIntersections() result is head's ray", p1.findIntersections(r9), null); //t = 0
		
	}

}
