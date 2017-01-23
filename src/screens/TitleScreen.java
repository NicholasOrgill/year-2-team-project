package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.TextSprite;

public class TitleScreen extends Screen {
	private TextSprite textSprite;

	int counter = 100;

	public TitleScreen(GameObject gameObject) {
		super(gameObject);
		textSprite = new TextSprite(0, 0, "WAIT " + counter);
		setNextScreen(new CautionScreen(gameObject));
	}

	@Override
	public void update() {
		textSprite.setText("WAIT " + counter);
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.update();
		counter--;
		if (counter <= 0) {
			moveScreen();
		}
	}

	@Override
	public void draw(Graphics context) {
		textSprite.draw(context);
	}

}
