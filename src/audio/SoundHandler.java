package audio;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHandler {

	HashMap<String, AudioInputStream> effects;
	static ArrayList<Clip> clips = new ArrayList<Clip>();
	
	public SoundHandler() {
		effects = new HashMap<String, AudioInputStream>();
		//clips = new ArrayList<Clip>();
		
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
			clips.add(clip);
			effects.put(effect, AudioSystem.getAudioInputStream(new File("src/res/audio/" + effect)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void stopAll() {
		for(Clip clip : clips) {
			clip.stop();
		}
	}
}