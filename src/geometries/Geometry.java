package geometries;

import primitives.*;

/**
 * interface for all geometries with getNormal
 */
public abstract class Geometry implements Intersectable{
	
	protected Color _emmission;
	protected Material _material;
	
    /*************** Constructor ********************/
    /**
     * Default constructor
     */
	public Geometry() {
		this._emmission = Color.BLACK;
	}
	
    /**
     * @param _emmission is color of geometry
     */
	public Geometry(Color _emmission) {
		this._emmission = _emmission;
		this._material = new Material(0, 0, 0);
	}
	
    /**
     * @param _emmission is color of geometry
     * @param _material of a geometry
     */
    public Geometry(Color _emmission, Material _material) {
        this._emmission = _emmission;
        this._material = _material;
    }
    
    /*************** getters ********************/
    /**
     *
     * @return color of geometry
     */
    public Color getEmissionLight() {
        return (_emmission);
    }
    
    /**
    *
    * @return _material of a geometry
    */
    public Material getMaterial() {
        return _material;
    }
    
    public abstract Vector getNormal(Point3D point);
}
