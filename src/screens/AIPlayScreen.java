package screens;

import java.awt.Graphics;

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


public class AIPlayScreen extends Screen {
	private SongFileProcessor reader;
	private SongObject song;
	private Beat[] beat;
	private Note[] note;
	private Note[] AINotes;
	int score = 0;
	final int MAX_NOTE_SCORE = 200;
	final int MAX_NOTE_RANGE = 100;
	
	private SystemTextCenter textAILevel; // An example text sprite
	private SystemTextCenter textGameMode; // An example text sprite
	private int count = 0; // A variable to count on the screen
	private SystemTextCenter leftText;
	private SystemTextCenter leftScore;
	private SystemTextCenter rightText;
	private SystemTextCenter rightScore;
	
	private Player audio = new Player();
	
	private PlaySprite playSpriteLeft; 
	private PlaySprite playSpriteRight;
	
	private BarSprite[] barSpriteLeft;
	private BarSprite[] barSpriteRight;
	private NoteSprite[] noteSpriteLeft;
	private NoteSprite[] noteSpriteRight;
	private NoteSprite[] noteSpriteAI;
	
	SongArray[] songArray;
	
	@Override
	public void keyPressed(int key) {
		for(Note note1 : note) {
			int time = note1.getTime();

			if (time <= count + MAX_NOTE_RANGE && time >= count - MAX_NOTE_RANGE /*&& note1.getButtons().toString().indexOf(key) != -1*/) {
				leftText.setText("NOTE HIT! Score for note: " + (MAX_NOTE_SCORE - Math.abs(time - count)));
				System.out.println("NOTE HIT! Score for note: " + (MAX_NOTE_SCORE - Math.abs(time - count)));
				score += (MAX_NOTE_SCORE - Math.abs(time - count));
				leftScore.setText("Score: " + score);

				break;
			}
		}
		System.out.println("on" + key);
		playSpriteLeft.push(key);

	}
	
	@Override
	public void keyReleased(int key) {
		System.out.println("off" + key);
		playSpriteLeft.unpush(key);
	}
	
	public AIPlayScreen(GameObject gameObject) {
		super(gameObject);
		textAILevel = new SystemTextCenter(getScreenWidth() / 2, getScreenHeight() - 60, "Game AI: Easy");
		textGameMode = new SystemTextCenter(getScreenWidth() / 2, getScreenHeight() - 40, "Single Player");
		
		leftText = new SystemTextCenter((int)(getScreenWidth() * 0.25), getScreenHeight() - 80, " ");
		leftScore = new SystemTextCenter((int)(getScreenWidth() * 0.25), getScreenHeight() - 60, " ");
		
		rightText = new SystemTextCenter((int)(getScreenWidth() * 0.75), getScreenHeight() - 80, " ");
		rightScore = new SystemTextCenter((int)(getScreenWidth() * 0.75), getScreenHeight() - 60, " ");
		
		playSpriteLeft = new PlaySprite(0, 0, 0, 0, 0.25);
		playSpriteRight = new PlaySprite(0,0,0,0,0.75);
	}
	
