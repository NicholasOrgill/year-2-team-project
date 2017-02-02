package screens;

import java.awt.Color;
import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.BeatSprite;
import sprites.FancyCenterTextSprite;

public class DevScreen extends Screen {
	private FancyCenterTextSprite textSprite;
	private FancyCenterTextSprite textSpriteOutline;
	private BeatSprite[] beatSprite;
	
	public DevScreen(GameObject gameObject) {
		super(gameObject);
		textSprite = new FancyCenterTextSprite(getScreenWidth() / 2, getScreenHeight() / 2, "BeatNetwork");
		textSprite.setFontSize(0.1);
		textSpriteOutline = new FancyCenterTextSprite(getScreenWidth() / 2, getScreenHeight() / 2, "BeatNetwork");
		textSpriteOutline.setFontSize(0.105);
		textSpriteOutline.setColor(new Color(128, 0, 128, 255));
		
		beatSprite = new BeatSprite[4];
		for(int i = 0 ; i < beatSprite.length ; i++) {
			beatSprite[i] = new BeatSprite(i * 90, 0, 80, 80);
		}
		
	}

	@Override
	public void update() {
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSpriteOutline.setScreenSize(getScreenWidth(), getScreenHeight());
		for(int i = 0 ; i < beatSprite.length ; i++) {
			beatSprite[i].setScreenSize(getScreenWidth(), getScreenHeight());
			beatSprite[i].update();
		}
		
	}

	@Override
	public void draw(Graphics context) {
		textSpriteOutline.draw(context);
		textSprite.draw(context);
		for(int i = 0 ; i < beatSprite.length ; i++) {
			beatSprite[i].draw(context);
		}
		
	}

}
