package engine;

import java.awt.Graphics;
/**
 * The screen class which represents one viewable screen in the game
 * @author bobbydilley
 *
 */
public class Screen {
	private GameObject gameObject;
	private Screen nextScreen;
	private boolean moveScreen;
	
	public Screen(GameObject gameObject) {
		this.gameObject = gameObject;
		this.moveScreen = false;
	}
	
	protected void setNextScreen(Screen nextScreen) {
		this.nextScreen = nextScreen;
	}
	
	protected Screen getNextScreen() {
		return this.nextScreen;
	}
	
	protected void moveScreen() {
		gameObject.getInputHandler().setScreen(nextScreen);
		moveScreen = true;
	}
	
	protected boolean shouldMoveScreen() {
		return moveScreen;
	}
	
	protected GameObject getGameObject() {
		return gameObject;
	}
	
	protected int getScreenWidth() {
		return (int) getGameObject().getWidth();
	}
	
	protected int getScreenHeight() {
		return (int) getGameObject().getHeight();
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics context) {
		
	}
	
	public void dispose() {
		
	}
	 
	public void keyPressed(int key) {
		
	}
	
	public void keyReleased(int key) {
		
	}
	
	public void powerKeyPressed(int key){
		
	}
	
	public void receivedPowerKey(int key){
		
	}
}
