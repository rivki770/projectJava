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

	/**
	 * 	@Test
	public void mini1part1() {
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 0.1, 0.1, 5));
		scene.updatDistance(1000);
		scene.updatBackground(Color.BLACK);
		scene.updatAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0));

		scene.addGeometries(
				
				new Sphere(new Color(java.awt.Color.RED), new Material(0.3, 0.7, 100, 0.5, 0),
				        50, new Point3D(-80, -80, 100)),
	
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.3, 0.7, 100, 0.5, 0),
						50, new Point3D(-10, -10, 50)),

				new Sphere(new Color(java.awt.Color.GREEN), new Material(0.3, 0.7, 100, 0 ,0),
						50, new Point3D(50, 50, 10)));
		

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(40, -40, -115), new Vector(0, 0, 1), 1, 4E-4, 2E-5));

		ImageWriter imageWriter = new ImageWriter("mini1part1", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.set_amountRays(80);
		render.renderImage();
		render.writeToImage();
	}
	 */

	@Test
	public void mini1DOF() 
	{
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 0.1, 0.1, 10));
		scene.updatDistance(1000);
		scene.updatBackground(new Color(java.awt.Color.PINK));
		scene.updatAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0));
		scene.addGeometries(

						new Sphere(new Color(java.awt.Color.BLUE),new Material(0.3, 0.7, 100, 0.5, 0), 20, new Point3D(0, 0,20)),

						new Sphere(new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(30, 35, 30)),
						
						new Sphere(new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(60, 70, 50)),
								
						new Sphere(new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(-30, -35,30)),
						
						new Sphere(new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(-60, -70, 50)),
						
						new Sphere(new Color(java.awt.Color.RED),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(90,105, 70)),
						
						new Sphere(new Color(java.awt.Color.RED),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(-90,-105, 70)),
						
						new Plane(new Color(0, 40, 60), new Material(0.5, 0.5, 1200, 0, 0), new Point3D(0, 0, 200), new Vector(0, 0, 1)) 
								
						);

		scene.addLights(new SpotLight(new Color(700, 400, 400), new Point3D(40, -40, -115), new Vector(0,0, 1), 1, 4E-4, 2E-5));

		ImageWriter imageWriter = new ImageWriter("mini1DOF", 200, 200, 600, 600);

		Render render = new Render(imageWriter, scene);

		render.set_amountRays(20);
		render.renderImage();
		render.writeToImage();
		}
}
