import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;

public class MagnifyBits {

	public static void main (String[] args) throws Exception {
		File f = new File("orig_img/WinkyFace.png");
		System.out.println(f.getName());
		BufferedImage img = magnifyBitsInImage(ImageIO.read(f), 6, 3, false);
		String newFile = "altered_java/" + f.getName();
		ImageIO.write(img, "png", new File(newFile));
		/*args = new String[1];
		args[0] = newFile;
		HiddenImage.main(args);*/
	}
	
	public static BufferedImage magnifyBitsInImage(BufferedImage image, int channel, int bitMag, boolean magAlpha) {
		// takes an image (such as hide_image.png or hide_text.png) and creates a new image with all pixel channels' LSB magnified
		// must be called from MagnifyBits.main
		
		// channel: 0 = red, 1 = green, 2 = blue, 3 = g&b, 4 = r&b, 5 = r&g, 6 = all; else = none (if you just want to magnify alpha)
		// 
		// bitMag: 1 = 1st LSB, 2 = 2nd LSB, else = both
		
		
		int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
            	int[] pixels = raster.getPixel(x, y, (int[]) null);
            	if (channel<3) oneChannel(pixels, channel, bitMag);
            	else if (channel<6) twoChannels(pixels, channel, bitMag);
            	else if (channel == 6) allChannels(pixels, bitMag);
            	else {
            		for (int i=0;i<3;i++) pixels[i] = 0;
            	}
            	if (magAlpha) {
            		try {doAlpha(pixels, bitMag);}
            		catch (ArrayIndexOutOfBoundsException e) {System.out.println("No Alpha");}
            	}
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
			}
		}
		ImageIO.write(image, "png", new File("altered_java.png"));
	}
	
	public static int[] magnifyBitsInArray(int[] bitstring) {
		// takes in an int[] passed through a method such as Steg.getLeastSignificantBit and magnifies each bit
		// can be called from other classes
		
		for (int i=0; i<bitstring.length;i++) bitstring[i] = magnifyLeast(bitstring[i]);
		return bitstring;
	}

	public static void oneChannel (int[] pixels, int channel, int bitMag) {
		int mag = 0;
		
		if (bitMag == 1) mag = magnifyLeast(pixels[channel]);
		else if (bitMag == 2) mag = magnify2ndLeast(pixels[channel]);
		else mag = magnifyBoth(pixels[channel]);
		
		for (int i=0;i<3;i++) pixels[i] = 0;
		pixels[channel] = mag;
	}
	
	public static void twoChannels (int[] pixels, int channel, int bitMag) {
		allChannels(pixels,bitMag);
		pixels[channel%3] = 0;
	}
	
	public static void allChannels (int[] pixels, int bitMag) {
		for (int i=0;i<3;i++) {
			if (bitMag == 1) pixels[i] = magnifyLeast(pixels[i]);
			else if (bitMag == 2) pixels[i] = magnify2ndLeast(pixels[i]);
			else pixels[i] = magnifyBoth(pixels[i]);
		}
	}
	
	public static void doAlpha(int[] pixels, int bitMag) {
		if (bitMag == 1) pixels[3] = magnifyLeast(pixels[3]);
		else if (bitMag == 2) pixels[3] = magnify2ndLeast(pixels[3]);
		else pixels[3] = magnifyBoth(pixels[3]);
	}
	
	public static int magnifyLeast(int p) {
		if ((p & 1) == 1) return 255;
		else return 0;
		
	}
	
	public static int magnify2ndLeast(int p) {
		if (((p & 2) >> 1) == 1) return 255;
		else return 0;
	}
	
	public static int magnifyBoth(int p) {
		int n = 0;
		if (((p & 2) >> 1) == 1) n += (15 << 4);
		if ((p & 1) == 1) n+= 15;
		return n;
	}
}
