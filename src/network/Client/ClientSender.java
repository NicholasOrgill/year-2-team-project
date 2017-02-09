package network.Client;

import java.io.*;
import java.net.Socket;

/**
 * This class is to send messages to server
 * @author Weifeng
 */
public class ClientSender extends Thread {
	private Socket server;
	private MessageQueue sendQueue;
	
	public ClientSender(Socket _server, MessageQueue _sendQueue){
		this.server = _server;
		this.sendQueue = _sendQueue;
	}
	
	
	public void run(){
		DataOutputStream toServer;
		try{
			toServer = new DataOutputStream(server.getOutputStream());
			while (true){
				Message sendMsg = sendQueue.take();
				
				//if there are messages in the queue
				//send it to server
				if (sendMsg != null){
					
					toServer.writeUTF(sendMsg.getMessage());
				}
			}
		}catch(IOException e){
			System.err.println(e.getMessage());
		}
	}
}
