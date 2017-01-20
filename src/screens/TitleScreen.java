package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.TextSprite;

public class TitleScreen extends Screen {
	private TextSprite textSprite;

	int counter = 0;

	public TitleScreen(GameObject gameObject) {
		super(gameObject);
		textSprite = new TextSprite(0, 0, "DOMINIC WILSON", 0.1);
		setNextScreen(new ErrorScreen(gameObject));
	}

	@Override
	public void update() {
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.update();
		counter++;
		if (counter > 1000) {
			moveScreen();
		}
	}

	@Override
	public void draw(Graphics context) {
		textSprite.draw(context);
	}

}
