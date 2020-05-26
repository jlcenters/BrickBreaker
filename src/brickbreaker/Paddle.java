package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {
// FIELDS
	private double x;
	private int width, height, startWidth;
	private long widthTimer;
	boolean altWidth;

	public final int YPOS = GameMain.HEIGHT - 100;

// CONSTRUCTOR
	public Paddle(int width, int height) {
		altWidth = false;
		this.width = width;
		startWidth = width;
		this.height = height;
		height = 20;

		x = GameMain.WIDTH / 2 - width / 2;
	}

// UPDATE
	public void update() {
		if ((System.nanoTime() - widthTimer) / 1000 > 4000000 && altWidth) {
			width = startWidth;
			altWidth = false;
		}
	}

// DRAW
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) x, YPOS, width, height);

		if (altWidth) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier New", Font.BOLD, 18));
			g.drawString("Shrinking In: " + ((4 - (System.nanoTime() - widthTimer) / 1000000000)), (int) x, YPOS + 18);
		}
	}

	public void mouseMoved(int mouseXPos) {
		x = mouseXPos; // SETS X OF MOUSE TO X OF PADDLE

		if (x > GameMain.WIDTH - width) {
			x = GameMain.WIDTH - width;
		}
	}

// HITBOX CHECK FOR COLLISION
	public Rectangle getRect() {
		return new Rectangle((int) x, YPOS, width, height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int newWidth) {
		width = newWidth;
		altWidth = true;
		setWidthTimer();
	}

	public int getStartWidth() {
		return startWidth;
	}

	public void setStartWidth(int startWidth) {
		this.startWidth = startWidth;
	}

	public long getWidthTimer() {
		return widthTimer;
	}

	public void setWidthTimer() {
		widthTimer = System.nanoTime();
	}
}
