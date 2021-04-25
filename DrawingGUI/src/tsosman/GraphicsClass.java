package tsosman;

import java.awt.BorderLayout; // Layout a container.
import java.awt.Graphics; // Abstract base class, allows to draw components.
import java.awt.Point; // x/y coordinates.
import java.awt.event.MouseAdapter; // Receives an event when a mouse is used. 
									// Needed class to create a MouseEvent.
import java.awt.event.MouseEvent; //  an event when a mouse is used 
import java.awt.event.MouseMotionAdapter; // Used in order for the event to be created over our layout.
import javax.swing.JFrame; // Adds suport for the swing components.
import javax.swing.JPanel; // Generic container.

@SuppressWarnings("serial") // to suppress the warning from extending to JPanel.
public class GraphicsClass extends JPanel{
	
	private Point lastPoint;
	
	public GraphicsClass() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				lastPoint = new Point(e.getX(),e.getY());
			}		
			});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent e) {
				Graphics graphics = getGraphics();
				graphics.drawLine(lastPoint.x, lastPoint.y, e.getX(), e.getY());
				graphics.dispose();
			}		
		});
	}
}
