package network.Client;

public class Network extends Thread{

	private MessageQueue receiveQueue;
	private MessageQueue sendQueue;
	
	public Network(MessageQueue _receiveQueue, MessageQueue _sendQueue){
		this.receiveQueue = _receiveQueue;
		this.sendQueue = _sendQueue;
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
	
	public void run(){
		while (true){
			if (this.receiveMessage())
				System.out.println(this.receive());
		}
	}
}
