package pacman;

import java.awt.Image;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Pacman extends Panel {
	private int pos_2x = 30;
	private int pos_2y = 30;
	public int aci;
	private List<Food> foods;

	public Pacman(int pos_x, int pos_y) {
		super(pos_x, pos_y);
		initImage(image);
		foods = new ArrayList<>();

	}

	private void initImage(Image image) {

		pacmanImage(image);
		getImageDimensions();

	}

	public void move() {
		pacmanImage(image);
	}

	public List<Food> getPoints() {
		return foods;
	}

	public void pacmanImage(Image image) {
		if (aci == 0)
			loadImage("C:\\Users\\Muhammet Þahin\\eclipse-workspace\\Pacman\\image\\pacman1.png");
		if (aci == 90)
			loadImage("C:\\Users\\Muhammet Þahin\\eclipse-workspace\\Pacman\\image\\pacman2.png");
		if (aci == 180)
			loadImage("C:\\Users\\Muhammet Þahin\\eclipse-workspace\\Pacman\\image\\pacman3.png");
		if (aci == 270)
			loadImage("C:\\Users\\Muhammet Þahin\\eclipse-workspace\\Pacman\\image\\pacman4.png");
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

		try {
			Board board = new Board();
			int keyCode = e.getKeyCode();

			int[][] levelData = board.optionallevelData();
			if(board.inGame) {
				if (keyCode == KeyEvent.VK_LEFT) {
					aci = 180;
					if (levelData[pos_y / 30][(pos_x / 30) - 1] != 1) {
						pos_x -= pos_2x;
					}
				} else if (keyCode == KeyEvent.VK_RIGHT) {
					aci = 0;
					if (levelData[pos_y / 30][(pos_x / 30) + 1] != 1) {
						pos_x += pos_2x;
					}
				} else if (keyCode == KeyEvent.VK_UP) {
					aci = 270;
					if (levelData[(pos_y / 30) - 1][pos_x / 30] != 1) {
						pos_y -= pos_2y;
					}
				} else if (keyCode == KeyEvent.VK_DOWN) {
					aci = 90;
					if (levelData[(pos_y / 30) + 1][pos_x / 30] != 1) {
						pos_y += pos_2y;
					}
				}	
			}
			else {
				if (keyCode == 's' || keyCode == 'S') {
                    board.inGame = true;
                    board.initGame();
                }
			}
			

		} catch (Exception e2) {
		}
	}

	public void keyReleased(KeyEvent e) {
	}

}
