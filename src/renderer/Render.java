package renderer;

import elements.*;
import primitives.*;
import static primitives.Util.*;
import geometries.Intersectable.GeoPoint;
//import geometries.Plane;
import scene.Scene;

import java.util.LinkedList;
import java.util.List;
/**
 * Render: rendering a image
 * @author rivki_kanterovich
 */
public class Render {

	    private Scene _scene;
	    private ImageWriter _imageWriter;
	    private double _amount_rays;
	    
	    private static final int MAX_CALC_COLOR_LEVEL = 10;
	    private static final double MIN_CALC_COLOR_K = 0.001;
	    
		private int _threads = 1;
		private final int SPARE_THREADS = 2;
		private boolean _print = false;
	    
	    private static final double NUM_RAYS = 5;
	    
		private class Pixel {
			private long _maxRows = 0;
			private long _maxCols = 0;
			private long _pixels = 0;
			public volatile int row = 0;
			public volatile int col = -1;
			private long _counter = 0;
			private int _percents = 0;
			private long _nextCounter = 0;

			/**
			 * The constructor for initializing the main follow up Pixel object
			 * @param maxRows the amount of pixel rows
			 * @param maxCols the amount of pixel columns
			 */
			public Pixel(int maxRows, int maxCols) {
				_maxRows = maxRows;
				_maxCols = maxCols;
				_pixels = maxRows * maxCols;
				_nextCounter = _pixels / 100;
				if (Render.this._print) System.out.printf("\r %02d%%", _percents);
			}

			/**
			 *  Default constructor for secondary Pixel objects
			 */
			public Pixel() {}

			/**
			 * Internal function for thread-safe manipulating of main follow up Pixel object - this function is
			 * critical section for all the threads, and main Pixel object data is the shared data of this critical
			 * section.<br/>
			 * The function provides next pixel number each call.
			 * @param target target secondary Pixel object to copy the row/column of the next pixel 
			 * @return the progress percentage for follow up: if it is 0 - nothing to print, if it is -1 - the task is
			 * finished, any other value - the progress percentage (only when it changes)
			 */
			private synchronized int nextP(Pixel target) {
				++col;
				++_counter;
				if (col < _maxCols) {
					target.row = this.row;
					target.col = this.col;
					if (_counter == _nextCounter) {
						++_percents;
						_nextCounter = _pixels * (_percents + 1) / 100;
						return _percents;
					}
					return 0;
				}
				++row;
				if (row < _maxRows) {
					col = 0;
					if (_counter == _nextCounter) {
						++_percents;
						_nextCounter = _pixels * (_percents + 1) / 100;
						return _percents;
					}
					return 0;
				}
				return -1;
			}

			/**
			 * Public function for getting next pixel number into secondary Pixel object.
			 * The function prints also progress percentage in the console window.
			 * @param target target secondary Pixel object to copy the row/column of the next pixel 
			 * @return true if the work still in progress, -1 if it's done
			 */
			public boolean nextPixel(Pixel target) {
				int percents = nextP(target);
				if (percents > 0)
					if (Render.this._print) System.out.printf("\r %02d%%", percents);
				if (percents >= 0)
					return true;
				if (Render.this._print) System.out.printf("\r %02d%%", 100);
				return false;
			}
		}
	    

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
	    
	    public ImageWriter get_imageWriter() {
	        return this._imageWriter;
	    }
	    
	    public double get_amountRays()
	    {
	    	return this._amount_rays;
	    }
	    
