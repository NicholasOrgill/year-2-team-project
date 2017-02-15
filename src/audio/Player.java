package audio;

import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * A Swing-based audio player program. NOTE: Can play only WAVE (*.wav) file.
 *
 * @author Nicholas Orgill
 */
public class Player {
	private AudioPlayer player = new AudioPlayer();
	private Thread playbackThread;
	private PlayingTimer timer;

	private boolean isPlaying = false;
	private boolean isPause = false;

	public Player() {
	}

	/**
	 * Start playing back the sound.
	 */
	public void playBack(final String audioFilePath) {
		timer = new PlayingTimer();
		timer.start();
		isPlaying = true;
		playbackThread = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					player.load(audioFilePath);
					timer.setAudioClip(player.getAudioClip());
					player.play();
					stopPlaying();
				} catch (UnsupportedAudioFileException ex) {
					System.out.println("The audio format is unsupported!");
					resetControls();
					ex.printStackTrace();
				} catch (LineUnavailableException ex) {
					System.out.println("Could not play the audio file because line is unavailable!");
					resetControls();
					ex.printStackTrace();
				} catch (IOException ex) {
					System.out.println("I/O error while playing the audio file!");
					resetControls();
					ex.printStackTrace();
				}

			}
		});

		playbackThread.start();
	}
	
	public AudioPlayer getAudioPlayer() {
		return player;
	}
	
	public PlayingTimer getPlayingTimer() {
		return timer;
	}

	public void stopPlaying() {
		isPause = false;
		timer.reset();
		timer.interrupt();
		player.stop();
		playbackThread.interrupt();
	}

	public void pausePlaying() {
		isPause = true;
		player.pause();
		timer.pauseTimer();
		playbackThread.interrupt();
	}

	public void resumePlaying() {
		isPause = false;
		player.resume();
		timer.resumeTimer();
		playbackThread.interrupt();
	}

	public void resetControls() {
		timer.reset();
		timer.interrupt();
		isPlaying = false;
	}
}