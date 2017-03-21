package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import input.InputHandler;
import sprites.BannerSprite;
import sprites.DotSpriteBackground;
import sprites.FancyCenterTextSprite;
import sprites.ImageGrad;
import sprites.ImageSprite;
import sprites.SystemBox;
import sprites.SystemTextCenterFade;
import utils.ColorPack;
import utils.ImageLoader;


/**
 * A Screen to select as Server or Client
 *
 */
public class NetworkSelect2 extends Screen {

	private DotSpriteBackground dotBackground;
	private ImageGrad imageGrad;
	private FancyCenterTextSprite title;
	private SystemTextCenterFade centex;
	private SystemTextCenterFade centex2;
	private SystemBox box;

	int count = 0;

	private ImageSprite networkImage;
	private ImageSprite networkImage2;
	private BannerSprite bannerSprite;
	

	@Override
	public void keyPressed(int key) {
		System.out.println("on" + key);
		if (key == InputHandler.PLAYKEY0) {
			moveScreen();
		}
		if(key == InputHandler.PLAYKEY2) {
			select();
		}
	}

	@Override
	public void keyReleased(int key) {
		System.out.println("off" + key);
	}

	public NetworkSelect2(GameObject gameObject) {
		super(gameObject);

		bannerSprite = new BannerSprite(getScreenWidth() / 2, getScreenHeight() / 2 + 80);

		networkImage = new ImageSprite(getScreenWidth() / 2 - 100, (int) (getScreenHeight() * 0.4),
				ImageLoader.loadImageFromResource("src/res/images/networkConnect.png"));
		networkImage2 = new ImageSprite(getScreenWidth() / 2 + 100, (int) (getScreenHeight() * 0.4),
				ImageLoader.loadImageFromResource("src/res/images/networkConnect.png"));

		networkImage.setOpacity(0);
		networkImage2.setOpacity(0);

		centex = new SystemTextCenterFade(getScreenWidth() / 2, getScreenHeight() / 2 + 90, " ");
		centex2 = new SystemTextCenterFade(getScreenWidth() / 2, getScreenHeight() / 2 + 110, " ");


		setNextScreen(new NetworkSelect(getGameObject()));

		box = new SystemBox();
		box.setScreenSize(getScreenWidth(), getScreenHeight());

		imageGrad = new ImageGrad();
		imageGrad.setOpacity(1);

		dotBackground = new DotSpriteBackground(10, 10, 20, 30, false, getScreenWidth(), getScreenHeight());
		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());

		title = new FancyCenterTextSprite((int) (getScreenWidth() * 0.94), (int) (getScreenHeight() * 0.85), "NETWORK");
		title.setScreenSize(getScreenWidth(), getScreenHeight());

	}

	@Override
	public void update() {

		title.setScreenSize(getScreenWidth(), getScreenHeight());
		if (count > 40) {
			title.update();
		}

		bannerSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		bannerSprite.update();

		networkImage.setScreenSize(getScreenWidth(), getScreenHeight());
		networkImage.update();

		networkImage2.setScreenSize(getScreenWidth(), getScreenHeight());
		networkImage2.update();

		imageGrad.setScreenSize(getScreenWidth(), getScreenHeight());
		imageGrad.update();

		centex.setScreenSize(getScreenWidth(), getScreenHeight());
		centex.update();
		
		centex2.setScreenSize(getScreenWidth(), getScreenHeight());
		centex2.update();

		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		dotBackground.update();

		box.setScreenSize(getScreenWidth(), getScreenHeight());

		if (count > 180) {
			box.update();
		}

		if (count == 180) {
			networkImage.fadeIn();
		}
		
		if (count == 190) {
			networkImage2.fadeIn();
			centex.setText("Press [E] to select as Server or Client. Press [Q] to confirm.");
		}

		count++;

	}

	@Override
	public void draw(Graphics context) {

		context.setColor(ColorPack.DARK);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());

		imageGrad.draw(context);

		dotBackground.draw(context);

		title.draw(context);
		if (count > 80) {
			box.draw(context);

		}

		if (count > 200) {

			centex.draw(context);
			centex2.draw(context);
		}

		networkImage.draw(context);
		networkImage2.draw(context);
	}
	
	private void select(){
		getGameObject().setServer(!getGameObject().isServer());
		if(getGameObject().isServer()){
			centex2.setText("You are Server.");
		}else{
			centex2.setText("You are Client.");
		}
	}

}
