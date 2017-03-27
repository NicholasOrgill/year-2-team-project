package main;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import engine.Engine;
import engine.GameObject;
import input.InputHandler;
import settings.SettingsHandler;

/**
 * The main class of the game
 * 
 * @author bobbydilley
 *
 */
public class Main {
	/**
	 * Main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Launching Game...");

		// Control fullscreen
		boolean fullScreen = false;

		if (args.length > 0) {
			if (args[0].equals("window")) {
				fullScreen = false;
			}
		}

		try {
			// Grab the settings
			SettingsHandler sh = new SettingsHandler();
			String key1 = sh.readSetting("playerkey1");
			String key2 = sh.readSetting("playerkey2");
			String key3 = sh.readSetting("playerkey3");
			String key4 = sh.readSetting("playerkey4");
			String key5 = sh.readSetting("powerkey1");
			String key6 = sh.readSetting("mutekey1");
			String key7 = sh.readSetting("quitkey1");
			String fullscreen1 = sh.readSetting("fullscreen");
			String serverip = sh.readSetting("serverip");
			
			// Set the fullscreen setting
			if(fullscreen1.equals("true")) {
				fullScreen = true;
			}
			
			Engine engine = new Engine("BeatNet", fullScreen);
			
			GameObject gameObject = engine.getGameObject();
			gameObject.setHostname(serverip);
			
			InputHandler inputHandler = engine.getInputHandler();
			
			// Store the keys
			inputHandler.storePlayKey(key1.charAt(0));
			inputHandler.storePlayKey(key2.charAt(0));
			inputHandler.storePlayKey(key3.charAt(0));
			inputHandler.storePlayKey(key4.charAt(0));
			inputHandler.storePowerKey(key5.charAt(0));
			inputHandler.storeMuteKey(key6.charAt(0));
			inputHandler.storeQuitKey(key7.charAt(0));
			
			engine.start();
		} catch (NullPointerException e) {
			System.out.println("Settings Issue: Please check that all settings are present in settings.xml");
			System.exit(-1);
		} catch (Exception e) {
			System.out.println("FATAL SYSTEM ERROR: -> ");
			System.out.println(e.getMessage());
			e.printStackTrace();
			System.exit(-1);
		}

	}
}
