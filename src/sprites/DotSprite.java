package sprites;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import engine.Sprite;

public class DotSprite extends Sprite {
	
	private Color color;
	private int size = 10;
	private Random random;
	
	public DotSprite(int x, int y, int width, int height) {
		super(x, y, width, height);
		random = new Random();
		
		setX(random.nextInt(800));
		setY(random.nextInt(600));
		setWidth(size);
		setHeight(size);
		
		color = new Color(255, 255, 255, 64 + random.nextInt(128));
	}

	@Override
	public void update() {
		//setY(getY() - 1);
	}

	@Override
	public void draw(Graphics context) {
		context.setColor(color);
		context.drawOval(getX(), getY(), getWidth(), getHeight());
		
	}

}
