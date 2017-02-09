package network.Server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

/**
 * This class will launch the server
 * @author Weifeng
 */
public class Server {
	
	public static void main(String[] args){
		ServerSocket serverSocket = null;
		
		//use array list to save player data
		ArrayList<Player> players = new ArrayList<Player>();
		
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
				Player p = new Player(players.size(), ClientSocket);
				players.add(p);
				
				//start a new Thread to solve messages from player
				new ServerThread(p,players).start();
				
			}
		} catch (Exception e) {
			System.err.println("IO error " + e.getMessage());
		}
	}
}
