/**
 * 
 */
package unittests;

import java.awt.Color;

import org.junit.Test;
import renderer.*;
/**
 * Unit test for renderer.ImageWriter class
 * @author rivki_kanterovich
 */
public class ImageWriterTest {

	/**
     * Produce a imageWriter with background and pixel grid
     */
	@Test
	public void writeToImage() {
        String imagename = "Plane try";
        int width = 1600;
        int height = 1000;
        int nx =800;
        int ny =500;
        ImageWriter imageWriter = new ImageWriter(imagename, width, height, nx, ny);
        for (int col = 0; col < ny; col++) {
            for (int row = 0; row < nx; row++) {
                if (col % 50 == 0 || row % 50 == 0) {
                	imageWriter.writePixel(row, col, Color.blue);
                }
                else
                	imageWriter.writePixel(row, col, Color.pink);
            }
        }
        imageWriter.writeToImage();
    }

}
