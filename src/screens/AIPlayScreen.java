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

public class AIPlayScreen extends Screen {
	private SongFileProcessor reader;
	private SongObject song;
	private Beat[] beat;
	private Note[] notes;
	private Note[] AINotes;
	private ArrayList<Note> Pnotes;
	int score = 0;
	int aiScore = 0;
	boolean[] keys = { false, false, false, false };
	final int MAX_NOTE_SCORE = 200;
	final int MAX_NOTE_RANGE = 100;
	private ArrayList<Note> ANotes;

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

	private int aiLevel = 0;

	SongArray[] songArray;

	@Override
	public void keyPressed(int key) {
		keys[key] = true;
		System.out.println("on" + key);
		playSpriteLeft.push(key);
	}

	@Override
	public void keyReleased(int key) {
		keys[key] = false;
		System.out.println("off" + key);
		playSpriteLeft.unpush(key);
	}

	/**
	 * Checks if one array is contained within another
	 * 
	 * @param userKeys
	 *            The current keys pressed
	 * @param noteKeys
	 *            The note's keys
	 * @return if userKeys is contained within noteKeys
	 */
	public boolean noteArrayContained(boolean[] userKeys, boolean[] noteKeys) {
		for (int i = 0; i < 4; i++) {
			if (!userKeys[i] && noteKeys[i])
				return false;
		}
		return true;
	}

	public void addScoreHelper(Note note, boolean status, SystemTextCenter text, SystemTextCenter scoreText, boolean player, ArrayList<Note> notes) {
		if (noteArrayContained(keys, note.getButtons()) || player == false) {
			int time = note.getTime();
			int diff = Math.abs(time - count);
			if (diff <= getGameObject().PERFECT) {
				System.out.println("Perfect!");
				text.setText("Perfect!");
				if(player) score += 100; else aiScore += 100;
				note.setHeld(true);
			} else if (diff <= getGameObject().EXCELLENT) {
				System.out.println("Excellent!");
				text.setText("Excellent!");
				if(player) score += 75; else aiScore += 75;
				note.setHeld(true);
			} else if (diff <= getGameObject().GOOD) {
				System.out.println("Good!");
				text.setText("Good!");
				if(player) score += 50; else aiScore += 50;
				note.setHeld(true);
			} else if (diff <= getGameObject().OKAY) {
				System.out.println("Okay!");
				text.setText("Okay!");
				if(player) score += 25; else aiScore += 25;
				note.setHeld(true);
			} else {
				System.out.println("Bad!");
				text.setText("Bad!");
			}
			if(player) scoreText.setText("Score: " + score); else scoreText.setText("Score: " + aiScore); 
			if (status) {
				for (NoteSprite sprite : notes.get(0).getGraphicalNotes()) {
					sprite.remove();
				}
				notes.remove(0);
			}
			
		}
	}

	public void addScore(ArrayList<Note> p, SystemTextCenter text, SystemTextCenter scoreText, boolean player) {
		Note note = p.get(0);
		if (note.getSustain() > 0) {
			if (note.isHeld()) {
				if(player) score += 5; else aiScore += 5;
				System.out.println("Still held down!");
			} else {
				addScoreHelper(note, false, text, scoreText, player, p);
			}
		} else {
			addScoreHelper(note, true, text, scoreText, player, p);
		}
	}

	public AIPlayScreen(GameObject gameObject) {
		super(gameObject);
		textAILevel = new SystemTextCenter(getScreenWidth() / 2, getScreenHeight() - 60, "Game AI: Expert");
		textGameMode = new SystemTextCenter(getScreenWidth() / 2, getScreenHeight() - 40, "Single Player");

		leftText = new SystemTextCenter((int) (getScreenWidth() * 0.25), getScreenHeight() - 80, " ");
		leftScore = new SystemTextCenter((int) (getScreenWidth() * 0.25), getScreenHeight() - 60, " ");

		rightText = new SystemTextCenter((int) (getScreenWidth() * 0.75), getScreenHeight() - 80, " ");
		rightScore = new SystemTextCenter((int) (getScreenWidth() * 0.75), getScreenHeight() - 60, " ");

		playSpriteLeft = new PlaySprite(0, 0, 0, 0, 0.25);
		playSpriteRight = new PlaySprite(0, 0, 0, 0, 0.75);
	}

