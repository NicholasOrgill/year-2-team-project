package network;


import static org.junit.Assert.*;

import org.junit.*;
import org.junit.Test;

import engine.GameObject;
import network.Client.*;
import network.Server.*;;

public class NetworkTest {
	
	
	@Test
	public void test() throws InterruptedException{
		
		GameObject serverGameObject = new GameObject(800, 600);
		GameObject clientGameObject = new GameObject(800, 600);
		
		MessageQueue serverInput = new MessageQueue();
		Server server = new Server(serverGameObject, serverInput, "server");
		server.start();
		Network client = new Network(clientGameObject, "localhost", "client");
		


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
		
	}
	

}
