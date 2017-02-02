package sprites;

import java.awt.Color;
import java.awt.Graphics;

import engine.Sprite;

public class TopBarSprite extends Sprite {
	private double height = 0.1;
	private CenterTextSprite textSprite;

	public TopBarSprite(String text) {
		super(0, 0);
		textSprite = new CenterTextSprite(0, 0, text);
		textSprite.setColor(Color.WHITE);
	}

	public void setText(String text) {
		this.textSprite.setText(text);
	}

	@Override
	public void update() {
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.setX((int) (getScreenWidth() * 0.1));
		textSprite.setY((int) (getScreenHeight() * height / 2));
		textSprite.update();
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public int getHeight() {
		return (int) (getScreenHeight() * height);
	}

	@Override
	public void draw(Graphics context) {
		context.setColor(Color.BLACK);
		context.fillRect(0, 0, getScreenWidth(), (int) (getScreenHeight() * height));

		for(int i = 0 ; i < 30 ; i++) {
			context.setColor(new Color(255, 255, 0, 30 * 2 - (i * 2)));
			context.drawLine(i + (i * 10), 0, i, (int) (getScreenHeight() * height));
		}
		textSprite.draw(context);
	}

}
