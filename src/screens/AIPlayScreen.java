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

public class AIPlayScreen extends Screen {
	private SongFileProcessor reader;
	private SongObject song;
	private Beat[] beat;
	private Note[] notes;
	private Note[] AINotes;
	int score = 0;
	int aiScore = 0;
	boolean[] keys = { false, false, false, false };
	final int MAX_NOTE_SCORE = 200;
	final int MAX_NOTE_RANGE = 100;

	int lineY = (int) Math.round(getScreenHeight() * 0.8);

	private SystemTextCenter textAILevel; // An example text sprite
	private SystemTextCenter textGameMode; // An example text sprite
	private int count = 0; // A variable to count on the screen
	private SystemTextCenter leftText;
	private SystemTextCenter leftScore;
	private SystemTextCenter rightText;
	private SystemTextCenter rightScore;
	private SystemTextCenter player1Text;
	private SystemTextCenter player2Text;

	private Player audio = new Player();

	private PlaySprite playSpriteLeft;
	private PlaySprite playSpriteRight;

	private BarSprite[] barSpriteLeft;
	private BarSprite[] barSpriteRight;
	private NoteSprite[] noteSpriteLeft;
	private NoteSprite[] noteSpriteRight;
	private NoteSprite[] noteSpriteAI;

	private int aiLevel = 0;

	private int[] scoreQuality = new int[5];

	private ArrayList<NoteHitSprite> hits = new ArrayList<NoteHitSprite>();
	private ArrayList<NoteHitSprite> aiHits = new ArrayList<NoteHitSprite>();

	private double speedScale = 0.4;

	SongArray[] songArray;

	int n = 0;

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

	public void scoreHelper(int difference, boolean ai) {
		SystemTextCenter text = leftText;
		SystemTextCenter scoreText = leftScore;
		int playerScore = score;
		if (ai) {
			text = rightText;
			scoreText = rightScore;
			playerScore = aiScore;
		}
		if (difference <= getGameObject().PERFECT) {
			text.setText("Perfect!");
			playerScore += 100;
			if (!ai)
				scoreQuality[0]++;
		} else if (difference <= getGameObject().EXCELLENT) {
			text.setText("Excellent!");
			playerScore += 75;
			if (!ai)
				scoreQuality[1]++;
		} else if (difference <= getGameObject().GOOD) {
			text.setText("Good!");
			playerScore += 50;
			if (!ai)
				scoreQuality[2]++;
		} else if (difference <= getGameObject().OKAY) {
			text.setText("Okay!");
			playerScore += 25;
			if (!ai)
				scoreQuality[3]++;
		} else {
			text.setText("Bad!");
			if (!ai)
				scoreQuality[4]++;
		}
		if (ai) {
			aiScore = playerScore;
		} else {
			score = playerScore;
		}
		scoreText.setText("" + playerScore);
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
		
		player1Text = new SystemTextCenter((int) (getScreenWidth() * 0.25), 45, "Player");
		player2Text = new SystemTextCenter((int) (getScreenWidth() * 0.75), 45, "AI");
	}

