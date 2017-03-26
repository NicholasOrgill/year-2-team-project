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
		client.send("SCOR:" + 150);
		server.inputMessage("SCOR:"+100);

		client.send("POWE:" + 90);
		server.inputMessage("POWE:" + 60);

		client.send("COMB:" + 15);
		server.inputMessage("COMB:" + 20);
		
		Thread.sleep(500);
		assertTrue(server.isAlive());
		assertTrue(clientGameObject.isConnected());
		
		Thread.sleep(5000);
		assertTrue(serverGameObject.getP2Score() == 150);
		assertTrue(clientGameObject.getP2Score() == 100);

		Thread.sleep(5000);
		assertTrue(serverGameObject.getP2Power() == 90);
		assertTrue(clientGameObject.getP2Power() == 60);
		
		Thread.sleep(5000);
		assertTrue(serverGameObject.getP2Combo() == 15);
		assertTrue(clientGameObject.getP2Combo() == 20);
		
	}
	

}
