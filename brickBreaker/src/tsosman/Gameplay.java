package tsosman;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial") // to suppress the warning from extending to JPanel.
public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private boolean play = false;
	private int score = 0;
	
	private int totalBricks = 21;
	
	private Timer timer;
	private int delay = 8;
	
	private int playerX = 310;
	
	private int ballPosX = 120;
	private int ballPosY = 350;
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator mapGen;
	
	public Gameplay() {
		mapGen = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	
	//Colors Method
	public void paint(Graphics g) {
		//Background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		//Map
		mapGen.draw((Graphics2D)g);
		//Borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);
		//Score
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 25));
		g.drawString(""+score, 340, 30);
		//Paddle
		g.setColor(Color.green);
		g.fillRect(playerX, 520, 100, 8);
		//Ball
		g.setColor(Color.green);
		g.fillOval(ballPosX, ballPosY, 20, 20);
		//Game Won
		if(totalBricks <= 0) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.green);
			g.setFont(new Font("arial", Font.BOLD, 35));
			g.drawString("You Won!", 190, 300);
			
			g.setFont(new Font("arial", Font.BOLD, 20));
			g.drawString("Press Enter to restart", 230, 350);
		}
		//Game Over
		if(ballPosY >570) {
			play = false;
			ballXdir = 0;
			ballYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("arial", Font.BOLD, 35));
			g.drawString("Game Over", 190, 300);
			
			g.setFont(new Font("arial", Font.BOLD, 20));
			g.drawString("Press Enter to restart", 230, 350);
		}
		
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play) {
			//if statement for the ball to bounce of of the rectangle
			if(new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,520,100,8))) {
				ballYdir = -ballYdir;
			}
			for (int i = 0; i < mapGen.map.length; i++) {
				for (int j = 0; j < mapGen.map[0].length; j++) {
					if(mapGen.map[i][j] > 0) {
						int brickX = j * mapGen.brickWidth + 80;
						int brickY = i * mapGen.brickHeight + 50;
						int brickWidth = mapGen.brickWidth;
						int brickHeight = mapGen.brickHeight;
						
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballPosX, ballPosY, 20, 20);
						Rectangle brickRect = rect;
						
						if(ballRect.intersects(brickRect)) {
							mapGen.setBrickValue(0,i,j);
							totalBricks--;
							score += 5;
									
							if(ballPosX + 19 <= brickRect.x || ballPosX +1 >= brickRect.x + brickRect.width) {
								ballXdir = -ballXdir;
							}else {
								ballYdir = -ballYdir;
							}
						}
					}
				}
			}
			ballPosX += ballXdir;
			ballPosY += ballYdir;
			if(ballPosX < 0) {
				ballXdir = -ballXdir;
			}
			if(ballPosY < 0) {
				ballYdir = -ballYdir;
			}
			if(ballPosX > 670) {
				ballXdir = -ballXdir;
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

	@Override
	//method for the keys to work
	public void keyPressed(KeyEvent e) {
		//RIGHT ARROW
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(playerX >= 592) {
				playerX = 592;
			}else {
				moveRight();
			}
		}
		//LEFT ARROW
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			}else {
				moveLeft();
			}
		}
		//ENTER
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballPosX = 120;
				ballPosY = 350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score = 0;
				totalBricks = 21;
				mapGen = new MapGenerator(3, 7);
				repaint();
				
			}
		}
	}
	
	public void moveRight() {
		play = true;
		playerX += 20;
	}
	public void moveLeft() {
		play = true;
		playerX -= 20;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	
}