	    public void set_amountRays(double num) {
	    	this._amount_rays = num;
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
	        
	        final Pixel thePixel = new Pixel(Ny, Nx);
	        
			// Generate threads
			Thread[] threads = new Thread[_threads];
			for (int i = _threads - 1; i >= 0; --i) {
				threads[i] = new Thread(() -> {
					Pixel pixel = new Pixel();
					while (thePixel.nextPixel(pixel)) {
				        if (camera.get_dis() == 0) {
			            	Ray ray = camera.constructRayThroughPixel(Nx, Ny, pixel.col, pixel.row, distance, width, height);
			                GeoPoint closestPoint = findCLosestIntersection(ray);
			                if (closestPoint == null) {
			                    _imageWriter.writePixel(pixel.col, pixel.row, background);
			                } else {
			                    _imageWriter.writePixel(pixel.col, pixel.row, calcColor(closestPoint, ray).getColor());
			                }
				        }
				        else {
			            	Ray ray = camera.constructRayThroughPixel(Nx, Ny, pixel.col, pixel.row, distance, width, height);
			        		List<Ray> rayFocals = findRayFocalPlane(camera.get_pointView(), camera.get_pointFocal(), camera.get_widthSh(), camera.get_heightSh());
			        		rayFocals.add(ray);
			                Color color = colorPixel(rayFocals);
			                _imageWriter.writePixel(pixel.col, pixel.row, color.getColor());
				        }
					}
				});
			}

			// Start threads
			for (Thread thread : threads) thread.start();

			// Wait for all threads to finish
			for (Thread thread : threads) try { thread.join(); } catch (Exception e) {}
			if (_print) System.out.printf("\r100%%\n");
	        

	    }
	    
	    
	    private List<Ray> findRayFocalPlane(Point3D viewPoint, Point3D focalPoint, double width, double heigh){
	    	List<Point3D> points = new LinkedList<Point3D>();
	    	double xStart = viewPoint.get_x().get() - width / 2;
	    	double xEnd = viewPoint.get_x().get() + width / 2;
	    	double yStart = viewPoint.get_y().get() - heigh / 2;
	    	double yEnd = viewPoint.get_y().get() + heigh / 2;
    		double z = viewPoint.get_z().get();
	    	for(int i = 0; i < _amount_rays; i ++)
	    	{
	    		double x = (double) ((Math.random()*(xEnd - xStart + 1)) + xStart);
	    		double y = (double) ((Math.random()*(yEnd - yStart + 1)) + yStart);
		    	points.add(new Point3D(x, y, z));
	    	}
	    	points.add(new Point3D(xEnd, yEnd, z));
	    	points.add(new Point3D(xEnd, yStart, z));
	    	points.add(new Point3D(xStart, yEnd, z));
	    	points.add(new Point3D(xStart, yStart, z));
	    	
	    	List<Ray> ray = new LinkedList<Ray>();
	    	for(Point3D point : points) {
				ray.add(new Ray(point, new Vector(focalPoint.subtract(point))));
	    	}
	    	return ray;
	    }
	    
	    private Color colorPixel(List<Ray> rayFocals) {
	    	List<Color> colors = new LinkedList<Color>();
	    	for(Ray ray : rayFocals) {
	    		GeoPoint closestPoint = findCLosestIntersection(ray);
	    		if(closestPoint == null)
	    			colors.add(_scene.getBackground());
	    		else
	    			colors.add(calcColor(closestPoint, ray));
	    	}
	    	
	    	
	    	if (equalColor(colors) == true)
	    		return colors.get(0);
	    	else {
	    		Color Pixelcolor = colors.get(0);
	    		for(int i = 1; i < _amount_rays + NUM_RAYS; i++)
	    		{
	    		    Pixelcolor = Pixelcolor.add(colors.get(i));
	    		}
	    		Pixelcolor = Pixelcolor.reduce(_amount_rays + NUM_RAYS);
	    		return Pixelcolor;
	    		}
	    }
	    
	    private boolean equalColor(List<Color> colors) {
	    	for (int i = 0; i < colors.size() - 1; i++) {
	    		if (!(colors.get(i).equals(colors.get(i + 1))))
	    			return false;
	    	}
	    	return true;
	    }


	    /**
	     * Finding the closest point to the P0 of the camera.
	     * @param  intersectionPoints list of points, the function should find from
	     * this list the closet point to P0 of the camera in the scene.
	     * @return  the closest point to the camera
	     */

