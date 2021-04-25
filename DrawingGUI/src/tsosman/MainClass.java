package tsosman;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainClass {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.getContentPane().add(new GraphicsClass(), BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 500);
		f.setVisible(true);
	}
}
