/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author rivki_kanterovich
 *
 */
public class DirectionalLight extends Light implements LightSource{
	private Vector _direction;
	
    public DirectionalLight(Color colorintensity, Vector direction) {
        _intensity = colorintensity;
        _direction = direction.normalized();
    }
	
    @Override
	public Color getIntensity(Point3D p) {
		return this._intensity;
	}
	
    @Override
	public Vector getL(Point3D p) {
		return this._direction;
	}

}
