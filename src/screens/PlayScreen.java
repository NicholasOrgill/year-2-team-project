package screens;

import java.awt.Graphics;

import audio.*;
import engine.GameObject;
import engine.Screen;
import sprites.SystemText;
import utils.ColorPack;
/**
 * 
 * @author Nicholas Orgill
 *
 */
public class PlayScreen extends Screen {
	
	private SystemText textSprite; // An example text sprite
	private int count = 0; // A variable to count on the screen
	
	private Player audio;
	private SoundHandler fx;
	private String[] fxlist;

	public PlayScreen(GameObject gameObject) {
		super(gameObject);
		textSprite = new SystemText(100, 100, "Game goes here");
		fx.fillEffects(fxlist);
	}
	
	@Override
	public void update() {
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.update();
	}
	
	@Override
	public void draw(Graphics context) {

		// We use this to draw a dark background
		context.setColor(ColorPack.DARK);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());

		// This is how you draw the sprites
		textSprite.draw(context);

	}
}
