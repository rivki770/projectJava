/**
 * 
 */
package elements;

import primitives.*;

/**
 * @author Rivki_Kanterovich
 *
 */
public class AmbientLight {
	
	private Color _intensity;

    public AmbientLight(Color ia, double ka) {
        this._intensity = ia.scale(ka);
    }
    
    public java.awt.Color getIntensity() {
        return _intensity.getColor();
    }
}
