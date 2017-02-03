package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.BeatSprite;
import sprites.CheckedSprite;
import sprites.FancyCenterTextSprite;
import sprites.MenuCenterTextSprite;
import utils.ColorPack;

public class DevScreen extends Screen {
	private FancyCenterTextSprite textSprite;
	private BeatSprite[] beatSprite;
	private MenuCenterTextSprite menuText;
	CheckedSprite checkedBackground;
	
	public DevScreen(GameObject gameObject) {
		super(gameObject);
		menuText = new MenuCenterTextSprite((int)(getScreenWidth() / 2), (int)(getScreenHeight() * 0.65), "START GAME");
		checkedBackground = new CheckedSprite(0, 0);
		
		textSprite = new FancyCenterTextSprite(getScreenWidth() / 2, getScreenHeight() / 2, "BeatNetwork");
		textSprite.setFontSize(0.1);
		textSprite.setColor(ColorPack.PRIMARY);

		
		int amount = 4;
		int size = (int) (getScreenWidth() * 0.05);
		int gap = 30;
		
		int x = (int) (getScreenWidth() / 2);
		int y = (int) (getScreenHeight() * 0.8);
		
		int start = x - (size + gap) * (int) (amount / 2);
		
		if(amount % 2 == 0) {
			 start += ((size + gap) / 2);
		}
		
		beatSprite = new BeatSprite[amount];
		for(int i = 0 ; i < beatSprite.length ; i++) {
			beatSprite[i] = new BeatSprite(start + i * (size + gap), y, size, size);
		}
		
	}

	@Override
	public void update() {
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		menuText.setScreenSize(getScreenWidth(), getScreenHeight());
		for(int i = 0 ; i < beatSprite.length ; i++) {
			beatSprite[i].setScreenSize(getScreenWidth(), getScreenHeight());
			beatSprite[i].update();
		}
		checkedBackground.update();
		
	}

	@Override
	public void draw(Graphics context) {
		checkedBackground.draw(context);
		menuText.draw(context);
		textSprite.draw(context);
		for(int i = 0 ; i < beatSprite.length ; i++) {
			beatSprite[i].draw(context);
		}
		
	}

}
