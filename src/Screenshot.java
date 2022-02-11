import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.Desktop;
import java.net.URI;
import javax.imageio.ImageIO;



public class Screenshot {

	public static void main(String[] args)  throws Exception{
		
		// screenshots & saves image. CAN SKIP THIS PART IF YOU JUST SAVE AN IMAGE FILE AS "img.png"
		// preferably keep the image size small or get specific snippet of what you want to speed up the process
		try
		{
			Robot robot = new Robot();

			Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
			File file = new File("img.png");
			boolean status = ImageIO.write(bufferedImage, "png", file);
			System.out.println("Screen capture success: " + status + "\nFile Path: " + file.getAbsolutePath());

		} catch (Exception ex) {
			System.err.println(ex);
		}
		
		// get RGB
		FileWriter writer = new FileWriter("C:\\Users\\xdann\\eclipse-workspace\\Screenshot Hexcode\\EVERY pixel value.txt");
		// read the image
		File file = new File("C:\\Users\\xdann\\eclipse-workspace\\Screenshot Hexcode\\img.png");
		BufferedImage img = ImageIO.read(file);
		int ysize = img.getHeight();
		int xsize = img.getWidth();
		
		for (int y = 0; y < ysize; y+=ysize/10) {
			for (int x = 0; x < xsize; x+=xsize/10) {
				// get most pixels
				int pixel = img.getRGB(x, y);
				// creating a Color object from pixel value
				Color color = new Color(pixel, true);
				// get the R G B values of that Color object
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();
				writer.append(red + ":");
				writer.append(green + ":");
				writer.append(blue + "");
				writer.append("\n");
				writer.flush();
			}
		}
		writer.close();
		System.out.println("RGB values at each pixel stored in \"EVERY pixel value.txt\"");
		
		// removing duplicate RHB values
		PrintWriter pw = new PrintWriter("Unique RGB List.txt");
		BufferedReader br1 = new BufferedReader(new FileReader("EVERY pixel value.txt"));
		String line1 = br1.readLine();
		while (line1 != null)
		{
			boolean flag = false;
			@SuppressWarnings("resource")
			BufferedReader br2 = new BufferedReader(new FileReader("Unique RGB List.txt"));
			String line2 = br2.readLine();
			while (line2 != null)
			{
				if (line1.equals(line2))
				{
					flag = true;
					break;
				}
				line2 = br2.readLine();
			}
			if (!flag)
			{
				pw.println(line1);
				pw.flush();
			}
			line1 = br1.readLine();
		}
		br1.close();
		pw.close();
		System.out.println("Removed duplicates and saved file as \"Unique RGB List.txt\"");
		
		// conversion from RGB to Hexcode
		PrintWriter toHex = new PrintWriter("Hexcode.txt");
		BufferedReader readSorted = new BufferedReader(new FileReader("Unique RGB List.txt"));
		String hexLine1 = readSorted.readLine();
		while (hexLine1 != null)
		{
			String[] split = hexLine1.split(":");
			String r = split[0];
			String g = split[1];
			String b = split[2];
			String hex = String.format("#%02X%02X%02X", Integer.parseInt(r), Integer.parseInt(g), Integer.parseInt(b));
			toHex.println(hex);
			toHex.flush();
			hexLine1 = readSorted.readLine();
		}
		readSorted.close();
		toHex.close();
		System.out.println("Converted RGB into Hexcode in \"Hexcode.txt\"");
		
		// open web browser with all the Hexcodes
		BufferedReader readHex = new BufferedReader(new FileReader("Hexcode.txt"));
		String hexC = readHex.readLine();
		while (hexC != null)
		{
			String[] split = hexC.split("#");
			String hexText = split[1];
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
			{
			    Desktop.getDesktop().browse(new URI("https://www.color-hex.com/color/" + hexText));
			}
			hexC = readHex.readLine();
		}
		readHex.close();
		System.out.println("Opened a browser with all of the hexcodes");
	}
}
