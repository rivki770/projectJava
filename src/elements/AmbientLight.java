/**
 * AmbientLight: class for representing a AmbientLight on a model
 */
package elements;

import primitives.*;

/**
 * @author Rivki_Kanterovich
 *
 */
public class AmbientLight {
	
	private Color _intensity;
	
	/*************** Constructor ********************/
    /**
     * Constructor for creating a AmbientLight
     *
     * @param _ia is a color of ambientLight
     * @param ka is exclusion factor.
     */	
    public AmbientLight(Color ia, double ka) {
        this._intensity = ia.scale(ka);
    }
    
    /*************** getters ********************/
    /**
     *
     * @return the color with ambientLight
     */
    public java.awt.Color getIntensity() {
        return _intensity.getColor();
    }
}
