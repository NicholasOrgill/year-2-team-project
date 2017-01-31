package audio;

import java.io.File;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHandler {

	static HashMap<String, AudioInputStream> effects;

	public static void fillEffects(String[] list) {
		try {
			for (String elem : list) {
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("/src/utils/effects/" + elem));
				effects.put(elem, ais);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void playEffect(String effect) {
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