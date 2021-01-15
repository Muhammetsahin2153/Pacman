package pacman;

import java.awt.Image;

public class Ghost extends Panel {
	public int aci;
	public double xvel = 10, yvel = 10;

	public Ghost(int pos_x, int pos_y) {
		super(pos_x, pos_y);
		initImage(image);
	}
	private void initImage(Image image) {

		hayaletUpdate(image);
		getImageDimensions();
	}
	public void move() {

		if (pos_x == 0 && pos_y <= 360)
			pos_y -= yvel;
		if (pos_y == 0 && pos_x <= 240)
			pos_x += xvel;
		if (pos_x > 0 && pos_y >= 360)
			pos_x -= xvel;
		if (pos_x >= 240 && pos_y < 360)
			pos_y += yvel;
	}
	public void move2() {

		if (pos_x == 60 && pos_y <=300)
			pos_y += yvel;
		if (pos_y == 120 && pos_x <= 240)
			pos_x -= xvel;
		if (pos_x >= 60 && pos_y == 300)
			pos_x += xvel;
			
		if (pos_x == 240 && pos_y <= 300)
			pos_y -= yvel;
			
	}
	public void move3() {
		
		if (pos_x == 0 && pos_y <= 360)
			pos_y -= yvel;
		if (pos_x <= 180 && pos_y == 0)
			pos_x += xvel;
		if (pos_x == 180&& pos_y <150)
			pos_y += yvel;
		if (pos_x>150 && pos_x <=290 && pos_y == 150)
			pos_x += xvel;
		if (pos_x == 300&& pos_y < 360)
			pos_y += yvel;
		if (pos_x > 0 && pos_y >= 360)
			pos_x -= xvel;
//		
	}
	public void hayaletUpdate(Image image) {
		loadImage("C:\\Users\\Muhammet Þahin\\eclipse-workspace\\Pacman\\image\\pacman6.png");
	}

}
