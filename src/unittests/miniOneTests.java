/**
 * 
 */
package unittests;

//import static org.junit.Assert.*;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * @author אסתי
 *
 */
public class miniOneTests {

	@Test
	public void trianglesSphere() {
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 20, 20, 100));
		scene.updatDistance(1000);
		scene.updatBackground(Color.BLACK);
		scene.updatAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0));

		scene.addGeometries(
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.3, 0.7, 100, 0, 0),
							50, new Point3D(-70, -70, 500)),
				new Sphere(new Color(java.awt.Color.GREEN), new Material(0.3, 0.7, 100, 0, 0),
						50, new Point3D(-20, -20, 250)),
				new Sphere(new Color(java.awt.Color.RED), new Material(0.3, 0.7, 100, 0, 0),
						50, new Point3D(40, 40, 60)));
		

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(40, -40, -115), new Vector(0, 0, 1), 1, 4E-4, 2E-5));

		ImageWriter imageWriter = new ImageWriter("mini", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	
	@Test
	public void fhfhfh() {
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 20, 20, 100));
		scene.updatDistance(1000);
		scene.updatBackground(Color.BLACK);
		scene.updatAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0));

		scene.addGeometries(
                new Triangle(new Color(128, 49, 106), new Material(1, 1, 50000, 0, 0),
                		new Point3D(-10, -10, 220),
                        new Point3D(99.12, -73, 220),
                        new Point3D(99.12, 53, 220)),

                new Triangle(new Color(52, 38, 97), new Material(1, 1, 50000, 0, 0),
                		new Point3D(-10, -10, 220),
                        new Point3D(-10, 116, 220),
                        new Point3D(99.12, 53, 220)),

                new Triangle(new Color(26, 98, 157), new Material(1, 1, 50000, 0, 0),
                		new Point3D(-10, -10, 220),
                        new Point3D(-10, 116, 220),
                        new Point3D(-99.12, 53, 220)),

                new Triangle(new Color(27, 155, 192), new Material(1, 1, 50000, 0, 0),
                		new Point3D(-10, -10, 220),
                        new Point3D(-99.12, -73, 220),
                        new Point3D(-99.12, 53, 220)));
		

        scene.addLights(new DirectionalLight(new Color(10, 10, 10), new Vector(1, -1, 0)),
                new SpotLight(new Color(400, 400, 1020), new Point3D(-300, -300, -100), new Vector(2, 2, -3), 1, 0.00001, 0.000005));

		ImageWriter imageWriter = new ImageWriter("mIRA", 1000, 1000, 1000, 1000);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

}
