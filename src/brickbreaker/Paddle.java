package brickbreaker;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {
// FIELDS
	private double x;
	private int width, height;
	public final int YPOS = GameMain.HEIGHT - 100;

// CONSTRUCTOR
	public Paddle() {
		width = 100;
		height = 20;
		x = GameMain.WIDTH / 2 - width / 2;

	}

// UPDATE
	public void update() {

	}

// DRAW
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) x, YPOS, width, height);
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
}
