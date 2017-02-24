package sprites;

import java.awt.Color;
import java.awt.Graphics;

import engine.Sprite;
import utils.ColorPack;

public class ModeBoxSprite extends Sprite {
	static int sizex = 230;
	static int sizey = 180;
	static int count = 0;
	static int max = 9;
	static int trans = 0;
	
	private CenterTextSprite title;
	
	public ModeBoxSprite(int x, int y) {
		super(x, y, sizex, sizey);
		title = new CenterTextSprite((int)(getScreenWidth() / 2), (int)(getScreenHeight() / 2), "SINGLE");
		title.setScreenSize(getScreenWidth(), getScreenHeight());
	}

	@Override
	public void update() {
		title.setX((int)(getScreenWidth() / 2));
		title.setY((int)(getScreenHeight() / 2));
		title.setScreenSize(getScreenWidth(), getScreenHeight());
		title.update();
		
		if(count != max) {
			count++;
		}
		trans = (int) (255 * Math.sin(Math.toRadians(count)));
		
	}

	
	@Override
	public void draw(Graphics context) {
		context.setColor(new Color(ColorPack.FADEDWHITE.getRed(), ColorPack.FADEDWHITE.getRed(), ColorPack.FADEDWHITE.getRed(), trans));
		
		context.fillRect(getX() - (int)(sizex / 2), getY() - (int)(sizey / 2), sizex, sizey);
		title.draw(context);
	}

}
