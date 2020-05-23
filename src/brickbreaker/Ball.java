package brickbreaker;

import java.awt.*;

public class Ball {
	private double x, y, dx, dy;
	private int ballSize = 30;

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
}
