package engine;

import input.InputHandler;
import screens.Overlay;

/**
 * The game object is given around to all the different scenes
 * @author bobbydilley
 *
 */
public class GameObject {
	private int width;
	private int height;
	private Overlay overlay;
	private InputHandler inputHandler;
	private int p1Score;
	private int p2Score;
	private boolean isServer = false;
	

	public GameObject(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public boolean isServer() {
		return isServer;
	}
	
	public InputHandler getInputHandler() {
		return this.inputHandler;
	}
	
	public void setInputHandler(InputHandler inputHandler) {
		this.inputHandler = inputHandler;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setOverlay(Overlay overlay) {
		this.overlay = overlay;
	}
	
	public Overlay getOverlay() {
		return this.overlay;
	}

	public int getP1Score() {
		return p1Score;
	}

	public void setP1Score(int score) {
		this.p1Score = score;
	}

	public int getP2Score() {
		return p2Score;
	}

	public void setP2Score(int p2Score) {
		this.p2Score = p2Score;
	}
}
