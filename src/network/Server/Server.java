package network.Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Server {
	public static void main(String[] args){
		ServerSocket serverSocket = null;
		ArrayList<Player> players = new ArrayList<Player>(); 
		
		
		try{
			serverSocket = new ServerSocket(4444);
		}catch(IOException e){
			System.err.println("Could not listen on port 4444");
			System.exit(-1);
		}
		
		try {
			while(true){
				Socket clientSocket = serverSocket.accept();
				Player p = new Player(clientSocket);
				players.add(p);
					ServerThread s = new ServerThread(players,p);
				s.start();
			}
			
		} catch (Exception e) {
			try{
				serverSocket.close();
			}catch(IOException io){
				System.err.println("Couldn't close server socket" + io.getMessage());
			}
		}
	}
}
