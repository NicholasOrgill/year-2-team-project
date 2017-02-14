package network.Server;

import java.net.Socket;
/**
 * This class is used to save the player data in the server
 * @author Weifeng
 */
public class Player {
	private int points = 0;
	
	private String name;
	
	private boolean isEngaged;
	private boolean isReady;
	private boolean isStarted;
	
	private Socket socket;
	
	
	//this is for client player
	//save client socket
	public Player(Socket _socket) {
		this.socket = _socket;
	}
	
	//this is for server player
	//just save the name 
	public Player(String _name) {
		this.name = _name;
	}

	//save the points of player
	public void setPoints(int _points){
		this.points = _points;
	}
	
	public void addPoints(int _points){
		this.points = this.points + _points;
	}
	
	//save the name of player
	public void setName(String _name){
		this.name = _name;
	}
	
	//judge the player is or not have opponent already
	public void setEngaed(boolean _isEngaged){
		this.isEngaged = _isEngaged;
	}
	
	//save the ready state of player
	public void setReady(boolean _isReady){
		this.isReady = _isReady;
	}
	
	//save the state of the player is or not playing the game
	public void setStarted(boolean _isStarted){
		this.isStarted = _isStarted;
	}
	
	//@return the Socket of player
	public Socket getSocket(){
		return this.socket;
	}
	
	//@return the points of player
	public int getPoints(){
		return this.points;
	}
	
	//@return the name 
	public String getName(){
		return this.name;
	}
	
	//@return the player is or nor engaged
	public boolean isEngaed(){
		return this.isEngaged;
	}
	
	//@return the ready state of player
	public boolean isReady(){
		return this.isReady;
	}
	
	//@return the game state of player
	public boolean isStarted(){
		return this.isStarted;
	}
}
