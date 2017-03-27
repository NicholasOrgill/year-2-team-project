package engine;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Test the engine code
 * @author bobbydilley
 *
 */
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
