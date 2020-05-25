package brickbreaker;

import java.awt.*;

public class Hud {
	private int score;

	public Hud() {
		init();
	}

	public void init() {
		score = 0;
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.CYAN);
		g.setFont(new Font("Courier New", Font.PLAIN, 18));
		g.drawString("Score: " + score, 20, 20);
	}

	public int getScore() {
		return score;
	}

	public void addScore(int score) {
		this.score += score;
	}
}
