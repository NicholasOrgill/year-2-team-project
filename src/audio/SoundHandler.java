package audio;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHandler {

	HashMap<String, AudioInputStream> effects;
	
	public SoundHandler() {
		effects = new HashMap<String, AudioInputStream>();
	}

	public void fillEffects(String[] list) {
		try {
			System.out.println("HI");
			for (String elem : list) {
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src/res/audio/" + elem));
				effects.put(elem, ais);
			}
		} catch (Exception e) {
			System.err.println("Terminal Exception");
			e.printStackTrace();
		}
	}

	public void playEffect(String effect) {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(effects.get(effect));
			clip.start();
			Thread.sleep(clip.getMicrosecondLength() / 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}