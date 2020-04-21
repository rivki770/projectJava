/**
 * 
 */
package elements;

import primitives.Point3D;
import primitives.Ray;
//import primitives.Util;
import primitives.Vector;

//import static primitives.Util.isZero;

/**
 * @author rivki_kanterovich
 *
 */
public class Camera {
	
    Point3D _p0;
    Vector _vTo;
    Vector _vUp;
    Vector _vRight;
    
    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {

        //if the the vectors are not orthogonal, throw exception.
        if (_vUp.dotProduct(_vTo) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");

        this._p0 =  new Point3D(_p0);
        this._vTo = _vTo.normalized();
        this._vUp = _vUp.normalized();

        _vRight = this._vTo.crossProduct(this._vUp).normalize();

    }
    
    public Point3D get_p0() {
        return new Point3D(_p0);
    }

    public Vector get_vTo() {
        return new Vector(_vTo);
    }

    public Vector get_vUp() {
        return new Vector(_vUp);
    }

    public Vector get_vRight() {
        return new Vector(_vRight);
    }
    
    public Ray constructRayThroughPixel(int nX, int nY,
            int j, int i, double screenDistance,
            double screenWidth, double screenHeight) {
    	return null;
    }

}
