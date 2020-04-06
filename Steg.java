
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Steg {
    public static void main(String[] args) throws Exception {

        //System.out.println(binaryToString(getLeastSigBits("hide_text.png")));
        ArrayList<Integer> a = new ArrayList();
        a.add(0);
        a.add(1);
        a.add(2);
        printArray(findHeader(getFirstAndSecondLeastSigBits("Images/WinkyFace.png",a)));
        //doAll("Images/small_images");
        //doAll("Images/big_images");
    //checkEverything("Images/found_images/","NewWinkyFace.png");
    }

    public static void printArray(int[] a){
        for(int i=0;i<1000;i++)
            System.out.println(a[i]);
    }

    public static int[] findHeader(int[] a){ 
        int[][] ints = getChunks(a,32);
        int[] s= new int[ints.length];
        for(int i=0;i<ints.length;i++)
            s[i]=binaryArrayToInt(ints[i]);
        return s;
    }

    public static void checkEverything(String dir, String filename) throws Exception{
        File file = new File(filename);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        } else System.out.println("Directory already exists");

        File f = new File(dir + filename);
        ArrayList<Integer> c = new ArrayList<>();
        c.add(0);
        write(binaryToString(getLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1r1000.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2r1000.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12r1000.txt");
        c.remove(0);
        c.add(1);
        write(binaryToString(getLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1g1000.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2g1000.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12g1000.txt");
        c.remove(0);
        c.add(2);
        write(binaryToString(getLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1b1000.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2b1000.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12b1000.txt");
        c.remove(0);
        c.add(0);
        c.add(1);
        write(binaryToString(getLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1rg1000.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2rg1000.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12rg1000.txt");
        c.remove(0);
        c.remove(0);
        c.add(0);
        c.add(2);
        write(binaryToString(getLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1rb1000.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2rb1000.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12rb1000.txt");
        c.remove(0);
        c.remove(0);
        c.add(1);
        c.add(2);
        write(binaryToString(getLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1gb1000.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2gb1000.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12gb1000.txt");
        c.add(0);
        write(binaryToString(getLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1rgb1000.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2rgb1000.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir +filename,c)), filename +  "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12rgb1000.txt");


    }

    public static void doAll(String d) throws Exception { //change the contents of the first arg to write to change what you want to do
        File dir = new File(d);
        ArrayList<Integer> c = new ArrayList<>();

        c.add(0);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsigred/" + (f.getName()).substring(0, f.getName().length() - 3) + "red1000.txt");
        c.remove(0);
        c.add(1);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsiggreen/" + (f.getName()).substring(0, f.getName().length() - 3) + "green1000.txt");
        c.remove(0);
        c.add(2);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsigblue/" + (f.getName()).substring(0, f.getName().length() - 3) + "blue1000.txt");
        c.remove(0);
        c.add(0);
        c.add(2);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsigredblue/" + (f.getName()).substring(0, f.getName().length() - 3) + "redblue1000.txt");
        c.remove(0);
        c.remove(0);
        c.add(1);
        c.add(0);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsigredgreen/" + (f.getName()).substring(0, f.getName().length() - 3) + "redgreen1000.txt");
        c.remove(0);
        c.remove(0);
        c.add(2);
        c.add(1);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsigbluegreen/" + (f.getName()).substring(0, f.getName().length() - 3) + "bluegreen1000.txt");
    }

    public static void write(String s, String destination) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
        writer.write(s);
        writer.close();
    }

    public static int[] getLeastSigBitsFast(String filename) throws Exception { //gets least significant bits
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
                if (count<1000) {
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
    public static int[] getFirstAndSecondLeastSigBits(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        boolean red = false;
        boolean green = false;
        boolean blue = false;
        if (p.contains(0))
            red = true;
        if (p.contains(1))
            green = true;
        if (p.contains(2))
            blue = true;
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
                    if (red){ all.add(pixels[0] & 1);
                    all.add((pixels[0] & 2) >> 1);}
                    if (green) {
                        all.add(pixels[1] & 1);
                        all.add((pixels[1] & 2) >> 1);
                    }
                    if (blue) {all.add(pixels[2] & 1);
                        all.add((pixels[2] & 2)>>1);}
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }
    public static int[] getFirstAndSecondLeastSigBitsFast(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        boolean red = false;
        boolean green = false;
        boolean blue = false;
        if (p.contains(0))
            red = true;
        if (p.contains(1))
            green = true;
        if (p.contains(2))
            blue = true;
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
                if (count<1000  ) {
                    if (red){ all.add(pixels[0] & 1);
                        all.add((pixels[0] & 2) >> 1);}
                    if (green) {
                        all.add(pixels[1] & 1);
                        all.add((pixels[1] & 2) >> 1);
                    }
                    if (blue) {all.add(pixels[2] & 1);
                        all.add((pixels[2] & 2)>>1);}
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }
    public static int[] getLeastSigBits(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        boolean red = false;
        boolean green = false;
        boolean blue = false;
        if (p.contains(0))
            red = true;
        if (p.contains(1))
            green = true;
        if (p.contains(2))
            blue = true;
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
                    if (red) all.add(pixels[0] & 1);
                    if (green) all.add(pixels[1] & 1);
                    if (blue) all.add(pixels[2] & 1);
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }
    public static int[] getLeastSigBitsFast(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        boolean red = false;
        boolean green = false;
        boolean blue = false;
        if (p.contains(0))
            red = true;
        if (p.contains(1))
            green = true;
        if (p.contains(2))
            blue = true;
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
                if (count<1000) {
                    if (red) all.add(pixels[0] & 1);
                    if (green) all.add(pixels[1] & 1);
                    if (blue) all.add(pixels[2] & 1);
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }
    public static int[] getSecondLeastSigBitsFast(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        boolean red = false;
        boolean green = false;
        boolean blue = false;
        if (p.contains(0))
            red = true;
        if (p.contains(1))
            green = true;
        if (p.contains(2))
            blue = true;
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
                if (count<1000) {
                    if (red) all.add((pixels[0] & 2) >> 1);
                    if (green) all.add((pixels[1] & 2) >> 1);
                    if (blue) all.add((pixels[2] & 2) >> 1);
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }
    public static int[] getSecondLeastSigBits(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        boolean red = false;
        boolean green = false;
        boolean blue = false;
        if (p.contains(0))
            red = true;
        if (p.contains(1))
            green = true;
        if (p.contains(2))
            blue = true;
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
                    if (red) all.add((pixels[0] & 2) >> 1);
                    if (green) all.add((pixels[1] & 2) >> 1);
                    if (blue) all.add((pixels[2] & 2) >> 1);
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
        return Integer.parseUnsignedInt(s, 2);
    }

    public static int[] listToArray(ArrayList<Integer> a) {
        int[] q = new int[a.size()];
        for (int i = 0; i < a.size(); i++)
            q[i] = a.get(i);
        return q;
    }
}

