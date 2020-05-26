package brickbreaker;

import java.awt.*;

public class PowerUp {

	private int x, y, dy, type, width, height;
	private boolean isOnScreen;
	private boolean isUsed;
	private Color color;

	public final static int WIDE_PADDLE = 4;
	public final static int FAST_BALL = 5;
	public final static Color WIDE_COLOR = Color.GREEN;
	public final static Color FAST_COLOR = Color.RED;

	public PowerUp(int xStart, int yStart, int type, int width, int height) {
		x = xStart;
		y = yStart;
		this.type = type;
		this.width = width;
		this.height = height;

		if (type < 4) {
			type = 4;
		}
		if (type > 5) {
			type = 5;
		}
		if (type == WIDE_PADDLE) {
			color = WIDE_COLOR;
		}
		if (type == FAST_BALL) {
			color = FAST_COLOR;
		}

		dy = (int) (Math.random() * 6 + 1);
	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}

	public void update() {
		y += dy;
		if (y > GameMain.HEIGHT) {
			isOnScreen = false;
		}
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public boolean isOnScreen() {
		return isOnScreen;
	}

	public void setOnScreen(boolean isOnScreen) {
		this.isOnScreen = isOnScreen;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public static int getWidePaddle() {
		return WIDE_PADDLE;
	}

	public static int getFastBall() {
		return FAST_BALL;
	}

	public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

}
