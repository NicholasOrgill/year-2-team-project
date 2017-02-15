package screens;

import java.awt.Graphics;

import audio.Player;
import audio.SoundHandler;
import engine.GameObject;
import engine.Screen;
import songmanager.Beat;
import songmanager.EofRepacker;
import songmanager.Note;
import songmanager.SongObject;
import sprites.BarSprite;
import sprites.NoteSprite;
import sprites.PlaySprite;
import sprites.SystemText;
import utils.ColorPack;
/**
 * 
 * @author Nicholas Orgill
 *
 */
public class PlayScreen extends Screen {
	private EofRepacker reader;
	private SongObject song;
	private Beat[] beat;
	private Note[] note;
	
	private SystemText textSprite; // An example text sprite
	private int count = 0; // A variable to count on the screen
	
	private Player audio = new Player();
	
	private SoundHandler fx = new SoundHandler();
	private String[] fxlist = {"sound_effect_one.wav"};
	private PlaySprite playSprite;
	
	private BarSprite[] barSprite;
	private NoteSprite[] noteSprite;
	
	
	public PlayScreen(GameObject gameObject) {
		super(gameObject);
		textSprite = new SystemText(100, 100, "Game goes here");
		playSprite = new PlaySprite(0, 0, 0, 0);		
	}
	
	@Override
	public void update() {
		
		if(count == 0) {
			//audio.playBack("src/res/audio/sound_effect_one.wav");
			reader = new EofRepacker();
			song = reader.GetSongObjectFromBassFile("src/songmanager/PART REAL_BASS_RS2.xml");
			beat = song.getBeats();
			note = song.getNotes();
			
			
			barSprite = new BarSprite[beat.length];
			noteSprite = new NoteSprite[note.length];
			
			for(int i = 0 ; i < beat.length ; i++) {
				barSprite[i] = new BarSprite((int)(getScreenWidth() / 2), (count - song.getSongLength()) + beat[i].getTime(), 0, 0);
			}
			
			for(int i = 0 ; i < note.length ; i++) {
				noteSprite[i] = new NoteSprite((int)(getScreenWidth() / 2), (count - song.getSongLength()) + note[i].getTime(), 0, 0, note[i].getButtons(), note[i].getSustain());
			}		
			
			
		}
		
		for(int i = 0 ; i < beat.length ; i++) {
			barSprite[i].setY((count - song.getSongLength()) + beat[i].getTime());
			barSprite[i].update();
		}
		
		for(int i = 0 ; i < note.length ; i++) {
			noteSprite[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSprite[i].update();
			noteSprite[i].setY((count - song.getSongLength()) + note[i].getTime());
		}
		
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		//textSprite.setText(audio.getPlayingTimer().toTimeString());
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
		
		for(int i = 0 ; i < beat.length ; i++) {
			barSprite[i].draw(context);
		}
		
		for(int i = 0 ; i < note.length ; i++) {
			noteSprite[i].draw(context);
		}

	}
}
