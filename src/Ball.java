import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball implements Runnable{
	int x, y, xDirection, yDirection, p1Score, p2Score, playerWhoWins;
	
	Paddle p1 = new Paddle(20, 220, 1);
	Paddle p2 = new Paddle(570, 220, 2);
	
	Rectangle ball;
	
	public Ball(int x, int y) {
		p1Score = p2Score = 0;
		this.x = x;
		this.y = y;
		
		Random r = new Random();
		int randomXDirection = r.nextInt(1);
		int randomYDirection = r.nextInt(1);
		if(randomXDirection==0)
			randomXDirection--;
		if(randomYDirection==0)
			randomYDirection--;
		setXDirection(randomXDirection);
		setYDirection(randomYDirection);
		
		ball = new Rectangle(this.x, this.y, 10, 10);
	}
	
	public void setXDirection(int xDirection) {
		this.xDirection = xDirection;
	}
	
	public void setYDirection(int yDirection) {
		this.yDirection = yDirection;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(ball.x, ball.y, ball.width, ball.height);
		if(playerWhoWins==1 || playerWhoWins==2) {
			g.setColor(Color.YELLOW);
			g.drawString("Player "+playerWhoWins+" is the Winner!", 230, 280);
		}
	}
	
	public void collision() {
		if(ball.intersects(p1.paddle))
			setXDirection(1);
		if(ball.intersects(p2.paddle))
			setXDirection(-1);
	}
	
	public void move() {
		collision();
		ball.x += xDirection;
		ball.y += yDirection;
		
		if(ball.x<=0) {
			setXDirection(1);
			p2Score++;
		}
		if(ball.x>=590) {
			setXDirection(-1);
			p1Score++;
		}
		if(ball.y<=20)
			setYDirection(1);
		if(ball.y>=490)
			setYDirection(-1);
	}
	
	public void run() {
		try {
			while(!Main.WINNER) {
				move();
				Thread.sleep(5);
				
				if(p1Score==2) {
					Main.WINNER = true;
					playerWhoWins = 1;
				}
				if(p2Score==2) {
					Main.WINNER = true;
					playerWhoWins = 2;
				}
			}
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
}