package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import input.InputHandler;
import network.Client.Network;
import network.Server.MessageQueue;
import network.Server.Server;
import sprites.BannerSprite;
import sprites.DotSpriteBackground;
import sprites.FancyCenterTextSprite;
import sprites.ImageGrad;
import sprites.ImageSprite;
import sprites.SystemBox;
import sprites.SystemTextCenterFade;
import utils.ColorPack;
import utils.ImageLoader;

public class NetworkSelect extends Screen {

	private DotSpriteBackground dotBackground;
	private ImageGrad imageGrad;
	private FancyCenterTextSprite title;
	private SystemTextCenterFade centex;
	private SystemBox box;
	private boolean networkrun = true;
	private Server server = null;
	private GameObject gObject;

	private ImageSprite networkImage;
	private ImageSprite networkImage2;

	private BannerSprite bannerSprite;

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

	public NetworkSelect(GameObject gameObject) {
		super(gameObject);
		gObject = gameObject;

		bannerSprite = new BannerSprite(getScreenWidth() / 2, getScreenHeight() / 2 + 80);

		networkImage = new ImageSprite(getScreenWidth() / 2 - 100, (int) (getScreenHeight() * 0.4),
				ImageLoader.loadImageFromResource("src/res/images/networkConnect.png"));
		networkImage2 = new ImageSprite(getScreenWidth() / 2 + 100, (int) (getScreenHeight() * 0.4),
				ImageLoader.loadImageFromResource("src/res/images/networkConnect.png"));

		networkImage.setOpacity(0);
		networkImage2.setOpacity(0);

		centex = new SystemTextCenterFade(getScreenWidth() / 2, getScreenHeight() / 2 + 90, "Waiting for Network");

		setNextScreen(new PlayScreen(gameObject));

		box = new SystemBox();
		box.setScreenSize(getScreenWidth(), getScreenHeight());

		imageGrad = new ImageGrad();
		imageGrad.setOpacity(1);

		dotBackground = new DotSpriteBackground(10, 10, 20, 30, false, getScreenWidth(), getScreenHeight());
		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());

		title = new FancyCenterTextSprite((int) (getScreenWidth() * 0.94), (int) (getScreenHeight() * 0.85), "NETWORK");
		title.setScreenSize(getScreenWidth(), getScreenHeight());

		networkrun = false;

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
		}

		if (count == 300) {
			if (gObject.isServer()) {
				centex.setText("Establising Network...");
				MessageQueue serverInput = new MessageQueue();
				String name = "Admin";

				server = new Server(serverInput, name);
				server.start();
			} else {
				centex.setText("Connecting Server...");
				Network n = new Network(gObject.getHostname(), gObject.getName());
				gObject.setNetwork(n);
			}

		}

		if (count == 410) {
			if (server != null && server.isAlive())
				centex.setText("Network Established");
			else if (gObject.getNetwork() != null && gObject.getNetwork().isConnected()) {
				centex.setText("Connected");
			} else {
				centex.setText("Network Check Fail.");
			}
		}

		if (count == 600) {
			moveScreen();
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

		if (count > 210) {

			centex.draw(context);
		}

		// bannerSprite.draw(context);

		networkImage.draw(context);
		networkImage2.draw(context);
	}

}
