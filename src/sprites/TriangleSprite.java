package sprites;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import engine.Sprite;

public class TriangleSprite extends Sprite {
	double initopac = 0.4;
	double opac = 0.4;
	double count = 0;

	
	public TriangleSprite(int x, int y, int width, int height) {
		super(x, y, width, height);

	}

	@Override
	public void update() {

		if(count == 359) {
			count = 0;
		}
		count++;
		
		double amount = Math.sin(Math.toRadians(count));

		opac = initopac + (0.2 * amount);
		setX(getX() + (int)(getScreenWidth() * 0.01 * opac));
		
		
	}
	
	public void setOpac(double opac) {
		this.initopac = opac;
	}

	@Override
	public void draw(Graphics context) {

		int xPoly[] = { getX(), getX() - (int)(getWidth() / 1.8), getX() + (int)(getWidth() / 1.8) };
		int yPoly[] = { getY() - (getHeight() / 2), getY() + (getHeight() / 2), getY() + (getHeight() / 2) };

		
		Graphics2D graphics = (Graphics2D) context;
		graphics.setColor(new Color((int)(255 * opac), (int)(255 * opac), 0));
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);     
		
		graphics.setStroke(new BasicStroke(5.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		
		graphics.fillPolygon(new Polygon(xPoly, yPoly, xPoly.length));
		
		graphics.setColor(new Color((int)(255 * opac), (int)(255 * opac), 0));
		graphics.drawPolygon(new Polygon(xPoly, yPoly, xPoly.length));

	}

}
