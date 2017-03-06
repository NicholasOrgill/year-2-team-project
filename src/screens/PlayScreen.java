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
import sprites.NoteSprite;
import sprites.PlaySprite;
import sprites.SystemTextCenter;
import utils.ColorPack;
/**
 * 
 * @author Nicholas Orgill
 *
 */
public class PlayScreen extends Screen {
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
//	private NoteSprite[] noteSprite2;
	
	SongArray[] songArray;
	
	@Override
	public void keyPressed(int key) {
		keys[key] = true;
		System.out.println("on" + key);
		playSprite.push(key);
	}
	
	@Override
	public void keyReleased(int key) {
		keys[key] = false;
		System.out.println("off" + key);
		playSprite.unpush(key);
	}
	/**
	 * Checks if one array is contained within another
	 * @param a1 The current keys pressed
	 * @param a2 The note's keys
	 * @return if a1 is contained within a2
	 */
	public boolean noteArrayContained(boolean[] userKeys, boolean[] noteKeys) {
		for(int i = 0; i < 4; i++) {
			if (!userKeys[i] && noteKeys[i])
				return false;
		}
		return true;
	}
	
	public void addScoreHelper(Note note, boolean status) {
		if (noteArrayContained(keys, note.getButtons())){
			int time = note.getTime();
			int diff = Math.abs(time - count);
			if (diff <= getGameObject().PERFECT) {
				System.out.println("Perfect!");
				textSprite.setText("Perfect!");
				score += 100;
				note.setHeld(true);
				sendScore(100);

			} else if (diff <= getGameObject().EXCELLENT) {
				System.out.println("Excellent!");
				textSprite.setText("Excellent!");
				score += 75;
				note.setHeld(true);
				sendScore(75);

			} else if (diff <= getGameObject().GOOD) {
				System.out.println("Good!");
				textSprite.setText("Good!");
				score += 50;
				note.setHeld(true);
				sendScore(50);
			} else if (diff <= getGameObject().OKAY) {
				System.out.println("Okay!");
				textSprite.setText("Okay!");
				score += 25;
				note.setHeld(true);
				sendScore(25);
			} else {
				System.out.println("Bad!");
				textSprite.setText("Bad!");
			}
			textScore.setText("Score: " + score);
			if(status) {
				for (NoteSprite sprite : Pnotes.get(0).getGraphicalNotes()) {
					sprite.remove();
				}
				Pnotes.remove(0);
			}
		}
	}
	
	public void addScore() {
		Note note = Pnotes.get(0);
		if(note.getSustain() > 0) {
			if(note.isHeld()){
				score+=5;
			} else {
				addScoreHelper(note, false);
			}
		} else {
			addScoreHelper(note, true);
		}
	}
	
	public PlayScreen(GameObject gameObject) {
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
		else if(count == 0) {
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
//			noteSprite2 = new NoteSprite[note2.length];
			
			/*for(int i = 0 ; i < beat.length ; i++) {
				barSprite[i] = new BarSprite((int)(getScreenWidth() / 2), (count - song.getSongLength()) + beat[i].getTime(), 0, 0);
			}
			
			for(int i = 0 ; i < note.length ; i++) {
				noteSprite[i] = new NoteSprite((int)(getScreenWidth() / 2), (count - song.getSongLength()) + note[i].getTime(), 0, 0, note[i].getButtons(), note[i].getSustain());
			}*/
			
			for(int i = 0 ; i < beat.length ; i++) {
				barSprite[i] = new BarSprite((int)(getScreenWidth() / 2), lineY - beat[i].getTime(), 0, 0);
			}
			
			for(int i = 0 ; i < notes.length ; i++) {
				noteSprite[i] = new NoteSprite((int)(getScreenWidth() / 2), lineY - notes[i].getTime(), 0, 0, notes[i].getButtons(), notes[i].getSustain(), 0.5);
				notes[i].addNoteSprite(noteSprite[i]);
			}
			

			for(int i = 0 ; i < note2.length ; i++) {
//				noteSprite2[i] = new NoteSprite((int)(getScreenWidth() / 2), lineY - note2[i].getTime(), 0, 0, note2[i].getButtons(), note2[i].getSustain(), 0.5);
			}
		}
		
		if(!Pnotes.isEmpty()) {
			Note tempNote = Pnotes.get(0);
			int yPos = lineY - (tempNote.getTime() - count);
			if(yPos > 600) {
				for (NoteSprite sprite : Pnotes.get(0).getGraphicalNotes()) {
					sprite.remove();
				}
				Pnotes.remove(0);
			}
		}
		
		/*for(int i = 0 ; i < beat.length ; i++) {
			barSprite[i].setY((count - song.getSongLength()) + beat[i].getTime());
			barSprite[i].update();
		}
		
		for(int i = 0 ; i < note.length ; i++) {
			noteSprite[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSprite[i].update();
			noteSprite[i].setY((count - song.getSongLength()) + note[i].getTime());
		}*/
		
		for(int i = 0 ; i < beat.length ; i++) {
			barSprite[i].setY(lineY - (beat[i].getTime() - count));
			barSprite[i].update();
		}
		
		for(int i = 0 ; i < notes.length ; i++) {
			noteSprite[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSprite[i].update();
			noteSprite[i].setY(lineY - (notes[i].getTime() - count));
			
//			noteSprite2[i].setScreenSize(getScreenWidth(), getScreenHeight());
//			noteSprite2[i].update();
//			noteSprite2[i].setY(lineY - (note2[i].getTime() - count));
			
			if(noteSprite[i].getY() == lineY) {
				//textSprite.setText("HOLD: " + audio.getPlayingTimer().getTimeInMill());
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
		
		//System.out.println(audio.getPlayingTimer().getTimeInMill());
		count = (int) (audio.getPlayingTimer().getTimeInMill());
		if (!Pnotes.isEmpty()) addScore();
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
			if (!noteSprite[i].isRemoved()) noteSprite[i].draw(context);
//			noteSprite2[i].setAI();
//			noteSprite2[i].draw(context);
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
	}
	
	public void sendScore(int _score){
		
		if (getGameObject().isServer()){
			getGameObject().getServer().inputMessage("SCOR:"+_score);
		}else{
			getGameObject().getNetwork().send("SCOR:"+_score);
		}
	}
}
