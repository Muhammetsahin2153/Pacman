package pacman;

import java.awt.Image;

public class Food extends Panel {
	public Pacman pacman;
	public Food(int pos_x, int pos_y) {
		super(pos_x, pos_y);
		initImage(image);
	}
	private void initImage(Image image) {

		pointImage(image);
		getImageDimensions();

	}
	public void pointImage(Image image) {
		loadImage("C:\\Users\\Muhammet Þahin\\eclipse-workspace\\Pacman\\image\\food.png");

	}
	
}
