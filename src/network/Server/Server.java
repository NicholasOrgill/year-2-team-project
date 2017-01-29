package network.Server;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server {
	public static void main(String[] args){
		ServerSocket serverSocket = null;
		
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
				Player p = new Player(players.size(), ClientSocket);
				players.add(p);
				
				new ServerThread(p,players).start();
				
			}
		} catch (Exception e) {
			System.err.println("IO error " + e.getMessage());
		}
	}
}
