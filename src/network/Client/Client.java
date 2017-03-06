package network.Client;

import java.io.*;
import java.net.*;

import engine.GameObject;
import network.MessageQueue;
/**
 * This class is to connect the server
 * @author Weifeng 
 */
public class Client {

	private MessageQueue sendQueue;
	private MessageQueue receiveQueue;
	private String hostname;
	private GameObject gameObject;

	public Client(GameObject gameObject, String _hostname, MessageQueue _sendQueue, MessageQueue _receiveQueue){
		this.hostname = _hostname;
		this.sendQueue = _sendQueue;
		this.receiveQueue = _receiveQueue;
		this.gameObject = gameObject;
	}

	/**
	 * this method is tring to connect the server
	 */
	public void start(){
		
		Socket server = null;
	    PrintStream toServer = null;
	    BufferedReader fromServer = null;
	    
		try {
			server = new Socket(this.hostname, 4444);
			toServer = new PrintStream(server.getOutputStream());
		    fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));
		    gameObject.setConnect(true);
		} catch (UnknownHostException e) {
			System.err.println("Unkown host: ");
		}catch (IOException e) {
			System.err.println("Could not get IO for the connection " + e.getMessage());
			return;
		}
		
		//start a new thread ClientSender 
		//when get input from user, send messages to server
		(new ClientSender(toServer,sendQueue)).start();
		
		//start a new thread ClientReceive
		//when get messages from server save it in the blocking queue
		(new ClientReceiver(fromServer,receiveQueue)).start();
	
		
	}
}
