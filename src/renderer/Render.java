package renderer;

import elements.*;
import geometries.*;
import primitives.*;
import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;
import scene.Scene;

//import java.awt.Color;
import java.util.List;
/**
 * Render: rendering a image
 * @author rivki_kanterovich
 */
public class Render {

	    private Scene _scene;
	    private ImageWriter _imageWriter;
	    private static final double DELTA = 0.1;
	    private static final int MAX_CALC_COLOR_LEVEL = 10;
	    private static final double MIN_CALC_COLOR_K = 0.001;

	    /*************** Constructor ********************/
	    /**
	     * Constructor for creating a render
	     * @param _scene is a details of model.
	     */
	    public Render(Scene _scene) {
	        this._scene = _scene;
	    }

	    /**
	     * Constructor for creating a render
	     * @param scene is a details of model.
	     * @param imageWriter is a details of the image of a model.
	     */
	    public Render(ImageWriter imageWriter, Scene scene) {
	        this._imageWriter = imageWriter;
	        this._scene= scene;
	    }

	    /*************** getters ********************/
	    /**
	     *
	     * @return scene
	     */
	    public Scene get_scene() {
	        return _scene;
	    }

	    /**
	     * Filling the buffer according to the geometries that are in the scene.
	     * This function does not creating the picture file, but create the buffer pf pixels
	     */
	    public void renderImage() {
	    	Camera camera= _scene.getCamera();
	    	java.awt.Color background = _scene.getBackground().getColor();
	        double distance = _scene.getDistance();

	        int width = (int) _imageWriter.getWidth();
	        int height = (int) _imageWriter.getHeight();
	        int Nx = _imageWriter.getNx();
	        int Ny = _imageWriter.getNy();
	        Ray ray;
	        
	        for (int row = 0; row < Ny; row++) {
	            for (int column = 0; column < Nx; column++) {
	            	ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
	                GeoPoint closestPoint = findCLosestIntersection(ray);
	                if (closestPoint == null) {
	                    _imageWriter.writePixel(column, row, background);
	                } else {
	                    _imageWriter.writePixel(column, row, calcColor(closestPoint, ray).getColor());
	                }
	            }
	        }
	    }

	    /**
	     * Finding the closest point to the P0 of the camera.
	     * @param  intersectionPoints list of points, the function should find from
	     * this list the closet point to P0 of the camera in the scene.
	     * @return  the closest point to the camera
	     */

	    private GeoPoint getClosestPoint(List<GeoPoint> intersectionPoints) {
	    	GeoPoint result = null;
	        double mindist = Double.MAX_VALUE;

	        Point3D p0 = this._scene.getCamera().get_p0();

	        for (GeoPoint geo: intersectionPoints ) {
	        	Point3D pt = geo.getPoint();
	            double distance = p0.distance(pt);
	            if (distance < mindist){
	                mindist= distance;
	                result = geo;
	            }
	        }
	        return result;
	    }
	    

	    /**
	     * Printing the grid with a fixed interval between lines
	     * @param interval The interval between the lines.
	     */
	    public void printGrid(int interval, java.awt.Color colorsep) {
	        double rows = this._imageWriter.getNx();
	        double collumns = this._imageWriter.getNy();
	        //Writing the lines.
	        for (int col = 0; col < collumns; col++) {
	            for (int row = 0; row < rows; row++) {
	                if (col % interval == 0 || row % interval == 0) {
	                    _imageWriter.writePixel(row, col, colorsep);
	                }
	            }
	        }
	    }
	    

	    public void writeToImage() {
	        _imageWriter.writeToImage();
	    }
	    
	    private Color calcColor(GeoPoint gp, Ray inRay)
	    {
	    	return calcColor(gp, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.getAmbientLight().getIntensity());
	    }

	    /**
	     * Calculate the color intensity in a point
	     * @param point the point for which the color is required
	     * @return the color intensity
	     */
	    private Color calcColor(GeoPoint gp, Ray ray, int level, double k) {
	        if (level == 0 || k < MIN_CALC_COLOR_K) {
	            return Color.BLACK;
	        }
	        
	        Color color = gp.getGeometry().getEmissionLight();
	        Point3D pointGeo = gp.getPoint();

	        Vector v = pointGeo.subtract(_scene.getCamera().get_p0()).normalize();
	        Vector n = gp.getGeometry().getNormal(pointGeo);

	        int nShininess = gp.getGeometry().getMaterial().getnShininess();
	        double kd = gp.getGeometry().getMaterial().getkD();
	        double ks = gp.getGeometry().getMaterial().getkS();
	        
	        if (_scene.getLightSources() != null) {
	            for (LightSource lightSource : _scene.getLightSources()) {
	                Vector l = lightSource.getL(pointGeo);
	                double nl = alignZero(n.dotProduct(l));
	                double nv = alignZero(n.dotProduct(v));

	                if (nl * nv > 0) {
	                	double ktr = transparency(lightSource, l, n, gp);
	                	if (ktr * k > MIN_CALC_COLOR_K) {
		                    Color ip = lightSource.getIntensity(pointGeo).scale(ktr);
		                    color = color.add(
		                            calcDiffusive(kd, nl, ip),
		                            calcSpecular(ks, l, n, v, nShininess, ip));
	                	}
	                }
	            }
	        }
            
            if (level == 1) 
            	return Color.BLACK;
            
            double kr = gp._geometry.getMaterial().getkR();
            double kkr = k * kr;
            if (kkr > MIN_CALC_COLOR_K) {
            	Ray reflectedRay = constructReflectedRay(n, gp._point, ray);
            	GeoPoint reflectedPoint = findCLosestIntersection(reflectedRay);
                if (reflectedPoint != null)
            		color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
            }
            
            double kt = gp._geometry.getMaterial().getkT();
            double kkt = k * kt;
            if (kkt > MIN_CALC_COLOR_K) {
            	Ray refractedRay =  constructRefractedRay(n, gp._point, ray);
            	GeoPoint reflectedPoint = findCLosestIntersection(refractedRay);
                if (reflectedPoint != null)
            		color = color.add(calcColor(reflectedPoint, refractedRay, level-1, kkt).scale(kt));
            }
            
	        return color;
	    }
	    
