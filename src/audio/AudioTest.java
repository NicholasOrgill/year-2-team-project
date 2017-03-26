package audio;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.Test;

import engine.GameObject;

public class AudioTest {

	/**
	 * Waits for a given number of ms
	 * 
	 * @param delay
	 *            Time to wait in ms
	 */
	public void wait(int delay) {
		try {
			Thread.sleep(delay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadAudio(AudioPlayer audio, String file) {
		try {
			audio.load(file);
		} catch (Exception e) {
		}
	}

	@Test
	public void test() {
		GameObject gameObject = new GameObject(800, 600);
		SoundHandler fx = new SoundHandler(gameObject);
		Player player = new Player(gameObject);
		String[] effects = { "titleshort.wav" };

		assertEquals(new HashMap<String, AudioInputStream>(), fx.effects);
		assertEquals(new HashMap<String, AudioFormat>(), fx.formats);
		assertNotNull(player.getAudioPlayer());

		fx.fillEffects(effects);
		fx.playEffect(effects[0]);
		player.playBack("src/res/audio/" + effects[0]);
		wait(1000);
		assertEquals(player.getPlayingTimer().toTimeString(), "00:00:01");
		player.pausePlaying();
		long systime = 1000;
		long timer1 = player.getPlayingTimer().getTimeInMill();
		assertTrue("Time elapsed for timer (" + timer1 + ") is not less than elapsed time (" + systime + ")",
				timer1 >= systime);
		player.resumePlaying();
		wait(10);
		long timer2 = player.getPlayingTimer().getTimeInMill();
		assertTrue(timer2 > timer1);
		player.stopPlaying();
		player.resetControls();
		
		AudioPlayer audio = new AudioPlayer();
		loadAudio(audio, "data/audio/beatnet.wav");
		assertEquals(audio.getClipSecondLength(), 100);
		assertEquals(audio.getClipLengthString(), "00:01:40");
		loadAudio(audio, "data/audio/beatnet1.wav");
	}
}
