package network.Client;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args){
		if (args.length != 1){
			System.err.println("Usage: java Client hostname");
			System.exit(1);
		}
		
		String hostname = args[0];
		Socket server = null;
		try {
			server = new Socket(hostname, 4444);
		} catch (UnknownHostException e) {
			System.err.println("Unkown host: ");
		}catch (IOException e) {
			System.err.println("Could not get IO for the connection " + e.getMessage());
			System.exit(1);
		}
		
		MessageQueue reveiveQueue = new MessageQueue();
		MessageQueue sendQueue = new MessageQueue();
		
		(new ClientReceiver(server,reveiveQueue)).start();
		
		(new ClientSender(server,sendQueue)).start();
		
		(new Network(reveiveQueue,sendQueue)).start();
		
		try{
			BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
			String userInput;
			while ((userInput = fromUser.readLine()) != null){
				Message msg = new Message(userInput);
				sendQueue.offer(msg);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}	
	}
}
