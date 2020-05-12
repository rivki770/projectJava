/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author rivki_kanterovich
 *
 */
public class SpotLight extends PointLight{
	
	private Vector _direction;
    
    public SpotLight(Color colorIntensity, Point3D _position, Vector _direction, double _kC, double _kL, double _kQ) {
        super(colorIntensity,_position,_kC,_kL,_kQ);
        this._direction = new Vector(_direction).normalized();
     }
    
    @Override
    public Color getIntensity(Point3D p) {

        Color pointLightIntensity = super.getIntensity(p);
        double projection = _direction.dotProduct(getL(p));

        Color IL = pointLightIntensity.scale(Math.max(0,projection));
        return IL;
    }

    @Override
    public Vector getL(Point3D p) {
        if (p.equals(_position)) {
            return null;
        } else {
            return p.subtract(_position).normalize();
        }
    }

}
