package sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import engine.Sprite;
import utils.ColorPack;

public class CheckedSprite extends Sprite {
	int trans = 110;
	int size = 70;
	int length = 50;
	
	public CheckedSprite(int x, int y) {
		super(x, y);

	}

	@Override
	public void update() {
		if(getX() == -size) {
			setX(-1);
		}
		
		if(getY() == -size) {
			setY(-1);
		}
		
		setX(getX() - 1);
		setY(getY() - 1);
	}
	@Override
	public void draw(Graphics context) {
		boolean flip = false;
		for(int i = getY() ; i < length * size; i+= (size)) {
			flip = !flip;
			for(int j = getX() ; j < length * size ; j+= (size * 2)) {
				if(flip) {
					context.setColor(ColorPack.PRIMARY);
					context.fillRect(j, i, size, size);
					context.setColor(ColorPack.SECONDARY);
					context.fillRect(j - size, i, size, size);
				} else {
					context.setColor(ColorPack.PRIMARY);
					context.fillRect(j + size, i, size, size);
					context.setColor(ColorPack.SECONDARY);
					context.fillRect(j, i, size, size);
				}
				
			}
		}
		
	}

}
