package geometries;

import static primitives.Util.isZero;

import java.util.List;

import primitives.*;

/**
 * Tube: class for representing Tube in environment
 */
public class Tube extends RadialGeometry {
    Ray _axisRay;

    /*************** Constructor ********************/
    /**
    * @param _emmission is color of the geometry
    * @param _material of the geometry
    * @param _radius number for radius in Sphere
    * @param _axisRay parameter of ray
    */
    public Tube(Color _emmission, Material _material, double _radius, Ray _axisRay) {
        super(_radius);
        this._axisRay = _axisRay;
        this._emmission = _emmission;
    	this._material = _material;
    }
    
    /**
    * @param _emmission is color of the geometry
    * @param _radius number for radius in Sphere
    * @param _axisRay parameter of ray
    */
    public Tube(Color _emmission, double _radius, Ray _axisRay) {
        this( _emmission, new Material(0,0,0), _radius, _axisRay);
    }
    
    /**
     *
     * @param _radius number for radius in Sphere
     * @param _axisRay parameter of ray
     */
    public Tube(double _radius, Ray _axisRay) {
    	this(Color.BLACK, _radius, _axisRay);
    }
    
    /**
    * @param _emmission is color of the geometry
    * @param _material of the geometry
    * @param _radius number for radius in Sphere
    * @param _vec parameter vector for ray of cylinder
    * @param _point parameter point for ray of cylinder
    */
    public Tube(Color _emmission,  Material _material, double _radius, Vector _vec, Point3D _point) {
        super(_radius);
        this._axisRay = new Ray(_point, _vec);
        this._emmission = _emmission;
        this._material = _material;
    }

    /**
    * @param _emmission is color of the geometry
    * @param _radius number for radius in Sphere
    * @param _vec parameter vector for ray of cylinder
    * @param _point parameter point for ray of cylinder
    */
    public Tube(Color _emmission, double _radius, Vector _vec, Point3D _point) {
        this(_emmission, new Material(0,0,0), _radius, _vec, _point);
    }
    
    /**
    * @param _radius number for radius in Sphere
    * @param _vec parameter vector for ray of cylinder
    * @param _point parameter point for ray of cylinder
    */
    public Tube(double _radius, Vector _vec, Point3D _point) {
    	this(Color.BLACK, _radius, _vec, _point);
    }
    

    


    /*************** getters ********************/
    /**
     *
     * @return parameters of Tube
     */
    public Ray get_axisRay() {
        return _axisRay;
    }

    public double get_radius() {
        return this._radius;
    }

    /*************** toString() ********************/
    @Override
    public String toString() {
        return "Tube: " +
                "Ray = " + _axisRay +
                ", Radius = " + _radius;
    }

    /**
     *
     * @param point of Point3D
     * @return  Normal for tube
     */
    @Override
    public Vector getNormal(Point3D point) {
    	// n = normalize(Point - O)
        // O is projection of P on cylinder's ray:
        // t = v (P � P0)
        // O = P0 + t * v
   
    	Point3D p0 = _axisRay.getPoint();
    	Vector v = _axisRay.getNormal();
    	
    	double t = point.subtract(p0).dotProduct(v);
        if (!isZero(t)) // if it's close to 0, we'll get ZERO vector exception
            p0 = p0.add(v.scale(t));
         
        Vector n = point.subtract(p0).normalize();
        return n;
    }
    
    /**
    * @param max is maxDistance to geometry point
    * @param ray from camera
    * @return list of intersections between ray and geometry
    */
    @Override
    public List<GeoPoint> findIntersections(Ray ray, double max){
    	return null;
    }
}
