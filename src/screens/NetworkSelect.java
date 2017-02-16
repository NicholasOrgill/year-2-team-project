package screens;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import network.Server.MessageQueue;
import engine.GameObject;
import engine.Screen;
import input.InputHandler;
import network.Client.ClientResolve;
import network.Client.Network;
import network.Server.Server;
import sprites.DotSpriteBackground;
import sprites.FancyCenterTextSprite;
import sprites.ImageGrad;
import sprites.SystemBox;
import sprites.SystemTextCenterFade;
import utils.ColorPack;

public class NetworkSelect extends Screen {


	private DotSpriteBackground dotBackground;
	private ImageGrad imageGrad;
	private FancyCenterTextSprite title;
	private SystemTextCenterFade centex;
	private SystemBox box;
	private boolean networkrun = true;
	private Server server = null;
	
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
		
		centex = new SystemTextCenterFade(getScreenWidth() / 2, getScreenHeight() / 2, "Waiting for Network");
		
		setNextScreen(new PlayScreen(gameObject));

		box = new SystemBox();
		box.setScreenSize(getScreenWidth(), getScreenHeight());
		
		imageGrad = new ImageGrad();
		imageGrad.setOpacity(1);

		dotBackground = new DotSpriteBackground(10, 10, 20, 30, false, getScreenWidth(), getScreenHeight());
		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		
		title = new FancyCenterTextSprite((int)(getScreenWidth() * 0.94), (int)(getScreenHeight() * 0.85), "NETWORK");
		title.setScreenSize(getScreenWidth(), getScreenHeight());
		
		networkrun = false;
		
		
	}

	@Override
	public void update() {
		
		title.setScreenSize(getScreenWidth(), getScreenHeight());
		if(count > 40) {
			title.update();
		}
		

		imageGrad.setScreenSize(getScreenWidth(), getScreenHeight());
		imageGrad.update();
		
		centex.setScreenSize(getScreenWidth(), getScreenHeight());
		centex.update();


		dotBackground.setScreenSize(getScreenWidth(), getScreenHeight());
		dotBackground.update();

		box.setScreenSize(getScreenWidth(), getScreenHeight());
		
		if(count > 180) {
			box.update();
		}
		
		
		if(count == 300) {
			centex.setText("Establising Network...");
			MessageQueue serverInput = new MessageQueue();
			String name = "Admin";
			
			server = new Server(serverInput, name);
			server.start();
			System.out.println(server.isAlive());
		}
		
		if(count == 410) {
			if (server != null && server.isAlive())
				centex.setText("Network Established");
			else{
				centex.setText("Network Check Fail.");
			}
		}
		
		if(count == 600) {
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
		if(count > 80) {
			box.draw(context);
			
		}
		
		if(count > 210) {
			
			centex.draw(context);
		}
		
		

	}

}
