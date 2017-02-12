package network.Server;

import java.io.IOException;
import java.net.*;

/**
 * This class will launch the server
 * @author Weifeng
 */
public class Server extends Thread{

	private MessageQueue serverInput;
	private String name;
	
	public Server(MessageQueue _serverInput, String _name){
		this.serverInput = _serverInput;
		this.name = _name;
	}
	
	public void run(){
		ServerSocket serverSocket = null;
		
		//use array list to save player data
		
		try {
			serverSocket = new ServerSocket(4444);
		} catch (IOException e) {
			System.err.println("Could not listen on port 4444");
			System.exit(-1);
		}
		
		try {
			while (true){
				Socket ClientSocket = serverSocket.accept();
				
				// when a player connected add this player to array list
				// the user ID will simply be determined by how many player already connected
				Player opponent = new Player(ClientSocket);
				Player me = new Player(name);
				System.out.println("Your opponent connected");
				
				//start a new Thread to solve messages from player
				new ServerThread(serverInput,opponent,me).start();
				
			}	
								
		} catch (Exception e) {
			System.err.println("IO error " + e.getMessage());
		}
	}
}
