package sprites;

import java.awt.Graphics;

import engine.Sprite;
import utils.ColorPack;

public class NoteSprite extends Sprite {
	int count = 0;
	
	int amount = 4;
	int gap = 20;
	int size = 60;
	int x = 0;
	int v = 0;
	int this_width = 0;
	int buttons[];
	int length = 10;
	boolean hit = false;
	
	public NoteSprite(int x, int y, int width, int height, int[] buttons, int length) {
		super(x, y, width, height);
		this.buttons = buttons;
		if(length != 0) {
			this.length = length;
		}
		
	}

	@Override
	public void update() {
		if(count == 0) {
			x = (int)(getScreenWidth() / 2);
			
		}
		
		
		this_width = (size + gap) * amount;
		
		count++;
	}
	
	public void hit() {
		hit = true;
	}

	@Override
	public void draw(Graphics context) {
		
		context.setColor(ColorPack.WHITE);
			
		for(int i = 0 ; i < buttons.length ; i++) {
			
				context.fillRect(x + (gap / 2) + (buttons[i] * (size + gap)) - (int)(this_width / 2), getY() - (int)(length / 2), size, length);
		}
		
	}

}
