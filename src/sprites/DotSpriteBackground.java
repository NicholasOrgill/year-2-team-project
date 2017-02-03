package sprites;

import java.awt.Graphics;

import engine.Sprite;

public class DotSpriteBackground extends Sprite {
	private DotSprite[] dotSprite;
	int amount = 100;

	public DotSpriteBackground(int x, int y, int width, int height) {
		super(x, y, width, height);
		dotSprite = new DotSprite[amount];
		for (int i = 0; i < amount; i++) {
			dotSprite[i] = new DotSprite(10, 20, 30, 40);
			dotSprite[i].setScreenSize(getScreenWidth(), getScreenHeight());
		}
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics context) {
		for (int i = 0; i < amount; i++) {
			dotSprite[i].draw(context);
		}

	}

}
