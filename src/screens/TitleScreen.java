package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.BeatSprite;
import sprites.TextSprite;

public class TitleScreen extends Screen {
	private TextSprite textSprite;
	private BeatSprite beatSprite;
	
	int counter = 100;

	public TitleScreen(GameObject gameObject) {
		super(gameObject);
		textSprite = new TextSprite(0, 0, "WAIT " + counter);
		beatSprite = new BeatSprite(10, 10, 50, 50);
		setNextScreen(new CautionScreen(gameObject));
	}

	@Override
	public void update() {
		textSprite.setText("WAIT " + counter);
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		beatSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.update();
		counter--;
		if (counter <= 0) {
			moveScreen();
		}
	}

	@Override
	public void draw(Graphics context) {
		textSprite.draw(context);
		beatSprite.draw(context);
	}

}
