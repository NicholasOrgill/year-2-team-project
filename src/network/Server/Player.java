package network.Server;

import java.net.Socket;
/**
 * This class is used to save the player data in the server
 * @author Weifeng
 */
public class Player {
	private int score = 0;
	private int select;
	
	private String name;
	
	private boolean isReady;
	private boolean isStarted;
	
	private Socket socket;
	
	
	/**
	 * Constructor 
	 * @param _socket the socket of clinet 
	 */
	public Player(Socket _socket) {
		this.socket = _socket;
	}
	
	/**
	 * Constructor 
	 * @param _name the name of player
	 */
	public Player(String _name) {
		this.name = _name;
	}

	/**
	 * Set the score of player
	 * @param _score 
	 */
	public void setScore(int _score){
		this.score = _score;
	}
	
	/**
	 * Add the Socre of player
	 * @param _score
	 */
	public void addScore(int _score){
		this.score = this.score + _score;
	}
	
	/**
	 * Set the name of player
	 * @param _name
	 */
	public void setName(String _name){
		this.name = _name;
	}
		
	/**
	 * Set the ready status of player
	 * @param _isReady
	 */
	public void setReady(boolean _isReady){
		this.isReady = _isReady;
	}
	
	/**
	 * Set the start status of players
	 * @param _isStarted
	 */
	public void setStarted(boolean _isStarted){
		this.isStarted = _isStarted;
	}
	
	/**
	 * 
	 * @return Socket of player
	 */
	public Socket getSocket(){
		return this.socket;
	}
	
	/**
	 * 
	 * @return Integer Score of player
	 */
	public int getScore(){
		return this.score;
	}
	
	/**
	 * 
	 * @return String Name of player
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * 
	 * @return boolean whether the player is ready
	 */
	public boolean isReady(){
		return this.isReady;
	}
	
	/**
	 * 
	 * @return boolean thether the player is start the game
	 */
	public boolean isStarted(){
		return this.isStarted;
	}
	
	
	/**
	 * 
	 * @param _select the select number of song
	 */
	public void setSelect(int _select){
		this.select = _select;
	}
	
	/**
	 * 
	 * @return Integer the select numbet of song
	 */
	public int getSelect(){
		return this.select;
	}
}
