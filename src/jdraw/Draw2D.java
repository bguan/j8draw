package jdraw;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * A simplistic starter program to learn 2D graphics
 */
public class Draw2D extends JPanel {
	
	private static final long serialVersionUID = 1L;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.BLACK);
		g.drawOval(200, 150, 200, 100);
		g.setColor(Color.RED);
		g.fillOval(200, 150, 200, 100);
	}
	
	public static void main(String[] arguments) throws IOException {
		JFrame frame = new JFrame("Java Draw");
		JFrame.setDefaultLookAndFeelDecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Draw2D jdraw = new Draw2D();
		jdraw.setPreferredSize(new Dimension(600,400));

		frame.add(jdraw);
		frame.pack();
		frame.setLocationRelativeTo(null); // this centers the frame
		frame.setVisible(true);
	}
}