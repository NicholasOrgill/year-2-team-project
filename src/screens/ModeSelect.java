package screens;

import java.awt.Graphics;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import engine.GameObject;
import engine.Screen;
import input.InputHandler;
import sprites.DotSpriteBackground;
import sprites.FancyCenterTextSprite;
import sprites.ImageGrad;
import sprites.ModeBoxSprite;
import sprites.SystemBox;
import sprites.SystemText;
import utils.ColorPack;

public class ModeSelect extends Screen {

	private SystemText textSprite;
	private DotSpriteBackground dotBackground;
	private ImageGrad imageGrad;
	private FancyCenterTextSprite title;
	private ModeBoxSprite boxSpriteSingle;
	private ModeBoxSprite boxSpriteAI;
	private ModeBoxSprite boxSpriteNetwork;

	private SystemBox box;

	int count = 0;

	@Override
	public void keyPressed(int key) {
		System.out.println("on" + key);
		if (key == InputHandler.PLAYKEY0) {
			moveScreen();
		}
	}

	@Override
	public void keyReleased(int key) {
		System.out.println("off" + key);
	}

	public ModeSelect(GameObject gameObject) {
		super(gameObject);
		textSprite = new SystemText(10, 10, "HELLO");
		try {
			textSprite.setText("ID:" + Inet4Address.getLocalHost().getHostAddress().replaceAll("\\.", ":"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setNextScreen(new NetworkSelect(gameObject));

		boxSpriteSingle = new ModeBoxSprite((int) (getScreenWidth() * 0.2), getScreenHeight() / 2);
		boxSpriteAI = new ModeBoxSprite((int) (getScreenWidth() * 0.5), getScreenHeight() / 2);
		boxSpriteNetwork = new ModeBoxSprite((int) (getScreenWidth() * 0.8), getScreenHeight() / 2);

		box = new SystemBox();
		box.setScreenSize(getScreenWidth(), getScreenHeight());

		imageGrad = new ImageGrad();
		imageGrad.setOpacity(1);

		dotBackground = new DotSpriteBackground(10, 10, 20, 30, false, getScreenWidth(), getScreenHeight());
		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());

		title = new FancyCenterTextSprite((int) (getScreenWidth() * 0.94), (int) (getScreenHeight() * 0.85),
				"SELECT MODE");
		title.setScreenSize(getScreenWidth(), getScreenHeight());

	}

	@Override
	public void update() {

		title.setScreenSize(getScreenWidth(), getScreenHeight());
		if (count > 40) {
			title.update();
		}

		imageGrad.setScreenSize(getScreenWidth(), getScreenHeight());
		imageGrad.update();
		
		boxSpriteSingle.setScreenSize(getScreenWidth(), getScreenHeight());
		boxSpriteAI.setScreenSize(getScreenWidth(), getScreenHeight());
		boxSpriteNetwork.setScreenSize(getScreenWidth(), getScreenHeight());
		
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.update();

		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		dotBackground.update();

		box.setScreenSize(getScreenWidth(), getScreenHeight());

		if (count > 180) {
			box.update();
		}

		if (count > 480) {
			boxSpriteAI.update();
			boxSpriteSingle.update();
			boxSpriteNetwork.update();
		}

		count++;

	}

	@Override
	public void draw(Graphics context) {

		context.setColor(ColorPack.DARK);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());

		imageGrad.draw(context);

		dotBackground.draw(context);

		textSprite.draw(context);

		boxSpriteSingle.draw(context);
		boxSpriteAI.draw(context);
		boxSpriteNetwork.draw(context);

		title.draw(context);
		if (count > 80) {
			// box.draw(context);
		}

	}

}
