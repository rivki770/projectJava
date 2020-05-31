package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Plane: class for representing plane in environment
 */

public class Plane extends Geometry {
    Point3D _point;
    Vector _normal;

    /*************** Constructor ********************/
    /**
     * @param _emmission is color of the geometry
     * @param _material of the geometry
     * @param point in the plane
     * @param normal to plane
     */
    public Plane(Color _emmission, Material _material, Point3D point, Vector normal) {
        this._point = point;
        this._normal = normal.normalize();
        this._emmission = _emmission;
        this._material = _material;
    }
    
    /**
     * @param _emmission is color of the geometry
     * @param point in the plane
     * @param normal to plane
     */
    public Plane(Color _emmission, Point3D point, Vector normal) {
    	this(Color.BLACK, new Material(0, 0, 0), point, normal);
    }
    
    /**
     *
     * @param point in the plane
     * @param normal to plane
     */
    public Plane(Point3D point, Vector normal) {
        this(Color.BLACK, point, normal);
    }
    
    /**
    * @param _emmission is color of the geometry
    * @param _material of the geometry
    * @param vertex Point3D in the plane
    * @param vertex1 Point3D in the plane
    * @param vertex2 Point3D in the plane
    */
    public Plane(Color _emmission, Material _material, Point3D vertex, Point3D vertex1, Point3D vertex2) {
    	this._point = vertex;
        Vector v = new Vector(vertex.subtract(vertex1));
        Vector u = new Vector(vertex.subtract(vertex2));
        Vector n = new Vector(v.crossProduct(u));
        n.normalize();
        //n = n.scale(-1);
        this._normal = n;
        this._emmission = _emmission;
        this._material = _material;
    }
    
    /**
    * @param _emmission is color of the geometry
    * @param vertex Point3D in the plane
    * @param vertex1 Point3D in the plane
    * @param vertex2 Point3D in the plane
    */
    public Plane(Color _emmission, Point3D vertex, Point3D vertex1, Point3D vertex2) {
    	this(_emmission, new Material(0, 0, 0), vertex, vertex1, vertex2);
    }

    /**
     *
     * @param vertex Point3D in the plane
     * @param vertex1 Point3D in the plane
     * @param vertex2 Point3D in the plane
     */
    public Plane(Point3D vertex, Point3D vertex1, Point3D vertex2) {
    	this(Color.BLACK, vertex, vertex1, vertex2);
    }
    
    /*************** getters ********************/
    /**
     *
     * @return point3D in a plane
     */
    public Point3D getPoint() {
        return _point;
    }

    /**
     *
     * @return vector normal in a plane
     */
    public Vector getNormal() {
        return new Vector(_normal);
    }

    /*************** toString() ********************/
    @Override
    public String toString() {
        return "Plane: " +
                "Point = " + _point +
                ", Normal = " + _normal;
    }

    /**
     *
     * @param point of Point3D in plane
     * @return Normal for plane
     */
    @Override
    public Vector getNormal(Point3D point) {
        return getNormal(null);
    }
    
    /**
    * @param max is maxDistance to geometry point
    * @param ray from camera
    * @return list of intersections between ray and geometry
    */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double max){
    	Vector vec;
        try {
            vec = _point.subtract(ray.getPoint());
        } catch (IllegalArgumentException e) {
            return null; // ray starts from _point - no intersections
        }

        double nv = _normal.dotProduct(ray.getNormal());
        if (isZero(nv)) // ray is parallel to the plane - no intersections
            return null;

        double t = alignZero(_normal.dotProduct(vec) / nv);
        
        if (t <= 0)
        	return null;
        
        if (alignZero(t - max) > 0)
        	return null;
        
        GeoPoint geo = new GeoPoint(this, ray.getTargetPoint(t));

        return List.of(geo);
    }
}
