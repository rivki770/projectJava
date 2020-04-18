/**
 * 
 */
package unittests;

import static org.junit.Assert.*;
import java.util.List;
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
	
	
	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Point3D)}.
	 */
    @Test
    public void findIntersectionsTest() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============
        Point3D p1 = new Point3D(0.0651530771650466, 0.355051025721682, 0);
        Point3D p2 = new Point3D(1.53484692283495, 0.844948974278318, 0);
        List<Point3D> exp = List.of(p1, p2);

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull("Ray's line out of sphere", sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(1, 1, 0))));

        // TC02: Ray starts before and crosses the sphere (2 points)
        List<Point3D> result = sphere.findIntersections(new Ray(new Point3D(-1, 0, 0), new Vector(3, 1, 0)));

        assertEquals("Wrong number of points", 2, result.size());
        if (result.get(0).get_x().get() > result.get(1).get_x().get()) {
            result = List.of(result.get(1), result.get(0));
        }
        assertEquals("Ray crosses sphere", exp, result);

        // TC03: Ray starts inside the sphere (1 point)
        assertEquals( "Ray from inside sphere",
        		List.of(new Point3D(2, 0, 0)),
                sphere.findIntersections(new Ray(new Point3D(0.5, 0.5, -0.5), new Vector(1.5, -0.5, 0.5))));

        // TC04: Ray starts after the sphere (0 points)
        assertNull("Sphere behind Ray", sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 1, 0.5))));

        // =============== Boundary Values Tests ==================
        // **** Group: Ray's line crosses the sphere (but not the center)

        // TC11: Ray starts at sphere and goes inside (1 points)
        assertEquals("Ray from sphere inside",
        		List.of(new Point3D(2, 0, 0)),
                sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, -1, 0))));

        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull("Ray from sphere outside",
        		sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 1, 0))) );

        // **** Group: Ray's line goes through the center
        
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point3D(1, -3, 0), new Vector(0, 1, 0)));

        assertEquals("Wrong number of points", 2, result.size());
        
        //if (result.get(0).get_y().get() > result.get(1).get_y().get()) {
          //  result = List.of(result.get(1), result.get(0));
        //}
        
        assertEquals("ray needs crosses sphere", List.of(new Point3D(1, -1, 0), new Point3D(1, 1, 0)), result);

        // TC14: Ray starts at sphere and goes inside (1 points)
        assertEquals("ray from sphere, and crosses sphere",
        		List.of(new Point3D(1, -1, 0)),
                sphere.findIntersections(new Ray(new Point3D(2, 0, 0), new Vector(-1, -1, 0))));

        // TC15: Ray starts inside (1 points)
        assertEquals("ray from inside sphere",
        		List.of(new Point3D(1, -1, 0)),
                sphere.findIntersections(new Ray(new Point3D(1, -0.5, 0), new Vector(0, -1, 0))));

        // TC16: Ray starts at the center (1 points)
        assertEquals("ray from a center of sphere",
        		List.of(new Point3D(1, -1, 0)),
                sphere.findIntersections(new Ray(new Point3D(1, 0, 0), new Vector(0, -1, 0))));

        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull("ray from sphere outside",
        		sphere.findIntersections(new Ray(new Point3D(1, -1, 0), new Vector(0, -1, 0))));

        // TC18: Ray starts after sphere (0 points)
        assertNull("ray outside sphere",
        		sphere.findIntersections(new Ray(new Point3D(1, 2, 0), new Vector(0, 1, 0))));

        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull("Tangent line, ray before sphere",
        		sphere.findIntersections(new Ray(new Point3D(0, 1, 0), new Vector(1, 0, 0))));

        // TC20: Ray starts at the tangent point
        assertNull("Tangent line, ray at sphere",
        		sphere.findIntersections(new Ray(new Point3D(1, 1, 0), new Vector(1, 0, 0))));

        // TC21: Ray starts after the tangent point
        assertNull("Tangent line, ray after sphere",
        		sphere.findIntersections(new Ray(new Point3D(2, 1, 0), new Vector(1, 0, 0))));

        // **** Group: Special cases
        // TC19: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull("Ray orthogonal to ray head to center line",
        		sphere.findIntersections(new Ray(new Point3D(3, 0, 0), new Vector(0, 0, 1))));
    }

}
