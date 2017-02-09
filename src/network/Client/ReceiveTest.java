package network.Client;

/**
 * This class is just for receive test
 * @author Weifeng
 */
public class ReceiveTest extends Thread {
	private Network network;
	
	public ReceiveTest(Network network){
		this.network = network;
	}
	
	//print any messages get from server
	public void run(){
		while (true){
			if (network.receiveMessage())
				System.out.println(network.receive());
		}
	}
}
