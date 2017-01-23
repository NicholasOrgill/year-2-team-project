package screens;

import java.awt.Color;
import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.TextSprite;

public class ErrorScreen extends Screen {

	TextSprite textSprite;
	int counter = 0;
	public ErrorScreen(GameObject gameObject) {
		super(gameObject);
		textSprite = new TextSprite(0, 0, "HELLO");
	}

	@Override
	public void update() {
		counter++;
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.setText("COUNTT " + counter);
	}

	@Override
	public void draw(Graphics context) {
		textSprite.draw(context);
	}

}
