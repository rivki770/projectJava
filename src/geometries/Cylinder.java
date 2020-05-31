package geometries;

import primitives.*;
import static primitives.Util.*;

import java.util.List;

/**
 * Cylinder: class for representing cylinder in 3D environment
 */

public class Cylinder extends Tube {
    double _height;

    /*************** Constructor ********************/
    /**
    * @param _emmission is color of the geometry
    * @param _material of the geometry
    * @param _radius parameter of radius in a cylinder
    * @param _axisRay parameter of ray
    * @param _height parameter of height in cylinder
    */
    public Cylinder(Color _emmission, Material _material, double _radius, Ray _axisRay, double _height) {
        super(_radius, _axisRay);
        this._height = _height;
        this._emmission = _emmission;
        this._material = _material;
    }
    
    /**
    * @param _emmission is color of the geometry
    * @param _radius parameter of radius in a cylinder
    * @param _axisRay parameter of ray
    * @param _height parameter of height in cylinder
    */
    public Cylinder(Color _emmission, double _radius, Ray _axisRay, double _height) {
    	this(_emmission, new Material(0,0,0), _radius, _axisRay, _height);
    }
    
    /**
     *
     * @param _radius parameter of radius in a cylinder
     * @param _axisRay parameter of ray
     * @param _height parameter of height in cylinder
     */

    public Cylinder(double _radius, Ray _axisRay, double _height) {
    	this(Color.BLACK, _radius, _axisRay, _height);
    }
    
    /**
    * @param _emmission is color of the geometry
    * @param _material of the geometry
    * @param _radius parameter of radius in a cylinder
    * @param _vec parameter vector for ray of cylinder
    * @param _point parameter point for ray of cylinder
    * @param _height parameter of height in cylinder
    */
    public Cylinder(Color _emmission, Material _material, double _radius, Vector _vec, Point3D _point, double _height) {
        super(_radius, _vec, _point);
        this._height = _height;
        this._material = _material;
        this._emmission = _emmission;
    }
    
    /**
    * @param _emmission is color of the geometry
    * @param _radius parameter of radius in a cylinder
    * @param _vec parameter vector for ray of cylinder
    * @param _point parameter point for ray of cylinder
    * @param _height parameter of height in cylinder
    */
    public Cylinder(Color _emmission, double _radius, Vector _vec, Point3D _point, double _height) {
    	this(_emmission, new Material(0,0,0),_radius, _vec, _point,_height); 
    }
    
    /**
     * @param _radius parameter of radius in a cylinder
     * @param _vec parameter vector for ray of cylinder
     * @param _point parameter point for ray of cylinder
     * @param _height parameter of height in cylinder
     */
    public Cylinder(double _radius, Vector _vec, Point3D _point, double _height) {
    	this(Color.BLACK, _radius, _vec, _point, _height);
    }
    


    /*************** getters ********************/
    /**
     *
     * @return parameters of cylinder
     */
    public double get_height() {
        return _height;
    }

    public double get_radius() {
        return this._radius;
    }

    public Ray get_axisRay() {
        return this._axisRay;
    }

    /*************** toString() ********************/

    @Override
    public String toString() {
        return "Cylinder: " +
                "Height = " + _height +
                ", Ray = " + _axisRay +
                ", Radius = " + _radius;
    }

    /**
     *
     * @param point of Point3D
     * @return Normal for cylinder
     */
    @Override
    public Vector getNormal(Point3D point) {
    	   Point3D o = _axisRay.getPoint();
           Vector v = _axisRay.getNormal();

           // projection of P-O on the ray:
           double t;
           try {
               t = alignZero(point.subtract(o).dotProduct(v));
           } catch (IllegalArgumentException e) { // P = O
               return v;
           }

           // if the point is at a base
           if (t == 0 || isZero(_height - t)) // if it's close to 0, we'll get ZERO vector exception
               return v;

           o = o.add(v.scale(t));
           return point.subtract(o).normalize();
    }
    
    /**
    * @param max is maxDistance to geometry point
    * @param ray from camera
    * @return list of intersections between ray and geometry
    */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double max){
    	//return super.findIntersections(ray);
    	return null;
    }
}
