package network.Server;

import java.net.Socket;
/**
 * This class is used to save the player data in the server
 * @author Weifeng
 */
public class Player {
	private int uid;
	private int points;
	
	private String name;
	
	private boolean isReady;
	private boolean isStarted;
	
	private Socket socket;
	
	/**
	 * save the user ID and Socket
	 *
	 */
	public Player(int _uid, Socket _socket) {
		this.uid = _uid;
		this.socket = _socket;
	}
	
	/**
	 * save the points of player
	 */
	public void setPoints(int _points){
		this.points = _points;
	}
	
	/**
	 * save the name of player
	 */
	public void setName(String _name){
		this.name = _name;
	}
	
	/**
	 * save the ready state of player
	 */
	public void setReady(boolean _isReady){
		this.isReady = _isReady;
	}
	
	/**
	 * save the state of the player is or not playing the game
	 */
	public void setStarted(boolean _isStarted){
		this.isStarted = _isStarted;
	}
	
	/**
	 * @return the Socket of player
	 */
	public Socket getSocket(){
		return this.socket;
	}
	
	/**
	 * @return the user ID
	 */
	public int getUid(){
		return this.uid;
	}
	
	/**
	 * @return the points of player
	 */
	public int getPoints(){
		return this.points;
	}
	
	/**
	 * @return the name 
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @return the ready state of player
	 */
	public boolean isReady(){
		return this.isReady;
	}
	
	/**
	 * @return the game state of player
	 */
	public boolean isStarted(){
		return this.isStarted;
	}
}
