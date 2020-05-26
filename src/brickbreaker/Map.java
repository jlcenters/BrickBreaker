package brickbreaker;

import java.awt.*;

public class Map {
//FIELDS
	private int[][] map; // TO MAKE A GRID
	private int brickHeight, brickWidth;

	public final int HOR_PAD = 80, VERT_PAD = 50;

	public Map(int row, int col) {
		initMap(row, col);

		// GENERATING BASED OFF WIDTH AND HEIGHT OF GAME VIEW
		brickWidth = (GameMain.WIDTH - 2 * HOR_PAD) / col; // 2 SPACES (ON THE OUTSIDE) WILL NOT BE FILLED
		brickHeight = (GameMain.HEIGHT / 2 - VERT_PAD * 2) / row; // DOES NOT FILL ON BOTTOM; FILLS ONLY UPPER PORTION
	}

	public void initMap(int row, int col) {
		map = new int[row][col];

		for (int i = 0; i < map.length; i++) { // LOOPS THRU ROW
			for (int j = 0; j < map[0].length; j++) { // LOOPS THRU COL; COL 0 WILL BE EMPTY
				int r = (int) (Math.random() * 3 + 1);

				map[i][j] = r;// START AT RANDOM NUM
			}
		}

		map[3][2] = 4;
		map[3][6] = 5;
	}

	public void draw(Graphics2D g) {
		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				if (map[row][col] > 0) { // CHECKS ONLY IF VALUE IS NOT 0 (CHOSEN VOID VALUE)

					// CLOSER THE BRICK GETS TO BREAKING, TINT INCREASES
					if (map[row][col] == 1) {
						g.setColor(new Color(200, 200, 200));
					}
					if (map[row][col] == 2) {
						g.setColor(new Color(150, 150, 150));
					}
					if (map[row][col] == 3) {
						g.setColor(new Color(100, 100, 100));
					}
					if (map[row][col] == PowerUp.WIDE_PADDLE) {
						g.setColor(PowerUp.WIDE_COLOR);
					}
					if (map[row][col] == PowerUp.FAST_BALL) {
						g.setColor(PowerUp.FAST_COLOR);
					}

					g.fillRect(col * brickWidth + HOR_PAD, row * brickHeight + VERT_PAD, brickWidth, brickHeight);
					g.setStroke(new BasicStroke(2));
					g.setColor(Color.WHITE); // OUTLINE
					g.drawRect(col * brickWidth + HOR_PAD, row * brickHeight + VERT_PAD, brickWidth, brickHeight);
				}
			}
		}
	}

	// WINLOSE
	public boolean isWin() {
		boolean won = false;
		int bricksRemain = 0;

		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map[0].length; col++) {
				bricksRemain += map[row][col]; // ADDS TO INT OBJECT FOR EVERY BRICK REMAINING

			}
		}

		if (bricksRemain == 0) {
			won = true;
		}

		return won;
	}

// GET/SET 
	public int[][] getMapGrid() {
		return map;
	}

	public void setBrick(int row, int col, int value) {
		map[row][col] = value;
	}

	public int getBrickWidth() {
		return brickWidth;
	}

	public int getBrickHeight() {
		return brickHeight;
	}

	public void brickHit(int row, int col) {
		map[row][col] -= 1;
		if (map[row][col] < 0) {
			map[row][col] = 0;
		}
	}
}
