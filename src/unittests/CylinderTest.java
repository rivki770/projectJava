/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;
import geometries.*;
import primitives.*;

/**
 *  Unit test for geometries.Cylinder class
 * @author rivki_kanterovich
 */
public class CylinderTest {

	/**
	 * Test method for {@link geometries.Cylinder#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Cylinder cy1 = new Cylinder(1.0, new Ray(new Point3D(0, 0, 1), new Vector(0, 1, 0)), 1d);
		
		// ========== Equivalence Partition Tests ==========
		//TC01: Point at a side of the cylinder
		assertEquals("getNormal() result is not a solution", new Vector(0, 0, 1), cy1.getNormal(new Point3D(0, 0.5, 2)));
		
		//TC02: Point at a 1st base of the cylinder
		assertEquals("getNormal() result is not a solution", new Vector(0, 1, 0), cy1.getNormal(new Point3D(0, 0, 1)));
		
		//TC03: Point at a 2nd base of the cylinder
		assertEquals("getNormal() result is not a solution", new Vector(0, 1, 0), cy1.getNormal(new Point3D(0, 1, 0)));
		
		
	}

}
