package geometries;


/**
 * RadialGeometry: class for representing radius
 */

public abstract class RadialGeometry implements Geometry {
    double _radius;

    /*************** Constructor And Copy Constructor ********************/
    /**
     *
     * @param _radius number for radius
     */
    public RadialGeometry(double _radius) {
        this._radius = _radius;
    }

    /**
     *
     * @param other radius
     */
    public RadialGeometry(RadialGeometry other) {
        this._radius = other._radius;
    }

    /*************** getters ********************/
    /**
     *
     * @return a radius
     */
    public double get_radius() {
        return _radius;
    }
}
