package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import input.InputHandler;
import sprites.ImageSprite;
import utils.ColorPack;
import utils.ImageLoader;

public class StartScreen extends Screen {
	int counter = 0;
	private ImageSprite image;
	
	@Override
	public void keyPressed(int key) {
		System.out.println("on" + key);
		if(key == InputHandler.PLAYKEY0) {
			setNextScreen(new TitleScreen(getGameObject()));
			getGameObject().getOverlay().getMiddleBottom().setText(" ");
			moveScreen();
		}
	}
	
	@Override
	public void keyReleased(int key) {
		System.out.println("off" + key);
	}
	
	public StartScreen(GameObject gameObject) {
		super(gameObject);
		setNextScreen(new TitleScreen(gameObject));
		
		image = new ImageSprite(getScreenWidth() / 2, (int)(getScreenHeight() * 0.4), ImageLoader.loadImageFromResource("src/res/images/konami_logo.png"));
		image.setScreenSize(getScreenWidth(), getScreenHeight());
		image.setSize(0.7f);
		image.setOpacity(0);
	}

	@Override
	public void update() {
		counter++;
		if(counter == 200) {
			image.fadeIn();
			
		}
		
		if(counter == 500) {
			image.impress();
			image.fadeOut();
		}
		if(counter == 600) {
			moveScreen();
		}
		
		image.setScreenSize(getScreenWidth(), getScreenHeight());
		image.update();
		
	}

	@Override
	public void draw(Graphics context) {
		// Draw in the background color
		context.setColor(ColorPack.WHITE);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());
	
		image.draw(context);
	}


}
