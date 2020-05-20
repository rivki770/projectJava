package elements;

import primitives.*;


/**
 * Camera: class for representing a PointLight on a model
 * @author rivki_kanterovich
 */
public class PointLight extends Light implements LightSource{
	protected Point3D _position;
	protected double _kC;
	protected double _kL;
	protected double _kQ;
	
    /*************** Constructor ********************/
    /**
     * Constructor for creating a pointLight
     *
     * @param colorintensity is color intensity
     * @param _position is point of a lights.
     * @param _kC is exclusion factor
     * @param _kL is exclusion factor
     * @param _kQ is exclusion factor
     */
    public PointLight(Color colorIntensity, Point3D _position, double _kC, double _kL, double _kQ) {
        this._intensity = colorIntensity;
        this._position = new Point3D(_position);
        this._kC = _kC;
        this._kL = _kL;
        this._kQ = _kQ;
    }
    
    public PointLight(Color colorIntensity, Point3D _position) {
        this(colorIntensity, _position, 1d, 0d, 0d);
    }
	
    /**
     * @param p the geometries point
     * @return the color in point of geometries
     */
    @Override
	public Color getIntensity(Point3D p) {
        double dsquared = p.distanceSquared(_position);
        double d = p.distance(_position);

        Color IL = _intensity.reduce(_kC + d *_kL + dsquared* _kQ);

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
    
    @Override
    public double getDistance(Point3D point) {
        return this.getL(point).get_head().distance(point);
    }

}
