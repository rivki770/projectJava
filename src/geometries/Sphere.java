package geometries;

import primitives.Point3D;
import primitives.Vector;

/**
 * Sphere: class for representing Sphere in environment
 */

public class Sphere extends RadialGeometry{
     Point3D _center;

    /*************** Constructor ********************/
    /**
     *
     * @param _radius number for radius in Sphere
     * @param _center point3D in Sphere's center
     */
    public Sphere(double _radius, Point3D _center) {
        super(_radius);
        this._center = _center;
    }

    /*************** getters ********************/
    /**
     *
     * @return point3D in Sphere's center
     */
    public Point3D get_center() {
        return _center;
    }

    /**
     *
     * @return number for radius in Sphere
     */
    public double get_radius() {
        return this._radius;
    }

    /*************** toString() ********************/
    @Override
    public String toString() {
        return "Sphere: " +
                "Center Point = " + _center +
                ", Radius = " + _radius;
    }

    /**
     *
     * @param point in sphere
     * @return Normal for sphere
     */
    @Override
    public Vector getNormal(Point3D point) {
    	// n = normalize(Point - o)
   
    	Point3D o = _center;
    	Vector n = point.subtract(o).normalize();
    	return n;
    }
}
