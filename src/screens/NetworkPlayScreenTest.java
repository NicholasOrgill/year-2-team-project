package screens;

import static org.junit.Assert.*;

import org.junit.Test;

import engine.GameObject;
import input.InputHandler;
import network.MessageQueue;
import network.Client.Network;
import network.Server.Server;

public class NetworkPlayScreenTest {

	@Test
	public void test() throws InterruptedException {
		
		InputHandler serverInputHandler = new InputHandler();
		InputHandler clientInputHandler = new InputHandler();
		GameObject serverGameObject = new GameObject(800, 600);
		GameObject clientGameObject = new GameObject(800, 600);
		
		MessageQueue serverInput = new MessageQueue();
		Server server = new Server(serverGameObject, serverInput, "server");
		server.start();
		serverGameObject.setServer(true);
		serverGameObject.setServer(server);
		serverGameObject.setInputHandler(serverInputHandler);
		serverGameObject.setSongFile(serverGameObject.getSongFiles()[0]);
		
		Network client = new Network(clientGameObject, "localhost", "client");
		clientGameObject.setServer(false);
		clientGameObject.setNetwork(client);
		clientGameObject.setInputHandler(clientInputHandler);
		
		NetworkPlayScreen serverscreen = new NetworkPlayScreen(serverGameObject);
		serverInputHandler.setScreen(serverscreen);
		NetworkPlayScreen clientscreen = new NetworkPlayScreen(clientGameObject);
		clientInputHandler.setScreen(clientscreen);
		
		serverscreen.keyPressed(InputHandler.POWERKEY);
		assertEquals(0,clientGameObject.getP2Power());
		serverscreen.keyPressed(InputHandler.PLAYKEY0);
		serverscreen.scoreHelper(10, false);
		serverscreen.keyPressed(InputHandler.MUTEKEY);
		assertTrue(serverGameObject.isMute());
		serverscreen.keyReleased(InputHandler.POWERKEY);
		serverscreen.keyReleased(InputHandler.PLAYKEY0);
		Thread.sleep(500);
		assertEquals(100,clientGameObject.getP2Score());
		assertEquals(1,clientGameObject.getP2Combo());
		assertEquals("PERFECT",clientGameObject.getP2Text());
		serverscreen.update();
		serverscreen.scoreHelper(20, false);
		Thread.sleep(500);
		assertEquals(175,clientGameObject.getP2Score());
		assertEquals(2,clientGameObject.getP2Combo());
		assertEquals("EXCELLENT",clientGameObject.getP2Text());
		serverscreen.scoreHelper(30, false);
		Thread.sleep(500);
		assertEquals(225,clientGameObject.getP2Score());
		assertEquals(3,clientGameObject.getP2Combo());
		assertEquals("GOOD",clientGameObject.getP2Text());
		serverscreen.scoreHelper(50, false);
		Thread.sleep(500);
		assertEquals(250,clientGameObject.getP2Score());
		assertEquals(0,clientGameObject.getP2Combo());
		assertEquals("GOOD",clientGameObject.getP2Text());
		serverscreen.scoreHelper(60, false);
		Thread.sleep(500);
		assertEquals(250,clientGameObject.getP2Score());
		assertEquals(0,clientGameObject.getP2Combo());
		assertEquals("BAD",clientGameObject.getP2Text());
		serverGameObject.setP2Text("BAD");
		serverscreen.scoreHelper(60, true);
		serverGameObject.setP2Text("PERFECT");
		serverscreen.scoreHelper(60, true);
		serverscreen.scoreHelper(10, false);
		for(int i = 0; i < 20; i ++){
			serverscreen.scoreHelper(10, false);
		}
		
		serverscreen.keyPressed(InputHandler.POWERKEY);
		Thread.sleep(500);
		
		clientscreen.scoreHelper(20, false);
		Thread.sleep(500);
		assertEquals(75,serverGameObject.getP2Score());
		assertEquals(1,serverGameObject.getP2Combo());
		assertEquals("EXCELLENT",serverGameObject.getP2Text());
		clientscreen.keyPressed(InputHandler.PLAYKEY0);
		clientscreen.keyReleased(InputHandler.PLAYKEY0);
		
	}

}
