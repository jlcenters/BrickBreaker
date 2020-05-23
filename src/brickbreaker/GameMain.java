package brickbreaker;

import javax.swing.JFrame;

public class GameMain {

	public static final int WIDTH = 640, HEIGHT = 480;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Brick Breaker");

		GamePanel panel = new GamePanel();

		// Thread thread = new Thread(panel); // THREADS USED IN MORE COMPLEX GAMES
		// (GOOD PRACTICE); MULTIPLE RUN
		// SIMULTANEOUSLY; PANEL IS WHAT THREAD WILL RUN; IMPLEMENTS RUNNABLE

		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		panel.play();// RUNS GAME
	}
}
