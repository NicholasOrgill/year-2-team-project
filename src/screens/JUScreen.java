package screens;

import java.awt.Graphics;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import engine.GameObject;
import engine.Screen;
import sprites.DotSpriteBackground;
import sprites.FancyCenterTextSprite;
import sprites.ImageSprite;
import sprites.SystemBox;
import sprites.SystemText;
import sprites.SystemTextCenterFade;
import utils.ColorPack;
import utils.ImageLoader;

public class JUScreen extends Screen {

	private SystemText textSprite;
	private DotSpriteBackground dotBackground;
	private ImageSprite imageSprite;
	
	private SystemBox box;
	private FancyCenterTextSprite title;
	int count = 0;
	
	
	public JUScreen(GameObject gameObject) {
		super(gameObject);
		textSprite = new SystemText(10, 10, "HELLO");
		//textSprite.setText("K44:K:C:A:2012072391");
		try {
			textSprite.setText("ID:" + Inet4Address.getLocalHost().getHostAddress().replaceAll("\\.", ":"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setNextScreen(new NetworkWait(gameObject));
		
		
		box = new SystemBox();
		box.setScreenSize(getScreenWidth(), getScreenHeight());
		

		dotBackground = new DotSpriteBackground(10, 10, 20, 30);
		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		
		imageSprite = new ImageSprite(getScreenWidth() / 2, (int)(getScreenHeight() * 0.4), ImageLoader.loadImageFromResource("src/res/beatnet.png"));
		imageSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		
		
		title = new FancyCenterTextSprite(getScreenWidth() / 2, (int)(getScreenHeight() * 0.4), "beatnet");
		title.setScreenSize(getScreenWidth(), getScreenHeight());
	}

	@Override
	public void update() {

		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.update();
		
		
		
		

		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		dotBackground.update();
		
		imageSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		imageSprite.update();
		
		title.setScreenSize(getScreenWidth(), getScreenHeight());
		title.update();
		
		count++;
		if(count > 500) {
			box.setScreenSize(getScreenWidth(), getScreenHeight());
			box.update();
			getGameObject().getOverlay().getMiddleBottom().setText("PLEASE WAIT");
			//moveScreen();
		}
		
	}

	@Override
	public void draw(Graphics context) {

		context.setColor(ColorPack.DARK);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());

		dotBackground.draw(context);

		textSprite.draw(context);
		
		
		if(count > 500) {
			//box.draw(context);
		}
		
		imageSprite.draw(context);
		
		//title.draw(context);
		
	}

}
