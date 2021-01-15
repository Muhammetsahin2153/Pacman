package pacman;

import java.awt.Image;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
public class Panel {
	protected int pos_x;
	protected int pos_y;
	protected int width;
    protected int height;
	protected boolean Vissible ;
	protected Image image;
	private final int pacmand_X = 16;
	private final int pacmand_Y = 16;
	
	public Panel(int pos_x, int pos_y){
		this.pos_x=pos_x;
		this.pos_y = pos_y;
		Vissible = true;
	}
	protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }
	protected void loadImage(String imageName) {

        ImageIcon ii = new ImageIcon(imageName);
        image = ii.getImage();
        
    }

    public Image getImage (){
        return image;
    } 
    public int getX() {
        return pos_x;
    }

    public int getY() {
        return pos_y;
    }

    public boolean isVissible() {
        return Vissible;
    }

    public void setVissible(Boolean Vissible) {
        this.Vissible = Vissible;
    }

    public Rectangle getBounds() {
        return new Rectangle(pos_x, pos_y, pacmand_X, pacmand_Y);
    }

	

	

}

