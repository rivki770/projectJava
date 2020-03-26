package unittests;

import org.junit.Test;
import primitives.Vector;

import static org.junit.Assert.*;
import static primitives.Util.isZero;

public class VectorTest {
    Vector v1 = new Vector(1, 2, 3);
    Vector v2 = new Vector(-2, -4, -6);
    Vector v3 = new Vector(0, 3, -2);

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

    @Test
    public void scale() {
        assertEquals(v2, v1.scale(-2));
        assertNotEquals(v3, v1.scale(1));
    }

    @Test
    public void dotProduct() {
        assertTrue("dotProduct() for orthogonal vectors is not zero", isZero(v1.dotProduct(v3)));
        assertTrue("dotProduct() wrong value", isZero(v1.dotProduct(v2) + 28));
        assertEquals(-28, v1.dotProduct(v2), 1e-10);
    }

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

    @Test
    public void lengthSquared() {
        assertEquals(14, v1.lengthSquared(), 1e-10);
    }

    @Test
    public void length() {
        assertEquals(5, new Vector(0, 3, 4).length(), 1e-10);
    }

    @Test
    public void normalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v);
        Vector vCopyNormalize = vCopy.normalize();

        assertTrue("normalize() function creates a new vector", vCopy.equals(vCopyNormalize));
        assertEquals("normalize() result is not a unit vector", 1, vCopyNormalize.length(), 1e-10);

    }

    @Test
    public void normalized() {
        Vector v = new Vector(1, 2, 3);
        Vector u = v.normalized();
        assertTrue("normalized() function does not create a new vector", v.equals(u));
    }
}