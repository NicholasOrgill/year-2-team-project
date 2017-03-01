package sprites;

import java.awt.Color;
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
	boolean buttons[];
	int length = 10;
	boolean hit = false;
	int ai = 0;
	double position;
	private boolean isRemoved = false;

	
	public NoteSprite(int x, int y, int width, int height, boolean[] buttons, int length, double position) {
		super(x, y, width, height);
		this.buttons = buttons;
		if(length != 0) {
			this.length = length;
		} else {
			this.length = 10;
		}
		this.position = position;
	}

	@Override
	public void update() {
		if(count == 0) {
			x = (int)(getScreenWidth() * position);
			
		}
		
		this_width = (size + gap) * amount;
		
		count++;
	}
	
	public void hit() {
		hit = true;
	}
	
	public void setAI() {
		ai = 1;
	}
	
	public void remove() {
		isRemoved = true;
	}
	
	public boolean isRemoved() {
		return isRemoved;
	}

	@Override
	public void draw(Graphics context) {
		if(ai == 1) {
			context.setColor(new Color(255, 0, 0, 90));
		} else {
			
		context.setColor(ColorPack.WHITE);
		}
		for(int i = 0 ; i < buttons.length ; i++) {
			
				if (buttons[i]) context.fillRect(x + (gap / 2) + (i * (size + gap)) - (int)(this_width / 2), getY() - length - 5, size, length);
		}
		
	}

}
