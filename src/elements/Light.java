/**
 * 
 */
package elements;

import primitives.Color;

/**
 * Light: interface of light intensity
 * @author rivki_kanterovich
 */

public abstract class Light {
	
    protected Color _intensity;
    
    /*************** Constructor ********************/
    /**
     * Constructor for creating a light intensity
     * @param _light is color intensity.
     */
    public Light(Color _light) {
    	this._intensity = _light;
    }
    
    /**
     * Default constructor for creating a light intensity
     */
    public Light() {
    	this._intensity = Color.BLACK;
    }

    /*************** getters ********************/
    /**
    *
    * @return the color of light intensity
    */
    public Color getIntensity() {
        return new Color(_intensity);
    }

}
