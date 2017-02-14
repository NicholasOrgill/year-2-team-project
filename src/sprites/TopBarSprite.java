package sprites;

import java.awt.Color;
import java.awt.Graphics;

import engine.Sprite;
import utils.ColorPack;

public class TopBarSprite extends Sprite {
	private double height = 0.1;
	private OutlineTextSprite textSprite;
	private boolean trans = false;
	
	public TopBarSprite(String text) {
		super(0, 0);
		textSprite = new OutlineTextSprite(0, 0, text);
		textSprite.setColor(Color.WHITE);
	}

	public void setText(String text) {
		this.textSprite.setText(text);
	}

	@Override
	public void update() {
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.setX((int) (getScreenWidth() * 0.04));
		textSprite.setY((int) (getScreenHeight() * height / 2));
		textSprite.update();
	}
	
	public void makeTrans() {
		trans = true;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getHeight() {
		return (int) (getScreenHeight() * height);
	}

	@Override
	public void draw(Graphics context) {
		if(!trans) {
			context.setColor(ColorPack.BLACK);
			context.fillRect(0, 0, getScreenWidth(), (int) (getScreenHeight() * height));
			context.setColor(ColorPack.SECONDARY);
			context.drawLine(0, (int) (getScreenHeight() * height), getScreenWidth(), (int) (getScreenHeight() * height));
		}
		
		textSprite.draw(context);
	}

}
