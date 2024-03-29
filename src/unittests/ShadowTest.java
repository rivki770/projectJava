package unittests;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Testing basic shadows
 * 
 * @author Dan
 */
public class ShadowTest {

	/**
	 * Produce a picture of a sphere and triangle with point light and shade
	 */
	@Test
	public void SphereTriangleInitial() {
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.updatDistance(1000);
		scene.updatBackground(Color.BLACK);
		scene.updatAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleInitial", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Sphere-Triangle shading - move triangle up-right
	 */
	@Test
	public void SphereTriangleMove1() {
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.updatDistance(1000);
		scene.updatBackground(Color.BLACK);
		scene.updatAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-62, 31, 0), new Point3D(-31, 62, 0), new Point3D(-58, 58, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleMove1", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}
	
	/**
	 * Sphere-Triangle shading - move triangle upper-righter
	 */
	@Test
	public void SphereTriangleMove2() {
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.updatDistance(1000);
		scene.updatBackground(Color.BLACK);
		scene.updatAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-49, 19, 0), new Point3D(-19, 49, 0), new Point3D(-47, 47, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-100, 100, -200), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleMove2", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

	/**
	 * Sphere-Triangle shading - move spot closer
	 */
	@Test
	public void SphereTriangleSpot1() {
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.updatDistance(1000);
		scene.updatBackground(Color.BLACK);
		scene.updatAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-85, 85, -135), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleSpot1", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}	
	
	/**
	 * Sphere-Triangle shading - move spot even more close
	 */
	@Test
	public void SphereTriangleSpot2() {
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.updatDistance(1000);
		scene.updatBackground(Color.BLACK);
		scene.updatAmbientLight(new AmbientLight(Color.BLACK, 0));

		scene.addGeometries(new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
				60, new Point3D(0, 0, 200)), //
				new Triangle(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), //
						new Point3D(-70, 40, 0), new Point3D(-40, 70, 0), new Point3D(-68, 68, 4)));

		scene.addLights(new SpotLight(new Color(400, 240, 0), //
				new Point3D(-74, 74, -65), new Vector(1, -1, 3), 1, 1E-5, 1.5E-7));

		ImageWriter imageWriter = new ImageWriter("sphereTriangleSpot2", 200, 200, 400, 400);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}	
	
	/**
	 * Produce a picture of a two triangles lighted by a spot light with a Sphere producing a shading
	 */
	@Test
	public void trianglesSphere() {
		Scene scene = new Scene("Test scene");
		scene.updatCamera(new Camera(new Point3D(0, 0, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)));
		scene.updatDistance(1000);
		scene.updatBackground(Color.BLACK);
		scene.updatAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		scene.addGeometries( //
				new Triangle(Color.BLACK, new Material(0, 0.8, 60), //
						new Point3D(-150, 150, 115), new Point3D(150, 150, 135), new Point3D(75, -75, 150)), //
				new Triangle(Color.BLACK, new Material(0, 0.8, 60), //
						new Point3D(-150, 150, 115), new Point3D(-70, -70, 140), new Point3D(75, -75, 150)), //
				new Sphere(new Color(java.awt.Color.BLUE), new Material(0.5, 0.5, 30), // )
						30, new Point3D(0, 0, 115)));

		scene.addLights(new SpotLight(new Color(700, 400, 400), //
				new Point3D(40, -40, -115), new Vector(-1, 1, 4), 1, 4E-4, 2E-5));

		ImageWriter imageWriter = new ImageWriter("trianglesSphere", 200, 200, 600, 600);
		Render render = new Render(imageWriter, scene);

		render.renderImage();
		render.writeToImage();
	}

}
