package network.Client;

/**
 * This class is for user connecting, sending and receiving
 * @author Weifeng
 */
public class Network{

	private MessageQueue sendQueue = new MessageQueue();;
	private MessageQueue receiveQueue = new MessageQueue();;
	
	/**
	 * start connecting
	 * @param hostname of server
	 */
	public Network(String hostname){
		Client c = new Client(hostname, sendQueue, receiveQueue);
		c.start();
	}
	
	/**
	 * send messages
	 */
	public void send(String _readline){
		Message msg = new Message(_readline);
		this.sendQueue.offer(msg);
	}
	
	/**
	 * check the receive queue is or not empty
	 */
	public boolean receiveMessage(){
		return (!this.receiveQueue.isEmpty());
	}
	
	/**
	 * @return the messages from server
	 */
	public String receive(){
		return this.receiveQueue.take().getMessage();
	}
	
}
