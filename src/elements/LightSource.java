package elements;

import primitives.*;

/**
 * @author Rivki_kanterovich
 *
 */
public interface LightSource {
	
    /**
     * Get light source intensity as it reaches a point I<sub>P</sub>
     *
     * @param p the geometries point
     * @return the color of light intensity
     */
	
	public Color getIntensity(Point3D p);
	
    /**
     * Get normalized vector in the direction from light source
     * towards the lighted point
     *
     * @param p the geometries point
     * @return a vector from light to geometries
     */
	public Vector getL(Point3D p);

}
