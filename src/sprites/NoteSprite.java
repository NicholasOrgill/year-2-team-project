package sprites;

import java.awt.Color;
import java.awt.Graphics;

import engine.Sprite;
import utils.ColorPack;

public class NoteSprite extends Sprite {
	int count = 0;

	int amount = 4;
	int gap = 20;
	int size = 60;
	int x = 0;
	int v = 0;
	int this_width = 0;
	boolean buttons[];
	int length = 10;
	boolean hit = false;
	int ai = 0;
	double position;
	private boolean isRemoved = false;
	private int opac = 255;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            Top left x position of the sprite
	 * @param y
	 *            Top left y position of the sprite
	 * @param width
	 *            Width of the sprite
	 * @param height
	 *            Height of the sprite
	 * @param buttons
	 *            The buttons that this sprite corresponds to
	 * @param length
	 *            The length of the note
	 * @param position
	 *            The position of the note
	 * @param speed
	 *            The speed of the note
	 */
	public NoteSprite(int x, int y, int width, int height, boolean[] buttons, int length, double position,
			double speed) {
		super(x, y, width, height);
		this.buttons = buttons;
		// if(length != 0 && ((length * speed) > 60)) {
		// this.length = (int) (length * speed);
		// } else {
		this.length = 60;
		// }
		this.position = position;
	}

	/**
	 * Gets the length of the note
	 * 
	 * @return The length of the note
	 */
	public int getLength() {
		return length;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		if (count == 0) {
			x = (int) (getScreenWidth() * position);
		}

		this_width = (size + gap) * amount;

		count++;

		if (isRemoved && opac > 0) {
			opac -= 1 + Math.abs(opac / 8);
		}

		setX(x);
		setWidth(size);
		setHeight(length);
	}

	/**
	 * Sets the note to hit
	 */
	public void hit() {
		hit = true;
	}

	/**
	 * Sets the note to AI
	 */
	public void setAI() {
		ai = 1;
	}

	/**
	 * Removes the note
	 */
	public void remove() {
		isRemoved = true;
	}

	/**
	 * States whether the note is removed
	 * 
	 * @return
	 */
	public boolean isRemoved() {
		return isRemoved;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Graphics context) {
		if (ai == 1) {
			context.setColor(new Color(255, 0, 0, 90));
		} else {
			context.setColor(
					new Color(ColorPack.WHITE.getRed(), ColorPack.WHITE.getGreen(), ColorPack.WHITE.getBlue(), opac));
		}
		if (isRemoved() && this.length == 60) {

		} else {
			for (int i = 0; i < buttons.length; i++) {
				if (buttons[i])
					context.fillRect(x + (gap / 2) + (i * (size + gap)) - (int) (this_width / 2), getY() - length, size,
							length);
			}
		}
	}
}
