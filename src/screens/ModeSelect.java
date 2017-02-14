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
import sprites.SystemBox;
import sprites.SystemText;
import utils.ColorPack;

public class ModeSelect extends Screen {

	private SystemText textSprite;
	private DotSpriteBackground dotBackground;
	private ImageGrad imageGrad;
	private FancyCenterTextSprite title;
	
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
		// textSprite.setText("K44:K:C:A:2012072391");
		try {
			textSprite.setText("ID:" + Inet4Address.getLocalHost().getHostAddress().replaceAll("\\.", ":"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setNextScreen(new NetworkSelect(gameObject));

		box = new SystemBox();
		box.setScreenSize(getScreenWidth(), getScreenHeight());
		
		imageGrad = new ImageGrad();
		imageGrad.setOpacity(1);

		dotBackground = new DotSpriteBackground(10, 10, 20, 30, false, getScreenWidth(), getScreenHeight());
		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		
		title = new FancyCenterTextSprite((int)(getScreenWidth() * 0.94), (int)(getScreenHeight() * 0.85), "MODE SELECT");
		title.setScreenSize(getScreenWidth(), getScreenHeight());
		
	}

	@Override
	public void update() {
		
		title.setScreenSize(getScreenWidth(), getScreenHeight());
		if(count > 40) {
			title.update();
		}
		

		imageGrad.setScreenSize(getScreenWidth(), getScreenHeight());
		imageGrad.update();

		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.update();

		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		dotBackground.update();

		box.setScreenSize(getScreenWidth(), getScreenHeight());
		
		if(count > 180) {
			box.update();
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

		title.draw(context);
		if(count > 80) {
			//box.draw(context);
		}

	}

}
