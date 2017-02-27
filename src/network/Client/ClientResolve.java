package network.Client;

import engine.GameObject;

/**
 * This class is just for receive test
 * @author Weifeng
 */
public class ClientResolve extends Thread {
	private Network network;
	private GameObject gameObject;
	
	public ClientResolve(GameObject gameObject, Network network){
		this.network = network;
		this.gameObject = gameObject;
	}
	
	//print any messages get from server
	public void run(){
		while (true){
			String receive = network.receive();
			resolve(receive);
		}
	}
	
	
	private void resolve(String _readline){
		if(_readline.equals("LOAD:")){
			gameObject.setReady(true);
		}else{
			System.out.println(_readline);
		}
	}
}