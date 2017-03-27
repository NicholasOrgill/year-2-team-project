package input;

import static org.junit.Assert.*;


import org.junit.Test;

public class InputHandlerTest {

	@Test
	public void test() {
	
		InputHandler inputHandler = new InputHandler();

		inputHandler.storePlayKey('Q');
		inputHandler.storePlayKey('W');
		inputHandler.storePlayKey('E');
		inputHandler.storePlayKey('R');
		
		inputHandler.storePlayKey(';');
		inputHandler.storePlayKey('q');
		
		inputHandler.storePowerKey('L');
		inputHandler.storePowerKey('l');
		inputHandler.storePowerKey(';');
		
		inputHandler.storeMuteKey('M');
		inputHandler.storeMuteKey(';');
		
		inputHandler.storeQuitKey('C');
		inputHandler.storeQuitKey(';');
		
		assertNotNull(inputHandler.getPlayKey());
		assertNotNull(inputHandler.getPowerKey());
		assertTrue(inputHandler.containAllKey());
		
		inputHandler.removePlayKey(0);
		assertFalse(inputHandler.containAllKey());
		inputHandler.removePowerKey(0);
		assertTrue(inputHandler.emptyPowerKey());
		
		
	}

}
