package songmanager;

/**
 * Represents a note, beat marker or chord.
 * @author Alex
 *
 */
public class Note {

	private int time, sustain;
	private int[] buttons;
	
	/**
	 * Create a note or chord
	 * @param time Time marker (ms)
	 * @param sustain Sustain length (ms)
	 * @param buttons Note buttons
	 */
	public Note(int time, int sustain, int[] buttons) {
		this.time = time;
		this.sustain = sustain;
		this.buttons = buttons;
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
}
