package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.SystemText;

public class ErrorScreen extends Screen {

	SystemText textSprite;
	int counter = 0;
	public ErrorScreen(GameObject gameObject) {
		super(gameObject);
		textSprite = new SystemText(10, 10, "GAME PROGRAM READY...");
		setNextScreen(new Overlay(gameObject));
	}

	@Override
	public void update() {
		counter++;
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		//textSprite.setText("COUNT " + counter);
	}

	@Override
	public void draw(Graphics context) {
		textSprite.draw(context);
	}

}
