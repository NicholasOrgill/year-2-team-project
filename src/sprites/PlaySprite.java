package sprites;

import java.awt.Graphics;

import engine.Sprite;
import utils.ColorPack;

public class PlaySprite extends Sprite {
	int count = 0;
	
	int amount = 4;
	int gap = 20;
	int size = 60;
	int x = 50;
	int y = 50;
	int this_width = 0;
	
	public PlaySprite(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	@Override
	public void update() {
		
		x = (int)(getScreenWidth() / 2);
		y = (int)(getScreenHeight() * 0.8);
		this_width = (size + gap) * amount;
		
		count++;
		
	}

	@Override
	public void draw(Graphics context) {
		context.setColor(ColorPack.WHITE);
		
		
		
		for(int i = 0 ; i < amount ; i++) {
			context.drawRect(x + (gap / 2) + (i * (size + gap)) - (int)(this_width / 2), y, size, 10);
			context.drawLine(x + (gap / 2) + (i * (size + gap)) + (int)(size / 2) - (int)(this_width / 2), y, x + (gap / 2) + (i * (size + gap)) + (int)(size / 2) - (int)(this_width / 2), 0);
		}
		
	}

}
