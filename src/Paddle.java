import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle implements Runnable {
	int x, y, yDirection, player;
	Rectangle paddle;
	
	public Paddle(int x, int y, int player) {
		this.x = x;
		this.y = y;
		this.player = player;
		paddle = new Rectangle(x, y, 10, 75);
	}
	
	public void keyPressed(KeyEvent e) {
		switch(player) {
			default:
				System.out.println("Please enter a valid player in Paddle constructor");
				break;
			case 1:
				if(e.getKeyCode()==e.VK_W)
					setYDirection(-2);
				if(e.getKeyCode()==e.VK_S)
					setYDirection(2);
				break;
			case 2:
				if(e.getKeyCode()==e.VK_UP)
					setYDirection(-2);
				if(e.getKeyCode()==e.VK_DOWN)
					setYDirection(2);
				break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(player) {
			default:
				System.out.println("Please enter a valid player in Paddle constructor");
				break;
			case 1:
				if(e.getKeyCode()==e.VK_W)
					setYDirection(0);
				if(e.getKeyCode()==e.VK_S)
					setYDirection(0);
				break;
			case 2:
				if(e.getKeyCode()==e.VK_UP)
					setYDirection(0);
				if(e.getKeyCode()==e.VK_DOWN)
					setYDirection(0);
				break;
		}
	}
	
	public void setYDirection(int yDirection) {
		this.yDirection = yDirection;
	}
	
	public void move() {
		paddle.y+=yDirection;
		if(paddle.y<=20)
			paddle.y=20;
		if(paddle.y>=425)
			paddle.y=425;
	}
	
	public void draw(Graphics g) {
		switch(player) {
			default:
				System.out.println("Please enter a valid player in Paddle constructor");
				break;
			case 1:
				g.setColor(Color.GREEN);
				g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
				break;
			case 2:
				g.setColor(Color.GREEN);
				g.fillRect(paddle.x, paddle.y, paddle.width, paddle.height);
				break;
		}
	}
	
	public void run() {
		try {
			while(!Main.WINNER) {
				move();
				Thread.sleep(7);
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}