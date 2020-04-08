import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Steg {
    public static void main(String[] args) throws Exception {
        ArrayList a = new ArrayList<Integer>();
        a.add(2);
        a.add(0);
        a.add(1);
        //(findHeader(getLeastSigBits("Images/WideDogIsWide.png", a)));

        getAllText("Images/found_images");
        getAllText("Images_pure");
        //File dir = new File("testtt");
        // System.out.println(dir.getPath());
         //magnifyEach("Images_pure");
       // magnifyEach("Images/found_images");
    }

    public static void magnifyEach(String d) throws Exception {
        File dir = new File(d);
        ArrayList<Integer> c = new ArrayList<>();
        BufferedImage img;
        for (File f : dir.listFiles()) {
            // System.out.println(f.getName());
            img = (ImageIO.read(f));
            magnifyEverything(f.getName(), img);
        }
    }

    public static void magnifyEverything(String name, BufferedImage img) throws Exception {
        // channel: 0 = red, 1 = green, 2 = blue, 3 = g&b, 4 = r&b, 5 = r&g, 6 = all; else = none (if you just want to magnify alpha)
        File file = new File("outputs/images/" + name.substring(0, name.length() - 4));
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println(name + "directory is created!");
            } else {
                System.out.println(name + ": Failed to create directory! Uh oh");
            }
        } else System.out.println(name + " directory already exists");
        BufferedImage a = deepCopy(img);
        BufferedImage b = deepCopy(img);
        BufferedImage c = deepCopy(img);
        BufferedImage d = deepCopy(img);
        BufferedImage e = deepCopy(img);
        ImageIO.write(MagnifyBits.magnifyBitsInImage(a, 0, 1, false), "png", new File(file.getPath() + "/1r.png"));
        ImageIO.write(MagnifyBits.magnifyBitsInImage(b, 1, 1, false), "png", new File(file.getPath() + "/1g.png"));
        ImageIO.write(MagnifyBits.magnifyBitsInImage(c, 2, 1, false), "png", new File(file.getPath() + "/1b.png"));
        ImageIO.write(MagnifyBits.magnifyBitsInImage(d, 0, 2, false), "png", new File(file.getPath() + "/2r.png"));
        ImageIO.write(MagnifyBits.magnifyBitsInImage(e, 1, 2, false), "png", new File(file.getPath() + "/2g.png"));
        ImageIO.write(MagnifyBits.magnifyBitsInImage(img, 2, 2, false), "png", new File(file.getPath() + "/2b.png"));

    }

    static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
    }

    public static void getAllText(String d) throws Exception {
        File dir = new File(d);
        ArrayList<Integer> c = new ArrayList<>();
        BufferedImage img;
        for (File f : dir.listFiles()) {
            // System.out.println(f.getName());
            // img = (ImageIO.read(f));
            //if (img.getColorModel().getPixelSize() == 24)
            checkEverythingSingleFile(d + "/", f.getName());
            checkEverythingSingleFileTBLR(d + "/", f.getName());
            //else if (img.getColorModel().getPixelSize() == 32)
            //  checkEverythingSingleFilewithAlpha(d + "/", f.getName());
            //  else
            //    System.err.println("Weird number of bits in a pixel in " + f.getName() + ": " + img.getColorModel().getPixelSize() + "! Didn't do a read!");
        }
    }

    public static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] < 15000 && a[i] > 0 && a[i + 1] < 15000 && a[i + 1] > 0) {
                System.out.println(a[i]);
                System.out.println(a[i + 1]);
            }
    }


    public static int[] findHeader(int[] a) {

        int[][] ints = getChunks(a, 32);
        int[] s = new int[ints.length];
        for (int i = 0; i < ints.length; i++)
            s[i] = binaryArrayToInt(ints[i]);
        return s;
    }

    public static void checkEverythingSingleFile(String dir, String filename) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("outputsfiltered/LRTB/" + filename.substring(0, filename.length() - 4) + ".txt"));
        String f = dir + filename;
        ArrayList<Integer> c = new ArrayList<>();
        c.add(0);
        writeMe(writer, c, f);
        c.clear();
        c.add(1);
        writeMe(writer, c, f);
        c.clear();
        c.add(2);
        writeMe(writer, c, f);
        c.clear();
        c.add(0);
        c.add(1);
        writeMe(writer, c, f);
        c.clear();
        c.add(1);
        c.add(0);
        writeMe(writer, c, f);
        c.clear();
        c.add(0);
        c.add(2);
        writeMe(writer, c, f);
        c.clear();
        c.add(2);
        c.add(0);
        writeMe(writer, c, f);
        c.clear();
        c.add(1);
        c.add(2);
        writeMe(writer, c, f);
        c.clear();
        c.add(2);
        c.add(1);
        writeMe(writer, c, f);
        c.clear();
        c.add(0);
        c.add(1);
        c.add(2);
        writeMe(writer, c, f);
        c.clear();
        c.add(0);
        c.add(2);
        c.add(1);
        writeMe(writer, c, f);
        c.clear();
        c.add(1);
        c.add(2);
        c.add(0);
        writeMe(writer, c, f);
        c.clear();
        c.add(1);
        c.add(0);
        c.add(2);
        writeMe(writer, c, f);
        c.clear();
        c.add(2);
        c.add(1);
        c.add(0);
        writeMe(writer, c, f);
        c.clear();
        c.add(2);
        c.add(0);
        c.add(1);
        writeMe(writer, c, f);
        writer.close();
    }

    public static void checkEverythingSingleFileTBLR(String dir, String filename) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("outputsfiltered/TBLR/" + filename.substring(0, filename.length() - 4) + ".txt"));
        String f = dir + filename;
        ArrayList<Integer> c = new ArrayList<>();
        c.add(0);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(1);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(2);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(0);
        c.add(1);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(1);
        c.add(0);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(0);
        c.add(2);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(2);
        c.add(0);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(1);
        c.add(2);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(2);
        c.add(1);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(0);
        c.add(1);
        c.add(2);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(0);
        c.add(2);
        c.add(1);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(1);
        c.add(2);
        c.add(0);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(1);
        c.add(0);
        c.add(2);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(2);
        c.add(1);
        c.add(0);
        writeMeTBLR(writer, c, f);
        c.clear();
        c.add(2);
        c.add(0);
        c.add(1);
        writeMeTBLR(writer, c, f);
        writer.close();
    }

    public static void writeMe(BufferedWriter writer, ArrayList<Integer> c, String filename) throws Exception {
        String channels = "";
        char[] colors = new char[]{'r', 'g', 'b', 'a'};
        for (int i = 0; i < c.size(); i++)
            channels += colors[c.get(i)];
        writer.write("\n1" + channels + "1500\n");
        writer.append(binaryToString(getLeastSigBitsFast(filename, c)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n2" + channels + "1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(filename, c)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n12" + channels + "1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(filename, c)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n12altt" + channels + "1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFastAlt(filename, c, true)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n12altf" + channels + "1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFastAlt(filename, c, false)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n123" + channels + "1500\n");
        writer.write(binaryToString(getFirstSecondAndThirdLeastSigBitsFast(filename, c)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n3" + channels + "1500\n");
        writer.write(binaryToString(getThirdLeastSigBitsFast(filename, c)).replaceAll("[^a-zA-Z0-9 \n]", ""));
    }

    public static void writeMeTBLR(BufferedWriter writer, ArrayList<Integer> c, String filename) throws Exception {
        String channels = "";
        char[] colors = new char[]{'r', 'g', 'b', 'a'};
        for (int i = 0; i < c.size(); i++)
            channels += colors[c.get(i)];
        writer.write("\n1" + channels + "1500\n");
        writer.append(binaryToString(getLeastSigBitsFastTBLR(filename, c)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n2" + channels + "1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFastTBLR(filename, c)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n12" + channels + "1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFastTBLR(filename, c)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n12altt" + channels + "1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFastTBLRAlt(filename, c, true)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n12altf" + channels + "1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFastTBLRAlt(filename, c, false)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n123" + channels + "1500\n");
        writer.write(binaryToString(getFirstSecondAndThirdLeastSigBitsFastTBLR(filename, c)).replaceAll("[^a-zA-Z0-9 \n]", ""));
        writer.append("\n3" + channels + "1500\n");
        writer.write(binaryToString(getThirdLeastSigBitsFast(filename, c)).replaceAll("[^a-zA-Z0-9 \n]", ""));
    }

    public static void checkEverythingSingleFilewithAlpha(String dir, String filename) throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter("outputs/" + filename.substring(0, filename.length() - 4) + "(hasalpha).txt"));

        File f = new File(dir + filename);

        ArrayList<Integer> c = new ArrayList<>();
        c.add(0);
        writer.write("1r1500\n");
        writer.append(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2r1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12r1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.add(1);
        writer.append("\n1g1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2g1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12g1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.add(2);
        writer.append("\n1b1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2b1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12b1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.add(0);
        c.add(1);
        writer.append("\n1rg1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2rg1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12rg1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.remove(0);
        c.add(1);
        c.add(2);
        writer.append("\n1gb1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2gb1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12gb1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.remove(0);
        c.add(0);
        c.add(2);
        writer.append("\n1rb1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2rb1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12rb1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.add(1);
        writer.append("\n1rgb1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2rgb1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12rgb1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.remove(0);
        c.remove(0);
        c.add(3);
        writer.append("\n1a1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2a1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12a1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.add(0);
        c.add(3);
        writer.append("\n1ra1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2ra1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12ra1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.remove(0);
        c.add(1);
        c.add(3);
        writer.append("\n1ga1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2ga1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12ga1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.remove(0);
        c.add(2);
        c.add(3);
        writer.append("\n1ba1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2ba1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12ba1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.remove(0);
        c.add(0);
        c.add(1);
        c.add(3);
        writer.append("\n1rga1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2rga1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12rga1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.remove(0);
        c.remove(0);
        c.add(0);
        c.add(2);
        c.add(3);
        writer.append("\n1rba1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2rba1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12rba1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.remove(0);
        c.remove(0);
        c.remove(0);
        c.add(2);
        c.add(1);
        c.add(3);
        writer.append("\n1gba1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2gba1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12gba1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        c.add(0);
        writer.append("\n1rgba1500\n");
        writer.write(binaryToString(getLeastSigBitsFast(dir + filename, c)));
        writer.append("\n2rgba1500\n");
        writer.write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)));
        writer.append("\n12rgba1500\n");
        writer.write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)));
        writer.close();

    }

    public static void checkEverything(String dir, String filename) throws Exception {
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
        write(binaryToString(getLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1r1500.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2r1500.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12r1500.txt");
        c.remove(0);
        c.add(1);
        write(binaryToString(getLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1g1500.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2g1500.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12g1500.txt");
        c.remove(0);
        c.add(2);
        write(binaryToString(getLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1b1500.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2b1500.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12b1500.txt");
        c.remove(0);
        c.add(0);
        c.add(1);
        write(binaryToString(getLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1rg1500.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2rg1500.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12rg1500.txt");
        c.remove(0);
        c.remove(0);
        c.add(0);
        c.add(2);
        write(binaryToString(getLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1rb1500.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2rb1500.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12rb1500.txt");
        c.remove(0);
        c.remove(0);
        c.add(1);
        c.add(2);
        write(binaryToString(getLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1gb1500.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2gb1500.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12gb1500.txt");
        c.add(0);
        write(binaryToString(getLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "1rgb1500.txt");
        write(binaryToString(getSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "2rgb1500.txt");
        write(binaryToString(getFirstAndSecondLeastSigBitsFast(dir + filename, c)), filename + "/" + (f.getName()).substring(0, f.getName().length() - 3) + "12rgb1500.txt");


    }

    public static void doAll(String d) throws Exception { //change the contents of the first arg to write to change what you want to do
        File dir = new File(d);
        ArrayList<Integer> c = new ArrayList<>();

        c.add(0);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsigred/" + (f.getName()).substring(0, f.getName().length() - 3) + "red1500.txt");
        c.remove(0);
        c.add(1);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsiggreen/" + (f.getName()).substring(0, f.getName().length() - 3) + "green1500.txt");
        c.remove(0);
        c.add(2);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsigblue/" + (f.getName()).substring(0, f.getName().length() - 3) + "blue1500.txt");
        c.remove(0);
        c.add(0);
        c.add(2);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsigredblue/" + (f.getName()).substring(0, f.getName().length() - 3) + "redblue1500.txt");
        c.remove(0);
        c.remove(0);
        c.add(1);
        c.add(0);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsigredgreen/" + (f.getName()).substring(0, f.getName().length() - 3) + "redgreen1500.txt");
        c.remove(0);
        c.remove(0);
        c.add(2);
        c.add(1);
        System.out.println("c size:" + c.size() + " c element:" + c.get(0));
        for (File f : dir.listFiles())
            // System.out.println(f.getName());
            write(binaryToString(getFirstAndSecondLeastSigBits(d + "/" + f.getName(), c)), "12leastsigbluegreen/" + (f.getName()).substring(0, f.getName().length() - 3) + "bluegreen1500.txt");
    }

    public static void write(String s, String destination) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
        writer.write(s);
        writer.close();
    }

    private static int[] getbits(BufferedImage image) throws Exception {
        // takes in an image passed through MagnifyBits.main (eg. altered_java.png) and generates the hidden image
        // must be called from HiddenImage.main

        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        int[][][] pixelMap = new int[height][width][3];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int[] pixel = raster.getPixel(x, y, (int[]) null);
                pixelMap[y][x][0] = pixel[0];
                pixelMap[y][x][1] = pixel[1];
                pixelMap[y][x][2] = pixel[2];
            }
        }
        ArrayList<Integer> pixelString = HiddenImage.getPixelString(pixelMap);
        int[] a = listToArray(pixelString);
        return a;
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
                if (count < 1500) {
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
                    for (int i = 0; i < p.size(); i++) {
                        all.add((pixels[p.get(i)] & 2) >> 1);
                        all.add(pixels[p.get(i)] & 1);
                    }
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getFirstAndSecondLeastSigBits(String filename, ArrayList<Integer> p, boolean k) throws Exception { //boolean k is there if u wanna skip 1000
        boolean red = false;
        boolean green = false;
        boolean blue = false;
        boolean alpha = false;
        if (p.contains(0))
            red = true;
        if (p.contains(1))
            green = true;
        if (p.contains(2))
            blue = true;
        if (p.contains(3))
            alpha = true;
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
                if (count > 999)
                    if (true) {
                        if (red) {
                            all.add((pixels[0] & 2) >> 1);
                            all.add(pixels[0] & 1);
                        }
                        if (green) {
                            all.add((pixels[1] & 2) >> 1);
                            all.add(pixels[1] & 1);
                        }
                        if (blue) {
                            all.add((pixels[2] & 2) >> 1);
                            all.add(pixels[2] & 1);
                        }
                        if (alpha) {
                            all.add((pixels[3] & 2) >> 1);
                            all.add(pixels[3] & 1);
                        }
                    }

                count++;

            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getFirstAndSecondLeastSigBitsFast(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
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
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        all.add((pixels[p.get(i)] & 2) >> 1);
                        all.add(pixels[p.get(i)] & 1);
                    }
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getFirstSecondAndThirdLeastSigBitsFastTBLR(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        BufferedImage image = ImageIO.read(new File(filename));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height of " + filename + ": " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        int count = 0;
        ArrayList<Integer> all = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        all.add((pixels[p.get(i)] & 4) >> 2);
                        all.add((pixels[p.get(i)] & 2) >> 1);
                        all.add(pixels[p.get(i)] & 1);
                    }
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getFirstSecondAndThirdLeastSigBitsFast(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
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
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        all.add((pixels[p.get(i)] & 4) >> 2);
                        all.add((pixels[p.get(i)] & 2) >> 1);
                        all.add(pixels[p.get(i)] & 1);
                    }
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getThirdLeastSigBitsFast(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
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
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        all.add((pixels[p.get(i)] & 4) >> 2);
                    }
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getThirdLeastSigBitsFastTBLR(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        BufferedImage image = ImageIO.read(new File(filename));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height of " + filename + ": " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        int count = 0;
        ArrayList<Integer> all = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        all.add((pixels[p.get(i)] & 4) >> 2);
                    }
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getFirstAndSecondLeastSigBitsFastAlt(String filename, ArrayList<Integer> p, boolean first) throws Exception { //gets least significant bits
        BufferedImage image = ImageIO.read(new File(filename));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height of " + filename + ": " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        int count = 0;
        boolean t = !first;
        ArrayList<Integer> all = new ArrayList<>();
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        if (t) {
                            all.add((pixels[p.get(i)] & 2) >> 1);
                            t = false;
                        } else {
                            all.add(pixels[p.get(i)] & 1);
                            t = true;
                        }
                    }
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getFirstAndSecondLeastSigBitsFastTBLRAlt(String filename, ArrayList<Integer> p, boolean first) throws Exception { //gets least significant bits
        BufferedImage image = ImageIO.read(new File(filename));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height of " + filename + ": " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        int count = 0;
        ArrayList<Integer> all = new ArrayList<>();
        boolean t = !first;
        for (int r = 0; r < height; r++) {
            for (int c = 0; c < width; c++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        if (t) {
                            all.add((pixels[p.get(i)] & 2) >> 1);
                            t = false;
                        } else {
                            all.add(pixels[p.get(i)] & 1);
                            t = true;
                        }
                        count++;
                    }
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getFirstAndSecondLeastSigBitsFastTBLR(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        BufferedImage image = ImageIO.read(new File(filename));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height of " + filename + ": " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        int count = 0;
        ArrayList<Integer> all = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        all.add((pixels[p.get(i)] & 2) >> 1);
                        all.add(pixels[p.get(i)] & 1);
                    }
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
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        all.add(pixels[p.get(i)] & 1);
                    }
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getLeastSigBitsFastTBLR(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        BufferedImage image = ImageIO.read(new File(filename));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height of " + filename + ": " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        int count = 0;
        ArrayList<Integer> all = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        all.add(pixels[p.get(i)] & 1);
                    }
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
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        all.add((pixels[p.get(i)] & 2) >> 1);
                    }
                    count++;
                }
            }
        }
        int[] a = new int[all.size()];
        for (int i = 0; i < all.size(); i++)
            a[i] = all.get(i);
        return a;
    }

    public static int[] getSecondLeastSigBitsFastTBLR(String filename, ArrayList<Integer> p) throws Exception { //gets least significant bits
        BufferedImage image = ImageIO.read(new File(filename));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height of " + filename + ": " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        int count = 0;
        ArrayList<Integer> all = new ArrayList<>();
        for (int c = 0; c < width; c++) {
            for (int r = 0; r < height; r++) {
                int[] pixels = raster.getPixel(c, r, (int[]) null);
                if (count < 1500) {
                    for (int i = 0; i < p.size(); i++) {
                        all.add((pixels[p.get(i)] & 2) >> 1);
                    }
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

   /* public static int binaryArrayToInt(int[] a) { //convert a binary array of digits into an int (unsigned I think)
        String s = "";
        for (int i = 0; i < a.length; i++)
            s += a[i];
        return Integer.parseUnsignedInt(s, 2);
    } */

    public static int binaryArrayToInt(int[] bits) { //does not work with twos-complement
        int val = 0;
        for (int i = 0; i < bits.length; i++) val += (bits[bits.length - 1 - i] * Math.pow(2, i));
        return val;
    }

    public static int[] listToArray(ArrayList<Integer> a) {
        int[] q = new int[a.size()];
        for (int i = 0; i < a.size(); i++)
            q[i] = a.get(i);
        return q;
    }
}