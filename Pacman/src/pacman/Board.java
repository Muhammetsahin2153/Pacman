package pacman;

import java.awt.Color;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
	private int satir = 0;
	private int sutun = 0;
	private int satirSayisi = 13;
	private int sutunSayisi = 11;
	private int width = 30;
	private int height = 30;
	private final int DELAY = 100;
	private final int pacman_X = 28;
	private final int pacman_Y = 28;
	private final int B_WIDTH = 328;
	private final int B_HEIGHT = 388;
	public boolean inGame;
	private boolean dying = false;
	private Timer timer;
	private Pacman pacman;
	private Ghost ghost, ghost2, ghost3;
	private List<Food> foods;
	public int hak = 3;
	public int score;

	public Board() {
		initBoard();
	}

	public void initBoard() {
		pacman = new Pacman(0, 0);
		ghost = new Ghost(300, 360);
		ghost2 = new Ghost(0, 180);
		ghost3 = new Ghost(240, 240);
		timer = new Timer(DELAY, this);
		timer.start();
		addKeyListener(new TAdapter());
		setFocusable(true);
		inGame = true;
		setBackground(Color.gray);
		
		initFood();

	}
	private void playGame(Graphics g) {

		if (dying) {

			death();

		} else {
			updatepacman();
			updateghost();
			updateFood();
			drawScore(g);
		}
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(inGame) {
			drawObjects(g);
		}
		else {
			drawGameOver(g);
		}
		
		
			

	}
	public int[][] optionallevelData() {
		int levelData[][] = { 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				{ 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1 },
				{ 0, 1, 1, 1, 1, 1, 0, 0, 0, 1, 1 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1 }, 
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, 
				{ 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0 },
				{ 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0 }, 
				{ 0, 1, 0, 0, 0, 1, 1, 1, 0, 1, 0 },
				{ 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, 
				{ 0, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }

		};
		return levelData;

	}
	private final int[][] kordinat = { { 12, 12 }, { 12, 42 }, { 12, 72 }, { 12, 102 }, { 12, 132 }, { 12, 162 },
			{ 12, 192 }, { 12, 222 }, { 12, 252 }, { 12, 282 }, { 12, 312 }, { 12, 342 }, { 12, 372 }, { 42, 12 },
			{ 42, 102 }, { 42, 132 }, { 42, 162 }, { 42, 192 }, { 42, 372 }, { 72, 12 }, { 72, 102 }, { 72, 132 },
			{ 72, 162 }, { 72, 192 }, { 72, 222 }, { 72, 252 }, { 72, 282 }, { 72, 312 }, { 72, 342 }, { 72, 372 },
			{ 102, 12 }, { 102, 102 }, { 102, 132 }, { 102, 162 }, { 102, 192 }, { 102, 222 }, { 102, 252 },
			{ 102, 282 }, { 102, 312 }, { 102, 372 }, { 132, 12 }, { 132, 102 }, { 132, 132 }, { 132, 162 },
			{ 132, 192 }, { 132, 222 }, { 132, 252 }, { 132, 282 }, { 132, 312 }, { 132, 372 }, { 162, 12 },
			{ 162, 102 }, { 162, 132 }, { 162, 162 }, { 162, 312 }, { 162, 372 }, { 192, 12 }, { 192, 42 }, { 192, 72 },
			{ 192, 102 }, { 192, 132 }, { 192, 162 }, { 192, 312 }, { 192, 342 }, { 192, 372 }, { 222, 12 },
			{ 222, 42 }, { 222, 72 }, { 222, 102 }, { 222, 132 }, { 222, 162 }, { 222, 312 }, { 222, 342 },
			{ 222, 372 }, { 252, 12 }, { 252, 42 }, { 252, 72 }, { 252, 102 }, { 252, 132 }, { 252, 162 }, { 252, 192 },
			{ 252, 222 }, { 252, 252 }, { 252, 282 }, { 252, 312 }, { 252, 342 }, { 252, 372 }, { 282, 12 },
			{ 282, 162 }, { 282, 192 }, { 282, 372 }, { 312, 12 }, { 312, 162 }, { 312, 192 }, { 312, 222 },
			{ 312, 252 }, { 312, 282 }, { 312, 312 }, { 312, 342 }, { 312, 372 }

	};
	public void initFood() {

		foods = new ArrayList<>();

		for (int[] p : kordinat) {
			foods.add(new Food(p[0], p[1]));
		}
	}
	public void drawObjects(Graphics g) {
		if (inGame) {
			int[][] levelData = optionallevelData();
			for (satir = 0; satir < satirSayisi; satir++) {
				for (sutun = 0; sutun < sutunSayisi; sutun++) {
					if (levelData[satir][sutun] == 1) {
						g.setColor(Color.blue);
						g.fillRect(sutun * 30, satir * 30, width, height);
					}
				}
			}
			for (Food foods : foods) {
				if (foods.isVissible()) {
					g.drawImage(foods.getImage(), foods.getX(), foods.getY(), 5, 5, this);
				}
			}

			if (pacman.isVissible()) {
				g.drawImage(pacman.getImage(), pacman.getX(), pacman.getY(), pacman_X, pacman_Y, this);
			}
			if (ghost.isVissible()) {
				g.drawImage(ghost.getImage(), ghost.getX(), ghost.getY(), width, height, this);
			}
			if (ghost2.isVissible()) {
				g.drawImage(ghost2.getImage(), ghost2.getX(), ghost2.getY(), width, height, this);
			}
			if (ghost3.isVissible()) {
				g.drawImage(ghost3.getImage(), ghost3.getX(), ghost3.getY(), width, height, this);
			}
			playGame(g);

		} else {
			showIntroScreen(g);
		}

	}

	private void showIntroScreen(Graphics g) {

		g.setColor(new Color(0, 32, 48));
		g.fillRect(50, 300 / 2 - 30, 300 - 100, 50);
		g.setColor(Color.white);
		g.drawRect(50, 300 / 2 - 30, 300 - 100, 50);

		String s = "Oyuna Baþla ";
		Font small = new Font("Times New Roman", Font.PLAIN, 20);
		FontMetrics metr = this.getFontMetrics(small);

		g.setColor(Color.white);
		g.setFont(small);
		g.drawString(s, (300 - metr.stringWidth(s)) / 2, 300 / 2);
	}

	private void drawGameOver(Graphics g) {

		String msg = "OYUN BÝTTÝ!";
		Font small = new Font("Times New Roman", Font.PLAIN, 30);
		FontMetrics fm = getFontMetrics(small);

		g.setColor(Color.red);
		setBackground(Color.white);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2, B_HEIGHT / 2);
	}

	private void drawScore(Graphics g) {

		String string;

		Font smallFont = null;
		g.setFont(smallFont);
		g.setColor(Color.black);
		string = "Score: " + score;
		g.drawString(string, 30, 40);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		inGame();

		checkPacman();

		Wait();
		repaint();

	}
	@Override
    public void addNotify() {
        super.addNotify();

        initGame();
    }

	private void inGame() {

		if (!inGame) {
			timer.stop();
		}
	}

	private void death() {

		hak--;

		if (hak == 0) {
			inGame = false;
		}
		initGame();
	}
	public void initGame() {
		hak=3;
		pacman = new Pacman(0, 0);
		ghost = new Ghost(300, 360);
		ghost2 = new Ghost(0, 180);
		ghost3 = new Ghost(240, 240);
		dying = false;
		
	}

	private void updatepacman() {
		if (pacman.isVissible()) {
			pacman.move();
		}
	}

	private void updateghost() {

		if (ghost.isVissible()) {
			ghost.move();
			ghost3.move2();
			ghost2.move3();
		}
	}

	private void updateFood() {

		if (foods.isEmpty()) {

			inGame = false;
			return;
		}

		for (int i = 0; i < foods.size(); i++) {

			Food pot = foods.get(i);

			if (pot.isVissible()) {
				if (foods.size() == 0)
					inGame = false;
			} else {
				foods.remove(i);
				score += 50;
			}
		}

	}

	public void checkPacman() {
		Rectangle r3 = pacman.getBounds();
		Rectangle r2 = ghost.getBounds();
		Rectangle r4 = ghost2.getBounds();
		Rectangle r5 = ghost3.getBounds();

		if (r3.intersects(r2) || r3.intersects(r4) || r3.intersects(r5)) {

			dying = true;
		}

		for (Food food : foods) {

			Rectangle r1 = food.getBounds();

			if (r3.intersects(r1)) {

				food.setVissible(false);
			}
		}
	}

	public void Wait() {
		if (pacman.pos_x >= B_WIDTH - pacman_X)
			pacman.pos_x = B_WIDTH - pacman_X;
		if (pacman.pos_y >= B_HEIGHT - pacman_Y)
			pacman.pos_y = B_HEIGHT - pacman_Y;
		if (pacman.pos_x <= 0)
			pacman.pos_x = 0;
		if (pacman.pos_y <= 0)
			pacman.pos_y = 0;

	}

	private class TAdapter extends KeyAdapter {
		@Override
		public void keyReleased(KeyEvent e) {
			pacman.keyReleased(e);

		}

		@Override
		public void keyPressed(KeyEvent e) {
			pacman.keyPressed(e);
			

		}

	}

}