	@Override
	public void update() {

		int lineY = (int) Math.round(getScreenHeight() * 0.8);

		if (audio.getAudioPlayer().playCompleted) {
			getGameObject().setP1Score(score);
			getGameObject().setP2Score(aiScore);
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

			SimpleAI ai = new SimpleAI();
			songArray = ai.recreateArray(song, 10);

			AINotes = songArray[aiLevel].getNotes();
			barSpriteLeft = new BarSprite[beat.length];
			barSpriteRight = new BarSprite[beat.length];
			noteSpriteLeft = new NoteSprite[notes.length];
			noteSpriteRight = new NoteSprite[notes.length];
			noteSpriteAI = new NoteSprite[AINotes.length];

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

		for (int i = 0; i < beat.length; i++) {
			barSpriteLeft[i].setY((int) (lineY - (beat[i].getTime() - count) * speedScale));
			barSpriteLeft[i].update();
			barSpriteRight[i].setY((int) (lineY - (beat[i].getTime() - count) * speedScale));
			barSpriteRight[i].update();
		}

		for (int i = 0; i < notes.length; i++) {
			noteSpriteLeft[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSpriteLeft[i].update();
			noteSpriteLeft[i].setY((int) (lineY - (notes[i].getTime() - count) * speedScale) + (noteSpriteLeft[i].getLength() / 3));

			noteSpriteRight[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSpriteRight[i].update();
			noteSpriteRight[i].setY((int) (lineY - (notes[i].getTime() - count) * speedScale) + (noteSpriteRight[i].getLength() / 3));
			noteSpriteAI[i].setScreenSize(getScreenWidth(), getScreenHeight());
			noteSpriteAI[i].update();
			noteSpriteAI[i].setY((int) (lineY - (AINotes[i].getTime() - count) * speedScale) + (noteSpriteAI[i].getLength() / 3));

			// Checks if the notes are in the playing area whether they should be removed
			removeSprites(i, noteSpriteLeft, playSpriteLeft, hits, false);
			removeSprites(i, noteSpriteRight, playSpriteRight, aiHits, true);
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
		
		player1Text.setScreenSize(getScreenWidth(), getScreenHeight());
		player1Text.update();
		
		player2Text.setScreenSize(getScreenWidth(), getScreenHeight());
		player2Text.update();

		for (NoteHitSprite hit : hits) {
			hit.update();
		}

		for (NoteHitSprite hit : aiHits) {
			hit.update();
		}

		count = (int) (audio.getPlayingTimer().getTimeInMill());

	}

	private void removeSprites(int i, NoteSprite[] noteSprite, PlaySprite playSprite, ArrayList<NoteHitSprite> hits,
			boolean ai) {
		if (noteSprite[i].isRemoved() == false) {
			if (noteSprite[i].getY() >= lineY && noteSprite[i].getY() <= lineY + (3 * noteSprite[i].getHeight())) {
				boolean notesHit = true;
				for (int p = 0; p < notes[p].getButtons().length; p++) {
					if (notes[i].getButtons()[p]) {
						if (keys[p] || ai) {
							hits.add(new NoteHitSprite((playSprite.getX() - playSprite.getWidth() / 2)
									+ (playSprite.getBlockSizeAndGap() / 2) + (p * playSprite.getBlockSizeAndGap()),
									playSprite.getY() + (playSprite.getBlockSize() / 2), playSprite.getBlockSize(),
									playSprite.getBlockSize()));
							if (!ai)
							keys[p] = false;
						} else {
							notesHit = false;
						}

					}
				}
				if (notesHit) {
					int difference;
					if (ai) {
						difference = Math.abs(notes[i].getTime() - AINotes[i].getTime());
					} else {
						 difference = Math.abs(noteSprite[i].getY() - 60 - lineY);
					}
					scoreHelper(difference, ai);
					noteSprite[i].remove();
					if (ai) {
						noteSpriteAI[i].remove();
					}
				}

			}

			// When the note is finished, increment the start of the array
			if (noteSprite[i].isRemoved() && noteSprite[i].getY() > getScreenHeight()) {
				n++;
			}
		}
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

		for (int i = 0; i < beat.length; i++) {
			barSpriteLeft[i].draw(context);
			barSpriteRight[i].draw(context);
		}

		for (int i = 0; i < notes.length; i++) {
			if (!noteSpriteLeft[i].isRemoved())
				noteSpriteLeft[i].draw(context);
			if (!noteSpriteRight[i].isRemoved())
				noteSpriteRight[i].draw(context);
			if (!noteSpriteAI[i].isRemoved()) {
				noteSpriteAI[i].setAI();
				noteSpriteAI[i].draw(context);
			}
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
		
		player1Text.draw(context);
		player2Text.draw(context);

		for (NoteHitSprite hit : hits) {
			hit.draw(context);
		}

		for (NoteHitSprite hit : aiHits) {
			hit.draw(context);
		}

		context.setColor(Color.RED);
		context.drawLine(0, lineY, getScreenWidth(), lineY);

		context.setColor(Color.BLUE);
		context.drawLine(0, lineY + 30, getScreenWidth(), lineY + 30);
	}
}
