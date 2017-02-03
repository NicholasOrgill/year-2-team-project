package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.BeatSprite;
import sprites.BottomBarSprite;
import sprites.FancyCenterTextSprite;
import sprites.MenuCenterTextSprite;
import sprites.TextSprite;
import sprites.TopBarSprite;
import utils.ColorPack;

public class TitleScreen extends Screen {
	private TopBarSprite topBarSprite;
	private BottomBarSprite bottomBarSprite;
	
	private MenuCenterTextSprite centerText;
	private FancyCenterTextSprite titleText;
	
	public TitleScreen(GameObject gameObject) {
		super(gameObject);
		topBarSprite = new TopBarSprite("CAUTION");
		bottomBarSprite = new BottomBarSprite("Press A to start");
		titleText = new FancyCenterTextSprite((getScreenWidth() / 2), (int)(getScreenHeight() * 0.4), "BeatNetwork");
		centerText = new MenuCenterTextSprite((getScreenWidth() / 2), (int)(getScreenHeight() * 0.7), "Start Game");
		
	}

	@Override
	public void update() {
		topBarSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		topBarSprite.update();
		
		bottomBarSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		bottomBarSprite.update();
		
		centerText.setScreenSize(getScreenWidth(), getScreenHeight());
		centerText.update();
		
		titleText.setScreenSize(getScreenWidth(), getScreenHeight());
		titleText.update();
	}

	@Override
	public void draw(Graphics context) {
		// Draw in the background color
		context.setColor(ColorPack.PRIMARY);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());
		
		// Draw the top and bottom
		bottomBarSprite.draw(context);
		topBarSprite.draw(context);
		
		// Draw the middle text
		centerText.draw(context);
		
		// Draw the title text
		//titleText.draw(context);
	}

}
