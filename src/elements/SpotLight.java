/**
 * 
 */
package elements;

import primitives.*;

/**
 * Camera: class for representing a SpotLight on a model, It's inherits PointLight
 * @author rivki_kanterovich
 */
public class SpotLight extends PointLight{
	
	private Vector _direction;
    
    /*************** Constructor ********************/
    /**
     * Constructor for creating a spotLight
     *
     * @param colorintensity is color intensity
     * @param _position is point of a lights.
     * @param __direction is direction of light
     * @param _kC is exclusion factor
     * @param _kL is exclusion factor
     * @param _kQ is exclusion factor
     */
    public SpotLight(Color colorIntensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ) {
        super(colorIntensity,_position,_kC,_kL,_kQ);
        this._direction = new Vector(_direction).normalized();
     }
    
    /**
     * @param p the geometries point
     * @return the color in point of geometries
     */
    @Override
    public Color getIntensity(Point3D p) {

        Color pointLightIntensity = super.getIntensity(p);
        double projection = _direction.dotProduct(getL(p));

        Color IL = pointLightIntensity.scale(Math.max(0,projection));
        return IL;
    }
    
    /**
     * @param p the geometries point
     * @return a vector from light to geometries
     */
    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        } else {
            return p.subtract(_position).normalize();
        }
    }

}
