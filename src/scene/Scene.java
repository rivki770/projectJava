package scene;

import primitives.*;
import elements.*;
import geometries.*;

/**
 * Scene: class for representing image in environment
 * @author rivki_kanterovich
 */
public class Scene {
	private String _name;
	private Color _background;
	private AmbientLight _ambientLight;
	private Geometries _geometries;
	private Camera _camera;
	private double _distance;
	
	/*************** Constructor ********************/
    /**
     *
     * @param _sceneName is name of scene
     */
	public Scene(String _sceneName) {
        this._name = _sceneName;
        this._geometries = new Geometries();
    }
	
	/*************** getters ********************/
    /**
     *
     * @return name of a scene
     */
	public String getName() {
		return _name;
	}
	
	/**
    *
    * @return background of a scene
    */
	public Color getBackground() {
		return _background;
	}
	
	/**
    *
    * @return AmbientLight of a scene
    */
	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}
	
	/**
    *
    * @return Geometries in a scene
    */
	public Geometries getGeometries() {
		return _geometries;
	}
	
	/**
    *
    * @return Camera of a scene
    */
	public Camera getCamera() {
		return _camera;
	}
	
	/**
    *
    * @return Distance between camera and view plane in a scene
    */
	public double getDistance() { 
		return _distance;
	}
	
	/*************** setters ********************/
    /**
     *
     * @param background is color of scene
     */
	public void updatBackground(Color background) {
        _background = background;
    }

	/**
    *
    * @param camera in a scene
    */
    public void updatCamera(Camera _camera) {
        this._camera = _camera;
    }

    /**
    *
    * @param Distance between camera and view plane in a scene
    */
    public void updatDistance(double _distance) {
        this._distance = _distance;
    }

    /**
    *
    * @param AmbientLight of a scene
    */
    public void updatAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }
	
    /**
    *
    * @param intersectables is list of geometries in a scene
    */
	public void addGeometries(Intersectable... intersectables) {
	     for (Intersectable i:intersectables ) {
	          _geometries.add(i);
	     }
	}
}
