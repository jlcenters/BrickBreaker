package brickbreaker;

import java.awt.*;

public class Ball {
	private double x, y, dx, dy;
	private int ballSize, startSpeed, speed;
	private long speedTimer;
	private boolean altSpeed;

	public Ball(int ballSize, int speed) {
		altSpeed = false;
		this.ballSize = ballSize;
		this.speed = speed;
		startSpeed = speed;
		x = 200;
		y = 200;
		dx = 1;
		dy = 3; // DIRECTIONAL OBJECTS SHOW THE RATE AT WHICH X & Y MOVE AT THEIR INT VALUE PER
				// LOOP

	}

	public void update() {
		setPosition();

		if ((System.nanoTime() - speedTimer) / 1000 > 9000000 && altSpeed) {
			speed = startSpeed;
			altSpeed = false;
		}
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

		if (altSpeed) {
			g.setColor(Color.RED);
			g.setFont(new Font("Courier New", Font.BOLD, 18));
			g.drawString("Slowing In: " + ((9 - (System.nanoTime() - speedTimer) / 1000000000)), 20, 35);
		}
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

	public int getStartSpeed() {
		return startSpeed;
	}

	public void setStartSpeed(int startSpeed) {
		this.startSpeed = startSpeed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
		altSpeed = true;
		setSpeedTimer();
	}

	public long getSpeedTimer() {
		return speedTimer;
	}

	public void setSpeedTimer() {
		speedTimer = System.nanoTime(); // billionth of a second
	}

	public boolean isAltSpeed() {
		return altSpeed;
	}

	public void setAltSpeed(boolean altSpeed) {
		this.altSpeed = altSpeed;
	}

	public int getBallSize() {
		return ballSize;
	}

	public void setBallSize(int ballSize) {
		this.ballSize = ballSize;
	}

}
