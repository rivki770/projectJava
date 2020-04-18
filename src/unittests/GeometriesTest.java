/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import geometries.*;
import primitives.*;
/**
 *  Unit test for geometries.Geometries class
 * @author rivki_kanterovich
 */
public class GeometriesTest {

	/**
	 * Test method for {@link geometries.Geometries#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testFindIntersections() {
		Plane p1 = new Plane(new Point3D(3, 0, 4), new Point3D(0, 0, 1), new Point3D(1, 0, 0));
		Triangle t1 = new Triangle(new Point3D(3, -5, 0), new Point3D(1, -5, 0), new Point3D(-2, -5, 5));
		Sphere sp1 = new Sphere(1d, new Point3D(1, 3, 0));
		
		// ========== Equivalence Partition Test ==========
		// TC01: Collection with geometries and some geometry is cut, but not everyone.
		Geometries _geometries1 = new Geometries();
		_geometries1.add(p1, t1, sp1);
		Ray r1 = new Ray(new Point3D(2, 1, 0.5), new Vector(0, -8, 0));
		assertEquals("findIntersections() result isn't empty", _geometries1.findIntersections(r1).size(), 2);
		
		// ========== Boundary Values Tests ==========
		
		// TC11: Collection without geometries
		Geometries _geometries2 = new Geometries();
		Ray r2 = new Ray(new Point3D(-2, -2, -2), new Vector(3, 3 ,2));
		assertEquals("findIntersections() result isn't empty", _geometries2.findIntersections(r2), null);
		
		// TC12: Collection with geometries and no geometry is cut
		Geometries _geometries3 = new Geometries();
		_geometries3.add(p1, t1, sp1);
		Ray r3 = new Ray(new Point3D(0, 2, 0), new Vector(-3, 0 ,5));
		assertEquals("findIntersections() result isn't empty", _geometries3.findIntersections(r3), null);
		
		// TC13: Collection with geometries and one geometry is cut
		Geometries _geometries4 = new Geometries();
		_geometries4.add(p1, t1, sp1);
		Ray r4 = new Ray(new Point3D(4, 3.5, 0), new Vector(-8, 0 ,0));
		assertEquals("findIntersections() result isn't empty", _geometries4.findIntersections(r4).size(), 2); //with the sphere
		
		Ray r5 = new Ray(new Point3D(2, -3, 0), new Vector(0, -3 ,1));
		assertEquals("findIntersections() result isn't empty", _geometries4.findIntersections(r5).size(), 1); //with the Triangle
		
		Ray r6 = new Ray(new Point3D(0, 1, 1), new Vector(-8, -6 ,6));
		assertEquals("findIntersections() result isn't empty", _geometries4.findIntersections(r6).size(), 1); //with the Plane
		
		// TC13: Collection with geometries and all geometries is cut
		Geometries _geometries5 = new Geometries();
		_geometries5.add(p1, t1, sp1);
		Ray r7 = new Ray(new Point3D(1, 5, 0.5), new Vector(0, -12 ,0));
		assertEquals("findIntersections() result isn't empty", _geometries5.findIntersections(r7).size(), 4);
	}

}
