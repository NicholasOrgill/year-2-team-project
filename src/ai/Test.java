package ai;

import songmanager.SongFileProcessor;
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
		SongFileProcessor reader = new SongFileProcessor();
		
		SongObject song = reader.readSongObjectFromXML("src/songmanager/songfile.xml");
		SimpleAI ai = new SimpleAI();
		SongArray[] songArray = ai.recreateArray(song, 10);
	
		System.out.println(song.getBeats()[0].getTime());
		System.out.println(songArray[9].getBeats()[0].getTime());
	}

}
