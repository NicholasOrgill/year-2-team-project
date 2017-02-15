package screens;

import java.awt.Graphics;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import audio.AudioPlayer;
import audio.Player;
import audio.SoundHandler;
import engine.GameObject;
import engine.Screen;
import sprites.PlaySprite;
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
	
	private Player audio = new Player();
	
	private SoundHandler fx = new SoundHandler();
	private String[] fxlist = {"sound_effect_one.wav"};
	private PlaySprite playSprite;
	
	public PlayScreen(GameObject gameObject) {
		super(gameObject);
		textSprite = new SystemText(100, 100, "Game goes here");
		playSprite = new PlaySprite(0, 0, 0, 0);
		
		
		
	}
	
	@Override
	public void update() {
		if(count == 0) {
			audio.playBack("src/res/audio/sound_effect_one.wav");
		}
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.setText(audio.getPlayingTimer().toTimeString());
		textSprite.update();
		
		playSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		playSprite.update();
		
		count++;
	}
	
	@Override
	public void draw(Graphics context) {

		// We use this to draw a dark background
		context.setColor(ColorPack.DARK);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());

		// This is how you draw the sprites
		textSprite.draw(context);
		
		playSprite.draw(context);

	}
}
