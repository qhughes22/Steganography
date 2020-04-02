import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class HiddenImage {
	public static void main (String[] args) throws Exception{
		if (args[0] == null) {
			System.out.println("ERROR: No image passed!");
			System.exit(0);
		}
			BufferedImage img = uncover(ImageIO.read(new File(args[0])));
			ImageIO.write(img, "png", new File("uncovered_java.png"));
	}

	private static BufferedImage uncover(BufferedImage image) {
		int width = image.getWidth();
        int height = image.getHeight();
		WritableRaster raster = image.getRaster();
		int[][][] pixelMap = new int[height][width][3];
		for (int y=0;y<height;y++) {
			for (int x=0;x<width;x++) {
				int[] pixel = raster.getPixel(x, y, (int[]) null);
				pixelMap[y][x][0] = pixel[0];
				pixelMap[y][x][1] = pixel[1];
				pixelMap[y][x][2] = pixel[2];
			}
		}
		ArrayList<Integer> pixelString = getPixelString(pixelMap);
		int newWidth = getWidth(pixelString);
		System.out.println(newWidth);
		int newHeight = getHeight(pixelString);
		System.out.println(newHeight);
		int[][][] newMap = new int[newHeight][newWidth][3];
		System.out.println(newMap.length + " " + newMap[0].length + " " + newMap[0][0].length);
		
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		WritableRaster ras2 = newImage.getRaster();
		
		int count = 64;
		for (int y=0; y<newHeight; y++) {
			for (int x=0; x<newWidth; x++) {
				System.out.println(x + " " + y);
				for (int z=0; z<3; z++) {
					newMap[y][x][z] = getChannel(pixelString, count);
					count+=8;
				}
				ras2.setPixel(x, y, newMap[y][x]);
			}
		}
		return newImage;
	}

	private static int getChannel(ArrayList<Integer> pixelString, int count) {
		int[] channelBits = new int[8];
		for (int i=0;i<channelBits.length;i++) channelBits[i] = pixelString.get(count+i);
		return bits2int(channelBits);
	}

	private static ArrayList<Integer> getPixelString(int[][][] pixelMap) {
		ArrayList<Integer> pixelString = new ArrayList<Integer>(2);
		for (int x=0; x<pixelMap.length; x++) {
			for (int y=0; y<pixelMap[x].length; y++) {
				for (int z=0; z<pixelMap[x][y].length; z++) {
					pixelString.add(int2bit(pixelMap[x][y][z]));
				}
			}
		}
		return pixelString;
	}

	private static int getHeight(ArrayList<Integer> pixelString) {
		int[] heightBits = new int[32];
		for (int i=0; i<heightBits.length; i++) heightBits[i] = pixelString.get(i);
		return bits2int(heightBits);
	}

	private static int bits2int(int[] bits) {
		int val = 0;
		for (int i=0; i<bits.length; i++) val+=(bits[bits.length-1-i]*Math.pow(2, i));
		return val;
	}

	private static int getWidth(ArrayList<Integer> pixelString) {
		int[] widthBits = new int[32];
		for (int i=0; i<widthBits.length; i++) widthBits[i] = pixelString.get(i+32);
		return bits2int(widthBits);
	}
	private static int int2bit(int n) {
		if (n == 255) return 1;
		else return 0;
	}
}
