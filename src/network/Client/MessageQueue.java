package network.Client;

import java.util.concurrent.*;

public class MessageQueue {
	private BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	
	public void offer(Message m){
		queue.offer(m);
	}
	
	public Message take(){
		while (true){
			try{
				return (queue.take());
			}catch(InterruptedException e){
				
			}
		}
	}
	
	public boolean isEmpty(){
		return queue.isEmpty();
	}
}
