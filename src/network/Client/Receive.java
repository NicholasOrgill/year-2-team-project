package network.Client;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class Receive extends Thread{
	private Socket server;
	
	public Receive(Socket _server) {
		super("Receive");
		this.server = _server;
	}
	
	public void run(){
		DataInputStream fromServr;
		
		try {
			fromServr = new DataInputStream(server.getInputStream());
			while (true){
				String readLine = fromServr.readUTF();
				System.out.println(readLine);
				if (readLine.substring(0,5).equals("PLAY:")){
					MiniMusicApp m = new MiniMusicApp();
					m.play();
				}
			}
		} catch (IOException e) {
			System.err.println("");
		}
		
	}
}
