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

public class TitleScreen extends Screen {
	private TopBarSprite topBarSprite;
	private BottomBarSprite bottomBarSprite;
	private DotSpriteBackground dotBackground;

	private MenuCenterTextSprite[] centerText;
	private FancyCenterTextSprite titleText;
	int counter = 0;
	int sel = 0;

	public TitleScreen(GameObject gameObject) {
		super(gameObject);
		topBarSprite = new TopBarSprite("BEATNETWORK");
		bottomBarSprite = new BottomBarSprite();
		//bottomBarSprite.setLeftText("PLEASE WAIT");
		//bottomBarSprite.setRightText("PLEASE WAIT");
		topBarSprite.makeTrans();
		bottomBarSprite.makeTrans();
		titleText = new FancyCenterTextSprite((getScreenWidth() / 2), (int) (getScreenHeight() * 0.4), "BeatNetwork");
		//dotBackground = new DotSpriteBackground(10, 10, 20, 30);
		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		String titles[] = { "Single Player", "Network Play", "Options", "Close" };
		centerText = new MenuCenterTextSprite[titles.length];
		for (int i = 0; i < centerText.length; i++) {
			centerText[i] = new MenuCenterTextSprite((int) (getScreenWidth() / 2),
					(int) (getScreenHeight() * (0.6 + (i * 0.07))), titles[i]);
		}

		centerText[0].select();
		for (int i = 1; i < centerText.length; i++) {
			centerText[i].deselect();
		}
		setNextScreen(new StartScreen(gameObject));

	}

	@Override
	public void update() {
		if (counter == 100) {
			counter = 0;
			sel++;
			if (sel == 4) {
				sel = 0;
				moveScreen();
			}
		}
		counter++;

		for (int i = 0; i < centerText.length; i++) {
			if (i == sel) {
				centerText[i].select();
			} else {
				centerText[i].deselect();
			}

		}
		topBarSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		topBarSprite.update();

		bottomBarSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		bottomBarSprite.update();

		for (int i = 0; i < centerText.length; i++) {
			centerText[i].setScreenSize(getScreenWidth(), getScreenHeight());
			centerText[i].update();
		}

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

		// Draw the middle text
		for (int i = 0; i < centerText.length; i++) {
			centerText[i].draw(context);
		}

		// Draw the title text
		// titleText.draw(context);
	}

}
