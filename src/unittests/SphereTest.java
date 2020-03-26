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
	 * Test method for {@link geometries.Sphere#Sphere(double, primitives.Point3D)}.
	 */
	@Test
	public void testConstructor() {
		try {
            new Sphere(0, new Point3D(0,0,0));
        } catch (IllegalArgumentException e) {}

        try {
        	new Sphere(8, new Point3D(0,0,0));
        } catch (IllegalArgumentException e) {}
        
        try {
        	new Sphere(-3, new Point3D(0,0,0));
        } catch (IllegalArgumentException e) {}
	}

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point3D)}.
	 */
	@Test
	public void testGetNormal() {
		Sphere sp1 = new Sphere(1, new Point3D(0,0,0));
		Sphere sp2 = new Sphere(4, new Point3D(1,1,1));
		
		Point3D p1 = new Point3D(1, 0, 0);
		Point3D p2 = new Point3D(1, 1, 5);
		
		assertEquals("getNormal() result is not a solution" , sp1.getNormal(p1), new Vector(1, 0, 0));
		assertEquals("getNormal() result is not a solution", sp2.getNormal(p2), new Vector(0, 0, 1));
	}

}
