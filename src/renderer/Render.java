package renderer;

import elements.*;
import geometries.*;
import primitives.*;
import geometries.Intersectable.GeoPoint;
import scene.Scene;

import java.awt.Color;
import java.util.List;
/**
 * Render: rendering a image
 * @author rivki_kanterovich
 */
public class Render {

	    private Scene _scene;
	    private ImageWriter _imageWriter;

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
	    	Intersectable geometries = _scene.getGeometries();
	    	java.awt.Color background = _scene.getBackground().getColor();
	        double  distance = _scene.getDistance();

	        int width = (int) _imageWriter.getWidth();
	        int height = (int) _imageWriter.getHeight();
	        int Nx = _imageWriter.getNx();
	        int Ny = _imageWriter.getNy();
	        Ray ray;
	        
	        for (int row = 0; row < Ny; row++) {
	            for (int column = 0; column < Nx; column++) {
	                ray = camera.constructRayThroughPixel(Nx, Ny, column, row, distance, width, height);
	                List<GeoPoint> intersectionPoints = geometries.findIntersections(ray);
	                if (intersectionPoints == null) {
	                    _imageWriter.writePixel(column, row, background);
	                } 
	                else {
	                	GeoPoint closestPoint = getClosestPoint(intersectionPoints);
	                    _imageWriter.writePixel(column, row, calcColor(closestPoint));
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
	    public void printGrid(int interval, Color colorsep) {
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
	     * @param point the point for which the color is required
	     * @return the color intensity
	     */
	    private Color calcColor(GeoPoint intersection) {
	        primitives.Color resultColor;
	        primitives.Color ambientLight = _scene.getAmbientLight().getIntensity();
	        primitives.Color emissionLight = intersection.getGeometry().getEmissionLight();
	        //List<LightSource> lights = _scene.getLightSources();

	        resultColor = ambientLight;
	        resultColor = resultColor.add(emissionLight);
	        return resultColor.getColor();
	    }
}
