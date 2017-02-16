package screens;

import java.awt.Graphics;

import engine.GameObject;
import engine.Screen;
import sprites.SystemTextCenter;
import sprites.TextSprite;
import utils.ColorPack;

public class EndScreen extends Screen {

	private SystemTextCenter player1Text;
	private SystemTextCenter player2Text;
	private SystemTextCenter gameFinished;
	private int player1Score;
	private int player2Score;

	public EndScreen(GameObject gameObject) {
		super(gameObject);

		// get the player's scores from the game object
		// player1Score = gameObject.getPlayer1Score;
		// player2Score = gameObject.getPlayer2Score;

		gameFinished = new SystemTextCenter(getScreenWidth() / 2, 100, "Game Over");
		player1Text = new SystemTextCenter(getScreenWidth() / 4, getScreenHeight() - 100, "Player 1 Score: " /* + player1Score */);
		player2Text = new SystemTextCenter((getScreenWidth() / 4) * 3, getScreenHeight() - 100, "Player 2 Score: " /* + player2Score */);

	}

	@Override
	public void update() {
		gameFinished.setScreenSize(getScreenWidth(), getScreenHeight());
		player1Text.setScreenSize(getScreenWidth(), getScreenHeight());
		player2Text.setScreenSize(getScreenWidth(), getScreenHeight());

	}

	@Override
	public void draw(Graphics context) {
		context.setColor(ColorPack.DARK);
		context.fillRect(0, 0, getScreenWidth(), getScreenHeight());

		gameFinished.draw(context);
		player1Text.draw(context);
		player2Text.draw(context);
	}

}
