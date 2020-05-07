package geometries;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * interface for all geometries with getNormal
 */
public abstract class Geometry implements Intersectable{
	
	protected Color _emmission;
	
	public Geometry() {
		this._emmission = Color.BLACK;
	}
	
	public Geometry(Color _emmission) {
		this._emmission = _emmission;
	}
    
    public Color getEmissionLight() {
        return (_emmission);
    }
    
    public abstract Vector getNormal(Point3D point);
}
