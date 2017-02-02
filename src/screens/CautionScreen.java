package screens;

import java.awt.Color;
import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.CenterTextSprite;
import sprites.CheckedSprite;
import sprites.TopBarSprite;
import sprites.TriangleSprite;

public class CautionScreen extends Screen {

	CenterTextSprite cautionText;
	CenterTextSprite cautionText2;
	CenterTextSprite infoText;
	CenterTextSprite descText;
	TriangleSprite triangle;
	CheckedSprite checkedBackground;
	TopBarSprite topBarSprite;

	int counter = 0;
	
	public CautionScreen(GameObject gameObject) {
		super(gameObject);
		cautionText = new CenterTextSprite(getScreenWidth() / 2, (int) (getScreenHeight() * 0.4), "CAUTION");
		cautionText2 = new CenterTextSprite(getScreenWidth() / 2, (int) (getScreenHeight() * 0.41), "CAUTION");
		cautionText.setFontSize(0.2);
		cautionText2.setFontSize(0.2);
		cautionText.setColor(new Color(255, 0, 0, 200));
		cautionText2.setColor(new Color(128, 0, 0, 200));
		
		topBarSprite = new TopBarSprite("Caution");
		
		checkedBackground = new CheckedSprite(0, 0);
		
		infoText = new CenterTextSprite(getScreenWidth() / 2, (int) (getScreenHeight() * 0.6),
				"EXTREME PLAY MOTIONS ARE DANGEROUS!");
		infoText.setFontSize(0.035);
		

		descText = new CenterTextSprite(getScreenWidth() / 2, (int) (getScreenHeight() * 0.7),
				"Be careful not to slip or fall while playing the game");
		descText.setFontSize(0.03);
		
		triangle = new TriangleSprite(getScreenWidth() / 2, getScreenHeight() / 2, (int) (getScreenWidth() * 0.4), (int) (getScreenWidth() * 0.4));
	}

	@Override
	public void update() {
		counter++;
		cautionText.setScreenSize(getScreenWidth(), getScreenHeight());
		cautionText2.setScreenSize(getScreenWidth(), getScreenHeight());
		infoText.setScreenSize(getScreenWidth(), getScreenHeight());
		descText.setScreenSize(getScreenWidth(), getScreenHeight());
		topBarSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		
		topBarSprite.update();
		checkedBackground.update();

		triangle.setScreenSize(getScreenWidth(), getScreenHeight());
		triangle.update();

		
		if (counter == 1000) {
			System.exit(0);
		}
	}

	@Override
	public void draw(Graphics context) {
		checkedBackground.draw(context);
		
		topBarSprite.draw(context);
		
		
		context.setColor(Color.BLACK);
		context.fillRect(0, (int)(getScreenHeight() * (1 - 0.1)), getScreenWidth(), (int)(getScreenHeight() * 0.1));
		
		cautionText2.draw(context);
		cautionText.draw(context);
		infoText.draw(context);
		descText.draw(context);
	}

}
