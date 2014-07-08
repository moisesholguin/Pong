import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
/**
 * This is my first Java game!!!
 * 
 * Pong User Manual:
 * 
 *      Start = Enter
 *      Pause = P
 *      Resume = SPACE
 *      Player 1 = W and S (Respectively for Up and Down)
 *      Player 2 = UP and DOWN (Arrow Keys)
 * 
 * The methods suspend() and resume() have been deprecated but
 * they will suffice for this simple game. If you want to play
 * again please exit window and run code again.
 * 
 * To change speeds of the ball and/or paddles alter the
 * Thread.sleep(number) in Ball.java and/or Paddle.java respectively.
 * 
 * To change the score needed to win alter the run() method's if statements
 * in Ball.java to the desired score limit.
 * 
 * Thank you and enjoy!
 * 
 * @Moises Holguin
 * @Monday, July 24, 2012
 */
public class Main extends JFrame {
	static Thread ball;
	static Thread p1;
	static Thread p2;
	static boolean WINNER = false;
	static boolean PAUSED = false;
	static boolean GAMESTARTED = false;

	static Ball b = new Ball(280, 250);
	
	Image dbImage;
	Graphics dbg;
	
	public Main() {
		setTitle("Pong Created By Moises Holguin");
		setSize(600,500);
		setBackground(Color.BLACK);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(new AL());
	}
	
	public static void main(String[] args) {
		new Main();
		ball = new Thread(b);
		p1 = new Thread(b.p1);
		p2 = new Thread(b.p2);
	}
	
	public void paint(Graphics g) {
		dbImage = createImage(getWidth(),getHeight());
		dbg = dbImage.getGraphics();
		draw(dbg);
		g.drawImage(dbImage, 0, 0, this);
	}
	
	public void draw(Graphics g) {
		b.draw(g);
		b.p1.draw(g);
		b.p2.draw(g);
		
		g.setColor(Color.WHITE);
		g.drawString(""+b.p1Score, 20, 50);
		g.drawString(""+b.p2Score, 570, 50);
		
		repaint();
		
		if(PAUSED) {
			g.setColor(Color.YELLOW);
			g.drawString("PAUSED", 270, 280);
		}
	}
	
	public class AL extends KeyAdapter {
		@SuppressWarnings("deprecation")
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_ENTER && !GAMESTARTED) {
				ball.start();
				p1.start();
				p2.start();
				GAMESTARTED = true;
			}
			else if(e.getKeyCode()==KeyEvent.VK_P && GAMESTARTED) {
				ball.suspend();
				p1.suspend();
				p2.suspend();
				PAUSED = true;
			}
			else if(e.getKeyCode()==KeyEvent.VK_SPACE && GAMESTARTED && PAUSED) {
				ball.resume();
				p1.resume();
				p2.resume();
				PAUSED = false;
			}
			else {
				b.p1.keyPressed(e);
				b.p2.keyPressed(e);
			}
		}
		
		public void keyReleased(KeyEvent e) {
			b.p1.keyReleased(e);
			b.p2.keyReleased(e);
		}
	}
}