	@Override
	public void update() {
		
		int lineY = (int)Math.round(getScreenHeight() * 0.8);
		
		if(count == 0) {
			if (score != 0) {
				setNextScreen(new EndScreen(getGameObject()));
				moveScreen();
			} else {
				audio.playBack("src/songmanager/Tetris.wav");
				reader = new SongFileProcessor();
				song = reader.readSongObjectFromXML("src/songmanager/songfile.xml");
				beat = song.getBeats();
				note = song.getNotes();
				
				
				SimpleAI ai = new SimpleAI();
				songArray = ai.recreateArray(song, 10);
				
				AINotes = songArray[0].getNotes();
				barSpriteLeft = new BarSprite[beat.length];
				barSpriteRight = new BarSprite[beat.length];
				noteSpriteLeft = new NoteSprite[note.length];
				noteSpriteRight = new NoteSprite[note.length];
				noteSpriteAI = new NoteSprite[AINotes.length];
				
				/*for(int i = 0 ; i < beat.length ; i++) {
					barSprite[i] = new BarSprite((int)(getScreenWidth() / 2), (count - song.getSongLength()) + beat[i].getTime(), 0, 0);
				}
				
				for(int i = 0 ; i < note.length ; i++) {
					noteSprite[i] = new NoteSprite((int)(getScreenWidth() / 2), (count - song.getSongLength()) + note[i].getTime(), 0, 0, note[i].getButtons(), note[i].getSustain());
				}*/
				
				for(int i = 0 ; i < beat.length ; i++) {
					barSpriteLeft[i] = new BarSprite((int)(getScreenWidth() * 0.25), lineY - beat[i].getTime(), 0, 0);
					barSpriteRight[i] = new BarSprite((int)(getScreenWidth() * 0.75), lineY - beat[i].getTime(), 0, 0);
				}
				
				for(int i = 0 ; i < note.length ; i++) {
					noteSpriteLeft[i] = new NoteSprite((int)(getScreenWidth() * 0.25), lineY - note[i].getTime(), 0, 0, note[i].getButtons(), note[i].getSustain(), 0.25);
					noteSpriteRight[i] = new NoteSprite((int)(getScreenWidth() * 0.75), lineY - note[i].getTime(), 0, 0, note[i].getButtons(), note[i].getSustain(), 0.75);
				}
				
	
				for(int i = 0 ; i < AINotes.length ; i++) {
					noteSpriteAI[i] = new NoteSprite((int)(getScreenWidth() * 0.75), lineY - AINotes[i].getTime(), 0, 0, AINotes[i].getButtons(), AINotes[i].getSustain(), 0.75);
				}
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
			barSpriteLeft[i].setY(lineY - (beat[i].getTime() - count));
			barSpriteLeft[i].update();
			barSpriteRight[i].setY(lineY - (beat[i].getTime() - count));
			barSpriteRight[i].update();
		}
		
		for(int i = 0 ; i < note.length ; i++) {
			noteSpriteLeft[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSpriteLeft[i].update();
			noteSpriteLeft[i].setY(lineY - (note[i].getTime() - count)); 
			
			noteSpriteRight[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSpriteRight[i].update();
			noteSpriteRight[i].setY(lineY - (note[i].getTime() - count));
			
			noteSpriteAI[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSpriteAI[i].update();
			noteSpriteAI[i].setY(lineY - (AINotes[i].getTime() - count));
			
			if(noteSpriteLeft[i].getY() == lineY) {
				//textSprite.setText("HOLD: " + audio.getPlayingTimer().getTimeInMill());
			}
		}
		
		textAILevel.setScreenSize(getScreenWidth(), getScreenHeight());
		//textSprite.setText(audio.getPlayingTimer().toTimeString());
		textAILevel.update();
		
		textGameMode.setScreenSize(getScreenWidth(), getScreenHeight());
		//textSprite.setText(audio.getPlayingTimer().toTimeString());
		textGameMode.update();
		
		leftText.setScreenSize(getScreenWidth(), getScreenHeight());
		leftText.update();
		
		leftScore.setScreenSize(getScreenWidth(), getScreenHeight());
		leftScore.update();
		
		rightText.setScreenSize(getScreenWidth(), getScreenHeight());
		rightText.update();
		
		rightScore.setScreenSize(getScreenWidth(), getScreenHeight());
		rightScore.update();
		
		playSpriteLeft.setScreenSize(getScreenWidth(), getScreenHeight());
		playSpriteLeft.update();
		
		playSpriteRight.setScreenSize(getScreenWidth(), getScreenHeight());
		playSpriteRight.update();
		
		System.out.println(audio.getPlayingTimer().getTimeInMill());
		count = (int) (audio.getPlayingTimer().getTimeInMill());

		
		
	}
	
	@Override
	public void draw(Graphics context) {

		// We use this to draw a dark background
		context.setColor(ColorPack.DARK);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());

		// This is how you draw the sprites
		textAILevel.draw(context);
		textGameMode.draw(context);
		
		leftText.draw(context);
		leftScore.draw(context);
		
		rightText.draw(context);
		rightScore.draw(context);
		
		playSpriteLeft.draw(context);
		playSpriteRight.draw(context);
		
		for(int i = 0 ; i < beat.length ; i++) {
			barSpriteLeft[i].draw(context);
			barSpriteRight[i].draw(context);
		}
		
		for(int i = 0 ; i < note.length ; i++) {
			noteSpriteLeft[i].draw(context);
			noteSpriteRight[i].draw(context);
			noteSpriteAI[i].setAI();
			noteSpriteAI[i].draw(context);
		}

	}
}
