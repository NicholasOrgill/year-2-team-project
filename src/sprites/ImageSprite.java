package sprites;

import java.awt.Graphics;
import java.awt.Image;

import engine.Sprite;

public class ImageSprite extends Sprite {
	private Image image;
	
	public ImageSprite(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public ImageSprite(int x, int y, Image image) {
		super(x, y, image.getWidth(null), image.getHeight(null));
		this.image = image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics context) {
		//context.fillRect(getX(), getY(), 50, 50);
		context.drawImage(image, getX() - (image.getWidth(null) / 2), getY() - (image.getHeight(null) / 2), null);
		}

}
