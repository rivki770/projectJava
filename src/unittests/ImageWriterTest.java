/**
 * 
 */
package unittests;

import java.awt.Color;

//import static org.junit.Assert.*;

import org.junit.Test;
import renderer.*;
//import elements.*;
//import primitives.*;
/**
 * @author rivki_kanterovich
 *
 */
public class ImageWriterTest {

	@Test
	public void writeToImage() {
        String imagename = "Plane try";
        int width = 1600;
        int height = 1000;
        int nx =160;
        int ny =100;
        ImageWriter imageWriter = new ImageWriter(imagename, width, height, nx, ny);
        for (int col = 0; col < ny; col++) {
            for (int row = 0; row < nx; row++) {
                if (col % 10 == 0 || row % 10 == 0) {
                	imageWriter.writePixel(row, col, Color.WHITE);
                }
            }
        }
        imageWriter.writeToImage();
    }

}
