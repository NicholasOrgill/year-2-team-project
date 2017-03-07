package songmanager;

import java.awt.image.BufferedImage;

/**
 * Represents the contents of a song file.
 * @author Alex
 *
 */
public class SongFile {

	private BufferedImage coverArt;
	private String audioInputPath;
	private SongObject song;
	
	public SongFile(BufferedImage coverArt, String audioInputPath, String notesFilePath) {
		this.coverArt = coverArt;
		this.audioInputPath = audioInputPath;
		
		SongFileProcessor processor = new SongFileProcessor();
		this.song = processor.readSongObjectFromXML(notesFilePath);
	}

	/**
	 * Returns a buffered image containing the cover art of the song
	 */
	public BufferedImage getCoverArt() {
		return coverArt;
	}
	
	/**
	 * Returns the file path to the audio file of the song
	 */
	public String getAudioInputPath() {
		return audioInputPath;
	}
	
	/**
	 * Returns the object containing the notes and metadata of the song
	 */
	public SongObject getSong() {
		return song;
	}
}
