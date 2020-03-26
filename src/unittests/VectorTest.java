/**
 * 
 */
package unittests;

import org.junit.Test;
import primitives.*;
import static primitives.Util.*;

import static org.junit.Assert.*;

/**
 * Unit test for primitives.Vector class
 * @author Rivki_Kanterovich
 */

public class VectorTest {
	/**
     * Test method for {@link primitives.Vector#Vector(primitives.Coordinate, primitives.Coordinate, primitives.Coordinate)}.
     * Test method for {@link primitives.Vector#Vector(double, double, double)}.
     */
	@Test
	public void testConstructors() {
		try {
			new Vector(new Coordinate(0), new Coordinate(0) ,new Coordinate(0)); 
		} catch(IllegalArgumentException e) {}
	
		
		try {
			new Vector(0, 0 ,0); 
		} catch(IllegalArgumentException e) {}
		
		try {
			new Vector(10, -3 ,5); 
		} catch(IllegalArgumentException e) {}
	}
	
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

    /**
     * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
     */
    @Test
    public void subtract() {

        assertTrue(new Vector(3,3,3).subtract(new Vector(1, 1, 1)).equals(new Vector(2,2,2)));
        try
        {
            assertTrue(v1.subtract(new Vector(1, 2, 3)).equals(new Vector(0,0,0)));
        }
        catch (Exception ex) {
        }
    }
    
    /**
     * Test method for {@link primitives.Vector#add(primitives.Vector)}.
     */
    @Test
    public void add() {
        assertTrue(v1.add(v2).equals(new Vector(-1, -2, -3)));
        try
        {
            assertTrue(v1.add(new Vector(-1, -2, -3)).equals(new Vector(0,0,0)));
        }
        catch (Exception ex) {
        }

    }

    /**
     * Test method for {@link primitives.Vector#scale(double)}.
     */
    @Test
    public void scale() {
        assertEquals(v2, v1.scale(-2));
        assertNotEquals(v3, v1.scale(1));
    }

    /**
     * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
     */
    @Test
    public void dotProduct() {
        assertTrue("dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
        assertTrue("dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
        assertEquals(-28, v1.dotProduct(v2), 1e-10);
    }

    /**
     * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
     */
    @Test
    public void crossProduct() {
        Vector vr = v1.crossProduct(v3);
        assertEquals("crossProduct() wrong result length", v1.length() * v3.length(), vr.length(), 0.00001);
        assertTrue("crossProduct() result is not orthogonal to 1ts operands", isZero(vr.dotProduct(v1)));
        assertTrue("crossProduct() result is not orthogonal to 1ts operands", isZero(vr.dotProduct(v3)));

        try {
            v1.crossProduct(v2);
            fail("crossProduct() for parallel vectors does not throw an exception");
        }
        catch (Exception e){
        }
    }

    /**
     * Test method for {@link primitives.Vector#lengthSquared()}.
     */
    @Test
    public void lengthSquared() {
        assertEquals(14, v1.lengthSquared(), 1e-10);
    }

    /**
     * Test method for {@link primitives.Vector#length()}.
     */
    @Test
    public void length() {
        assertEquals(5, new Vector(0, 3, 4).length(), 1e-10);
    }

    /**
     * Test method for {@link primitives.Vector#normalize()}.
     */
    @Test
    public void normalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();

        assertTrue("normalize() function creates a new vector", vCopy.equals(vCopyNormalize));
        assertEquals("normalize() result is not a unit vector", 1, vCopyNormalize.length(), 1e-10);
    }

    /**
     * Test method for {@link primitives.Vector#normalized()}.
     */
    @Test
    public void normalized() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalized();
        assertTrue("normalized() function does not create a new vector", v.equals(u));
    }
}