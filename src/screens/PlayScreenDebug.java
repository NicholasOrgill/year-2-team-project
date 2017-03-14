package screens;

import java.awt.Color;
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
	boolean[] keys = { false, false, false, false };
	final int MAX_NOTE_SCORE = 200;
	final int MAX_NOTE_RANGE = 200;

	int lineY = (int) Math.round(getScreenHeight() * 0.8);

	private SystemTextCenter textSprite; // An example text sprite
	private SystemTextCenter textScore; // An example text sprite
	private int count = 0; // A variable to count on the screen

	private Player audio = new Player();

	private PlaySprite playSprite;

	private BarSprite[] barSprite;
	private NoteSprite[] noteSprite;
	private NoteSprite[] noteSprite2;

	private int[] scoreQuality = new int[5];

	private ArrayList<NoteHitSprite> hits = new ArrayList<NoteHitSprite>();
	private double speedScale;

	SongArray[] songArray;

	int n = 0;

	public PlayScreenDebug(GameObject gameObject) {
		super(gameObject);
		textSprite = new SystemTextCenter(getScreenWidth() / 2, getScreenHeight() - 100, "Game AI: Easy");
		textScore = new SystemTextCenter(getScreenWidth() / 2, getScreenHeight() - 80, "SinglePlayer");
		playSprite = new PlaySprite(0, 0, 0, 0, 0.5);
	}

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

	public void scoreHelper(int difference) {
		if (difference <= getGameObject().PERFECT) {
			textSprite.setText("Perfect!");
			score+=100;
			scoreQuality[0]++;
		} else if (difference <= getGameObject().EXCELLENT) {
			textSprite.setText("Excellent!");
			score+=75;
			scoreQuality[1]++;
		} else if (difference <= getGameObject().GOOD) {
			textSprite.setText("Good!");
			score+=50;
			scoreQuality[2]++;
		} else if (difference <= getGameObject().OKAY) {
			textSprite.setText("Okay!");
			score+=25;
			scoreQuality[3]++;
		} else {
			textSprite.setText("Bad!");
			scoreQuality[4]++;
		}
		textScore.setText(""+score);
	}

	@Override
	public void update() {
		getGameObject().setSpeed(0.4);
		speedScale = getGameObject().getSpeed();
		if (audio.getAudioPlayer().playCompleted) {
			getGameObject().setP1Score(score);
			getGameObject().setScoreQuality(scoreQuality);
			setNextScreen(new EndScreen(getGameObject()));
			moveScreen();
		}

		if (count == 0) {
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

			for (int i = 0; i < beat.length; i++) {
				barSprite[i] = new BarSprite((int) (getScreenWidth() / 2), lineY - beat[i].getTime(), 0, 0);
			}

			for (int i = 0; i < notes.length; i++) {
				noteSprite[i] = new NoteSprite((int) (getScreenWidth() / 2), lineY - notes[i].getTime(), 0, 0,
						notes[i].getButtons(), notes[i].getSustain(), 0.5, speedScale);
				notes[i].addNoteSprite(noteSprite[i]);
			}
		}

		for (int i = 0; i < beat.length; i++) {
			barSprite[i].setY((int) (lineY - (beat[i].getTime() - count) * speedScale));
			barSprite[i].update();
		}

		for (int i = n; i < notes.length; i++) {
			noteSprite[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSprite[i].update();

			noteSprite[i]
					.setY((int) (lineY - (notes[i].getTime() - count) * speedScale) + (noteSprite[i].getLength() / 3));

			// If the note is in the playing area
			if (noteSprite[i].isRemoved() == false) {
				if (noteSprite[i].getY() >= lineY && noteSprite[i].getY() <= lineY + (3 * noteSprite[i].getHeight())) {
					boolean notesHit = true;
					for (int p = 0; p < notes[p].getButtons().length; p++) {
						if (notes[i].getButtons()[p]) {
							if (keys[p]) {
								hits.add(new NoteHitSprite((playSprite.getX() - playSprite.getWidth() / 2)
										+ (playSprite.getBlockSizeAndGap() / 2) + (p * playSprite.getBlockSizeAndGap()),
										playSprite.getY() + (playSprite.getBlockSize() / 2), playSprite.getBlockSize(),
										playSprite.getBlockSize()));
								keys[p] = false;
							} else {
								notesHit = false;
							}

						}
					}
					if (notesHit) {
						int difference = Math.abs(noteSprite[i].getY() - 60 - lineY);
						scoreHelper(difference);
						noteSprite[i].remove();
					}

				}

				// When the note is finished, increment the start of the array
				if (noteSprite[i].isRemoved() && noteSprite[i].getY() > getScreenHeight()) {
					n++;
				}
			}

		}

		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.update();

		textScore.setScreenSize(getScreenWidth(), getScreenHeight());
		textScore.update();

		playSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		playSprite.update();

		for (NoteHitSprite hit : hits) {
			hit.update();
		}

		count = (int) (audio.getPlayingTimer().getTimeInMill());
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

		for (int i = 0; i < beat.length; i++) {
			barSprite[i].draw(context);
		}

		for (int i = n; i < notes.length; i++) {
			noteSprite[i].draw(context);
			context.drawLine(0, noteSprite[i].getY() - 30, getScreenWidth(), noteSprite[i].getY() - 30);
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

		for (NoteHitSprite hit : hits) {
			hit.draw(context);
		}

		context.setColor(Color.RED);
		context.drawLine(0, lineY, getScreenWidth(), lineY);

		context.setColor(Color.BLUE);
		context.drawLine(0, lineY + 30, getScreenWidth(), lineY + 30);
	}
}