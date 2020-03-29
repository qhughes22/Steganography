import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;

public class MagnifyBits {

	public static void main (String[] args) throws Exception {
		/*if (args[0] == null) {
			System.out.println("Usage: java MagnifyBits <image location or url>");
		}*/
		BufferedImage img = magnifyBits(ImageIO.read(new File("hide_image.png")));
		ImageIO.write(img, "png", new File("altered_java.png"));
	}

	private static BufferedImage magnifyBits(BufferedImage image) {
		int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	int[] pixels = raster.getPixel(x, y, (int[]) null);
            	for (int i=0; i < pixels.length; i++) {
            		pixels[i] = magnify(pixels[i]);
            	}
            	raster.setPixel(x, y, pixels);
            }
        }
        return image;
	}

	private static int magnify(int p) {
		if ((p & 1) == 1) return 255;
		else return 0;
		
	}
	
}
