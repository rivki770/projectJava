package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * Cylinder: class for representing cylinder in 3D environment
 */

public class Cylinder extends Tube {
    double _height;

    /*************** Constructor ********************/
    /**
     *
     * @param _radius parameter of radius in a cylinder
     * @param _axisRay parameter of ray
     * @param _height parameter of height in cylinder
     */

    public Cylinder(double _radius, Ray _axisRay, double _height) {
        super(_radius, _axisRay);
        this._height = _height;
    }

    /**
     *
     * @param _radius parameter of radius in a cylinder
     * @param _vec parameter vector for ray of cylinder
     * @param _point parameter point for ray of cylinder
     * @param _height parameter of height in cylinder
     */
    public Cylinder(double _radius, Vector _vec, Point3D _point, double _height) {
        super(_radius, _vec, _point);
        this._height = _height;
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
        return null;
    }
}
