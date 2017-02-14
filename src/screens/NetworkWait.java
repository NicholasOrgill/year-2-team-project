package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.BottomBarSprite;
import sprites.DotSpriteBackground;
import sprites.FancyCenterTextSprite;
import sprites.MenuCenterTextSprite;
import sprites.TopBarSprite;
import utils.ColorPack;

public class NetworkWait extends Screen {
	private TopBarSprite topBarSprite;
	private BottomBarSprite bottomBarSprite;
	private DotSpriteBackground dotBackground;

	private MenuCenterTextSprite[] centerText;
	private FancyCenterTextSprite titleText;
	int counter = 0;
	int sel = 0;

	public NetworkWait(GameObject gameObject) {
		super(gameObject);
		topBarSprite = new TopBarSprite("NETWORK WAIT");
		bottomBarSprite = new BottomBarSprite();
		bottomBarSprite.setLeftText("PLEASE WAIT");
		bottomBarSprite.setRightText("PLEASE WAIT");
		
		titleText = new FancyCenterTextSprite((getScreenWidth() / 2), (int) (getScreenHeight() * 0.4), "BeatNetwork");
		
		dotBackground = new DotSpriteBackground(10, 10, 20, 30);
		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());

	}

	@Override
	public void update() {
	
		topBarSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		topBarSprite.update();

		bottomBarSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		bottomBarSprite.update();

		titleText.setScreenSize(getScreenWidth(), getScreenHeight());
		titleText.update();
		
		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		dotBackground.update();
		
	}

	@Override
	public void draw(Graphics context) {

		dotBackground.draw(context);

		// Draw in the background color
		context.setColor(ColorPack.PRIMARY);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());

		// Draw the top and bottom
		bottomBarSprite.draw(context);
		topBarSprite.draw(context);


		// Draw the title text
		titleText.draw(context);
	}

}
