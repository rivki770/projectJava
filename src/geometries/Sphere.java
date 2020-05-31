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
     * @param _emmission is color of the geometry
     * @param _material of the geometry
     * @param _radius number for radius in Sphere
     * @param _center point3D in Sphere's center 
     */
     public Sphere(Color _emmission, Material _material, double _radius, Point3D _center) {
         super(_radius);
         this._center = _center;
         this._material = _material;
         this._emmission = _emmission;
     }
     
    /**
     * @param _emmission is color of the geometry
     * @param _radius number for radius in Sphere
     * @param _center point3D in Sphere's center
     */
     public Sphere(Color _emmission, double _radius, Point3D _center) {
    	this(_emmission, new Material(0, 0, 0), _radius, _center);
     }
      
    /**
     *
     * @param _radius number for radius in Sphere
     * @param _center point3D in Sphere's center
     */
    public Sphere(double _radius, Point3D _center) {
        this(Color.BLACK, _radius, _center);
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
    
    /**
    * @param max is maxDistance to geometry point
    * @param ray from camera
    * @return list of intersections between ray and geometry
    */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double max) {
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
        
        double t1dist = alignZero(t1 - max);
        double t2dist = alignZero(t1 - max);
        
        if (t1dist > 0) {
            return null;
        }
        
        if (t2dist > 0) {
            return null;
        }
        
        if (t1 <= 0 && t2 <= 0) return null;
        
        if (t1 > 0 && t2 > 0) return List.of(new GeoPoint(this, ray.getTargetPoint(t1)),
        		new GeoPoint(this, ray.getTargetPoint(t2))); //P1 , P2
        if (t1 > 0)
            return List.of(new GeoPoint(this, ray.getTargetPoint(t1)));
        else
            return List.of(new GeoPoint(this, ray.getTargetPoint(t2)));
    }
}
