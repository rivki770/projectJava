/**
 * Camera: class for representing a camera in model
 */
package elements;

import primitives.*;
import static primitives.Util.*;

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
    
    /*************** Constructor ********************/
    /**
     * Constructor for creating a point
     *
     * @param _Po is center Point of a camera
     * @param _vTo is vector from _Po to center the viea plane
     * @param _vUp is vector from _Po to up.
     */
    public Camera(Point3D _p0, Vector _vTo, Vector _vUp) {

        //if the the vectors are not orthogonal, throw exception.
        if (_vUp.dotProduct(_vTo) != 0)
            throw new IllegalArgumentException("the vectors must be orthogonal");

        this._p0 =  new Point3D(_p0);
        this._vTo = _vTo.normalized();
        this._vUp = _vUp.normalized();

        _vRight = this._vTo.crossProduct(this._vUp).normalize();
    }
    
    /*************** getters ********************/
    /**
     *
     * @return new Point with _p0 value
     */
    public Point3D get_p0() {
        return new Point3D(_p0);
    }
    /**
    *
    * @return new Vector with _vTo value
    */
    public Vector get_vTo() {
        return new Vector(_vTo);
    }
    /**
    *
    * @return new Vector with _vUp value
    */
    public Vector get_vUp() {
        return new Vector(_vUp);
    }
    /**
    *
    * @return new Vector with _vRight value
    */
    public Vector get_vRight() {
        return new Vector(_vRight);
    }
    
    /**
    *
    * @param nX is amount of pixels in length
    * @param nY is amount of pixels in width
    * @param j is row of a pixel 
    * @param i is a column of a pixel
    * @param screenDistance is distance between camera and view plane
    * @param screenWidth is the width of a view plane
    * @param screenHeight is the length of a view plane 
    * @return Ray that comes out of the center of the camera and cuts the view plane
    */
    public Ray constructRayThroughPixel(int nX, int nY,
            int j, int i, double screenDistance,
            double screenWidth, double screenHeight) {
    	 if (isZero(screenDistance)) //The camera on the view plane
         {
             throw new IllegalArgumentException("distance cannot be 0");
         }

         Point3D Pc = _p0.add(_vTo.scale(screenDistance));

         double Ry = screenHeight/nY;
         double Rx = screenWidth/nX;

         double yi =  ((i - nY/2d)*Ry + Ry/2d);
         double xj=   ((j - nX/2d)*Rx + Rx/2d);

         Point3D Pij = Pc;

         if (! isZero(xj))
         {
             Pij = Pij.add(_vRight.scale(xj));
         }
         if (! isZero(yi))
         {
             Pij = Pij.add(_vUp.scale(-yi));
         }
         
         Vector Vij = Pij.subtract(_p0);

         return new Ray(_p0,Vij);
    }

}
