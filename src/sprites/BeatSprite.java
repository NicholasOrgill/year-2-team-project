package sprites;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import engine.Sprite;

public class BeatSprite extends Sprite {
	private CenterTextSprite textSprite;
	private boolean buttonUp = true;
	
	public BeatSprite(int x, int y, int width, int height) {
		super(x, y, width, height);
		textSprite = new CenterTextSprite(getX(), getY(), "B");
		
		
		textSprite.setColor(new Color(128, 255, 255, 255));
	}
	
	public void setUp() {
		buttonUp = true;
	}
	
	public void setDown() {
		buttonUp = false;
	}

	@Override
	public void update() {
		textSprite.setScreenSize(getScreenWidth(), getScreenHeight());
		textSprite.setX(getX() + (getWidth() / 2));
		textSprite.setFontSize(((double) getHeight() * 0.75) / (double) getScreenHeight());
		if(buttonUp) {
			textSprite.setY(getY() + (getHeight() / 2));
		} else {
			textSprite.setY(getY() + (int) (getScreenHeight() * 0.007) + (getHeight() / 2));
		}
	}

	@Override
	public void draw(Graphics context) {
		Graphics2D graphics = (Graphics2D) context;

		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		graphics.setStroke(new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		GradientPaint redtowhite = new GradientPaint(5, 7, Color.red, 200, 7, Color.blue);
		
		if(buttonUp) {
			graphics.setColor(new Color(128, 255, 255, 255));
			
			graphics.fillOval(getX(), getY() + (int) (getScreenHeight() * 0.008), getWidth(), getHeight());
			graphics.setPaint(redtowhite);
			graphics.fillOval(getX(), getY(), getWidth(), getHeight());
		} else {
			graphics.setColor(new Color(128, 255, 255, 255));
			graphics.fillOval(getX(), getY() + (int) (getScreenHeight() * 0.008), getWidth(), getHeight());
			graphics.setPaint(redtowhite);
			graphics.fillOval(getX(), getY() + (int) (getScreenHeight() * 0.007), getWidth(), getHeight());
		}
		

		textSprite.draw(context);
	}

}
