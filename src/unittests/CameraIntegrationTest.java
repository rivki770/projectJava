/**
 * 
 */
package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

import primitives.*;
import geometries.*;
import elements.*;

import java.util.List;

/**
 * @author rivki_kanterovich
 *
 */
public class CameraIntegrationTest {


    Camera cam1 = new Camera(Point3D.ZERO, new Vector(0, 0, 1), new Vector(0, -1, 0));
    Camera cam2 = new Camera(new Point3D(0, 0, -0.5), new Vector(0, 0, 1), new Vector(0, -1, 0));
    
    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Sphere#findIntersections(primitives.Point3D)}.
     */
    @Test
    public void constructRayThroughPixelWithSphere1() {
        //TO DO
        Sphere sph =  new Sphere(1, new Point3D(0, 0, 3));
        List<Point3D> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad", 2,count);
        System.out.println("count sphere1: "+count);

    }
    
    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Sphere#findIntersections(primitives.Point3D)}.
     */
    @Test
    public void constructRayThroughPixelWithSphere2() {
        Sphere sph =  new Sphere(2.5, new Point3D(0, 0, 2.5));

        List<Point3D> results;
        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad", 18,count);
        System.out.println("count sphere2: "+count);
    }

    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Sphere#findIntersections(primitives.Point3D)}.
     */
    @Test
    public void constructRayThroughPixelWithSphere3() {
        Sphere sph =  new Sphere(2, new Point3D(0, 0, 2));

        List<Point3D> results;
        int count = 0;
        // TODO explanations
        int Nx =3;
        int Ny =3;

        // TODO explanations
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam2.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad", 10,count);
        System.out.println("count sphere3: "+count);
    }
    
    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Sphere#findIntersections(primitives.Point3D)}.
     */
    @Test
    public void constructRayThroughPixelWithSphere4() {
        //TO DO
        Sphere sph =  new Sphere(4, new Point3D(0, 0, 1));
        List<Point3D> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad", 9,count);
        System.out.println("count sphere4: "+count);
    }
    
    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Sphere#findIntersections(primitives.Point3D)}.
     */
    @Test
    public void constructRayThroughPixelWithSphere5() {
        //TO DO
        Sphere sph =  new Sphere(0.5, new Point3D(0, 0, -1));
        List<Point3D> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = sph.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad", 0, count);
        System.out.println("count sphere5: "+ count);

    }
    
    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    public void constructRayThroughPixelWithTriangle1() {
        //TO DO
        Triangle tr1 =  new Triangle(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        List<Point3D> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = tr1.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad", 1,count);
        System.out.println("count triangle1: " + count);
    }
    
    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    public void constructRayThroughPixelWithTriangle2() {
        //TO DO
        Triangle tr2 =  new Triangle(new Point3D(0, -20, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        List<Point3D> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = tr2.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad", 2,count);
        System.out.println("count triangle2: " + count);
    }
    
    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void constructRayThroughPixelWithPlane1() {
        //TO DO
        Plane pl1 =  new Plane(new Point3D(0, -1, 2), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        List<Point3D> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = pl1.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }
        assertEquals("too bad", 9,count);
        System.out.println("count plane1: " + count);
    }
    
    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void constructRayThroughPixelWithPlane2() {
        //TO DO
        Plane pl2 =  new Plane(new Point3D(0, -1.5, 1), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        List<Point3D> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = pl2.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad", 9,count);
        System.out.println("count plane2: " + count);
    }
    
    /**
     * Test method for
     * {@link elements.Camera#constructRayThroughPixel(int, int, int, int, double, double, double)}.
     * {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    public void constructRayThroughPixelWithPlane3() {
        //TO DO
        Plane pl3 =  new Plane(new Point3D(0, -0.5, 0), new Point3D(1, 1, 2), new Point3D(-1, 1, 2));
        List<Point3D> results;
        int count = 0;
        int Nx =3;
        int Ny =3;
        for (int i = 0; i < Ny; ++i) {
            for (int j = 0; j < Nx; ++j) {
                results = pl3.findIntersections(cam1.constructRayThroughPixel(Nx, Ny, j, i, 1, 3, 3));
                if (results != null)
                    count += results.size();
            }
        }

        assertEquals("too bad", 6,count);
        System.out.println("count plane3: " + count);

    }
}
