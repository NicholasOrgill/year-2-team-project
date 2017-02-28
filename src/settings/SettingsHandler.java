package settings;

import java.util.HashMap;

public class SettingsHandler {

	private String filePath;
	
	public SettingsHandler() {
		filePath = "settings.xml";
	}
	
	/**
	 * Changes the saved value of a particular setting.
	 * @param settingName Name of the setting to be changed
	 * @param newValue New value of the setting (as a string)
	 */
	public void changeSetting(String settingName, String newValue) {
		
	}
	
	/**
	 * Reads a particular setting from the settings file
	 * @param settingName Name of the setting to be read
	 * @return Value of the setting (as a string)
	 */
	public String readSetting(String settingName) {
		return null;
	}
	
	/**
	 * Returns a HashMap of setting names to setting values for all settings
	 */
	public HashMap<String, String> readAllSettings() {
		return null;
	}
}
