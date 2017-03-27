package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.SystemTextCenterFade;

/**
 * The Overlay which simply displays the press start over all the screens
 * @author Bobby Dilley
 *
 */
public class Overlay extends Screen {
	private SystemTextCenterFade middleBottom;

	/**
	 * {@inheritDoc}
	 */
	public Overlay(GameObject gameObject) {
		super(gameObject);
		middleBottom = new SystemTextCenterFade(getScreenWidth() / 2, (int) (getScreenHeight() * 0.95), "PRESS START");

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		middleBottom.setScreenSize(getScreenWidth(), getScreenHeight());
		middleBottom.update();

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Graphics context) {
		// Draw on top of everything
		middleBottom.draw(context);
	}

	/**
	 * Gets the text from the bottom of the screen
	 * 
	 * @return The text from the bottom of the screen
	 */
	public SystemTextCenterFade getMiddleBottom() {
		return middleBottom;
	}

}
