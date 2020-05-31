package primitives;
import static primitives.Util.*;



/**
 * Ray: class for representing Ray in environment
 */

public class Ray {
	
    private static final double DELTA = 0.1;
    
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
        this._point = new Point3D(other._point);
        this._normal = other._normal;
    }
    
    public Ray(Point3D point, Vector direction, Vector normal) {
    	_normal = new Vector(direction).normalized();

        double nv = normal.dotProduct(direction);
        
        Vector delta = normal.scale((nv > 0 ? DELTA : -DELTA));
        _point = point.add(delta);
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
    
    public Point3D getTargetPoint(double length) {
        return isZero(length ) ? _point : _point.add(_normal.scale(length));
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
