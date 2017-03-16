package audio;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundHandler {

	HashMap<String, AudioInputStream> effects;
	HashMap<String, AudioFormat> formats;

	static ArrayList<Clip> clips = new ArrayList<Clip>();

	public SoundHandler() {
		effects = new HashMap<String, AudioInputStream>();
		formats = new HashMap<String, AudioFormat>();
		// clips = new ArrayList<Clip>();

	}

	public void fillEffects(String[] list) {
		try {
			// System.out.println("HI");
			for (String elem : list) {
				AudioInputStream ais = AudioSystem.getAudioInputStream(new File("src/res/audio/" + elem));
				AudioFormat format = ais.getFormat();
				effects.put(elem, ais);
				formats.put(elem, format);
			}
		} catch (Exception e) {
			System.err.println("Terminal Exception");
			e.printStackTrace();
		}

	}
	
	public void playEffect(String effect) {
		playEffect(effect, false);
	}

	public void playEffect(String effect, boolean loop) {
		try {
			DataLine.Info info = new DataLine.Info(Clip.class, formats.get(effect));
			Clip clip = (Clip) AudioSystem.getLine(info);
			// Clip clip = AudioSystem.getClip();
			clip.open(effects.get(effect));
			if (loop)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				clip.start();
			clips.add(clip);
			effects.put(effect, AudioSystem.getAudioInputStream(new File("src/res/audio/" + effect)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopAll() {
		for (Clip clip : clips) {
			clip.stop();
		}
	}
}