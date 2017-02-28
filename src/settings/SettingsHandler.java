package settings;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import songmanager.Beat;
import songmanager.Note;

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
		try {
			// Create document from file
			SAXBuilder saxBuilder = new SAXBuilder();
			File file = new File(filePath);
			Document doc = null;
			try {
				doc = saxBuilder.build(file);
			} catch (IOException e) {
				System.out.println("Error reading Settings file: " + e);
				System.exit(1);
			}		
			Element settingsElement = doc.getRootElement();
			
			
		} catch (JDOMException e) {
			System.out.println("Error parsing Settings file with JDOM: " + e);
		}
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
