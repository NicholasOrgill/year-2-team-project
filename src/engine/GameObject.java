package engine;

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

	public GameObject(int width, int height) {
		this.width = width;
		this.height = height;
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
}
