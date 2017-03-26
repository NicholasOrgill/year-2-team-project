package sprites;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import engine.Sprite;
import utils.ImageLoader;

public class ImageGrad extends Sprite {
	private Image image;
	private boolean impress = false;
	private float size = 1;
	private float opacity = 1f;
	private boolean fadeIn = false;
	private boolean fadeOut = false;
	private float famount = 0.8f;

	/**
	 * {@inheritDoc}
	 */
	public ImageGrad(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	/**
	 * Constructor with image
	 * 
	 * @param x
	 *            Top left x position of the sprite
	 * @param y
	 *            Top left y position of the sprite
	 * @param image
	 *            The image displayed
	 */
	public ImageGrad(int x, int y, Image image) {
		super(x, y, image.getWidth(null), image.getHeight(null));
		this.image = image;
	}

	/**
	 * Sets the image
	 * 
	 * @param image
	 *            The new image
	 */
	public void setImage(Image image) {
		this.image = image;
	}

	/**
	 * Default constructor
	 */
	public ImageGrad() {
		super(0, 0, 0, 0);
		this.image = ImageLoader.loadImageFromResource("src/res/images/gradient.png");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		if (impress) {
			size += Math.max(0.1, (int) (size * 0.6));
			;
		}

		if (fadeOut) {
			opacity = Math.max(0, opacity * famount);
			if (opacity == 0) {
				fadeOut = false;
			}
		}

		if (fadeIn) {
			opacity = Math.min(1, Math.max(0.1f, opacity) * 1.2f);
			if (opacity == 1) {
				fadeIn = false;
			}
		}

	}

	/**
	 * Sets the sprite opacity
	 * 
	 * @param opac
	 *            The new opacity
	 */
	public void setOpacity(float opac) {
		this.opacity = opac;
	}

	/**
	 * Gets the opacity of the sprite
	 * 
	 * @return The sprite opacity
	 */
	public float getOpacity() {
		return opacity;
	}

	/**
	 * Impresses the sprite
	 */
	public void impress() {
		impress = true;
		fadeOut = true;
	}

	/**
	 * Fades the sprite in
	 */
	public void fadeIn() {
		fadeIn = true;
		fadeOut = false;
	}

	/**
	 * Fades the sprite out
	 */
	public void fadeOut() {
		fadeOut = true;
		fadeIn = false;
	}

	/**
	 * Fades the sprite out quickly
	 */
	public void fadeOutQuick() {
		this.famount = 0f;
		fadeOut = true;
		fadeIn = false;
	}

	/**
	 * Sets the size of the sprite to the given value
	 * 
	 * @param size
	 *            The new size
	 */
	public void setSize(float size) {
		this.size = size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Graphics context) {

		((Graphics2D) context).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));

		context.drawImage(image, 0, (int) (getScreenHeight() * 0.7), getScreenWidth(), (int) (getScreenHeight() * 0.3),
				null);
		((Graphics2D) context).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}

}
