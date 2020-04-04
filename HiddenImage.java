import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class HiddenImage {
	public static void main (String[] args){
		if (args.length == 0) {
			System.out.println("ERROR: No image passed!");
			System.exit(0);
		}
		try {
			File f = new File(args[0]);
			BufferedImage img = uncover(ImageIO.read(f));	
			ImageIO.write(img, "png", new File("uncovered_java/" + f.getName()));
		}
		catch (Exception e) {
			System.out.println("No dice");
			return;
		}
	}

	public static BufferedImage uncover(int[] bitstring, int width, int height) {
		// takes in an array of magnified bits and generates the hidden image
		// can be called from other classes
		
		ArrayList<Integer> pixelString = getPixelString(bitstring);
		int newWidth = width/8;
		System.out.println(newWidth);
		int newHeight = height/8;
		System.out.println(newHeight);
		int[][] redMap = new int[newHeight][newWidth];
		int[][] greenMap = new int[newHeight][newWidth];
		int[][] blueMap = new int[newHeight][newWidth];
		
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		WritableRaster ras2 = newImage.getRaster();
		
		int count = 64;
		for (int y=0; y<newHeight; y++) {
			for (int x=0; x<newWidth; x++) {
				setChannels(y, x, redMap, greenMap, blueMap, count, pixelString);
				count+=24;
				ras2.setPixel(x, y, new int[]{redMap[y][x],greenMap[y][x],blueMap[y][x]});
			}
		}
		return newImage;
	}
	
	private static ArrayList<Integer> getPixelString(int[] bitstring) {
		ArrayList<Integer> pixelString = new ArrayList<Integer>(2);
		for (int i=0; i<bitstring.length; i++) pixelString.add(bitstring[i]);
		return pixelString;
	}

	private static BufferedImage uncover(BufferedImage image) throws Exception{
		// takes in an image passed through MagnifyBits.main (eg. altered_java.png) and generates the hidden image
		// must be called from HiddenImage.main
		
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
		int[][] redMap = new int[newHeight][newWidth];
		int[][] greenMap = new int[newHeight][newWidth];
		int[][] blueMap = new int[newHeight][newWidth];
		
		BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
		WritableRaster ras2 = newImage.getRaster();
		
		int count = 64;
		for (int y=0; y<newHeight; y++) {
			for (int x=0; x<newWidth; x++) {
				System.out.println(x + " " + y);
				setChannels(y, x, redMap, greenMap, blueMap, count, pixelString);
				count+=24;
				ras2.setPixel(x, y, new int[]{redMap[y][x],greenMap[y][x],blueMap[y][x]});
			}
		}
		return newImage;
	}

	private static void setChannels(int i, int j, int[][] redMap, int[][] greenMap, int[][] blueMap, int count, ArrayList<Integer> pixelString) {
		int c = count;
		redMap[i][j] = getChannel(pixelString, c);
		c+=8;
		greenMap[i][j] = getChannel(pixelString, c);
		c+=8;
		blueMap[i][j] = getChannel(pixelString, c);
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
	
	private static int getChannel(ArrayList<Integer> pixelString, int count) {
		int[] channelBits = new int[8];
		for (int i=0;i<channelBits.length;i++) channelBits[i] = pixelString.get(count+i);
		return bits2int(channelBits);
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
