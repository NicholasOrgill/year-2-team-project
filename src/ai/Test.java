package ai;

import songmanager.EofRepacker;
import songmanager.SongObject;

/**
 * @author Sam Simple Test to see the randomised notes
 */
public class Test {

	public Test() {

	}

	public static void main(String[] args) {
		Test t = new Test();
		t.runTest();
	}

	/**
	 * Creates a SongObject and AI, and prints the value of the timing of the
	 * first beat at the original position and with level 9 AI
	 */
	private void runTest() {
		EofRepacker reader = new EofRepacker();
		
		SongObject song = reader.GetSongObjectFromBassFile("src/songmanager/PART REAL_BASS_RS2.xml");
		SimpleAI ai = new SimpleAI();
		SongArray[] songArray = ai.recreateArray(song, 10);
	
		System.out.println(song.getBeats()[0].getTime());
		System.out.println(songArray[9].getBeats()[0].getTime());
	}

}
