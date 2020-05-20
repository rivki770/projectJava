package elements;

import primitives.*;

/**
 * Camera: class for representing a DirectionLight on a model
 * @author rivki_kanterovich
 */
public class DirectionalLight extends Light implements LightSource{
	private Vector _direction;
	
    /*************** Constructor ********************/
    /**
     * Constructor for creating a DirectionLight
     *
     * @param colorintensity is color intensity
     * @param direction is vector from light to geometries.
     */
    public DirectionalLight(Color intensity, Vector direction) {
        _intensity = intensity;
        _direction = direction.normalized();
    }
	
    /**
     * @param p the geometries point
     * @return the color of light intensity
     */
    @Override
	public Color getIntensity(Point3D p) {
		return this._intensity;
	}
	
    /**
     * @param p the geometries point
     * @return a vector from light to geometries
     */
    @Override
	public Vector getL(Point3D p) {
		return this._direction;
	}
    
    @Override
    public double getDistance(Point3D point) {
//        return  this._direction.getHead().distance(point);
        return Double.POSITIVE_INFINITY;
    }
}
