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
	 
///**
	@Test
	public void mini1DOF() 
	{
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 0.1, 0.1, 10));
		scene.updatDistance(1000);
		scene.updatBackground(new Color(java.awt.Color.BLACK));
		scene.updatAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0));
		scene.addGeometries(

						new Sphere(new Color(java.awt.Color.BLUE),new Material(0.3, 0.7, 100, 0.5, 0), 20, new Point3D(0, 0, 20)),
						
						new Sphere(new Color(java.awt.Color.BLUE),new Material(0.3, 0.7, 100, 0.5, 0.2), 20, new Point3D(15, -15, 100)),

						new Sphere(new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(30, 35, 30)),
						
						new Sphere(new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(60, 70, 50)),
								
						new Sphere(new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(-30, -35, 30)),
						
						new Sphere(new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(-60, -70, 50)),
						
						new Sphere(new Color(java.awt.Color.RED),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(90,105, 70)),
						
						new Sphere(new Color(java.awt.Color.RED),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(-90,-105, 70)),
						
						new Plane(new Color(java.awt.Color.BLACK), new Material(0.5, 0.5, 1200, 0.8, 0), new Point3D(0, 0, 80), new Vector(0, 0, 1)) 
								
						);

		scene.addLights(new SpotLight(new Color(java.awt.Color.WHITE), new Point3D(110, -110, -120), new Vector(0,0, 1), 1, 4E-4, 2E-5),
				new DirectionalLight(new Color(java.awt.Color.WHITE), new Vector(0, 1, 0))
				);

		ImageWriter imageWriter = new ImageWriter("mini1DOF", 200, 200, 600, 600);

		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.set_amountRays(Math.random()*(500 - 300 + 1) + 300);
		render.mini2 = false;
		render.renderImage();
		render.writeToImage();
		}
		//*/
	
	@Test
	public void mini2OSS() 
	{
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 0.1, 0.1, 10));
		scene.updatDistance(1000);
		scene.updatBackground(new Color(java.awt.Color.BLACK));
		scene.updatAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0));
		scene.addGeometries(

						new Sphere(new Color(java.awt.Color.BLUE),new Material(0.3, 0.7, 100, 0.5, 0), 20, new Point3D(0, 0, 20)),
						
						new Sphere(new Color(java.awt.Color.BLUE),new Material(0.3, 0.7, 100, 0.5, 0), 20, new Point3D(15, -15, 100)),

						new Sphere(new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(30, 35, 50)),
						
						new Sphere(new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(60, 70, 80)),
								
						new Sphere(new Color(java.awt.Color.GREEN),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(-30, -35, 50)),
						
						new Sphere(new Color(java.awt.Color.ORANGE),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(-60, -70, 80)),
						
						new Sphere(new Color(java.awt.Color.RED),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(90,105, 100)),
						
						new Sphere(new Color(java.awt.Color.RED),new Material(0.3, 0.7, 100, 0.5 , 0), 20, new Point3D(-90, -105, 100)),
						
						new Plane(new Color(java.awt.Color.BLACK), new Material(0.5, 0.5, 1200, 0.8, 0), new Point3D(0, 0, 120), new Vector(0, 0, 1)) 
								
						);

		scene.addLights(new SpotLight(new Color(java.awt.Color.WHITE), new Point3D(110, -110, -120), new Vector(0,0, 1), 1, 4E-4, 2E-5),
				new DirectionalLight(new Color(java.awt.Color.WHITE), new Vector(0, 1, 0))
				);

		ImageWriter imageWriter = new ImageWriter("miniOSS2", 200, 200, 600, 600);

		Render render = new Render(imageWriter, scene).setMultithreading(3).setDebugPrint();

		render.set_amountRays(20);
		render.mini2 = true;
		render.renderImage();
		render.writeToImage();
		}
}
