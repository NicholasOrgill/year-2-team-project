package network.Server;

import java.net.Socket;

public class Player {
	private int uid;
	private int points;
	
	private String name;
	
	private boolean isReady;
	private boolean isStarted;
	
	private Socket socket;
	
	
	public Player(int _uid, Socket _socket) {
		this.uid = _uid;
		this.socket = _socket;
	}
	
	public void setPoints(int _points){
		this.points = _points;
	}
	
	public void setName(String _name){
		this.name = _name;
	}
	
	public void setReady(boolean _isReady){
		this.isReady = _isReady;
	}
	
	public void setStarted(boolean _isStarted){
		this.isStarted = _isStarted;
	}
	
	public Socket getSocket(){
		return this.socket;
	}
	
	public int getUid(){
		return this.uid;
	}
	
	public int getPoints(){
		return this.points;
	}
	
	public String getName(){
		return this.name;
	}
	
	public boolean isReady(){
		return this.isReady;
	}
	
	public boolean isStarted(){
		return this.isStarted;
	}
}
