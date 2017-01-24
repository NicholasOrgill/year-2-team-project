package songmanager;

/*
 * 	- title
	- songLength
	- startBeat
	- averageTempo
	- artistName
	- ebeats (+count)
	- - ebeat (+time, measure)
	- notes (+count)
	- - note (+time,string,sustain)
	- chords (+count)
	- - chord (+time,chordId)
	- - - chordNote (+string)
	- handShape (+chordId,endTime,startTime)
 */

/**
 * Holds all the notes, beats and metadata for a song
 * @author Alex
 *
 */
public class SongObject {

	private String title, artist;
	private int songLength, averageTempo, beatCount, noteCount;
	private Note[] notes, beats;
	
	/**
	 * Creates a new song object
	 * @param title
	 * @param artist
	 * @param songLength Length of the song (ms)
	 * @param averageTempo Beats per minute
	 * @param beatCount Number of beats
	 * @param noteCount Number of notes
	 * @param notes Array of notes
	 * @param beats Array of beats
	 */
	public SongObject(String title, String artist, int songLength, int averageTempo, int beatCount, int noteCount, Note[] notes, Note[] beats) {
		this.title = title;
		this.artist = artist;
		this.songLength = songLength;
		this.averageTempo = averageTempo;
		this.beatCount = beatCount;
		this.noteCount = noteCount;
		this.notes = notes;
		this.beats = beats;
	}
	
	/**
	 * Gets the title of the song
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Gets the artist of the song
	 */
	public String getArtist() {
		return artist;
	}
	
	/**
	 * Gets the length of the song (ms)
	 */
	public int getSongLength() {
		return songLength;
	}
	
	/**
	 * Gets the average tempo of the song
	 */
	public int getAverageTempo() {
		return averageTempo;
	}
	
	/**
	 * Gets the number of beats in the song
	 */
	public int getBeatCount() {
		return beatCount;
	}
	
	/**
	 * Gets the number of notes in the song
	 */
	public int getNoteCount() {
		return noteCount;
	}
	
	/**
	 * Gets the array of all notes in the song
	 */
	public Note[] getNotes() {
		return notes;
	}
	
	/**
	 * Gets the array of all beats in the song
	 */
	public Note[] getBeats() {
		return beats;
	}
}