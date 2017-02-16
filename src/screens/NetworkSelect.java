package screens;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import engine.GameObject;
import engine.Screen;
import input.InputHandler;
import network.Client.ClientResolve;
import network.Client.Network;
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
			centex.setText("Checking Network...");
			String hostname = "localhost";
			String name = "Client";
			
			
			
			if(networkrun) {
				Network n = new Network(hostname,name);
				
				
				//start receive test
				(new ClientResolve(n)).start();
						
				//send ant input from user to server
				try{
					BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
					String userInput;
					n.send("NETCHECK");
				}catch (Exception e) {
					System.err.println(e.getMessage());
				}	
			}
			
		}
		
		if(count == 410) {
			centex.setText("Network Check Fail.");
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
