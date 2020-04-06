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

	private static BufferedImage magnifyBitsInImage(BufferedImage image, boolean least) {
		// takes an image (such as hide_image.png or hide_text.png) and creates a new image with all pixel channels' LSB magnified
		// must be called from MagnifyBits.main
		
		// least: True = 1st LSB, False = 2nd LSB
		
		int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	int[] pixels = raster.getPixel(x, y, (int[]) null);
            	for (int i=0; i < pixels.length; i++) {
            		if (least) pixels[i] = magnify(pixels[i]);
            		else pixels[i] = magnify2(pixels[i]);
            	}
            	raster.setPixel(x, y, pixels);
            }
        }
        return image;
	}
	
	private static BufferedImage magnifyBitsInImage(BufferedImage image, int channel, boolean least) {
		// takes an image (such as hide_image.png or hide_text.png) and creates a new image with all pixel channels' LSB magnified
		// must be called from MagnifyBits.main
		
		// channel: 0 = red, 1 = green, 2 = blue
		// least: True = 1st LSB, False = 2nd LSB
		
		
		int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	int[] pixels = raster.getPixel(x, y, (int[]) null);
            	if (least) pixels[channel] = magnify(pixels[channel]);
            	else pixels[channel] = magnify2(pixels[channel]);
            	raster.setPixel(x, y, pixels);
            }
        }
        return image;
	}
	
	public static void generateMagnifiedImage(int[] bitstring, BufferedImage image) throws Exception{
		// takes an image and an array of its LSB's and generates an image with those bits magnified
		// can be called from other classes
		
		int width = image.getWidth();
		int height = image.getHeight();
		WritableRaster raster = image.getRaster();
		int[] magnifiedBits = magnifyBitsInArray(bitstring);
		
		int count = 0;
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				int[] pixels = raster.getPixel(x, y, (int[]) null);
				for (int i=0; i < 3; i++) {
					pixels[i] = magnifiedBits[count];
					count++;
				}
				raster.setPixel(x, y, pixels);
			}
		}
		ImageIO.write(image, "png", new File("altered_java.png"));
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
	private static int magnify2(int p) {
		if (((p & 2) >> 1) == 1) return 255;
		else return 0;
	}
}
