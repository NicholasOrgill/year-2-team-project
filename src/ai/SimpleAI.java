package ai;

import java.util.Random;

import songmanager.Beat;
import songmanager.Note;
import songmanager.SongObject;

/**
 * @author Sam Basic AI which recreates the song's array of notes and beats with
 *         differing timings as a basic way to simulate an AI
 */
public class SimpleAI {

	private SongArray[] songArray;
	private int levels;

	public SimpleAI() {

	}

	/**
	 * Initalises the SongArray Object and fills it with the various levels of
	 * AI
	 * 
	 * @param song
	 *            The song to recreate
	 * @param levels
	 *            The number of levels of AI difficulty
	 * @return The SongArray object
	 */
	public SongArray[] recreateArray(SongObject song, int levels) {
		songArray = new SongArray[levels];
		for (int i = 0; i < levels; i++) {
			songArray[i] = new SongArray(recreateNotes(song.getNotes(), i), recreateBeats(song.getBeats(), i));
		}
		return songArray;
	}

	/**
	 * Creates an array of Note timings for the AI to play
	 * 
	 * @param notes
	 *            The array of notes in the song
	 * @param currentLevel
	 *            The current level of AI to simulate
	 * @return The array of timings that the AI will play
	 */
	private Note[] recreateNotes(Note[] notes, int currentLevel) {
		Note[] newNotes = new Note[notes.length];
		Random rand = new Random();
		for (int i = 0; i < notes.length; i++) {
			// Generates new beat timing with +/- 25 ms on the original timing
			// (multiplied by the current level)
			int newTime = notes[i].getTime() + rand.nextInt(50 * currentLevel + 1)
					- rand.nextInt(50 * currentLevel + 1);
			// Creates a new note with the new timing, same sustain and same
			// buttons
			newNotes[i] = new Note(newTime, notes[i].getSustain(), notes[i].getButtons());
		}
		return newNotes;
	}

	/**
	 * Creates an array of beat timings for the AI to play
	 * 
	 * @param beats
	 *            The array of beats in the song
	 * @param currentLevel
	 *            The current level of AI to simulate
	 * @return The array of timings that the AI will play
	 */
	private Beat[] recreateBeats(Beat[] beats, int currentLevel) {
		Beat[] newBeats = new Beat[beats.length];
		Random rand = new Random();
		for (int i = 0; i < beats.length; i++) {
			// Generates new beat timing with +/- 25 ms on the original timing
			// (multiplied by the current level)
			int newTime = beats[i].getTime() + rand.nextInt(50 * currentLevel + 1)
					- rand.nextInt(50 * currentLevel + 1);
			// Creates a new note with the new timing and same measure
			newBeats[i] = new Beat(newTime, beats[i].getMeasure());
		}
		return newBeats;
	}

	/**
	 * @return The number of levels of AI
	 */
	public int getLevels() {
		return levels;
	}

	/**
	 * @return The array of beats and notes at different AI levels
	 */
	public SongArray[] getSongArray() {
		return songArray;
	}
}
