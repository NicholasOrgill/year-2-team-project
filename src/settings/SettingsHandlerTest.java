package settings;

import static org.junit.Assert.*;

import org.junit.Test;

public class SettingsHandlerTest {

	@Test
	public void test() {
		SettingsHandler sh = new SettingsHandler();
		sh.changeSetting("testSetting", "42");
		assertTrue(sh.readSetting("testSetting").compareTo("42") == 0);
		sh.changeSetting("testSetting", "68");
		assertFalse(sh.readAllSettings() == null);
	}

}
