package geometries;

import primitives.*;

//import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Geometries implements Intersectable {
	
	private List<Intersectable> _geometries = new ArrayList<>();
	
	 /*************** Constructor ********************/
    /**
     * default constructor
     */
	public Geometries() {
		_geometries = new ArrayList<Intersectable>();
	}

	/**
     * parameters constructor
     */
    public Geometries(Intersectable... _geometries) {
        add( _geometries);
    }

    /**
    * Add geometries in the geometries collection
    * @param geometrie
    */
    public void add(Intersectable... geometries) {
    	_geometries.addAll(Arrays.asList(geometries));
    }

    /**
     *
     * @param ray
     * @return list of Point3D that intersect the geometries collection
     */
    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersections = null;
        for (Intersectable geo : _geometries) {
            List<Point3D> tempIntersections = geo.findIntersections(ray);
            if (tempIntersections != null) {
                if (intersections == null)
                    intersections = new ArrayList<Point3D>();
                intersections.addAll(tempIntersections);
            }
        }
        return intersections;
     }
}
