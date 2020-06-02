package brickbreaker;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
// FIELDS
	boolean playing;
	private BufferedImage image;
	private Graphics2D g;
	private MyMouseMotionListener mouseListener;
	private int mouseX;

// ENTITIES
	private Ball ball;
	private Paddle paddle;
	private Map map;
	private Hud hud;
	private ArrayList<PowerUp> powerUps;

// DEFAULT SERIAL ID
	private static final long serialVersionUID = 1L;

	public GamePanel() {
		// CONSTRUCTOR
		init();

	}

	public void init() {
		ball = new Ball(15, 12);
		paddle = new Paddle(100, 20);
		mouseListener = new MyMouseMotionListener();
		addMouseMotionListener(mouseListener);
		playing = true;
		map = new Map(4, 8);
		hud = new Hud();
		mouseX = 0;
		playing = true;
		image = new BufferedImage(GameMain.WIDTH, GameMain.HEIGHT, BufferedImage.TYPE_INT_RGB);
		powerUps = new ArrayList<>();

		g = (Graphics2D) image.getGraphics(); // TELLING G TO DRAW ON IMAGE IN AN UPDATED FORMAT

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // REMOVES PIXELATION
	}

	public void play() {
		// GAME LOOP
		try {
			Thread.sleep(4000); // PROGRAM DELAYS 2000MS BEFORE PLAYING
		} catch (Exception e) {
			e.printStackTrace();
		}

		while (playing) {

			// UPDATE
			update();

			// RENDER/DRAW
			draw();

			// DISPLAY
			repaint();

			// WAIT
			try {
				Thread.sleep(ball.getSpeed()); // DELAYS LOOP TO SLOW BALL DOWN
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		ball.update();
		paddle.update();
		checkCollisions();

		for (PowerUp pu : powerUps) {
			pu.update();
		}
	}

	public void draw() {
		g.setColor(Color.WHITE); // BG
		g.fillRect(0, 0, GameMain.WIDTH, GameMain.HEIGHT);

		ball.draw(g);
		paddle.draw(g);
		map.draw(g);
		hud.draw(g);

		drawPowerUps();

		if (map.isWin()) {
			drawWin();
			playing = false;
		}
		if (ball.isLoss()) {
			drawLose();
			playing = false;
		}
	}

	public void drawWin() {
		g.setColor(Color.CYAN);
		g.setFont(new Font("Courier New", Font.BOLD, 50));
		g.drawString("YOU WIN!", 200, 200);
	}

	public void drawLose() {
		g.setColor(Color.RED);
		g.setFont(new Font("Courier New", Font.BOLD, 50));
		g.drawString("You Lose :(", 200, 200);
	}

	public void drawPowerUps() {
		for (PowerUp pu : powerUps) {
			pu.draw(g);
		}
	}

	public void checkCollisions() {
		Rectangle ballRect = ball.getRect();
		Rectangle paddleRect = paddle.getRect();

		// POWERUPS
		for (int i = 0; i < powerUps.size(); i++) {
			Rectangle puRect = powerUps.get(i).getRect();

			if (paddleRect.intersects(puRect)) {
				if ((powerUps.get(i).getType() == PowerUp.WIDE_PADDLE) && !(powerUps.get(i).isUsed())) {
					paddle.setWidth(paddle.getWidth() * 2);
					powerUps.get(i).setUsed(true);
				}
				if ((powerUps.get(i).getType() == PowerUp.FAST_BALL) && !(powerUps.get(i).isUsed())) {
					ball.setSpeed(ball.getSpeed() / 2);
					powerUps.get(i).setUsed(true);
				}
			}
		}

		// PADDLE COLLISION
		if (ballRect.intersects(paddleRect)) {
			ball.setDY(-ball.getDY()); // IF BALL INTERSECTS PADDLE, BALL DIRECTION IS INVERSED

			// ADJUST MOVEMENT TO BE MORE EXTREME IF LANDS ON LEFT OR RIGHT QUARTERS
			if (ball.getX() < mouseX + paddle.getWidth() / 4) { // LEFT
				ball.setDX(-ball.getdX() - 1);
			}
			if (ball.getX() > mouseX + paddle.getWidth() * 3 / 4 && ball.getX() > mouseX + paddle.getWidth() / 4) { // RIGHT
				ball.setDX(-ball.getdX() + 1);
			}
		}

		// MAP COLLISION
		A: for (int row = 0; row < map.getMapGrid().length; row++) { // CAN LABEL LOOPS TO SPECIFY WHAT TO BREAK OUT OF
			for (int col = 0; col < map.getMapGrid()[0].length; col++) {
				if (map.getMapGrid()[row][col] > 0) { // CHECKS ONLY IF VALUE IS NOT 0 (CHOSEN VOID VALUE)
					int brickX = col * map.getBrickWidth() + map.HOR_PAD;
					int brickY = row * map.getBrickHeight() + map.VERT_PAD;
					int brickWidth = map.getBrickWidth();
					int brickHeight = map.getBrickHeight();

					Rectangle brickRect = new Rectangle(brickX, brickY, brickWidth, brickHeight);

					if (ballRect.intersects(brickRect)) { // IF BALL INTERSECTS BRICK, BRICK IS REMOVED AND SCORE += 50

						if (map.getMapGrid()[row][col] > 3) {
							powerUps.add(
									new PowerUp(brickX, brickY, map.getMapGrid()[row][col], brickWidth, brickHeight));

							map.setBrick(row, col, 0);
						} else {
							map.brickHit(row, col);
						}
						map.brickHit(row, col);
						ball.setDY(-ball.getDY());
						hud.addScore(50);
						break A; // (WILL BREAK OUT OF BOTH LOOPS INSTEAD OF YOUNGEST)
					}
				}
			}
		}
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g; // FORMAT G TO BE MORE MODERN

		g2.drawImage(image, 0, 0, GameMain.WIDTH, GameMain.HEIGHT, null); // DESCRIBES WHERE G2 DRAWS

		g2.dispose(); // DELETES THE EXCESS GRAPHICS OBJECTS TO SAVE MEMORY; WOULD OTHERWISE BE
						// STORING A LOT
						// OF UNNECESSARY OBJECTS
	}

	private class MyMouseMotionListener implements MouseMotionListener {

		@Override
		public void mouseDragged(MouseEvent arg0) {

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			mouseX = arg0.getX();
			paddle.mouseMoved(arg0.getX());
		}

	}

}
