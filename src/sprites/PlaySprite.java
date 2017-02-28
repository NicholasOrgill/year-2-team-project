package sprites;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
	int[] push = new int[amount];
	double position;
	
	
	public PlaySprite(int x, int y, int width, int height, double d) {
		super(x, y, width, height);
		for(int i = 0 ; i < push.length ; i++) {
			push[i] = 0;
		}
		this.position = d;
	}

	@Override
	public void update() {
		
		x = (int)(getScreenWidth() * position);
		y = (int)(getScreenHeight() * 0.8);
		this_width = (size + gap) * amount;
		
		count++;
		
	}
	
	public void push(int pushed) {
		push[pushed] = 1;
	}
	
	public void unpush(int pushed) {
		push[pushed] = 0;
	}

	@Override
	public void draw(Graphics context) {
		context.setColor(ColorPack.WHITE);
	
		
		for(int i = 0 ; i < amount ; i++) {
			
			if(push[i]  == 1) {
				context.drawRect(x + (gap / 2) + (i * (size + gap)) - (int)(this_width / 2), y + 5, size, 10);
			} else {
				context.drawRect(x + (gap / 2) + (i * (size + gap)) - (int)(this_width / 2), y, size, 10);
			}
			
			
			context.drawLine(x + (gap / 2) + (i * (size + gap)) + (int)(size / 2) - (int)(this_width / 2), y, x + (gap / 2) + (i * (size + gap)) + (int)(size / 2) - (int)(this_width / 2), 0);
		}
		
	}

}