	    /**
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
	    
	    /**
	     * Calculate the color intensity in a point
	     * @param gp the point for which the color is required
	     * @param inRay to the point
	     * @return the color for the point
	     */
	    private Color calcColor(GeoPoint gp, Ray inRay)
	    {
	    	return calcColor(gp, inRay, MAX_CALC_COLOR_LEVEL, 1.0).add(_scene.getAmbientLight().getIntensity());
	    }

	    /**
	     * Calculate the color intensity in a point
	     * @param point the point for which the color is required
         * @param Ray to the point
         * @param level for a recursion
         * @param k is promotes transparency
	     * @return the color for the point
	     */
	    private Color calcColor(GeoPoint gp, Ray ray, int level, double k) {
	        if (level == 0 || k < MIN_CALC_COLOR_K) {
	            return Color.BLACK;
	        }
	        
	        Color color = gp.getGeometry().getEmissionLight(); //color of a geometry
	        Point3D pointGeo = gp.getPoint();

	        Vector v = pointGeo.subtract(_scene.getCamera().get_p0()).normalize();
	        Vector n = gp.getGeometry().getNormal(pointGeo); //the normal vector in a point
	        
	        int nShininess = gp.getGeometry().getMaterial().getnShininess();
	        double kd = gp.getGeometry().getMaterial().getkD(); //Attenuation factors
	        double ks = gp.getGeometry().getMaterial().getkS(); //Attenuation factors
	        
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
            //Adding reflection
            if (kkr > MIN_CALC_COLOR_K) {
            	Ray reflectedRay = constructReflectedRay(n, gp._point, ray);
            	GeoPoint reflectedPoint = findCLosestIntersection(reflectedRay);
                if (reflectedPoint != null)
            		color = color.add(calcColor(reflectedPoint, reflectedRay, level-1, kkr).scale(kr));
            }
            
            double kt = gp._geometry.getMaterial().getkT();
            double kkt = k * kt;
            //Add transparency
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
	    
	    
	    /**
	     * Calculate shadow at point.
	     * @param light
	     * @param l is vector
	     * @param n is normal in point
	     * @return Whether there is a shadow or not
	     */
	    /**
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
	    
	    /**
	     * Calculate partial shadow at point.
	     * @param light
	     * @param l is vector
	     * @param n is normal in point
	     * @return 
	     */
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
	    
	    /**
	     * Adding reflection
	     * @param n is normal of point
	     * @param gp is a geometry point
	     * @param inRay is ray to point
	     * @return reflection ray
	     */
	    private Ray constructReflectedRay(Vector n, Point3D gp, Ray inRay) {
	    	Vector v = inRay.getNormal();
	    	double nv = n.dotProduct(v);
	    	if (nv == 0)
	    		return null;
	    	Vector r = v.subtract(n.scale(2 * nv));
	    	return new Ray(gp, r, n);
	    }
	    
	    /**
	     * Add transparency
	     * @param n is normal of point
	     * @param gp is a geometry point
	     * @param inRay is ray to point
	     * @return refraction ray
	     */
	    private Ray constructRefractedRay(Vector n, Point3D gp, Ray inRay) {
	    	return new Ray(gp, inRay.getNormal(), n.scale(-1));
	    }
	    
	    
	    /**
	     * Find intersections of a ray with the scene geometries and get the
	     * intersection point that is closest to the ray head. If there are no
	     * intersections, null will be returned.
	     *
	     * @param ray intersecting the scene
	     * @return the closest point
	     */
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
	    
		public Render setMultithreading(int threads) {
			if (threads < 0)
				throw new IllegalArgumentException("Multithreading patameter must be 0 or higher");
			if (threads != 0)
				_threads = threads;
			else {
				int cores = Runtime.getRuntime().availableProcessors() - SPARE_THREADS;
				if (cores <= 2)
					_threads = 1;
				else
					_threads = cores;
			}
			return this;
		}

		/**
		 * Set debug printing on
		 * 
		 * @return the Render object itself
		 */
		public Render setDebugPrint() {
			_print = true;
			return this;
		}
}
