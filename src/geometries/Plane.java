package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Plane: class for representing plane in environment
 */

public class Plane implements Geometry {
    Point3D point;
    Vector normal;

    /*************** Constructor ********************/
    /**
     *
     * @param point in the plane
     * @param normal to plane
     */
    public Plane(Point3D point, Vector normal) {
        this.point = point;
        this.normal = normal;
    }

    /**
     *
     * @param vertex Point3D in the plane
     * @param vertex1 Point3D in the plane
     * @param vertex2 Point3D in the plane
     */
    public Plane(Point3D vertex, Point3D vertex1, Point3D vertex2) {
        this.point = vertex;
        Vector v = new Vector(vertex.subtract(vertex1));
        Vector u = new Vector(vertex.subtract(vertex2));
        Vector n = new Vector(v.crossProduct(u));
        n.normalize();
        n.scale(-1);
        this.normal = n;
    }

    /*************** getters ********************/
    /**
     *
     * @return point3D in a plane
     */
    public Point3D getPoint() {
        return point;
    }

    /**
     *
     * @return vector normal in a plane
     */
    public Vector getNormal() {
        return normal;
    }

    /*************** toString() ********************/
    @Override
    public String toString() {
        return "Plane: " +
                "Point = " + point +
                ", Normal = " + normal;
    }

    /**
     *
     * @param point of Point3D in plane
     * @return Normal for plane
     */
    @Override
    public Vector getNormal(Point3D point) {
        return this.getNormal();
    }
}
