package sprites;

import java.awt.Graphics;

import engine.Sprite;
import utils.ColorPack;

public class PlaySprite extends Sprite {
	int count = 0;
	
	int amount = 4;
	int gap = 10;
	int size = 40;
	int x = 50;
	int y = 50;
	
	public PlaySprite(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics context) {
		context.setColor(ColorPack.WHITE);
		
		for(int i = 0 ; i < amount ; i++) {
			context.drawRect(x + (i * (size + gap)), y, size, 10);
			context.drawLine(x + (i * (size + gap)) + (int)(size / 2), y, x + (i * (size + gap)) + (int)(size / 2), 0);
		}
		
	}

}
