
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Steg {
    public static void main(String[] args) throws Exception {
        BufferedImage image = ImageIO.read(new File("hide_text.png"));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height: " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        int count = 0;
        ArrayList<Integer> all = new ArrayList<>();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                if (true) {
                    all.add((pixels[0] & 1));
                    all.add((pixels[1] & 1));
                    all.add((pixels[2] & 1));
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for(int i =0;i<all.size();i++)
            a[i] = all.get(i);
        System.out.println(binaryToString(a));
        System.out.println("");
    }

    public static int binaryToUnsignedInt(int[] binary) { //binary array to unsigned int
        int toReturn = 0;
        for (int i = 0; i < binary.length; i++)
            toReturn += binary[i] * (Math.pow(2, binary.length - 1 - i));
        return toReturn;
    }

    public static String binaryToString(int[] binary) { //binary array to string
        String toReturn = "";
        int[][] chunks = getChunks(binary,8);
        for(int[] t: chunks)
            toReturn+=(char) (binaryArrayToInt(t));
        return toReturn;
    }

    public static int[][] getChunks(int[] binary, int chunksize) { //split an array of ints into multiple arrays
        if (binary.length % chunksize != 0)
            System.err.println("Warning: length of binary is not divisible by chunksize (" + binary.length + ")");
        int numChunks = binary.length / chunksize;
        int[][] toReturn = new int[numChunks][];
        for (int i = 0; i < numChunks; i++) {
            toReturn[i] = Arrays.copyOfRange(binary, chunksize * i, chunksize *(i+1));
        }
        return toReturn;
    }

    public static int arrayToInt(int[] a){ //convert an array of digits into an int
        String s = "";
        for(int i=0;i<a.length;i++)
            s += a[i];
        return Integer.parseInt(s);
    }
    public static int binaryArrayToInt(int[] a){ //convert a binary array of digits into an int (unsigned I think)
        String s = "";
        for(int i=0;i<a.length;i++)
            s += a[i];
        return Integer.parseInt(s,2);
    }
}