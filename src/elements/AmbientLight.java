package elements;

import primitives.*;

/**
 * AmbientLight: class for representing a AmbientLight on a model
 * @author Rivki_Kanterovich
 */
public class AmbientLight extends Light{
	
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
    
 }
