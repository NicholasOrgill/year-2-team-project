package network.Client;

public class Network{

	private MessageQueue sendQueue = new MessageQueue();;
	private MessageQueue receiveQueue = new MessageQueue();;
	
	public Network(String hostname){
		Client c = new Client(hostname, sendQueue, receiveQueue);
		c.start();
	}
	
	public void send(String _readline){
		Message msg = new Message(_readline);
		this.sendQueue.offer(msg);
	}
	
	public boolean receiveMessage(){
		return (!this.receiveQueue.isEmpty());
	}
	
	public String receive(){
		return this.receiveQueue.take().getMessage();
	}
	
}
