package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.DotSpriteBackground;
import sprites.FancyCenterTextSprite;
import sprites.ImageGrad;
import sprites.SystemTextCenterShine;
import sprites.SystemTextKern;
import sprites.SystemTextShine;
import utils.ColorPack;

public class SelectScreen extends Screen {

	private SystemTextKern textSprite;
	private DotSpriteBackground dotBackground;
	private ImageGrad imageGrad;
	private FancyCenterTextSprite title;
	
	private SystemTextCenterShine typeText;
	private SystemTextCenterShine levelText;
	private SystemTextCenterShine numberText;
	private SystemTextShine nameText;
	private SystemTextShine byText;
	
	public SelectScreen(GameObject gameObject) {
		super(gameObject);
		nameText = new SystemTextShine(78, 430, "Tetris Theme Tune");
		nameText.setFontSize(0.032);
		
		byText = new SystemTextShine(78, 450, "Robert Dilley");
		byText.setFontSize(0.022);
		
		
		typeText = new SystemTextCenterShine(getScreenWidth() - 142, 280, "EXTREME");
		levelText = new SystemTextCenterShine(getScreenWidth() - 142, 120, "LEVEL");
		numberText = new SystemTextCenterShine(getScreenWidth() - 142, 190, "4");
		levelText.setFontSize(0.032);
		numberText.setFontSize(0.2);
		typeText.shine();
		
		
		imageGrad = new ImageGrad();
		imageGrad.setOpacity(1);

		dotBackground = new DotSpriteBackground(10, 10, 20, 30, false, getScreenWidth(), getScreenHeight());
		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());

		title = new FancyCenterTextSprite((int) (getScreenWidth() * 0.94), (int) (getScreenHeight() * 0.85),
				"SELECT MUSIC");
		title.setScreenSize(getScreenWidth(), getScreenHeight());
		
		textSprite = new SystemTextKern(20, 30, "FIRST SONG");
		
	}

	@Override
	public void update() {
		typeText.setScreenSize(getScreenWidth(), getScreenHeight());
		levelText.setScreenSize(getScreenWidth(), getScreenHeight());
		numberText.setScreenSize(getScreenWidth(), getScreenHeight());
		nameText.setScreenSize(getScreenWidth(), getScreenHeight());
		byText.setScreenSize(getScreenWidth(), getScreenHeight());
		
		title.setScreenSize(getScreenWidth(), getScreenHeight());
		title.update();

		imageGrad.setScreenSize(getScreenWidth(), getScreenHeight());
		imageGrad.update();
		
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.update();

		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		dotBackground.update();
	}

	@Override
	public void draw(Graphics context) {
		context.setColor(ColorPack.DARK);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());
		imageGrad.draw(context);
		dotBackground.draw(context);
		
		context.setColor(ColorPack.WHITE);
		context.drawLine(0, 48, 199, 48);
		
		// Draw the box
		context.setColor(ColorPack.FADEDWHITE);
		int pwidth = 320;
		int pheight = 240;
		double scale = 1.4;
		pwidth = (int)(pwidth * scale);
		pheight = (int)(pheight * scale);
		context.fillRect(getScreenWidth() / 2 - pwidth / 2 - 100, getScreenHeight() / 2 - pheight / 2 - 50, pwidth, pheight);
		
		typeText.draw(context);
		levelText.draw(context);
		numberText.draw(context);
		nameText.draw(context);
		byText.draw(context);
		
		textSprite.draw(context);

		title.draw(context);
	}

}