package brickbreaker;

import java.awt.*;

public class Ball {
	private double x, y, dx, dy;
	private int ballSize = 8;

	public Ball() {
		x = 200;
		y = 200;
		dx = 1;
		dy = 3; // DIRECTIONAL OBJECTS SHOW THE RATE AT WHICH X & Y MOVE AT THEIR INT VALUE PER
				// LOOP

	}

	public void update() {
		setPosition();
	}

	public void setPosition() {
		x += dx;
		y += dy;

		if (x < 0) {
			dx = -dx;
		}
		if (y < 0) {
			dy = -dy;
		}
		if (x > GameMain.WIDTH - ballSize) {
			dx = -dx;
		}
		if (y > GameMain.HEIGHT - ballSize) {
			dy = -dy;
		}
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.RED);
		g.setStroke(new BasicStroke(4)); // LINE THICKNESS
		g.drawOval((int) x, (int) y, ballSize, ballSize);

	}

	public Rectangle getRect() {
		return new Rectangle((int) x, (int) y, ballSize, ballSize);
	}

	// LOSE CHECK
	public boolean isLoss() {
		boolean lose = false;

		if (y > GameMain.HEIGHT - ballSize * 2) {
			lose = true;
		}

		return lose;
	}

// MANIPULATING THE Y DIRECTION FOR COLLISION
	public void setDY(double theDY) {
		dy = theDY;
	}

	public double getDY() {
		return dy;
	}

	public void setDX(double dx) {
		this.dx += dx;
	}

	public double getdX() {
		return dx;
	}

	public double getX() {
		return x;
	}

}
