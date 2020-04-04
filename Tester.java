import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Tester {
	public static void main (String[] args) throws Exception{
		File f = new File("orig_img/Brothers.png");
		/*try {generate1(f);}
		catch (Error e) {System.out.println("1 - Failed");}
		try {generate2(f);}
		catch (Error e) {System.out.println("2 - Failed");}
		try {generate3(f);}
		catch (Error e) {System.out.println("3 - Failed");}*/
		generate3(f);
	}

	private static void generate1(File f) throws Exception{
		int[] bitstring = Steg.getLeastSigBits("orig_img/" + f.getName());
		BufferedImage img = ImageIO.read(f);
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage image = HiddenImage.uncover(bitstring, width, height);	
		ImageIO.write(image, "png", new File("uncovered_java/1/" + f.getName()));		
	}

	private static void generate2(File f) throws Exception{
		int[] bitstring = Steg.getSecondLeastSigBits("orig_img/" + f.getName());
		BufferedImage img = ImageIO.read(f);
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage image = HiddenImage.uncover(bitstring, width, height);	
		ImageIO.write(image, "png", new File("uncovered_java/2/" + f.getName()));		
	}

	private static void generate3(File f) throws Exception{
		int[] bitstring = Steg.getLeastSecondLeastSigBits("orig_img/" + f.getName());
		BufferedImage img = ImageIO.read(f);
		int width = img.getWidth();
		int height = img.getHeight();
		BufferedImage image = HiddenImage.uncover(bitstring, width, height);	
		ImageIO.write(image, "png", new File("uncovered_java/3/" + f.getName()));		
	}
}
