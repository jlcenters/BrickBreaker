package brickbreaker;

import javax.swing.JPanel;

public class GamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // DEFAULT SERIAL ID

	public GamePanel() {
		// BLANK CONSTRUCTOR
	}

	public void play() {
		// GAME LOOP
		try {
			Thread.sleep(2000); // PROGRAM DELAYS 2000MS BEFORE PLAYING
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
