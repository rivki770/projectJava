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
	/**
	 * Test method for {@link geometries.Polygon#findIntersections(primitives.Ray)}.
	 */
	@Test
	public void testfindIntersectionsRay() {
		
		// ========== Equivalence Partition Test ==========
		Triangle t1 = new Triangle(new Point3D(3, 0, 0), new Point3D(1, 0, 0), new Point3D(-2, 0, 5));
		
		// TC01: The Point inside triangle
		Ray r1 = new Ray(new Point3D(2, 1, 0), new Vector(0, -4, 1));
		assertEquals("findIntersections() result isn't inside of a triangle", t1.findIntersections(r1), List.of(new Point3D(2, 0, 0.25)));
		
		// TC02: The Point outside against vertex
		Ray r2 = new Ray(new Point3D(2, 1, 0), new Vector(2, -1, 5));
		assertEquals("findIntersections() result isn't outside against vertex", t1.findIntersections(r2), null); //����� �������
		
		// TC03: The Point outside against edge
		Ray r3 = new Ray(new Point3D(2, 1, 0), new Vector(2, -1, -1));
		assertEquals("findIntersections() result isn't outside against edge", t1.findIntersections(r3), null); //����� ����
		
		// ========== Boundary Values Tests ==========

		// TC11: The Point on edge
		Ray r4 = new Ray(new Point3D(2, 1, 0), new Vector(4, -4, 0));
		assertEquals("findIntersections() result isn't on edge", t1.findIntersections(r4), null); //���� �������
		
		// TC12: The Point in vertex
		Ray r5 = new Ray(new Point3D(2, 1, 0), new Vector(0, -2, 0));
		assertEquals("findIntersections() result isn't in vertex", t1.findIntersections(r5), null); //���� ����
		
		// TC13: The Point on edge's continuation
		Ray r6 = new Ray(new Point3D(2, 1, 0), new Vector(6, -3, 0));
		assertEquals("findIntersections() result isn't on edge's continuation", t1.findIntersections(r6), null); //���� ����� ����
	}

}
