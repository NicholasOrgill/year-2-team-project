package screens;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import engine.GameObject;
import engine.Screen;
import input.InputHandler;
import network.Message;
import network.MessageQueue;
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
			if (getGameObject().isServer()){
				centex.setText("Establising Network...");
				MessageQueue serverInput = new MessageQueue();
				
				Server server = new Server(getGameObject(), serverInput, getGameObject().getP1Name());
				getGameObject().setServer(server);
				server.start();
			}else{
				centex.setText("Connecting Server...");
				Network n = new Network(getGameObject(), getGameObject().getHostname(), getGameObject().getP1Name());
				getGameObject().setNetwork(n);
			}
			
		}
		
		if(count == 410) {
			if (getGameObject().getServer() != null && getGameObject().getServer().isAlive()){
				centex.setText("Network Established");
				Message msg = new Message("READ:");
				getGameObject().getServer().getServerInput().offer(msg);
			}

			else if (getGameObject().getNetwork() != null && getGameObject().isConnected()){
				centex.setText("Connected");
				getGameObject().getNetwork().sendReadyMsg();
			}else {
				centex.setText("Network Check Fail.");				
			}
		}
		
		if(count >= 410 && getGameObject().isReady()){
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
