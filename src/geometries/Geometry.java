package geometries;

import primitives.*;

/**
 * interface for all geometries with getNormal
 */
public abstract class Geometry implements Intersectable{
	
	protected Color _emmission;
	protected Material _material;
	
	public Geometry() {
		this._emmission = Color.BLACK;
	}
	
	public Geometry(Color _emmission) {
		this._emmission = _emmission;
		this._material = new Material(0, 0, 0);
	}
	
    public Geometry(Color _emmission, Material _material) {
        this._emmission = _emmission;
        this._material = _material;
    }
    
    public Color getEmissionLight() {
        return (_emmission);
    }
    
    public Material getMaterial() {
        return _material;
    }
    
    public abstract Vector getNormal(Point3D point);
}
