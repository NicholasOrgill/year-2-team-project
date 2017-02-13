package network.Client;

/**
 * This class is for user connecting, sending and receiving
 * @author Weifeng
 */
public class Network{

	private MessageQueue sendQueue = new MessageQueue();;
	private MessageQueue receiveQueue = new MessageQueue();;
	private boolean playing;
	
	/**
	 * start connecting
	 * @param hostname of server
	 * @param name of client
	 */
	public Network(String hostname, String name){
		Client c = new Client(hostname, sendQueue, receiveQueue);
		c.start();
		Message msg = new Message("NAME:" + name);
		this.sendQueue.offer(msg);
		
	}
	
	//send message
	public void send(String _readline){
		Message msg = new Message(_readline);
		this.sendQueue.offer(msg);
	}
	
	//send a ready message
	public void sendReadyMsg(){
		Message msg = new Message("READ:");
		this.sendQueue.offer(msg);
	}
	
	//send a gameover message
	public void sendGameOverMsg(){
		Message msg = new Message("OVER:");
		this.sendQueue.offer(msg);
	}
	
	
	//check the receive queue is or not empty
	public boolean receiveMessage(){
		return this.receiveQueue.isEmpty();
	}
	
	
	//@return the messages from server
	public String receive(){
		return this.receiveQueue.take().getMessage();
	}
	
	//check whether the game is playing or not
	public boolean playing(){
		return this.playing;
	}
	
	//set the game playing status
	public void setPlaying(boolean p){
		this.playing = p;
	}
	
}
