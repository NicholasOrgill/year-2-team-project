package songmanager;

import java.awt.image.BufferedImage;

public class SongFile {

	private BufferedImage coverArt;
	private String audioInputPath;
	private SongObject song;
	
	public SongFile(BufferedImage coverArt, String audioInputPath, SongObject song) {
		this.coverArt = coverArt;
		this.audioInputPath = audioInputPath;
		this.song = song;
	}

	public BufferedImage getCoverArt() {
		return coverArt;
	}
	
	public String getAudioInputPath() {
		return audioInputPath;
	}
	
	public SongObject getSong() {
		return song;
	}
}
