package songmanager;

/**
 * Represents a note, beat marker or chord.
 * @author Alex
 *
 */
public class Note {

	private int time, sustain, measure;
	private boolean beat;
	private int[] buttons;
	
	/**
	 * Creates a measure (beat marker, with no actual note or chord)
	 * @param time Time marker (ms)
	 * @param measure Bar number
	 */
	public Note(int time, int measure) {
		//Set values
		this.time = time;
		this.measure = measure;
		this.beat = true;
		
		//Error values
		buttons = new int[0];
		sustain = -1;
	}
	
	/**
	 * Create a note or chord
	 * @param time Time marker (ms)
	 * @param sustain Sustain length (ms)
	 * @param buttons Note buttons
	 */
	public Note(int time, int sustain, int[] buttons) {
		//Set Values
		this.time = time;
		this.beat = false;
		this.sustain = sustain;
		this.buttons = buttons;
		
		//Error values
		this.measure = 0;
	}
	
	/**
	 * Gets the time a note is place
	 * @return Time marker (ms)
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * Gets the length of time a note should be held
	 * @return Sustain length (ms)
	 */
	public int getSustain() {
		return sustain;
	}
	
	/**
	 * Returns the set of buttons which should be pressed for a note or chord
	 * @return Array of buttons (values 0-3)
	 */
	public int[] getButtons() {
		return buttons;
	}
	
	/**
	 * Returns the bar number of a beat
	 * -1 means it is a beat which is not a new bar
	 * @return Beat marker
	 */
	public int getMeasure() {
		return measure;
	}
	
	/**
	 * Returns whether a note is a beat or not
	 * @return True for beat, false for note/chord
	 */
	public boolean getBeat() {
		return beat;
	}
}
