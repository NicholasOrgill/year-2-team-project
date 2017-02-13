package ai;

import java.util.Random;

import songmanager.Note;

public class RecreateArray {
	
	private SongArray[] songArray;
	private int levels;

	public RecreateArray(Note[] notes, Note[] beats, int levels) {
		this.songArray = new SongArray[levels];
		this.levels = levels;
		for (int i = 0; i < levels; i ++) {
			songArray[i] = new SongArray(recreateNotes(notes, i),recreateBeats(beats, i) );
		}
	}

	private Note[] recreateNotes(Note[] notes, int currentLevel) {
		Note[] newNotes = new Note[notes.length];
		Random rand = new Random();
		for (int i = 0; i < notes.length; i++) {
			//Generates new beat timing with +/- 5 ms on the original timing (multiplied by the current level)
			int newTime = notes[i].getTime() + rand.nextInt(10 * currentLevel) - 5 * currentLevel;
			//Creates a new note with the new timing and same measure
			newNotes[i] = new Note(newTime, notes[i].getMeasure());
		}
		return newNotes;
	}
	
	private Note[] recreateBeats(Note[] beats, int currentLevel) {
		Note[] newBeats = new Note[beats.length];
		Random rand = new Random();
		for (int i = 0; i < beats.length; i++) {
			//Generates new beat timing with +/- 5 ms on the original timing (multiplied by the current level)
			int newTime = beats[i].getTime() + rand.nextInt(10 * currentLevel) - 5 * currentLevel;
			//Creates a new note with the new timing and same measure
			newBeats[i] = new Note(newTime, beats[i].getMeasure());
		}
		return newBeats;
	}
	
	public int getLevels() {
		return levels;
	}

	public SongArray[] getSongArray() {
		return songArray;
	}
}
