
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Steg {
    public static void main(String[] args) throws Exception {

        //System.out.println(binaryToString(getLeastSigBits("hide_text.png")));
       // write(binaryToString(getLeastSigBits("Images/DogDog.png")), "leastsig/DogDog.txt");

        doAll("Images/small_images");
     }

    public static void doAll(String d) throws Exception{ //change the contents of the first arg to write to change what you want to do
        File dir = new File(d);
        for(File f: dir.listFiles())
           // System.out.println(f.getName());
            write(binaryToString(getLeastSigBits(d+ "/" +f.getName())), "leastsig/"+(f.getName()).substring(0,f.getName().length()-3)+".txt");
    }

    public static void write(String s, String destination)throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
        writer.write(s);
        writer.close();
    }





    public static int[] getLeastSigBits(String filename) throws Exception { //gets least significant bits
        BufferedImage image = ImageIO.read(new File(filename));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height of " + filename + ": " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        int count = 0;
        ArrayList<Integer> all = new ArrayList<>();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                if (true) {
                    all.add(pixels[0] & 1);
                    all.add(pixels[1] & 1);
                    all.add(pixels[2] & 1);
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }
    public static int[] getSecondLeastSigBits(String filename) throws Exception { //gets least significant bits
        BufferedImage image = ImageIO.read(new File(filename));
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
                    all.add((pixels[0] & 2)>>1);
                    all.add((pixels[1] & 2)>>1);
                    all.add((pixels[2] & 2)>>1);
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int binaryToUnsignedInt(int[] binary) { //binary array to unsigned int
        int toReturn = 0;
        for (int i = 0; i < binary.length; i++)
            toReturn += binary[i] * (Math.pow(2, binary.length - 1 - i));
        return toReturn;
    }

    public static String binaryToString(int[] binary) { //binary array to string
        String toReturn = "";
        int[][] chunks = getChunks(binary, 8);
        for (int[] t : chunks)
            toReturn += (char) (binaryArrayToInt(t));
        return toReturn;
    }

    public static int[][] getChunks(int[] binary, int chunksize) { //split an array of ints into multiple arrays
        if (binary.length % chunksize != 0)
            System.err.println("Warning: length of binary is not divisible by chunksize (" + binary.length + ")");
        int numChunks = binary.length / chunksize;
        int[][] toReturn = new int[numChunks][];
        for (int i = 0; i < numChunks; i++) {
            toReturn[i] = Arrays.copyOfRange(binary, chunksize * i, chunksize * (i + 1));
        }
        return toReturn;
    }

    public static int arrayToInt(int[] a) { //convert an array of digits into an int
        String s = "";
        for (int i = 0; i < a.length; i++)
            s += a[i];
        return Integer.parseInt(s);
    }

    public static int binaryArrayToInt(int[] a) { //convert a binary array of digits into an int (unsigned I think)
        String s = "";
        for (int i = 0; i < a.length; i++)
            s += a[i];
        return Integer.parseInt(s, 2);
    }
public static int[] listToArray(ArrayList<Integer> a){
        int[] q = new int[a.size()];
        for(int i=0;i<a.size();i++)
            q[i]=a.get(i);
        return q;
    }
}

