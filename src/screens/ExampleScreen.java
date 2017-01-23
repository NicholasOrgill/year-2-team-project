package screens;

import java.awt.Color;
import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.TextSprite;

public class ExampleScreen extends Screen {
	TextSprite sprite = new TextSprite(0, 0, "Hello World");
	
	public ExampleScreen(GameObject gameObject) {
		super(gameObject);

	}

	@Override
	public void update() {
		sprite.setScreenSize(getScreenWidth(), getScreenHeight());
	}

	@Override
	public void draw(Graphics context) {
		sprite.draw(context);
	}

}
