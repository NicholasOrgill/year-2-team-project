package screens;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;

import ai.SimpleAI;
import ai.SongArray;
import audio.Player;
import engine.GameObject;
import engine.Screen;
import songmanager.Beat;
import songmanager.Note;
import songmanager.SongFileProcessor;
import songmanager.SongObject;
import sprites.BarSprite;
import sprites.NoteHitSprite;
import sprites.NoteSprite;
import sprites.PlaySprite;
import sprites.SystemTextCenter;
import utils.ColorPack;
/**
 * 
 * @author Nicholas Orgill
 *
 */
public class PlayScreenDebug extends Screen {
	private SongFileProcessor reader;
	private SongObject song;
	private Beat[] beat;
	private Note[] notes;
	private ArrayList<Note> Pnotes;
	private Note[] note2;
	int score = 0;
	boolean[] keys = {false, false, false, false};
	final int MAX_NOTE_SCORE = 200;
	final int MAX_NOTE_RANGE = 200;
	
	private SystemTextCenter textSprite; // An example text sprite
	private SystemTextCenter textScore; // An example text sprite
	private int count = 0; // A variable to count on the screen
	
	private Player audio = new Player();
	
	private PlaySprite playSprite; 
	
	private BarSprite[] barSprite;
	private NoteSprite[] noteSprite;
	private NoteSprite[] noteSprite2;
	
	private ArrayList<NoteHitSprite> hits = new ArrayList<NoteHitSprite>();
	
	private double speedScale = 0.4;
	
	SongArray[] songArray;
		
	public PlayScreenDebug(GameObject gameObject) {
		super(gameObject);
		textSprite = new SystemTextCenter(getScreenWidth() / 2, getScreenHeight() - 100, "Game AI: Easy");
		textScore = new SystemTextCenter(getScreenWidth() / 2, getScreenHeight() - 80, "SinglePlayer");
		playSprite = new PlaySprite(0, 0, 0, 0, 0.5);		
	}
	
	@Override
	public void update() {
		
		int lineY = (int)Math.round(getScreenHeight() * 0.8);
		
		if(audio.getAudioPlayer().playCompleted) {
			getGameObject().setP1Score(score);
			setNextScreen(new EndScreen(getGameObject()));
			moveScreen();
		}
		
		if(count == 0) {
			audio.playBack("src/songmanager/Tetris.wav");
			reader = new SongFileProcessor();
			song = reader.readSongObjectFromXML("src/songmanager/songfile.xml");
			beat = song.getBeats();
			notes = song.getNotes();
			Pnotes = new ArrayList<Note>(Arrays.asList(notes));
			SimpleAI ai = new SimpleAI();
			songArray = ai.recreateArray(song, 10);
			
			note2 = songArray[6].getNotes();
			barSprite = new BarSprite[beat.length];
			noteSprite = new NoteSprite[notes.length];
			noteSprite2 = new NoteSprite[note2.length];
		
			
			for(int i = 0 ; i < beat.length ; i++) {
				barSprite[i] = new BarSprite((int)(getScreenWidth() / 2), lineY - beat[i].getTime(), 0, 0);
			}
			
			for(int i = 0 ; i < notes.length ; i++) {
				noteSprite[i] = new NoteSprite((int)(getScreenWidth() / 2), lineY - notes[i].getTime(), 0, 0, notes[i].getButtons(), notes[i].getSustain(), 0.5);
				notes[i].addNoteSprite(noteSprite[i]);
			}
		}
		
				
		for(int i = 0 ; i < beat.length ; i++) {
			barSprite[i].setY((int)(lineY - (beat[i].getTime() - count) * speedScale));
			barSprite[i].update();
		}
		
		for(int i = 0 ; i < notes.length ; i++) {
			noteSprite[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSprite[i].update();
			noteSprite[i].setY((int)(lineY - (notes[i].getTime() - count) * speedScale));
			
			if(noteSprite[i].getY() >= lineY + 2 * noteSprite[i].getLength() && noteSprite[i].isRemoved() == false) {
				hits.add(new NoteHitSprite((playSprite.getX() - playSprite.getWidth() / 2) + (playSprite.getBlockSizeAndGap() / 2), (getScreenHeight() / 2), playSprite.getBlockSize(), playSprite.getBlockSize()));
				//hits.add(new NoteHitSprite(getScreenWidth() / 2, getScreenHeight() / 2, 40, 40));
				textSprite.setText("HOLD: " + audio.getPlayingTimer().getTimeInMill());
				noteSprite[i].remove();
			}
		}
		
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		//textSprite.setText(audio.getPlayingTimer().toTimeString());
		textSprite.update();
		
		textScore.setScreenSize(getScreenWidth(), getScreenHeight());
		//textSprite.setText(audio.getPlayingTimer().toTimeString());
		textScore.update();
		
		playSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		playSprite.update();
		
		for(NoteHitSprite hit : hits) {
			hit.update();
		}
		
		//System.out.println(audio.getPlayingTimer().getTimeInMill());
		count = (int) (audio.getPlayingTimer().getTimeInMill());
		//count+=5;
	}
	
	@Override
	public void draw(Graphics context) {

		// We use this to draw a dark background
		context.setColor(ColorPack.DARK);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());

		// This is how you draw the sprites
		textSprite.draw(context);
		textScore.draw(context);
		
		playSprite.draw(context);
		
		for(int i = 0; i < beat.length; i++) {
			barSprite[i].draw(context);
		}
		
		for(int i = 0; i < notes.length; i++) {
			noteSprite[i].draw(context);
		}
		
		// Initial box for things to go in
		context.setColor(ColorPack.DARK);
		context.fillRect(10, 10, getScreenWidth() / 2 - 20, 70);
		context.setColor(ColorPack.FADEDWHITE);
		context.drawRect(10, 10, getScreenWidth() / 2 - 20, 70);
		
		
		// Secondary box
		context.setColor(ColorPack.DARK);
		context.fillRect(10 + getScreenWidth() / 2 - 10, 10, getScreenWidth() / 2 - 20, 70);
		context.setColor(ColorPack.FADEDWHITE);
		context.drawRect(10 + getScreenWidth() / 2 - 10, 10, getScreenWidth() / 2 - 20, 70);
		
		for(NoteHitSprite hit : hits) {
			hit.draw(context);
		}
	}
}
