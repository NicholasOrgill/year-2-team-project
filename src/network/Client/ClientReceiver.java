package network.Client;

import java.io.*;
import java.net.Socket;

public class ClientReceiver extends Thread {
	private Socket server;
	private MessageQueue receiveQueue;
	
	public ClientReceiver(Socket _server, MessageQueue _receiveQueue){
		super();
		this.server = _server;
		this.receiveQueue = _receiveQueue;
	}
	
	public void run(){
		DataInputStream fromServer;
		try{
			fromServer = new DataInputStream(server.getInputStream());
			while(true){
				String readline = fromServer.readUTF();
				if(readline != null){ 
					Message msg = new Message(readline);
					receiveQueue.offer(msg);
				}
			}
		}catch(IOException e){
			System.err.println("Something went wrong with the Server "+ e.getMessage());
		}
	}
}
