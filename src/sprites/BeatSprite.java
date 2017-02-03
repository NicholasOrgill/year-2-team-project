package sprites;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

import engine.Sprite;
import utils.ColorPack;

public class BeatSprite extends Sprite {

	public BeatSprite(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics context) {
		Graphics2D graphics = (Graphics2D) context;

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setStroke(new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		GradientPaint redtowhite = new GradientPaint(getX() - (getWidth() / 2), getY() - (getHeight() / 2), ColorPack.PRIMARY, getX()  - (getWidth() / 2) + (int) (getWidth() * 0.8),
				getY() - (getHeight() / 2) + (int) (getHeight() * 0.7), ColorPack.SECONDARY);

		graphics.setColor(ColorPack.BROWN);
		graphics.fillOval(getX() - (getWidth() / 2) - (int) (getWidth() * 0.05), getY() - (getHeight() / 2) - (int) (getHeight() * 0.05),
				(int) (getWidth() * 1.1), (int) (getHeight() * 1.1));

		
		graphics.setPaint(redtowhite);
		graphics.fillOval(getX() - (getWidth() / 2), getY() - (getHeight() / 2), getWidth(), getHeight());

	}

}
