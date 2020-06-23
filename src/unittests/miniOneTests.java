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
	public void mini1part1() {
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 20, 20, 10));
		scene.updatDistance(1000);
		scene.updatBackground(Color.BLACK);
		scene.updatAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0));

		scene.addGeometries(
				
				new Sphere(new Color(java.awt.Color.RED), new Material(0.3, 0.7, 100, 0.5, 0),
				        50, new Point3D(-80, -80, 660)),
	
				new Sphere(new Color(java.awt.Color.RED), new Material(0.3, 0.7, 100, 0.5, 0),
						50, new Point3D(-10, -10, 360)),

				new Sphere(new Color(java.awt.Color.RED), new Material(0.3, 0.7, 100, 0.5 , 0),
						50, new Point3D(50, 50, 60)));
		

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(40, -40, -115), new Vector(0, 0, 1), 1, 4E-4, 2E-5));

		ImageWriter imageWriter = new ImageWriter("mini1part1", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	
    @Test
    public void mini1part2() {
        Scene scene = new Scene("Test scene");
        scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0), 20, 20, 0));
        scene.updatDistance(1000);
        scene.updatBackground(Color.BLACK);
        scene.updatAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

        scene.addGeometries(

                new Sphere(new Color(java.awt.Color.BLACK), new Material(0.8, 0.8, 30, 0.5, 0), 190, new Point3D(100, 0, -200)),
                
                new Sphere(new Color(java.awt.Color.orange), new Material(0.8, 0.8, 30, 0.5, 0), 150, new Point3D(-320, 35, -100)),

                new Sphere(new Color(java.awt.Color.GREEN), new Material(0.8, 0.8, 30, 0, 0), 95, new Point3D(-200, 95, -200)),
                
                new Sphere(new Color(java.awt.Color.blue), new Material(0.8, 0.8, 30, 0.5, 0), 110, new Point3D(-50, 95, -450)),
                
                new Sphere(new Color(java.awt.Color.RED), new Material(0.8, 0.8, 30, 0.5, 0), 70, new Point3D(200, 125, -350)),
                

                
                new Triangle(Color.BLACK, new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(500, 200, -100), new Point3D(-500, 200, -100), new Point3D(1800, 200, -700)),

                new Triangle(Color.BLACK, new Material(0.8, 1, 10000, 0, 1),
                        new Point3D(-500, 200, -100), new Point3D(1800, 200, -700), new Point3D(-1800, 200, -700))
        );

        scene.addLights(new DirectionalLight(new Color(10, 10, 10), new Vector(1, -1, 0)),
                new SpotLight(new Color(400, 400, 1020), new Point3D(-300, -300, -100), new Vector(2, 2, -3), 1, 0.00001, 0.000005));

        ImageWriter imageWriter = new ImageWriter("mini1part2", 1000, 1000, 1000, 1000);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
    }
}
