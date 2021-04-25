package tsosman;

import javax.swing.JFrame;

public class MainClass {

	public static void main(String[] args) {
		JFrame f = new JFrame();
		Gameplay gplay = new Gameplay();
		f.setBounds(10,10,710,610);
		f.setTitle("Brick Bracker");
		f.setResizable(false);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(gplay);
	}

}
