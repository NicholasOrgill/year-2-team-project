package screens;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import org.junit.Test;

import engine.GameObject;
import engine.Screen;
import input.InputHandler;
import songmanager.SongFile;

/**
 * The Screen Test to test the screen drawing ability
 * @author bobbydilley
 *
 */
public class ScreenTest {

	
	/**
	 * This will test to make sure the screen are update()ing and draw()ing correctly.
	 */
	@Test
	public void test() {
		// Create a new canvas and give it a size
		BufferedImage bufferedImage = new BufferedImage(800, 600, BufferedImage.TYPE_4BYTE_ABGR);

		// Get the graphics element
		Graphics graphics = bufferedImage.getGraphics();

		// Create a game object
		GameObject gameObject = new GameObject(800, 600);

		// Create arraylist of screens to check
		ArrayList<Screen> screens = new ArrayList<Screen>();

		// Create the screens
		screens.add(new AISelectScreen(gameObject));
		screens.add(new ExampleScreen(gameObject));
		screens.add(new ModeSelect(gameObject));
		screens.add(new NetworkSelect(gameObject));
		screens.add(new NetworkSelect2(gameObject));
		screens.add(new Overlay(gameObject));
		screens.add(new PlayScreen2(gameObject));
		screens.add(new SelectScreen(gameObject));
		screens.add(new StartScreen(gameObject));
		screens.add(new TitleScreen(gameObject));

		// Fill up the game object
		gameObject.setAiLevel(5);
		assertTrue(gameObject.getAiLevel() == 5);
		gameObject.setAiLevelText("Example");
		assertTrue(gameObject.getAiLevelText().equals("Example"));
		gameObject.setConnect(true);
		gameObject.setCurrentSelect(0);
		gameObject.setHeight(600);
		gameObject.setWidth(800);
		gameObject.setHostname("localhost");
		gameObject.setInputHandler(new InputHandler());
		gameObject.setOverlay(new Overlay(gameObject));
		gameObject.setP1Name("bobby");
		gameObject.setP2Name("victoria");
		gameObject.setP1Score(0);
		gameObject.setP2Score(0);
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
		gameObject.setScoreQuality(array);
		gameObject.setSongFile(gameObject.getSongFiles()[0]);
		
		
		AIPlayScreen aiPlayScreen = new AIPlayScreen(gameObject);
		aiPlayScreen.bad(true);
		aiPlayScreen.scoreHelper(50, true);
		aiPlayScreen.oppoKeyPressed(0);
		aiPlayScreen.oppoKeyReleased(0);
		
		screens.add(aiPlayScreen);
		screens.add(new EndScreen(gameObject));
		screens.add(new NetworkPlayScreen(gameObject));
		screens.add(new PlayScreen(gameObject));
		
		
		// Draw over in black
		graphics.setColor(new Color(0, 0, 0));
		graphics.fillRect(0, 0, 800, 600);
		assertFalse(checkImage(bufferedImage));

		for (Screen screen : screens) {
			// Draw over in black
			graphics.setColor(new Color(0, 0, 0));
			graphics.fillRect(0, 0, 800, 600);

			// Update the screens and check that drawing is occouring
			screen.update();
			screen.draw(graphics);
			assertTrue(checkImage(bufferedImage));
		}

	}

	/**
	 * Will check if an image has anything on it
	 * 
	 * @param bufferedImage
	 *            The image to check
	 * @return True if image has something on it, false if not
	 */
	public boolean checkImage(BufferedImage bufferedImage) {
		// Check if there is anything on the graphics
		for (int i = 0; i < 600; i++) {
			for (int j = 0; j < 800; j++) {
				// -16777216 Being the representation of black
				if (bufferedImage.getRGB(j, i) != -16777216) {
					return true;
				}
			}
		}
		return false;
	}

}
