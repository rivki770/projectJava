/**
 * 
 */
package elements;

import primitives.Color;

/**
 * @author rivki_kanterovicàñúé
 *
 */
public abstract class Light {
	
    protected Color _intensity;
    
    public Light(Color _light) {
    	this._intensity = _light;
    }
    
    public Light() {
    	this._intensity = Color.BLACK;
    }

    public Color getIntensity() {
        return new Color(_intensity);
    }

}