	    /**
	     * Calculate specular component of light reflection.
	     * @param ks is exclusion factor
	     * @param d is direction from light to point
	     * @param n is normal of point
	     * @param v is vector from a viewpoint on the object
	     * @param nShininess
	     * @param il is intensity at the point
	     * @return
	     */
	    private Color calcSpecular(double ks, Vector d, Vector n, Vector v, double nExponent, Color il) {
	    	double nd = alignZero(n.dotProduct(d));
	    	Vector r = d.add(n.scale(-2 * nd)); 
	        double minusVR = alignZero(r.dotProduct(v) * (-1));
	        if (minusVR <= 0) return Color.BLACK; // view from direction opposite to r vector
	        return il.scale(ks * Math.pow(minusVR, nExponent));
	    }

	    /**
	     * Calculate Diffusive component of light reflection.
	     * @param kd is exclusion factor
	     * @param nl is scalar multiplication between vectors
	     * @param il is intensity at the point
	     * @return
	     */
	    private Color calcDiffusive(double kd, double nl, Color il) {
	        if (nl < 0) nl = -nl;
	        return il.scale(nl * kd);
	    }


	    //private boolean sign(double val) {
	      //  return (val > 0d);
	    //}
	    
	    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
	        Vector lightDirection = l.scale(-1); // from point to light source
	        Ray lightRay = new Ray(geopoint._point, lightDirection, n);
	        Point3D pointGeo = geopoint.getPoint();
	        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
	        if (intersections == null) {
	            return true;
	        }
	        double lightDistance = light.getDistance(geopoint._point);
	        for (GeoPoint gp : intersections) {
	            double temp = gp.getPoint().distance(pointGeo) - lightDistance;
	            if (alignZero(temp) <= 0 && gp._geometry.getMaterial().getkT() == 0)
	                return false;
	        }
	            return true;
	    }
	    
	    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
	        Vector lightDirection = l.scale(-1); // from point to light source
	        Ray lightRay = new Ray(geopoint._point, lightDirection, n);
	        Point3D pointGeo = geopoint.getPoint();
	        
	        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(lightRay);
	        if (intersections == null) {
	            return 1.0;
	        }
	        double lightDistance = light.getDistance(pointGeo);
	        double ktr = 1.0;
	        for (GeoPoint gp : intersections) {
	            double temp = gp.getPoint().distance(pointGeo) - lightDistance;
	            if (alignZero(temp) <= 0){
	            	ktr *= gp.getGeometry().getMaterial().getkT();
	            	if(ktr < MIN_CALC_COLOR_K)
	            		return 0.0;
	            }
	        }
	            return ktr;
	    }
	    
	    private Ray constructReflectedRay(Vector n, Point3D gp, Ray inRay) {
	    	Vector v = inRay.getNormal();
	    	double nv = n.dotProduct(v);
	    	if (nv == 0)
	    		return null;
	    	Vector r = v.subtract(n.scale(2 * nv));
	    	return new Ray(gp, r, n);
	    }
	    
	    private Ray constructRefractedRay(Vector n, Point3D gp, Ray inRay) {
	    	return new Ray(gp, inRay.getNormal(), n.scale(-1));
	    }
	    
	    private GeoPoint findCLosestIntersection(Ray ray) {
	    	if (ray == null)
	    		return null;
	    	
	        GeoPoint closestPoint = null;
	        double closestDistance = Double.MAX_VALUE;
	        Point3D ray_p0 = ray.getPoint();

	        List<GeoPoint> intersections = _scene.getGeometries().findIntersections(ray);
	        if (intersections == null)
	            return null;

	        for (GeoPoint geoPoint : intersections) {
	            double distance = ray_p0.distance(geoPoint.getPoint());
	            if (distance < closestDistance) {
	                closestPoint = geoPoint;
	                closestDistance = distance;
	            }
	        }
	        return closestPoint;
	    }
}
