package engine;

import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class GameObject {
	private int width;
	private int height;
	
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
}
