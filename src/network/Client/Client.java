package network.Client;

import java.io.*;
import java.net.*;

public class Client {

	MessageQueue sendQueue;
	MessageQueue receiveQueue;
	private String hostname;
	
	public Client(String _hostname, MessageQueue _sendQueue, MessageQueue _receiveQueue){
		this.hostname = _hostname;
		this.sendQueue = _sendQueue;
		this.receiveQueue = _receiveQueue;
	}
	
	public void start(){
		
		Socket server = null;
		try {
			server = new Socket(this.hostname, 4444);
		} catch (UnknownHostException e) {
			System.err.println("Unkown host: ");
		}catch (IOException e) {
			System.err.println("Could not get IO for the connection " + e.getMessage());
			System.exit(1);
		}
				
		(new ClientSender(server,sendQueue)).start();		
		(new ClientReceiver(server,receiveQueue)).start();
	
		
	}
}
