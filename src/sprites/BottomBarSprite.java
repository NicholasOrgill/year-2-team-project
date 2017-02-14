package sprites;

import java.awt.Color;
import java.awt.Graphics;

import engine.Sprite;
import utils.ColorPack;

public class BottomBarSprite extends Sprite {
	private double height = 0.1;
	private MenuCenterTextSprite textSprite;
	private FadeCenterTextSprite textSpriteLeft;
	private FadeCenterTextSprite textSpriteRight;
	private boolean trans = false;
	
	public BottomBarSprite() {
		super(0, 0);
		textSprite = new MenuCenterTextSprite(0, 0, " ");
		textSprite.setColor(Color.WHITE);
		
		textSpriteLeft = new FadeCenterTextSprite(0, 0, " ");
		textSpriteLeft.setColor(Color.WHITE.darker());
		textSpriteLeft.setFontSize(0.023);
		
		textSpriteRight = new FadeCenterTextSprite(0, 0, " ");
		textSpriteRight.setColor(Color.WHITE.darker());
		textSpriteRight.setFontSize(0.023);
	}

	public void setText(String text) {
		this.textSprite.setText(text);
	}
	
	public void setLeftText(String text) {
		this.textSpriteLeft.setText(text);
	}
	
	public void setRightText(String text) {
		this.textSpriteRight.setText(text);
	}
	
	public void makeTrans() {
		trans = true;
	}

	@Override
	public void update() {
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.setX((int) (getScreenWidth() / 2));
		textSprite.setY((int) ((getScreenHeight()) - (int) (getScreenHeight() * height) + (getScreenHeight() * height / 2)));
		textSprite.update();
		
		textSpriteLeft.setScreenSize(getScreenWidth(), getScreenHeight());
		textSpriteLeft.setX((int) (getScreenWidth() * 0.1));
		textSpriteLeft.setY((int) ((getScreenHeight()) - (int) (getScreenHeight() * height) + (getScreenHeight() * height / 2)));
		textSpriteLeft.update();
		
		textSpriteRight.setScreenSize(getScreenWidth(), getScreenHeight());
		textSpriteRight.setX((int) (getScreenWidth() - (getScreenWidth() * 0.1)));
		textSpriteRight.setY((int) ((getScreenHeight()) - (int) (getScreenHeight() * height) + (getScreenHeight() * height / 2)));
		textSpriteRight.update();
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
			context.fillRect(0, (getScreenHeight()) - (int) (getScreenHeight() * height), getScreenWidth(), (int) (getScreenHeight() * height));
			
			context.setColor(ColorPack.SECONDARY);
			context.drawLine(0, (getScreenHeight()) - (int) (getScreenHeight() * height), getScreenWidth(), (getScreenHeight()) - (int) (getScreenHeight() * height));

		}
		
		textSprite.draw(context);
		textSpriteLeft.draw(context);
		textSpriteRight.draw(context);
	}

}
