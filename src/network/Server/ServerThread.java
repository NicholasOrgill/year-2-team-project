package network.Server;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

/**
 * This class is to get messages from user
 * @author Administrator
 */
public class ServerThread extends Thread{
	private ArrayList<Player> players = new ArrayList<>();
	private Socket clientSocket;
	private Player myClient;
	
	public ServerThread(Player _myPlayer, ArrayList<Player> _players){
		super("ServerThread");
		this.myClient = _myPlayer;
		this.players = _players;
		this.clientSocket = _myPlayer.getSocket();
	}
	
	public void run(){
		
		DataOutputStream toClient;
		DataInputStream fromClient;
	
		try {
			toClient = new DataOutputStream(clientSocket.getOutputStream());
			fromClient = new DataInputStream(clientSocket.getInputStream());
			
			String readLine;
			
			// for now just return any messages get from player
			// will use ServerResolve to resolve messages later
			while ( (readLine = fromClient.readUTF()) !=null ){
				System.out.println("got message "+ readLine +" form " + myClient.getUid());
				toClient.writeUTF("I got you message : "+ readLine);
			}
			
		} catch (IOException e) {
			System.out.println("Player " + myClient.getUid() + " disconnected");
		}
	}
	
}
