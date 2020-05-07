package geometries;

import java.util.List;

import primitives.*;
import static primitives.Util.*;

/**
 * Triangle: class for representing Triangle in environment
 */

public class Triangle extends Polygon {

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        super(p1, p2, p3);
    }
    
    public Triangle(Color _emmission, Point3D p1, Point3D p2, Point3D p3) {
    	super(_emmission, p1, p2, p3);
	}
    
    public Triangle(Color _emmission, Material _material, Point3D p1, Point3D p2, Point3D p3) {
    	super(_emmission, _material, p1, p2, p3);
	}

	@Override
    public List<GeoPoint> findIntersections(Ray ray) {
        List<GeoPoint> intersections = _plane.findIntersections(ray);
        if (intersections == null) return null;

        Point3D p0 = ray.getPoint();
        Vector v = ray.getNormal();

        Vector v1 = _vertices.get(0).subtract(p0);
        Vector v2 = _vertices.get(1).subtract(p0);
        Vector v3 = _vertices.get(2).subtract(p0);

        double s1 = v.dotProduct(v1.crossProduct(v2));
        if (isZero(s1)) return null;
        double s2 = v.dotProduct(v2.crossProduct(v3));
        if (isZero(s2)) return null;
        double s3 = v.dotProduct(v3.crossProduct(v1));
        if (isZero(s3)) return null;
        
        intersections.get(0)._geometry = this;

        return ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0)) ? intersections : null;

    }
}
