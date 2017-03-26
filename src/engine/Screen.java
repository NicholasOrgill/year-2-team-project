package engine;

import java.awt.Graphics;

/**
 * The screen class which represents one viewable screen in the game
 * 
 * @author bobbydilley
 *
 */
public class Screen {
	private GameObject gameObject;
	private Screen nextScreen;
	private boolean moveScreen;

	/**
	 * Constructor
	 * 
	 * @param gameObject
	 *            The game object being used
	 */
	public Screen(GameObject gameObject) {
		this.gameObject = gameObject;
		this.moveScreen = false;
	}

	/**
	 * Sets the screen to be navigated to next
	 * 
	 * @param nextScreen
	 *            The next screen that will be displayed
	 */
	protected void setNextScreen(Screen nextScreen) {
		this.nextScreen = nextScreen;
	}

	/**
	 * Gets the next screen to be displayed
	 * 
	 * @return The next screen to be displayed
	 */
	protected Screen getNextScreen() {
		return this.nextScreen;
	}

	/**
	 * Sets the next screen and navigates to it
	 */
	protected void moveScreen() {
		gameObject.getInputHandler().setScreen(nextScreen);
		moveScreen = true;
	}

	/**
	 * Flag for whether the screen should be moved
	 * 
	 * @return The status of screen moving
	 */
	protected boolean shouldMoveScreen() {
		return moveScreen;
	}

	/**
	 * Gets the current game object
	 * 
	 * @return The current game object
	 */
	protected GameObject getGameObject() {
		return gameObject;
	}

	/**
	 * Gets the width of the screen
	 * 
	 * @return The width of the screen
	 */
	protected int getScreenWidth() {
		return (int) getGameObject().getWidth();
	}

	/**
	 * Gets the height of the screen
	 * 
	 * @return The height of the screen
	 */
	protected int getScreenHeight() {
		return (int) getGameObject().getHeight();
	}

	/**
	 * Updates the screen - this is called ~60 times per second, or every 16ms
	 */
	public void update() {

	}

	/**
	 * Draws the screen using the provided graphics object
	 * 
	 * @param context
	 *            The graphics object used to draw the screen
	 */
	public void draw(Graphics context) {

	}

	/**
	 * Deletes the screen
	 */
	public void dispose() {

	}

	/**
	 * Records a key press on the keyboard
	 * 
	 * @param key
	 *            The integer value of the key pressed
	 */
	public void keyPressed(int key) {

	}

	/**
	 * Records a key release from the keyboard
	 * 
	 * @param key
	 *            The integer value of the key released
	 */
	public void keyReleased(int key) {

	}

	/**
	 * Records a key being pressed by the opponent
	 * 
	 * @param key
	 *            The integer value of the key pressed
	 */
	public void oppoKeyPressed(int key) {

	}

	/**
	 * Records a key being released by the opponent
	 * 
	 * @param key
	 *            The integer value of the key released
	 */
	public void oppoKeyReleased(int key) {

	}
}
