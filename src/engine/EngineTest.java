package engine;

import static org.junit.Assert.*;

import org.junit.Test;

public class EngineTest {

	@Test
	public void test() {
		
		// Create the engine and test it starts
		Engine engine = new Engine("Beatnet Test", false);
		engine.start();
		assertTrue(engine.isShowing());
		engine.stop();
		
		
		
		
	
		
	}

}
