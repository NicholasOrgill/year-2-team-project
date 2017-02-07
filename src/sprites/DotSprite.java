package sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import engine.Sprite;

public class DotSprite extends Sprite {
	
	private Color color;
	private int size = 4;
	private Random random;
	private int speed = 1;
	private int trans = 0;
	
	int counter = 0;
	int step = 4;
	
	
	
	
	public DotSprite(int x, int y, int width, int height) {
		super(x, y, width, height);
		random = new Random();
		
		setX(random.nextInt(800));
		setY(random.nextInt(600) + 600);
		setWidth(size);
		setHeight(size);
		
		trans = random.nextInt(128);
		color = new Color(255, 255, 255, trans);
		
		step = 1 + random.nextInt(4);
		
		
	}

	@Override
	public void update() {
		
		if(getY() < 0) {
			setY(getScreenHeight());
		}
		
		if(counter > step) {
			counter = 0;
			setY(getY() - 1);
		}
		counter++;
		
		double amount = (double) getY() / (double) (getScreenHeight() * 2);
		
		color = new Color(255, 255, 255, Math.max(0, (int) (trans * amount)));
		
	}

	@Override
	public void draw(Graphics context) {
		context.setColor(color);
		
		
		context.drawRect(getX(), getY(), getWidth(), getHeight());
		
	}

}
