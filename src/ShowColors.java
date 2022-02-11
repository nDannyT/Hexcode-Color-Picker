import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ShowColors extends JFrame {
	 
    public ShowColors() {
        super("Rectangles Drawing Demo");
 
        getContentPane().setBackground(Color.WHITE);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
 
    void drawRectangles(Graphics square) {
        super.paint(square);
        Graphics2D g2d = (Graphics2D) square;
        g2d.setColor(new Color(50,100,75));
        g2d.fillRect(100,100, 10, 10);
    }
    
    public void paint(Graphics square) {
        super.paint(square);
        drawRectangles(square);
    }
    public static void main(String[] args) throws Exception {
 
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ShowColors().setVisible(true);
            }
        });
        

		BufferedReader readSorted = new BufferedReader(new FileReader("Unique RGB List.txt"));
		BufferedReader readHex = new BufferedReader(new FileReader("Hexcode.txt"));
		
		String hex = readHex.readLine();
		String hexLine1 = readSorted.readLine();
		int x = 10;
		int y = 10;
        
		while (hexLine1 != null)
		{
			y-=20;
			String[] split = hexLine1.split(":");
			int r = Integer.parseInt(split[0]);
			int g = Integer.parseInt(split[1]);
			int b = Integer.parseInt(split[2]);
			hex = readHex.readLine();
			hexLine1 = readSorted.readLine();
		}
		readSorted.close();
    }
}