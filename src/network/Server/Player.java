package network.Server;

import java.net.Socket;

public class Player {

	private Socket socket;
	private boolean isReady = false;
	
	public Player(Socket _socket) {
		this.socket = _socket;
	}
	
	public Socket getSocket(){
		return this.socket;
	}
	
	public void setReady(boolean _boolean){
		this.isReady = _boolean;
	}
	
	public boolean isReady(){
		return this.isReady;
	}
}
