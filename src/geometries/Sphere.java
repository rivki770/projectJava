package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

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
    
    public Sphere(Color _emmission, double _radius, Point3D _center) {
        super(_radius);
        this._center = _center;
        this._emmission = _emmission;
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
   
    	Point3D o = this.get_center();
    	Vector n = (point.subtract(o)).normalize();
    	return n;
    }
    
    @Override
    public List<GeoPoint> findIntersections(Ray ray) {
        Point3D p0 = ray.getPoint();
        Vector v = ray.getNormal();
        Vector u;
        
        try {
            u = _center.subtract(p0);   // p0 == _center
        } catch (IllegalArgumentException e) {
            return List.of(new GeoPoint(this, ray.getTargetPoint(_radius)));
        }
        double tm = alignZero(v.dotProduct(u));
        double dSquared = (tm == 0) ? u.lengthSquared() : u.lengthSquared() - tm * tm;
        double thSquared = alignZero(_radius * _radius - dSquared);

        if (thSquared <= 0) return null;

        double th = alignZero(Math.sqrt(thSquared));
        if (th == 0) return null;

        double t1 = alignZero(tm - th);
        double t2 = alignZero(tm + th);
        if (t1 <= 0 && t2 <= 0) return null;
        if (t1 > 0 && t2 > 0) return List.of(new GeoPoint(this, ray.getTargetPoint(t1)),
        		new GeoPoint(this, ray.getTargetPoint(t2))); //P1 , P2
        if (t1 > 0)
            return List.of(new GeoPoint(this, ray.getTargetPoint(t1)));
        else
            return List.of(new GeoPoint(this, ray.getTargetPoint(t2)));
    }
}
