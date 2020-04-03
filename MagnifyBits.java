import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;

public class MagnifyBits {

	public static void main (String[] args) throws Exception {
		BufferedImage img = magnifyBitsInImage(ImageIO.read(new File("hide_image.png")));
		ImageIO.write(img, "png", new File("altered_java.png"));
		args = new String[1];
		args[0] = "altered_java.png";
		HiddenImage.main(args);
	}

	private static BufferedImage magnifyBitsInImage(BufferedImage image) {
		// takes an image (such as hide_image.png or hide_text.png) and creates a new image with all pixel channels' LSB magnified
		// must be called from MagnifyBits.main
		
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
	
	public static int[] magnifyBitsInArray(int[] bitstring) {
		// takes in an int[] passed through a method such as Steg.getLeastSignificantBit and magnifies each bit
		// can be called from other classes
		
		for (int i=0; i<bitstring.length;i++) bitstring[i] = magnify(bitstring[i]);
		return bitstring;
	}

	public static int magnify(int p) {
		if ((p & 1) == 1) return 255;
		else return 0;
		
	}
	
}
