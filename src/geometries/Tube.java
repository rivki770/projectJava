package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Tube: class for representing Tube in environment
 */
public class Tube extends RadialGeometry {
    Ray _axisRay;

    /*************** Constructor ********************/
    /**
     *
     * @param _radius number for radius in Sphere
     * @param _axisRay parameter of ray
     */
    public Tube(double _radius, Ray _axisRay) {
        super(_radius);
        this._axisRay = _axisRay;
    }

    /**
     *
     * @param _radius number for radius in Sphere
     * @param _vec parameter vector for ray of cylinder
     * @param _point parameter point for ray of cylinder
     */
    public Tube(double _radius, Vector _vec, Point3D _point) {
        super(_radius);
        this._axisRay = new Ray(_point, _vec);
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
        return null;
    }
}
