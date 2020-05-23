package brickbreaker;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
// FIELDS
	boolean playing;
	private BufferedImage image;
	private Graphics2D g;
	private MyMouseMotionListener mouseListener;

// ENTITIES
	Ball ball;
	Paddle paddle;

// DEFAULT SERIAL ID
	private static final long serialVersionUID = 1L;

	public GamePanel() {
		// CONSTRUCTOR
		init();

	}

	public void init() {
		playing = true;

		image = new BufferedImage(GameMain.WIDTH, GameMain.HEIGHT, BufferedImage.TYPE_INT_RGB);

		g = (Graphics2D) image.getGraphics(); // TELLING G TO DRAW ON IMAGE IN AN UPDATED FORMAT

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // REMOVES PIXELATION
	}

	public void play() {
		// GAME LOOP
		ball = new Ball();
		paddle = new Paddle();
		mouseListener = new MyMouseMotionListener();
		addMouseMotionListener(mouseListener);
		playing = true;

		try {
			Thread.sleep(2000); // PROGRAM DELAYS 2000MS BEFORE PLAYING
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
				Thread.sleep(7); // DELAYS LOOP TO SLOW BALL DOWN
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void update() {
		ball.update();
	}

	public void draw() {
		g.setColor(Color.WHITE); // BG
		g.fillRect(0, 0, GameMain.WIDTH, GameMain.HEIGHT);

		ball.draw(g);
		paddle.draw(g);
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
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseMoved(MouseEvent arg0) {
			paddle.mouseMoved(arg0.getX());
		}

	}

}
