package sprites;

import java.awt.Color;
import java.awt.Graphics;

import engine.Sprite;

public class SystemBox extends Sprite {
	int count = 90;
	boolean run = true;
	
	
	
	public SystemBox() {
		super(0, 0, 0, 0);
		
	}


	@Override
	public void update() {
		if(run) {
			count-=(count/10);
			if(count == 0) {
				run = false;
			}
		}
		
	}



	@Override
	public void draw(Graphics context) {
		context.setColor(new Color(255, 255, 255, 200 - count));
		context.fillRect((int)(getScreenWidth() * 0.05), (int)(getScreenHeight() * 0.05) + count, (int)(getScreenWidth() * 0.9), (int)(getScreenHeight() * 0.85));
	}

}
