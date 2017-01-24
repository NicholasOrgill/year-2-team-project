package songmanager;

public class Note {

	private int time, sustain;
	private int[] string;
	
	public Note(int time, int sustain, int[] string) {
		this.time = time;
		this.sustain = sustain;
		this.string = string;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getSustain() {
		return sustain;
	}
	
	public int[] getString() {
		return string;
	}
}
