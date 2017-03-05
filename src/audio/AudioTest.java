package audio;

public class AudioTest {
	public static void main(String[] args) {
		Player audio;
		SoundHandler fx = new SoundHandler();
		
		String[] fxlist = {"sound_effect_one.wav"};
		fx.fillEffects(fxlist);
		fx.playEffect("sound_effect_one.wav");
		
		
	}
}