	@Override
	public void update() {

		int lineY = (int) Math.round(getScreenHeight() * 0.8);

		if (audio.getAudioPlayer().playCompleted) {
			getGameObject().setP1Score(score);
			getGameObject().setP2Score(aiScore);
			setNextScreen(new EndScreen(getGameObject()));
			moveScreen();
		} else if (count == 0) {
			audio.playBack("src/songmanager/Tetris.wav");
			reader = new SongFileProcessor();
			song = reader.readSongObjectFromXML("src/songmanager/songfile.xml");
			beat = song.getBeats();
			notes = song.getNotes();
			Pnotes = new ArrayList<Note>(Arrays.asList(notes));

			SimpleAI ai = new SimpleAI();
			songArray = ai.recreateArray(song, 10);

			AINotes = songArray[aiLevel].getNotes();
			barSpriteLeft = new BarSprite[beat.length];
			barSpriteRight = new BarSprite[beat.length];
			noteSpriteLeft = new NoteSprite[notes.length];
			noteSpriteRight = new NoteSprite[notes.length];
			noteSpriteAI = new NoteSprite[AINotes.length];
			ANotes = new ArrayList<Note>(Arrays.asList(notes));

			/*
			 * for(int i = 0 ; i < beat.length ; i++) { barSprite[i] = new
			 * BarSprite((int)(getScreenWidth() / 2), (count -
			 * song.getSongLength()) + beat[i].getTime(), 0, 0); }
			 * 
			 * for(int i = 0 ; i < note.length ; i++) { noteSprite[i] = new
			 * NoteSprite((int)(getScreenWidth() / 2), (count -
			 * song.getSongLength()) + note[i].getTime(), 0, 0,
			 * note[i].getButtons(), note[i].getSustain()); }
			 */

			for (int i = 0; i < beat.length; i++) {
				barSpriteLeft[i] = new BarSprite((int) (getScreenWidth() * 0.25), lineY - beat[i].getTime(), 0, 0);
				barSpriteRight[i] = new BarSprite((int) (getScreenWidth() * 0.75), lineY - beat[i].getTime(), 0, 0);
			}

			for (int i = 0; i < notes.length; i++) {
				noteSpriteLeft[i] = new NoteSprite((int) (getScreenWidth() * 0.25), lineY - notes[i].getTime(), 0, 0,
						notes[i].getButtons(), notes[i].getSustain(), 0.25, getGameObject().getSpeed());
				noteSpriteRight[i] = new NoteSprite((int) (getScreenWidth() * 0.75), lineY - notes[i].getTime(), 0, 0,
						notes[i].getButtons(), notes[i].getSustain(), 0.75, getGameObject().getSpeed());
			}

			for (int i = 0; i < AINotes.length; i++) {
				noteSpriteAI[i] = new NoteSprite((int) (getScreenWidth() * 0.75), lineY - AINotes[i].getTime(), 0, 0,
						AINotes[i].getButtons(), AINotes[i].getSustain(), 0.75, getGameObject().getSpeed());
			}
		}

		if (!Pnotes.isEmpty())

		{
			Note tempNote = Pnotes.get(0);
			int yPos = lineY - (tempNote.getTime() - count);
			if (yPos > 600) {
				for (NoteSprite sprite : Pnotes.get(0).getGraphicalNotes()) {
					sprite.remove();
				}
				Pnotes.remove(0);
			}
		}

		if (!ANotes.isEmpty())

		{
			Note tempNote = ANotes.get(0);
			int yPos = lineY - (tempNote.getTime() - count);
			if (yPos > 600) {
				for (NoteSprite sprite : ANotes.get(0).getGraphicalNotes()) {
					sprite.remove();
				}
				ANotes.remove(0);
			}
		}

		/*
		 * for(int i = 0 ; i < beat.length ; i++) { barSprite[i].setY((count -
		 * song.getSongLength()) + beat[i].getTime()); barSprite[i].update(); }
		 * 
		 * for(int i = 0 ; i < note.length ; i++) {
		 * noteSprite[i].setScreenSize(getScreenWidth(), getScreenHeight());
		 * noteSprite[i].update(); noteSprite[i].setY((count -
		 * song.getSongLength()) + note[i].getTime()); }
		 */

		for (int i = 0; i < beat.length; i++) {
			barSpriteLeft[i].setY(lineY - (beat[i].getTime() - count));
			barSpriteLeft[i].update();
			barSpriteRight[i].setY(lineY - (beat[i].getTime() - count));
			barSpriteRight[i].update();
		}

		for (int i = 0; i < notes.length; i++) {
			noteSpriteLeft[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSpriteLeft[i].update();
			noteSpriteLeft[i].setY(lineY - (notes[i].getTime() - count));

			noteSpriteRight[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSpriteRight[i].update();
			noteSpriteRight[i].setY(lineY - (notes[i].getTime() - count));

			noteSpriteAI[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSpriteAI[i].update();
			noteSpriteAI[i].setY(lineY - (AINotes[i].getTime() - count));

			if (noteSpriteLeft[i].getY() == lineY) {
				// textSprite.setText("HOLD: " +
				// audio.getPlayingTimer().getTimeInMill());
			}
		}

		textAILevel.setScreenSize(

				getScreenWidth(), getScreenHeight());
		// textSprite.setText(audio.getPlayingTimer().toTimeString());
		textAILevel.update();

		textGameMode.setScreenSize(getScreenWidth(), getScreenHeight());
		// textSprite.setText(audio.getPlayingTimer().toTimeString());
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

		if (!Pnotes.isEmpty()) {
			addScore(Pnotes, leftText, leftScore, true);
		}
		
		if (!ANotes.isEmpty()) {
			if (count > ANotes.get(0).getTime()) {
				addScore(ANotes, rightText, rightScore, false);
			}
		}

	}

/*	private void addAIScore(int count, Note[] notes, Note[] aiNotes) {
		int time = getNoteNumber(ANotes.get(0), notes);
		if (notes[time].getSustain() > 0) {
			addAIScoreHelper(notes, aiNotes, time, false);
			int heldTime = aiNotes[time].getTime() + aiNotes[time].getSustain();
			int trueHeldTime = notes[time].getTime() + notes[time].getSustain();

			if (heldTime > trueHeldTime) {
				aiScore += (0.12 * (trueHeldTime - aiNotes[time].getTime()));
			} else {
				aiScore += (0.12 * (heldTime - notes[time].getTime()));
			}

		} else {
			addAIScoreHelper(notes, aiNotes, time, true);
		}
		ANotes.remove(0);
	}*/

/*	private int getNoteNumber(Note note, Note[] notes) {
		for (int i = 0; i < notes.length; i++) {
			if (note == notes[i]) {
				return i;
			} else {
			}
		}
		return -1;
	}*/

/*	private void addAIScoreHelper(Note[] notes, Note[] aiNotes, int i, boolean status) {
		int noteTime = notes[i].getTime();
		int aiTime = aiNotes[i].getTime();

		int diff = Math.abs(noteTime - aiTime);

		if (diff <= getGameObject().PERFECT) {
			System.out.println("Perfect!");
			rightText.setText("Perfect!");
			aiScore += 100;
		} else if (diff <= getGameObject().EXCELLENT) {
			System.out.println("Excellent!");
			rightText.setText("Excellent!");
			aiScore += 75;
		} else if (diff <= getGameObject().GOOD) {
			System.out.println("Good!");
			rightText.setText("Good!");
			aiScore += 50;
		} else if (diff <= getGameObject().OKAY) {
			System.out.println("Okay!");
			rightText.setText("Okay!");
			aiScore += 25;
		} else {
			System.out.println("Bad!");
			rightText.setText("Bad!");
		}
		rightScore.setText("Score: " + aiScore);
		if (status) {
			for (NoteSprite sprite : Pnotes.get(0).getGraphicalNotes()) {
				sprite.remove();
			}
		}
	}*/

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

		for (int i = 0; i < beat.length; i++) {
			barSpriteLeft[i].draw(context);
			barSpriteRight[i].draw(context);
		}

		for (int i = 0; i < notes.length; i++) {
			if (!noteSpriteLeft[i].isRemoved())
				noteSpriteLeft[i].draw(context);
			if (!noteSpriteRight[i].isRemoved())
				noteSpriteRight[i].draw(context);
			noteSpriteAI[i].setAI();
			noteSpriteAI[i].draw(context);
		}

	}
}
