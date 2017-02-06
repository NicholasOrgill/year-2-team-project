package network.Client;

public class ReceiveTest extends Thread {
	private Network network;
	
	public ReceiveTest(Network network){
		this.network = network;
	}
	
	public void run(){
		while (true){
			if (network.receiveMessage())
				System.out.println(network.receive());
		}
	}
}
