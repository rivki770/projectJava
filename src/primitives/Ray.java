package primitives;



/**
 * Ray: class for representing Ray in environment
 */

public class Ray {
    Point3D _point;
    primitives.Vector _normal;

    /*************** Constructor ********************/
    /**
     *
     * @param point point3D
     * @param normal normalize vector
     */
    public Ray(Point3D point, Vector normal) {

        //if (normal.length() != 1) {
           // throw new IllegalArgumentException("The vector isn't normalize vector");
        //}
        this._point = point;
        this._normal = normal.normalize();
    }

    /**
     *
     * @param other vector for copy constructor
     */
    public Ray(Ray other) {
        this._point = other._point;
        this._normal = other._normal;
    }

    /*************** getters ********************/
    /**
     *
     * @return parameters of Tube
     */
    public Point3D getPoint() {
        return new Point3D(_point);
    }

    public Vector getNormal() {
        return new Vector(_normal);
    }

    /*************** toString() and equals() ********************/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ray ray = (Ray) o;
        return _point.equals(ray._point) &&
                _normal.equals(ray._normal);
    }

    @Override
    public String toString() {
        return "Ray: " +
                "Point3D = " + _point.toString() +
                ", Vector = " + _normal.toString();
    }
}
