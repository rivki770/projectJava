/**
 * 
 */
package scene;

import primitives.*;
import elements.*;
import geometries.*;

/**
 * * @author rivki_kanterovich
 *
 */
public class Scene {
	private String _name;
	private Color _background;
	private AmbientLight _ambientLight;
	private Geometries _geometries;
	private Camera _camera;
	private double _distance;
	
	public Scene(String _sceneName) {
        this._name = _sceneName;
        this._geometries = new Geometries();
    }
	
	public String getName() {
		return _name;
	}
	
	public Color getBackground() {
		return _background;
	}
	
	public AmbientLight getAmbientLight() {
		return _ambientLight;
	}
	
	public Geometries getGeometries() {
		return _geometries;
	}
	
	public Camera getCamera() {
		return _camera;
	}
	
	public double getDistance() { 
		return _distance;
	}
	
	public void updatBackground(Color background) {
        _background = background;
    }

    public void updatCamera(Camera _camera) {
        this._camera = _camera;
    }

    public void updatDistance(double _distance) {
        this._distance = _distance;
    }

    public void updatAmbientLight(AmbientLight _ambientLight) {
        this._ambientLight = _ambientLight;
    }
	
	public void addGeometries(Intersectable... intersectables) {
	     for (Intersectable i:intersectables ) {
	          _geometries.add(i);
	     }
	}
}
