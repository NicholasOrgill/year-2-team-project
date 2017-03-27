package network;


import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;

import engine.GameObject;
import input.InputHandler;
import network.Client.*;
import network.Server.*;
import screens.NetworkPlayScreen;;

public class NetworkTest {
	
	
	@Test
	public void test() throws InterruptedException{
		
		
		
		
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
		
		server.inputMessage("READ:");
		client.sendReadyMsg();
		assertTrue(server.isAlive());
		Thread.sleep(500);
		assertTrue(clientGameObject.isConnected());
		
		client.send("SCOR:" + 150);
		server.inputMessage("SCOR:"+100);
		
		Thread.sleep(500);
		assertEquals(150,serverGameObject.getP2Score());
		assertEquals(100,clientGameObject.getP2Score());

		client.send("POWE:" + 90);
		server.inputMessage("POWE:" + 60);
		Thread.sleep(500);
		assertEquals(90,serverGameObject.getP2Power());
		assertEquals(60,clientGameObject.getP2Power());
		
		client.send("COMB:" + 15);
		server.inputMessage("COMB:" + 20);
		Thread.sleep(500);
		assertEquals(15,serverGameObject.getP2Combo());
		assertEquals(20,clientGameObject.getP2Combo());
		
		//testing NetworkPlayScreen
		NetworkPlayScreen serverscreen = new NetworkPlayScreen(serverGameObject);
		serverInputHandler.setScreen(serverscreen);
		NetworkPlayScreen clientscreen = new NetworkPlayScreen(clientGameObject);
		clientInputHandler.setScreen(clientscreen);
		
		serverscreen.keyPressed(InputHandler.POWERKEY);
		assertEquals(60,clientGameObject.getP2Power());
		serverscreen.keyPressed(InputHandler.PLAYKEY0);
		
		serverscreen.scoreHelper(10, false);
		serverscreen.keyPressed(InputHandler.MUTEKEY);
		assertTrue(serverGameObject.isMute());
		serverscreen.keyReleased(InputHandler.POWERKEY);
		serverscreen.keyReleased(InputHandler.PLAYKEY0);
		Thread.sleep(500);
		assertEquals(200,clientGameObject.getP2Score());
		assertEquals(1,clientGameObject.getP2Combo());
		assertEquals("PERFECT",clientGameObject.getP2Text());
		serverscreen.update();
		
		serverscreen.scoreHelper(20, false);
		Thread.sleep(500);
		assertEquals(275,clientGameObject.getP2Score());
		assertEquals(2,clientGameObject.getP2Combo());
		assertEquals("EXCELLENT",clientGameObject.getP2Text());
		
		serverscreen.scoreHelper(30, false);
		Thread.sleep(500);
		assertEquals(325,clientGameObject.getP2Score());
		assertEquals(3,clientGameObject.getP2Combo());
		assertEquals("GOOD",clientGameObject.getP2Text());
		
		serverscreen.scoreHelper(50, false);
		Thread.sleep(500);
		assertEquals(350,clientGameObject.getP2Score());
		assertEquals(0,clientGameObject.getP2Combo());
		assertEquals("GOOD",clientGameObject.getP2Text());
		
		serverscreen.scoreHelper(60, false);
		Thread.sleep(500);
		assertEquals(350,clientGameObject.getP2Score());
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
		assertEquals(225,serverGameObject.getP2Score());
		assertEquals(1,serverGameObject.getP2Combo());
		assertEquals("EXCELLENT",serverGameObject.getP2Text());
		clientscreen.keyPressed(InputHandler.PLAYKEY0);
		clientscreen.keyReleased(InputHandler.PLAYKEY0);
		
	}
	

}
